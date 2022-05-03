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
import com.javarevolutions.ws.rest.vo.Quiz;
import com.javarevolutions.ws.rest.vo.VOUsuario;

@Path("/ecotips")
public class ServiceEcotip {
	
	JSONObject myObject;
	
	@GET
    @Path("/getEcotip/{id}")
	@Produces({MediaType.APPLICATION_JSON})
    public Response getEcotip(@PathParam("id") String id) {
        Database db = Database.getInstance();
        Ecotip e = db.getEcotip(Integer.parseInt(id));
        return Response.ok(e.getTitol()).build();
    }
	
	@GET
    @Path("/getQuiz/{idQuiz}")
	@Produces({MediaType.APPLICATION_JSON})
    public Response getQuiz(@PathParam("idQuiz") int idQuiz) {
        Database db = Database.getInstance();
        Quiz q = db.getQuiz(idQuiz);
        return Response.ok("done").build();
    }
}
