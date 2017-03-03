package com.grocery.UI;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.grocery.controller.usecases.CustomerController;
import com.grocery.controller.usecases.StaffController;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.awt.Font;

public class Login extends JPanel {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private static String USER;
	public boolean Privilege;
	private CustomerController ct = new CustomerController();
	private StaffController st = new StaffController();
	private JTextField txtYourNameHere;
	private JButton RegCustomer;
	private JSeparator separator;
	private JLabel lblStaff;
	private JTextField txtYourNameHereStaff;
	private JLabel lblLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setBackground(SystemColor.info);
		setBounds(100, 100, 550, 725);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		JLabel lblCustomer = new JLabel("Customer:");
		lblCustomer.setBounds(124, 273, 77, 14);
		this.add(lblCustomer);
		
		txtYourNameHere = new JTextField();
		txtYourNameHere.setBounds(198, 270, 237, 20);
		txtYourNameHere.setText("Your name here");
		this.add(txtYourNameHere);
		txtYourNameHere.setColumns(10);
		
		separator = new JSeparator();
		separator.setBounds(137, 363, 295, 14);
		this.add(separator);
		
		lblStaff = new JLabel("Staff:");
		lblStaff.setBounds(124, 395, 115, 14);
		this.add(lblStaff);
		
		txtYourNameHereStaff = new JTextField();
		txtYourNameHereStaff.setBounds(198, 392, 237, 20);
		txtYourNameHereStaff.setText("Your name here");
		this.add(txtYourNameHereStaff);
		txtYourNameHereStaff.setColumns(10);
		
		//Not implemented yet
		JButton LoginCustomer = new JButton("Login");
		LoginCustomer.setForeground(SystemColor.text);
		LoginCustomer.setFont(new Font("Calibri", Font.PLAIN, 14));
		LoginCustomer.setBackground(SystemColor.activeCaption);
		LoginCustomer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
				}
			}
		});
		
		//Customer Login and register
		LoginCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String message;
				 USER = txtYourNameHere.getText();
				 boolean isValid = ct.loginCustomer(USER);
				 
				 if(isValid){
					 message = "Login Successful!";
					 JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
					 goToMenu();					
					 Privilege = false; 
				 }
				 else{
					 message = "Credentials invalid!";
					 JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
				 }
				  
			}
		});
		
		RegCustomer = new JButton("Register");
		RegCustomer.setForeground(SystemColor.text);
		RegCustomer.setFont(new Font("Calibri", Font.PLAIN, 14));
		RegCustomer.setBackground(SystemColor.activeCaption);
		
		RegCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String message;
				 USER = txtYourNameHere.getText();
				 boolean isValid = ct.loginCustomer(USER);
				 
				 if(isValid)
					 message = "Account Already Exists!";
				 else{
					 message = "Account Created";
					 ct.createCustomer(USER);
				 }
				 goToMenu();
				 Privilege = false;
				 JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//Staff Login user and Register
		JButton btnLoginStaff = new JButton("Login");
		btnLoginStaff.setForeground(SystemColor.text);
		btnLoginStaff.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnLoginStaff.setBackground(SystemColor.activeCaption);
		btnLoginStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String message;
				 USER = txtYourNameHereStaff.getText();
				 boolean isValid = st.loginStaff(USER);
				 
				 if(isValid){
					 message = "Login Successful!";
					 Privilege = true;
					 goToStaffMenu();
				 }
				 else
					 message = "Credentials invalid!";
				 
				 JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JButton btnRegStaff = new JButton("Register");
		btnRegStaff.setForeground(SystemColor.text);
		btnRegStaff.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnRegStaff.setBackground(SystemColor.activeCaption);
		btnRegStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String message;
				 USER = txtYourNameHereStaff.getText();
				 boolean isValid = st.loginStaff(USER);
				 
				 if(isValid)
					 message = "Account Already Exists!";
				 else{
					 message = "Staff Account Created";
				 }
				 goToStaffMenu();
				 Privilege = true;
				 JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		LoginCustomer.setBounds(164, 312, 123, 23);
		this.add(LoginCustomer);
		
		RegCustomer.setBounds(297, 312, 116, 23);
		this.add(RegCustomer);
		
		btnRegStaff.setBounds(289, 447, 123, 23);
		this.add(btnRegStaff);
			
		btnLoginStaff.setBounds(164, 447, 116, 23);
		this.add(btnLoginStaff);
		
		lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblLogin.setBounds(255, 192, 77, 14);
		add(lblLogin);
	}
	
	private void goToMenu() {
		this.removeAll();
		JPanel pane = new CustomerMenu();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
	private void goToStaffMenu() {
		this.removeAll();
		JPanel pane = new StaffMenu();
		this.add(pane);
		pane.setVisible(true);
		pane.setLocation(0,0);
	}
	
	public static String getUSER(){
		return USER;
	}
}
