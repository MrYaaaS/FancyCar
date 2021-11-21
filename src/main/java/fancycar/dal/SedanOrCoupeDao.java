package fancycar.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fancycar.model.*;

public class SedanOrCoupeDao extends CarModelsDao {
	private static SedanOrCoupeDao instance = null;

	protected SedanOrCoupeDao() {
		super();
	}

	public static SedanOrCoupeDao getInstance() {
		if (instance == null) {
			instance = new SedanOrCoupeDao();
		}
		return instance;
	}

	public SedanOrCoupe getSedanOrCoupeById(int sedanOrCoupeId) throws SQLException {
		String selectSedanOrCoupe = "SELECT SedanOrCoupe.ModelId AS ModelId, Brand, Model, FuelType, GasTank "
				+ "FROM SedanOrCoupe INNER JOIN CarModels " + "  ON SedanOrCoupe.ModelId = CarModels.ModelId "
				+ "WHERE SedanOrCoupe.ModelId=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSedanOrCoupe);
			selectStmt.setInt(1, sedanOrCoupeId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultModelId = results.getInt("ModelId");
				String brand = results.getString("Brand");
				String menu = results.getString("Model");
				CarModels.FuelType fuelType = CarModels.FuelType.valueOf(results.getString("FuelType"));
				BigDecimal gasTank = results.getBigDecimal("GasTank");
				SedanOrCoupe sedanOrCoupe = new SedanOrCoupe(resultModelId, brand, menu, fuelType, gasTank);
				return sedanOrCoupe;
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

	public List<SedanOrCoupe> getSedanOrCoupesByGasTank(BigDecimal gasTank) throws SQLException {
		List<SedanOrCoupe> sedanOrCoupes = new ArrayList<SedanOrCoupe>();
		String selectSedanOrCoupes = "SELECT ModelId, Brand, Model, FuelType, GasTank " + "FROM SedanOrCoupe "
				+ "WHERE GasTank=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSedanOrCoupes);
			selectStmt.setBigDecimal(1, gasTank);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int modelId = results.getInt("ModelId");
				String brand = results.getString("Brand");
				String menu = results.getString("Model");
				CarModels.FuelType fuelType = CarModels.FuelType.valueOf(results.getString("FuelType"));
				BigDecimal resultGasTank = results.getBigDecimal("GasTank");
				SedanOrCoupe sedanOrCoupe = new SedanOrCoupe(modelId, brand, menu, fuelType, resultGasTank);
				sedanOrCoupes.add(sedanOrCoupe);
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
		return sedanOrCoupes;
	}

	public List<SedanOrCoupe> getSedanOrCoupesByBrand(String brand) throws SQLException {
		List<SedanOrCoupe> sedanOrCoupes = new ArrayList<SedanOrCoupe>();
		String selectSedanOrCoupes = "SELECT ModelId, SedanOrCoupe.Brand AS Brand, Model, FuelType, GasTank "
				+ "FROM SedanOrCoupe INNER JOIN CarModels " + "  ON SedanOrCoupe.Brand = CarModels.Brand "
				+ "WHERE SedanOrCoupe.Brand=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSedanOrCoupes);
			selectStmt.setString(1, brand);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int modelId = results.getInt("ModelId");
				String resultBrand = results.getString("Brand");
				String menu = results.getString("Model");
				CarModels.FuelType fuelType = CarModels.FuelType.valueOf(results.getString("FuelType"));
				BigDecimal gasTank = results.getBigDecimal("GasTank");
				SedanOrCoupe sedanOrCoupe = new SedanOrCoupe(modelId, resultBrand, menu, fuelType, gasTank);
				sedanOrCoupes.add(sedanOrCoupe);
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
		return sedanOrCoupes;
	}
}
