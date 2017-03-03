package com.grocery.controller.DBConnection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;
import com.grocery.controller.boundaryInterfacesDB.CustomerBIDB;
import com.grocery.model.Address;
import com.grocery.model.CreditCard;
import com.grocery.model.Order;
import com.grocery.model.Product;
import com.grocery.model.ShoppingCartItem;


public class CustomerDB implements CustomerBIDB {

	Connection connection = null;
	
	public CustomerDB(){
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
	
	//View products relative to customers zip
	@Override
	public ArrayList<Product> getAllProducts(String customerName) {
		
		ArrayList<Product> allProducts = new ArrayList<Product>();
		Statement stmt = null;
	    String query = "SELECT DISTINCT p.PRODUCTIMAGE, p.PRODUCTNAME,p.PRODUCTCATEGORY,p.PRODUCTSIZE,p.PRODUCTTYPE " +
	    		       "FROM Customer c, Address a, Product p, Shipping_address s, Price_state ps " +
	    			   "WHERE c.CUSTOMERNAME = s.CUSTOMERNAME " +
	    			   "AND a.ADDRESSID = s.ADDRESSID " +
	    			   "AND ps.PRODUCTNAME = p.PRODUCTNAME " +
	    			   "AND a.STATE = ps.PSTATE " +
	    			   "AND c.CUSTOMERNAME = ? ";
	    try {
	    	PreparedStatement updateemp = connection.prepareStatement(query);
  	      		updateemp.setString(1, customerName);
	        ResultSet rs = updateemp.executeQuery();
	        while (rs.next()) {
	            String PRODUCTNAME = rs.getString("PRODUCTNAME");
	            String PRODUCTCATEGORY = rs.getString("PRODUCTCATEGORY");
	            String PRODUCTTYPE = rs.getString("PRODUCTTYPE");
	            double PRODUCTSIZE = rs.getDouble("PRODUCTSIZE");
	            String PRODUCTIMAGE = rs.getString("PRODUCTIMAGE");
	            Product product = new Product(PRODUCTNAME,PRODUCTCATEGORY,PRODUCTTYPE,PRODUCTSIZE,PRODUCTIMAGE);
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
	
	//Add CreditCard for Customer
	@Override
	public void addCreditCard(CreditCard creditCard){
		Statement stmt = null;
	    String query = "INSERT INTO CREDITCARD VALUES (?,?,?,?,?)";
	    try {
	        PreparedStatement updateemp = connection.prepareStatement(query);
	        	      updateemp.setLong(1,creditCard.getCredit_card_no());
	        	     updateemp.setString(2,creditCard.getCustomerName());
	        	      updateemp.setInt(3,creditCard.getExpirationMonth());
	        	      updateemp.setInt(4,creditCard.getExpirationYear());
	        	      updateemp.setInt(5, creditCard.getAddressID());
	        	      updateemp.executeUpdate();
	    }
	    catch (SQLException e ) {
	    	e.printStackTrace();
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
	}
	
	@Override
	public void addToShoppingCart(ShoppingCartItem shoppingCart) {
		Statement stmt = null;
	    String query = "INSERT INTO SHOPPINGCART VALUES (?,?,?)";
	    try {
	        PreparedStatement updateemp = connection.prepareStatement(query);
	        	      updateemp.setString(1, shoppingCart.getProduct());
	        	      updateemp.setInt(2,shoppingCart.getQuantity());
	        	      updateemp.setString(3, shoppingCart.getCustomer_name());
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
	public int getAddressID(Address address){
		Statement stmt = null;
		String query = "SELECT ADDRESSID FROM ADDRESS WHERE STREET = ? AND CITY = ? AND ZIP = ? AND STATE = ?";
		try {
	        PreparedStatement updateemp = connection.prepareStatement(query);
	        	      updateemp.setString(1, address.getStreet());
	        	      updateemp.setString(2,address.getCity());
	        	      updateemp.setInt(3, address.getZip());
	        	      updateemp.setString(4,address.getState());
	        	      ResultSet rs = updateemp.executeQuery();
	        	      rs.next();
	        	      return rs.getInt("ADDRESSID");
	    }
	    catch (SQLException e ) {
	    	e.printStackTrace();
	    	return -1;
	    }
	    finally {
	        if (stmt != null){
	        	try { stmt.close(); }
	        	catch (SQLException e) { e.printStackTrace(); }
	        }
	     }
	}


	public void updateStock(String productName, int quantity,String customerName){
		Statement stmt = null;
		String query = "UPDATE STOCK "+
 			   		   "SET QUANTITY = ? "+
 			   		   "WHERE PRODUCTNAME = ? AND ADDRESSID = ? ";
		String queryStock = "SELECT UNIQUE s.QUANTITY,a.ADDRESSID FROM STOCK s,Address a "+
 			   		   		"WHERE s.PRODUCTNAME = ? AND a.ADDRESSID = s.ADDRESSID "+
 			   		   		"AND a.STATE IN( SELECT UNIQUE a.STATE FROM CUSTOMER c, SHIPPING_ADDRESS sa, Address a "+
 			   		   		"WHERE c.CUSTOMERNAME = sa.CUSTOMERNAME AND sa.ADDRESSID = a.ADDRESSID "+
 			   		   		"AND c.CUSTOMERNAME = ?) ";
		try {
	        PreparedStatement updateemp = connection.prepareStatement(queryStock);
	        	      updateemp.setString(1, productName);
	        	      updateemp.setString(2, customerName);
	        	      ResultSet rs = updateemp.executeQuery();
	        	      rs.next();
	        	      int qt = rs.getInt("QUANTITY");
	        	      int address = rs.getInt("ADDRESSID");
	        	      PreparedStatement update = connection.prepareStatement(query);
	        	      update.setInt(1, qt-quantity);
	        	      update.setString(2, productName);
	        	      update.setInt(3,address);
	        	      update.execute();
	        	      
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
	public void placeOrder(Order order) {
		int orderNo = 0;
		Statement stmt = null;
	    String queryShopping = "SELECT C.PRODUCTNAME,C.QUANTITY " +
	    		       			"FROM PRODUCT P, SHOPPINGCART C " +
	    		       			"WHERE P.PRODUCTNAME = C.PRODUCTNAME " +
	    		       			"AND C.CUSTOMERNAME = ? ";
	    String queryOrder = "INSERT INTO ORDERS VALUES (NULL, NULL,'I',?,?,?,?)";
	    String queryOrderNo = "SELECT ORDERID FROM ORDERS WHERE CUSTOMERNAME = ? AND ADDRESSID = ?"; 
	    String queryOrderDetail = "INSERT INTO ORDER_DETAIL VALUES (?,?,?)";
	    
	    try {
	    	//Creates a Order with a Unique Order ID
	    	PreparedStatement updateemp = connection.prepareStatement(queryOrder);
  	      	updateemp.setString(1,order.getCustomer().getName());
  	      	updateemp.setInt(2,getAddressID(order.getAddress())); 
  	      	updateemp.setLong(3,order.getCredit_card().getCredit_card_no());
  	      	updateemp.setFloat(4,(float) order.getTotal());
  	      	updateemp.executeUpdate();
  	      	
  	      	//Retrieves the Order ID for that order
  	      	PreparedStatement updategetOID = connection.prepareStatement(queryOrderNo);
  	      		updategetOID.setString(1,order.getCustomer().getName());
  	      		updategetOID.setInt(2,getAddressID(order.getAddress()));
  	      	ResultSet rsOID = updategetOID.executeQuery();
  	      	while(rsOID.next()){
  	      		int tempORDERID = rsOID.getInt("ORDERID");
  	      		if(tempORDERID>=orderNo)
  	      			orderNo=tempORDERID;
  	      	}
			

  	      	//Populates the Order detail for that Order from Shopping Cart
  	        PreparedStatement updateod = connection.prepareStatement(queryOrderDetail);
  	        PreparedStatement getShopping = connection.prepareStatement(queryShopping);
      			getShopping.setString(1, order.getCustomer().getName());

	        ResultSet rs = getShopping.executeQuery();
	        while (rs.next()) {
	            String PRODUCTNAME = rs.getString("PRODUCTNAME");
	            int QUANTITY = rs.getInt("QUANTITY");
	            ShoppingCartItem shoppingCart = new ShoppingCartItem (PRODUCTNAME,QUANTITY,order.getCustomer().getName());
	            updateod.setString(1,shoppingCart.getProduct());
	  	      	updateod.setInt(2,shoppingCart.getQuantity());
	  	      	updateod.setLong(3,orderNo);
	  	      	updateod.executeUpdate();
	  	      	updateStock(shoppingCart.getProduct(), QUANTITY, order.getCustomer().getName());
	        }
	        
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
	public boolean deleteCreditCard(CreditCard creditCard) {
		Statement stmt = null;
	    String query = "DELETE FROM CREDITCARD WHERE CREDITCARDNO = ? ";
	    try {
	    	PreparedStatement updateemp = connection.prepareStatement(query);
  	      	updateemp.setLong(1,creditCard.getCredit_card_no());
  	      	updateemp.executeUpdate();
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    	return false;
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
	    return true;
	}

	@Override
	public void updateCreditCard(CreditCard creditCard,CreditCard origCreditCard) {
		Statement stmt = null;
	    String query = "UPDATE CREDITCARD "+
	    			   "SET EXPIRATIONMONTH = ?,EXPIRATIONYEAR = ?,ADDRESSID = ? "+
	    			   "WHERE CUSTOMERNAME = ? AND CREDITCARDNO = ? ";
    try {
        PreparedStatement updateemp = connection.prepareStatement(query);
        	      updateemp.setInt(1, creditCard.getExpirationMonth());
        	      updateemp.setInt(2, creditCard.getExpirationYear());
        	      updateemp.setInt(3, creditCard.getAddressID());
        	      updateemp.setString(4, creditCard.getCustomerName());
        	      updateemp.setLong(5, origCreditCard.getCredit_card_no());
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

	//Add Address
	@Override
	public void addAddress(Address address, String customerName) {
		Statement stmt = null;
		int AddressID;
	    String query = "INSERT INTO ADDRESS VALUES (NULL,?,?,?,?)";
	    String queryAddressID = "SELECT ADDRESSID FROM ADDRESS WHERE STREET = ? AND  CITY = ?  AND ZIP = ? AND STATE = ? ";
	   String queryShipping = "INSERT INTO SHIPPING_ADDRESS VALUES (?,?)";
	    try {
	        PreparedStatement updateemp = connection.prepareStatement(query);
	        	      updateemp.setString(1, address.getStreet());
	        	      updateemp.setString(2,address.getCity());
	        	      updateemp.setInt(3, address.getZip());
	        	      updateemp.setString(4, address.getState());
	        	      updateemp.executeUpdate();
	        PreparedStatement select = connection.prepareStatement(queryAddressID);
	        	      select.setString(1, address.getStreet());
	        	      select.setString(2,address.getCity());
	        	      select.setInt(3, address.getZip());
	        	      select.setString(4, address.getState());
	        	      select.executeUpdate();
	      	ResultSet rs = select.executeQuery();
	      	ArrayList<Integer> addresses = new ArrayList<Integer>();
	      	while (rs.next()) {
	      		addresses.add(rs.getInt("ADDRESSID"));	
	      	}
	      	AddressID = addresses.get(addresses.size()-1);
	      	PreparedStatement shipping_address = connection.prepareStatement(queryShipping);
    		shipping_address.setString(1, customerName);
    		shipping_address.setInt(2,AddressID);
    		shipping_address.executeUpdate();
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
	public void deleteFromShoppingCart(ShoppingCartItem shoppingCart) {
		Statement stmt = null;
	    String query = "DELETE FROM  SHOPPINGCART WHERE CUSTOMERNAME=? AND PRODUCTNAME=? ";
	    try {
	    	PreparedStatement updateemp = connection.prepareStatement(query);
  	      	updateemp.setString(1, shoppingCart.getCustomer_name());
  	      	updateemp.setString(2, shoppingCart.getProduct());
  	      	updateemp.executeUpdate();
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
	}
	
	
	@Override
	public void deleteAddress(Address address) {
		Statement stmt = null;
	    String query = "DELETE FROM ADDRESS WHERE ADDRESSID = ? ";
		 JOptionPane.showMessageDialog(null, getAddressID(address), "", JOptionPane.INFORMATION_MESSAGE);

	    try {
	    	PreparedStatement updateemp = connection.prepareStatement(query);
	    	updateemp.setInt(1, getAddressID(address));
  	      	updateemp.executeUpdate();

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
	}
	@Override
	public void updateAddress(Address address, Address origAddress) {
		Statement stmt = null;
	    String query = "UPDATE ADDRESS "+
	    			   "SET STREET = ?,CITY = ?,ZIP = ?,STATE = ? "+
	    			   "WHERE ADDRESSID = ? ";
    try {
        PreparedStatement updateemp = connection.prepareStatement(query);
        	      updateemp.setString(1, address.getStreet());
        	      updateemp.setString(2, address.getCity());
        	      updateemp.setInt(3, address.getZip());
        	      updateemp.setString(4, address.getState());
        	      updateemp.setInt(5, getAddressID(origAddress));
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
	public void createCustomer(String customerName) {
		Statement stmt = null;
	    String query = "INSERT INTO CUSTOMER VALUES (?,0)";
	    try {
	        PreparedStatement updateemp = connection.prepareStatement(query);
	        	      updateemp.setString(1, customerName);
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
	public boolean loginCustomer(String customerName) {
		boolean isValid = false;
		Statement stmt = null;
	    String query = "SELECT CUSTOMERNAME " +
	    		       "FROM CUSTOMER " +
	    			   "WHERE CUSTOMERNAME = '"+customerName+"' ";
	    try {
	        stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        rs.next();
	        String Customer = rs.getString("CUSTOMERNAME");
	        if(Customer!=null){
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
	public ArrayList<CreditCard> getAllCreditCards(String customerName) {
		ArrayList<CreditCard> allCreditCards = new ArrayList<CreditCard>();
		Statement stmt = null;
	    String query = "SELECT cc.CREDITCARDNO,cc.EXPIRATIONMONTH,cc.EXPIRATIONYEAR,cc.ADDRESSID " +
	    		       "FROM CreditCard cc, Customer c " +
	    			   "WHERE cc.CUSTOMERNAME = c.CUSTOMERNAME " +
	    			   "AND c.CUSTOMERNAME = '"+customerName+"' ";
	    try {
	        stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            long CREDITCARDNO = rs.getLong("CREDITCARDNO");
	            int EXPIRATIONMONTH = rs.getInt("EXPIRATIONMONTH");
	            int EXPIRATIONYEAR = rs.getInt("EXPIRATIONYEAR");
	            int ADDRESSID = rs.getInt("ADDRESSID");
	            CreditCard CreditCard = new CreditCard(CREDITCARDNO,EXPIRATIONMONTH,EXPIRATIONYEAR,ADDRESSID);
	            allCreditCards.add(CreditCard);
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
		return allCreditCards;
	}

	//View All Addresses
	@Override
	public ArrayList<Address> getAllAddresses(String customerName) {
		ArrayList<Address> allAddresses = new ArrayList<Address>();
		Statement stmt = null;
	    String query = "SELECT a.STREET,a.CITY,a.ZIP,a.STATE " +
	    		       "FROM ADDRESS a, CUSTOMER c, SHIPPING_ADDRESS s " +
	    			   "WHERE s.ADDRESSID = a.ADDRESSID " +
	    			   "AND s.CUSTOMERNAME = c.CUSTOMERNAME " +
	    			   "AND c.CUSTOMERNAME = '"+customerName+"' ";
	    try {
	        stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            String STREET = rs.getString("STREET");
	            String CITY = rs.getString("CITY");
	            int ZIP = rs.getInt("ZIP");
	            String STATE = rs.getString("STATE");
	            Address address = new Address(STREET,CITY,ZIP,STATE);
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

	@Override
	public ArrayList<ShoppingCartItem> viewShoppingCart(String customerName) {
		ArrayList<ShoppingCartItem> shoppingCart = new ArrayList<ShoppingCartItem>();
		Statement stmt = null;
	    String query = "SELECT * FROM SHOPPINGCART " +
	    			   "WHERE SHOPPINGCART.CUSTOMERNAME = '"+customerName+"' ";
	    try {
	        stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            String CUSTOMERNAME = rs.getString("CUSTOMERNAME");
	            int QUANTITY = rs.getInt("QUANTITY");
	            String PRODUCTNAME = rs.getString("PRODUCTNAME");
	            ShoppingCartItem spi = new ShoppingCartItem(PRODUCTNAME,QUANTITY,CUSTOMERNAME);
	            shoppingCart.add(spi);
	        }
	    }
	    catch (SQLException e ) { e.printStackTrace(); }
	    finally {
	        if (stmt != null){
	        	try { stmt.close(); }
	        	catch (SQLException e) { e.printStackTrace(); }
	        }
	    }
		return shoppingCart;
	}

	@Override
	public void updateShoppingCart(ShoppingCartItem shoppingCart) {
		//Statement stmt = null;
	    String query = "UPDATE SHOPPINGCART "+
	    			   "SET QUANTITY = ? "+
	    			   "WHERE CUSTOMERNAME = ? AND PRODUCTNAME = ? ";
    try {
        PreparedStatement updateemp = connection.prepareStatement(query);
        	      updateemp.setInt(1, shoppingCart.getQuantity());
        	      updateemp.setString(2, shoppingCart.getCustomer_name());
        	      updateemp.setString(3, shoppingCart.getProduct());
        	      updateemp.executeUpdate();
    }
    catch (SQLException e ) { e.printStackTrace(); }

     }

	@Override
	public double getPrice(String productName, String customerName) {
		double Price = 0;
		String query= "SELECT p.PRICE, p.PRODUCTNAME "+
					  "FROM PRICE_STATE p, PRODUCT pr, CUSTOMER c, SHIPPING_ADDRESS s, ADDRESS a "+
					  "WHERE p.PRODUCTNAME = pr.PRODUCTNAME AND "+
					  "c.CUSTOMERNAME = s.CUSTOMERNAME AND "+
					  "s.ADDRESSID = a.ADDRESSID AND "+
					  "a.STATE = p.PSTATE AND "+
					  "c.CUSTOMERNAME = ? AND "+
					  "p.PRODUCTNAME = ? ";
					  
		   try {
			   	PreparedStatement updateemp = connection.prepareStatement(query);
			   	updateemp.setString(1, customerName);
			   	updateemp.setString(2, productName);
			   	updateemp.executeUpdate();
			   	ResultSet rs = updateemp.executeQuery();
		        rs.next();
		        Price = rs.getDouble("PRICE");
		   }	
		   catch (SQLException e ) { e.printStackTrace(); }
		   
		return Price;
		}

	@Override
	public Address getAddressByCC(CreditCard creditCard) {
		Address address = null;
		String query = "SELECT A.STREET,A.CITY,A.ZIP,A.STATE FROM Address A,CreditCard C WHERE A.AddressID = C.AddressID AND C.CreditCardNo=?";
	    try {
	    	PreparedStatement updateemp = connection.prepareStatement(query);
	      		updateemp.setLong(1, creditCard.getCredit_card_no());
	      	ResultSet rs = updateemp.executeQuery();
	      	rs.next();
            String STREET = rs.getString("STREET");
            String CITY = rs.getString("CITY");
            int ZIP = rs.getInt("ZIP");
            String STATE = rs.getString("STATE");
            address = new Address(STREET,CITY,ZIP,STATE);
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    } 
		return address;
	}
}
	


