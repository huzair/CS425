package com.grocery.UI;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class StaffMenu extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean Privilege;
	private JSeparator separator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffMenu frame = new StaffMenu();
					frame.setVisible(true);
					//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					
					//frame.setLocation((1366/2)-(frame.getWidth())/2, (768/2)-(frame.getHeight()/2));
					//frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getHeight()/2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StaffMenu() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 725);
		//this = new JPanel();
		this.setBackground(SystemColor.inactiveCaptionBorder);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setthis(this);
		this.setLayout(null);
		
		separator = new JSeparator();
		separator.setBounds(218, 335, 2, 81);
		separator.setOrientation(SwingConstants.VERTICAL);
		this.add(separator);
		
		//Customer Login and register
		JButton btnProducts = new JButton("<html>Modify<br/>Stock</html>");
		btnProducts.setForeground(Color.WHITE);
		btnProducts.setBackground(SystemColor.activeCaption);
		btnProducts.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goToStock();
			}
		});
		
		btnProducts.setBounds(106, 335, 92, 81);
		this.add(btnProducts);
		
		JButton btnShipping = new JButton("<html>Add/Modify<br/>Products</html>");
		btnShipping.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnShipping.setForeground(SystemColor.text);
		btnShipping.setBackground(SystemColor.activeCaption);
		btnShipping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goToProducts();
			}
		});
		btnShipping.setBounds(239, 335, 92, 81);
		this.add(btnShipping);
		
		JButton btnCC = new JButton("<html>Modify<br/>Price</html>");
		btnCC.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnCC.setForeground(SystemColor.text);
		btnCC.setBackground(SystemColor.activeCaption);
		btnCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPrice();
			}

		});
		btnCC.setBounds(371, 335, 92, 81);
		this.add(btnCC);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(351, 335, 2, 81);
		this.add(separator_1);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblMenu.setBounds(271, 304, 46, 14);
		add(lblMenu);
		
		JLabel label = new JLabel("Sign Out");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToLogin();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}	
		});
		label.setForeground(SystemColor.textHighlight);
		label.setFont(new Font("Calibri", Font.PLAIN, 14));
		label.setBounds(406, 303, 57, 14);
		add(label);
	}
	
	private void goToLogin() {
		this.removeAll();
		JPanel pane = new Login();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
	private void goToStock(){
		this.removeAll();
		JPanel pane = new ModifyStock();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
	private void goToProducts(){
		this.removeAll();
		JPanel pane = new ModifyProduct();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
	private void goToPrice(){
		this.removeAll();
		JPanel pane = new ModifyPrice();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
}
