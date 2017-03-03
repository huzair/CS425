package com.grocery.model;

public class SupplierSells {
	private String product_name;
	private double price;
	private int quantity;
	
	public SupplierSells(String product_name, double price, int quantity){
		this.product_name = product_name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
