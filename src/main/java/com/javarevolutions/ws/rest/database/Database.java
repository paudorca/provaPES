package com.javarevolutions.ws.rest.database; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.javarevolutions.ws.rest.vo.Ecotip;
import com.javarevolutions.ws.rest.vo.Oferta;
import com.javarevolutions.ws.rest.vo.Pregunta;
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
	
	public int update(String query) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		
			try {
				Connection ConnTry = DriverManager.getConnection(url, username, password);
			    Statement stmt = ConnTry.createStatement();
			    int x = stmt.executeUpdate(query);
			    //ConnTry.commit();
			    return x;
			}
			catch (SQLException ex){
			    // handle any errors
				System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			    return -1;
			}
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -2;
		}
	}
	
	//Ecotip
	public Ecotip getEcotip(int id) {
			
			String query = "SELECT * FROM Ecotips WHERE id = " + id;
			
			ResultSet rs = query(query);
			Ecotip ecotip = new Ecotip();
			ecotip.setId(id);
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

	public ArrayList<Ecotip> getAllEcotips() {

		String query = "SELECT * FROM Ecotips;";
		ResultSet rs = query(query);
		
		ArrayList<Ecotip> ecotips = new ArrayList<Ecotip>();
		Ecotip aux = new Ecotip();
		try {
			while(rs.next()) {
				
				aux.setId(rs.getInt("id"));
				aux.setTitol(rs.getString("titol"));
				aux.setText(rs.getString("contingut"));
				aux.setQuiz(rs.getInt("id_quiz"));
				ecotips.add(aux);
			}
			return ecotips;
		} catch (SQLException e) {
			e.printStackTrace();
			return ecotips;
		}
	}
	
	public void insertEcotip(Ecotip e) {
		String query = "INSERT INTO Ecotips VALUES (id = " + e.getId() + ", titol = '" + e.getTitol() + "', data_publ = null, contingut = '"+ e.getText() +"';";
		update(query);
	}
	
	//Usuari
	public VOUsuario getUsuari(String email) {
		String query = "SELECT * FROM Usuari WHERE email = '" + email + "';";
		VOUsuario user = new VOUsuario();
		user.setEmail(email);
		ResultSet rs = query(query);
		try {
			rs.next();
			user.setNom(rs.getString("nom"));
			user.setEdad(rs.getInt("edat"));
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Oferta
	public Oferta getOferta(String email) {
		String query = "SELECT * FROM Ofertes WHERE id = '" + email + "';";
		
		Oferta oferta = new Oferta();
		ResultSet rs = query(query);
		//Tractar resultset
		try {
			rs.next();

			return oferta;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return oferta;
		}
	}
	
	public void createOferta(Oferta oferta) {
		
		
	}

	public int createUser(VOUsuario user, String contrasenya) {
		String query = "INSERT INTO Usuari (nom, email, edat) VALUES ('" + user.getNom() + "', '" + user.getEmail() + "', "+ user.getEdad() +");";
		update(query);
		query = "INSERT INTO Passwords (email, pass) VALUES ('" + user.getEmail() + "', '" + contrasenya + "');";
		return update(query);
	}
	
	public int deleteUsuari(String email) {
		
		
		String query = "DELETE FROM Passwords WHERE email = '" + email + "';";
		update(query);
		query = "DELETE FROM Usuari WHERE email = '" + email + "';";
		return update(query);
	}

	
	private ResultSet getResposta(int id_pregunta){
		
		String query = "SELECT * FROM Respostes WHERE id_pregunta = " + id_pregunta + ";";
		return query(query);
	}
	
	public ArrayList<Pregunta> getPreguntes(int idQuiz) {
		
		String query = "SELECT * FROM Preguntes WHERE id_quiz = " + idQuiz + ";";
		ResultSet rs = query(query);

		ArrayList<Pregunta> preguntes = new ArrayList<Pregunta>();
		Pregunta aux = new Pregunta();
		
		try {
			while(rs.next()) {
				
				aux.setId(rs.getInt("id_pregunta"));
				aux.setDescripcio(rs.getString("text_preg"));

				ArrayList<String> aux_res = new ArrayList<String>();
				ResultSet aux_rs = getResposta(rs.getInt("id_pregunta"));
				int i = 0;
				
				while (aux_rs.next()) {
					
					if (aux_rs.getBoolean("correcte")) aux.setRespostaCorrecta(i);
					aux_res.add(aux_rs.getString("text_res"));
				}
				
				aux.setRespostes(aux_res);
				preguntes.add(aux);
			}
			return preguntes;
		} catch (SQLException e) {
			e.printStackTrace();
			return preguntes;
		}
	}

	public Boolean loginUser(String email, String contrasenya) {
		String query = "SELECT * FROM Passwords WHERE email = '" + email + "';";
		ResultSet rs = query(query);
		try {
			rs.next();
			return contrasenya == rs.getString("pass");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}