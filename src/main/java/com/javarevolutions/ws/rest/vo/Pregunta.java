package com.javarevolutions.ws.rest.vo;

import java.util.ArrayList;

import com.javarevolutions.ws.rest.database.Database;


public class Pregunta {
	private int id; 
	private String text;
	private ArrayList<String> respostes;
	private int respostaCorrecta;
	
	public String getDescripcio() {
		return text;
	}
	public void setDescripcio(String descripcio) {
		this.text = descripcio;
	}
	public ArrayList<String> getRespostes() {
		return respostes;
	}
	public int getRespostaCorrecta() {
		return respostaCorrecta;
	}
	public void setRespostaCorrecta(int respostaCorrecta) {
		this.respostaCorrecta = respostaCorrecta;
	} 
}
