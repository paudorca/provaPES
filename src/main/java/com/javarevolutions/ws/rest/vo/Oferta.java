package com.javarevolutions.ws.rest.vo;

public class Oferta {
	private String email; 
	private String adr;
	private int codiPostal; 
	private String poblacio;
	private String habitacions;
	private String descripcio; 
	private String nivellEnergetic;
	private String numCas;
	private int numeroOcupants;
	private int superficie;
	private int preu;
	
	public Oferta() {
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
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
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio; 
	} 
	public String getHabitacions() {
		return habitacions;
	}
	public void setHabitacions(String habitacions) {
		this.habitacions = habitacions;
	}
	public int getSuperficie() {
		return superficie;
	}
	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}
	public String getNumCas() {
		return numCas;
	}
	public void setNumCas(String numCas) {
		this.numCas = numCas;
	}
}
