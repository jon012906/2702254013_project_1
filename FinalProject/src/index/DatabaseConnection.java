package index;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import controller.MenuController;

public class DatabaseConnection {

    public Connection connection;
    public Statement statement;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ptpuddingDB", "root", "");
            statement = connection.createStatement();
            System.out.println("Connection successfully established");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void migrateTables() {
        createUsersTable();
        createMenuTable();
    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "email VARCHAR(50) NOT NULL,"
                + "password VARCHAR(50) NOT NULL)";
        try {
            exec(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createMenuTable() {
        String query = "CREATE TABLE IF NOT EXISTS menu("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "kode VARCHAR(10) NOT NULL,"
                + "nama VARCHAR(50) NOT NULL,"
                + "harga DOUBLE NOT NULL,"
                + "stok INT NOT NULL)";
        try {
            exec(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exec(String query) {
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
