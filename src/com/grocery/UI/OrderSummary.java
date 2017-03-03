package com.grocery.UI;
//import java.awt.Dimension;
import java.awt.EventQueue;
//import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import com.grocery.controller.usecases.CustomerController;
import com.grocery.model.Address;
import com.grocery.model.CreditCard;
import com.grocery.model.Customer;
import com.grocery.model.Order;
import com.grocery.model.ShoppingCartItem;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;

public class OrderSummary extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double orderTotal;
	private CustomerController ct = new CustomerController();
	private String USER = Login.getUSER();
	private JTable cardTable;
	private JTable shippingTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderSummary frame = new OrderSummary();
					//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation(400,50);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderSummary() {
		setBackground(SystemColor.info);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 700);
		//this = new JPanel();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setthis(this);
		this.setLayout(null);
		
		JLabel lblProductsInThe = new JLabel("Products in Your Cart:");
		lblProductsInThe.setBounds(28, 24, 169, 14);
		this.add(lblProductsInThe);
		
		JButton btnBackToCart = new JButton("Back to Cart");
		btnBackToCart.setBackground(SystemColor.activeCaption);
		btnBackToCart.setForeground(SystemColor.text);
		btnBackToCart.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnBackToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goToShoppingCart();
			}
		});
		btnBackToCart.setBounds(28, 315, 105, 23);
		this.add(btnBackToCart);
		
		JLabel lblShippingAddress = new JLabel("Choose a Shipping Address:");
		lblShippingAddress.setBounds(28, 372, 169, 14);
		this.add(lblShippingAddress);
		
		JLabel lblShippingCreditCard = new JLabel("Choose a Credit Card:");
		lblShippingCreditCard.setBounds(28, 484, 169, 14);
		this.add(lblShippingCreditCard);
		
		JButton btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.setBackground(SystemColor.activeCaption);
		btnPlaceOrder.setForeground(SystemColor.text);
		btnPlaceOrder.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Customer customer = new Customer(USER,0);
				long CC = 0;
				int Em = 0, Ey = 0;
				if(cardTable.getSelectedRow()>=0){
					int index = cardTable.getSelectedRow();
					CC = (long) cardTable.getModel().getValueAt(cardTable.convertRowIndexToModel(index), cardTable.convertColumnIndexToModel(0));
					Em = (int) cardTable.getModel().getValueAt(cardTable.convertRowIndexToModel(index), cardTable.convertColumnIndexToModel(1));
					Ey = (int) cardTable.getModel().getValueAt(cardTable.convertRowIndexToModel(index), cardTable.convertColumnIndexToModel(2));
				}
				CreditCard creditCard = new CreditCard(CC,Em,Ey);
				
				String street = null,city = null,state = null;
				int zip = 0;
				if(shippingTable.getSelectedRow()>=0){
					int index = shippingTable .getSelectedRow();
					street = shippingTable .getModel().getValueAt(shippingTable.convertRowIndexToModel(index), shippingTable.convertColumnIndexToModel(0)).toString();
					city = shippingTable .getModel().getValueAt(shippingTable.convertRowIndexToModel(index), shippingTable.convertColumnIndexToModel(1)).toString();
					zip = (int) shippingTable .getModel().getValueAt(shippingTable.convertRowIndexToModel(index), shippingTable.convertColumnIndexToModel(2));
					state = shippingTable .getModel().getValueAt(shippingTable.convertRowIndexToModel(index), shippingTable.convertColumnIndexToModel(3)).toString();
					//state="IL";
				}
				Address address = new Address(street,city,zip,state);
				Order order = new Order(customer,address,creditCard,orderTotal);
				ct.placeOrder(order);
				 JOptionPane.showMessageDialog(null, "Order Placed", "", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnPlaceOrder.setBounds(399, 604, 105, 23);
		this.add(btnPlaceOrder);
		
		JLabel lblTotalCost = new JLabel("Total Cost:");
		lblTotalCost.setBounds(360, 319, 74, 14);
		this.add(lblTotalCost);
		
		 JLabel label = new JLabel("0$");
	     label.setBounds(421, 319, 67, 14);
	     this.add(label);
		
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setBounds(28,48,476,245);
	        this.add(scrollPane);
	        JTable cartTable = new JTable();
	        DefaultTableModel cartModel = new DefaultTableModel();
	        
	        Object[] cartColumnsName = new Object[3];     
	        cartColumnsName[0] = "Name";
	        cartColumnsName[1] = "Quantity";
	        cartColumnsName[2] = "Total Price";
	        cartModel.setColumnIdentifiers(cartColumnsName);
	       
	        ArrayList<ShoppingCartItem> shoppingCart = ct.viewShoppingCart(USER);
	        Object[] cartRowData = new Object[3];
	        for(int i = 0; i < shoppingCart.size(); i++){
	        	cartRowData[0] = shoppingCart.get(i).getProduct();
	        	int quantity = shoppingCart.get(i).getQuantity();
	        	cartRowData[1] = quantity;
	        	cartRowData[2] = (quantity)*(ct.getPrice(shoppingCart.get(i).getProduct(), USER));
	        	orderTotal += (quantity)*(ct.getPrice(shoppingCart.get(i).getProduct(), USER));
	            cartModel.addRow(cartRowData);
	        }
	        
	        label.setText(" $"+Double.toString(orderTotal));
	        
	        
	        scrollPane.setViewportView(cartTable);
	        cartTable.setModel(cartModel);
	        
	        
	        
	        JScrollPane scrollPane_1 = new JScrollPane();
	        scrollPane_1.setBounds(28, 395, 476, 78);
	        this.add(scrollPane_1);
	        
	        shippingTable = new JTable();
	        DefaultTableModel shippingModel = new DefaultTableModel();
		        
		        Object[] ColumnsName = new Object[4];     
		        ColumnsName[0] = "Street";
		        ColumnsName[1] = "City";
		        ColumnsName[2] = "Zip";
		        ColumnsName[3] = "State";
		        shippingModel.setColumnIdentifiers(ColumnsName);
		       
		        ArrayList<Address> address = ct.viewAddresses(USER);
		        Object[] RowData = new Object[4];
		        for(int i = 0; i < address.size(); i++){
		        	RowData[0] = address.get(i).getStreet();
		        	RowData[1] = address.get(i).getCity();
		        	RowData[2] = address.get(i).getZip();
		        	RowData[3] = address.get(i).getState();
		            shippingModel.addRow(RowData);
		        }
		       
		        scrollPane_1.setViewportView(shippingTable);
		        shippingTable.setModel(shippingModel);
		        
		        JScrollPane scrollPane_2 = new JScrollPane();
		        scrollPane_2.setBounds(28, 508, 476, 78);
		        this.add(scrollPane_2);
		        
		        cardTable = new JTable();
		        DefaultTableModel cardModel = new DefaultTableModel();
			        
			        Object[] ColumnsName1 = new Object[3];     
			        ColumnsName1[0] = "Credit Card No";
			        ColumnsName1[1] = "Expiration Month";
			        ColumnsName1[2] = "Expiration Year";
			        cardModel.setColumnIdentifiers(ColumnsName1);
			       
			        ArrayList<CreditCard> creditCard = ct.getAllCreditCard(USER);
			        Object[] RowData1 = new Object[3];
			        for(int i = 0; i <  creditCard.size(); i++){
			        	RowData1[0] =  creditCard.get(i).getCredit_card_no();
			        	RowData1[1] =  creditCard.get(i).getExpirationMonth();
			        	RowData1[2] =  creditCard.get(i).getExpirationYear();
			        	
			            cardModel.addRow(RowData1);
			        }
			       
			        scrollPane_2.setViewportView(cardTable);
			        cardTable.setModel(cardModel);
	       
	}
	private void goToShoppingCart(){
    	this.removeAll();
		JPanel pane = new ShoppingCart();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
    }
}
