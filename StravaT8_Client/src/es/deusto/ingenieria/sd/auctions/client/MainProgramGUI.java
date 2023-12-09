package es.deusto.ingenieria.sd.auctions.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.auctions.client.gui.ActivityTimeChallengeGUI;
import es.deusto.ingenieria.sd.auctions.client.gui.ChallengesGUI;
import es.deusto.ingenieria.sd.auctions.client.gui.DistanceChallengeGUI;
import es.deusto.ingenieria.sd.auctions.client.gui.LoginGUI;
import es.deusto.ingenieria.sd.auctions.client.gui.RegisterGUI;
import es.deusto.ingenieria.sd.auctions.client.gui.SessionGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainProgramGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel overallPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainProgramGUI frame = new MainProgramGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainProgramGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        overallPanel = new JPanel();
        overallPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(overallPanel);
        overallPanel.setLayout(new BoxLayout(overallPanel, BoxLayout.Y_AXIS));

        JLabel stravaLabel = new JLabel("STRAVA");
        stravaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        overallPanel.add(stravaLabel);

        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.Y_AXIS));

        JButton registerButton = new JButton("Register");
        JButton logoutButton = new JButton("Logout");
        JButton loginButton = new JButton("Login");
        
        registerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, registerButton.getPreferredSize().height));
        logoutButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, logoutButton.getPreferredSize().height));
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, loginButton.getPreferredSize().height));
        
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                RegisterGUI registerWindow = new RegisterGUI();
                registerWindow.setVisible(true);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MainProgram.loginDialog.logout();
                dispose();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                logoutButton.setVisible(true);
                LoginGUI loginWindow = new LoginGUI();
                loginWindow.setVisible(true);
            }
        });

        
        accountPanel.add(registerButton);
        accountPanel.add(logoutButton);
        accountPanel.add(loginButton);
        logoutButton.setVisible(false);

        JLabel accountLabel = new JLabel("Functions");
        accountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        overallPanel.add(accountLabel);
        overallPanel.add(accountPanel);

        JLabel functionsLabel = new JLabel("Functions");
        functionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        overallPanel.add(functionsLabel);

        JPanel functionPanel = new JPanel();
        functionPanel.setLayout(new GridLayout(0, 2));

        JButton dChallengeButton = new JButton("Distance Challenge");
        JButton tChallengeButton = new JButton("Activity Time Challenge");
        JButton createSessionButton = new JButton("Create Session");
        JButton getChallengesButton = new JButton("Get Active Challenges");
        JButton getSessionsButton = new JButton("Get Training Sessions");

        dChallengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                DistanceChallengeGUI distanceChallengeWindow = new DistanceChallengeGUI();
                distanceChallengeWindow.setVisible(true);
            }
        });

        tChallengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ActivityTimeChallengeGUI activityTimeChallengeWindow = new ActivityTimeChallengeGUI();
                activityTimeChallengeWindow.setVisible(true);
            }
        });

        createSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SessionGUI sessionWindow = new SessionGUI();
                sessionWindow.setVisible(true);
            }
        });

        getChallengesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ChallengesGUI gui = new ChallengesGUI();
                gui.setVisible(true);
            }
        });

        getSessionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MainProgram.mainWindow.getTrainingSession(MainProgram.loginController.getToken());
            }
        });

        functionPanel.add(getChallengesButton);
        functionPanel.add(getSessionsButton);
        functionPanel.add(dChallengeButton);
        functionPanel.add(createSessionButton);
        functionPanel.add(tChallengeButton);

        overallPanel.add(functionPanel);
    }
}
