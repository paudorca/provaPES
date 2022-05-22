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
		fotos = db.getFotos("email", "extern");
		
		ret.put("result", "https://res.cloudinary.com/homies-image-control/image/upload/" + fotos.get(0));
		
		return ret;
	}
	
	@GET
	@Path("/getFotos")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONArray ofertaGetImatge(@PathParam("email") String email) throws JSONException {
		
		JSONArray ret = new JSONArray();
		Database db = Database.getInstance();
		ArrayList<String> fotos = new ArrayList<String>();
		fotos = db.getAllFotos();
		JSONObject aux = new JSONObject();
		for (int i = 0; i < fotos.size(); i+=2) {
			aux.put(fotos.get(i), "https://res.cloudinary.com/homies-image-control/image/upload/" + fotos.get(i+1));
			ret.put(aux);
    	}
		return ret;
	}
			
	@POST
	@Path("/postFoto")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response extPublicaImatge(JSONObject json) throws JSONException {
		
		String save = json.getString("URL").substring(61, json.getString("URL").length() - 1);
		Database db = Database.getInstance();
		db.insertFoto(json.getString("email"), save, "extern");
		
		return Response.ok("done").build();
	}
}
