package com.grocery.model;

import java.sql.Date;

public class Order {
	private int orderID;
	private Date date;
	private char status;
	private double orderTotal;
	//private ArrayList<OrderDetail> order_detail = new ArrayList<OrderDetail>();
	private Customer customer;
	private CreditCard credit_card;
	private Address address;
	
	public Order(int orderID, Date date, char status, /*ArrayList<OrderDetail> order_detail,*/ Customer customer, Address address, CreditCard credit_card){
		this.orderID = orderID;
		this.date = date;
		this.status = status;
		//this.order_detail.addAll(order_detail);
		this.customer = customer;
		this.credit_card = credit_card;
		this.address = address;
	}

	public Order(Customer customer, Address address, CreditCard credit_card, double orderTotal) {
		this.customer = customer;
		this.address = address;
		this.credit_card = credit_card;
		this.orderTotal = orderTotal;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public double getTotal() {
		return orderTotal;
	}

	public void setTotal(double total) {
		this.orderTotal = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CreditCard getCredit_card() {
		return credit_card;
	}

	public void setCredit_card(CreditCard credit_card) {
		this.credit_card = credit_card;
	}
}
