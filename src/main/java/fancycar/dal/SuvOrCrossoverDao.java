package fancycar.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fancycar.model.*;

public class SuvOrCrossoverDao extends CarModelsDao {
	private static SuvOrCrossoverDao instance = null;

	protected SuvOrCrossoverDao() {
		super();
	}

	public static SuvOrCrossoverDao getInstance() {
		if (instance == null) {
			instance = new SuvOrCrossoverDao();
		}
		return instance;
	}

	public SuvOrCrossover getSuvOrCrossoverById(int suvOrCrossoverId) throws SQLException {
		String selectSuvOrCrossover = "SELECT SuvOrCrossover.ModelId AS ModelId, Brand, Model, FuelType, NumberOfSeats "
				+ "FROM SuvOrCrossover INNER JOIN CarModels " + "  ON SuvOrCrossover.ModelId = CarModels.ModelId "
				+ "WHERE SuvOrCrossover.ModelId=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSuvOrCrossover);
			selectStmt.setInt(1, suvOrCrossoverId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultModelId = results.getInt("ModelId");
				String brand = results.getString("Brand");
				String menu = results.getString("Model");
				CarModels.FuelType fuelType = CarModels.FuelType.valueOf(results.getString("FuelType"));
				int numberOfSeats = results.getInt("NumberOfSeats");
				SuvOrCrossover suvOrCrossover = new SuvOrCrossover(resultModelId, brand, menu, fuelType, numberOfSeats);
				return suvOrCrossover;
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

	public List<SuvOrCrossover> getSuvOrCrossoversByNumberOfSeats(int numberOfSeats) throws SQLException {
		List<SuvOrCrossover> suvOrCrossovers = new ArrayList<SuvOrCrossover>();
		String selectSuvOrCrossovers = "SELECT ModelId, Brand, Model, FuelType, NumberOfSeats " + "FROM SuvOrCrossover "
				+ "WHERE NumberOfSeats=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSuvOrCrossovers);
			selectStmt.setInt(1, numberOfSeats);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int modelId = results.getInt("ModelId");
				String brand = results.getString("Brand");
				String menu = results.getString("Model");
				CarModels.FuelType fuelType = CarModels.FuelType.valueOf(results.getString("FuelType"));
				int resultNumberOfSeats = results.getInt("NumberOfSeats");
				SuvOrCrossover suvOrCrossover = new SuvOrCrossover(modelId, brand, menu, fuelType, resultNumberOfSeats);
				suvOrCrossovers.add(suvOrCrossover);
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
		return suvOrCrossovers;
	}

	public List<SuvOrCrossover> getSuvOrCrossoversByBrand(String brand) throws SQLException {
		List<SuvOrCrossover> suvOrCrossovers = new ArrayList<SuvOrCrossover>();
		String selectSuvOrCrossovers = "SELECT ModelId, SuvOrCrossover.Brand AS Brand, Model, FuelType, GasTank "
				+ "FROM SuvOrCrossover INNER JOIN CarModels " + "  ON SuvOrCrossover.Brand = CarModels.Brand "
				+ "WHERE SuvOrCrossover.Brand=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSuvOrCrossovers);
			selectStmt.setString(1, brand);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int modelId = results.getInt("ModelId");
				String resultBrand = results.getString("Brand");
				String menu = results.getString("Model");
				CarModels.FuelType fuelType = CarModels.FuelType.valueOf(results.getString("FuelType"));
				int numberOfSeats = results.getInt("NumberOfSeats");
				SuvOrCrossover suvOrCrossover = new SuvOrCrossover(modelId, resultBrand, menu, fuelType, numberOfSeats);
				suvOrCrossovers.add(suvOrCrossover);
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
		return suvOrCrossovers;
	}
}
