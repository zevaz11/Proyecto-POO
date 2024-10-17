package Menu;

import models.Abilitie;
import models.Arena;
import models.Character;
//import window.ImagesClass;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

public class CreateCharacter extends JFrame {

    private boolean currentStatus = true;
    public JPanel backgroundImagePanel;
    public JLabel backgroundImageLabel = new JLabel(new ImageIcon(CreateCharacter.class.getResource("/images/kingdom.png")));
    public JFrame frameWindow;
    public JPanel previousPanel;
    ArrayList<Character> allCharacters;
    ArrayList<Abilitie> allAbilities;
    JComboBox<String> skillsComboBox, skillsComboBox2;
    NewGameMenu startGame;
    File selectedFile;
    JComboBox<String> photosComboBox;

    public class changeComponentsStatus  {
        private Component[] containerElements;

        public changeComponentsStatus (Container screen) {
            this.containerElements = screen.getComponents();
        }
        public void disableComponents (Container container) {
            Component[] containerElements = container.getComponents();
            currentStatus = false;
            for (Component element : containerElements) {
                if ((element instanceof JComboBox<?>) || (element instanceof JTextField)) {
                    element.setEnabled(false);
                }
                if (element instanceof Container) {
                    disableComponents((Container) element);
                }
            }
        }

        public void enableComponents (Container container) {
            Component[] containerElements = container.getComponents();
            currentStatus = true;
            for (Component element : containerElements) {
                element.setEnabled(true);
                if (element instanceof Container) {
                    enableComponents((Container) element);
                }
            }
        }
    }

    public class darken {

        private JPanel blackScreen;
        private float opacity = 0;

