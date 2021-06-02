package com.simplilearn.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public Connection connection;
	
	//Create connection
	public DBConnection(String url, String username, String password) throws ClassNotFoundException, SQLException{
		getClass().forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection(url, username, password);
	}
	//get connection
	public Connection getConnection() {
		return this.connection;
	}
	
	//close connection
	public void closeConnection() throws SQLException {
		if(connection != null) {
			this.connection.close();
		}
	}
}