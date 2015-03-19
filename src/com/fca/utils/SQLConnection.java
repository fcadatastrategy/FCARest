package com.fca.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.driver.OracleDriver;

public class SQLConnection {

	private String url;
	private String password;
	private String username;
	private static SQLConnection instance;

	private SQLConnection()
	{
		try {
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@fcadev.ck6ecvz0lmqp.eu-west-1.rds.amazonaws.com:1521:orcl";
			username = "awsdb";
			password = "welcome1";

		} catch (Exception e) {
			System.out.println("Failed to Register JDBC Driver!");
			e.printStackTrace();
		}
		System.out.println("Oracle JDBC Driver Registered!");
	}

	
	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new SQLConnection();
		}
		try {
			return DriverManager.getConnection(instance.url,instance.username,instance.password);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static void close(Connection connection)
	{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
