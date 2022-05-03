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

import com.javarevolutions.ws.rest.database.Queries;
import com.javarevolutions.ws.rest.vo.Like;
import com.javarevolutions.ws.rest.vo.Match;
import com.javarevolutions.ws.rest.vo.VOUsuario;

@Path("/oferta")
public class ServiceOferta {
	
	@POST
	@Path("/createOferta")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response setUsuario(VOUsuario vo) throws JSONException {
		myObject = new JSONObject(); 
		myObject.put("usuario", vo.getUsuario());
        myObject.put("password", vo.getPassword());
        Queries q = new Queries(); 
		String result = q.createUser(vo);
        return Response.ok(result).build();
	}
	
	@POST
	@Path("/addMatch")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response addMatch(Match m) throws JSONException {
		myObject = new JSONObject(); 
		myObject.put("usuario1", m.getUsuari1());
        myObject.put("usuario2", m.getUsuari2());
		Queries q = new Queries(); 
		String result = q.addMatch(m);
        return Response.ok(result).build();
	}
	
	@POST
	@Path("/addLike")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response addLike(Like l) throws JSONException {
		myObject = new JSONObject(); 
		myObject.put("usuario1", l.getUsuari1());
        myObject.put("usuario2", l.getUsuari2());
		Queries q = new Queries(); 
		String result = q.addLike(l);
        return Response.ok(result).build();
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
    @Path("/getUsuari/{email}")
	@Produces({MediaType.APPLICATION_JSON})
    public Response getEcotip(@PathParam("email") String email) {
        Queries q = new Queries();
        VOUsuario u = new VOUsuario(email); 
        q.getUsuari(u); 
        return Response.ok(u).build();
    }
	
	@GET
	@Path("/todos")
	public String damelos() throws JSONException {
		return "hola";
	}
}
