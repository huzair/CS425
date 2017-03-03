package com.grocery.controller.boundaryInterfacesDB;

import java.util.ArrayList;
import com.grocery.model.Address;
import com.grocery.model.CreditCard;
import com.grocery.model.Order;
import com.grocery.model.Product;
import com.grocery.model.ShoppingCartItem;

public interface CustomerBIDB {
	public ArrayList<Product> getAllProducts(String customerName); //done
	
	public void createCustomer(String customerName); //done -- Tested
	public boolean loginCustomer(String customerName); //done -- Tested
	
	public void addToShoppingCart(ShoppingCartItem shoppingCart); // done -- Tested
	public void deleteFromShoppingCart(ShoppingCartItem shoppingCart);
	public void updateShoppingCart(ShoppingCartItem shoppingCart); // done -- Tested
	public ArrayList<ShoppingCartItem> viewShoppingCart(String customerName); //done -- Tested
	
	public double getPrice(String productName,String customerName);
	
	public void placeOrder(Order order); //partial -- Partial Testing done
	
	public void addCreditCard(CreditCard creditCard); //done -- Tested
	public boolean deleteCreditCard(CreditCard creditCard); //done -- Tested
	public ArrayList<CreditCard> getAllCreditCards(String customerName); //done -- Tested
	
	public void addAddress(Address address,String customerName); //done -- Tested
	public void deleteAddress(Address address); //doing
	public void updateAddress(Address address, Address origAddress);
	public ArrayList<Address> getAllAddresses(String customerName); //done -- Tested
	public int getAddressID(Address address);

	public Address getAddressByCC(CreditCard creditCard);

	public void updateCreditCard(CreditCard creditCard, CreditCard origCreditCard);
}

