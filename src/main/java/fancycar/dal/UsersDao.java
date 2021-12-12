package fancycar.dal;

import fancycar.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDao {
    protected ConnectionManager connectionManager;

    private static UsersDao instance = null;
    protected UsersDao() {
        connectionManager = new ConnectionManager();
    }
    public static UsersDao getInstance() {
        if(instance == null) {
            instance = new UsersDao();
        }
        return instance;
    }

    public Users create(Users user) throws SQLException {
        String insertUser = "INSERT INTO Users(UserName,Password,FirstName,LastName,Email,Phone) VALUES(?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertUser);
            insertStmt.setString(1, user.getUserName());
            insertStmt.setString(2, user.getPassword());
            insertStmt.setString(3, user.getFirstName());
            insertStmt.setString(4, user.getLastName());
            insertStmt.setString(5, user.getEmail());
            insertStmt.setString(6, user.getPhone());
            insertStmt.executeUpdate();

            return user;
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

    public Users updatePassword(Users user) throws SQLException {
        String updateUser = "UPDATE Users SET Password=? WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateUser);
            updateStmt.setString(1, user.getPassword());
            updateStmt.setString(2, user.getUserName());
            updateStmt.executeUpdate();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public Users getUserByUserName(String userName) throws SQLException {
        String selectUser = "SELECT UserName,Password,FirstName,LastName,Email,Phone FROM Users WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectUser);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            if(results.next()) {
                String resultUserName = results.getString("UserName");
                String userPassword = results.getString("Password");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String email = results.getString("Email");
                String phone = results.getString("Phone");
                Users user = new Users(resultUserName, userPassword, firstName,
                    lastName, email, phone);
                return user;
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
        return null;
    }
}
