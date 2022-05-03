package com.javarevolutions.ws.rest.vo;
import java.sql.Array;

import java.util.ArrayList;

public class Ecotip {
	private int id;
	private String titol;
	private String text;
	private int idQuiz;

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitol() {
		return this.titol;
	}
	public void setTitol(String titol) {
		this.titol = titol;
	}
	public String getText() {
		return this.text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getQuiz() {
		return idQuiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
}
	
