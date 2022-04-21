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

import com.javarevolutions.ws.rest.vo.VOUsuario;

@Path("/users")
public class ServiceLoginJR {
	
	ArrayList<VOUsuario> lista; 
	JSONObject myObject; 
	
	@POST
	@Path("/createUser")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject setUsuario(VOUsuario vo) throws JSONException {
		myObject = new JSONObject(); 
		myObject.put("usuario", vo.getUsuario());
        myObject.put("password", vo.getPassword());
		return myObject;
		/* Queries q = new Queries(); 
		 * String insert = q.insertUser(vo)
		 */
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
