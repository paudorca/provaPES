package com.javarevolutions.ws.rest.database; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private static Database dbObject;
	private Connection conn;
	
	public Database() {
		connect();
	}

	public static Database getInstance() {

		if(dbObject == null) {
			dbObject = new Database();
		}
		
		return dbObject;
	}
	
	private void connect() {
		
		String url = "jdbc:mysql://localhost:3306/HomiesApp";
		String username = "homies.admin";
		String password = "homies.SQL";
		
		try (Connection c = DriverManager.getConnection(url, username, password)) {
		    System.out.println("Database connected!");
		    conn = c;
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	public Connection getConnection() {
		return conn; 
	}
	
	public ResultSet query(String query) {
		try {
			
		    Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    return rs;
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return null;
	}
	
	public void update(String query) {
		try {
			
		    Statement stmt = conn.createStatement();
		    stmt.executeUpdate(query);
		    
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}