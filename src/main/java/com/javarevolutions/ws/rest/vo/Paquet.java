package com.javarevolutions.ws.rest.vo;

import java.io.Serializable;

public class Paquet implements Serializable {
	private String nom; 
	private String ip; 
	private String missatge;
	
	public Paquet(String nom, String ip, String missatge) {
		super();
		this.nom = nom;
		this.ip = ip;
		this.missatge = missatge;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMissatge() {
		return missatge;
	}
	public void setMissatge(String missatge) {
		this.missatge = missatge;
	}
}
