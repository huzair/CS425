package com.grocery.model;

public class Product {
	private String name;
	private String category;
	private double size;
	private String type;
	private double price;
	private String state;
	private String image;
	
	//private Map<String,Double> price_state = new HashMap<String,Double>();
	
	public Product(String name,String category,double size, String type){
		this.name = name;
		this.category = category; 
		this.size = size;
		this.type = type;
	}

	public Product(String name, double price, String state) {
		this.name = name;
		this.price = price;
		this.state = state;
	}

	public Product(String name, String category, String type, double size, String image) {
		this.name = name;
		this.category = category;
		this.type = type;
		this.size = size;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
