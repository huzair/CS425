package com.grocery.controller.usecases;

import java.util.ArrayList;

import com.grocery.controller.DBConnection.StaffDB;
import com.grocery.controller.boundaryInterfacesDB.StaffBIDB;
import com.grocery.model.Address;
import com.grocery.model.Product;
import com.grocery.model.Staff;
import com.grocery.model.Stock;
import com.grocery.model.Warehouse;

public class StaffController {
	private StaffBIDB StaffBIDB = new StaffDB();

	public boolean loginStaff(String staffName){
		return StaffBIDB.loginStaff(staffName);
	}
	
	public void createStaff(Staff staff){
		StaffBIDB.createStaff(staff);
		StaffBIDB.loginStaff(staff.getName());
	}
	
	public boolean addProduct(Product product){
		return StaffBIDB.addProduct(product);
	}
	
	public boolean updateProduct(Product product){
		return StaffBIDB.updateProduct(product);
	}
	public boolean deleteProduct(String productName){
		return StaffBIDB.deleteProduct(productName);
	}
	public ArrayList<Product> viewProducts(){
		ArrayList<Product> Products = new ArrayList<Product>();
		Products.addAll(StaffBIDB.getAllProducts());
		return Products;
	}
	
	public boolean addProductPrice(String productName, double productPrice, String state){
		return StaffBIDB.addProductPrice(productName, productPrice, state);
	}
	
	public boolean updateProductPrice(String productName, double productPrice, String state){
		return StaffBIDB.updateProductPrice(productName, productPrice, state);
	}
	
	public boolean deleteProductPrice(Product p){
		return StaffBIDB.deleteProductPrice(p);
	}
	
	public void addStock(Stock stock, Warehouse warehouse){
		StaffBIDB.addStock(stock,warehouse);
	}
	
	public ArrayList<Address> getAllWarehouses(){
		return StaffBIDB.getAllWarehouses();
	}
	
	public ArrayList<Stock> getAllStock(){
		return StaffBIDB.getAllStock();
	}
	public ArrayList<Product> getAllProduct(){
		return StaffBIDB.getAllProducts();
	}

	public ArrayList<Product> getAllPrice() {
		return StaffBIDB.getAllPrice();
	}
}
