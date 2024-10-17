package Menu;

import models.Arena;
import models.Character;
import models.Tower;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewGameMenu {
    JFrame newGameScreen;
    JPanel previousPanel, leftPanel, rightPanel;
    JLabel player1Background, player2Background, readyPlayer1, readyPlayer2;
    JSplitPane newGamePanel;
    letterFonts subMenuFonts = new letterFonts();
    ArrayList<Character> listOfCharacters;
    ArrayList<Tower> listOfTowers;
    ArrayList<String> usersBox;
    JComboBox<String> userSelectionLeft, userSelectionRight;
    boolean Ready1 = false;
    boolean Ready2 = false;
    Arena newArena;
    JButton PlayerReady1, PlayerReady2, backButton1, backButton2;
    winMenu winnerScreen;

    public NewGameMenu(JFrame mainScreen, JPanel menuPanel, ArrayList<Character> characters, ArrayList<User> users, ArrayList<Tower> towers, Arena arena, winMenu newWinner) {

        //set characters list
        listOfCharacters = characters;
        listOfTowers = towers;

        //set screen
        newGameScreen = mainScreen;
        newGameScreen.setLocationRelativeTo(null); //set the window in the center of the screen

        //charge the previous menu panel and the arena
        previousPanel = menuPanel;
        newArena = arena;
        winnerScreen = newWinner;

        // Create two panels for the split pane
        leftPanel = new JPanel(new GridBagLayout());
        rightPanel = new JPanel(new GridBagLayout());

        // Create panel images
        player1Background = new JLabel(new ImageIcon("src/images/player1Background.png"));
        player1Background.setLayout(new GridBagLayout());
        leftPanel.add(player1Background);

        player2Background = new JLabel(new ImageIcon("src/images/player2Background.png"));
        player2Background.setLayout(new GridBagLayout());
        rightPanel.add(player2Background);

        // Create GridBagConstraints for layout management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(25, 10, 25, 10); // Padding around components
        gbc.fill = GridBagConstraints.NONE; // No resizing of components
        gbc.anchor = GridBagConstraints.CENTER; // Center components in their grid cells

        // Create message labels for each panel
        JLabel leftLabel = new JLabel("Select User for Left:");
        JLabel rightLabel = new JLabel("Select User for Right:");

        leftLabel.setFont(subMenuFonts.font3);  // Set custom font
        leftLabel.setForeground(Color.WHITE);  // Set text color for the title

        rightLabel.setFont(subMenuFonts.font3);  // Set custom font
        rightLabel.setForeground(Color.WHITE);  // Set text color for the title

        // Create user selection combo boxes and set preferred size to make them smaller
        usersBox = new ArrayList<>();
        for (User currentUser : users)
            usersBox.add(currentUser.name);
        String[] usersList = usersBox.toArray(new String[0]);
        userSelectionLeft = new JComboBox<>(usersList);
        userSelectionRight = new JComboBox<>(usersList);

        //Ready button images
        String readyImg = "src/images/readyButton.png";
        String readyImg2 = "src/images/readyButton2.png";

        // Buttons
        PlayerReady1 = createCustomButton("Ready", readyImg, readyImg2, 300, 75);
        PlayerReady2 = createCustomButton("Ready", readyImg, readyImg2, 300, 75);

        //Back button images
        String backImg = "src/images/backRedButton.png";
        String backImg2 = "src/images/backRedButton2.png";

        //Back button #1
        backButton1 = createCustomButton("", backImg, backImg2, 250, 50);
        backButton2 = createCustomButton("", backImg, backImg2, 250, 50);

        // Set smaller size for the combo boxes
        userSelectionLeft.setPreferredSize(new Dimension(150, 25));
        userSelectionRight.setPreferredSize(new Dimension(150, 25));

        // Add label and combo boxes to the left panel using GridBagLayout
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0 for label
        player1Background.add(leftLabel, gbc);

        gbc.gridy = 1; // Row 1 for combo box
        player1Background.add(userSelectionLeft, gbc);
        gbc.gridy = 2; // Row 2 for character selection combo box

        // Add the ready buttons to the left panel using GridBagLayout
        gbc.gridy = 4;
        player1Background.add(PlayerReady1, gbc);

        gbc.insets = new Insets(80, 10, 10, 10); // Padding around components
        // Add the back buttons to the left panel using GridBagLayout
        gbc.gridy = 6;
        player1Background.add(backButton1, gbc);

        gbc.insets = new Insets(25, 10, 25, 10); // Padding around components

        // Add label and combo boxes to the right panel using GridBagLayout
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0 for label
        player2Background.add(rightLabel, gbc);

        gbc.gridy = 1; // Row 1 for combo box
        player2Background.add(userSelectionRight, gbc);
        gbc.gridy = 2; // Row 2 for character selection combo box

        // Add the ready buttons to the right panel using GridBagLayout
        gbc.gridy = 4;
        player2Background.add(PlayerReady2, gbc);

        // Add the back buttons to the right panel using GridBagLayout
        gbc.insets = new Insets(80, 10, 10, 10); // Padding around components

        gbc.gridy = 6;
        player2Background.add(backButton2, gbc);

        // Create a JSplitPane to split the window into two halves
        newGamePanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        newGamePanel.setDividerLocation(480); // Position the divider in the middle

        // Add the split pane to the frame
        newGameScreen.add(newGamePanel);
        //newGamePanel.setVisible(true);

        PlayerReady1.addActionListener(e -> checkReady(1, users));
        PlayerReady2.addActionListener(e -> checkReady(2, users));
        backButton1.addActionListener(e -> backToMenu());
        backButton2.addActionListener(e -> backToMenu());

        newGameScreen.revalidate();
        newGameScreen.repaint();
    }

    private JButton createCustomButton(String text, String image, String image2, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height)); // Set preferred size
        button.setMaximumSize(new Dimension(width, height)); // Set maximum size
        button.setIcon(new ImageIcon(image));
        button.setRolloverIcon(new ImageIcon(image2));
        button.setBorderPainted(false); // Remove the border around the button text
        button.setText(null);
        return button;
    }

    private void checkReady(int num, ArrayList<User> allUsers) {
        if (num == 1) {
            Ready1 = true;
        } else {
            Ready2 = true;
        }
        if (Ready1 && Ready2) {
            imageReady1();
            imageReady2();
            //Define users for the play
            newArena.player1 = getUserByName((String) userSelectionLeft.getSelectedItem(), allUsers);
            newArena.player2 = getUserByName((String) userSelectionRight.getSelectedItem(), allUsers);
            // Both players are ready, start the game
            newGamePanel.setVisible(false);
            setArena arenaWindow = new setArena(newGameScreen, listOfCharacters,  listOfTowers, newArena, subMenuFonts);
            Actions arenaActions = new Actions(arenaWindow, winnerScreen, arenaWindow.actualElement);
            readyPlayer1.setVisible(false);
            readyPlayer2.setVisible(false);
        } else if (Ready1) {
            imageReady1();
        } else {
            imageReady2();
        }
    }

    public void updateUsers(ArrayList<User> newUsers){

        userSelectionLeft.removeAllItems();
        userSelectionRight.removeAllItems();
        usersBox.clear();

        for (User currentUser : newUsers)
            usersBox.add(currentUser.name);
        String[] usersList = usersBox.toArray(new String[0]);

        userSelectionLeft.setModel(new DefaultComboBoxModel<>(usersList));
        userSelectionRight.setModel(new DefaultComboBoxModel<>(usersList));

        //remove old action listeners for the ready buttons
        for (ActionListener al : PlayerReady1.getActionListeners())
            PlayerReady1.removeActionListener(al);
        for (ActionListener al : PlayerReady2.getActionListeners())
            PlayerReady2.removeActionListener(al);

        PlayerReady1.addActionListener(e -> checkReady(1, newUsers));
        PlayerReady2.addActionListener(e -> checkReady(2, newUsers));
    }

    public User getUserByName(String name, ArrayList<User> allUsers){

        for (User currentUser : allUsers)
            if(name == currentUser.name)
                return currentUser;
        return null;
    }

    private void backToMenu(){
        //back to menu
            newGamePanel.setVisible(false);
            previousPanel.setVisible(true);
    }

    private void imageReady1(){
        // Ready1 Panel
        readyPlayer1 = new JLabel(new ImageIcon("src/images/readyPlayer1.png"));
        readyPlayer1.setLayout(new GridBagLayout());
        leftPanel.remove(player1Background);
        leftPanel.add(readyPlayer1);
        leftPanel.revalidate();
        leftPanel.repaint();
    }

    private void imageReady2(){
        // Ready2 Panel
        readyPlayer2 = new JLabel(new ImageIcon("src/images/readyPlayer2.png"));
        readyPlayer2.setLayout(new GridBagLayout());
        rightPanel.remove(player2Background);
        rightPanel.add(readyPlayer2);
        rightPanel.revalidate();
        rightPanel.repaint();
    }
}

