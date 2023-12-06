package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.auctions.client.MainProgram;
import es.deusto.ingenieria.sd.auctions.client.MainProgramGUI;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class LoginGUI extends JFrame {

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
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
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
		lblNewLabel_1.setBounds(73, 88, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(73, 132, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		emailField = new JTextField();
		emailField.setBounds(169, 85, 161, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(169, 129, 161, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JComboBox comboAccount = new JComboBox();
		comboAccount.addItem("Google");
		comboAccount.addItem("Facebook");
		comboAccount.setBounds(169, 44, 161, 20);
		contentPane.add(comboAccount);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener(){
		      @Override
		      public void actionPerformed(ActionEvent evt){
		    	  //if there is already a user logged in, logout before re-logging in
		    	  if (MainProgram.loginController.getToken() != -1) {
			    	  MainProgram.loginDialog.logout();
		    	  }
		    	  MainProgram.loginDialog.login(comboAccount.getSelectedItem().toString(), emailField.getText(), passwordField.getText());
		    	  dispose();
		      }

		});
		
		btnNewButton.setBounds(241, 199, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(
				(e) -> {
					this.dispose();
			});
		btnNewButton_1.setBounds(73, 199, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Account");
		lblNewLabel_3.setBounds(73, 44, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		
	}
}