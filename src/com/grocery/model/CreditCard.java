package com.grocery.model;

public class CreditCard {
	private long credit_card_no;
	private Customer customer;
	private int expirationMonth;
	private int expirationYear;
	private int billing_address;
	
	public CreditCard(long ccno, Customer customer, int expirationMonth,int expirationYear, int billing_address){
		this.credit_card_no = ccno;
		this.customer = customer;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.billing_address = billing_address;
	}
	public CreditCard(long ccno, int expirationMonth,int expirationYear, int billing_address){
		this.credit_card_no = ccno;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.billing_address = billing_address;
	}
	public CreditCard(long ccno, int expirationMonth,int expirationYear){
		this.credit_card_no = ccno;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
	}
	
	
	public CreditCard(){
		
	}

	public CreditCard(long ccno, Customer customer, int expirationMonth,int expirationYear) {
		this.credit_card_no = ccno;
		this.customer = customer;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
	}
	public long getCredit_card_no() {
		return credit_card_no;
	}

	public void setCredit_card_no(long credit_card_no) {
		this.credit_card_no = credit_card_no;
	}

	public int getExpirationMonth() {
		return expirationMonth;
	}
	public int getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationMonth(int expiration) {
		this.expirationMonth = expiration;
	}
	public void setExpirationYear(int expiration) {
		this.expirationYear = expiration;
	}
	
	public String getCustomerName(){
		return customer.getName();
	}
	public void setAddressID(int addressID){
		billing_address = addressID;
	}
	public int getAddressID(){
		return billing_address;
	}
	
}
