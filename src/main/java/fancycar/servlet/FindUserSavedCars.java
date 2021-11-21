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
public class FindUserSavedCars extends HttpServlet {
  protected UserSavedCarsDao userSavedCarsDao;
  @Override
  public void init() throws ServletException {
    userSavedCarsDao = UserSavedCarsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    List<CarListings> savedCarList = new ArrayList<>();
    HttpSession session = req.getSession(true);
    Users user = (Users) session.getAttribute("user");
    String userName = user.getUserName();
    if (userName == null || userName.trim().isEmpty()) {
      messages.put("success", "Please enter a valid name.");
    } else {
      try {
        savedCarList = userSavedCarsDao.getCarsByUserName(userName);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + userName);
      messages.put("previous username", userName);
    }
    req.setAttribute("CarListings", savedCarList);
    req.getRequestDispatcher("/UserSavedCars.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    List<UserSavedCars> savedCarsList = new ArrayList<>();
//    String userName = req.getParameter("username");
//    if (userName == null || userName.trim().isEmpty()) {
//      messages.put("sucess", "Please enter a valid name.");
//    } else {
//      try {
//        savedCarsList = userSavedCarsDao.getCarsByUserName(userName);
//      } catch (SQLException e) {
//        e.printStackTrace();
//        throw new IOException(e);
//      }
//      messages.put("success", "Displaying results for " + userName);
//    }
//    req.setAttribute("savedCarsList", savedCarsList);
    req.getRequestDispatcher("/UserSavedCars.jsp").forward(req, resp);
  }
}
