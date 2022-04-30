package com.javarevolutions.ws.rest.database; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private static Database dbObject;
	String url;
	String username;
	String password;
	
	public Database() {
		url = "jdbc:mysql://localhost:3306/HomiesApp";
		username = "homies.admin";
		password = "homies.SQL";
	}

	public static Database getInstance() {

		if(dbObject == null) {
			dbObject = new Database();
		}
		return dbObject;
	}	
	
	public ResultSet query(String query) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		
			try {
				Connection ConnTry = DriverManager.getConnection(url, username, password);
			    Statement stmt = ConnTry.createStatement();
			    ResultSet rs = stmt.executeQuery(query);
			    			    
			    return rs;
			}
			catch (SQLException ex){
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	
	public void update(String query) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		
			try {
				Connection ConnTry = DriverManager.getConnection(url, username, password);
			    Statement stmt = ConnTry.createStatement();
			    stmt.executeUpdate(query);
			}
			catch (SQLException ex){
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}