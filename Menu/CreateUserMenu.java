package Menu;

import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class CreateUserMenu extends JFrame {

    JFrame mainWindow;
    JPanel newUserPanel, previousPanel;
    ActionListener backMenu, createUser;
    letterFonts Fonts = new letterFonts();
    JTextField nameBox;
    StatisticsMenu statisticsMenu;
    //-----> user new information
    public ImageIcon profilePicture;

    public CreateUserMenu(JFrame newUserScreen, JPanel menuPanel, ArrayList<User> users, StatisticsMenu newStatisticsMenu) {

        //charge methods
        actionsListeners(users);
        statisticsMenu = newStatisticsMenu;

        //set screen
        mainWindow = newUserScreen;
        //mainWindow.setLocationRelativeTo(null); //set the window in the center of the screen

        //charge the previous menu panel
        previousPanel = menuPanel;

        // create the panel for the new user menu
        newUserPanel = new JPanel(); //Create a panel
        newUserPanel.setBounds(0, 0, 1000, 700);
        newUserPanel.setVisible(false);
        mainWindow.add(newUserPanel); //put the panel on the window

        //new user background
        JLabel newUserBackground = new JLabel(new ImageIcon("src/images/newUserScreen.png"));
        newUserBackground.setLayout(null);
        newUserBackground.setBounds(0, 0, 1000, 700);
        newUserPanel.add(newUserBackground);

        // Add components specific to the Create New User menu
        JLabel newUserTitle = new JLabel("Create user");
        newUserTitle.setFont(Fonts.subMenusFont);  // Set custom font
        newUserTitle.setBounds(325, 60, 375, 50);
        newUserTitle.setForeground(Color.WHITE);  // Set text color for the title
        newUserBackground.add(newUserTitle);

        //Profile picture
        JButton profilePicture = new JButton();
        profilePicture.setLayout(null);
        profilePicture.setBounds(425, 200, 150, 150);
        profilePicture.setIcon(new ImageIcon("src/images/defaultPicture.png"));
        profilePicture.setRolloverIcon(new ImageIcon("src/images/defaultPicture2.png"));
        profilePicture.setBorderPainted(false); // Remove the border from the botton
        profilePicture.addMouseListener(new filesImageActionListener());

        newUserBackground.add(profilePicture);

        //Text Box
        nameBox = new JTextField();
        nameBox.setBounds(400, 400, 200, 35);
        nameBox.setText(" Nombre de usuario...");
        nameBox.setBackground(Color.DARK_GRAY);
        nameBox.setFont(Fonts.font4);
        nameBox.setForeground(Color.white);
        newUserBackground.add(nameBox);

        //Back button
        JButton backButton = new JButton();
        backButton.setLayout(null);
        backButton.setBounds(200, 550, 250, 50);
        backButton.setIcon(new ImageIcon("src/images/backRedButton.png"));
        backButton.setRolloverIcon(new ImageIcon("src/images/backRedButton2.png"));
        backButton.setBorderPainted(false); // Remove the border from the botton
        backButton.addActionListener(backMenu);
        newUserBackground.add(backButton);

        //Create user button
        JButton createButton = new JButton();
        createButton.setLayout(null);
        createButton.setBounds(550, 550, 250, 50);
        createButton.setIcon(new ImageIcon("src/images/createButton.png"));
        createButton.setRolloverIcon(new ImageIcon("src/images/createButton2.png"));
        createButton.setBorderPainted(false); // Remove the border from the botton
        createButton.addActionListener(createUser);
        newUserBackground.add(createButton);

        newUserScreen.revalidate();
        newUserScreen.repaint();

    }
    //---------- Action Listeners ----------

    private void actionsListeners(ArrayList<User> allUsers){

        //back to menu
        backMenu = _ -> {
            newUserPanel.setVisible(false);
            previousPanel.setVisible(true);
        };

        createUser = _ -> {
            System.out.println("Creado!");
            User newUser = new User(nameBox.getText(), profilePicture);
            allUsers.add(newUser);
            //previousPanel.setVisible(true);
        };

    }

    //edit user photo
    public class filesImageActionListener extends MouseAdapter {
        public filesImageActionListener() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File ("src/images/userImages"));

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                profilePicture = new ImageIcon(String.valueOf(selectedFile));
            }
        }
    }
}