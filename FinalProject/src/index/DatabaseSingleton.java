package index;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import controller.UserController;

public class DatabaseConnection {

	public Connection connection;
	public Statement statement;
	

	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.
					getConnection("jdbc:mysql://localhost:3306/libraryDB", "root", "");
			statement = connection.createStatement();
			System.out.println("Create connection successful");
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void migrateTables() {
		createUsersTable();
		createBooksTable();
		
		
	}
	public void createUsersTable() {
		String query = "CREATE TABLE IF NOT EXISTS users("
				+ "id INT AUTO_INCREMENT PRIMARY KEY,"
				+ "email VARCHAR(50) NOT NULL,"
				+ "password VARCHAR(50) NOT NULL)";
		try {
			exec(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createBooksTable() {
		String query = "CREATE TABLE IF NOT EXISTS books("
				+ "id INT AUTO_INCREMENT PRIMARY KEY,"
				+ "title VARCHAR(50) NOT NULL,"
				+ "author VARCHAR(50) NOT NULL,"
				+ "genre VARCHAR(50) NOT NULL)";
		try {
			exec(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void exec(String query) {
		try {
			statement.execute(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}