package com.javarevolutions.ws.rest.vo;

public class Xat {
	String xatId; 
	String email1; 
	String email2; 
	String username1; 
	String username2;
	
	public String getXatId() {
		return xatId;
	}
	public Xat(String xatId, String email1, String email2, String username1, String username2) {
		super();
		this.xatId = xatId;
		this.email1 = email1;
		this.email2 = email2;
		this.username1 = username1;
		this.username2 = username2;
	}
	public void setXatId(String xatId) {
		this.xatId = xatId;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getUsername1() {
		return username1;
	}
	public void setUsername1(String username1) {
		this.username1 = username1;
	}
	public String getUsername2() {
		return username2;
	}
	public void setUsername2(String username2) {
		this.username2 = username2;
	} 
}
