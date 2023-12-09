package es.deusto.ingenieria.sd.auctions.client.gui;

import javax.swing.*;

import es.deusto.ingenieria.sd.auctions.client.MainProgram;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChallengesGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JComboBox<ChallengeDTO> challengeComboBox;
    private List<ChallengeDTO> challenges;

    public ChallengesGUI() {
        setTitle("Challenge Selection");
        setSize(300, 175);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // set challenges
        challenges = MainProgram.mainWindow.getChallenges();

        // Display challenges in a combo box
        challengeComboBox = new JComboBox<>();
        for (ChallengeDTO challenge : challenges) {
            challengeComboBox.addItem(challenge);
        }
        panel.add(challengeComboBox, BorderLayout.NORTH);

        // Button panel with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Accept Challenge button
        JButton acceptChallengeButton = new JButton("Accept Challenge");
        acceptChallengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChallengeDTO selectedChallenge = (ChallengeDTO) challengeComboBox.getSelectedItem();
                if (selectedChallenge != null) {
                    MainProgram.mainWindow.acceptChallenge(MainProgram.loginController.getToken(), selectedChallenge);
                    System.out.println("Accepted Challenge: " + selectedChallenge);
                } else {
                    JOptionPane.showMessageDialog(ChallengesGUI.this, "No challenge selected.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(acceptChallengeButton);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener((e) -> {
            this.dispose();
        });
        buttonPanel.add(backButton);

        // Add button panel to the main panel
        panel.add(buttonPanel, BorderLayout.CENTER);

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChallengesGUI gui = new ChallengesGUI();
                    gui.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
