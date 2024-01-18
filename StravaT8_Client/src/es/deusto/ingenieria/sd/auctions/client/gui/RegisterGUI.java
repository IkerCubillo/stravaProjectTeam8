package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.auctions.client.MainProgram;
import es.deusto.ingenieria.sd.auctions.client.MainProgramGUI;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JTextField nameField;
	private JTextField birthdateField;
	private JTextField weightField;
	private JTextField heightField;
	private JTextField mbpmField;
	private JTextField bpmField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
public RegisterGUI() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration");
		lblNewLabel.setBounds(174, 11, 74, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Account");
		lblNewLabel_1.setBounds(38, 40, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(38, 60, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setBounds(38, 80, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Birthdate");
		lblNewLabel_4.setBounds(38, 100, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Weight");
		lblNewLabel_5.setBounds(38, 120, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Height");
		lblNewLabel_6.setBounds(38, 140, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("mBPM");
		lblNewLabel_7.setBounds(38, 160, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("BPM");
		lblNewLabel_8.setBounds(38, 180, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		JComboBox comboAccount = new JComboBox();
		comboAccount.addItem("Google");
		comboAccount.addItem("Facebook");
		comboAccount.setMaximumRowCount(2);
		comboAccount.setBounds(174, 36, 185, 22);
		contentPane.add(comboAccount);
		
		emailField = new JTextField();
		emailField.setBounds(174, 60, 185, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(174, 80, 185, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		birthdateField = new JTextField();
		birthdateField.setBounds(174, 100, 185, 20);
		contentPane.add(birthdateField);
		birthdateField.setColumns(10);
		
		weightField = new JTextField();
		weightField.setBounds(174, 120, 185, 20);
		contentPane.add(weightField);
		weightField.setColumns(10);
		
		heightField = new JTextField();
		heightField.setBounds(174, 140, 185, 20);
		contentPane.add(heightField);
		heightField.setColumns(10);
		
		mbpmField = new JTextField();
		mbpmField.setBounds(174, 160, 185, 20);
		contentPane.add(mbpmField);
		mbpmField.setColumns(10);
		
		bpmField = new JTextField();
		bpmField.setBounds(174, 180, 185, 20);
		contentPane.add(bpmField);
		bpmField.setColumns(10);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener(){
		      @Override
		      public void actionPerformed(ActionEvent evt){
		    	  String weight = weightField.getText();
		    	  float w = Float.parseFloat(weight);
		    	  String height = heightField.getText();
		    	  float h = Float.parseFloat(height);
		    	  String mBPM = mbpmField.getText();
		    	  int m = Integer.parseInt(mBPM);
		    	  String BPM = bpmField.getText();
		    	  int b = Integer.parseInt(BPM);
		    	  SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		    	  String birthdate = birthdateField.getText();
		    	  Date bd = null;
					try {
						bd = formatter.parse(birthdate);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					MainProgram.loginDialog.register(comboAccount.getSelectedItem().toString(), emailField.getText(), nameField.getText(), 
							bd, w, h, m, b);
					dispose();
		      }

		});
		
		btnNewButton.setBounds(270, 211, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(
				(e) -> {
					this.dispose();
			});
		btnNewButton_1.setBounds(85, 211, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
