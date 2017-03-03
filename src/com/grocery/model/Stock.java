package com.grocery.model;

public class Stock {
	
	private String productName;
	private int quantity;
	private int addressID;
	
	public Stock(String productName, int quantity, int addressID){
		this.productName = productName;
		this.quantity = quantity;
		this.addressID = addressID;
	}

	public Stock(String productName, int quantity) {
		this.productName = productName;
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

}
