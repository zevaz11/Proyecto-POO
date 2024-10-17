package Menu;

import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StatisticsMenu extends JFrame{

    JFrame mainWindow;
    JPanel statisticsPanel, previousPanel;
    ActionListener backMenu, leftUser, rightUser;
    letterFonts Fonts = new letterFonts();
    JLabel profilePicture, actualName, destroyedTowers, destroyedEnemies, matchesWon;
    JButton leftButton, rightButton;
    ArrayList<User> users;
    int actualIndex;

    public StatisticsMenu(JFrame newScreen, JPanel menuPanel, ArrayList<User> newUsers) {

        users = newUsers;

        //charge methods
        actionsListeners();

        //set screen
        mainWindow = newScreen;

        //charge the previous menu panel
        previousPanel = menuPanel;

        // create the panel for the new user menu
        statisticsPanel = new JPanel(new GridBagLayout()); //Create a panel
        statisticsPanel.setBounds(0, 0, 1000, 700);
        statisticsPanel.setVisible(false);
        mainWindow.add(statisticsPanel); //put the panel on the window
        actualIndex = 0;

        //new user background
        JLabel statisticsBackground = new JLabel(new ImageIcon("src/images/statisticsBackground.png"));
        statisticsBackground.setLayout(new GridBagLayout());
        statisticsPanel.add(statisticsBackground);

        // Title
        JLabel statisticsTitle = new JLabel("Statistics");
        statisticsTitle.setFont(Fonts.subMenusFont);  // Set custom font
        statisticsTitle.setForeground(Color.white);  // Set text color for the title

        //Profile picture
        profilePicture = new JLabel();
        profilePicture.setLayout(null);
        profilePicture.setIcon(users.getFirst().picture);

        //Go left button
        leftButton = new JButton();
        leftButton.setPreferredSize(new Dimension(50, 50)); // Set preferred size
        leftButton.setIcon(new ImageIcon("src/images/leftButton.png"));
        leftButton.setRolloverIcon(new ImageIcon("src/images/leftButton2.png"));
        leftButton.setBorderPainted(false); // Remove the border from the botton
        leftButton.addActionListener(leftUser);

        //Go right button
        rightButton = new JButton();
        rightButton.setPreferredSize(new Dimension(50, 50)); // Set preferred size
        rightButton.setIcon(new ImageIcon("src/images/rightButton.png"));
        rightButton.setRolloverIcon(new ImageIcon("src/images/rightButton2.png"));
        rightButton.setBorderPainted(false); // Remove the border from the botton
        rightButton.addActionListener(rightUser);

        //User name
        actualName = new JLabel(users.getFirst().name);
        actualName.setFont(Fonts.font3);  // Set custom font
        actualName.setForeground(Color.orange);  // Set text color for the title
        //matches won
        matchesWon = new JLabel("Matches won: "+users.getFirst().statistics.getMatchesWon());
        matchesWon.setFont(Fonts.font4);  // Set custom font
        matchesWon.setForeground(Color.white);  // Set text color for the title
        //destroyed towers
        destroyedTowers = new JLabel("Destroyed Towers: "+users.getFirst().statistics.getDestroyedTowers());
        destroyedTowers.setFont(Fonts.font4);  // Set custom font
        destroyedTowers.setForeground(Color.white);  // Set text color for the title
        //destroyed enemies
        destroyedEnemies = new JLabel("Enemies defeated: "+users.getFirst().statistics.getDefeatedEnemies());
        destroyedEnemies.setFont(Fonts.font4);  // Set custom font
        destroyedEnemies.setForeground(Color.white);  // Set text color for the title

        //Back button
        JButton backButton = new JButton();
        backButton.setPreferredSize(new Dimension(250, 50)); // Set preferred size
        backButton.setIcon(new ImageIcon("src/images/backRedButton.png"));
        backButton.setRolloverIcon(new ImageIcon("src/images/backRedButton2.png"));
        backButton.setBorderPainted(false); // Remove the border from the botton
        backButton.addActionListener(backMenu);

        // Add components to the window using GridBagLayout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(50, 10, 50, 10);
        gbc.fill = GridBagConstraints.NONE; // Let components have their preferred size

        // Add title label to the top, spanning all columns
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        statisticsBackground.add(statisticsTitle, gbc);

        gbc.insets = new Insets(10, 50, 10, 50); // Padding around components
        gbc.gridwidth = 1;  // Reset gridwidth for buttons

        gbc.gridx = 1; gbc.gridy = 1;
        statisticsBackground.add(profilePicture, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        statisticsBackground.add(leftButton, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        statisticsBackground.add(rightButton, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        statisticsBackground.add(actualName, gbc);

        gbc.weighty = 0.1; gbc.gridx = 0; gbc.gridy = 3;
        statisticsBackground.add(matchesWon, gbc);

        gbc.gridx = 1;
        statisticsBackground.add(destroyedTowers, gbc);

        gbc.gridx = 2;
        statisticsBackground.add(destroyedEnemies, gbc);

        gbc.weighty = 0.3; gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        statisticsBackground.add(backButton, gbc);

        mainWindow.revalidate();
        mainWindow.repaint();


    }
    //---------- Action Listeners ----------

    private void actionsListeners(){

        //back to menu
        backMenu = _ -> {
            statisticsPanel.setVisible(false);
            previousPanel.setVisible(true);
        };

        leftUser = _ -> {
            if (actualIndex != 0) {
                actualIndex--;
                for(ActionListener al : leftButton.getActionListeners())
                    leftButton.removeActionListener(al);
                for(ActionListener al : rightButton.getActionListeners())
                    rightButton.removeActionListener(al);
                leftButton.addActionListener(leftUser);
                rightButton.addActionListener(rightUser);
                updateUser();
            }
        };
        rightUser = _ -> {
            if (actualIndex != users.size()-1) {
                actualIndex++;
                for(ActionListener al : leftButton.getActionListeners())
                    leftButton.removeActionListener(al);
                for(ActionListener al : rightButton.getActionListeners())
                    rightButton.removeActionListener(al);
                leftButton.addActionListener(leftUser);
                rightButton.addActionListener(rightUser);
                updateUser();
            }
        };

    }

    private void updateUser(){
        //Profile picture
        profilePicture.setIcon(users.get(actualIndex).picture);
        //User name
        actualName.setText(users.get(actualIndex).name);
        //matches won
        matchesWon.setText("Matches won: "+users.get(actualIndex).statistics.getMatchesWon());
        //destroyed towers
        destroyedTowers.setText("Destroyed Towers: "+users.get(actualIndex).statistics.getDestroyedTowers());
        //destroyed enemies
        destroyedEnemies.setText("Enemies defeated: "+users.get(actualIndex).statistics.getDefeatedEnemies());
    }

    public void rechargeUsers(ArrayList<User> newUsers){
        users = newUsers;
        actualIndex = 0;
        updateUser();

    }

}
