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
        JSONObject individual = new JSONObject(); 
    	
        for (int i = 0; i < ecotips.size(); ++i) {
        	individual = new JSONObject();
        	Ecotip e = ecotips.get(i);
        	individual.put("id", e.getId());
        	individual.put("titol", e.getTitol()); 
        	individual.put("descripcio", e.getText());
        	json.put(i, individual);
        }
        return json;
    }
	
	@GET
    @Path("/getQuiz/{idQuiz}")
	@Produces({MediaType.APPLICATION_JSON})
    public JSONObject getQuiz(@PathParam("idQuiz") int idQuiz) throws JSONException {
        Database db = Database.getInstance();
        JSONObject json = new JSONObject();
        ArrayList<Pregunta> preguntes= new ArrayList<Pregunta>();
		preguntes = db.getPreguntes(idQuiz);
		for (int i = 0; i < preguntes.size(); ++i) {
        	JSONObject individual = new JSONObject();
        	Pregunta p = preguntes.get(i);
        	individual.put("id",p.getId());
        	individual.put("descripcio",p.getDescripcio());
        	ArrayList<String> respostes = p.getRespostes(); 
        	JSONArray jsonRespostes = new JSONArray(); 
        	for (int j = 0; j < respostes.size();++j) {
        		jsonRespostes.put(respostes.get(j)); 
        	}
        	individual.put("respostes",jsonRespostes); 
        	individual.put("respostaCorrecta",p.getRespostaCorrecta()); 
        }
        return json;
    }
}
