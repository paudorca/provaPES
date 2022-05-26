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

		if(dbObject == null) dbObject = new Database();
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
	
	public int insertEcotip(Ecotip e) {
		String query = "INSERT INTO Ecotips VALUES (id = " + e.getId() + ", titol = '" + e.getTitol() + "', data_publ = null, contingut = '"+ e.getText() +"';";
		return update(query);
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
	
	public int createUser(VOUsuario user, String data, String contrasenya, String descripcio) {
		
		String query = "INSERT INTO Usuari (email, nom, data_naix, descr) VALUES ('" + user.getEmail() + "', '" + user.getNom() + "', '" + data + "', '" + descripcio + "')";
		int x = update(query);
		query = "INSERT INTO Passwords (email, pass) VALUES ('" + user.getEmail() + "', '" + contrasenya + "');";
		update(query);
		return x;
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
			//int naix = ;
			user.setEdat(2022 - Integer.parseInt(rs.getDate("data_naix").toString().substring(0,4)));
			user.setDescripcio("");
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

	public int createOferta(Oferta oferta) {
		
		String query = "INSERT INTO Oferta (email, adr, num_cas, cod_pos, pob, nivell_energetic, num_ocupants, habitacions, superficie, descr, preu)"
				+ "VALUES ('" + oferta.getEmail() + "', '" + oferta.getAdr() + "', '" + oferta.getNumCas() + "', " + oferta.getCodiPostal() + ", '" + oferta.getPoblacio() + "', '" + oferta.getNivellEnergetic() + "', "  + oferta.getNumeroOcupants() + ", " + oferta.getHabitacions() + ", " + oferta.getSuperficie() +", '" + oferta.getDescripcio() + "', " + oferta.getPreu() +");";
		return update(query);
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
	
	public int deleteOferta(String email) {
		
		String query = "DELETE FROM Ofertes WHERE email = '" + email + "';";
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
	
	public int serveiInsertFoto(int id, String URL) {
		
		String query = "INSERT INTO ServeiFoto (id, foto) VALUES (" + id + ",'" + URL + "');";
		return update(query);
	}
	
	public int insertFoto(String email, String URL, String tipus) {
		
		String query = "INSERT INTO Imatges (email, url, tipus) VALUES ('" + email + "','" + URL + "','" + tipus + "');";
		return update(query);
	}
	
	public ArrayList<String> getFotos(String email, String tipus) {
		
		String query = "SELECT * FROM Imatges WHERE email = '" + email + "' AND tipus ='" + tipus + "';";
		ResultSet rs = query(query);
		
		ArrayList<String> fotos = new ArrayList<String>();
		
		try {
			while(rs.next()) {
				fotos.add(rs.getString("url"));
			}
			if (fotos.isEmpty()) fotos.add("Fail");
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fotos;
	}
	
	public int deleteFotos(String email, String tipus) {
		
		String query = "DELETE FROM Imatges WHERE email = '" + email + "' AND tipus ='" + tipus + "');";
		return update(query);
	}

	public ArrayList<String> getAllFotos() {
		String query = "SELECT * FROM ServeiFoto;";
		ResultSet rs = query(query);
		
		ArrayList<String> fotos = new ArrayList<String>();
		
		try {
			while(rs.next()) {
				fotos.add(rs.getString("nom"));
				fotos.add(rs.getString("foto"));
			}	
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fotos;
	}

	public int setPreferencies(String email, int int1, int int2, int int3, 
			int int4, int int5, int int6, int int7,
			int int8) {
		  String query = "INSERT INTO Preferencies VALUES "
					+ "('" + email + "'," +int1 + "," + int2 + "," + int3 + "," + int3 + ","
					  + int3 + "," + int3 + "," + int3 + "," + int3 + ");";
		return update(query); 
	}

	public ArrayList<VOUsuario> getUsuarisSemblants() {
		// TODO Auto-generated method stub
		return null;
	}

	public int addLike(String like, String liked) {
		String query = "SELECT * FROM LIKES where usuari1= '" + liked + "' and usuari2 = '" + like + "';"; 
		ResultSet rs = query(query); 
		if (rs == null) {
			String query1 = "UPDATE Likes SET reciproc = 1 where usuari1 = '" + liked + "' and usuari2 = '" + like + "'"; 
			return update(query1); 
		}
		query = "INSERT INTO Likes VALUES ('" + like + "', '" + liked + "',0)"; 
		return update(query); 
	}
}