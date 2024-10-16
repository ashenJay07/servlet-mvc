package com.epay.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
	public static DatabaseConfig instance;
	
	// Store Database Configurations
	private String url;
	private String username;
	private String password;
	
	private DatabaseConfig() {
		url = "jdbc:mysql://localhost:3306/oop_mvc_project";
		username = "root";
		password = "root";
	}
	
	public static DatabaseConfig getDBInstance() {
		if (instance == null)
			instance = new DatabaseConfig();
		
		return instance;
	}
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url, username, password);
	}
	
}
