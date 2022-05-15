package com.javarevolutions.ws.rest.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	public Response createOferta(JSONObject json) throws JSONException {
		
		Database db = Database.getInstance();
		Oferta o = new Oferta();
		o.setAdr(json.getString("adr")); 
		o.setCodiPostal(json.getInt("codiPostal"));
		o.setEmail(json.getString("email"));
		o.setNivellEnergetic(json.getString("nivellEnergetic")); 
		o.setNumeroOcupants(json.getInt("numeroOcupants")); 
		o.setPoblacio(json.getString("poblacio")); 
		o.setPreu(json.getInt("preu")); 

		db.createOferta(o);
        return Response.ok("done").build();
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
		json.put("codiPostal",o.getCodiPostal());
		json.put("nivellEnergetic",o.getNivellEnergetic());
		json.put("numeroOcupants",o.getNumeroOcupants());
		json.put("poblacio",o.getPoblacio());
		json.put("preu",o.getPreu());
        return json;
	}
	
	@GET
	@Path("/deleteOferta/{email}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteOferta(@PathParam("email") String email) throws JSONException {
		
		Database db = Database.getInstance();
		db.deleteOferta(email);
		return Response.ok("done").build();
	}
	
	@POST
	@Path("/postFotos")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response ofertaPublicaImatges(JSONObject json) throws JSONException {
		
		Database db = Database.getInstance();
		String email = json.getString("email");
		 
    	for (int i = 0; json.isNull("URL_" + i); ++i) {
    		db.insertFoto(email, json.getString("URL_" + i), "oferta"); 
    	}
		
		
		return Response.ok("done").build();
	}
	
	@GET
	@Path("/getFotos")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response ofertaGetImatges(JSONObject json) throws JSONException {

		Database db = Database.getInstance();
		String email = json.getString("email");
		 
    	for (int i = 0; json.isNull("URL_" + i); ++i) {
    		db.insertFoto(email, json.getString("URL_" + i), "oferta"); 
    	}
		
		
		return Response.ok("done").build();
	}
	
	@POST
	@Path("/getFotos/{email}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject ofertaGetImatge(@PathParam("email") String email) throws JSONException {
		
		JSONObject json = new JSONObject();
		Database db = Database.getInstance();
		ArrayList<String> fotos = new ArrayList<String>();
		fotos = db.getFotos("email", "perfil");
		
		for (int i = 0; i < fotos.size(); ++i) {
			json.put("URL_" + i, fotos.get(i));
    	}
		return json;
	} 
}
