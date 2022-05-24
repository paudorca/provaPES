package com.javarevolutions.ws.rest.kmeans;

import java.util.Map;

public class Centroid {

    private Map<String, Double> coordinates = null;

	public Centroid(Map<String, Double> coordinates) {
		this.coordinates = coordinates; 
	}

	public Map<String, Double> getCoordinates() {
		return coordinates;
	}

	@Override
	public String toString() {
		return "Centroid [coordinates=" + coordinates + ", getCoordinates()=" + getCoordinates() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}