package com.grocery.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import com.grocery.controller.usecases.CustomerController;
import com.grocery.model.Address;
import com.grocery.model.CreditCard;
import com.grocery.model.Customer;
import com.grocery.model.Product;
import com.grocery.model.ShoppingCartItem;

public class CustomerUnitTest {

	String customer = "xxxTestUserxxx";
	Long cc =1000000000000001L;
	CustomerController ct = new CustomerController();
	
	@Test
	public void createAndLoginCustomer() {
		if(!ct.loginCustomer(customer))
			ct.createCustomer(customer);
		assertTrue(ct.loginCustomer(customer));
	}
	
	@Test
	public void addCreditCard(){
		Customer cust = new Customer ("xxxTestUserxxx",0);
		CreditCard creditCard = new CreditCard(cc,cust,3,2017,25);
		if(ct.getAllCreditCard(customer).size()==0 || ct.getAllCreditCard(customer).get(0).getCredit_card_no()!=cc )
			ct.addCreditCard(creditCard);
		ArrayList<CreditCard> allCreditCards = ct.getAllCreditCard(customer);
		assertEquals(1,allCreditCards.size());	
	}
	
	/*@Test
	public void updateCreditCard(){
		int newAddId = 30;
		Customer cust = new Customer (customer,0);
		CreditCard creditCard = new CreditCard(cc,cust,3,2017,25);
		CreditCard newcreditCard = new CreditCard(cc,cust,3,2017,newAddId);
		if(ct.getAllCreditCard(customer).size()==0 || ct.getAllCreditCard(customer).get(0).getCredit_card_no()!=cc )
			ct.addCreditCard(creditCard);
		ct.updateCreditCard(newcreditCard);
		int addid = ct.getAllCreditCard(customer).get(0).getAddressID();
		assertEquals(newAddId,addid);	
	}*/
	
	@Test
	public void addAddress(){
		Address address = new Address("352 Springlake ln","Aurora",60504,"IL");
		if(ct.viewAddresses(customer).size()==0)
			ct.addAddress(address,customer);
		
		assertTrue(ct.viewAddresses(customer).size()!=0);
	}
	
	@Test
	public void addToShoppingCart(){
		if(ct.viewProducts(customer).size()==0){
			addAddress();
		}
		Product p = ct.viewProducts(customer).get(0);
		ShoppingCartItem shoppingCart = new ShoppingCartItem(p.getName(),17,customer);
		if(ct.viewShoppingCart(customer).get(0)==null)
			ct.addShoppingCartItem(shoppingCart);
		int qt = ct.viewShoppingCart(customer).get(0).getQuantity();
		assertEquals(17,qt);
	}
	
	

	
}
