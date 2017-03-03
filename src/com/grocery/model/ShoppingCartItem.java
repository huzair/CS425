package com.grocery.model;

public class ShoppingCartItem {
	private String customer_name;
	private String product;
	private int quantity;
	
	//private Map<String,Integer> product_quantity = new HashMap<String,Integer>();
	
	public ShoppingCartItem(String product, int quantity, String customer_name){
		this.product = product;
		this.quantity = quantity;
		this.customer_name = customer_name;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

