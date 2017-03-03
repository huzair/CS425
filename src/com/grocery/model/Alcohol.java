package com.grocery.model;

public class Alcohol extends Product {
	
	private double alcohol_content;
	
	public Alcohol(String name, String category, double size, String type) {
		super(name, category, size, type);
	}

	public double getAlcohol_content() {
		return alcohol_content;
	}

	public void setAlcohol_content(double alcohol_content) {
		this.alcohol_content = alcohol_content;
	}


}
