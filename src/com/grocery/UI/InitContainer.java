package com.grocery.UI;
//import java.awt.Dimension;
import java.awt.EventQueue;
//import java.awt.Toolkit;
import javax.swing.JFrame;

public class InitContainer extends JFrame {

	 /**
	 * 
	 */
	
	private static final long serialVersionUID = -8624608687253976767L;
	Login login = new Login();
	 CustomerMenu menu = new CustomerMenu();

	/**
	 * Launch the application.
	 */
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitContainer frame = new InitContainer();
					//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation((1366/2)-(frame.getWidth())/2, (768/2)-(frame.getHeight()/2));
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
	public InitContainer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 725);
		this.add(login);
		login.setVisible(true);
		login.setLocation(0, 0);
	}
}
