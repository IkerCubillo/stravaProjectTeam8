package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.auctions.client.MainProgramWindow;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginWindow2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JTextField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow2 frame = new LoginWindow2();
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
	public LoginWindow2() {
		ServiceLocator serviceLocator = new ServiceLocator();
		LoginController loginController = new LoginController(serviceLocator);
		LoginWindow loginDialog = new LoginWindow(loginController);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(200, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(73, 41, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(73, 90, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		emailField = new JTextField();
		emailField.setBounds(169, 41, 161, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(169, 90, 161, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(
				(e) -> {
					loginDialog.login(emailField.getText(), passwordField.getText());
					this.dispose();
			});
		btnNewButton.setBounds(242, 146, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(
				(e) -> {
					this.dispose();
			});
		btnNewButton_1.setBounds(73, 146, 89, 23);
		contentPane.add(btnNewButton_1);
	}

}
