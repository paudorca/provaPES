package com.javarevolutions.ws.rest.vo;

public class Like {
	int id;
	VOUsuario usuari1; 
	VOUsuario usuari2; 
	
	public Like(int id, VOUsuario usuari1, VOUsuario usuari2) {
		this.id = id; 
		this.usuari1 = usuari1; 
		this.usuari2 = usuari2;
	}
	
	public int getId() {
		return id; 
	}
	
	public VOUsuario getUsuari1() {
		return usuari1; 
	}
	
	public VOUsuario getUsuari2() {
		return usuari2; 
	}
}
