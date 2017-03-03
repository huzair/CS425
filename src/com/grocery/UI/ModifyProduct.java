package com.grocery.UI;

import java.awt.EventQueue;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.grocery.controller.usecases.StaffController;
import com.grocery.model.Product;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;

public class ModifyProduct extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5720559235307690507L;
	private JTextField textProduct;
	private JTextField textCategory;
	private JTextField textType;
	private JTextField textSize;
	private JTextField textImage;
	private DefaultTableModel model;
	private JTable tableStock;
	private JScrollPane scrollPane;
	private StaffController sc = new StaffController();
	private JTable table;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyProduct frame = new ModifyProduct();
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
	public ModifyProduct() {
		setBackground(SystemColor.info);
		setBounds(100, 100, 550, 700);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		JLabel lblProductInfo = new JLabel("Product Info:");
		lblProductInfo.setBounds(10, 36, 151, 14);
		this.add(lblProductInfo);
		
		JLabel label_1 = new JLabel("Choose a product to modify.");
		label_1.setBounds(85, 11, 250, 14);
		this.add(label_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 36, 414, 269);
		this.add(scrollPane);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(SystemColor.text);
		btnUpdate.setBackground(SystemColor.activeCaption);
		btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product product = getProductFromTable();
				boolean isUpdate = sc.updateProduct(product);
				if(isUpdate){
					model.removeRow(tableStock.getSelectedRow());
					updateTable(retFromFields());
					JOptionPane.showMessageDialog(null, "Product Updated", "", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Update Failed", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnUpdate.setBounds(85, 582, 192, 23);
		this.add(btnUpdate);
		
		JButton button_1 = new JButton("Delete");
		button_1.setForeground(SystemColor.text);
		button_1.setBackground(SystemColor.activeCaption);
		button_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isDelete = sc.deleteProduct(getProductFromTable().getName());
				if(isDelete){
					model.removeRow(tableStock.getSelectedRow());
				}
				else{
					 JOptionPane.showMessageDialog(null, "Delete Failed", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		button_1.setBounds(85, 331, 414, 23);
		this.add(button_1);
		
		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		this.add(table);
		
		JLabel label_2 = new JLabel("Add a new price information:");
		label_2.setBounds(83, 389, 217, 14);
		this.add(label_2);
		
		JLabel label_3 = new JLabel("Product:");
		label_3.setBounds(29, 417, 151, 14);
		this.add(label_3);
		
		textProduct = new JTextField();
		textProduct.setText("Product's name");
		textProduct.setColumns(10);
		textProduct.setBounds(82, 414, 195, 20);
		this.add(textProduct);
		
		JLabel label_4 = new JLabel("Category:");
		label_4.setBounds(20, 442, 129, 14);
		this.add(label_4);
		
		textCategory = new JTextField();
		textCategory.setText("for example: food");
		textCategory.setColumns(10);
		textCategory.setBounds(82, 439, 195, 20);
		this.add(textCategory);
		
		JLabel label_5 = new JLabel("Type:");
		label_5.setBounds(41, 466, 116, 14);
		this.add(label_5);
		
		textType = new JTextField();
		textType.setText("for example: fruit");
		textType.setColumns(10);
		textType.setBounds(82, 464, 195, 20);
		this.add(textType);
		
		JLabel label_6 = new JLabel("Size:");
		label_6.setBounds(45, 493, 116, 14);
		this.add(label_6);
		
		textSize = new JTextField();
		textSize.setText("for example: 5.0");
		textSize.setColumns(10);
		textSize.setBounds(82, 491, 195, 20);
		this.add(textSize);
		
		JLabel label_7 = new JLabel("Image Link:");
		label_7.setBounds(13, 520, 116, 14);
		this.add(label_7);
		
		textImage = new JTextField();
		textImage.setText("http://");
		textImage.setColumns(10);
		textImage.setBounds(82, 517, 195, 20);
		this.add(textImage);
		
		JButton button_2 = new JButton("Add");
		button_2.setForeground(SystemColor.text);
		button_2.setBackground(SystemColor.activeCaption);
		button_2.setFont(new Font("Calibri", Font.PLAIN, 14));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product product = retFromFields();
				 boolean isAdd = sc.addProduct(product);
				 if(isAdd){
					 updateTable(retFromFields());
					 JOptionPane.showMessageDialog(null, "Product Added", "", JOptionPane.INFORMATION_MESSAGE);
				 }
				 else{
					 JOptionPane.showMessageDialog(null, "Add Failed", "", JOptionPane.INFORMATION_MESSAGE);
				 }
			}
		});
		
		// -------------- Set Fields to selected table item ------------------
				
		button_2.setBounds(85, 548, 192, 23);
		this.add(button_2);
		
		tableStock = new JTable();
		tableStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(getProductFromTable()!=null){
					textProduct.setText(getProductFromTable().getName());
					textCategory.setText(getProductFromTable().getCategory());
					textType.setText(getProductFromTable().getType());
					textSize.setText(Double.toString(getProductFromTable().getSize()));
					textImage.setText("");
					
					try{
					
						ArrayList<Product> products = sc.getAllProduct();
						int index = tableStock.getSelectedRow();
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
					}
			
		});
		
        model = new DefaultTableModel();
        
        Object[] columnsName = new Object[4];     
        columnsName[0] = "Name";
        columnsName[1] = "Category";
        columnsName[2] = "Type";
        columnsName[3] = "Size";      
        model.setColumnIdentifiers(columnsName);
        
        Object[] rowData = new Object[5];

        ArrayList<Product> products = sc.getAllProduct();
        for(int i = 0; i < products.size(); i++){
            rowData[0] = products.get(i).getName();
            rowData[1] = products.get(i).getCategory();
            rowData[2] = products.get(i).getType();
            rowData[3] = products.get(i).getSize();
            model.addRow(rowData);
        }
        scrollPane.setViewportView(tableStock);
        tableStock.setModel(model);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(307, 417, 192, 188);
        this.add(lblNewLabel);
        
        JLabel lblImage = new JLabel("Image:");
        lblImage.setBounds(376, 389, 46, 14);
        this.add(lblImage);
        
        JButton btnMenu = new JButton("Menu");
        btnMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		goToMenu();
        	}
        });
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFont(new Font("Calibri", Font.PLAIN, 14));
        btnMenu.setBackground(SystemColor.activeCaption);
        btnMenu.setBounds(85, 616, 192, 23);
        add(btnMenu);
        
	}
	
	public Product getProductFromTable(){
		int index = 0;
		String name = null,category = null,type = null,image = null;
		double size = 0;
		if(tableStock.getSelectedRow()>=0){
		index = tableStock.getSelectedRow();
		name = tableStock.getModel().getValueAt(tableStock.convertRowIndexToModel(index), tableStock.convertColumnIndexToModel(0)).toString();
		category = tableStock.getModel().getValueAt(tableStock.convertRowIndexToModel(index), tableStock.convertColumnIndexToModel(1)).toString();
		type = tableStock.getModel().getValueAt(tableStock.convertRowIndexToModel(index), tableStock.convertColumnIndexToModel(2)).toString();
		size =  (Double) tableStock.getModel().getValueAt(tableStock.convertRowIndexToModel(index), tableStock.convertColumnIndexToModel(3));
		}
		return new Product(name,category,type,size,image);
	}
	
	public Product retFromFields(){
		String name = textProduct.getText();
		String category = textCategory.getText();
		String type = textType.getText();
		double size = Double.parseDouble(textSize.getText());
		String image = textImage.getText();
		return new Product(name,category,type,size,image);
	}
	
	private void updateTable(Product product) {
		Object[] RowData = new Object[5];
		RowData[0] = product.getName();
	    RowData[1] = product.getCategory();
	    RowData[2] = product.getType();
	    RowData[3] = product.getSize();
	    RowData[4] = product.getImage();
	    model.addRow(RowData);
	    scrollPane.setViewportView(tableStock);
	    tableStock.setModel(model);
	    tableStock.invalidate();
	}
	private void goToMenu() {
		this.removeAll();
		JPanel pane = new StaffMenu();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
}
