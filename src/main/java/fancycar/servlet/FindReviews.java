package fancycar.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fancycar.dal.CarListingsDao;
import fancycar.model.*;
import fancycar.dal.ReviewsDao;

@WebServlet("/findreviews")
public class FindReviews extends HttpServlet {

	protected ReviewsDao reviewsDao;

	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<Reviews> reviews = new ArrayList<Reviews>();

		HttpSession session = req.getSession(true);
		Users user = (Users) session.getAttribute("user");
		String userName = user.getUserName();
		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Please enter a valid name.");
		} else {
			String query = req.getQueryString();
			if (query == null) {

				try {
					reviews = reviewsDao.getReviewsByUserName(userName);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
				messages.put("success", "Displaying results for " + userName);
				messages.put("previoususername", userName);

				req.setAttribute("reviews", reviews);
				req.getRequestDispatcher("/FindReviews.jsp").forward(req, resp);
			} else if (query.startsWith("vin")) {
				String[] queryInfo = query.split("=");
				String vin = queryInfo[1];

				try {
					reviews = reviewsDao.getReviewsByVin(vin);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
				messages.put("success", "Displaying results for " + vin);
				messages.put("previousvin", vin);

				req.setAttribute("reviews", reviews);
				req.setAttribute("vin", vin);
				req.getRequestDispatcher("/DisplayReviews.jsp").forward(req, resp);
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<Reviews> reviews = new ArrayList<Reviews>();

		HttpSession session = req.getSession(true);
		Users user = (Users) session.getAttribute("user");
		String userName = user.getUserName();
		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Please enter a valid name.");
		} else {
			String vin = req.getParameter("vin");
			Date created = new Date();
			String content = req.getParameter("content");
			BigDecimal rating = new BigDecimal(req.getParameter("rating"));

			try {
				CarListingsDao carListingsDao = CarListingsDao.getInstance();
				CarListings car = carListingsDao.getCarListingByVin(vin);
				Reviews review = new Reviews(created, content, rating, user, car);
				review = reviewsDao.create(review);
				reviews = reviewsDao.getReviewsByVin(vin);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}

			req.setAttribute("reviews", reviews);
			req.setAttribute("vin", vin);
			req.getRequestDispatcher("/DisplayReviews.jsp").forward(req, resp);
		}
	}
}
