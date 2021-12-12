package fancycar.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fancycar.model.*;
import fancycar.model.CarModels.FuelType;

public class CarModelsDao {
	protected ConnectionManager connectionManager;
	private static CarModelsDao instance = null;

	protected CarModelsDao() {
		connectionManager = new ConnectionManager();
	}

	public static CarModelsDao getInstance() {
		if (instance == null) {
			instance = new CarModelsDao();
		}
		return instance;
	}

	public CarModels getCarModelById(int modelId) throws SQLException {
		String selectCarModel = "SELECT * " + "FROM CarModels " + "WHERE ModelId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCarModel);
			selectStmt.setInt(1, modelId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultModelId = results.getInt("ModelId");
				String brand = results.getString("Brand");
				String menu = results.getString("Model");
				// results.getString("FuelType").toUpperCase()
				FuelType fuelType;
				try {
					fuelType = CarModels.FuelType
							.valueOf(results.getString("FuelType").toUpperCase().trim());
							
					} catch (IllegalArgumentException exception) {
						fuelType = FuelType.UNKNOWN;
					}
				CarModels carModel = new CarModels(resultModelId, brand, menu, fuelType);
				return carModel;
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

	public List<CarModels> getCarModelsByBrand(String brand) throws SQLException {
		List<CarModels> carModels = new ArrayList<CarModels>();
		String selectCarModels = "SELECT * " + "FROM CarModels " + "WHERE Brand=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCarModels);
			selectStmt.setString(1, brand);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int modelId = results.getInt("ModelId");
				String resultBrand = results.getString("Brand");
				String menu = results.getString("Model");
				String fuelTypeString = results.getString("FuelType").toUpperCase().trim();
				// fuelTypeString.equals("GASOLINE") || fuelTypeString.equals("DIESEL")
				// ||fuelTypeString.equals("ELECTRICAL") ||fuelTypeString.equals("HYBRID")
				if (fuelTypeString.equals("GASOLINE") || fuelTypeString.equals("DIESEL")
						|| fuelTypeString.equals("ELECTRICAL") || fuelTypeString.equals("HYBRID")) {
//					CarModels.FuelType f = CarModels.FuelType.valueOf("GASOLINE");
					CarModels.FuelType fuelType = CarModels.FuelType
							.valueOf(results.getString("FuelType").toUpperCase().trim());
					CarModels carModel = new CarModels(modelId, resultBrand, menu, fuelType);
					carModels.add(carModel);
				}

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
		return carModels;
	}
}
