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


public class CustomerMenu extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSeparator separator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerMenu frame = new CustomerMenu();
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
	public CustomerMenu() {
		setBounds(100, 100, 550, 725);
		this.setBackground(SystemColor.inactiveCaptionBorder);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		separator = new JSeparator();
		separator.setBounds(218, 335, 2, 81);
		separator.setOrientation(SwingConstants.VERTICAL);
		this.add(separator);
		
		//Customer Login and register
		JButton btnProducts = new JButton("<html>Browse<br/>Products</html>");
		btnProducts.setForeground(Color.WHITE);
		btnProducts.setBackground(SystemColor.activeCaption);
		btnProducts.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goToShopping();
			}
		});
		
		btnProducts.setBounds(106, 335, 92, 81);
		this.add(btnProducts);
		
		JButton btnShipping = new JButton("<html>Manage<br/>Shipping<br/>Address</html>");
		btnShipping.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnShipping.setForeground(SystemColor.text);
		btnShipping.setBackground(SystemColor.activeCaption);
		btnShipping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goToShipping();
			}
		});
		btnShipping.setBounds(239, 335, 92, 81);
		this.add(btnShipping);
		
		JButton btnCC = new JButton("<html> Manage<br/>Credit Card</html>");
		btnCC.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnCC.setForeground(SystemColor.text);
		btnCC.setBackground(SystemColor.activeCaption);
		btnCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToCreditCards();
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
	private void goToShopping(){
		this.removeAll();
		JPanel pane = new ShoppingCart();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
	private void goToShipping(){
		this.removeAll();
		JPanel pane = new ShippingInfo();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
	private void goToCreditCards(){
		this.removeAll();
		JPanel pane = new CreditCardInfo();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
}
