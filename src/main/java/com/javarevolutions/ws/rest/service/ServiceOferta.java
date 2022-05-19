package com.javarevolutions.ws.rest.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.javarevolutions.ws.rest.database.Database;
import com.javarevolutions.ws.rest.vo.Oferta;

@Path("/oferta")
public class ServiceOferta {
	
	@POST
	@Path("/createOferta")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject createOferta(JSONObject json) throws JSONException {
		
		JSONObject ret = new JSONObject();
		
		Database db = Database.getInstance();
		Oferta o = new Oferta();
		o.setAdr(json.getString("adr"));
		o.setDescripcio(json.getString("descripcio"));
		o.setEmail(json.getString("email"));
		o.setNivellEnergetic(json.getString("nivellEnergetic"));
		o.setHabitacions(json.getString("habitacions"));
		o.setSuperficie(json.getInt("superficie"));
		o.setNumCas(json.getString("numCas"));
		o.setNumeroOcupants(json.getInt("numeroOcupants"));
		o.setPoblacio(json.getString("poblacio"));
		o.setPreu(json.getInt("preu")); 

		ret.put("result", db.createOferta(o));
        return ret;
	}

	@GET
	@Path("/getOferta/{email}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject getOferta(@PathParam("email") String email) throws JSONException {
		
		JSONObject json = new JSONObject();
		Database db = Database.getInstance();
		Oferta o = db.getOferta(email);
		
		json.put("email",o.getEmail()); 
		json.put("adr",o.getAdr());
		json.put("nivellEnergetic",o.getNivellEnergetic());
		json.put("numeroOcupants",o.getNumeroOcupants());
		json.put("poblacio",o.getPoblacio());
		json.put("preu",o.getPreu());
        json.put("habitacions", o.getHabitacions());
        json.put("descripcio", o.getDescripcio());
		json.put("numCas", o.getNumCas());
		json.put("superficie", o.getSuperficie());
		
		return json;
	}
	
	@GET
	@Path("/deleteOferta/{email}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject deleteOferta(@PathParam("email") String email) throws JSONException {
		
		Database db = Database.getInstance();
		JSONObject ret = new JSONObject();
		ret.put("result", db.deleteOferta(email));
		return ret;
	}
	
	@POST
	@Path("/postFotos")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject ofertaPublicaImatges(JSONArray json) throws JSONException {
		
		Database db = Database.getInstance();
		String email = json.getString(0);
		
		JSONObject ret = new JSONObject();
		ret.put("result",1);
		
    	for (int i = 1; json.isNull(i); ++i) {
    		if (db.insertFoto(email, json.getString(i), "oferta") < 0) ret.put("result", -1); 
    	}
		
		return ret;
	}
	
	@POST
	@Path("/getFotos/{email}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONArray ofertaGetImatge(@PathParam("email") String email) throws JSONException {
		
		JSONArray ret = new JSONArray();
		Database db = Database.getInstance();
		ArrayList<String> fotos = new ArrayList<String>();
		fotos = db.getFotos("email", "perfil");
		
		for (int i = 0; i < fotos.size(); ++i) {
			ret.put(i, fotos.get(i));
    	}
		return ret;
	} 
}
