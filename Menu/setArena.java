package Menu;

import models.*;
import models.Character;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class setArena extends JFrame {

    // ----- variables -----
    public int actualElement;
    private JLabel profileSpace, selectTowersMSG, profilePicture, namePlayer;
    private ImageIcon squareImg;
    Border border3 = BorderFactory.createLineBorder(Color.BLUE, 3);
    JLabel specialMSG1, damageSpecial1, manaSpecial1, specialMSG2, damageSpecial2, manaSpecial2;
    // ---------------------
    // ----- Arena -----
    private setImages arenaImages;
    private Arena arena;
    private letterFonts arenaFonts;
    private JFrame arenaWindow;
    private JPanel arenaPanel, actionsPanel, towerPanel, abilitiesPanel;
    // ---------------------
    // ----- Lists -----
    private ArrayList<square> arenaSquares;
    private ArrayList<Character> characters;
    private ArrayList<Tower> towers;
    private ArrayList<JPanel> characterPanelList;
    // ---------------------
    // ----- Buttons -----
    JButton normalAttack, moveCharacter, passTurn;
    JButton simpleAttack, specialAttack1, specialAttack2, backButton;
    private JButton towerCart;
    // ---------------------


    public setArena(JFrame newScreen, ArrayList<Character> listOfCharacters, ArrayList<Tower> listOfTowers, Arena mainArena, letterFonts newFonts){

        Random random = new Random();
        arenaWindow = newScreen;
        arenaWindow.setTitle("Arena"); //put a title to the window

        actualElement = random.nextInt(3);

        arenaWindow.revalidate();
        arenaWindow.repaint();

        arenaImages = new setImages();
        arenaImages.arenaButtonsImages();

        //initialize configuration
        characters = listOfCharacters;
        towers = listOfTowers;
        arenaFonts = newFonts;
        arena = mainArena;

        setComponents();

    }

    // ------------------------------------- Set components -------------------------------------

    private void setComponents(){

        setArenaPanel();
        arenaImages.player1Configuration(actualElement, arena.player1);
        setArenaSquares();
        setTowerCart();
        setArenaImages();
        towerInformationPanel();
        setActionsPanel();
        setAbilitiesPanel();
        characterPanels();
        setCharactersCart();

        //desactivate panels
        actionsPanel.setVisible(false);
        towerPanel.setVisible(false);
    }

    // ------------------------------------- Build the arena -------------------------------------

    private void setArenaPanel(){
        //set panel
        arenaPanel = new JPanel(); //Create a panel
        arenaPanel.setLayout(null);
        //arenaPanel.setBackground(Color.DARK_GRAY); //set color of the panel
        arenaPanel.setBounds(0, 0, 1000, 700);
        arenaWindow.getContentPane().add(arenaPanel); //put the panel on the window
    }

    private void setArenaImages(){

        //Profile selection space
        profileSpace = new JLabel(new ImageIcon(arenaImages.profileImg));
        arenaPanel.add(profileSpace);
        profileSpace.setBounds(0, 0, 250, 700);

        //User profile
        profilePicture = new JLabel(arenaImages.playerImg);
        profileSpace.add(profilePicture);
        profilePicture.setBounds(50, 30, 150, 150);
        profilePicture.setBorder(border3);

        // Subpanel for the name
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); //center the text
        namePanel.setBounds(0, 190, 250, 30);
        namePanel.setOpaque(false);

        //Name label
        namePlayer = new JLabel(arena.player1.name);
        namePlayer.setFont(arenaFonts.font4);  // Set custom font
        namePlayer.setForeground(Color.WHITE);  // Set text color for the title
        namePanel.add(namePlayer);

        profileSpace.add(namePanel);

        //Background
        ImageIcon originalImage = new ImageIcon(arenaImages.backGroundImg); // Charge the image
        Image image = originalImage.getImage();
        Image scaledImage = image.getScaledInstance(750, 700,  java.awt.Image.SCALE_SMOOTH); // Resize the image
        //Add the image to the arena panel
        JLabel waterArenaImage = new JLabel(new ImageIcon(scaledImage));
        arenaPanel.add(waterArenaImage);
        waterArenaImage.setBounds(250, 0, 750, 700);

    }

    private void setArenaSquares(){

        //setting the image of the button
        squareImg = new ImageIcon(arenaImages.squaresElementImg);

        int locationX = 325;
        int locationY = 50;

        for(int i=1; i<101; i++) {
            if ((i-1)%10 == 0 && i-1 != 0){ //to change the row
                locationY += 60;
                locationX = 325; //reset the column
            }

            JButton square = new JButton();
            square.setBounds(locationX, locationY, 60, 60);
            square.setIcon(squareImg);
            square.setDisabledIcon(new ImageIcon(arenaImages.squareOff));

            addSquare(square, i-1);
            arenaPanel.add(square);
            locationX += 60;
        }
    }

    // ------------------------------------- Towers -------------------------------------

    private void setTowerCart(){
        towerCart = new JButton();
        towerCart.setBounds(65, 375, 120, 160);
        towerCart.setIcon(arenaImages.squareImg);
        towerCart.setEnabled(true);

        //Text
        selectTowersMSG = new JLabel("Select your towers!");
        selectTowersMSG.setFont(arenaFonts.font4);  // Set custom font
        selectTowersMSG.setBounds(35, 320, 375, 50);
        selectTowersMSG.setForeground(Color.WHITE);  // Set text color for the title
        arenaPanel.add(selectTowersMSG);
        arenaPanel.add(towerCart);

    }

    // ------------------------------------- Select Characters -------------------------------------

    private void characterPanels(){

        characterPanelList = new ArrayList<JPanel>();

        for (Character current : characters) {
            //create new panel
            JPanel temporalPanel = new JPanel(); //Create a panel
            temporalPanel.setLayout(null);
            temporalPanel.setVisible(false);
            temporalPanel.setOpaque(false);

            temporalPanel.setBounds(0, 270, 250, 430);
            profileSpace.add(temporalPanel); //put the panel on the window
            characterPanelList.add(temporalPanel); //save the panel in a list of panels
        }
    }

    private void setCharactersCart(){
        //Text
        JLabel selectCharacterMSG = new JLabel("Select a character!");
        selectCharacterMSG.setFont(arenaFonts.font4);  // Set custom font
        selectCharacterMSG.setBounds(35, 10, 375, 50);
        selectCharacterMSG.setForeground(Color.WHITE);  // Set text color for the title
        characterPanelList.get(0).add(selectCharacterMSG);

        //characters cart
        int posX = 25;
        int posY = 80;
        for (Character current : characters){

            //create the button for the characters
            JButton temporalButton = new JButton();
            temporalButton.setBounds(posX, posY, 50, 50);
            temporalButton.setIcon(new ImageIcon(current.photoFileName.getImage().getScaledInstance(temporalButton.getWidth(), temporalButton.getHeight(), Image.SCALE_SMOOTH)));
            temporalButton.setEnabled(true);
            current.button = temporalButton;
            characterPanelList.get(0).add(current.button);

            if (posX == 175){
                posX = 25; //reset the X coordenate
                posY += 75; //change row
            }
            else
                posX += 75;
        }
    }

    // ------------------------------------- PANEL OF ABILITIES -------------------------------------

    private void setActionsPanel() {
        //set actions panel
        actionsPanel = new JPanel(); //Create a panel
        actionsPanel.setLayout(null);
        //arenaPanel.setBackground(Color.DARK_GRAY); //set color of the panel
        actionsPanel.setBounds(0, 370, 250, 430);
        //actionsPanel.setVisible(false);
        actionsPanel.setOpaque(false);

        //Attack Button
        normalAttack = new JButton();
        normalAttack.setBounds(25, 40, 200, 50);
        normalAttack.setIcon(arenaImages.normalAttack);
        normalAttack.setRolloverIcon(arenaImages.normalAttack2);
        normalAttack.setBorderPainted(false); // Remove the border from the botton
        actionsPanel.add(normalAttack); //put the panel on the window
        //Move Character Button
        moveCharacter = new JButton();
        moveCharacter.setBounds(25, 100, 200, 50);
        moveCharacter.setIcon(arenaImages.moveButton);
        moveCharacter.setRolloverIcon(arenaImages.moveButton2);
        moveCharacter.setBorderPainted(false); // Remove the border from the botton
        actionsPanel.add(moveCharacter); //put the panel on the window
        //Skip Turn Button
        passTurn = new JButton();
        passTurn.setBounds(25, 160, 200, 50);
        passTurn.setIcon(arenaImages.passTurn);
        passTurn.setRolloverIcon(arenaImages.passTurn2);
        passTurn.setBorderPainted(false); // Remove the border from the botton
        actionsPanel.add(passTurn); //put the panel on the window

        profileSpace.add(actionsPanel);
    }

    private void setAbilitiesPanel(){

        //-------------> Abilities panel
        abilitiesPanel = new JPanel(); //Create a panel
        abilitiesPanel.setLayout(null);
        //arenaPanel.setBackground(Color.DARK_GRAY); //set color of the panel
        abilitiesPanel.setBounds(0, 240, 250, 430);
        //actionsPanel.setVisible(false);
        abilitiesPanel.setOpaque(false);

        //Text basic attack
        JLabel basicAttackMSG = new JLabel("Simple Attack");
        basicAttackMSG.setFont(arenaFonts.font5);  // Set custom font
        basicAttackMSG.setBounds(115, 30, 375, 50);
        basicAttackMSG.setForeground(Color.WHITE);  // Set text color for the title
        abilitiesPanel.add(basicAttackMSG);
        // //
        JLabel damageBasic = new JLabel("Damage: 100");
        damageBasic.setFont(arenaFonts.font5);  // Set custom font
        damageBasic.setBounds(115, 50, 375, 50);
        damageBasic.setForeground(Color.WHITE);  // Set text color for the title
        abilitiesPanel.add(damageBasic);
        // //
        JLabel manaBasic = new JLabel("Mana: 20");
        manaBasic.setFont(arenaFonts.font5);  // Set custom font
        manaBasic.setBounds(115, 70, 375, 50);
        manaBasic.setForeground(Color.WHITE);  // Set text color for the title
        abilitiesPanel.add(manaBasic);

        //Text special attack #1
        specialMSG1 = new JLabel("Special 1");
        specialMSG1.setFont(arenaFonts.font5);  // Set custom font
        specialMSG1.setBounds(115, 130, 375, 50);
        specialMSG1.setForeground(Color.WHITE);  // Set text color for the title
        abilitiesPanel.add(specialMSG1);
        // //
        damageSpecial1 = new JLabel("Damage: ");
        damageSpecial1.setFont(arenaFonts.font5);  // Set custom font
        damageSpecial1.setBounds(115, 150, 375, 50);
        damageSpecial1.setForeground(Color.WHITE);  // Set text color for the title
        abilitiesPanel.add(damageSpecial1);
        // //
        manaSpecial1 = new JLabel("Mana: ");
        manaSpecial1.setFont(arenaFonts.font5);  // Set custom font
        manaSpecial1.setBounds(115, 170, 375, 50);
        manaSpecial1.setForeground(Color.WHITE);  // Set text color for the title
        abilitiesPanel.add(manaSpecial1);

        //Text special attack #2
        specialMSG2 = new JLabel("Special 2");
        specialMSG2.setFont(arenaFonts.font5);  // Set custom font
        specialMSG2.setBounds(115, 230, 375, 50);
        specialMSG2.setForeground(Color.WHITE);  // Set text color for the title
        abilitiesPanel.add(specialMSG2);
        // //
        damageSpecial2 = new JLabel("Damage: ");
        damageSpecial2.setFont(arenaFonts.font5);  // Set custom font
        damageSpecial2.setBounds(115, 250, 375, 50);
        damageSpecial2.setForeground(Color.WHITE);  // Set text color for the title
        abilitiesPanel.add(damageSpecial2);
        // //
        manaSpecial2 = new JLabel("Mana: ");
        manaSpecial2.setFont(arenaFonts.font5);  // Set custom font
        manaSpecial2.setBounds(115, 270, 375, 50);
        manaSpecial2.setForeground(Color.WHITE);  // Set text color for the title
        abilitiesPanel.add(manaSpecial2);


        //Simple Attack Button
        simpleAttack = new JButton();
        simpleAttack.setBounds(25, 40, 80, 80);
        simpleAttack.setIcon(arena.getAbilitiesList().getFirst().getImage());
        abilitiesPanel.add(simpleAttack); //put the panel on the window
        //Special Attack #1 Button
        specialAttack1 = new JButton();
        specialAttack1.setBounds(25, 140, 80, 80);
        abilitiesPanel.add(specialAttack1); //put the panel on the window
        ////Special Attack #2 Button
        specialAttack2 = new JButton();
        specialAttack2.setBounds(25, 240, 80, 80);
        abilitiesPanel.add(specialAttack2); //put the panel on the window

        //Back button
        backButton = new JButton();
        backButton.setLayout(null);
        backButton.setBounds(37, 350, 175, 35);
        backButton.setIcon((new ImageIcon(new ImageIcon("src/images/back.png").getImage().getScaledInstance(backButton.getWidth(), backButton.getHeight(), Image.SCALE_SMOOTH))));
        backButton.setRolloverIcon((new ImageIcon(new ImageIcon("src/images/back2.png").getImage().getScaledInstance(backButton.getWidth(), backButton.getHeight(), Image.SCALE_SMOOTH))));;
        backButton.setBorderPainted(false); // Remove the border from the botton
        abilitiesPanel.add(backButton);

        abilitiesPanel.setVisible(false);
        profileSpace.add(abilitiesPanel);

    }

    // ------------------------------------- Tower information panel -------------------------------------
    private void towerInformationPanel(){
        //set panel
        towerPanel = new JPanel(); //Create a panel
        towerPanel.setLayout(null);
        towerPanel.setBounds(0, 270, 250, 430);
        towerPanel.setOpaque(false);

        //Text
        JLabel towerMSG = new JLabel("Tower Information");
        towerMSG.setFont(arenaFonts.font4);  // Set custom font
        towerMSG.setBounds(40, 10, 375, 50);
        towerMSG.setForeground(Color.WHITE);  // Set text color for the title
        towerPanel.add(towerMSG);

        profileSpace.add(towerPanel);
    }

    // ---------------> Add square method
    public void addSquare(JButton newButton, int temporalId){
        //add a new square to the list of squares

        if(arenaSquares == null){
            arenaSquares = new ArrayList<square>();
            arenaSquares.add(new square(0, newButton, temporalId));
        }
        else {
            square newSquare = new square(0, newButton, temporalId);
            arenaSquares.add(newSquare);
        }
    }

    // ------------------------------------- Setters -------------------------------------

    public JFrame getScreen(){return arenaWindow;}
    public JPanel getArenaPanel(){return arenaPanel;}
    public ArrayList<square> getArenaSquares(){return arenaSquares;}
    public JButton getTowerCart(){return towerCart;}
    public ArrayList<Character> getCharacters() {return characters;}
    public setImages getArenaImages(){return arenaImages;}
    public Arena getArena(){return arena;}
    public JLabel getProfileSpace(){return profileSpace;}
    public JLabel getProfilePicture(){return profilePicture;}
    public JLabel getNamePlayer(){return namePlayer;}
    public JLabel getSelectTowersMSG(){return selectTowersMSG;}
    public ArrayList<JPanel> getCharactersPanel(){return characterPanelList;}
    public JButton getNormalAttack(){return normalAttack;}
    public JButton getMoveCharacter(){return moveCharacter;}
    public JButton getPassTurn(){return passTurn;}
    public JPanel getActionsPanel(){return actionsPanel;}
    public ArrayList<Tower> getTowers(){return towers;}
    public JPanel getTowerPanel(){return towerPanel;}
    public JPanel getAbilitiesPanel(){return abilitiesPanel;}
    public JButton getSimpleAttack() {return simpleAttack;}
    public JButton getSpecialAttack1(){return specialAttack1;}
    public JButton getSpecialAttack2() {return specialAttack2;}
    public JLabel getSpecialMSG1() {return specialMSG1;}
    public JLabel getDamageSpecial1() {return damageSpecial1;}
    public JLabel getManaSpecial1() {return manaSpecial1;}
    public JLabel getSpecialMSG2() {return specialMSG2;}
    public JLabel getDamageSpecial2() {return damageSpecial2;}
    public JLabel getManaSpecial2() {return manaSpecial2;}
    public JButton getBackButton(){return backButton;}
}
