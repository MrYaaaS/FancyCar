package fancycar.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fancycar.model.*;

public class PickupTruckDao extends CarModelsDao {
	private static PickupTruckDao instance = null;

	protected PickupTruckDao() {
		super();
	}

	public static PickupTruckDao getInstance() {
		if (instance == null) {
			instance = new PickupTruckDao();
		}
		return instance;
	}

	public PickupTruck getPickupTruckById(int pickupTruckId) throws SQLException {
		String selectPickupTruck = "SELECT PickupTruck.ModelId AS ModelId, Brand, Model, FuelType, BedType, BedLength "
				+ "FROM PickupTruck INNER JOIN CarModels " + "  ON PickupTruck.ModelId = CarModels.ModelId "
				+ "WHERE PickupTruck.ModelId=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPickupTruck);
			selectStmt.setInt(1, pickupTruckId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultModelId = results.getInt("ModelId");
				String brand = results.getString("Brand");
				String menu = results.getString("Model");
				CarModels.FuelType fuelType = CarModels.FuelType.valueOf(results.getString("FuelType"));
				String bedType = results.getString("BedType");
				BigDecimal bedLength = results.getBigDecimal("BedLength");
				PickupTruck pickupTruck = new PickupTruck(resultModelId, brand, menu, fuelType, bedType, bedLength);
				return pickupTruck;
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

	public List<PickupTruck> getPickupTrucksByBedType(String bedType) throws SQLException {
		List<PickupTruck> pickupTrucks = new ArrayList<PickupTruck>();
		String selectPickupTrucks = "SELECT ModelId, Brand, Model, FuelType, BedType, BedLength " + "FROM PickupTruck "
				+ "WHERE BedType=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPickupTrucks);
			selectStmt.setString(1, bedType);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int modelId = results.getInt("ModelId");
				String brand = results.getString("Brand");
				String menu = results.getString("Model");
				CarModels.FuelType fuelType = CarModels.FuelType.valueOf(results.getString("FuelType"));
				String resultBedType = results.getString("BedType");
				BigDecimal bedLength = results.getBigDecimal("BedLength");
				PickupTruck pickupTruck = new PickupTruck(modelId, brand, menu, fuelType, resultBedType, bedLength);
				pickupTrucks.add(pickupTruck);
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
		return pickupTrucks;
	}

	public List<PickupTruck> getPickupTrucksByBedLength(BigDecimal bedLength) throws SQLException {
		List<PickupTruck> pickupTrucks = new ArrayList<PickupTruck>();
		String selectPickupTrucks = "SELECT ModelId, Brand, Model, FuelType, BedType, BedLength " + "FROM PickupTruck "
				+ "WHERE BedLength=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPickupTrucks);
			selectStmt.setBigDecimal(1, bedLength);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int modelId = results.getInt("ModelId");
				String brand = results.getString("Brand");
				String menu = results.getString("Model");
				CarModels.FuelType fuelType = CarModels.FuelType.valueOf(results.getString("FuelType"));
				String bedType = results.getString("BedType");
				BigDecimal resultBedLength = results.getBigDecimal("BedLength");
				PickupTruck pickupTruck = new PickupTruck(modelId, brand, menu, fuelType, bedType, resultBedLength);
				pickupTrucks.add(pickupTruck);
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
		return pickupTrucks;
	}

	public List<PickupTruck> getPickupTrucksByBrand(String brand) throws SQLException {
		List<PickupTruck> pickupTrucks = new ArrayList<PickupTruck>();
		String selectPickupTrucks = "SELECT ModelId, PickupTruck.Brand AS Brand, Model, FuelType, GasTank "
				+ "FROM PickupTruck INNER JOIN CarModels " + "  ON PickupTruck.Brand = CarModels.Brand "
				+ "WHERE PickupTruck.Brand=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPickupTrucks);
			selectStmt.setString(1, brand);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int modelId = results.getInt("ModelId");
				String resultBrand = results.getString("Brand");
				String menu = results.getString("Model");
				CarModels.FuelType fuelType = CarModels.FuelType.valueOf(results.getString("FuelType"));
				String bedType = results.getString("BedType");
				BigDecimal bedLength = results.getBigDecimal("BedLength");
				PickupTruck pickupTruck = new PickupTruck(modelId, resultBrand, menu, fuelType, bedType, bedLength);
				pickupTrucks.add(pickupTruck);
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
		return pickupTrucks;
	}
}
