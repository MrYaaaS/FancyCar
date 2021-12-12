package fancycar.servlet;

import fancycar.dal.RecommendationsDao;
import fancycar.model.CarListings;
import fancycar.model.Recommendations;
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

@WebServlet("/deleterecommendation")
public class DeleteRecommendationServlet extends UserSavedCarsServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Recommendations> recommendations = new ArrayList<>();

        String query = req.getQueryString();
        String[] queryInfo = query.split("=");
        int recommendationId = Integer.parseInt(queryInfo[1]);

        HttpSession session = req.getSession(true);
        Users user = (Users) session.getAttribute("user");
        String userName = user.getUserName();
        if (user == null) {
            messages.put("success", "not signed in.");
        } else {
            try {
                Recommendations recommendation = new Recommendations(recommendationId);
                RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
                recommendationsDao.delete(recommendation);
                recommendations = recommendationsDao.getRecommendationsByUserName(userName);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "created");
        }
        req.setAttribute("recommendations", recommendations);
        req.getRequestDispatcher("/FindRecommendations.jsp").forward(req, resp);
    }
}
