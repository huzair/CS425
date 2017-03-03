package com.grocery.model;

public class Warehouse {
	//private Map<String,Integer> product_quantity = new HashMap<String,Integer>();
	private double capacity;
	private int addressID;
	
	public Warehouse(double capacity, int addressID){
		this.capacity = capacity;
		this.addressID = addressID;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddress(int addressID) {
		this.addressID = addressID;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
}
