package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.auctions.client.MainProgram;
import es.deusto.ingenieria.sd.auctions.client.MainProgramWindow;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
import es.deusto.ingenieria.sd.auctions.client.controller.MainController;
import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;

import javax.swing.JButton;

public class SessionWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField titleField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
public SessionWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Distance Challenge");
		lblNewLabel.setBounds(157, 11, 131, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setBounds(47, 35, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sport");
		lblNewLabel_2.setBounds(47, 76, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel distanceField = new JLabel("Distance");
		distanceField.setBounds(47, 117, 46, 14);
		contentPane.add(distanceField);
		
		JLabel startDateField = new JLabel("Start date");
		startDateField.setBounds(47, 158, 57, 14);
		contentPane.add(startDateField);
		
		titleField = new JTextField();
		titleField.setBounds(177, 35, 160, 20);
		contentPane.add(titleField);
		titleField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(177, 158, 160, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(178, 117, 160, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JComboBox comboSport = new JComboBox();
		comboSport.setModel(new DefaultComboBoxModel(new String[] {"Running", "Cycling"}));
		comboSport.setMaximumRowCount(3);
		comboSport.setBounds(177, 76, 160, 20);
		contentPane.add(comboSport);
		
		JLabel startTimeField = new JLabel("Start time");
		startTimeField.setBounds(47, 199, 57, 14);
		contentPane.add(startTimeField);
		
		JLabel durationField = new JLabel("Duration");
		durationField.setBounds(47, 240, 46, 14);
		contentPane.add(durationField);
		
		textField_3 = new JTextField();
		textField_3.setBounds(177, 199, 160, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(177, 240, 160, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener(){
		      @Override
		      public void actionPerformed(ActionEvent evt){
		    	  String distance = textField_2.getText();
					float dist = Float.parseFloat(distance);
					String duration = textField_4.getText();
					float dur = Float.parseFloat(duration);
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					String startDate = textField_1.getText();
					String startTime = textField_3.getText();
					Date st = null;
					try {
						st = formatter.parse(startDate);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					LocalTime stt = null;
					stt = LocalTime.parse(startTime);
					MainProgram.mainWindow.createSession(MainProgram.loginController.getToken(), titleField.getText(), 
							comboSport.getSelectedItem().toString(), dist, st, stt, dur);
					dispose();
		      }

		});
		
		btnNewButton.setBounds(348, 100, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(
				(e) -> {
					this.dispose();
			});
		btnNewButton_1.setBounds(347, 198, 89, 23);
		contentPane.add(btnNewButton_1);
	}
	
	

}
