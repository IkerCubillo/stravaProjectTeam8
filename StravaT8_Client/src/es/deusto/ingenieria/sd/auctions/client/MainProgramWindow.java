package es.deusto.ingenieria.sd.auctions.client;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import es.deusto.ingenieria.sd.auctions.client.gui.RegisterWindow;
import es.deusto.ingenieria.sd.auctions.client.gui.LoginWindow2;
import es.deusto.ingenieria.sd.auctions.client.gui.MainWindow;
import es.deusto.ingenieria.sd.auctions.client.gui.DistanceChallengeWindow;
import es.deusto.ingenieria.sd.auctions.client.gui.ActivityTimeChallengeWindow;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
import es.deusto.ingenieria.sd.auctions.client.controller.MainController;
import es.deusto.ingenieria.sd.auctions.client.gui.AcceptChallengeWindow;
import es.deusto.ingenieria.sd.auctions.client.gui.SessionWindow;
import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;


public class MainProgramWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RegisterWindow registerWindow; 
	private LoginWindow2 loginWindow;
	private DistanceChallengeWindow distanceChallengeWindow; 
	private ActivityTimeChallengeWindow activityTimeChallengeWindow;
	private AcceptChallengeWindow acceptChallengeWindow; 
	private SessionWindow sessionWindow;

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
		ServiceLocator serviceLocator = new ServiceLocator();
		LoginController loginController = new LoginController(serviceLocator);
		MainController mainController = new MainController(serviceLocator);			
		MainWindow mainWindow = new MainWindow(mainController);
		
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
		btnNewButton.setBounds(81, 70, 89, 23);
		btnNewButton.addActionListener(
				(e) -> {
					this.dispose();
					registerWindow.setVisible(true);
			});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setBounds(273, 70, 89, 23);
		btnNewButton_1.addActionListener(
				(e) -> {
					this.dispose();
					loginWindow.setVisible(true);
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
		btnNewButton_2.addActionListener(
				(e) -> {
					this.dispose();
					distanceChallengeWindow.setVisible(true);
			});
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Activity Time Challenge");
		btnNewButton_3.setBounds(269, 159, 155, 23);
		btnNewButton.addActionListener(
				(e) -> {
					this.dispose();
					activityTimeChallengeWindow.setVisible(true);
			});
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Accept Challenge");
		btnNewButton_4.setLocation(33, 204);
		btnNewButton_4.setSize(123, 23);
		btnNewButton.addActionListener(
				(e) -> {
					mainWindow.acceptChallenge(loginController.getToken());
			});
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Create session");
		btnNewButton_5.setLocation(269, 204);
		btnNewButton_5.setSize(155, 23);
		btnNewButton.addActionListener(
				(e) -> {
					this.dispose();
					sessionWindow.setVisible(true);
			});
		contentPane.add(btnNewButton_5);
	}
}
