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
import com.javarevolutions.ws.rest.vo.Ecotip;
import com.javarevolutions.ws.rest.vo.VOUsuario;

@Path("/ecotips")
public class ServiceEcotip {
	
	JSONObject myObject;
	
	@POST
	@Path("/createEcotip")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject setUsuario(Ecotip e) throws JSONException {
		myObject = new JSONObject(); 
		myObject.put("id", e.getId());
        myObject.put("descripcio", e.getDescripcio());
		return myObject;
		/* Queries q = new Queries(); 
		 * String insert = q.insertUser(vo)
		 */
	}
	
	@GET
    @Path("/getEcotip/{id}")
	@Produces({MediaType.APPLICATION_JSON})
    public Response getEcotip(@PathParam("id") String id) {
        return Response.ok(id).build();
        /*Queries q = new Queries(); 
        JSONObject j =  q.getEcotip(id); 
        return j;  */
    }
}
