package com.javarevolutions.ws.rest.database; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.javarevolutions.ws.rest.vo.Ecotip;
import com.javarevolutions.ws.rest.vo.Oferta;
import com.javarevolutions.ws.rest.vo.VOUsuario;

public class Database {
	
	private static Database dbObject;
	private String url= "jdbc:mysql://localhost:3306/HomiesApp";
	private String username = "homies.admin";
	private String password  = "homies.SQL";

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
	
	//Ecotip
	public Ecotip getEcotip(int id) {
			
			String query = "SELECT * FROM Ecotips WHERE id = " + id;
			
			ResultSet rs = query(query);
			Ecotip ecotip = new Ecotip();
			ecotip.setId(id);
			ecotip.setTitol("Fail");
			try {
				rs.next();
				ecotip.setTitol(rs.getString("titol"));
				ecotip.setText(rs.getString("contingut"));
				return ecotip;
			} catch (SQLException e) {
				e.printStackTrace();
				return ecotip;
			}
	}
	
	public void insertEcotip(Ecotip e) {
		String query = "INSERT INTO Ecotips VALUES (id = " + e.getId() + ", titol = '" + e.getTitol() + "', data_publ = null, contingut = '"+ e.getText() +"';";
		update(query);
	}
	
	//Usuari
	public void getUsuari(VOUsuario user) {
		String query = "SELECT * FROM Usuari WHERE email = " + user.getEmail();
		
		ResultSet rs = query(query);
		try {
			user.setNom(rs.getString("nom"));
			user.setEdad(rs.getInt("edat"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Oferta
	public void getOferta(Oferta oferta) {
		String query = "SELECT * FROM Ofertes WHERE id = " + oferta.getId();
		
		ResultSet rs = query(query);
		//Tractar resultset
	}
}