        public darken(JLabel backgroundImageLabel) {

            blackScreen = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // Establecer un color de fondo semitransparente
                    g.setColor(new Color(0, 0, 0, (int) opacity)); // Blanco con 50% de opacidad
                    g.fillRect(0, 0, getWidth(), getHeight()); // Dibujar el fondo
                }
            };
            blackScreen.setBounds(0, 0, 1000, 700);
            blackScreen.setOpaque(false);
            blackScreen.setBackground(Color.BLACK);
            backgroundImageLabel.add(blackScreen);
            backgroundImageLabel.setLayout(null);
            //backgroundImageLabel.repaint();
            //darkenShape ();
        }

        public void darkenShape(JLabel backgroundImageLabel) {
            for (int i = 0; i <= 250; i += 6) {
                opacity = i;
                blackScreen.repaint();
                backgroundImageLabel.revalidate();
                backgroundImageLabel.repaint();
                Toolkit.getDefaultToolkit().sync();
                //this

                try {
                    Thread.sleep(25); // Espera 50 ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            opacity = 255;
            blackScreen.repaint();
        }

        public void ligthenShape(JLabel backgroundImageLabel) {
            for (int i = 255; i > 5; i -= 6) {
                opacity = i;
                //blackScreen.revalidate();
                blackScreen.repaint();
                backgroundImageLabel.revalidate();
                backgroundImageLabel.repaint();
                Toolkit.getDefaultToolkit().sync();

                try {
                    Thread.sleep(25); // Espera 50 ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            opacity = 0;
            blackScreen.repaint();
        }

    }

    public class changeElementsComboBoxTheme {
        private JComboBox<String> elementsCboBox;
        private JLabel backgroundImageLabel;

        public changeElementsComboBoxTheme(JComboBox<String> elementsCboBox, JLabel backgroundImageLabel) {
            this.elementsCboBox = elementsCboBox;
            this.backgroundImageLabel = backgroundImageLabel;
            changeElementTheme ();
        }

        public void changeElementTheme() {
            String selectedOption = (String) elementsCboBox.getSelectedItem();
            File soundFile;
            try {
                if (Objects.equals(selectedOption, "Fire\uD83D\uDD25")) {
                    soundFile = new File("src/sounds/fireSoundFile.wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream (soundFile);

                    Clip clipSound = AudioSystem.getClip();
                    clipSound.open(audioStream);
                    clipSound.start();
                    backgroundImageLabel.setIcon(new ImageIcon(CreateCharacter.class.getResource("/images/fireVillageFile.png")));
                }
                else if (Objects.equals(selectedOption, "Water\uD83D\uDCA7")) {
                    soundFile = new File("src/sounds/waterSoundFile.wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream (soundFile);

                    Clip clipSound = AudioSystem.getClip();
                    clipSound.open(audioStream);
                    clipSound.start();
                    backgroundImageLabel.setIcon(new ImageIcon (CreateCharacter.class.getResource("/images/waterVillageFile.png")));
                }
                else if (Objects.equals(selectedOption, "Earth\uD83E\uDEA8")) {
                    soundFile = new File("src/sounds/earthSoundFile.wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream (soundFile);

                    Clip clipSound = AudioSystem.getClip();
                    clipSound.open(audioStream);
                    clipSound.start();
                    backgroundImageLabel.setIcon(new ImageIcon (CreateCharacter.class.getResource("/images/earthVillageFile.png")));
                }
                else if (Objects.equals(selectedOption, "Air\uD83C\uDF2A")) {
                    soundFile = new File("src/sounds/airSoundFile.wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream (soundFile);

                    Clip clipSound = AudioSystem.getClip();
                    clipSound.open(audioStream);
                    clipSound.start();
                    backgroundImageLabel.setIcon(new ImageIcon (CreateCharacter.class.getResource("/images/airVillageFile.png")));
                }
                else {
                    backgroundImageLabel.setIcon(new ImageIcon ("/images/kingdom.png"));
                }
            } catch (UnsupportedAudioFileException | IOException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    public class filesImageActionListener extends MouseAdapter {
        private JLabel characterPhoto;
        public filesImageActionListener(JLabel characterPhoto) {
            this.characterPhoto = characterPhoto;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JFileChooser fileChooser = new JFileChooser();

            File initialDirectory = new File("src/images");
            fileChooser.setCurrentDirectory(new File ("src/images/characterImages"));

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                characterPhoto.setIcon(new ImageIcon(String.valueOf(selectedFile)));
            }
        }
    }

    private class cancelLabelActionListener extends MouseAdapter {
        private JLabel labelCancel;

        public cancelLabelActionListener(JLabel labelCancel) {
            this.labelCancel = labelCancel;
        }

        @Override
        public void mouseClicked (MouseEvent e) {
            if (currentStatus) {
                backgroundImagePanel.setVisible(false);
                previousPanel.setVisible(true);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (currentStatus) {
                labelCancel.setText("<html><span style='background-color: #fae3a5; " +
                        "color: #560101; padding: 10px;'><u>Cancel</html>");
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            labelCancel.setText("<html><span style='background-color: #fae3a5; " +
                    "color: #961805; padding: 10px;'><u>Cancel</html>");
            //labelCancel.repaint();
        }
    }

    private class createLabelActionListener extends MouseAdapter {
        private JLabel labelCreate;
        private JLabel lblCreateText;
        private JTextField nameText;
        private JTextField healthText;
        private JTextField attackText;
        private JTextField defenseText;
        private JComboBox comboBoxElements;
        private JComboBox comboBoxSkills;
        JComboBox comboBoxSkills2;
        private JComboBox comboBoxPhotos;
        private JFrame windowCreateChar;

        public createLabelActionListener(JLabel labelCreate, JLabel lblCreateText, JTextField nameText, JTextField healthText, JTextField attackText, JTextField defenseText, JComboBox comboBoxElements, JComboBox comboBoxSkills, JComboBox comboBoxSkills2, JComboBox comboBoxPhotos, JFrame windowCreate) {
            this.labelCreate = labelCreate;
            this.lblCreateText = lblCreateText;
            this.nameText = nameText;
            this.healthText = healthText;
            this.attackText = attackText;
            this.defenseText = defenseText;
            this.comboBoxElements = comboBoxElements;
            this.comboBoxSkills = comboBoxSkills;
            this.comboBoxSkills2 = comboBoxSkills2;
            this.comboBoxPhotos = comboBoxPhotos;
            this.windowCreateChar = windowCreateChar;

        }

        @Override
        public void mouseClicked (MouseEvent e) {
            if (currentStatus) {

                if (nameText.getText().equals("") || healthText.getText().equals("") || attackText.getText().equals("") || defenseText.getText().equals("") || comboBoxPhotos.getSelectedItem().equals("None")) {
                    JOptionPane.showMessageDialog(null, "Ups, you got missing information...");

                }
                else {
                    try {
                        Character newCharacter = new Character(nameText.getText(), Integer.parseInt(healthText.getText()), 100, 0, Integer.parseInt(attackText.getText()), Integer.parseInt(defenseText.getText()), comboBoxElements.getSelectedItem().toString(), 1, 0, new ImageIcon("src/images/characterImages/" + photosComboBox.getSelectedItem()));
                        //set abilitie #1
                        for(Abilitie temp : allAbilities){
                            if(temp.getName() == skillsComboBox.getSelectedItem().toString())
                                newCharacter.abilitiesList.add(temp);
                        }
                        //set abilitie #2
                        for(Abilitie temp : allAbilities){
                            if(temp.getName() == skillsComboBox2.getSelectedItem().toString())
                                newCharacter.abilitiesList.add(temp);
                        }
                        allCharacters.add(newCharacter);
                        JOptionPane.showMessageDialog(null, "Character successfully created...");
                        backgroundImagePanel.setVisible(false);
                        previousPanel.setVisible(true);
                        startGame.listOfCharacters = allCharacters;
                    } catch (NumberFormatException i) {
                        JOptionPane.showMessageDialog(null, "Ups, some date are wrong...");
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (currentStatus) {
                labelCreate.setIcon(new ImageIcon("src/images/createCharacterButton2.png"));
                lblCreateText.setText("<html><span style='background-color: #5fab0e; " +
                        "color: #000000; padding: 10px;'>+ Create</html>");
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            labelCreate.setIcon(new ImageIcon("src/images/createCharacterButton.png"));
            lblCreateText.setText("<html><span style='background-color: #aedd05; " +
                    "color: #000000; padding: 10px;'>+ Create</html>");
        }
    }

    public void fillCharacterPhotosString (ArrayList<String> photosArray) {
        File characterPhotosFolder = new File("src/images/characterImages");

        if (characterPhotosFolder.exists() && characterPhotosFolder.isDirectory()) {
            for (File imageFile : characterPhotosFolder.listFiles()) {
                if (imageFile.getName().endsWith(".png")) {
                    photosArray.add(imageFile.getName());
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Ups, there is no a respective folder for character images...");
        }

    }


    public CreateCharacter(JFrame windowCreateChar, JPanel previousPanel, ArrayList<Abilitie> abilities, ArrayList<Character> characters, NewGameMenu newGame) {
        this.previousPanel = previousPanel;

        frameWindow = windowCreateChar;
        frameWindow.setLayout(null); // Desactivar el layout manager del JFrame

        backgroundImagePanel = new JPanel();
        backgroundImagePanel.setBounds(0, 0, 1000, 700);
        backgroundImagePanel.setVisible(false);

        backgroundImagePanel.add(backgroundImageLabel);
        frameWindow.add(backgroundImagePanel);

        backgroundImageLabel.setLayout(null); // Desactivar el layout manager del JPanel que contiene la imagen

        allCharacters = characters;
        allAbilities = abilities;
        startGame = newGame;

        darken blackBackground = new darken(backgroundImageLabel);
        changeComponentsStatus screenComponentsStatus = new changeComponentsStatus(backgroundImageLabel);

        // Crear un JLabel con texto y saltos de línea (usando HTML)
        JLabel labelCreate1 = new JLabel("<html><span style='background-color: #00a8f3; " +
                "color: #C21704; padding: 0px;'>CREATE A NEW CHARACTER</html>");
        labelCreate1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        labelCreate1.setBounds(90, 3, 450, 55); // Establecer la posición y tamaño del JLabel

        JLabel labelCreate2 = new JLabel("<html><span style='background-color: #00a8f3; " +
                "color: #C21704; padding: 0px;'>TO PROTECT YOUR KINGDOM ⚔\uFE0F</html>");
        labelCreate2.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        labelCreate2.setBounds(60, 30, 450, 55); // Establecer la posición y tamaño del JLabel


        JLabel imgSquare2 = new JLabel(new ImageIcon(CreateCharacter.class.getResource("/images/backSquare2.png")));
        imgSquare2.setBounds(240, 60, 500, 500);
        JLabel imgSquare = new JLabel(new ImageIcon(CreateCharacter.class.getResource("/images/backSquare.png")));
        imgSquare.setBounds(240, 125, 500, 500);

        JLabel nameLabel = new JLabel("<html>Name:</html>");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 21));
        nameLabel.setBounds(40, 40, 65, 25);

        JTextField nameTextField = new JTextField();
        nameTextField.setFont(new Font("Arial", Font.ITALIC, 16));
        nameTextField.setBounds(40, 65, 140, 30);
        nameTextField.setBackground(Color.decode("#fae3a5"));
        nameTextField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));



        JLabel healthLabel = new JLabel("<html>Health:</html>");
        healthLabel.setFont(new Font("Arial", Font.BOLD, 21));
        healthLabel.setBounds(200, 40, 70, 25);

        JTextField healthTextField = new JTextField();
        healthTextField.setFont(new Font("Arial", Font.ITALIC, 16));
        healthTextField.setBounds(200, 65, 70, 30);
        healthTextField.setBackground(Color.decode("#fae3a5"));
        healthTextField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));



        JLabel attackLabel = new JLabel("<html>Attack:</html>");
        attackLabel.setFont(new Font("Arial", Font.BOLD, 21));
        attackLabel.setBounds(40, 150, 80, 25);

        JTextField attackTextField = new JTextField();
        attackTextField.setFont(new Font("Arial", Font.ITALIC, 16));
        attackTextField.setBounds(40, 175, 80, 30);
        attackTextField.setBackground(Color.decode("#fae3a5"));
        attackTextField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));


        JLabel defenseLabel = new JLabel("<html>Defense:</html>");
        defenseLabel.setFont(new Font("Arial", Font.BOLD, 21));
        defenseLabel.setBounds(150, 150, 90, 25);

        JTextField defenseTextField = new JTextField();
        defenseTextField.setFont(new Font("Arial", Font.ITALIC, 16));
        defenseTextField.setBounds(150, 175, 90, 30);
        defenseTextField.setBackground(Color.decode("#fae3a5"));
        defenseTextField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));

        JLabel elementsLabel = new JLabel("<html>Vital Elements:</html>");
        elementsLabel.setFont(new Font("Arial", Font.BOLD, 21));
        elementsLabel.setBounds(40, 290, 150, 20);

        String [] elementsOptions = {"None", "Fire\uD83D\uDD25", "Water\uD83D\uDCA7", "Earth\uD83E\uDEA8", "Air\uD83C\uDF2A"};
        JComboBox<String> elementsComboBox = new JComboBox<>(elementsOptions);
        elementsComboBox.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 16));
        elementsComboBox.setPreferredSize(new Dimension(100, 35));
        elementsComboBox.setBounds(40, 330, 150, 35);
        elementsComboBox.setBackground(Color.decode("#fae3a5"));

        elementsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenComponentsStatus.disableComponents(backgroundImageLabel);
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        blackBackground.darkenShape(backgroundImageLabel);
                        new changeElementsComboBoxTheme(elementsComboBox, backgroundImageLabel);
                        blackBackground.ligthenShape(backgroundImageLabel);
                        screenComponentsStatus.enableComponents(backgroundImageLabel);
                        return null;
                    }
                };
                worker.execute();


            }
        });





        JLabel imgCharacterPhoto = new JLabel(new ImageIcon(CreateCharacter.class.getResource("/images/noneImageIcon.png")));
        imgCharacterPhoto.setBounds(300, 40, 150, 150);


        ArrayList<String> photosOptions = new ArrayList<>();
        photosOptions.add("None");
        fillCharacterPhotosString (photosOptions);
        Vector<String> photosOptionsV = new Vector<>(photosOptions);
        photosComboBox = new JComboBox<>(photosOptionsV);
        photosComboBox.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 16));
        photosComboBox.setPreferredSize(new Dimension(100, 35));
        photosComboBox.setBounds(280, 220, 150, 35);
        photosComboBox.setBackground(Color.decode("#fae3a5"));

        photosComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (photosComboBox.getSelectedItem().equals("None")) {
                    imgCharacterPhoto.setIcon(new ImageIcon("src/images/noneImageIcon.png"));
                }
                else {
                    imgCharacterPhoto.setIcon(new ImageIcon("src/images/characterImages/" + photosComboBox.getSelectedItem()));
                }
            }
        });

        JLabel imgFolder = new JLabel(new ImageIcon(CreateCharacter.class.getResource("/images/folderIcon.png")));
        imgFolder.setBounds(440, 220, 35, 35);
        imgFolder.addMouseListener(new filesImageActionListener(imgCharacterPhoto));

        JLabel skillsLabel = new JLabel("<html>Skills:</html>");
        skillsLabel.setFont(new Font("Arial", Font.BOLD, 21));
        skillsLabel.setBounds(280, 290, 70, 20);

        String[] abilitiesOptions = new String[abilities.size()];

        // We iterate over the ArrayList to extract the names.
        for (int i = 0; i < abilities.size(); i++) {
            abilitiesOptions[i] = abilities.get(i).getName();
        }

        skillsComboBox = new JComboBox<>(abilitiesOptions);
        skillsComboBox.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 16));
        skillsComboBox.setPreferredSize(new Dimension(100, 20));
        skillsComboBox.setBounds(280, 320, 150, 35);
        skillsComboBox.setBackground(Color.decode("#fae3a5"));

        skillsComboBox2 = new JComboBox<>(abilitiesOptions);
        skillsComboBox2.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 16));
        skillsComboBox2.setPreferredSize(new Dimension(100, 20));
        skillsComboBox2.setBounds(280, 370, 150, 35);
        skillsComboBox2.setBackground(Color.decode("#fae3a5"));

        JLabel btnCancel = new JLabel("<html><span style='background-color: #fae3a5; " +
                "color: #961805; padding: 10px;'><u>Cancel</html>");
        btnCancel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 30));
        btnCancel.setBounds(100, 420, 150, 50); // Establecer la posición y tamaño del JLabel
        btnCancel.addMouseListener (new cancelLabelActionListener(btnCancel));

        JLabel textCreate = new JLabel("<html><span style='background-color: #aedd05; " +
                "color: #000000; padding: 10px;'>+ Create</html>");
        textCreate.setFont(new Font("Segoe UI Emoji", Font.BOLD, 30));
        textCreate.setBounds(10, 0, 150, 50);

        JLabel btnCreate = new JLabel(new ImageIcon(CreateCharacter.class.getResource("/images/createCharacterButton.png")));
        btnCreate.setBounds(270, 420, 150, 50);
        btnCreate.addMouseListener (new createLabelActionListener(btnCreate, textCreate, nameTextField, healthTextField, attackTextField, defenseTextField, elementsComboBox, skillsComboBox, skillsComboBox2, photosComboBox, windowCreateChar));

        imgSquare.add(nameLabel);
        imgSquare.add(nameTextField);

        imgSquare.add(healthLabel);
        imgSquare.add(healthTextField);

        imgSquare.add(attackLabel);
        imgSquare.add(attackTextField);

        imgSquare.add(defenseLabel);
        imgSquare.add(defenseTextField);

        imgSquare.add(elementsLabel);
        imgSquare.add(elementsComboBox);

        imgSquare.add(imgCharacterPhoto);

        imgSquare.add(photosComboBox);
        imgSquare.add(imgFolder);

        imgSquare.add(skillsLabel);
        imgSquare.add(skillsComboBox);
        imgSquare.add(skillsComboBox2);

        imgSquare.add(btnCancel);
        btnCreate.add(textCreate);
        imgSquare.add(btnCreate);

        // Añadir el JLabel al panel con la imagen de fondo
        backgroundImageLabel.add(imgSquare);
        imgSquare2.add(labelCreate1);
        imgSquare2.add(labelCreate2);
        backgroundImageLabel.add(imgSquare2);

        windowCreateChar.revalidate();
        windowCreateChar.repaint();
    }

}
