package com.javarevolutions.ws.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.javarevolutions.ws.rest.kmeans.Centroid;
import com.javarevolutions.ws.rest.kmeans.EuclideanDistance;
import com.javarevolutions.ws.rest.kmeans.Kmeans;
import com.javarevolutions.ws.rest.vo.VOUsuario;

@Path("/users")
public class ServiceLoginJR {
	
	ArrayList<VOUsuario> lista; 
	JSONObject myObject; 
	
	@POST
	@Path("/createUser")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject createUser(JSONObject json) throws JSONException {
		
		JSONObject output = new JSONObject(); 
		
		Database db = Database.getInstance();
		String nom = json.getString("nom");
		String email = json.getString("email");
		String contrasenya = json.getString("contrasenya");
		String data = "" + json.getString("any") + "-" + json.getString("mes") + "-" + json.getString("dia");
		//String data = "1999-07-31";
		VOUsuario user = new VOUsuario(nom,email);
		output.put("resposta",db.createUser(user, data, contrasenya, json.getString("descripcio")));
        return output; 
	}
	
	@POST
	@Path("/loginUser")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject loginUser(JSONObject json) throws JSONException {
		
		JSONObject output = new JSONObject(); 
		
		Database db = Database.getInstance();
		String email = json.getString("email");
		String contrasenya = json.getString("contrasenya");
		
		output.put("login",db.loginUser(email, contrasenya));
        return output; 
	}
	
	@GET
	@Path("/getUsuari/{email}")
	public JSONObject getUsuari(@PathParam("email") String email) throws JSONException {
		JSONObject output = new JSONObject(); 
		Database db = Database.getInstance();
		VOUsuario user = new VOUsuario();
		user = db.getUsuari(email); 
		output.put("email", user.getEmail()); 
		output.put("nom", user.getNom()); 
		output.put("edat", user.getEdat()); 
		output.put("descripcio", user.getDescripcio()); 
		
		return output; 
    }
	
	@GET
	@Path("/getUsuarisSemblants/{email}")
	public JSONObject getUsuarisSemblants(@PathParam("email") String email) throws JSONException {
		Database db = Database.getInstance(); 
		ArrayList<VOUsuario> usuarios = db.getUsuarisSemblants(); 
		
		List<Record> records; 
		Map<Centroid, List<Record>> clusters = Kmeans.fit(records,4, new EuclideanDistance(), 1000);
		
		
		return output; 
    }
	
	@POST
	@Path("/deleteUser/{email}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject deleteUser(@PathParam("email") String email) throws JSONException {
		
		JSONObject output = new JSONObject();
		
		Database db = Database.getInstance();
		output.put("resposta",db.deleteUsuari(email));
		
		return output;
	}
	
	@POST
	@Path("/setGustosPersonals/{email}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject setGustosPersonals(@PathParam("email") String email,JSONObject gustos) throws JSONException {
		JSONObject output = new JSONObject(); 
		Database db = Database.getInstance(); 
		int result = db.setPreferencies(email,gustos.getInt("animals"),gustos.getInt("musica"),gustos.getInt("menjar"),
				gustos.getInt("esport"),gustos.getInt("videojocs"),
				gustos.getInt("literatura"),gustos.getInt("oci_nocturn"),gustos.getInt("horari_laboral")); 
		output.put("resultat", result); 
		return output;
	}
	
	@POST
	@Path("/postFoto")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response userPublicaImatge(JSONObject json) throws JSONException {
		

		Database db = Database.getInstance();
		String save = json.getString("URL").substring(61, json.getString("URL").length());
		db.insertFoto(json.getString("email"), save, "perfil");
		
		return Response.ok("done").build();
	}
	
	@GET
	@Path("/getFoto/{email}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject userGetImatge(@PathParam("email") String email) throws JSONException {
		

		Database db = Database.getInstance();
		JSONObject ret = new JSONObject();
		ArrayList<String> fotos = new ArrayList<String>();
		fotos = db.getFotos(email, "perfil");
		
		ret.put("result", "https://res.cloudinary.com/homies-image-control/image/upload/" + fotos.get(0));
		
		return ret;
	} 
}
