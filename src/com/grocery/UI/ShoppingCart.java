package com.grocery.UI;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import com.grocery.controller.usecases.CustomerController;
import com.grocery.model.Product;
import com.grocery.model.ShoppingCartItem;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class ShoppingCart extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7119673621171184379L;
	private CustomerController ct = new CustomerController();
	private String USER = Login.getUSER();
	private JTable productTable;
	private JSpinner spinner_8;
	private JButton btnAddToCart;
	private JButton btnDelete;
	private JButton button;
	private JSpinner spinner;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTable cartTable;
	DefaultTableModel cartModel;
	private JButton button_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShoppingCart frame = new ShoppingCart();
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getHeight()/2);
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
	public ShoppingCart() {
		setBackground(SystemColor.info);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 700);
		//this = new JPanel();
		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setthis(this);
		this.setLayout(null);
		
		JLabel lblShoppingCart = new JLabel("Shopping Cart:");
		lblShoppingCart.setBounds(29, 345, 113, 14);
		this.add(lblShoppingCart);
		
		JButton btnPlaceOrder = new JButton("Order Summary");
		btnPlaceOrder.setBackground(SystemColor.activeCaption);
		btnPlaceOrder.setForeground(SystemColor.window);
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goToOrderSummary();
				
				 //JFrame home = new OrderSummary();
				// home.setVisible(true);
				 //this.setVisible(false);
				 
			}
		});
		btnPlaceOrder.setBounds(358, 651, 131, 23);
		this.add(btnPlaceOrder);
		
		JLabel lblProducts = new JLabel("Products:");
		lblProducts.setBounds(29, 19, 69, 14);
		this.add(lblProducts);
		
		spinner_8 = new JSpinner();
		spinner_8.setBounds(284, 289, 69, 20);
		this.add(spinner_8);
		
		btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.setBackground(SystemColor.activeCaption);
		btnAddToCart.setForeground(SystemColor.window);
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(productTable.getSelectedRow()>=0){
					int quantity = (int) spinner_8.getValue();
					int index = productTable.getSelectedRow();
					String productName = productTable.getModel().getValueAt(productTable.convertRowIndexToModel(index), productTable.convertColumnIndexToModel(0)).toString();
					ShoppingCartItem shoppingCart = new ShoppingCartItem(productName,quantity,USER);
					if(quantity<=0)
						JOptionPane.showMessageDialog(null,"Quantity invalid", "InfoBox: " + "title", JOptionPane.INFORMATION_MESSAGE);
					else
						ct.addShoppingCartItem(shoppingCart);
					Object[] cartRowData = new Object[2];
					cartRowData[0] = productName;
				    cartRowData[1] = quantity;
				    cartModel.addRow(cartRowData);
				    scrollPane_1.setViewportView(cartTable);
				    cartTable.setModel(cartModel);
				    cartTable.invalidate();
				}}
		});
		btnAddToCart.setBounds(358, 288, 131, 23);
		this.add(btnAddToCart);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBackground(SystemColor.activeCaption);
		btnDelete.setForeground(SystemColor.window);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cartTable.getSelectedRow()>=0){
					int index = cartTable.getSelectedRow();
					String productName = cartTable.getModel().getValueAt(cartTable.convertRowIndexToModel(index), cartTable.convertColumnIndexToModel(0)).toString();
					String str_quantity = cartTable.getModel().getValueAt(cartTable.convertRowIndexToModel(index), cartTable.convertColumnIndexToModel(1)).toString();
					int quantity = Integer.parseInt(str_quantity);
					ShoppingCartItem shoppingCart = new ShoppingCartItem(productName,quantity,USER);
					ct.deleteShoppingCartItem(shoppingCart);
					cartModel.removeRow(index);
				}
			}
		});
		btnDelete.setBounds(358, 577, 131, 23);
		this.add(btnDelete);
		
		button = new JButton("Update Quantity");
		button.setBackground(SystemColor.activeCaption);
		button.setForeground(SystemColor.window);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//System.out.println("Breakpoint");
				if(cartTable.getSelectedRow()>=0){
					int index = cartTable.getSelectedRow();
					String productName = cartTable.getModel().getValueAt(cartTable.convertRowIndexToModel(index), cartTable.convertColumnIndexToModel(0)).toString();
					int quantity = (int) spinner.getValue();
					ShoppingCartItem shoppingCart = new ShoppingCartItem(productName,quantity,USER);
					ct.updateShoppingCart(shoppingCart);
				
					cartModel.removeRow(index);
					Object[] cartRowData = new Object[2];
					cartRowData[0] = productName;
				    cartRowData[1] = quantity;
				    cartModel.addRow(cartRowData);
				    scrollPane_1.setViewportView(cartTable);
				    cartTable.setModel(cartModel);
				    cartTable.invalidate();
				}
			}
		});
		button.setBounds(358, 543, 131, 23);
		this.add(button);
		
		spinner = new JSpinner();
		spinner.setBounds(299, 544, 54, 20);
		this.add(spinner);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 44, 460, 231);
		this.add(scrollPane);
		
		//Populates the product table
		productTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        
        Object[] columnsName = new Object[5];     
        columnsName[0] = "Name";
        columnsName[1] = "Category";
        columnsName[2] = "Type";
        columnsName[3] = "Size";      
        columnsName[4] = "Price";
        model.setColumnIdentifiers(columnsName);
        
        Object[] rowData = new Object[5];

        ArrayList<Product> products = products();
        for(int i = 0; i < products.size(); i++){
            rowData[0] = products.get(i).getName();
            rowData[1] = products.get(i).getCategory();
            rowData[2] = products.get(i).getType();
            rowData[3] = products.get(i).getSize();
            rowData[4] = ct.getPrice(products.get(i).getName(), USER);
            model.addRow(rowData);
        }
        scrollPane.setViewportView(productTable);
        productTable.setModel(model);
        
        //------------------------------ End Product Population --------------------//
        
        
        //Populates Shopping Cart
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(39, 376, 450, 156);
        this.add(scrollPane_1);
        cartTable = new JTable();
        cartModel = new DefaultTableModel();
        
        Object[] cartColumnsName = new Object[2];     
        cartColumnsName[0] = "Name";
        cartColumnsName[1] = "Quantity";
        cartModel.setColumnIdentifiers(cartColumnsName);
       
        ArrayList<ShoppingCartItem> shoppingCart = ct.viewShoppingCart(USER);
        Object[] cartRowData = new Object[2];
        for(int i = 0; i < shoppingCart.size(); i++){
        	cartRowData[0] = shoppingCart.get(i).getProduct();
        	cartRowData[1] = shoppingCart.get(i).getQuantity();
            cartModel.addRow(cartRowData);
        }
        
        scrollPane_1.setViewportView(cartTable);
        cartTable.setModel(cartModel);
        
        button_1 = new JButton("Menu");
        button_1.setBackground(SystemColor.activeCaption);
        button_1.setForeground(SystemColor.window);
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		goToMenu();
        	}
        });
        button_1.setBounds(358, 15, 131, 23);
        this.add(button_1);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(49, 543, 161, 135);
        add(lblNewLabel);
        
        
        
        productTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					try{
					
						ArrayList<Product> products = products();
						int index = productTable.getSelectedRow();
						ImageIcon icon = new ImageIcon(new URL(products.get(index).getImage()));   //Place hoder, please change the ".jpg:" to the link string from DB. 			
						Image img = icon.getImage();
						double iconWidthd = icon.getIconWidth();
						double iconHeightd = icon.getIconHeight();
						double multiplier;
						int iconWidth;
						int iconHeight;
						if(iconWidthd>=iconHeightd){
							iconWidth = 207;
							multiplier = iconHeightd/iconWidthd;
							iconHeight =  (int) (207*multiplier); 
						}
						else{
							iconHeight = 317;
							multiplier = iconWidthd/iconHeightd;
							iconWidth = (int) (317*multiplier);
						}
					
	        			Image newimg = img.getScaledInstance(iconWidth,iconHeight,java.awt.Image.SCALE_SMOOTH);
	        			icon = new ImageIcon(newimg);
						lblNewLabel.setIcon(icon);
						
			        	    } catch (IOException e1) {
								e1.printStackTrace();
							}
	        			}    
					
			
		});
        
        //------------------------------ End Shopping Cart Population --------------------//
        
    
	
	}
	private void goToOrderSummary(){
    	this.removeAll();
		JPanel pane = new OrderSummary();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
    }
	private ArrayList<Product> products(){
		ArrayList<Product> listProducts = ct.viewProducts(USER);
		listProducts.sort((a, b)->a.getType().compareTo(b.getType()));
		return listProducts;
	}
	private void goToMenu(){
		this.removeAll();
		JPanel pane = new CustomerMenu();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
}
