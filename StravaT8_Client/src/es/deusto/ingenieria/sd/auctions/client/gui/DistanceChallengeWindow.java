package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.auctions.client.MainProgram;
import es.deusto.ingenieria.sd.auctions.client.MainProgramWindow;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
import es.deusto.ingenieria.sd.auctions.client.controller.MainController;
import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class DistanceChallengeWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField startField;
	private JTextField endField;
	private JTextField distanceField;
	private MainProgramWindow mpw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DistanceChallengeWindow frame = new DistanceChallengeWindow();
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
public DistanceChallengeWindow() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Distance Challenge");
		lblNewLabel.setBounds(157, 11, 131, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(47, 41, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Start");
		lblNewLabel_2.setBounds(47, 123, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("End");
		lblNewLabel_3.setBounds(47, 164, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sport type");
		lblNewLabel_4.setBounds(47, 205, 57, 14);
		contentPane.add(lblNewLabel_4);
		
		nameField = new JTextField();
		nameField.setBounds(177, 41, 160, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		startField = new JTextField();
		startField.setBounds(177, 123, 160, 20);
		contentPane.add(startField);
		startField.setColumns(10);
		
		endField = new JTextField();
		endField.setBounds(178, 164, 160, 20);
		contentPane.add(endField);
		endField.setColumns(10);
		
		JComboBox comboSport = new JComboBox();
		comboSport.setModel(new DefaultComboBoxModel(new String[] {"Running", "Cycling", "Both"}));
		comboSport.setMaximumRowCount(3);
		comboSport.setBounds(177, 205, 160, 20);
		contentPane.add(comboSport);
		
		JButton btnNewButton = new JButton("Set up");
		btnNewButton.setBounds(250, 240, 89, 23);
		btnNewButton.addActionListener(
				(e) -> {
					String distance = distanceField.getText();
					float dist = Float.parseFloat(distance);
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					String start = startField.getText();
					String end = endField.getText();
					Date st = null;
					try {
						st = formatter.parse(start);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Date en = null;
					try {
						en = formatter.parse(end);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					MainProgram.mainWindow.setupDistanceChallenge(MainProgram.loginController.getToken(), nameField.getText(), 
							st, en, dist, comboSport.getSelectedItem().toString());
					this.dispose();
					mpw.setVisible(true);
			});
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("Distance");
		lblNewLabel_5.setBounds(47, 82, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		distanceField = new JTextField();
		distanceField.setBounds(177, 82, 160, 20);
		contentPane.add(distanceField);
		distanceField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(
				(e) -> {
					this.dispose();
					mpw.setVisible(true);
			});
		btnNewButton_1.setBounds(85, 240, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
