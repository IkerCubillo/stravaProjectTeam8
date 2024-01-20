package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.auctions.client.MainProgram;
import es.deusto.ingenieria.sd.auctions.client.MainProgramGUI;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
import es.deusto.ingenieria.sd.auctions.client.controller.MainController;
import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DistanceChallengeGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField startField;
	private JTextField endField;
	private JTextField distanceField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DistanceChallengeGUI frame = new DistanceChallengeGUI();
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
public DistanceChallengeGUI() {
		
		//Configuracion de la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(750, 500);
		setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        //Fondo blanco
        JLabel labelBlanco = new JLabel("");
        JLabel labelFondo = new JLabel("");
        labelBlanco.setIcon(new ImageIcon(DistanceChallengeGUI.class.getResource("/images/Blanco.PNG")));
        labelFondo.setIcon(new ImageIcon(DistanceChallengeGUI.class.getResource("/images/como-medir-distancias-en-google-maps-1200x675.jpg")));
        
        //Labels
		JLabel lblNewLabel = new JLabel("Distance Challenge");
		JLabel lblNewLabel_1 = new JLabel("Name");
		JLabel lblNewLabel_2 = new JLabel("Start Date (dd-MM-yyyy)");
		JLabel lblNewLabel_3 = new JLabel("End Date (dd-MM-yyyy)");
		JLabel lblNewLabel_4 = new JLabel("Sport type");
		JLabel lblNewLabel_5 = new JLabel("Distance (m)");
		
		
		//TextFields
		nameField = new JTextField();
		startField = new JTextField();
		endField = new JTextField();
		distanceField = new JTextField();

		//ComboBox
		JComboBox comboSport = new JComboBox();
		comboSport.setModel(new DefaultComboBoxModel(new String[] {"Running", "Cycling", "Both"}));
		comboSport.setMaximumRowCount(3);
		
		//Botones
		JButton btnNewButton = new JButton("Set up");
		JButton botonBack = new JButton("Back");
		
        //setBounds
		labelFondo.setBounds(0, 0, getWidth(), getHeight());
        labelBlanco.setBounds(190, 66, 382, 316);
        labelFondo.setBounds(0, 0, 734, 461);
		lblNewLabel.setBounds(357, 88, 131, 14);
		lblNewLabel_1.setBounds(230, 118, 150, 14);
		lblNewLabel_2.setBounds(230, 200, 150, 14);
		lblNewLabel_3.setBounds(230, 241, 150, 14);
		lblNewLabel_4.setBounds(230, 282, 157, 14);
		lblNewLabel_5.setBounds(230, 159, 157, 14);
		nameField.setBounds(377, 118, 140, 20);
		startField.setBounds(377, 200, 140, 20);
		endField.setBounds(377, 241, 140, 20);
		distanceField.setBounds(377, 159, 140, 20);
		btnNewButton.setBounds(440, 317, 89, 23);
		botonBack.setBounds(275, 317, 89, 23);
		comboSport.setBounds(377, 282, 160, 20);

		//Listeners
		btnNewButton.addActionListener(new ActionListener(){
		      @Override
		      public void actionPerformed(ActionEvent evt){
		    	  String distance = distanceField.getText();
					float dist = Float.parseFloat(distance);
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy, MM, dd");
					String start = startField.getText();
					String end = endField.getText();
					java.util.Date date = null;
					try {
						date = sdf1.parse(start);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
					java.util.Date date1 = null;
					try {
						date1 = sdf1.parse(end);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					java.sql.Date sqlEndDate = new java.sql.Date(date1.getTime());
					MainProgram.mainWindow.setupDistanceChallenge(MainProgram.loginController.getToken(), nameField.getText(), 
							sqlStartDate, sqlEndDate, dist, comboSport.getSelectedItem().toString());
					dispose();
		      }

		});
		

		//Anyadir al contentPane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel_2);
		contentPane.add(lblNewLabel_3);
		contentPane.add(lblNewLabel_4);
				
		contentPane.add(nameField);
		nameField.setColumns(10);
		contentPane.add(startField);
		startField.setColumns(10);
		contentPane.add(endField);
		endField.setColumns(10);
				
		contentPane.add(comboSport);
		contentPane.add(lblNewLabel_5);
				
		contentPane.add(botonBack);
		contentPane.add(btnNewButton);
				
		contentPane.add(labelBlanco);
		contentPane.add(labelFondo);
		
		contentPane.add(distanceField);
		distanceField.setColumns(10);
		
		contentPane.setComponentZOrder(distanceField, 0);

		botonBack.addActionListener(
				(e) -> {
					this.dispose();
			});


	}
}
