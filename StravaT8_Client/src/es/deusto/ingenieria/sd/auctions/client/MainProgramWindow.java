package es.deusto.ingenieria.sd.auctions.client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import es.deusto.ingenieria.sd.auctions.client.gui.RegisterWindow;
import es.deusto.ingenieria.sd.auctions.client.gui.LoginWindow2;
import es.deusto.ingenieria.sd.auctions.client.gui.MainWindow;
import es.deusto.ingenieria.sd.auctions.client.gui.DistanceChallengeWindow;
import es.deusto.ingenieria.sd.auctions.client.gui.LoginWindow;
import es.deusto.ingenieria.sd.auctions.client.gui.ActivityTimeChallengeWindow;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
import es.deusto.ingenieria.sd.auctions.client.controller.MainController;
import es.deusto.ingenieria.sd.auctions.client.gui.SessionWindow;
import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;


public class MainProgramWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainProgramWindow frame = new MainProgramWindow();
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
	public MainProgramWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("STRAVA");
		lblNewLabel.setBounds(200, 11, 39, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBounds(33, 70, 89, 23);
		btnNewButton.addActionListener(new ActionListener(){
		      @Override
		      public void actionPerformed(ActionEvent evt){
		    	  RegisterWindow registerWindow = new RegisterWindow();
				registerWindow.setVisible(true);
		      }

		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_8 = new JButton("Logout");
		btnNewButton_8.setBounds(313, 70, 89, 23);
		btnNewButton_8.addActionListener(new ActionListener(){
		      @Override
		      public void actionPerformed(ActionEvent evt){
		    	  MainProgram.loginDialog.logout();
		    	  dispose();
		      }

		});
		contentPane.add(btnNewButton_8);
		btnNewButton_8.setVisible(false);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setBounds(173, 70, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener(){
		      @Override
		      public void actionPerformed(ActionEvent evt){
		    	  btnNewButton_8.setVisible(true);
		    	  LoginWindow2 loginWindow = new LoginWindow2();
		    	  loginWindow.setVisible(true);
		      }

		});
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Account");
		lblNewLabel_1.setBounds(200, 38, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Functions");
		lblNewLabel_2.setBounds(193, 123, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Distance Challenge");
		btnNewButton_2.setLocation(33, 159);
		btnNewButton_2.setSize(123, 23);
		btnNewButton_2.addActionListener(new ActionListener(){
		      @Override
		      public void actionPerformed(ActionEvent evt){
		    	  DistanceChallengeWindow distanceChallengeWindow = new DistanceChallengeWindow();
		    	  distanceChallengeWindow.setVisible(true);
		      }

		});
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Activity Time Challenge");
		btnNewButton_3.setBounds(269, 159, 155, 23);
		btnNewButton_3.addActionListener(new ActionListener(){
		      @Override
		      public void actionPerformed(ActionEvent evt){
		    	  ActivityTimeChallengeWindow activityTimeChallengeWindow = new ActivityTimeChallengeWindow();
		    	  activityTimeChallengeWindow.setVisible(true);
		      }

		});
		
		contentPane.add(btnNewButton_3);
		
//		JButton btnNewButton_4 = new JButton("Accept Challenge");
//		btnNewButton_4.setLocation(33, 190);
//		btnNewButton_4.setSize(123, 23);
//		btnNewButton_4.addActionListener(new ActionListener(){
//		      @Override
//		      public void actionPerformed(ActionEvent evt){
//		    	  MainProgram.mainWindow.acceptChallenge(MainProgram.loginController.getToken());
//		      }
//
//		});
//		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Create session");
		btnNewButton_5.setLocation(269, 190);
		btnNewButton_5.setSize(155, 23);
		btnNewButton_5.addActionListener(new ActionListener(){
		      @Override
		      public void actionPerformed(ActionEvent evt){
		    	  
		  					SessionWindow sessionWindow = new SessionWindow();
		  					sessionWindow.setVisible(true);
		  			
		      }

		});
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Get challenges");
		btnNewButton_6.addActionListener(
				(e) -> {
					MainProgram.mainWindow.getChallenges();
			});
		btnNewButton_6.setBounds(33, 221, 123, 23);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Get training sessions");
		btnNewButton_7.addActionListener(
				(e) -> {
					MainProgram.mainWindow.getTrainingSession(MainProgram.loginController.getToken());
			});
		btnNewButton_7.setBounds(269, 221, 155, 23);
		contentPane.add(btnNewButton_7);
		
		
	}
}
