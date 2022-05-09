package com.javarevolutions.ws.rest.vo;

import java.util.ArrayList;

public class Pregunta {
	private int id; 
	private String text;
	private ArrayList<String> respostes;
	private int respostaCorrecta;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcio() {
		return text;
	}
	public void setDescripcio(String descripcio) {
		this.text = descripcio;
	}
	public ArrayList<String> getRespostes() {
		return respostes;
	}
	public void setRespostes(ArrayList<String> respostes) {
		this.respostes = respostes;
	}
	public int getRespostaCorrecta() {
		return respostaCorrecta;
	}
	public void setRespostaCorrecta(int respostaCorrecta) {
		this.respostaCorrecta = respostaCorrecta;
	} 
}
