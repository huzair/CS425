package com.grocery.UI;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import com.grocery.controller.usecases.StaffController;
import com.grocery.model.Product;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;

public class ModifyPrice extends JPanel {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -8193031516767625213L;
	private StaffController sc = new StaffController();
	//private JPanel this;
	private JLabel lblPricing;
	private JScrollPane scrollPane;
	private JButton button;
	private JLabel lblPrice;
	private JTextField textPrice;
	private JTextField textState;
	private JLabel lblState;
	private JLabel label_2;
	private JTextField textProduct;
	private JLabel lblAddANew;
	private JButton btnDelete;
	private JLabel lblPriceInfo;
	private JTable priceTable;
	private DefaultTableModel priceModel;
	private JButton btnMenu;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyPrice frame = new ModifyPrice();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public ModifyPrice() {
		setBackground(SystemColor.info);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 700);
		//this = new JPanel();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setthis(this);
		this.setLayout(null);
		
		// -------------- Update button ------------------
		JButton btnUpdateAndSave = new JButton("Update");
		btnUpdateAndSave.setBackground(SystemColor.activeCaption);
		btnUpdateAndSave.setForeground(SystemColor.text);
		btnUpdateAndSave.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnUpdateAndSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Product product = getProductFromTable();
				boolean isUpdate = sc.updateProductPrice(product.getName(), Double.parseDouble(textPrice.getText()), product.getState());
				if(isUpdate){
					priceModel.removeRow(priceTable.getSelectedRow());
					updatePriceTable(retFromFields());
					JOptionPane.showMessageDialog(null, "Price Updated", "", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Update Failed", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		btnUpdateAndSave.setBounds(244, 505, 141, 23);
		this.add(btnUpdateAndSave);
		
		lblPricing = new JLabel("Left click to select a product");
		lblPricing.setBounds(95, 21, 250, 14);
		this.add(lblPricing);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(95, 46, 414, 269);
		this.add(scrollPane);
		
		priceTable = new JTable();
		scrollPane.setViewportView(priceTable);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setBounds(42, 477, 116, 14);
		this.add(lblPrice);
		
		textPrice = new JTextField();
		textPrice.setText("for example: 5.00");
		textPrice.setColumns(10);
		textPrice.setBounds(92, 474, 294, 20);
		this.add(textPrice);
		
		textState = new JTextField();
		textState.setText("2\u2013letter state abbreviation (for example: IL)");
		textState.setColumns(10);
		textState.setBounds(92, 449, 294, 20);
		this.add(textState);
		
		lblState = new JLabel("State:");
		lblState.setBounds(42, 452, 129, 14);
		this.add(lblState);
		
		label_2 = new JLabel("Product:");
		label_2.setBounds(30, 427, 151, 14);
		this.add(label_2);
		
		textProduct = new JTextField();
		textProduct.setText("Product's name");
		textProduct.setColumns(10);
		textProduct.setBounds(92, 424, 294, 20);
		this.add(textProduct);
		
		lblAddANew = new JLabel("Add a new price information:");
		lblAddANew.setBounds(93, 399, 217, 14);
		this.add(lblAddANew);
		
		// -------------- Delete button ------------------
		btnDelete = new JButton("Delete");
		btnDelete.setBackground(SystemColor.activeCaption);
		btnDelete.setForeground(SystemColor.text);
		btnDelete.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isDelete = sc.deleteProductPrice(getProductFromTable());
				if(isDelete){
					priceModel.removeRow(priceTable.getSelectedRow());
				}
				else{
					 JOptionPane.showMessageDialog(null, "Delete Failed", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		btnDelete.setBounds(95, 342, 414, 23);
		this.add(btnDelete);
		
		lblPriceInfo = new JLabel("Price Info:");
		lblPriceInfo.setBounds(20, 46, 151, 14);
		this.add(lblPriceInfo);
		
		// -------------- Set Fields to selected table item ------------------
		priceTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(getProductFromTable()!=null){
				textProduct.setText(getProductFromTable().getName());
				textState.setText(getProductFromTable().getState());
				textPrice.setText(Double.toString(getProductFromTable().getPrice()));
				}
			}
		});
		
		// -------------- Add button ------------------
		button = new JButton("Add");
		button.setBackground(SystemColor.activeCaption);
		button.setForeground(SystemColor.text);
		button.setFont(new Font("Calibri", Font.PLAIN, 14));
		button.setBounds(93, 505, 141, 23);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Product product = retFromFields();
				 boolean isAdd = sc.addProductPrice(product.getName(), product.getPrice(), product.getState());
				 if(isAdd){
					 updatePriceTable(retFromFields());
					 JOptionPane.showMessageDialog(null, "Price Added", "", JOptionPane.INFORMATION_MESSAGE);
				 }
				 else{
					 JOptionPane.showMessageDialog(null, "Add Failed", "", JOptionPane.INFORMATION_MESSAGE);
				 }
			}
		});
		
		this.add(button);
	
	// -------------- Populate the table ------------------
	priceModel = new DefaultTableModel();
    
    Object[] ColumnsName = new Object[3];   
    ColumnsName[0] = "Product Name";
    ColumnsName[1] = "State";
    ColumnsName[2] = "Price";
    
    priceModel.setColumnIdentifiers(ColumnsName);
   
    ArrayList<Product> price = sc.getAllPrice();
    Object[] RowData = new Object[3];
    for(int i = 0; i < price.size(); i++){
    	RowData[0] = price.get(i).getName();
    	RowData[1] = price.get(i).getState();
    	RowData[2] = price.get(i).getPrice();
        priceModel.addRow(RowData);
    }
   
    scrollPane.setViewportView(priceTable);
    priceTable.setModel(priceModel);    
    
    btnMenu = new JButton("Menu");
    btnMenu.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		goToMenu();
    	}
    });
    btnMenu.setForeground(Color.WHITE);
    btnMenu.setFont(new Font("Calibri", Font.PLAIN, 14));
    btnMenu.setBackground(SystemColor.activeCaption);
    btnMenu.setBounds(92, 581, 141, 23);
    add(btnMenu);
    
    
	}
	
	public Product getProductFromTable(){
		int index = 0;
		String name = null, state = null;
		double price = 0;
		if(priceTable.getSelectedRow()>=0){
		index = priceTable.getSelectedRow();
		name = priceTable.getModel().getValueAt(priceTable.convertRowIndexToModel(index), priceTable.convertColumnIndexToModel(0)).toString();
		state = priceTable.getModel().getValueAt(priceTable.convertRowIndexToModel(index), priceTable.convertColumnIndexToModel(1)).toString();
		price = (double) priceTable.getModel().getValueAt(priceTable.convertRowIndexToModel(index), priceTable.convertColumnIndexToModel(2));
		}
	return new Product(name,price,state);
	}
	
	public Product retFromFields(){
		String product = textProduct.getText();
		String state = textState.getText();
		double price = Double.parseDouble(textPrice.getText());
		return new Product(product,price,state);
	}
	
	private void updatePriceTable(Product product) {
		Object[] RowData = new Object[3];
		RowData[0] = product.getName();
	    RowData[1] = product.getState();
	    RowData[2] = product.getPrice();
	    priceModel.addRow(RowData);
	    scrollPane.setViewportView(priceTable);
	    priceTable.setModel(priceModel);
	    priceTable.invalidate();
	}
	private void goToMenu() {
		this.removeAll();
		JPanel pane = new StaffMenu();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
}


