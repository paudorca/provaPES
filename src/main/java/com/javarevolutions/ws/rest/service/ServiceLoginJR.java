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
	public Response createUser(JSONObject json) throws JSONException {
		
		Database db = Database.getInstance();
		String nom = json.getString("nom");
		String email = json.getString("email");
		String contrasenya = json.getString("contrasenya");
		VOUsuario user = new VOUsuario(nom,email,contrasenya);
		user.setEdad(json.getInt("edat"));
        db.createUser(user);
        
        return Response.ok("done").build();
	}
	
	
	
	@POST
	@Path("/preferencies")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public int setGustosPersonals() throws JSONException {
		return 1;
		/* Queries q = new Queries(); 
		 * String insert = q.insertaGustosUsuari()
		 */
	}
	
	@GET
	@Path("/getGustosUsuari/{idUsuari}")
	public Response getGustosPersonals(@PathParam("idUsuari") String idUsuari) {
		return null;
        /*Queries q = new Queries(); 
        JSONObject j =  q.getGustosPersonals(id); 
        return j;  */
    }
	
	@GET
	@Path("/inicializa")
	public String ini() {
		return "inicializado con exito";
	}
	
	@GET
	@Path("/validaUsuario")
	public String validaUsuario() {
		myObject = new JSONObject(); 
		return "Prueba";
	}
	
	@GET
	@Path("/todos")
	public String damelos() throws JSONException {
		return "hola";
	}
}
