package fancycar.servlet;

import fancycar.dal.UserSavedCarsDao;
import fancycar.model.CarListings;
import fancycar.model.UserSavedCars;
import fancycar.model.Users;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/usersavedcars")
public class UserSavedCarsServlet extends HttpServlet {
  protected UserSavedCarsDao userSavedCarsDao;
  @Override
  public void init() throws ServletException {
    userSavedCarsDao = UserSavedCarsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    HttpSession session = req.getSession(true);
    Users user = (Users) session.getAttribute("user");
    String userName = user.getUserName();
    if (userName == null || userName.trim().isEmpty()) {
      messages.put("success", "Please enter a valid name.");
    } else {
      String query = req.getQueryString();
      if (query == null) {
        List<CarListings> savedCarList = null;
        try {
          savedCarList = userSavedCarsDao.getCarsByUserName(userName);
        } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
        messages.put("success", "Displaying results for " + userName);
        messages.put("previous username", userName);
        req.setAttribute("CarListings", savedCarList);
        req.getRequestDispatcher("/UserSavedCars.jsp").forward(req, resp);
      } else if (query.startsWith("vin")) {
        String[] queryInfo = query.split("=");
        String vin = queryInfo[1];
        List<Users> users = null;
        try {
          users = userSavedCarsDao.getUsersByVin(vin);
        } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
        messages.put("success", "Displaying results for " + vin);
        messages.put("previousvin", vin);

        req.setAttribute("users", users);
        req.setAttribute("vin", vin);

        req.getRequestDispatcher("/DisplaySavedCarUsers.jsp").forward(req, resp);
      }
    }

  }

  // create saved car
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    HttpSession session = req.getSession(true);
    Users user = (Users) session.getAttribute("user");
    String vin = req.getParameter("vin");

    List<Users> users = null;
    if (user == null) {
      messages.put("success", "not signed in.");
    } else {
      try {
        UserSavedCars savedCar = new UserSavedCars(user, new CarListings(vin));
        savedCar = userSavedCarsDao.create(savedCar);
        users = userSavedCarsDao.getUsersByVin(vin);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "created");
    }
    //TODO
    req.setAttribute("users", users);
    req.getRequestDispatcher("/DisplaySavedCarUsers.jsp").forward(req, resp);
  }
}
