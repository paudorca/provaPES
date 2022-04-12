package com.javarevolutions.ws.rest.service;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.javarevolutions.ws.rest.vo.VOUsuario;

@Path("/demo")
public class ServiceLoginJR {
	
	ArrayList<VOUsuario> lista; 
	JSONObject myObject; 
	
	@POST
	@Path("/setUsuario")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject setUsuario(VOUsuario vo) throws JSONException {
		myObject = new JSONObject(); 
		myObject.put("name", vo.getUsuario());
        myObject.put("password", vo.getPassword());
		return myObject;
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
