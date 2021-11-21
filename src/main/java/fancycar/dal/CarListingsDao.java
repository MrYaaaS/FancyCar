package fancycar.dal;
import fancycar.model.*;

import fancycar.model.CarModels.FuelType;
import java.sql.*;

/**
 * This class is modified from "BlogPost. java" from the sample code TODO: CHECK
 * SQL STRING.
 */
public class CarListingsDao {

protected ConnectionManager connectionManager;
	
	
	private static CarListingsDao instance = null;
	protected CarListingsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CarListingsDao getInstance() {
		if(instance == null) {
			instance = new CarListingsDao();
		}
		return instance;
	}

	/**
	 * To create a new car listing
	 * 
	 * @param carListing represent a new car listing
	 * @return given car listing
	 * @throws SQLException
	 */
	public CarListings create(CarListings carListing) throws SQLException {

		// CarListing needs 12 arguments
		String insertCarListing = "INSERT INTO CarListings(VIN, ModelId, SellersId, PictureURL, Year, City, ExteriorColor, InteriorColor, Mileage, HasAccident, IsCPO, Price) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();

			insertStmt = connection.prepareStatement(insertCarListing, Statement.RETURN_GENERATED_KEYS);
			// set up the argument for car listings
			// carList: 1.String vin, 2.int carmodel, 3.int sellers, 4.String pictureUrl,
			// 5.int year,
			// 6.String city, 7.String exteriorColor, 8.String interiorColor, 9.int mileage,
			// 10.boolean hasAccident, 11.boolean isCPO, 12.int price
			insertStmt.setString(1, carListing.getVin());
			insertStmt.setInt(2, carListing.getCarmodel().getModelId());
			insertStmt.setInt(3, carListing.getSellers().getSellerId());
			insertStmt.setString(4, carListing.getPictureUrl());
			insertStmt.setInt(5, carListing.getYear());
			insertStmt.setString(6, carListing.getCity());
			insertStmt.setString(7, carListing.getExteriorColor());
			insertStmt.setString(8, carListing.getInteriorColor());
			insertStmt.setInt(9, carListing.getMileage());
			insertStmt.setBoolean(10, carListing.isHasAccident());
			insertStmt.setBoolean(11, carListing.isCPO());
			insertStmt.setInt(12, carListing.getPrice());

			// update
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			// the car listing does not have id. the following could be ignored.
			resultKey = insertStmt.getGeneratedKeys();
			@SuppressWarnings("unused")
			int carId = -1;
			if (resultKey.next()) {
				carId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			return carListing;

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

//    SELECT CarListings.VIN,
//        CarModels.ModelId, CarModels.Brand, CarModels.Model, CarModels.FuelType,
//        Sellers.SellerId, Sellers.Name, Sellers.Zip, Sellers.HasFranchise,
//        CarListings.PictureUrl, CarListings.Year, CarListings.City, CarListings.ExteriorColor,
//        CarListings.InteriorColor, CarListings.Mileage, CarListings.HasAccident, CarListings.IsCPO, CarListings.Price
//    FROM CarModels
//        JOIN CarListings ON CarModels.ModelId = CarListings.ModelId
//        JOIN Sellers ON CarListings.SellersId = Sellers.SellersId
//    WHERE VIN=?

//    CarListings(String vin, CarModels carmodel, Sellers sellers, String pictureUrl, int year, String city,
//                String exteriorColor, String interiorColor, int mileage, boolean hasAccident, boolean isCPO, int price) {
	public CarListings getCarListingByVin(String vin) throws SQLException {
		String selectCarListings = "SELECT CarListings.VIN, CarModels.ModelId, CarModels.Brand, CarModels.Model, CarModels.FuelType, "
				+ "Sellers.SellerId, Sellers.Name, Sellers.Zip, Sellers.HasFranchise, CarListings.PictureUrl, "
				+ "CarListings.Year, CarListings.City, CarListings.ExteriorColor, CarListings.InteriorColor, "
				+ "CarListings.Mileage, CarListings.HasAccident, CarListings.IsCPO, CarListings.Price "
				+ "FROM CarModels " + "JOIN CarListings ON CarModels.ModelId = CarListings.ModelId "
				+ "JOIN Sellers ON CarListings.SellerId = Sellers.SellerId " + "WHERE CarListings.VIN=?";

		Connection connection = null;
		PreparedStatement selectStmt = null;

		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCarListings);
			selectStmt.setString(1, vin);
			results = selectStmt.executeQuery();

			if (results.next()) {
				String newVin = results.getString("VIN");
				// car model need 4 params: int modelId, String brand, String model, FuelType
				// fuelType
				FuelType fuelType;
				try {
					fuelType = CarModels.FuelType
							.valueOf(results.getString("FuelType").toUpperCase().trim());
							
					} catch (IllegalArgumentException exception) {
						fuelType = FuelType.UNKNOWN;
					}
				CarModels newCarModel = new CarModels(results.getInt("ModelId"), results.getString("Brand"),
						results.getString("Model"), fuelType);
				// sellers need 4 params: int sellerId, String name, String zip, boolean
				// hasFranchise
				Sellers newSellers = new Sellers(results.getInt("SellerId"), results.getString("Name"),
						results.getString("Zip"), results.getBoolean("HasFranchise"));
				String newPictureUrl = results.getString("PictureUrl");
				int newYear = results.getInt("Year");
				String newCity = results.getString("City");
				String newExteriorColor = results.getString("ExteriorColor");
				String newInteriorColor = results.getString("InteriorColor");
				int newMileage = results.getInt("Mileage");
				boolean newHasAccident = results.getBoolean("HasAccident");
				boolean newIsCPO = results.getBoolean("IsCPO");
				int newPrice = results.getInt("Price");

				CarListings carListing = new CarListings(newVin, newCarModel, newSellers, newPictureUrl, newYear,
						newCity, newExteriorColor, newInteriorColor, newMileage, newHasAccident, newIsCPO, newPrice);
				return carListing;
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

}
