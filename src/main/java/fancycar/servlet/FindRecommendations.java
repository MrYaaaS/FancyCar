package fancycar.servlet;

import fancycar.dal.CarListingsDao;
import fancycar.dal.RecommendationsDao;
import fancycar.model.CarListings;
import fancycar.model.Recommendations;
import fancycar.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/findrecommendations")
public class FindRecommendations extends HttpServlet {

	protected RecommendationsDao recommendationsDao;

	@Override
	public void init() throws ServletException {
		recommendationsDao = RecommendationsDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<Recommendations> recommendations = new ArrayList<>();

		HttpSession session = req.getSession(true);
		Users user = (Users) session.getAttribute("user");
		String userName = user.getUserName();

		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Please enter a valid name.");
		} else {
			String query = req.getQueryString();
			if (query == null) {
				try {
					recommendations = recommendationsDao.getRecommendationsByUserName(userName);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
				messages.put("success", "Displaying results for " + userName);
				messages.put("previoususername", userName);
				req.setAttribute("recommendations", recommendations);
				req.getRequestDispatcher("/FindRecommendations.jsp").forward(req, resp);
			} else if (query.startsWith("vin")) {
				String[] queryInfo = query.split("=");
				String vin = queryInfo[1];
				try {
					recommendations = recommendationsDao.getRecommendationsByVin(vin);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
				messages.put("success", "Displaying results for " + vin);
				messages.put("previousvin", vin);
				req.setAttribute("vin", vin);
				req.setAttribute("recommendations", recommendations);
				req.getRequestDispatcher("/DisplayRecommendations.jsp").forward(req, resp);
			}
		}

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<Recommendations> recommendations = new ArrayList<>();

		HttpSession session = req.getSession(true);
		Users user = (Users) session.getAttribute("user");
		String userName = user.getUserName();

		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Please enter a valid name.");
		} else {
			String vin = req.getParameter("vin");
			Date created = new Date();

			try {
				CarListingsDao carListingsDao = CarListingsDao.getInstance();
				CarListings car = carListingsDao.getCarListingByVin(vin);
				Recommendations recommendation = new Recommendations(user, car, created);
				recommendation = recommendationsDao.create(recommendation);
				recommendations = recommendationsDao.getRecommendationsByVin(vin);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + vin);
			messages.put("previousvin", vin);
			req.setAttribute("vin", vin);
			req.setAttribute("recommendations", recommendations);
			req.getRequestDispatcher("/DisplayRecommendations.jsp").forward(req, resp);
		}
	}
}
