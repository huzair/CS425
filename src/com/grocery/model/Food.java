package com.grocery.model;

public class Food extends Product{
	
	private String nutrition;
	
	public Food(String name, String category, double size, String type) {
		super(name, category, size, type);
	}

	public String getNutrition() {
		return nutrition;
	}

	public void setNutrition(String nutrition) {
		this.nutrition = nutrition;
	}

}
