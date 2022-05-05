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
import com.javarevolutions.ws.rest.vo.Ecotip;
import com.javarevolutions.ws.rest.vo.VOUsuario;

@Path("/users")
public class ServiceLoginJR {
	
	ArrayList<VOUsuario> lista; 
	JSONObject myObject; 
	
	@POST
	@Path("/createUser")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject createUser(JSONObject json) throws JSONException {
		
		JSONObject output = new JSONObject(); 
		
		Database db = Database.getInstance();
		String nom = json.getString("nom");
		String email = json.getString("email");
		String contrasenya = json.getString("contrasenya");
		VOUsuario user = new VOUsuario(nom,email);
		user.setEdad(json.getInt("edat"));
		output.put("resposta",db.createUser(user, contrasenya));
        
        return output; 
	}
	
	@GET
	@Path("/getUsuari/{email}")
	public JSONObject getUsuari(@PathParam("email") String email) throws JSONException {
		JSONObject output = new JSONObject(); 
		Database db = Database.getInstance();
		VOUsuario user = new VOUsuario();
		user = db.getUsuari(email); 
		output.put("email", user.getEmail()); 
		output.put("nom", user.getNom()); 
		output.put("edat", user.getEdad()); 
		
		return output; 
    }
	
	@POST
	@Path("/deleteUser/{email}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject deleteUser(@PathParam("email") String email) throws JSONException {
		
		JSONObject output = new JSONObject();
		
		Database db = Database.getInstance();
		output.put("resposta",db.deleteUsuari(email));
		
		return output;
	}
	
	@POST
	@Path("/setGustosPersonals")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response setGustosPersonals(JSONObject json) throws JSONException {
		return Response.ok("proba").build(); 
		//he de implementar l'algorisme de recomanacio, un cop estigui,podrem fer aquesta crida
	}
	
	@GET
	@Path("/getGustosPersonals/{idUsuari}")
	public JSONObject getGustosPersonals(@PathParam("idUsuari") String idUsuari) {
		JSONObject json = new JSONObject();
		//crida a una nova clase, que encara he de implementar 
		return json; 
    }
	
	@GET
	@Path("/getPreferenciesUsuari/{idUsuari}")
	public Response getPreferenciesUsuari(@PathParam("idUsuari") String idUsuari) {
		return Response.ok("proba").build(); 
		//he de implementar l'algorisme de recomanacio, un cop estigui,podrem fer aquesta crida
    }
	
	//de prova per comprobar la conexio
	@GET
	@Path("/todos")
	public Response damelos() throws JSONException {
		return Response.ok("hola").build();
	}
}
