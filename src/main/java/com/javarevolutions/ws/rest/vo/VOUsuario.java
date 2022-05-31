package com.javarevolutions.ws.rest.vo;

public class VOUsuario {
	private String nom; 
	private String edat;
	private String email;
	private String descripcio;
	private String foto;
	private int punts;
	
	public VOUsuario() {
		this.nom = null; 
		this.email = null; 
	}
	
	public VOUsuario(String nom, String email) {
		this.nom = nom; 
		this.email = email; 
	}
	public String getNom() {
		return nom;
		
	}
	public void setNom(String nom) {
		this.nom = nom; 
		
	}
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio; 
		
	}
	public String getEdat() {
		return edat; 
	}
	public void setEdat(String edat) {
		this.edat = edat; 
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email; 
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		if(foto == null) this.foto = "";
		else this.foto = foto; 
	}
	public int getPunts() {
		return punts;
	}
	public void setPunts(int punts) {
		this.punts = punts; 
	}
}
