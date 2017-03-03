package com.grocery.controller.boundaryInterfacesDB;

import java.util.ArrayList;


import com.grocery.model.Address;
import com.grocery.model.Product;
import com.grocery.model.Staff;
import com.grocery.model.Stock;
import com.grocery.model.Warehouse;

public interface StaffBIDB {

	public void createStaff(Staff staff); //done
	public boolean loginStaff(String staffName); //done
	
	public boolean addProduct(Product product); //done
	public boolean deleteProduct(String productName); //done
	public boolean updateProduct(Product product); //done
	public ArrayList<Product> getAllProducts(); //done
	
	public boolean addProductPrice(String productName, double productPrice, String state); //done
	public boolean deleteProductPrice(Product p); //done
	public boolean updateProductPrice(String productName, double productPrice, String state); //done
	
	public ArrayList<Product> getAllPrice();
	
	public ArrayList<Address> getAllWarehouses();
	public ArrayList<Stock> getAllStock();
	
	public void addStock(Stock stock, Warehouse warehouse); //done
}
