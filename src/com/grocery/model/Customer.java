package com.grocery.model;

import java.util.ArrayList;

public class Customer {
	private String name;
	private double balance;
	private ArrayList<Address> address = new ArrayList<Address>();
	//private ArrayList<CreditCard> credit_card = new ArrayList<CreditCard>();
	
	public Customer(String name, double balance){
		this.name = name;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public ArrayList<Address> getAddress() {
		return address;
	}
}
