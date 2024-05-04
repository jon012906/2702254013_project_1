package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import index.DatabaseConnection;
import index.DatabaseSingleton;
import models.User;

public class UserController {
    private static DatabaseConnection dbConnection = DatabaseSingleton.getInstance();

    public void insertDefaultUsers() {
        try {
            boolean result1 = insertUser(new User("admin@gmail.com", "admin123"));
            boolean result2 = insertUser(new User("john@gmail.com", "john123"));
            boolean result3 = insertUser(new User("jane@gmail.com", "jane123"));

            if (!result1 || !result2 || !result3) {
                System.out.println("Fail to insert some data");
            } else {
                System.out.println("Successfully insert default users!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean insertUser(User user) {
    
        if (userExists(user)) {
            return false;
        }
        String query = "INSERT INTO users (email, password) VALUES (?, ?)";
        try {
            PreparedStatement stmt = dbConnection.connection.prepareStatement(query);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
       
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean userExists(User user) {
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";
        try {
            PreparedStatement stmt = dbConnection.connection.prepareStatement(query);
            stmt.setString(1, user.getEmail());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean loginUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try {
            PreparedStatement stmt = dbConnection.connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
