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
import com.javarevolutions.ws.rest.vo.Pregunta;

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
        ArrayList<Ecotip> ecotips = new ArrayList<Ecotip>();
        ecotips = db.getAllEcotips(); 
        //JSONObject individual = new JSONObject(); 
    	
        for (int i = 0; i < ecotips.size(); ++i) {
        	JSONObject individual = new JSONObject();
        	Ecotip e = ecotips.get(i);
        	individual.put("id", e.getId());
        	individual.put("titol", e.getTitol()); 
        	individual.put("descripcio", e.getText()); 
        	individual.put("idQuiz", e.getQuiz()); 
        	json.put(i, individual);
        }
        return json;
    }
	
	@GET
    @Path("/getQuiz/{idQuiz}")
	@Produces({MediaType.APPLICATION_JSON})
    public JSONObject getQuiz(@PathParam("idQuiz") int idQuiz) {
        Database db = Database.getInstance();
        JSONObject json = new JSONObject();
        ArrayList<Pregunta> preguntes= new ArrayList<Pregunta>();
		preguntes = db.getPreguntes(idQuiz);
		//afegir el quiz al JSONObject
        return json;
    }
}
