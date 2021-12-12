package fancycar.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fancycar.model.*;

public class SellersDao {
	protected ConnectionManager connectionManager;
	private static SellersDao instance = null;

	protected SellersDao() {
		connectionManager = new ConnectionManager();
	}

	public static SellersDao getInstance() {
		if (instance == null) {
			instance = new SellersDao();
		}
		return instance;
	}

	public Sellers getSellerById(int sellerId) throws SQLException {
		String selectSeller = "SELECT SellerId, Name, Zip, HasFranchise " + "FROM Sellers " + "WHERE SellerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSeller);
			selectStmt.setInt(1, sellerId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultSellerId = results.getInt("SellerId");
				String name = results.getString("Name");
				String zip = results.getString("Zip");
				boolean hasFranchise = results.getBoolean("HasFranchise");
				Sellers seller = new Sellers(resultSellerId, name, zip, hasFranchise);
				return seller;
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

	public List<Sellers> getSellersByZip(String zip) throws SQLException {
		List<Sellers> sellers = new ArrayList<Sellers>();
		String selectSellers = "SELECT SellerId, Name, Zip, HasFranchise " + "FROM Sellers " + "WHERE Zip=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSellers);
			selectStmt.setString(1, zip);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int sellerId = results.getInt("SellerId");
				String name = results.getString("Name");
				String resultZip = results.getString("Zip");
				boolean hasFranchise = results.getBoolean("HasFranchise");
				Sellers seller = new Sellers(sellerId, name, resultZip, hasFranchise);
				sellers.add(seller);
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
		return sellers;
	}

	public List<Sellers> getSellersByHasFranchise(boolean hasFranchise) throws SQLException {
		List<Sellers> sellers = new ArrayList<Sellers>();
		String selectSellers = "SELECT SellerId, Name, Zip, HasFranchise " + "FROM Sellers " + "WHERE HasFranchise=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSellers);
			selectStmt.setBoolean(1, hasFranchise);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int sellerId = results.getInt("SellerId");
				String name = results.getString("Name");
				String zip = results.getString("Zip");
				boolean resultHasFranchise = results.getBoolean("HasFranchise");
				Sellers seller = new Sellers(sellerId, name, zip, resultHasFranchise);
				sellers.add(seller);
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
		return sellers;
	}
}
