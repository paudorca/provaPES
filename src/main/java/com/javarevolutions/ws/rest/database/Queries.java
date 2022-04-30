package com.javarevolutions.ws.rest.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javarevolutions.ws.rest.vo.Ecotip;
import com.javarevolutions.ws.rest.vo.Oferta;
import com.javarevolutions.ws.rest.vo.VOUsuario;

public class Queries {

	private static Queries qObject;
	
	public static Queries getInstance() {

		if(qObject == null) {
			qObject = new Queries();
		}
		return qObject;
	}	
	
	public void getEcotip(Ecotip ecotip) {
		
		Database db = Database.getInstance();
		String query = "SELECT * FROM Ecotips WHERE id = " + ecotip.getId();
		
		ResultSet rs = db.query(query);
		
		try {
			rs.next();
			ecotip.setTitol(rs.getString("titol"));
			ecotip.setText(rs.getString("contingut"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertEcotip(Ecotip e) {
		Database db = Database.getInstance();
		String query = "INSERT INTO Ecotips VALUES (id = " + e.getId() + ", titol = '" + e.getTitol() + "', data_publ = null, contingut = '"+ e.getText() +"';";
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
}