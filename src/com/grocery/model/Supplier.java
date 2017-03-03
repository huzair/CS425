package com.grocery.model;

public class Supplier {
	private String name;
	private Address address;
	//private ArrayList<SupplierSells> sells = new ArrayList<SupplierSells>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}