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

@Path("/external")
public class ServiceExtern {
	
	JSONObject myObject;
	
	@GET
	@Path("/getFoto/{email}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject extGetImatge(@PathParam("email") String email) throws JSONException {
		
	
		Database db = Database.getInstance();
		JSONObject ret = new JSONObject();
		ArrayList<String> fotos = new ArrayList<String>();
		fotos = db.getFotos(email, "extern");
		
		ret.put("foto", "https://res.cloudinary.com/homies-image-control/image/upload/" + fotos.get(0));
		
		return ret;
	}
	
	@GET
	@Path("/getFotos")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONArray ofertaGetImatge() throws JSONException {
		
		JSONArray ret = new JSONArray();
		Database db = Database.getInstance();
		ArrayList<String> fotos = new ArrayList<String>();
		fotos = db.getAllFotos();
		for (int i = 0; i < fotos.size(); i+=2) {
			JSONObject aux = new JSONObject();
			aux.put("id", fotos.get(i));
			aux.put("url", "https://res.cloudinary.com/homies-image-control/image/upload/" + fotos.get(i+1));
			ret.put(i, aux);
    	}
		return ret;
	}
	
	@POST
	@Path("/postImatge")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject uploadImatgeServei(JSONObject json) throws JSONException {
		
		JSONObject ret = new JSONObject();
		String save = json.getString("URL").substring(61, json.getString("URL").length());
		Database db = Database.getInstance();
		ret.put("resposta", db.uploadFoto(json.getString("id"), save));
		
		return ret;
	}
	
	@POST
	@Path("/postFoto")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject extPublicaImatge(JSONObject json) throws JSONException {
		
		JSONObject ret = new JSONObject();
		String save = json.getString("URL").substring(61, json.getString("URL").length());
		Database db = Database.getInstance();
		
		if(db.insertFoto(json.getString("email"), save, "extern", null) == 1) ret.put("resposta", 1);
		else if(db.updateFoto(json.getString("email"), save, "extern", null) == 1) ret.put("resposta", 1);
		else ret.put("resposta", -1);
		
		return ret;
	}
}
