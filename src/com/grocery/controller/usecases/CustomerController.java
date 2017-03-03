package com.grocery.controller.usecases;
import java.util.ArrayList;

import com.grocery.controller.DBConnection.CustomerDB;
import com.grocery.controller.boundaryInterfacesDB.CustomerBIDB;
import com.grocery.model.Address;
import com.grocery.model.CreditCard;
import com.grocery.model.Customer;
import com.grocery.model.Order;
import com.grocery.model.Product;
import com.grocery.model.ShoppingCartItem;

public class CustomerController {
	
	private CustomerBIDB customerBIDB = new CustomerDB();
	
	public ArrayList<Product> viewProducts(String customerName){
		ArrayList<Product> products = new ArrayList<Product>();
		products.addAll(customerBIDB.getAllProducts(customerName));
		
		return products;
	}
	
	public ArrayList<Address> viewAddresses(String customerName){
		ArrayList<Address> Addresss = new ArrayList<Address>();
		Addresss.addAll(customerBIDB.getAllAddresses(customerName));
		return Addresss;
	}
	
	public ArrayList<CreditCard> viewCreditCards(Customer customer){
		ArrayList<CreditCard> CreditCards = new ArrayList<CreditCard>();
		CreditCards.addAll(customerBIDB.getAllCreditCards(customer.getName()));
		return CreditCards;
	}
	
	public void createCustomer(String customerName){
		customerBIDB.createCustomer(customerName);
		customerBIDB.loginCustomer(customerName);
	}
	
	public boolean loginCustomer(String customerName){
		return customerBIDB.loginCustomer(customerName);
	}
	
	public void addCreditCard(CreditCard creditCard){
		customerBIDB.addCreditCard(creditCard);
	}
	public void updateCreditCard(CreditCard creditCard, CreditCard origCreditCard){
		customerBIDB.updateCreditCard(creditCard,origCreditCard);
	}
	public boolean deleteCreditCard(CreditCard creditCard){
		return customerBIDB.deleteCreditCard(creditCard);
	}
	public ArrayList<CreditCard> getAllCreditCard(String customerName){
		return customerBIDB.getAllCreditCards(customerName);
	}
	
	public Address getAddress(CreditCard creditCard){
		return customerBIDB.getAddressByCC(creditCard);
	}
	
	public void addAddress(Address address, String customerName){
		customerBIDB.addAddress(address, customerName);
	}
	public void updateAddress(Address address,Address origAddress){
		customerBIDB.updateAddress(address, origAddress);
	}
	public void deleteAddress(Address address){
		customerBIDB.deleteAddress(address);
	}
	
	public void addShoppingCartItem(ShoppingCartItem shoppingCart){
		customerBIDB.addToShoppingCart(shoppingCart);
	}
	public void deleteShoppingCartItem(ShoppingCartItem shoppingCart){
		customerBIDB.deleteFromShoppingCart(shoppingCart);
	}
	public void updateShoppingCart(ShoppingCartItem shoppingCart){
		customerBIDB.updateShoppingCart(shoppingCart);
	}
	
	public double getPrice(String productName, String customerName){
		return customerBIDB.getPrice(productName, customerName);
	}
	public ArrayList<ShoppingCartItem> viewShoppingCart(String customerName){
		return customerBIDB.viewShoppingCart(customerName);
	}
	
	public void placeOrder(Order order){
		customerBIDB.placeOrder(order);
	}
	public int getAddressID(Address address){
		return customerBIDB.getAddressID(address);
	}
}