package com.grocery.UI;

import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.grocery.controller.usecases.CustomerController;
import com.grocery.model.Address;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;

public class ShippingInfo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JPanel this;
	private JTextField textStreet;
	private JTextField textCity;
	private JTextField textZip;
	private JTextField textState;
	private JScrollPane scrollPane; 
	private JTable shippingTable;
	private DefaultTableModel shippingModel;
	private CustomerController ct = new CustomerController();
	private String USER = Login.getUSER();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShippingInfo frame = new ShippingInfo();
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
	public ShippingInfo() {
		setBackground(SystemColor.info);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 700);
		//this = new JPanel();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setthis(this);
		this.setLayout(null);
		
		JLabel lblChooseAShipping = new JLabel("Left click to select a shipping address, use the delete and update button");
		lblChooseAShipping.setBounds(119, 25, 400, 20);
		this.add(lblChooseAShipping);
		
		JLabel label = new JLabel("Street:");
		label.setBounds(65, 395, 86, 14);
		this.add(label);
		
		JLabel label_1 = new JLabel("City:");
		label_1.setBounds(75, 419, 59, 14);
		this.add(label_1);
		
		JLabel label_2 = new JLabel("Zip Code:");
		label_2.setBounds(53, 446, 81, 14);
		this.add(label_2);
		
		JLabel label_3 = new JLabel("  State:");
		label_3.setBounds(63, 473, 88, 14);
		this.add(label_3);
		
		textStreet = new JTextField();
		textStreet.setText("street number & street name");
		textStreet.setColumns(10);
		textStreet.setBounds(119, 395, 294, 20);
		this.add(textStreet);
		
		textCity = new JTextField();
		textCity.setText("city");
		textCity.setColumns(10);
		textCity.setBounds(119, 420, 294, 20);
		this.add(textCity);
		
		textZip = new JTextField();
		textZip.setText("zip code");
		textZip.setColumns(10);
		textZip.setBounds(119, 445, 294, 20);
		this.add(textZip);
		
		textState = new JTextField();
		textState.setText("2\u2013letter state abbreviation (for example: IL)");
		textState.setColumns(10);
		textState.setBounds(119, 470, 294, 20);
		this.add(textState);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAdd.setForeground(SystemColor.text);
		btnAdd.setBackground(SystemColor.activeCaption);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ct.addAddress(retFromFields(), USER);
				updateShippingTable(retFromFields());
			}
		});
		btnAdd.setBounds(119, 501, 141, 23);
		this.add(btnAdd);
		
		JPanel panel = new JPanel();
		panel.setBounds(119, 56, 379, 245);
		this.add(panel);

		panel.setLayout(null);

		shippingTable = new JTable();
		scrollPane = new JScrollPane();
		
		shippingTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(getShippingFromTable()!=null){
				textStreet.setText(getShippingFromTable().getStreet());
				textCity.setText(getShippingFromTable().getCity());
				String zip = Integer.toString(getShippingFromTable().getZip());
				textZip.setText(zip);
				textState.setText(getShippingFromTable().getState());
				}
			}
		});
		scrollPane.setBounds(0, 0, 379, 245);
		panel.add(scrollPane);
		
		
	        	shippingModel = new DefaultTableModel();
		        
		        Object[] ColumnsName = new Object[4];     
		        ColumnsName[0] = "Street";
		        ColumnsName[1] = "City";
		        ColumnsName[2] = "Zip";
		        ColumnsName[3] = "State";
		        shippingModel.setColumnIdentifiers(ColumnsName);
		       
		        ArrayList<Address> address= ct.viewAddresses(USER);
		        Object[] RowData = new Object[4];
		        for(int i = 0; i < address.size(); i++){
		        	RowData[0] = address.get(i).getStreet();
		        	RowData[1] = address.get(i).getCity();
		        	RowData[2] = address.get(i).getZip();
		        	RowData[3] = address.get(i).getState();
		            shippingModel.addRow(RowData);
		        }
		       
		        scrollPane.setViewportView(shippingTable);
		        shippingTable.setModel(shippingModel);
		
		
		//scrollPane.setViewportView(table);
		
		JLabel lblAddANew = new JLabel("Add a new shipping address:");
		lblAddANew.setBounds(119, 371, 217, 14);
		this.add(lblAddANew);
		
		JButton btnUpdateChanges = new JButton("Update Changes");
		btnUpdateChanges.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnUpdateChanges.setForeground(SystemColor.text);
		btnUpdateChanges.setBackground(SystemColor.activeCaption);
		btnUpdateChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ct.updateAddress(retFromFields(),getShippingFromTable());
				shippingModel.removeRow(shippingTable.getSelectedRow());
				updateShippingTable(retFromFields());
			}
		});
		btnUpdateChanges.setBounds(270, 501, 141, 23);
		this.add(btnUpdateChanges);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnDelete.setForeground(SystemColor.text);
		btnDelete.setBackground(SystemColor.activeCaption);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ct.deleteAddress(getShippingFromTable());
				shippingModel.removeRow(shippingTable.getSelectedRow());
				textStreet.setText(null);
				textCity.setText(null);
				textZip.setText(null);
				textState.setText(null);
			}
		});
		btnDelete.setBounds(229, 312, 141, 23);
		this.add(btnDelete);
		
		JLabel lblStock = new JLabel("Shipping Address:");
		lblStock.setBounds(7, 55, 129, 14);
		this.add(lblStock);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToMenu();
			}
		});
		btnMenu.setForeground(Color.WHITE);
		btnMenu.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnMenu.setBackground(SystemColor.activeCaption);
		btnMenu.setBounds(272, 627, 141, 23);
		add(btnMenu);
	}
	
	public Address getShippingFromTable(){
		int index = 0,zip = 0;
		String city = null, street = null, state = null;
		if(shippingTable.getSelectedRow()>=0){
		index = shippingTable.getSelectedRow();
		street = shippingTable.getModel().getValueAt(shippingTable.convertRowIndexToModel(index), shippingTable.convertColumnIndexToModel(0)).toString();
		city = shippingTable.getModel().getValueAt(shippingTable.convertRowIndexToModel(index), shippingTable.convertColumnIndexToModel(1)).toString();
		zip = (int) shippingTable.getModel().getValueAt(shippingTable.convertRowIndexToModel(index), shippingTable.convertColumnIndexToModel(2));
		state = shippingTable.getModel().getValueAt(shippingTable.convertRowIndexToModel(index), shippingTable.convertColumnIndexToModel(3)).toString();
		}
	return new Address(street,city,zip,state);
	}
	
	public Address retFromFields(){
		int zip = Integer.parseInt(textZip.getText());
		String city = textCity.getText(), street = textStreet.getText()
					  ,state = textState.getText();
		return new Address(street,city,zip,state);
	}

	private void updateShippingTable(Address address) {
		Object[] RowData = new Object[4];
		RowData[0] = address.getStreet();
	    RowData[1] = address.getCity();
	    RowData[2] = address.getZip();
	    RowData[3] = address.getState();
	    shippingModel.addRow(RowData);
	    scrollPane.setViewportView(shippingTable);
	    shippingTable.setModel(shippingModel);
	    shippingTable.invalidate();
	}
	private void goToMenu() {
		this.removeAll();
		JPanel pane = new CustomerMenu();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
}
