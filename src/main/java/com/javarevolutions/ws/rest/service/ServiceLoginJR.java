package com.javarevolutions.ws.rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.javarevolutions.ws.rest.database.Database;
import com.javarevolutions.ws.rest.kmeans.*;
import com.javarevolutions.ws.rest.kmeans.Record;
import com.javarevolutions.ws.rest.vo.VOUsuario;
import com.javarevolutions.ws.rest.vo.Xat;

@Path("/users")
public class ServiceLoginJR {
	
	private int iterador; 
	
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
		output.put("foto", user.getFoto()); 
		
		return output; 
    }
	
	@GET
	@Path("/getXat/{email}")
	public JSONArray getXat(@PathParam("email") String email) throws JSONException {
		Database db = Database.getInstance(); 
		ArrayList<Xat> xats1 = db.getXat1(email);
		ArrayList<Xat> xats2 = db.getXat2(email);
		JSONArray json = new JSONArray();  
		for (int i = 0; i < xats1.size();++i) {
			JSONObject xat = new JSONObject();
			xat.put("xatId",xats1.get(i).getXatId());
			xat.put("email1",xats1.get(i).getEmail1());
			xat.put("email2",xats1.get(i).getEmail2());
			xat.put("username1",xats1.get(i).getUsername1());
			xat.put("username2",xats1.get(i).getUsername2());
			json.put(xat); 
		}
		for (int i = 0; i < xats2.size();++i) {
			JSONObject xat = new JSONObject();
			xat.put("xatId",xats2.get(i).getXatId());
			xat.put("email1",xats2.get(i).getEmail1());
			xat.put("email2",xats2.get(i).getEmail2());
			xat.put("username1",xats2.get(i).getUsername1());
			xat.put("username2",xats2.get(i).getUsername2()); 
			json.put(xat); 
		}
		return json;
    }
	
	@SuppressWarnings("null")
	@GET
	@Path("/getUsuarisSemblants/{email}")
	public JSONObject getUsuarisSemblants(@PathParam("email") String email) throws JSONException {
		JSONObject output = new JSONObject();
		JSONArray json = new JSONArray();
		Database db = Database.getInstance(); 
		 ArrayList<String> usuarisCluster = db.getUsuarisMateixCluster(email);
		 for (int i = 0; i < usuarisCluster.size();++i) {
			 if (email != usuarisCluster.get(i)) json.put(usu)
		 }
		 return output; 
    }
	
	public ArrayList<String> convertListToArray(List<Record> llista) {
		ArrayList<String> output = new ArrayList<String>(); 
		for (int i = 0; i < llista.size();++i) {
			output.add(llista.get(i).getDescription()); 
		}
		return output; 
	}
	
	@POST
	@Path("/changeDescr")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject changeDescr(JSONObject json) throws JSONException {
		
		JSONObject output = new JSONObject();
		
		Database db = Database.getInstance();
		
		output.put("resposta",db.updateDescr(json.getString("email"), json.getString("descr")));
		
		return output;
	}
	
	@POST
	@Path("/changePass")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject changePass(JSONObject json) throws JSONException {
		
		JSONObject output = new JSONObject();
		
		Database db = Database.getInstance();
		
		if(db.loginUser(json.getString("email"), json.getString("contrasenya_actual"))) output.put("resposta",db.updatePassword(json.getString("email"), json.getString("contrasenya_nova")));
		else output.put("resposta", "Incorrect Password");
		
		return output;
	}
	
	@POST
	@Path("/deleteUser")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject deleteUser(JSONObject json) throws JSONException {
		
		JSONObject output = new JSONObject();
		
		Database db = Database.getInstance();
		if(db.loginUser(json.getString("email"), json.getString("contrasenya"))) output.put("resposta",db.deleteUsuari(json.getString("email")));
		else output.put("resposta", "Incorrect Password");
		
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
		List<Record> records = new ArrayList<Record>();
		
		HashMap<String,HashMap<String,Double >> resultat = db.getAllPreferencies(); 
		Iterator<String> it = null; 
		
		it = resultat.keySet().iterator();
		 
		while(it.hasNext()){
		    String clave = it.next();
		    HashMap<String, Double> valor = resultat.get(clave);
		    Record record = new Record(clave); 
		    record.setFeatures(valor); 
		    records.add(record); 
		}
		iterador = 1; 
		Map<Centroid, List<Record>> clusters = Kmeans.fit(records,2, new EuclideanDistance(), 1000);
		clusters.forEach((key, value) -> {
			ArrayList<String> nombres = convertListToArray(value);
			for (int j = 0; j < nombres.size();++j) {
				String nombre = nombres.get(j);
				db.putCluster(nombre,iterador); 
			}
			++iterador; 
		}); 
		return output;
	}
	
	@POST
	@Path("/postXat")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject creaXat(JSONObject json) throws JSONException {
		
		JSONObject ret = new JSONObject();
		Database db = Database.getInstance();
		int result = db.createXat(json.getString("chatId"),
				json.getString("email1"),json.getString("email2"));
		ret.put("result", result); 
		return ret; 
	}
	
	@POST
	@Path("/postFoto")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject userPublicaImatge(JSONObject json) throws JSONException {
		
		JSONObject ret = new JSONObject();
		Database db = Database.getInstance();
		String save = json.getString("URL").substring(61, json.getString("URL").length());
		ret.put("resposta", db.insertFoto(json.getString("email"), save, "perfil", null));
		
		return ret;
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
		
		ret.put("foto", "https://res.cloudinary.com/homies-image-control/image/upload/" + fotos.get(0));
		
		return ret;
	} 
	
	@POST
	@Path("/Match")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject Match(JSONObject json) throws JSONException {
		
		JSONObject ret = new JSONObject();
		Database db = Database.getInstance();
		
		if (db.isMatched(json.getString("email1"), json.getString("email2"))) ret.put("resposta", 0);
		else ret.put("resposta", db.insertMatch(json.getString("email1"), json.getString("email2"), db.isStarted(json.getString("email1"), json.getString("email2"))));
		
		return ret;
	}
	
	@GET
	@Path("/isMatched")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject isMatch(JSONObject json) throws JSONException {
		
		Database db = Database.getInstance();
		JSONObject ret = new JSONObject();
		
		ret.put("resposta", db.isMatched(json.getString("email1"), json.getString("email2")));
		
		return ret;
	} 
	
	@POST
	@Path("/deleteMatch")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject deleteMatch(JSONObject json) throws JSONException {
		
		JSONObject ret = new JSONObject();
		Database db = Database.getInstance();
		
		if (!db.isMatched(json.getString("email1"), json.getString("email2")) && !db.isStarted(json.getString("email1"), json.getString("email2"))) ret.put("resposta", 0);
		ret.put("resposta", db.deleteMatch(json.getString("email1"), json.getString("email2")));
		
		return ret;
	}
}
