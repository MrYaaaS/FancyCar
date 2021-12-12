package fancycar.dal;

import fancycar.model.CarListings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonalizedRecommendationsDao {

    protected ConnectionManager connectionManager;

    private static PersonalizedRecommendationsDao instance = null;
    protected PersonalizedRecommendationsDao() {
        connectionManager = new ConnectionManager();
    }
    public static PersonalizedRecommendationsDao getInstance() {
        if(instance == null) {
            instance = new PersonalizedRecommendationsDao();
        }
        return instance;
    }

    public List<CarListings> getPersonalizedRecommendaitonByUserName(String userName) throws SQLException {
        List<CarListings> carListings = new ArrayList<>();
        String selectCarListings = "SELECT CarListings.*\n" +
                "FROM CarListings CROSS JOIN (\n" +
                "\tSELECT\n" +
                "\t\tMAX(Price) AS MaxPrice,\n" +
                "        MIN(Price) AS MinPrice,\n" +
                "        MAX(Price) - MIN(Price) AS PriceDiff,\n" +
                "\t\tMAX(Year) AS MaxYear,\n" +
                "\t\tMIN(Year) AS MinYear,\n" +
                "\t\tMAX(Mileage) AS MaxMileage,\n" +
                "\t\tMIN(Mileage) AS MinMileage,\n" +
                "        MAX(Mileage) - MIN(Mileage) AS MileageDiff\n" +
                "\tFROM (\n" +
                "\t\tSELECT VIN\n" +
                "\t\tFROM UserSavedCars\n" +
                "\t\tWHERE Username = ?\n" +
                "\t) AS SavedCars JOIN CarListings\n" +
                "\t\tON SavedCars.VIN = CarListings.VIN\n" +
                ") AS SavedConditions\n" +
                "WHERE Price > MinPrice - 0.2 * PriceDiff \n" +
                "\tAND Price < MaxPrice + 0.2 * PriceDiff\n" +
                "\tAND Year > MinYear - 2\n" +
                "    AND Year < MaxYear + 2\n" +
                "    AND Mileage > MinMileage - 0.2 * MileageDiff \n" +
                "\tAND Mileage < MaxMileage + 0.2 * MileageDiff\n" +
                "ORDER BY Price ASC, Year DESC, Mileage ASC\n" +
                "LIMIT 50;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCarListings);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            while(results.next()) {
                String vin = results.getString("VIN");
                CarListingsDao carListingsDao = CarListingsDao.getInstance();
                CarListings car = carListingsDao.getCarListingByVin(vin);
                carListings.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return carListings;
    }
}
