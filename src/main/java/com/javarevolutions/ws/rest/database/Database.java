package com.javarevolutions.ws.rest.database; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.javarevolutions.ws.rest.vo.Ecotip;
import com.javarevolutions.ws.rest.vo.Oferta;
import com.javarevolutions.ws.rest.vo.Pregunta;
import com.javarevolutions.ws.rest.vo.VOUsuario;
import com.javarevolutions.ws.rest.vo.Xat;


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
		
		String query = "INSERT INTO Usuari (email, nom, data_naix, descr, puntuacio) VALUES ('" + user.getEmail() + "', '" + user.getNom() + "', '" + data + "', '" + descripcio + "', 0)";
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

	public int updatePunt(String email, int p) {
		
		
		ResultSet rs = query("SELECT puntuacio FROM Usuari WHERE email = '" + email + "';");
		
		String query = "";
		try {
			if(rs.next()) {
				query = "UPDATE Usuari SET puntuacio = " + p + rs.getInt("puntuacio") + " WHERE email = '" + email + "';";
			}
			return update(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	//Usuari
	public VOUsuario getUsuari(String email) {
		String query = "SELECT * FROM Usuari WHERE email = '" + email + "';";
		VOUsuario user = new VOUsuario();
		user.setEmail(email);
		ResultSet rs = query(query);
		try {
			if(rs.next()) {
				user.setNom(rs.getString("nom"));
				user.setEdat(rs.getDate("data_naix").toString());
				user.setDescripcio(rs.getString("descr"));
				user.setFoto(rs.getString("foto"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public int updateDescr(String email, String descripcio) {
		
		String query = "UPDATE Usuari SET descr = '" + descripcio + "' WHERE email = '" + email + "';";
		return update(query);
	}
	
	public int updatePassword(String email, String contrasenya) {
		
		String query = "UPDATE Usuari SET pass = '" + contrasenya + "' WHERE email = '" + email + "';";
		return update(query);
	}
	
	public int deleteUsuari(String email) {
		
		deleteOferta(email);
		deletePreferences(email);
		deleteMatch(email, "delete");
		deleteXat(email, "delete");
		
		
		String query = "DELETE FROM Passwords WHERE email = '" + email + "';";
		update(query);
		query = "DELETE FROM Usuari WHERE email = '" + email + "';";
		return update(query);
	}

	public int createOferta(Oferta oferta) {
		
		String query = "INSERT INTO Ofertes (email, adr, num_cas, pob, nivell_energetic, num_ocupants, habitacions, superficie, descr, preu) VALUES ('" + oferta.getEmail() + "', '" + oferta.getAdr() + "', '" + oferta.getNumCas() + "', '" + oferta.getPoblacio() + "', '" + oferta.getNivellEnergetic() + "', "  + oferta.getNumeroOcupants() + ", " + oferta.getHabitacions() + ", " + oferta.getSuperficie() +", '" + oferta.getDescripcio() + "', " + oferta.getPreu() +");";
		return update(query);
	}

	public int updateOferta(Oferta oferta) {
		
		String query = "UPDATE Ofertes SET adr = '" + oferta.getAdr() + "', num_cas = '" + oferta.getNumCas() + "', pob = '" + oferta.getPoblacio() + "', nivell_energetic = '" + oferta.getNivellEnergetic() + "', num_ocupants = " + oferta.getNumeroOcupants() + ", habitacions = " + oferta.getHabitacions() + ", superficie = " + oferta.getSuperficie() + ", descr = '" + oferta.getDescripcio() + "', preu = " + oferta.getPreu() + " WHERE email = '" + oferta.getEmail() + "';";
		return update(query);
	}

	//Oferta
	public Oferta getOferta(String email) {
		String query = "SELECT * FROM Ofertes WHERE email = '" + email + "';";
		
		Oferta oferta = new Oferta();
		ResultSet rs = query(query);
		oferta.setEmail(email);
		
		try {
			if(rs.next()) {
			oferta.setEmail(email);
			oferta.setAdr(rs.getString("adr"));
			oferta.setNivellEnergetic(rs.getString("nivell_energetic"));
			oferta.setNumeroOcupants(rs.getInt("num_ocupants"));
			oferta.setPoblacio(rs.getString("pob"));
			oferta.setPreu(rs.getInt("preu"));
	        oferta.setHabitacions(rs.getInt("habitacions"));
	        oferta.setDescripcio(rs.getString("descr"));
			oferta.setNumCas(rs.getString("num_cas"));
			oferta.setSuperficie(rs.getInt("superficie"));
			}
			else oferta.setEmail("Fail");
			
			return oferta;
		} catch (SQLException e) {
			e.printStackTrace();
			return oferta;
		}
	}
	
	public int deleteOferta(String email) {
		deleteFotos(email,"oferta");
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
	
	public int uploadFoto(String id, String URL) {
		
		String query = "INSERT INTO ServeiFoto (nom, foto) VALUES ('" + id + "', '" + URL + "');";
		return update(query);
	}
	
	public int insertFoto(String email, String URL, String tipus, String id) {
		
		String query;
		if (tipus.equals("perfil")) query = "UPDATE Usuari SET foto = '" + URL + "' WHERE email = '" + email + "';";
		else if (tipus.equals("oferta")) query = "INSERT INTO ImatgesOferta (email, id, url) VALUES ('" + email + "', '" + id + "', '" + URL + "');";
		else if (tipus.equals("extern")) query = "INSERT INTO ImatgesServei (id, url) VALUES ('" + email + "','" + URL + "');";
		else query = "Fail";
		return update(query);
	}
	
	public int updateFoto(String email, String URL, String tipus, String id) {
		
		String query;
		if (tipus.equals("perfil")) query = "UPDATE Usuari SET foto = '" + URL + "' WHERE email = '" + email + "';";
		else if (tipus.equals("oferta")) query = "UPDATE ImatgesOferta SET url = '" + URL + "' WHERE email = '" + email + "' AND id = '" + id + "';";
		else if (tipus.equals("extern")) query = "UPDATE ImatgesServei SET url = '" + URL + "' WHERE id = '" + email + "';";
		else query = "Fail";
		return update(query);
	}
	
	public ArrayList<String> getFotos(String email, String tipus) {
		
		String query;
		if (tipus.equals("perfil")) query = "SELECT foto FROM Usuari WHERE email = '" + email + "';";
		else if (tipus.equals("oferta")) query = "SELECT * FROM ImatgesOferta WHERE email = '" + email + "';";
		else if (tipus.equals("extern")) query = "SELECT * FROM ImatgesServei WHERE id = '" + email + "';";
		else query = "Fail";
		ResultSet rs = query(query);
		
		ArrayList<String> fotos = new ArrayList<String>();
		
		try {
			if (tipus.equals("perfil") && rs.next()) fotos.add(rs.getString("foto"));
			else if (tipus.equals("oferta")) {
				while(rs.next()) {
					fotos.add(rs.getString("id"));
					fotos.add(rs.getString("url"));
				}
			}
			else if (tipus.equals("extern") && rs.next()) fotos.add(rs.getString("url"));
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}

		if (fotos.isEmpty()) fotos.add("Fail");
		return fotos;
	}
	
	public int deleteFotos(String email, String tipus) {
		
		String query;
		if (tipus.equals("perfil")) query = "DELETE foto FROM Usuari WHERE email = '" + email + "';";
		else if (tipus.equals("oferta")) query = "DELETE FROM ImatgesOferta WHERE email = '" + email + "';";
		else if (tipus.equals("extern")) query = "DELETE FROM ImatgesServei WHERE id = '" + email + "';";
		else query = "Fail";
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
					+ "('" + email + "'," +int1 + "," + int2 + "," + int3 + "," + int4 + ","
					  + int5 + "," + int6 + "," + int7 + "," + int8 + ");";
		int result = update(query);
		if (result == -1) {
			String query2 = "UPDATE Preferencies SET Animals = " + int1 + ",Musica = " + int2 + ",Menjar = " + int3 + ",Esport = " + 
					int4 + ",Videojocs = " + int5 + ",Literatura = " + int6 + ",Oci_nocturn = " + int7 + ",Horari_laboral = " + int8 + 
					" where Usuari = '" + email + "';";
			return update(query2); 
		}
		return result; 
	}

	public ArrayList<VOUsuario> getUsuarisSemblants() {
		
		return null;
	}
	
	public int insertMatch(String email1, String email2, Boolean started) {
		
		if (started) {
			String query = "UPDATE Matches SET mat = 1 WHERE email1 = '" + email1 + "' and email2 = '" + email2 + "';";
			if (update(query) == 1) return 1;
			else {
				
				query = "UPDATE Matches SET mat = 1 WHERE email1 = '" + email2 + "' and email2 = '" + email1 + "';";
				return update(query);
			}
		}
		
		else {
			
			String query = "INSERT INTO Matches (email1, email2, mat) VALUES ('" + email1 + "','" + email2 + "',0);";
			return update(query);
		}
	}
	
	public Boolean isMatched(String email1, String email2) {
		
		String query = "SELECT mat FROM Matches WHERE email1 = '" + email1 + "' and email2 = '" + email2 + "';";
		ResultSet rs = query(query);
		
		try {
			if(rs.next()) {
				if (rs.getInt("mat") == 1) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		query = "SELECT mat FROM Matches WHERE email1 = '" + email2 + "' and email2 = '" + email1 + "';";
		ResultSet rs2 = query(query);
		
		try {
			if(rs2.next()){
				if (rs2.getInt("mat") == 1) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean isStarted(String email1, String email2) {

		String query = "SELECT mat FROM Matches WHERE email1 = '" + email1 + "' and email2 = '" + email2 + "';";
		ResultSet rs = query(query);
		
		try {
			if(rs.next()) {
				if (rs.getInt("mat") == 0) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		query = "SELECT mat FROM Matches WHERE email1 = '" + email2 + "' and email2 = '" + email1 + "';";
		ResultSet rs2 = query(query);
		
		try {
			if(rs2.next()) {
				if (rs2.getInt("mat") == 0) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int deleteMatch(String email1, String email2) {
		
		if(email2.equals("delete")) {
			String del1 = "DELETE FROM Matches WHERE email1 = '" + email1 + "';";
			String del2 = "DELETE FROM Matches WHERE email2 = '" + email1 + "';";
			return update(del1) + update(del2);
		}
		
		String query = "DELETE FROM Matches WHERE email1 = '" + email1 + "' and email2 = '" + email2 + "';";
				
		if (update(query) == 1) return 1;
		else {
			
			query = "DELETE FROM Matches WHERE email1 = '" + email2 + "' and email2 = '" + email1 + "';";
			return update(query);
		}
	}
	
	public HashMap<String,HashMap<String,Double >> getAllPreferencies() {
		String query = "SELECT * FROM Preferencies"; 
		HashMap<String,HashMap<String,Double >> resultat = new HashMap<String, HashMap<String, Double>>(); 
		ResultSet rs = query(query);
		try {
			while(rs.next()) {
				HashMap<String,Double> intern = new HashMap<String, Double>(); 
				String Usuari = rs.getString("Usuari");
				int Animals = rs.getInt("Animals");
				int Musica = rs.getInt("Musica"); 
				int Menjar = rs.getInt("Menjar");
				int Esport = rs.getInt("Esport");
				int Videojocs = rs.getInt("Videojocs"); 
				int Literatura = rs.getInt("Literatura"); 
				int Oci_nocturn = rs.getInt("Oci_nocturn");
				int Horari_laboral = rs.getInt("Horari_laboral");
				intern.put("Animals", (double) Animals);
				intern.put("Musica", (double) Musica);
				intern.put("Menjar", (double) Menjar);
				intern.put("Esport", (double) Esport);
				intern.put("Videojocs", (double) Videojocs);
				intern.put("Literatura", (double) Literatura);
				intern.put("Oci_nocturn", (double) Oci_nocturn);
				intern.put("Horari_laboral", (double) Horari_laboral);
				
				resultat.put(Usuari, intern); 
			}
			return resultat; 
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int deletePreferences(String email) {
		
		String query = "DELETE FROM Preferences WHERE email = '" + email + "';";
		return update(query);
	}

	public int createXat(String chatId, String email1, String email2) {
		String query = "INSERT INTO Chat VALUES('"+chatId+"','"+email1+"','"+email2+"');";
		return update(query); 
	}

	public ArrayList<Xat> getXat1(String email) {
		ArrayList<Xat> xats = new ArrayList<Xat>(); 
		String query = "SELECT * FROM Chat WHERE email1 = '"+ email + "';";
		ResultSet rs = query(query);
		try {
			while (rs.next()) {
				String query1 = "SELECT nom FROM Usuari WHERE email = '"+ email + "';"; 
				String query2 = "SELECT nom FROM Usuari WHERE email = '"+ rs.getString("email2") + "';";
				ResultSet rs1 = query(query1);
				ResultSet rs2 = query(query2);
				if (rs1.next() && rs2.next()) {
				Xat xat = new Xat(rs.getString("chatId"),email,rs.getString("email2"),rs1.getString("nom"),rs2.getString("nom"));
				xats.add(xat); 
				} 
			}
			return xats; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
	public ArrayList<Xat> getXat2(String email) {
		ArrayList<Xat> xats = new ArrayList<Xat>(); 
		String query = "SELECT * FROM Chat WHERE email2 = '"+ email + "';";
		ResultSet rs = query(query);
		try {
			while (rs.next()) {
				String query1 = "SELECT nom FROM Usuari WHERE email = '"+ email + "';"; 
				String query2 = "SELECT nom FROM Usuari WHERE email = '"+ rs.getString("email1") + "';";
				ResultSet rs1 = query(query1);
				ResultSet rs2 = query(query2);
				if (rs1.next() && rs2.next()) {
				Xat xat = new Xat(rs.getString("chatId"),email,rs.getString("email2"),rs1.getString("nom"),rs2.getString("nom"));
				xats.add(xat); 
				} 
			}
			return xats; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int deleteXat(String email1, String email2) {
		
		if(email2.equals("delete")) {
			String del1 = "DELETE FROM Chat WHERE email1 = '" + email1 + "';";
			String del2 = "DELETE FROM Chat WHERE email2 = '" + email1 + "';";
			return update(del1) + update(del2);
		}
		
		String query = "DELETE FROM Chat WHERE email1 = '" + email1 + "' and email2 = '" + email2 + "';";
				
		if (update(query) == 1) return 1;
		else {
			
			query = "DELETE FROM Chat WHERE email1 = '" + email2 + "' and email2 = '" + email1 + "';";
			return update(query);
		}
	}

	public int putCluster(String nombre, int i) {
		String query = "UPDATE Usuari set idCluster = " + i + " WHERE email = '"+ nombre +"';";
		return update(query);
	}

	public ArrayList<String> getUsuarisMateixCluster(String email) {
		ArrayList<String> noms = new ArrayList<String>(); 
		String query = "SELECT idCluster from Usuari WHERE email = '" + email + "';";
		ResultSet rs = query(query);
		int id = -1; 
		try {
			if(rs.next()) {
				id = rs.getInt("idCluster"); 
			}
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		query = "SELECT email from Usuari WHERE idCluster = " + id + ";";
		ResultSet rs2 = query(query);
		try {
			while (rs2.next()) {
				 noms.add(rs2.getString("email")); 
			}
			return noms; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}

	public boolean teOfertes(String email) {
		String query = "SELECT * from Ofertes WHERE email = '" + email + "';"; 
		ResultSet rs = query(query); 
		try {
			if (rs.next()) {
				return true; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}