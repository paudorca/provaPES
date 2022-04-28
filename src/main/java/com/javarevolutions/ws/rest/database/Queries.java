package com.javarevolutions.ws.rest.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javarevolutions.ws.rest.vo.Ecotip;
import com.javarevolutions.ws.rest.vo.Oferta;
import com.javarevolutions.ws.rest.vo.VOUsuario;

public class Queries {

	public void getEcotip(Ecotip ecotip) {
		
		Database db = Database.getInstance();
		String query = "SELECT * FROM Ecotips WHERE id = " + ecotip.getId();
		
		ResultSet rs = db.query(query);
		
		try {
			ecotip.setDescripcio(rs.getString("contingut"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertEcotip() {
		//insertar ecotip a la BD 
	}
	
	public void getOferta(Oferta oferta) {
		Database db = Database.getInstance();
		String query = "SELECT * FROM Ofertes WHERE id = " + oferta.getId();
		
		ResultSet rs = db.query(query);
	} 
	
	public void getUsuari(VOUsuario user) {
		Database db = Database.getInstance();
		String query = "SELECT * FROM Usuari WHERE email = " + user.getEmail();
		
		ResultSet rs = db.query(query);
		try {
			user.setNom(rs.getString("nom"));
			user.setEdad(rs.getInt("edat"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getProva() {
		
		Database db = Database.getInstance();
		String query = "SELECT * FROM Usuari WHERE id = 1 ";
		
		ResultSet rs = db.query(query);
		try {
			return rs.getString("email");
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}
}