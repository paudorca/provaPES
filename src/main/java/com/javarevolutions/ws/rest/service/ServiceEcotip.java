package com.javarevolutions.ws.rest.service; 

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
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
    @Path("/getAllEcotips/")
	@Produces({MediaType.APPLICATION_JSON})
    public JSONArray getAllEcotips() throws JSONException {
        Database db = Database.getInstance();
        JSONArray json = new JSONArray();  
        ArrayList<Ecotip> ecotips = db.getAllEcotips(); 
    	
        for (int i = 0; i < ecotips.size(); ++i) {
        	JSONObject individual = new JSONObject();
        	Ecotip e = ecotips.get(i);
        	individual.put("id", e.getId());
        	individual.put("titol", e.getTitol()); 
        	individual.put("descripcio", e.getText()); 
        	individual.put("idQuiz", e.getQuiz()); 
        	json.put(i, individual);
        }
        /*
        JSONObject individual = new JSONObject();
    	individual.put("id", ecotips.get(0).getId());
    	individual.put("titol", ecotips.get(0).getTitol()); 
    	individual.put("descripcio",ecotips.get(0).getText()); 
    	individual.put("idQuiz",ecotips.get(0).getQuiz()); 
    	json.put(0, individual);
    	
    	JSONObject individual1 = new JSONObject();
    	individual1.put("id", ecotips.get(1).getId());
    	individual1.put("titol", ecotips.get(1).getTitol()); 
    	individual1.put("descripcio",ecotips.get(1).getText()); 
    	individual1.put("idQuiz",ecotips.get(1).getQuiz()); 
    	json.put(1, individual1);
    	
    	JSONObject individual2 = new JSONObject();
    	individual2.put("id", ecotips.get(2).getId());
    	individual2.put("titol", ecotips.get(2).getTitol()); 
    	individual2.put("descripcio",ecotips.get(2).getText()); 
    	individual2.put("idQuiz",ecotips.get(2).getQuiz()); 
    	json.put(2, individual2);
    	*/
        return json;
    }
	
	@GET
    @Path("/getQuiz/{idQuiz}")
	@Produces({MediaType.APPLICATION_JSON})
    public JSONObject getQuiz(@PathParam("idQuiz") int idQuiz) {
        Database db = Database.getInstance();
        JSONObject json = new JSONObject();
        Quiz q = new Quiz();
		q.setId(idQuiz);
		q.setPreguntes(db.getPreguntes(idQuiz));
		//afegir el quiz al JSONObject
        return json;
    }
}
