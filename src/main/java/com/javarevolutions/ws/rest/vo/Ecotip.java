package com.javarevolutions.ws.rest.vo;
import java.sql.Array;
import java.util.ArrayList;

public class Ecotip {
	private int id;
	private String descripcio;
	//private ArrayList<Pregunta> preguntes;
	//private ArrayList<Image> imatges; 
	
	public Ecotip (int id) {
		this.id = id; 
	}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcio() {
		return this.descripcio;
	}
	public void setDescripcio(String desc) {
		this.descripcio = desc;
	}
}
	
