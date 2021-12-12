package fancycar.servlet;

import fancycar.model.CarListings;
import fancycar.model.UserSavedCars;
import fancycar.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/deleteusersavedcars")
public class DeleteSavedCarServlet extends UserSavedCarsServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<CarListings> savedCarsList = new ArrayList<>();

        String query = req.getQueryString();
        String[] queryInfo = query.split("=");
        String vin = queryInfo[1];

        HttpSession session = req.getSession(true);
        Users user = (Users) session.getAttribute("user");
        String userName = user.getUserName();
        if (user == null) {
            messages.put("success", "not signed in.");
        } else {
            try {
                UserSavedCars savedCar = new UserSavedCars(user, new CarListings(vin));
                savedCar = userSavedCarsDao.delete(savedCar);
                savedCarsList = userSavedCarsDao.getCarsByUserName(userName);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "created");
        }
        req.setAttribute("CarListings", savedCarsList);
        req.getRequestDispatcher("/UserSavedCars.jsp").forward(req, resp);
    }
}
