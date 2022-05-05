package com.javarevolutions.ws.rest.service; 

import javax.ws.rs.GET;
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

@Path("/ecotips")
public class ServiceEcotip {
	
	JSONObject myObject;
	
	@GET
    @Path("/getEcotip/{id}")
	@Produces({MediaType.APPLICATION_JSON})
    public JSONObject getEcotip(@PathParam("id") String id) throws JSONException {
        Database db = Database.getInstance();
        Ecotip e = db.getEcotip(Integer.parseInt(id));
        JSONObject json = new JSONObject(); 
        json.put("titol",e.getTitol()); 
        json.put("text", e.getText()); 
        json.put("idQuiz",e.getQuiz()); 
        return json;
    }
	
	@GET
    @Path("/getQuiz/{idQuiz}")
	@Produces({MediaType.APPLICATION_JSON})
    public Response getQuiz(@PathParam("idQuiz") int idQuiz) {
        Database db = Database.getInstance();
        Quiz q = new Quiz();
		q.setId(idQuiz);
		q.setPreguntes(db.getPreguntes(idQuiz));
        return Response.ok("done").build();
    }
}
