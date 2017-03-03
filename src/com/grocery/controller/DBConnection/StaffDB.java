package com.grocery.controller.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.grocery.model.Stock;
import com.grocery.controller.boundaryInterfacesDB.StaffBIDB;
import com.grocery.model.Address;
import com.grocery.model.Product;
import com.grocery.model.Staff;
import com.grocery.model.Warehouse;

public class StaffDB implements StaffBIDB{

Connection connection = null;
	
	public StaffDB(){
		try { Class.forName("oracle.jdbc.driver.OracleDriver"); }
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		try { 
			if(connection==null)
				connection = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", "jyeung","super8andtab");
			}
		catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
	
	@Override
	public void createStaff(Staff staff) {
		Statement stmt = null;
	    String query = "INSERT INTO STAFF VALUES(?,?,?,?)";
	    try {
	        PreparedStatement updateemp = connection.prepareStatement(query);
	        	      updateemp.setString(1, staff.getName());
	        	      updateemp.setDouble(2, staff.getSalary());
	        	      updateemp.setString(3, staff.getJobTitle());
	        	      updateemp.setInt(4, staff.getAddress().getAddressID());
	        	      updateemp.executeUpdate();
	    }
	    catch (SQLException e ) { e.printStackTrace(); }
	    finally {
	        if (stmt != null){
	        	try { stmt.close(); }
	        	catch (SQLException e) { e.printStackTrace(); }
	        }
	    }
	}

	@Override
	public boolean loginStaff(String staffName) {
		boolean isValid = false;
		Statement stmt = null;
	    String query = "SELECT STAFFNAME " +
	    		       "FROM STAFF " +
	    			   "WHERE STAFFNAME = '"+staffName+"' ";
	    try {
	        stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        rs.next();
	        String staff = rs.getString("STAFFNAME");
	        if(staff!=null){
	        	isValid = true;
	        }
	    } catch (SQLException e ) {
	    	try { stmt.close(); }
	    	catch (SQLException e1) { e1.printStackTrace(); }
	    	return false;
	    }
	    finally {
	        if (stmt != null){
	        	try { stmt.close(); }
	        	catch (SQLException e) { e.printStackTrace();}
	        }
	    }
		return isValid;
		
	}

	@Override
	public boolean addProduct(Product product) {
		boolean isAdd = true;
		Statement stmt = null;
	    String query = "INSERT INTO PRODUCT VALUES (?,?,?,?)";
	    try {
	        PreparedStatement updateemp = connection.prepareStatement(query);
	        	      updateemp.setString(1, product.getName());
	        	      updateemp.setString(2,product.getCategory());
	        	      updateemp.setDouble(3, product.getSize());
	        	      updateemp.setString(4, product.getType());
	        	      updateemp.executeUpdate();
	    }
	    catch (SQLException e ) {
	    	e.printStackTrace();
	    	isAdd = false;
	    }
	    finally {
	        if (stmt != null){
	        	try { stmt.close(); }
	        	catch (SQLException e) { e.printStackTrace(); }
	        }
	     }
	    return isAdd;
	}

	@Override
	public boolean deleteProduct(String productName) {
		boolean isDelete = true;
		Statement stmt = null;
	    String query = "DELETE FROM PRODUCT WHERE PRODUCTNAME = ?";
	    try {
	        PreparedStatement updateemp = connection.prepareStatement(query);
	        	      updateemp.setString(1, productName);
	        	      updateemp.executeUpdate();
	    }
	    catch (SQLException e ) {
	    	e.printStackTrace();
	    	isDelete = false;
	    }
	    finally {
	        if (stmt != null){
	        	try { stmt.close(); }
	        	catch (SQLException e) { e.printStackTrace(); }
	        }
	     }
	    return isDelete;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean isUpdate = true;
		Statement stmt = null;
		    String query = "UPDATE PRODUCT " +
		    			   "SET PRODUCTNAME = ?,PRODUCTCATEGORY=?,PRODUCTSIZE=? ,PRODUCTTYPE =? "+
                           "WHERE PRODUCTNAME = ? ";
	    try {
	        PreparedStatement updateemp = connection.prepareStatement(query);
	        	      updateemp.setString(1, product.getName());
	        	      updateemp.setString(2,product.getCategory());
	        	      updateemp.setDouble(3, product.getSize());
	        	      updateemp.setString(4, product.getType());
	        	      updateemp.setString(5, product.getName());
	        	      updateemp.executeUpdate();
	    }
	    catch (SQLException e ) {
	    	e.printStackTrace();
	    	isUpdate = false;
	    }
	    finally {
	        if (stmt != null){
	        	try { stmt.close(); }
	        	catch (SQLException e) { e.printStackTrace(); }
	        }
	     }
	    return isUpdate;
	}

	@Override
	public ArrayList<Product> getAllProducts() {

		ArrayList<Product> allProducts = new ArrayList<Product>();
		Statement stmt = null;
	    String query = "SELECT DISTINCT * FROM PRODUCT";
	    try {
	        stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            String PRODUCTNAME = rs.getString("PRODUCTNAME");
	            String PRODUCTCATEGORY = rs.getString("PRODUCTCATEGORY");
	            String PRODUCTTYPE = rs.getString("PRODUCTTYPE");
	            double PRODUCTSIZE = rs.getDouble("PRODUCTSIZE");
	            String IMAGE = rs.getString("PRODUCTIMAGE");
	            Product product = new Product(PRODUCTNAME,PRODUCTCATEGORY,PRODUCTTYPE,PRODUCTSIZE,IMAGE);
	            allProducts.add(product);
	        }
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    } finally {
	        if (stmt != null){
	        	try {
	        		stmt.close();
	        	}
	        	catch (SQLException e) {
				e.printStackTrace();					
	        	}
	        }
	    }
		return allProducts;
	}

