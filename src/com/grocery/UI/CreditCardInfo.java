package com.grocery.UI;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.grocery.controller.usecases.CustomerController;
import com.grocery.model.Address;
import com.grocery.model.CreditCard;
import com.grocery.model.Customer;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.SystemColor;

public class CreditCardInfo extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCreditCardNumber;
	private JTextField textCC;
	private JLabel lblCardHolder;
	private JLabel ExpDate;
	private JTextField textName;
	private JTextField textMM;
	private JLabel lblCreditCardInfo;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JLabel lblYourCreditCards;
	private JLabel lblBillingAddress_1;
	private JScrollPane scrollPane_2;
	
	private JTable cardTable;
	DefaultTableModel cardModel;
	private CustomerController ct = new CustomerController();
	private String USER = Login.getUSER();
	private JTextField textYY;
	private JScrollPane scrollPane_1;
	private JTable billingAddress;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditCardInfo frame = new CreditCardInfo();
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
	public CreditCardInfo() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 700);
		//this = new JPanel();
		this.setBackground(SystemColor.info);
		this.setForeground(SystemColor.inactiveCaptionBorder);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setthis(this);
		this.setLayout(null);
		
		lblCreditCardInfo = new JLabel("All of your Credit Cards");
		lblCreditCardInfo.setBounds(100, 26, 405, 14);
		this.add(lblCreditCardInfo);
		
		lblCreditCardNumber = new JLabel("Card Number:");
		lblCreditCardNumber.setBounds(22, 450, 96, 14);
		this.add(lblCreditCardNumber);
		
		textCC = new JTextField();
		textCC.setBounds(106, 447, 294, 20);
		textCC.setText("Insert 16 Digit number");
		this.add(textCC);
		textCC.setColumns(10);
		
		lblCardHolder = new JLabel("Card Holder:");
		lblCardHolder.setBounds(29, 475, 114, 14);
		this.add(lblCardHolder);
		
		textName = new JTextField();
		textName.setBounds(106, 472, 294, 20);
		textName.setText("Insert Name");
		this.add(textName);
		textName.setColumns(10);
		
		ExpDate = new JLabel("Exp Date:");
		ExpDate.setBounds(39, 500, 83, 14);
		this.add(ExpDate);
		
		textMM = new JTextField();
		textMM.setBounds(106, 497, 105, 20);
		textMM.setText("MM");
		this.add(textMM);
		textMM.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setForeground(SystemColor.text);
		btnAdd.setBackground(SystemColor.activeCaption);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreditCard cc = retFromFields();
				int addressID = ct.getAddressID(getBillingFromTable());
				cc.setAddressID(addressID);
				ct.addCreditCard(cc);
				updateCardTable(cc);
			}
		});
		btnAdd.setBounds(106, 528, 105, 23);
		this.add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreditCard cc = retFromFields();
				CreditCard origcc = getCCFromTable();
				int addressID = ct.getAddressID(getBillingFromTable());
				cc.setAddressID(addressID);
				ct.updateCreditCard(cc,origcc);
			}
		});
		btnUpdate.setForeground(SystemColor.text);
		btnUpdate.setBackground(SystemColor.activeCaption);
		btnUpdate.setBounds(221, 528, 105, 23);
		this.add(btnUpdate);
		
		JLabel lblDoubleClickOn = new JLabel("Add a new credit card:");
		lblDoubleClickOn.setBounds(106, 425, 252, 14);
		this.add(lblDoubleClickOn);
		
		JLabel lblDoubleClickOn_1 = new JLabel("All of your available addresses");
		lblDoubleClickOn_1.setBounds(98, 206, 426, 14);
		this.add(lblDoubleClickOn_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 358, 514, 14);
		this.add(separator);
		
		JButton btnDelete = new JButton("Delete Credit Card");
		btnDelete.setBackground(SystemColor.activeCaption);
		btnDelete.setForeground(SystemColor.text);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ct.deleteCreditCard(getCCFromTable())){
					cardModel.removeRow(cardTable.getSelectedRow());
					textCC.setText(null);
					textName.setText(null);
					textMM.setText(null);
					textYY.setText(null);
				}
				else
					JOptionPane.showMessageDialog(null, "Could not be deleted!", "", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnDelete.setBounds(100, 376, 396, 23);
		this.add(btnDelete);
		
		lblYourCreditCards = new JLabel("Credit Cards:");
		lblYourCreditCards.setBounds(20, 46, 96, 14);
		this.add(lblYourCreditCards);
		
		lblBillingAddress_1 = new JLabel("<Html>Billing<br/>Address:<Html>");
		lblBillingAddress_1.setBounds(35, 231, 83, 37);
		this.add(lblBillingAddress_1);
		
		scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(100, 45, 396, 135);
        this.add(scrollPane_2);
        
        cardTable = new JTable();
        	cardModel = new DefaultTableModel();
	        Object[] ColumnsName1 = new Object[3];     
	        ColumnsName1[0] = "Credit Card No";
	        ColumnsName1[1] = "Expiration Month";
	        ColumnsName1[2] = "Expiration Year";
	        cardModel.setColumnIdentifiers(ColumnsName1);
	       
	        ArrayList<CreditCard> creditCard = ct.getAllCreditCard(USER);
	        Object[] RowData1 = new Object[3];
	        for(int i = 0; i < creditCard.size(); i++){
	        	RowData1[0] = creditCard.get(i).getCredit_card_no();
	        	RowData1[1] = creditCard.get(i).getExpirationMonth();
	        	RowData1[2] = creditCard.get(i).getExpirationYear();
	        	
	            cardModel.addRow(RowData1);
	        }
	       
	        scrollPane_2.setViewportView(cardTable);
	        cardTable.setModel(cardModel);
	        
	        textYY = new JTextField();
	        textYY.setText("YYYY");
	        textYY.setColumns(10);
	        textYY.setBounds(218, 497, 105, 20);
	        this.add(textYY);
	        
	        scrollPane_1 = new JScrollPane();
	        scrollPane_1.setBackground(new Color(255, 255, 255));
	        scrollPane_1.setBounds(98, 229, 398, 100);
	        this.add(scrollPane_1);
	        
	        billingAddress = new JTable();
	        scrollPane_1.setViewportView(billingAddress);
	        
	        JButton btnAddAddress = new JButton("Manage Address");
	        btnAddAddress.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		goToShipping();
	        	}
	        });
	        btnAddAddress.setForeground(SystemColor.text);
	        btnAddAddress.setBackground(SystemColor.activeCaption);
	        btnAddAddress.setBounds(100, 589, 396, 23);
	        this.add(btnAddAddress);
	        
	
     
	billingAddress = new JTable();
     DefaultTableModel billingModel = new DefaultTableModel();
	        
	        Object[] ColumnsName = new Object[4];     
	        ColumnsName[0] = "Street";
	        ColumnsName[1] = "City";
	        ColumnsName[2] = "Zip";
	        ColumnsName[3] = "State";
	        billingModel.setColumnIdentifiers(ColumnsName);
	       
	        ArrayList<Address> address = ct.viewAddresses(USER);
	        Object[] RowData = new Object[4];
	        for(int i = 0; i < address.size(); i++){
	        	RowData[0] = address.get(i).getStreet();
	        	RowData[1] = address.get(i).getCity();
	        	RowData[2] = address.get(i).getZip();
	        	RowData[3] = address.get(i).getState();
	            billingModel.addRow(RowData);
	        }
	       
	        scrollPane_1.setViewportView(billingAddress);
	        billingAddress.setModel(billingModel);
	        
	        JButton btnMenu = new JButton("Menu");
	        btnMenu.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		goToMenu();
	        	}
	        });
	        btnMenu.setForeground(Color.WHITE);
	        btnMenu.setBackground(SystemColor.activeCaption);
	        btnMenu.setBounds(391, 11, 105, 23);
	        add(btnMenu);
	        
	}
	
	
	
	
	
	
	
	public CreditCard getCCFromTable(){
		int index, Em = 0, Ey = 0;
		long CC = 0;
		if(cardTable.getSelectedRow()>=0){
			index = cardTable.getSelectedRow();
			CC = (long) cardTable.getModel().getValueAt(cardTable.convertRowIndexToModel(index), cardTable.convertColumnIndexToModel(0));
			Em = (int) cardTable.getModel().getValueAt(cardTable.convertRowIndexToModel(index), cardTable.convertColumnIndexToModel(1));
			Ey = (int) cardTable.getModel().getValueAt(cardTable.convertRowIndexToModel(index), cardTable.convertColumnIndexToModel(2));
		}
		CreditCard creditCard = new CreditCard(CC,new Customer(USER,0),Em,Ey,0);
				return creditCard;
	}
	
	public CreditCard retFromFields(){
		long CC = Long.parseLong(textCC.getText());
		int Em = Integer.parseInt(textMM.getText()),Ey = Integer.parseInt(textYY.getText());
		return new CreditCard(CC,new Customer(USER,0),Em,Ey);
	}
	
	public Address getBillingFromTable(){
		int index = 0,zip = 0;
		String city = null, street = null, state = null;
		if(billingAddress.getSelectedRow()>=0){
		index = billingAddress.getSelectedRow();
		street = billingAddress.getModel().getValueAt(billingAddress.convertRowIndexToModel(index), billingAddress.convertColumnIndexToModel(0)).toString();
		city = billingAddress.getModel().getValueAt(billingAddress.convertRowIndexToModel(index), billingAddress.convertColumnIndexToModel(1)).toString();
		zip = (int) billingAddress.getModel().getValueAt(billingAddress.convertRowIndexToModel(index), billingAddress.convertColumnIndexToModel(2));
		state = billingAddress.getModel().getValueAt(billingAddress.convertRowIndexToModel(index), billingAddress.convertColumnIndexToModel(3)).toString();
		}
	return new Address(street,city,zip,state);
	}
	
	private void updateCardTable(CreditCard creditCard) {
		Object[] RowData = new Object[3];
		RowData[0] = creditCard.getCredit_card_no();
	    RowData[1] = creditCard.getExpirationMonth();
	    RowData[2] = creditCard.getExpirationYear();
	    cardModel.addRow(RowData);
	    scrollPane_2.setViewportView(cardTable);
	    cardTable.setModel(cardModel);
	    cardTable.invalidate();
	}
	private void goToMenu() {
		this.removeAll();
		JPanel pane = new CustomerMenu();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
	private void goToShipping() {
		this.removeAll();
		JPanel pane = new ShippingInfo();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
}
