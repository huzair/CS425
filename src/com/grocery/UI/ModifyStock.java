package com.grocery.UI;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import com.grocery.controller.usecases.CustomerController;
import com.grocery.controller.usecases.StaffController;
import com.grocery.model.Address;
import com.grocery.model.Stock;
import com.grocery.model.Warehouse;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Font;

public class ModifyStock extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -933335764828704791L;
	private StaffController st = new StaffController();
	private CustomerController ct = new CustomerController();
	private JTextField textProduct;
	private JTextField textQuantity;
	private JTable tableWarehouse;
	private JTable tableStock;
	DefaultTableModel warehouseModel;
	DefaultTableModel stockModel;
	JScrollPane scrollPane;
	JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyStock frame = new ModifyStock();
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
	public ModifyStock() {
		setBounds(100, 100, 550, 700);
		this.setBackground(SystemColor.info);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		JLabel lblStock = new JLabel("Left click to select a product, type in to add");
		lblStock.setBounds(93, 30, 393, 14);
		this.add(lblStock);

		JLabel lblAddANew = new JLabel("Add a new stock information:");
		lblAddANew.setBounds(93, 472, 217, 14);
		this.add(lblAddANew);
		
		JLabel lblProduct = new JLabel("Product:");
		lblProduct.setBounds(30, 499, 151, 14);
		this.add(lblProduct);
		
		textProduct = new JTextField();
		textProduct.setText("Product's name");
		textProduct.setColumns(10);
		textProduct.setBounds(95, 496, 294, 20);
		this.add(textProduct);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(30, 527, 116, 14);
		this.add(lblQuantity);
		
		textQuantity = new JTextField();
		textQuantity.setText("Enter an integer");
		textQuantity.setColumns(10);
		textQuantity.setBounds(93, 524, 294, 20);
		this.add(textQuantity);
		
		JButton btnAddupdate = new JButton("Add/Update");
		btnAddupdate.setBackground(SystemColor.activeCaption);
		btnAddupdate.setForeground(SystemColor.text);
		btnAddupdate.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAddupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stock stock = retFromFields();
				Warehouse warehouse = new Warehouse(0,ct.getAddressID(getAddressFromTable()));
				st.addStock(stock, warehouse);
				JOptionPane.showMessageDialog(null, "Stock added/updated", "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAddupdate.setBounds(93, 571, 413, 23);
		this.add(btnAddupdate);
		
		JLabel lblStockInfo = new JLabel("Stock Info:");
		lblStockInfo.setBounds(20, 60, 151, 14);
		this.add(lblStockInfo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(93, 55, 413, 250);
		this.add(scrollPane);
		
		tableStock = new JTable();
		scrollPane.setViewportView(tableStock);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(93, 326, 413, 113);
		this.add(scrollPane_1);
		
		tableWarehouse = new JTable();
		scrollPane_1.setViewportView(tableWarehouse);
		
		JLabel lblWarehouses = new JLabel("Warehouses: ");
		lblWarehouses.setBounds(20, 334, 151, 14);
		this.add(lblWarehouses);
		
		
		warehouseModel = new DefaultTableModel();
        
        Object[] ColumnsName = new Object[5];     
        ColumnsName[0] = "Street";
        ColumnsName[1] = "City";
        ColumnsName[2] = "Zip";
        ColumnsName[3] = "State";
        ColumnsName[4] = "Warehouse ID";
        warehouseModel.setColumnIdentifiers(ColumnsName);
       
        ArrayList<Address> warehouse = st.getAllWarehouses();
        Object[] RowData = new Object[5];
        for(int i = 0; i < warehouse.size(); i++){
        	RowData[0] = warehouse.get(i).getStreet();
        	RowData[1] = warehouse.get(i).getCity();
        	RowData[2] = warehouse.get(i).getZip();
        	RowData[3] = warehouse.get(i).getState();
        	RowData[4] = warehouse.get(i).getAddressID();
            warehouseModel.addRow(RowData);
        }
       
        scrollPane_1.setViewportView(tableWarehouse);
        tableWarehouse.setModel(warehouseModel);
        
        
        
        stockModel = new DefaultTableModel();
        
        Object[] StockColumnsName = new Object[3];     
        StockColumnsName[0] = "Product";
        StockColumnsName[1] = "Quantity";
        StockColumnsName[2] = "Warehouse ID";
        stockModel.setColumnIdentifiers(StockColumnsName);
       
        ArrayList<Stock> stock = st.getAllStock();
        Object[] StockRowData = new Object[3];
        for(int i = 0; i < stock.size(); i++){
        	StockRowData[0] = stock.get(i).getProductName();
        	StockRowData[1] = stock.get(i).getQuantity();
        	StockRowData[2] = stock.get(i).getAddressID();
            stockModel.addRow(StockRowData);
        }
       
        scrollPane.setViewportView(tableStock);
        tableStock.setModel(stockModel);
        
        JButton btnMenu = new JButton("Menu");
        btnMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		goToMenu();
        	}
        });
        btnMenu.setBackground(SystemColor.activeCaption);
        btnMenu.setForeground(SystemColor.text);
        btnMenu.setFont(new Font("Calibri", Font.PLAIN, 14));
        btnMenu.setBounds(369, 649, 137, 23);
        add(btnMenu);    	
	}
	
	public Stock retFromFields(){
		String productName = textProduct.getText() ;
		int quantity = Integer.parseInt(textQuantity.getText());
		return new Stock(productName,quantity);
	}
	
	public Address getAddressFromTable(){
		int index = 0,zip = 0;
		String city = null, street = null, state = null;
		if(tableWarehouse.getSelectedRow()>=0){
		index = tableWarehouse.getSelectedRow();
		street = tableWarehouse.getModel().getValueAt(tableWarehouse.convertRowIndexToModel(index), tableWarehouse.convertColumnIndexToModel(0)).toString();
		city = tableWarehouse.getModel().getValueAt(tableWarehouse.convertRowIndexToModel(index), tableWarehouse.convertColumnIndexToModel(1)).toString();
		zip = (int) tableWarehouse.getModel().getValueAt(tableWarehouse.convertRowIndexToModel(index), tableWarehouse.convertColumnIndexToModel(2));
		state = tableWarehouse.getModel().getValueAt(tableWarehouse.convertRowIndexToModel(index), tableWarehouse.convertColumnIndexToModel(3)).toString();
		}
	return new Address(street,city,zip,state);
	}
	
	private void goToMenu(){
		this.removeAll();
		JPanel pane = new StaffMenu();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
}