	@Override
	public boolean addProductPrice(String productName, double productPrice, String state) {
		Statement stmt = null;
	    String query = "INSERT INTO PRICE_STATE VALUES(?,?,?)";
	    try {
	        PreparedStatement updateemp = connection.prepareStatement(query);
	        	      updateemp.setString(3, productName);
	        	      updateemp.setDouble(1, productPrice);
	        	      updateemp.setString(2, state);
	        	      updateemp.executeUpdate();
	    }
	    catch (SQLException e ) {
	    	e.printStackTrace();
	    	return false;
	    }
	    finally {
	        if (stmt != null){
	        	try { stmt.close(); }
	        	catch (SQLException e) { e.printStackTrace(); }
	        }
	    }
	    return true;
	}

		
	

	@Override
	public boolean deleteProductPrice(Product p) {
		Statement stmt = null;
	    String query = "DELETE FROM PRICE_STATE WHERE PRODUCTNAME = ? AND PSTATE=? AND PRICE = ?";
	    try {
	        PreparedStatement updateemp = connection.prepareStatement(query);
	        	      updateemp.setString(1, p.getName());
	        	      updateemp.setString(2, p.getState());
	        	      updateemp.setDouble(3, p.getPrice());
	        	      updateemp.executeUpdate();
	    }
	    catch (SQLException e ) {
	    	e.printStackTrace();
	    	return false;
	    }
	    finally {
	        if (stmt != null){
	        	try {
	        		stmt.close();
	        	}
	        	catch (SQLException e) {
	        		e.printStackTrace();
	        	}
	        }
	     }
	    return true;
	}

	@Override
	public boolean updateProductPrice(String productName, double productPrice, String state) {
			Statement stmt = null;
			    String query = "UPDATE PRICE_STATE " +
			    			   "SET PRICE=? "+
	                           "WHERE PRODUCTNAME = ? AND PSTATE=? ";
		    try {
		        PreparedStatement updateemp = connection.prepareStatement(query);
		        	      updateemp.setDouble(1,productPrice);
		        	      updateemp.setString(2, productName);
		        	      updateemp.setString(3, state);
		        	      updateemp.executeUpdate();
		    }
		    catch (SQLException e ) {
		    	e.printStackTrace();
		    	return false;
		    }
		    finally {
		        if (stmt != null){
		        	try { stmt.close(); }
		        	catch (SQLException e) { e.printStackTrace(); }
		        }
		     }
		    return true;
		}

