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

@Path("/ecotips")
public class ServiceEcotip {
	
	JSONObject myObject;
	
	@POST
	@Path("/createEcotip")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject setUsuario(Ecotip e) throws JSONException {
		/*myObject = new JSONObject(); 
		myObject.put("id", e.getId());
        myObject.put("descripcio", e.getText());
        
        Database db = Database.getInstance();
        db.insertEcotip(e);
		return myObject;*/
		
	}
	
	@GET
    @Path("/getEcotip/{id}")
	@Produces({MediaType.APPLICATION_JSON})
    public Response getEcotip(@PathParam("id") String id) {
        Database db = Database.getInstance();
        Ecotip e = db.getEcotip(Integer.parseInt(id));
        return Response.ok(e.getTitol()).build();
    }
}
