package com.grocery.model;

public class Address {
	private int addressID;
	private String street;
	private String city;
	private int zip;
	private String state;
	
	public Address(String street, String city, int zip, String state)
	{
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.state = state;
	}
	public Address(String street, String city, int zip, String state, int addressID)
	{
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.addressID = addressID;
	}
	public Address(){
		
	}
	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