	@Override
	public void addStock(Stock stock, Warehouse warehouse) {
		Statement stmt = null;
		String queryfind = "SELECT UNIQUE PRODUCTNAME FROM STOCK WHERE PRODUCTNAME = ? AND ADDRESSID = ? ";
	    String query1 = "INSERT INTO STOCK VALUES(?,?,?) ";
	    String query2 = "UPDATE STOCK SET QUANTITY = ? WHERE PRODUCTNAME = ? AND ADDRESSID = ? ";

    try {
    	PreparedStatement find = connection.prepareStatement(queryfind);
    	find.setString(1, stock.getProductName());
    	find.setInt(2,warehouse.getAddressID());
    	ResultSet rs = find.executeQuery();
    	boolean isFind  = false;
    	isFind = rs.next();
		 JOptionPane.showMessageDialog(null, warehouse.getAddressID(), "", JOptionPane.INFORMATION_MESSAGE);

    	if(!isFind){
        PreparedStatement updateemp1 = connection.prepareStatement(query1);
        	      updateemp1.setString(1, stock.getProductName() );
        	      updateemp1.setInt(2, warehouse.getAddressID());
        	      updateemp1.setInt(3, stock.getQuantity());
        	      updateemp1.executeUpdate();
    	}
    
    	else{
    		PreparedStatement updateemp2 = connection.prepareStatement(query2);
			updateemp2.setInt(1,stock.getQuantity());
			updateemp2.setString(2, stock.getProductName());
			updateemp2.setInt(3, warehouse.getAddressID());
			updateemp2.executeUpdate();
    	}
     
    }
    catch (SQLException e ) {   	
    	e.printStackTrace();
    	}
    finally {
        if (stmt != null){
        	try { stmt.close(); }
        	catch (SQLException e) { e.printStackTrace(); }
        }
     }
   }

	@Override
	public ArrayList<Address> getAllWarehouses() {
		ArrayList<Address> allAddresses = new ArrayList<Address>();
		Statement stmt = null;
	    String query = "SELECT a.STREET,a.CITY,a.ZIP,a.STATE,a.ADDRESSID " +
	    		       "FROM ADDRESS a, WAREHOUSE w " +
	    			   "WHERE a.ADDRESSID = w.ADDRESSID ";
	    try {
	        stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            String STREET = rs.getString("STREET");
	            String CITY = rs.getString("CITY");
	            int ZIP = rs.getInt("ZIP");
	            String STATE = rs.getString("STATE");
	            int ADDRESSID = rs.getInt("ADDRESSID");
	            Address address = new Address(STREET,CITY,ZIP,STATE,ADDRESSID);
	            allAddresses.add(address);
	        }
	    }
	    catch (SQLException e ) { e.printStackTrace(); }
	    finally {
	        if (stmt != null){
	        	try { stmt.close(); }
	        	catch (SQLException e) { e.printStackTrace(); }
	        }
	    }
		return allAddresses;
	}
	
	public ArrayList<Stock> getAllStock(){
		ArrayList<Stock> allStock = new ArrayList<Stock>();
		Statement stmt = null;
	    String query = "SELECT PRODUCTNAME, QUANTITY, ADDRESSID " +
	    		       "FROM STOCK ";
	    try {
	        stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            String PRODUCTNAME = rs.getString("PRODUCTNAME");
	            int QUANTITY = rs.getInt("QUANTITY");
	            int ADDRESSID = rs.getInt("ADDRESSID");
	            Stock stock = new Stock(PRODUCTNAME,QUANTITY,ADDRESSID);
	            allStock.add(stock);
	        }
	    }
	    catch (SQLException e ) { e.printStackTrace(); }
	    finally {
	        if (stmt != null){
	        	try { stmt.close(); }
	        	catch (SQLException e) { e.printStackTrace(); }
	        }
	    }
		return allStock;
	}

	@Override
	public ArrayList<Product> getAllPrice() {
		ArrayList<Product> allProducts = new ArrayList<Product>();
		Statement stmt = null;
	    String query = "SELECT PRODUCTNAME, PSTATE, PRICE " +
	    		       "FROM PRICE_STATE ";
	    try {
	        stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            String PRODUCTNAME = rs.getString("PRODUCTNAME");
	            String PSTATE = rs.getString("PSTATE");
	            double PRICE = rs.getDouble("PRICE");
	            Product pstate = new Product(PRODUCTNAME,PRICE,PSTATE);
	            allProducts.add(pstate);
	        }
	    }
	    catch (SQLException e ) { e.printStackTrace(); }
	    finally {
	        if (stmt != null){
	        	try { stmt.close(); }
	        	catch (SQLException e) { e.printStackTrace(); }
	        }
	    }
		return allProducts;
	}

}
