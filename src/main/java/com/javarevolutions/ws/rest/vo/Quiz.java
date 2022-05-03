package com.javarevolutions.ws.rest.vo;

import java.util.ArrayList;

public class Quiz {
	private int id;
	ArrayList<Pregunta> preguntes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Pregunta> getPreguntes() {
		return preguntes;
	}
	public void setPreguntes(ArrayList<Pregunta> preguntes) {
		this.preguntes = preguntes;
	} 
}
