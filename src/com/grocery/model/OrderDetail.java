package com.grocery.model;

public class OrderDetail {
	private int orderID;
	private String product_name;
	private int quantity;
	
	public OrderDetail(int orderID, String product_name, int quantity){
		this.orderID = orderID;
		this.product_name = product_name;
		this.quantity = quantity;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
