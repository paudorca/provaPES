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
}
