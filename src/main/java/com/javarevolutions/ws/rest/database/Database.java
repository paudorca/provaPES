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
			e.printStackTrace();
			return -2;
		}
	}
	
	public void insertEcotip(Ecotip e) {
		String query = "INSERT INTO Ecotips VALUES (id = " + e.getId() + ", titol = '" + e.getTitol() + "', data_publ = null, contingut = '"+ e.getText() +"';";
		update(query);
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
		
		
		try {
			
			while(rs.next()) {
				
				Ecotip aux = new Ecotip();
				aux.setId(rs.getInt("id"));
				aux.setTitol(rs.getString("titol"));
				aux.setText(rs.getString("contingut"));
				ecotips.add(aux);
			}
			
			return ecotips;
		} catch (SQLException e) {
			e.printStackTrace();
			return ecotips;
		}
	}
	
	public int createUser(VOUsuario user, String contrasenya) {
		String query = "INSERT INTO Usuari (nom, email, data_naix, descr) VALUES ('" + user.getNom() + "', '" + user.getEmail() + "', "+ user.getEdad() +");";
		update(query);
		query = "INSERT INTO Passwords (email, pass) VALUES ('" + user.getEmail() + "', '" + contrasenya + "');";
		return update(query);
	}

	public Boolean loginUser(String email, String contrasenya) {
		String query = "SELECT * FROM Passwords WHERE email = '" + email + "';";
		ResultSet rs = query(query);
		try {
			rs.next();
			if (contrasenya.equals(rs.getString("pass"))) return true;
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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
	
	public int deleteUsuari(String email) {
		
		
		String query = "DELETE FROM Passwords WHERE email = '" + email + "';";
		update(query);
		query = "DELETE FROM Usuari WHERE email = '" + email + "';";
		return update(query);
	}

	public void createOferta(Oferta oferta) {
		
		String query = "INSERT INTO Oferta (email, adr, cod_pos, pob, nivell_energetic, num_ocupants, descr, preu) VALUES ();";
		update(query);
	}

	//Oferta
	public Oferta getOferta(String email) {
		String query = "SELECT * FROM Ofertes WHERE id = '" + email + "';";
		
		Oferta oferta = new Oferta();
		ResultSet rs = query(query);
		oferta.setEmail(email);
		
		try {
			rs.next();
			
			oferta.setAdr(rs.getString("adr"));
			oferta.setCodiPostal(rs.getInt("cod_pos"));
			oferta.setPoblacio(rs.getString("pob"));
			oferta.setNivellEnergetic(rs.getString("nivell_energetic"));
			oferta.setNumeroOcupants(rs.getInt("num_ocupants"));
			oferta.setPreu(rs.getInt("preu"));

			return oferta;
		} catch (SQLException e) {
			e.printStackTrace();
			return oferta;
		}
	}
	
	public void deleteOferta(String email) {
		
		String query = "DELETE FROM Ofertes WHERE email = '" + email + "';";
		update(query);
	}
	
	private ResultSet getResposta(int id_pregunta){
		
		String query = "SELECT * FROM Respostes WHERE id_pregunta = " + id_pregunta + ";";
		return query(query);
	}
	
	public ArrayList<Pregunta> getPreguntes(int idQuiz) {
		
		String query = "SELECT * FROM Preguntes WHERE id_quiz = " + idQuiz + ";";
		ResultSet rs = query(query);

		ArrayList<Pregunta> preguntes = new ArrayList<Pregunta>();
		
		
		try {
			while(rs.next()) {
				
				Pregunta aux = new Pregunta();
				aux.setId(rs.getInt("id_pregunta"));
				aux.setDescripcio(rs.getString("text_preg"));

				ArrayList<String> 
				aux_res = new ArrayList<String>();
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
	
	public void serveiInsertFoto(int id, String URL) {
		
		String query = "INSERT INTO ServeiFoto (id, foto) VALUES (" + id + ",'" + URL + "');";
		update(query);
	}
	
	public void insertFoto(String email, String URL, String tipus) {
		
		String query = "INSERT INTO Imatges (email, foto, tipus) VALUES ('" + email + "','" + URL + "','" + tipus + "');";
		update(query);
	}
	
	public ArrayList<String> getFotos(String email, String tipus) {
		
		String query = "SELECT * FROM Imatges WHERE email = '" + email + "' AND tipus ='" + tipus + "');";
		ResultSet rs = query(query);
		
		ArrayList<String> fotos = new ArrayList<String>();
		
		try {
			while(rs.next()) {
				fotos.add(rs.getString("Foto"));
			}	
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fotos;
	}
	
	public void deleteFotos(String email, String tipus) {
		
		String query = "DELETE FROM Imatges WHERE email = '" + email + "' AND tipus ='" + tipus + "');";
		update(query);
	}
}