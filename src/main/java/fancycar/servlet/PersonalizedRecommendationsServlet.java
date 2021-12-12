package fancycar.servlet;

import fancycar.dal.PersonalizedRecommendationsDao;
import fancycar.model.CarListings;
import fancycar.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/personalizedrecommendations")
public class PersonalizedRecommendationsServlet extends HttpServlet {

    protected PersonalizedRecommendationsDao personalizedRecommendationsDao;
    @Override
    public void init() throws ServletException {
        personalizedRecommendationsDao = PersonalizedRecommendationsDao.getInstance();
    }

    /**
     * Get Personalized Recommendations for user
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        HttpSession session = req.getSession(true);
        Users user = (Users) session.getAttribute("user");
        String userName = user.getUserName();
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please sign in as a valid user.");
        } else {
            List<CarListings> recommendedCarList = null;
            try {
                recommendedCarList = personalizedRecommendationsDao.getPersonalizedRecommendaitonByUserName(userName);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + userName);
            messages.put("previous username", userName);
            req.setAttribute("CarListings", recommendedCarList);
        }

        req.getRequestDispatcher("/PersonalizedRecommendations.jsp").forward(req, resp);
    }

}
