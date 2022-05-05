package com.javarevolutions.ws.rest.vo;

public class VOUsuario {
	private String usuario; 
	private String nom; 
	private int edad; 
	private String password; 
	private boolean userValido;
	private String email;
	
	public VOUsuario() {
		this.nom = null; 
		this.email = null; 
		this.password = null; 
	}
	
	public VOUsuario(String nom, String email, String password) {
		this.nom = nom; 
		this.email = email; 
		this.password = password; 
	}
	
	public String getUsuario() {
		return usuario;
	}
	public String getNom() {
		return nom;
		
	}
	public int getEdad() {
		return edad; 
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isUserValido() {
		return userValido;
	}
	public void setUserValido(boolean userValido) {
		this.userValido = userValido;
	}
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}
	public void setNom(String nom) {
		this.nom = nom; 
		
	}
	public void setEdad(int edad) {
		this.edad = edad; 
		
	}
	public void setEmail(String email) {
		this.email = email; 
	}
}
