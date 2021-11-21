package fancycar.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fancycar.model.*;

public class ReviewsDao {
	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;

	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}

	public static ReviewsDao getInstance() {
		if (instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	

	public Reviews create(Reviews review) throws SQLException {
		String insertReview = "INSERT INTO Reviews(Created, Content, Rating, UserName, VIN) " + "VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setTimestamp(1, new Timestamp(review.getCreated().getTime()));
			insertStmt.setString(2, review.getContent());
			insertStmt.setBigDecimal(3, review.getRating());
			insertStmt.setString(4, review.getUser().getUserName());
			insertStmt.setString(5, review.getCar().getVin());
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if (resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
			if (resultKey != null) {
				resultKey.close();
			}
		}
	}

	public Reviews getReviewById(int reviewId) throws SQLException {
		String selectReview = "SELECT ReviewId, Created, Content, Rating, UserName, VIN " + "FROM Reviews "
				+ "WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			CarListingsDao carListingsDao = CarListingsDao.getInstance();
			if (results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String content = results.getString("Content");
				BigDecimal rating = results.getBigDecimal("Rating");
				String userName = results.getString("UserName");
				String vin = results.getString("VIN");

				Users user = usersDao.getUserByUserName(userName);
				CarListings carListing = carListingsDao.getCarListingByVin(vin);
				Reviews review = new Reviews(resultReviewId, created, content, rating, user, carListing);
				return review;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}

	public List<Reviews> getReviewsByUserName(String userName) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews = "SELECT ReviewId, Created, Content, Rating, UserName, VIN " + "FROM Reviews "
				+ "WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			CarListingsDao carListingsDao = CarListingsDao.getInstance();
			while (results.next()) {
				int reviewId = results.getInt("ReviewId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String content = results.getString("Content");
				BigDecimal rating = results.getBigDecimal("Rating");
				String resultUserName = results.getString("UserName");
				String vin = results.getString("VIN");

				Users resultUser = usersDao.getUserByUserName(resultUserName);
				CarListings carListing = carListingsDao.getCarListingByVin(vin);
				Reviews review = new Reviews(reviewId, created, content, rating, resultUser, carListing);
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return reviews;
	}

	public List<Reviews> getReviewsByVin(String vin) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews = "SELECT ReviewId, Created, Content, Rating, UserName, VIN " + "FROM Reviews "
				+ "WHERE VIN=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1, vin);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			CarListingsDao carListingsDao = CarListingsDao.getInstance();
			while (results.next()) {
				int reviewId = results.getInt("ReviewId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String content = results.getString("Content");
				BigDecimal rating = results.getBigDecimal("Rating");
				String userName = results.getString("UserName");
				String resultVin = results.getString("VIN");

				Users user = usersDao.getUserByUserName(userName);
				CarListings resultCarListing = carListingsDao.getCarListingByVin(resultVin);
				Reviews review = new Reviews(reviewId, created, content, rating, user, resultCarListing);
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return reviews;
	}

	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Reviews instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
