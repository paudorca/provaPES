package com.javarevolutions.ws.rest.vo;

public class Oferta {
	private String email; 
	private String carrer; 
	private int numero; 
	private int codiPostal; 
	private String poblacio; 
	private String nivellEnergetic; 
	private int numeroOcupants; 
	private int preu;
	
	public Oferta() {
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCarrer() {
		return carrer;
	}
	public void setCarrer(String carrer) {
		this.carrer = carrer;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getCodiPostal() {
		return codiPostal;
	}
	public void setCodiPostal(int string) {
		this.codiPostal = string;
	}
	public String getPoblacio() {
		return poblacio;
	}
	public void setPoblacio(String poblacio) {
		this.poblacio = poblacio;
	}
	public String getNivellEnergetic() {
		return nivellEnergetic;
	}
	public void setNivellEnergetic(String nivellEnergetic) {
		this.nivellEnergetic = nivellEnergetic;
	}
	public int getNumeroOcupants() {
		return numeroOcupants;
	}
	public void setNumeroOcupants(int numeroOcupants) {
		this.numeroOcupants = numeroOcupants;
	}
	public int getPreu() {
		return preu;
	}
	public void setPreu(int preu) {
		this.preu = preu;
	} 
}
