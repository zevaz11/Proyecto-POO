package Menu;

import models.*;
import models.Character;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Window extends JFrame {

    JPanel menuPanel;
    JLabel menuBackground;
    NewGameMenu newGameMenu;
    CreateUserMenu newUserMenu;
    CreateCharacter createCharacterPanel;
    StatisticsMenu statisticsMenu;
    winMenu winnerScreen;

    //Objects in game
    ArrayList<Tower> towers;
    ArrayList<Abilitie> abilities;
    ArrayList<Character> characters;
    ArrayList<User> users;
    Arena gameArena;
    setImages gameImages;

    public Window(){

        initializeData();
        initializeMenu();
        initializeArena();
    }

    // ---------------------------- Initialization ----------------------------

    private void initializeData() {
        setSize(1000,700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE); //finish the program when the window is closed
        setLocationRelativeTo(null); //set the window in the center of the screen
        setTitle("League TEC"); //put a title to the window
        setMenuPanel();
        menuSettings();
        gameImages = new setImages();
        gameImages.charactersImages();
        gameImages.menuButtonsImages();
    }

    private void initializeMenu(){
        //Initialize fonts
        letterFonts menuFonts = new letterFonts();

        // Custom colors, it works with the RGB system
        Color ButtonColor = new Color(70, 130, 180); // Steel Blue
        Color ExitColor = new Color(219, 75, 60);

        // Create title label
        JLabel titleLabel = new JLabel("LEAGUE OF TEC", JLabel.CENTER);
        titleLabel.setFont(menuFonts.titleFont);  // Set custom font
        titleLabel.setForeground(Color.WHITE);  // Set text color for the title

        // Create buttons with custom colors, font size, and size
        JButton newGameButton = createCustomButton("New Game", ButtonColor, Color.WHITE, gameImages.newGameImg, gameImages.newGameImg2);
        JButton createUserButton = createCustomButton("Create New User", ButtonColor, Color.WHITE, gameImages.newUserImg, gameImages.newUserImg2);
        JButton createCharacterButton = createCustomButton("Create New Character", ButtonColor, Color.WHITE, gameImages.newCharacterImg, gameImages.newCharacterImg2);
        JButton statisticsButton = createCustomButton("Statistics", ButtonColor, Color.WHITE, gameImages.statisticsImg, gameImages.statisticsImg2);
        JButton exitButton = createCustomButton("Exit", ExitColor, Color.BLACK, gameImages.exitImg, gameImages.exitImg2);

        // Add components to the window using GridBagLayout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 50, 10);
        gbc.fill = GridBagConstraints.NONE; // Let components have their preferred size

        // Add title label to the top, spanning all columns
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Set to 1 if you want the title centered in a single column
        menuBackground.add(titleLabel, gbc);

        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components

        // Add buttons below the title
        gbc.gridwidth = 0;  // Reset the gridwidth for buttons

        // First row (buttons)

        gbc.gridx = 0; gbc.gridy = 1;
        menuBackground.add(newGameButton, gbc);

        // Second row
        gbc.gridx = 0; gbc.gridy = 2;
        menuBackground.add(createUserButton, gbc);

        // Third row
        gbc.gridx = 0; gbc.gridy = 3;
        menuBackground.add(createCharacterButton, gbc);

        // Fourth row
        gbc.gridx = 0; gbc.gridy = 4;
        menuBackground.add(statisticsButton, gbc);

        // Fifth row (Exit button)
        gbc.gridx = 0; gbc.gridy = 5;
        menuBackground.add(exitButton, gbc);

        // Add action listeners to buttons
        newGameButton.addActionListener(e -> setNewGamePanel());
        createUserButton.addActionListener(e -> setNewUserPanel());
        createCharacterButton.addActionListener(e -> setCreateCharacterPanel());
        statisticsButton.addActionListener(e -> setStatisticsPanel());
        exitButton.addActionListener(e -> System.exit(0));

    }

    private void initializeArena(){
        //Initialize arena
        towers = new ArrayList();
        characters = gameImages.chargeCharacters();
        gameImages.chargeAbilities();
        abilities = gameImages.getAbilities();
        gameImages.addAbilities(characters);
        users = gameImages.chargeUsers();

        towers.add(new Tower(1));
        towers.add(new Tower(1));
        towers.add(new Tower(1));
        towers.add(new Tower(2));
        towers.add(new Tower(2));
        towers.add(new Tower(2));

        gameArena = new Arena(ArenaType.FIRE, towers, new int[]{3, 6}, abilities);

        //Create menus' panels
        winnerScreen = new winMenu(this, menuPanel);
        newGameMenu = new NewGameMenu(this, menuPanel, characters, users, towers, gameArena, winnerScreen);
        createCharacterPanel = new CreateCharacter( this, menuPanel, abilities, characters, newGameMenu);
        statisticsMenu = new StatisticsMenu(this, menuPanel, users);
        newUserMenu = new CreateUserMenu(this, menuPanel, users, statisticsMenu);
        newGameMenu.newGamePanel.setVisible(false);
    }

    // ---------------------------- Panels ----------------------------

    private void setMenuPanel(){
        //set panel
        menuPanel = new JPanel(new GridBagLayout()); //Create a panel
        menuPanel.setBackground(Color.DARK_GRAY); //set color of the panel
        menuPanel.setBounds(0, 0, 1000, 700);
        getContentPane().add(menuPanel); //put the panel on the window
    }

    private void setNewGamePanel(){
        //charge the new user menu
        newGameMenu.newGamePanel.setVisible(true);
        menuPanel.setVisible(false);
        setTitle("New Game - Select Users");
        newGameMenu.updateUsers(users);
        revalidate();
        repaint();
    }

    private void setNewUserPanel(){
        //charge the new user menu
        menuPanel.setVisible(false);
        newUserMenu.newUserPanel.setVisible(true);
        setTitle("Create New User");
    }

    private void setCreateCharacterPanel(){
        //charge the new user menu
        menuPanel.setVisible(false);
        createCharacterPanel.backgroundImagePanel.setVisible(true);
        setTitle("Create Character");
    }
    private void setStatisticsPanel(){
        //charge the new user menu
        menuPanel.setVisible(false);
        statisticsMenu.statisticsPanel.setVisible(true);
        setTitle("Statistics");
        statisticsMenu.rechargeUsers(users);
    }

    private void menuSettings(){
        //menu background
        menuBackground = new JLabel(new ImageIcon("src/images/menuScreen.png"));
        menuBackground.setLayout(new GridBagLayout());
        menuPanel.add(menuBackground);
    }


    // Method to create a button with custom colors and font
    private JButton createCustomButton(String text, Color bgColor, Color textColor, String imageSource, String cursorImage) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(250, 50)); // Set preferred size
        button.setMaximumSize(new Dimension(300, 75)); // Set maximum size
        ImageIcon temporalImage = new ImageIcon(imageSource);
        button.setIcon(temporalImage);
        button.setRolloverIcon(new ImageIcon(cursorImage));
        button.setBorderPainted(false); // Remove the border from the botton
        button.setText(null);
        return button;
    }
}
