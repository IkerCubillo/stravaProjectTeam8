package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.auctions.client.MainProgram;
import es.deusto.ingenieria.sd.auctions.client.MainProgramGUI;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
import es.deusto.ingenieria.sd.auctions.client.controller.MainController;
import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;

public class ActivityTimeChallengeGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField startField;
	private JTextField endField;
	private JTextField timeField;

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
public ActivityTimeChallengeGUI() {
		
		//Configuracion de la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
		setSize(750, 500);
		setLocationRelativeTo(null);
        getContentPane().setLayout(null);
		
		//Fondo Blanco
        JLabel labelBlanco = new JLabel("");
        JLabel labelFondo = new JLabel("");
        labelBlanco.setIcon(new ImageIcon(DistanceChallengeGUI.class.getResource("/images/Blanco.PNG")));
        labelFondo.setIcon(new ImageIcon(DistanceChallengeGUI.class.getResource("/images/imagenCiclista.jpg")));
        
        //Labels
		JLabel lblNewLabel = new JLabel("Activity Time Challenge");
		JLabel lblNewLabel_1 = new JLabel("Name");
		JLabel lblNewLabel_2 = new JLabel("Start Date (dd-MM-yyyy)");
		JLabel lblNewLabel_3 = new JLabel("End Date (dd-MM-yyyy)");
		JLabel lblNewLabel_4 = new JLabel("Sport type");
		JLabel lblNewLabel_5 = new JLabel("Activity Time (minutes)");

        
        //TextFields
		nameField = new JTextField();
		startField = new JTextField();
		endField = new JTextField();
		timeField = new JTextField();

        
        //ComboBox
		JComboBox comboSport = new JComboBox();
		comboSport.setModel(new DefaultComboBoxModel(new String[] {"Running", "Cycling", "Both"}));
		comboSport.setMaximumRowCount(3);
        
        //Botones
		JButton btnNewButton = new JButton("Set up");
		JButton btnNewButton_1 = new JButton("Back");

        //setBounds
		labelFondo.setBounds(0, 0, getWidth(), getHeight());
        labelBlanco.setBounds(190, 66, 382, 316);
        labelFondo.setBounds(0, 0, 734, 461);
		lblNewLabel.setBounds(357, 88, 131, 14);
		lblNewLabel_1.setBounds(230, 118, 150, 14);
		lblNewLabel_2.setBounds(230, 200, 150, 14);
		lblNewLabel_3.setBounds(230, 241, 150, 14);
		lblNewLabel_4.setBounds(230, 282, 150, 14);
		lblNewLabel_5.setBounds(230, 159, 150, 14);
		nameField.setBounds(377, 118, 160, 20);
		timeField.setBounds(377, 159, 160, 20);
		startField.setBounds(377, 241, 160, 20);
		endField.setBounds(377, 159, 160, 20);
		comboSport.setBounds(377, 282, 160, 20);
		btnNewButton.setBounds(440, 317, 89, 23);
		btnNewButton_1.setBounds(275, 317, 89, 23);

		//Listeners
		btnNewButton.addActionListener(new ActionListener(){
		      @Override
		      public void actionPerformed(ActionEvent evt){
		    	  String activityTime = timeField.getText();
					float time = Float.parseFloat(activityTime);
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
					MainProgram.mainWindow.setupActivityTimeChallenge(MainProgram.loginController.getToken(), nameField.getText(), 
							st, en, time, comboSport.getSelectedItem().toString());
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
		
		contentPane.add(labelBlanco);
		contentPane.add(labelFondo);
				
		contentPane.add(nameField);
		nameField.setColumns(10);
		contentPane.add(startField);
		startField.setColumns(10);
		contentPane.add(endField);
		endField.setColumns(10);
				
		contentPane.add(comboSport);
		contentPane.add(btnNewButton_1);
		
		contentPane.add(btnNewButton);
		contentPane.add(lblNewLabel_5);
		
		contentPane.add(timeField);
		timeField.setColumns(10);
		
		contentPane.setComponentZOrder(nameField, 0);
		contentPane.setComponentZOrder(timeField, 0);
		contentPane.setComponentZOrder(startField, 0);
		contentPane.setComponentZOrder(endField, 0);
		contentPane.setComponentZOrder(comboSport, 0);
		contentPane.setComponentZOrder(btnNewButton, 0);
		contentPane.setComponentZOrder(lblNewLabel_5, 0);
		contentPane.setComponentZOrder(btnNewButton_1, 0);
		
		btnNewButton_1.addActionListener(
				(e) -> {
					this.dispose();
			});


	}

}