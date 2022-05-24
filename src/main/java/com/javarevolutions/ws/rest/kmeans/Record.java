package com.javarevolutions.ws.rest.kmeans;

import java.util.Map;

public class Record {
	
    private String description;
    private Map<String, Double> features;
    
    public Record(String description) {
    	this.description = description; 
    }
    
	public String getDescription() {
		return description;
	}
	public Map<String, Double> getFeatures() {
		return features;
	}
	
	public void setFeatures(Map<String, Double> features) {
		this.features = features; 
	}
}
