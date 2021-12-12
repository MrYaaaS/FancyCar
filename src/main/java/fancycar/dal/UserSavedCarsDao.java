package fancycar.dal;

import fancycar.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserSavedCarsDao {
    protected ConnectionManager connectionManager;

    private static UserSavedCarsDao instance = null;
    protected UserSavedCarsDao() {
        connectionManager = new ConnectionManager();
    }
    public static UserSavedCarsDao getInstance() {
        if(instance == null) {
            instance = new UserSavedCarsDao();
        }
        return instance;
    }

    public UserSavedCars create(UserSavedCars userSavedCar) throws SQLException {
        String insertUserSavedCar = "INSERT INTO UserSavedCars(UserName,"
            + "VIN) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertUserSavedCar);

            insertStmt.setString(1, userSavedCar.getUser().getUserName());
            insertStmt.setString(2, userSavedCar.getCar().getVin());

            insertStmt.executeUpdate();
            return userSavedCar;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public List<CarListings> getCarsByUserName(String userName)
    throws SQLException {
        List<CarListings> carListings = new ArrayList<>();
        String selectCarListings = "SELECT UserName, VIN FROM UserSavedCars WHERE UserName=?;";
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

    public List<Users> getUsersByVin(String vin)
    throws SQLException {
        List<Users> users = new ArrayList<>();
        String selectUsers = "SELECT UserName, VIN FROM UserSavedCars WHERE "
            + "VIN=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectUsers);

            selectStmt.setString(1, vin);
            results = selectStmt.executeQuery();
            while(results.next()) {
                String userName = results.getString("UserName");
                UsersDao usersDao = UsersDao.getInstance();
                Users user = usersDao.getUserByUserName(userName);
                users.add(user);
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
        return users;
    }

    public UserSavedCars delete(UserSavedCars userSavedCar) throws SQLException {
        String deleteUserSavedCar = "DELETE FROM UserSavedCars WHERE UserName=? AND VIN=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteUserSavedCar);
            deleteStmt.setString(1, userSavedCar.getUser().getUserName());
            deleteStmt.setString(2, userSavedCar.getCar().getVin());
            int affectedRows = deleteStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No records available to delete for "
                    + "UserName=" + userSavedCar.getUser().getUserName() + " and VIN=" + userSavedCar.getCar().getVin());
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
}

