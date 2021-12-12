package fancycar.dal;

import fancycar.model.CarListings;
import fancycar.model.Recommendations;
import fancycar.model.Users;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecommendationsDao {
	protected ConnectionManager connectionManager;

	private static RecommendationsDao instance = null;

	protected RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}

	public static RecommendationsDao getInstance() {
		if (instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}
	

	public Recommendations create(Recommendations recommendation) throws SQLException {
		String insertRecommendation = "INSERT INTO Recommendations(UserName, VIN, Created) " + "VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendation, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, recommendation.getUser().getUserName());
			insertStmt.setString(2, recommendation.getCar().getVin());
			insertStmt.setTimestamp(3, new Timestamp(recommendation.getCreated().getTime()));
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int recommendationId = -1;
			if (resultKey.next()) {
				recommendationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recommendation.setRecommendationId(recommendationId);
			return recommendation;
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

	public Recommendations getRecommendationById(int recommendationId) throws SQLException {
		String selectRecommendation = "SELECT RecommendationId, Created, UserName, VIN " + "FROM Recommendations "
				+ "WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setInt(1, recommendationId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			CarListingsDao carListingsDao = CarListingsDao.getInstance();
			if (results.next()) {
				int resultRecommendationId = results.getInt("RecommendationId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String userName = results.getString("UserName");
				String vin = results.getString("VIN");

				Users user = usersDao.getUserByUserName(userName);
				CarListings carListing = carListingsDao.getCarListingByVin(vin);
				Recommendations recommendation = new Recommendations(resultRecommendationId, user, carListing, created);
				return recommendation;
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

	public List<Recommendations> getRecommendationsByUserName(String userName) throws SQLException {
		List<Recommendations> recommendations = new ArrayList<>();
		String selectRecommendations = "SELECT RecommendationId, Created, UserName, VIN " + "FROM Recommendations "
				+ "WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			CarListingsDao carListingsDao = CarListingsDao.getInstance();
			while (results.next()) {
				int recommendationId = results.getInt("RecommendationId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String resultUserName = results.getString("UserName");
				String vin = results.getString("VIN");

				Users resultUser = usersDao.getUserByUserName(resultUserName);
				CarListings carListing = carListingsDao.getCarListingByVin(vin);
				Recommendations recommendation = new Recommendations(recommendationId, resultUser, carListing, created);
				recommendations.add(recommendation);
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
		return recommendations;
	}

	public List<Recommendations> getRecommendationsByVin(String vin) throws SQLException {
		List<Recommendations> recommendations = new ArrayList<Recommendations>();
		String selectRecommendations = "SELECT RecommendationId, Created, UserName, VIN " + "FROM Recommendations "
				+ "WHERE VIN=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setString(1, vin);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			CarListingsDao carListingsDao = CarListingsDao.getInstance();
			while (results.next()) {
				int recommendationId = results.getInt("RecommendationId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String userName = results.getString("UserName");
				String resultVin = results.getString("VIN");

				Users user = usersDao.getUserByUserName(userName);
				CarListings resultCarListing = carListingsDao.getCarListingByVin(resultVin);
				Recommendations recommendation = new Recommendations(recommendationId, user, resultCarListing, created);
				recommendations.add(recommendation);
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
		return recommendations;
	}

	public Recommendations delete(Recommendations recommendation) throws SQLException {
		String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecommendation);
			deleteStmt.setInt(1, recommendation.getRecommendationId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Recommendations instance.
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
