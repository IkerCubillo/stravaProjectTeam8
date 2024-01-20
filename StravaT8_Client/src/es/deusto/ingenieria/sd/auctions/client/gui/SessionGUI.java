package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Properties;
import java.sql.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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

import javax.swing.JButton;

public class SessionGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField titleTf;
	private JTextField distanceTf;
	private JTextField startDateTf;
	private JTextField startTimeTf;
	private JTextField durationTf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SessionGUI frame = new SessionGUI();
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
public SessionGUI() {
		
	
	//Configuracion de la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
		setSize(750, 500);
		setLocationRelativeTo(null);
	    getContentPane().setLayout(null);
		
	    //Fondo Blanco
	    JLabel labelBlanco = new JLabel("");
        JLabel labelFondo = new JLabel("");
//        labelBlanco.setIcon(new ImageIcon(DistanceChallengeGUI.class.getResource("/images/Blanco.PNG")));
//        labelFondo.setIcon(new ImageIcon(DistanceChallengeGUI.class.getResource("/images/imagenCiclista.jpg")));
	    
	    //Labels
		JLabel lblNewLabel = new JLabel("Distance Challenge");
		JLabel lblNewLabel_1 = new JLabel("Title");
		JLabel lblNewLabel_2 = new JLabel("Sport");
		JLabel distanceField = new JLabel("Distance");
		JLabel startDateField = new JLabel("Start date");
		JLabel startTimeField = new JLabel("Start time");
		JLabel durationField = new JLabel("Duration");
		
		//TextFields
		titleTf = new JTextField();
		distanceTf = new JTextField();
		startDateTf = new JTextField();
		startTimeTf = new JTextField();
		durationTf = new JTextField();
		
		//ComboBox
		JComboBox comboSport = new JComboBox();
		comboSport.setModel(new DefaultComboBoxModel(new String[] {"Running", "Cycling"}));
		comboSport.setMaximumRowCount(3);
	    
	    //Botones
		JButton btnNewButton = new JButton("Create");
		JButton btnNewButton_1 = new JButton("Back");		 
		
		
		//setBounds
		labelFondo.setBounds(0, 0, getWidth(), getHeight());
        labelBlanco.setBounds(190, 66, 382, 330);
		lblNewLabel.setBounds(340, 88, 131, 14);
		lblNewLabel_1.setBounds(230, 118, 46, 14);
		lblNewLabel_2.setBounds(230, 159, 46, 14);
		distanceField.setBounds(230, 200, 46, 14);
		startDateField.setBounds(230, 241, 57, 14);
		titleTf.setBounds(377, 118, 160, 20);
		distanceTf.setBounds(377, 200, 160, 20);
		startDateTf.setBounds(377, 238, 160, 20);
		comboSport.setBounds(377, 159, 160, 20);
		startTimeField.setBounds(230, 282, 57, 14);
		durationField.setBounds(230, 323, 46, 14);
		startTimeTf.setBounds(377, 282, 160, 20);
		durationTf.setBounds(377, 323, 160, 20);
		btnNewButton.setBounds(420, 364, 89, 23);
		btnNewButton_1.setBounds(275, 364, 89, 23);

		//Listeners
		btnNewButton.addActionListener(new ActionListener(){
		      @Override
		      public void actionPerformed(ActionEvent evt){
		    	  	String distance = distanceTf.getText();
					float dist = Float.parseFloat(distance); 
					String duration = durationTf.getText();
					float dur = Float.parseFloat(duration);
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
					String startDate = startDateTf.getText();
					String startTime = startTimeTf.getText();
					java.util.Date date = null;
					try {
						date = sdf1.parse(startDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
					
					LocalTime stt = null;
					stt = LocalTime.parse(startTime);
					MainProgram.mainWindow.createSession(MainProgram.loginController.getToken(), titleTf.getText(), 
							comboSport.getSelectedItem().toString(), dist, sqlStartDate, stt, dur);
					dispose();
		      }

		});
		
		btnNewButton_1.addActionListener(
				(e) -> {
					this.dispose();
			});
		
	    
		//Anyadir al contentPane
        labelFondo.setBounds(0, 0, 734, 461);
	    contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel_2);
		contentPane.add(distanceField);
		contentPane.add(startDateField);
		
		contentPane.add(titleTf);
		titleTf.setColumns(10);
		contentPane.add(distanceTf);
		distanceTf.setColumns(10);
		contentPane.add(startDateTf);
		startDateTf.setColumns(10);
		
		contentPane.add(labelBlanco);
		//contentPane.add(labelFondo);
		
		contentPane.add(comboSport);
		contentPane.add(startTimeField);
		contentPane.add(durationField);
		
		contentPane.add(startTimeTf);
		startTimeTf.setColumns(10);
		contentPane.add(durationTf);
		durationTf.setColumns(10);
		
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton_1);
		
		contentPane.setComponentZOrder(durationField, 0);
		contentPane.setComponentZOrder(startTimeField, 0);
		contentPane.setComponentZOrder(durationTf, 0);
		contentPane.setComponentZOrder(startTimeTf, 0);
		contentPane.setComponentZOrder(comboSport, 0);
		contentPane.setComponentZOrder(btnNewButton, 0);
		//contentPane.setComponentZOrder(lblNewLabel_5, 0);
		contentPane.setComponentZOrder(btnNewButton_1, 0);
	}
}