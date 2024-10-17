package Menu;

import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class winMenu extends JFrame{

        JFrame mainWindow;
        JPanel winnerPanel, previousPanel;
        ActionListener backMenu;
        letterFonts Fonts = new letterFonts();
        JTextField nameBox;
        User winner;
        JLabel profilePicture, winnerName;

        public winMenu(JFrame newScreen, JPanel menuPanel) {

            //charge methods
            actionsListeners();

            //set screen
            mainWindow = newScreen;

            //charge the previous menu panel
            previousPanel = menuPanel;

            // create the panel for the new user menu
            winnerPanel = new JPanel(); //Create a panel
            winnerPanel.setBounds(0, 0, 1000, 700);
            winnerPanel.setVisible(false);
            mainWindow.add(winnerPanel); //put the panel on the window

            //new user background
            JLabel winnerBackground = new JLabel(new ImageIcon("src/images/winBackground.png"));
            winnerBackground.setLayout(null);
            winnerBackground.setBounds(0, 0, 1000, 700);
            winnerPanel.add(winnerBackground);

            // Title
            JLabel winnerTitle = new JLabel("WINNER");
            winnerTitle.setFont(Fonts.subMenusFont);  // Set custom font
            winnerTitle.setBounds(400, 60, 375, 50);
            winnerTitle.setForeground(Color.ORANGE);  // Set text color for the title
            winnerBackground.add(winnerTitle);

            //Profile picture
            profilePicture = new JLabel();
            profilePicture.setLayout(null);
            profilePicture.setBounds(425, 200, 150, 150);
            profilePicture.setIcon(new ImageIcon("src/images/defaultPicture.png"));

            winnerBackground.add(profilePicture);

            // Subpanel for the name
            JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); //center the text
            namePanel.setBounds(0, 350, 1000, 200);
            namePanel.setOpaque(false);

            // Winner name
            winnerName = new JLabel("Name");
            winnerName.setFont(Fonts.subMenusFont);  // Set custom font
            winnerName.setForeground(Color.white);  // Set text color for the title
            namePanel.add(winnerName);

            winnerBackground.add(namePanel);

            //Back button
            JButton backButton = new JButton();
            backButton.setLayout(null);
            backButton.setBounds(375, 550, 250, 50);
            backButton.setIcon(new ImageIcon("src/images/backRedButton.png"));
            backButton.setRolloverIcon(new ImageIcon("src/images/backRedButton2.png"));
            backButton.setBorderPainted(false); // Remove the border from the botton
            backButton.addActionListener(backMenu);
            winnerBackground.add(backButton);

            mainWindow.revalidate();
            mainWindow.repaint();


        }
        //---------- Action Listeners ----------

        private void actionsListeners(){

            //back to menu
            backMenu = _ -> {
                winnerPanel.setVisible(false);
                previousPanel.setVisible(true);
            };

        }

        public void updateWinner(User newWinner){
            winnerName.setText(newWinner.name);
            profilePicture.setIcon(newWinner.picture);
        }
    }
