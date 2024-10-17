package Menu;

import models.*;
import models.Character;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Actions {

    // ----- variables -----
    private setArena mainArena;
    private ArrayList<square> arenaSquares;
    private int numberTowers = 0, numberCharacters = 0, actualTurn = 1, actualElement;
    private JLabel profileSpace, selectTowersMSG, profilePicture, namePlayer,
            characterInformation, currentMana, currentLevel, currentHealth, currentAttack, characterProfile;
    JLabel specialMSG1, damageSpecial1, manaSpecial1, specialMSG2, damageSpecial2, manaSpecial2;
    private Character currentCharacterSelected, eliminatedCharacter;
    private ImageIcon squareImg;
    // ---------------------
    // ----- Arena -----
    private setImages arenaImages;
    private Arena arena;
    private letterFonts arenaFonts;
    private JFrame arenaWindow;
    private JPanel arenaPanel, actionsPanel, towerPanel, abilitiesPanel;
    private winMenu winnerScreen;
    // ---------------------
    //----- Lists -----
    private ArrayList<Character> characters;
    private ArrayList<Tower> towers;
    private ArrayList<JPanel> characterPanelList;
    private ArrayList<Tower> player1Towers;
    private ArrayList<Tower> player2Towers;
    // ---------------------
    //----- Borders -----
    Border border1 = BorderFactory.createLineBorder(Color.BLUE, 1);
    Border border2 = BorderFactory.createLineBorder(Color.RED, 1);
    Border border3 = BorderFactory.createLineBorder(Color.BLUE, 3);
    Border border4 = BorderFactory.createLineBorder(Color.RED, 3);
    Border border5 = BorderFactory.createLineBorder(Color.GREEN, 4);
    // ---------------------
    // ----- Buttons -----
    private JButton towerCart;
    JButton normalAttack, moveCharacter, passTurn;
    JButton simpleAttack, specialAttack1, specialAttack2, backButton;
    // ---------------------
    // ----- Action Listeners -----
    ActionListener selectPersonalTowers, selectEnemieTowers;
    // ---------------------

    public Actions(setArena newArena, winMenu newWinner, int actualElement){
        this.actualElement = actualElement;
        mainArena = newArena;
        winnerScreen = newWinner;
        initializeVariables();
        defineActionListeners();
        initializeActions();
    }

    // ------------------------------------- Initialize actions -------------------------------------
    public void initializeActions(){

        player1Towers = new ArrayList<>();
        player2Towers = new ArrayList<>();

        //squares
        halfSquaresState(false, 2);
        for(square current : arenaSquares){
            ActionListener temporalAction = placeTowers(current);
            current.newActionListener(temporalAction);
        }

        //tower cart
        towerCart.addActionListener(selectPersonalTowers);

        //characters cart
        for (Character current : characters) {

            current.button.addActionListener(selectCharacterButton(current));
        }

        backButton.addActionListener(e -> backAbilities());
        eliminatedCharacter = null;
        chargeCharacterInformation();

    }

    private void showCharacters(){

        squareCharacter();

        characterPanelList.getFirst().setVisible(true);
        profileSpace.revalidate();
        profileSpace.repaint();
    }

    // ------------------------------------- General Methods -------------------------------------

    private void rechargeImages(){
        //Profile cart
        towerCart.setIcon(arenaImages.squareImg);
        profileSpace.setIcon(new ImageIcon(arenaImages.profileImg));
        profilePicture.setIcon(arenaImages.playerImg);
        if(actualTurn == 1)
            profilePicture.setBorder(border3);
        else
            profilePicture.setBorder(border4);
        arenaWindow.revalidate();
        arenaWindow.repaint();
        //name of the player
        if(actualTurn == 2)
            namePlayer.setText(arena.player2.name);
        else
            namePlayer.setText(arena.player1.name);
        namePlayer.revalidate();
        namePlayer.repaint();

        checkTowers();
        checkCharacters();
    }

    public void halfSquaresState(boolean cambio, int halfArena){
        //activate or deactivate half of the arena buttons/squares

        int halfStart;
        int halfEnd;

        if(halfArena == 1){
            halfStart = 50;
            halfEnd = 99;
        }
        else {
            halfStart = 0;
            halfEnd = 49;
        }

        for (int i = 0; i<100; i++) {
            if (i >= halfStart && i <= halfEnd)
                arenaSquares.get(i).button.setEnabled(cambio);
        }
    }

    private void cleanActionListeners(){
        //delete the action listeners from the squares

        for(int i = 0; i<100; i++)
            for (ActionListener al : arenaSquares.get(i).button.getActionListeners())
                arenaSquares.get(i).button.removeActionListener(al);
    }

    private void cleanRollOverImage(){
        //set the rollOver icon from every square to null
        for(square current : arenaSquares)
            current.button.setRolloverIcon(null);
        normalAttack.setIcon(arenaImages.normalAttack);
        moveCharacter.setIcon(arenaImages.moveButton);

        normalAttack.repaint();
        moveCharacter.repaint();
    }

    public void squareCharacter(){

        for(int i = 0; i<100; i++)
            arenaSquares.get(i).action = placeCharacters(arenaSquares.get(i).id);
    }

    private void rechargeActions(){
        //restore the action listeners to the squares

        for(int i = 0; i<100; i++) {
            arenaSquares.get(i).button.addActionListener(arenaSquares.get(i).action);
        }
    }
    private void chargeCharactersActions(){
        //activate functions for the characters
        for (Character current : arena.characters){
            if(current.getHealth() == 0){
                continue;
            }
            current.square.action = showOptions(current.square);
            if(actualTurn == 1 && current.team == 1)
                current.square.button.addActionListener(current.square.action);
            else if(actualTurn == 2 && current.team == 2)
                current.square.button.addActionListener(current.square.action);
        }

        //activate functions for the towers
        for (Tower current : towers){
            if(current.getState()) {
                current.towerSquare.action = showOptions(current.towerSquare);
                if (actualTurn == 1 && current.getTeam() == 1)
                    current.towerSquare.button.addActionListener(current.towerSquare.action);
                else if (actualTurn == 2 && current.getTeam() == 2)
                    current.towerSquare.button.addActionListener(current.towerSquare.action);
            }
        }
    }

    private void deleteCharactersActions(){
        for (Character current : arena.characters) {
            for (ActionListener al : current.square.button.getActionListeners())
                current.square.button.removeActionListener(al);
        }
    }

    private void chargeCharacterInformation(){

        //Character Image
        characterProfile = new JLabel();
        characterProfile.setBounds(25, 290, 80, 80);
        characterProfile.setIcon(new ImageIcon("src\\images\\abilitiesImages\\jellyFish.png"));
        profileSpace.add(characterProfile); //put the panel on the window
        //Information label
        characterInformation = new JLabel("Information");
        characterInformation.setFont(arenaFonts.font5);  // Set custom font
        characterInformation.setForeground(Color.YELLOW);  // Set text color for the title
        characterInformation.setBounds(115, 285, 150, 20);
        profileSpace.add(characterInformation);
        //Level label
        currentLevel = new JLabel("Level: ");
        currentLevel.setFont(arenaFonts.font6);  // Set custom font
        currentLevel.setForeground(Color.WHITE);  // Set text color for the title
        currentLevel.setBounds(115, 310, 100, 20);
        profileSpace.add(currentLevel);
        //Health label
        currentHealth = new JLabel("Health: ");
        currentHealth.setFont(arenaFonts.font6);  // Set custom font
        currentHealth.setForeground(Color.WHITE);  // Set text color for the title
        currentHealth.setBounds(115, 330, 100, 20);
        profileSpace.add(currentHealth);
        //Maná label
        currentMana = new JLabel("Mana: ");
        currentMana.setFont(arenaFonts.font6);  // Set custom font
        currentMana.setForeground(Color.WHITE);  // Set text color for the title
        currentMana.setBounds(115, 350, 100, 20);
        profileSpace.add(currentMana);

        hideInformation();
    }
    private void rechargeInformation(Character currentCharacter){
        characterProfile.setIcon(new ImageIcon(currentCharacter.photoFileName.getImage().getScaledInstance(characterProfile.getWidth(), characterProfile.getHeight(), Image.SCALE_SMOOTH)));
        characterInformation.setText(currentCharacter.name);
        currentLevel.setText("Level: "+currentCharacter.level);
        currentHealth.setText("Health: "+currentCharacter.health);
        currentMana.setText("Mana: "+currentCharacter.mana);
    }

    private void activateInformation(){
        characterProfile.setVisible(true);
        characterInformation.setVisible(true);
        currentLevel.setVisible(true);
        currentHealth.setVisible(true);
        currentMana.setVisible(true);
//        currentAttack.setVisible(true);
    }
    private void hideInformation(){
        characterProfile.setVisible(false);
        characterInformation.setVisible(false);
        currentLevel.setVisible(false);
        currentHealth.setVisible(false);
        currentMana.setVisible(false);
//        currentAttack.setVisible(false);
    }

    private void showAbilities(Character characterSelected){
        //set panel configuration temporally
        specialAttack1.setIcon(characterSelected.abilitiesList.get(1).getImage());
        specialAttack2.setIcon(characterSelected.abilitiesList.get(2).getImage());
        specialMSG1.setText(characterSelected.abilitiesList.get(1).getName());
        damageSpecial1.setText("Damage: " + characterSelected.abilitiesList.get(1).getDamage());
        manaSpecial1.setText("Mana: " + characterSelected.abilitiesList.get(1).getManaExpense());
        specialMSG2.setText(characterSelected.abilitiesList.get(2).getName());
        damageSpecial2.setText("Damage: " + characterSelected.abilitiesList.get(2).getDamage());
        manaSpecial2.setText("Mana: " + characterSelected.abilitiesList.get(2).getManaExpense());
        abilitiesPanel.setVisible(true);

        for (ActionListener al : simpleAttack.getActionListeners())
            simpleAttack.removeActionListener(al);
        for (ActionListener al : specialAttack1.getActionListeners())
            specialAttack1.removeActionListener(al);
        for (ActionListener al : specialAttack2.getActionListeners())
            specialAttack2.removeActionListener(al);

        simpleAttack.addActionListener(normalAttackOption(characterSelected, 0));
        specialAttack1.addActionListener(normalAttackOption(characterSelected, 1));
        specialAttack2.addActionListener(normalAttackOption(characterSelected, 2));
        //hide old information
        actionsPanel.setVisible(false);
        hideInformation();
        cleanRollOverImage();
        actionsPanel.setVisible(false);

    }

    private boolean lastDigit(int number, int digit){
        //function that return if a number is finished in one
        return number % 10 == digit || number == digit;
    }

    private int[] availablesSquares(int characterId){
        //function that return the available squares for a character (to move or attack)
        int[] offsets;
        if (characterId == 0) //first corner
            offsets = new int[]{+1,+10,+11};
        else if (characterId == 9) //up right corner
            offsets = new int[]{-1, +10, +9};
        else if (characterId == 90) //down left corner
            offsets = new int[]{+1,-9, -10,};
        else if(characterId == 99) //last corner
            offsets = new int[]{-1,-10,-11};
        else if (lastDigit(characterId, 0 )) //first column
            offsets = new int[]{+1, -10, +10, -9, +11};
        else if(lastDigit(characterId, 9)) //last column
            offsets = new int[]{-1, -10, +10, -11, +9};
        else if (characterId <10) //first row
            offsets = new int[]{-1, +1, +10, +11, +9};
        else if (characterId > 90) //last row
            offsets = new int[]{-1, +1, -10, -11, -9};
        else
            offsets = new int[]{-1, +1, -10, +10, -11, -9, +11, +9};
        return offsets;
    }

    // ------------------------------------- Action Listeners -------------------------------------

    public void defineActionListeners(){

        selectPersonalTowers = selectTowers(1);
        selectEnemieTowers = selectTowers(2);
        //passTurnOption =
    }

    // ------------------------------------- Place Towers  -------------------------------------

    private ActionListener placeTowers(square selectSquare){
        //creating the action of place the towers in the arena
        ActionListener putTowerAction = e -> {


            if(selectSquare.objectIn != 0){
                int cero; //aquí debe ir un mensaje que diga "ya hay una torre ahí"
            }
            else {
                //setting the image of the square
                ImageIcon towerSquare = new ImageIcon(arenaImages.towerSquareImg);
                selectSquare.button.setIcon(new ImageIcon(towerSquare.getImage().getScaledInstance(selectSquare.button.getWidth(), selectSquare.button.getHeight(), Image.SCALE_SMOOTH)));
                selectSquare.objectIn = 1;

                if (numberTowers < 3) {
                    selectSquare.tower = towers.get(numberTowers);
                    //selectSquare.tower.location = selectSquare.id;
                    towers.get(numberTowers).towerSquare = selectSquare;
                    towers.get(numberTowers).button = selectSquare.button;
                    selectSquare.button.setBorder(border1);
                    player1Towers.add(towers.get(numberTowers));
                }
                else {
                    selectSquare.tower = towers.get(numberTowers);
                    towers.get(numberTowers).towerSquare = selectSquare;
                    towers.get(numberTowers).button = selectSquare.button;
                    //selectSquare.tower.location = selectSquare.id;
                    selectSquare.button.setBorder(border2);
                    player2Towers.add(towers.get(numberTowers));
                }
                numberTowers++;

            }

            if(numberTowers == 3){ //set player 2 towers
                halfSquaresState(false, 1);
                halfSquaresState(true, 2);
                towerCart.removeActionListener(selectPersonalTowers); //remove the action listener
                towerCart.addActionListener(selectEnemieTowers);
                arenaImages.player2Configuration(actualElement, arena.player2);
                actualTurn = 2;
                rechargeImages();
            }
            else if (numberTowers == 6){ //end the set of the towers
                halfSquaresState(true, 1);
                halfSquaresState(false, 2);
                towerCart.removeActionListener(selectPersonalTowers); //remove the action listener
                arenaPanel.remove(towerCart);
                arenaPanel.remove(selectTowersMSG);
                arenaWindow.revalidate();
                arenaPanel.repaint();
                arenaImages.player1Configuration(actualElement, arena.player1);
                actualTurn = 1;
                cleanActionListeners();
                rechargeImages();
                showCharacters();
            }

            cleanActionListeners();
        };
        return putTowerAction;
    }


    private ActionListener selectTowers(int playersTurn){
        //Activate half of the arena to place the towers
        return _ -> {

            rechargeActions();
            halfSquaresState(true, playersTurn);
        };
    }

    // ------------------------------------- Place Characters  -------------------------------------

    private ActionListener placeCharacters(int buttonId) {
        //creating the action of place the characters in the arena
        ActionListener putCharacterAction = e -> {

            int tempIndex = 0;

            //find the square selected
            while (arenaSquares.get(tempIndex).id != buttonId) {
                tempIndex++;
            }

            if (arenaSquares.get(tempIndex).objectIn != 0) {
                //aquí debe ir un mensaje que diga "ya hay una torre ahí"
            } else {

                //saving the character in the square
                arena.characters.add(currentCharacterSelected);
                currentCharacterSelected.square = arenaSquares.get(tempIndex);
                arenaSquares.get(tempIndex).character = currentCharacterSelected;
                arenaSquares.get(tempIndex).button.setIcon(new ImageIcon(currentCharacterSelected.photoFileName.getImage().getScaledInstance(arenaSquares.get(tempIndex).button.getWidth(), arenaSquares.get(tempIndex).button.getHeight(), Image.SCALE_SMOOTH)));

                //Defining characters team
                if (numberCharacters == 0 || numberCharacters == 2 || numberCharacters == 4) {
                    arenaSquares.get(tempIndex).character.team = 1;
                    arenaSquares.get(tempIndex).button.setBorder(border1);
                }
                else {
                    arenaSquares.get(tempIndex).character.team = 2;
                    arenaSquares.get(tempIndex).button.setBorder(border2);
                }
                //change state of the game
                arenaSquares.get(tempIndex).objectIn = 1;
                numberCharacters++;

                //temporalSquare.character = new Character();

                cleanActionListeners();

                if (numberCharacters == 6) { //-----> All characters selected
                    halfSquaresState(true, 1);
                    characterPanelList.getFirst().setVisible(false); //desactivate the characters panel

                    actualTurn = arenaImages.changeTurn(2, actualElement, arena.player1, arena.player2);
                    rechargeImages();
                    profileSpace.revalidate();
                    profileSpace.repaint();
                    //-----> Change turn
                    deleteCharactersActions();
                    chargeCharactersActions();

                }
                else{
                    //-----> Change turn
                    halfSquaresState(false, actualTurn);
                    actualTurn = arenaImages.changeTurn(actualTurn, actualElement, arena.player1, arena.player2);
                    halfSquaresState(true, actualTurn);
                    currentCharacterSelected.button.setEnabled(false);
                }
                rechargeImages();
            }

        };
        return putCharacterAction;
    }


    private ActionListener selectCharacterButton(models.Character characterSelected){
        //Activate half of the arena to place the characters
        ActionListener selectedCharacter = e -> {

            currentCharacterSelected = characterSelected; //save the selected character
            rechargeActions();
        };
        return selectedCharacter;
    }

    private void checkTowers(){
        int deaths1 = 0;
        int deaths2 = 0;
        for (Tower current : player1Towers){
            if (current.health == 0 && current.getState()) {
                current.towerSquare.tower = null;
                current.towerSquare.objectIn = 0;
                current.button.setIcon(squareImg);
                current.button.setBorder(null);
                current.changeState();
                arena.player2.statistics.addDestroyedTower();
            }
            if(!current.getState()){
                deaths1++;
            }
        }
        for (Tower current : player2Towers){
            if (current.health == 0 && current.getState()) {
                current.towerSquare.tower = null;
                current.towerSquare.objectIn = 0;
                current.button.setIcon(squareImg);
                current.button.setBorder(null);
                current.changeState();
                arena.player1.statistics.addDestroyedTower();
            }
            if(!current.getState()){
                deaths2++;
            }
        }
        if(deaths1 == 3)
            activateWinner(arena.player2);
        else if(deaths2 == 3)
            activateWinner(arena.player1);
    }

    private void checkCharacters(){
        replacedCharacter(eliminatedCharacter);
        for(Character temp : characters){
            if(temp.getHealth() == 0){
                temp.square.button.setIcon(squareImg);
                temp.square.character = null;
                temp.square.objectIn = 0;
                temp.square.button.setBorder(null);
                temp.square = null;
                eliminatedCharacter = temp;
                if(temp.getTeam() == 1)
                    arena.player2.statistics.addDefeatedEnemies();
                else
                    arena.player1.statistics.addDefeatedEnemies();
            }

        }
    }

    private void replacedCharacter(Character tempCharacter){

        if(tempCharacter == null){
            return;
        }
        for(Tower tower : towers){
            if((tower.getTeam() == tempCharacter.getTeam()) && tower.getState()) {
                int[] offsets = availablesSquares(tower.towerSquare.id);
                for(int offset : offsets){
                    if(arenaSquares.get(tower.towerSquare.id+offset).objectIn != 1){
                        tempCharacter.square = arenaSquares.get(tower.towerSquare.id+offset);
                        tempCharacter.square.objectIn = 1;
                        tempCharacter.square.character = tempCharacter;
                        tempCharacter.square.button.setIcon((new ImageIcon(tempCharacter.photoFileName.getImage().getScaledInstance(tempCharacter.square.button.getWidth(), tempCharacter.square.button.getHeight(), Image.SCALE_SMOOTH))));
                        tempCharacter.restoreInformation();
                        eliminatedCharacter = null;
                        return;
                    }
                }
            }
        }
    }
    // ------------------------------------- SHOW ACTIONS OF THE CHARACTERS  -------------------------------------

    private ActionListener showOptions(square selectedSquare){

        ActionListener objectAction = _ -> {
        };

        if (selectedSquare.tower != null) {
            objectAction= _ -> {
                //remove all borders
                for (models.Character current : arena.characters){
                    if (current.square == null)
                        continue;
                    current.square.button.setBorder(null);
                }
                towerPanel.setVisible(true);
                hideInformation();

            };
        }
        else if (selectedSquare.character != null){
            objectAction= _ -> {
                //turn of panels
                towerPanel.setVisible(false);
                abilitiesPanel.setVisible(false);
                //remove all borders
                for (models.Character current : arena.characters){
                    if(current.square == null)
                        continue;
                    current.square.button.setBorder(null);
                }
                //remove all roll over images
                cleanRollOverImage();
                rechargeInformation(selectedSquare.character);
                activateInformation();
                arenaWindow.revalidate();
                arenaWindow.repaint();
                actionsPanel.setVisible(true);
                selectedSquare.button.setBorder(border5);

                //Normal Attack Button configuration
                for (ActionListener al : normalAttack.getActionListeners())
                    normalAttack.removeActionListener(al); //remove old action listeners
                normalAttack.addActionListener(e -> showAbilities(selectedSquare.character));
                //Move button configuration
                for (ActionListener al : moveCharacter.getActionListeners())
                    moveCharacter.removeActionListener(al); //remove old action listeners
                moveCharacter.addActionListener(moveOption(selectedSquare.character));
                //Pass button configuration
                for (ActionListener al : passTurn.getActionListeners())
                    passTurn.removeActionListener(al); //remove old action listeners
                passTurn.addActionListener(e->passTurnMethod());

                profileSpace.revalidate();
                profileSpace.repaint();
            };
        }
        return objectAction;
    }

    // ------------------------------------- NORMAL ATTACK -------------------------------------

    private ActionListener normalAttackOption(models.Character character, int index){

        int characterId = character.square.id;

        return _ -> {

            cleanRollOverImage();
            cleanActionListeners();

            int[] offsets = availablesSquares(characterId);

            for (int offset : offsets) {
                int newId = characterId + offset;
                arenaSquares.get(newId).button.addActionListener(takeDamage(character, newId, index));
            }
            //show the attack square when the mouse roll over
            for (int offset : offsets) {
                int newId = characterId + offset;
                if ((arenaSquares.get(newId).tower != null && arenaSquares.get(newId).tower.team != character.team) || (arenaSquares.get(newId).character != null && arenaSquares.get(newId).character.team != character.team))
                    arenaSquares.get(newId).button.setRolloverIcon((new ImageIcon(arenaImages.attackSquare.getImage().getScaledInstance(arenaSquares.getFirst().button.getWidth(), arenaSquares.getFirst().button.getHeight(), Image.SCALE_SMOOTH))));
            }
        };
    }

    private ActionListener takeDamage(models.Character character, int objective, int tipoAtaque) {

        return _ -> {
            //---> check if there's an enemie
            if (arenaSquares.get(objective).character != null && arenaSquares.get(objective).character.team != actualTurn) {
                //reduce enemie health
                arenaSquares.get(objective).character.reduceHealth(character.abilitiesList.get(tipoAtaque).getDamage());
                //reduce mana
                character.reduceMana(character.abilitiesList.get(tipoAtaque).getManaExpense());
                //increase the level of the character if it finishes a character
                if(arenaSquares.get(objective).character.getHealth() == 0)
                    character.increaseLevel();
                //Change of turn
                passTurnMethod();
            }
            else if (arenaSquares.get(objective).tower != null && arenaSquares.get(objective).tower.team != actualTurn){
                //reduce enemie tower health
                arenaSquares.get(objective).tower.reduceHealth(character.abilitiesList.get(tipoAtaque).getDamage());
                //reduce mana
                character.reduceMana(character.abilitiesList.get(tipoAtaque).getManaExpense());
                //increase the level of the character if it finishes a tower
                if(arenaSquares.get(objective).tower.getHealth() == 0)
                    character.increaseLevel();
                //Change of turn
                passTurnMethod();
            }

        };
    }


    // ------------------------------------- MOVE ACTION -------------------------------------

    private ActionListener moveOption(models.Character character){

        int characterId = character.square.id;

        ActionListener moveAction = _ -> {

            cleanRollOverImage();
            cleanActionListeners();
            moveCharacter.setIcon(arenaImages.moveButton2);
            int[] offsets = availablesSquares(characterId);

            for (int offset : offsets) {
                int newId = characterId + offset;
                arenaSquares.get(newId).button.addActionListener(newCharacterPosition(character, newId));
            }
            //show the movement square when the mouse roll over
            for (int offset : offsets) {
                int newId = characterId + offset;
                if (arenaSquares.get(newId).tower == null && arenaSquares.get(newId).character == null) {
                    arenaSquares.get(newId).button.setRolloverIcon(arenaImages.moveSquare);
                }
            }
        };

        return moveAction;
    }

    private ActionListener newCharacterPosition(Character character, int newPositon){

        return e -> {
            if(arenaSquares.get(newPositon).character == null && arenaSquares.get(newPositon).tower == null ) {
                //reorganize old and new location
                arenaSquares.get(newPositon).button.setIcon(new ImageIcon(character.photoFileName.getImage().getScaledInstance(arenaSquares.get(newPositon).button.getWidth(), arenaSquares.get(newPositon).button.getHeight(), Image.SCALE_SMOOTH)));
                character.square.button.setIcon(squareImg);
                character.square.character = null;
                character.square.button.removeActionListener(character.square.action);
                character.square.button.setBorder(null);
                character.square = arenaSquares.get(newPositon);
                arenaSquares.get(newPositon).character = character;
                if (character.team == 1)
                    arenaSquares.get(newPositon).button.setBorder(border1);
                else
                    arenaSquares.get(newPositon).button.setBorder(border2);
                //Change of turn
                passTurnMethod();
                moveCharacter.setIcon(arenaImages.moveButton);

            }
        };
    }

    // ------------------------------------- PASS TURN -------------------------------------
    private void passTurnMethod(){
        //Change of turn
        actualTurn = arenaImages.changeTurn(actualTurn, actualElement, arena.player1, arena.player2);
        refillCharactersMana();
        afterAction();
        arenaWindow.revalidate();
        arenaWindow.repaint();

    }

    private void afterAction(){
        //all methods that need to be called after any action
        rechargeImages();
        cleanRollOverImage();
        cleanActionListeners();
        chargeCharactersActions();
        actionsPanel.setVisible(false);
        abilitiesPanel.setVisible(false);
        hideInformation();

        for (models.Character current : arena.characters) {
            if(current.square != null)
                current.square.button.setBorder(null);
        }
    }

    private void refillCharactersMana(){
        for(Character temp : characters)
            temp.refillMana();
    }

    private void backAbilities(){
        abilitiesPanel.setVisible(false);
        activateInformation();
        actionsPanel.setVisible(true);
    }

    private void activateWinner(User winner){
        winner.statistics.addMatchWon(); //update statistics
        arenaPanel.setVisible(false);
        winnerScreen.winnerPanel.setVisible(true);
        winnerScreen.updateWinner(winner);
        finishGame();
    }
    private void finishGame(){

        //reestablecer todo
    }

    // ------------------------------------- Getters -------------------------------------
    public void initializeVariables() {
        arenaWindow = mainArena.getScreen();
        arenaPanel = mainArena.getArenaPanel();
        arenaSquares = mainArena.getArenaSquares();
        towerCart = mainArena.getTowerCart();
        characters = mainArena.getCharacters();
        towers = mainArena.getTowers();
        arenaImages = mainArena.getArenaImages();
        arena = mainArena.getArena();
        profileSpace = mainArena.getProfileSpace();
        profilePicture = mainArena.getProfilePicture();
        namePlayer = mainArena.getNamePlayer();
        selectTowersMSG = mainArena.getSelectTowersMSG();
        characterPanelList = mainArena.getCharactersPanel();
        normalAttack = mainArena.getNormalAttack();
        moveCharacter = mainArena.getMoveCharacter();
        passTurn = mainArena.getPassTurn();
        actionsPanel = mainArena.getActionsPanel();
        towerPanel = mainArena.getTowerPanel();
        abilitiesPanel = mainArena.getAbilitiesPanel();
        simpleAttack = mainArena.getSimpleAttack();
        specialAttack1 = mainArena.getSpecialAttack1();
        specialAttack2 = mainArena.getSpecialAttack2();
        specialMSG1 = mainArena.getSpecialMSG1();
        damageSpecial1 = mainArena.getDamageSpecial1();
        manaSpecial1 = mainArena.getManaSpecial1();
        specialMSG2 = mainArena.getSpecialMSG2();
        damageSpecial2 = mainArena.getDamageSpecial2();
        manaSpecial2 = mainArena.getManaSpecial2();
        backButton = mainArena.getBackButton();
        squareImg = new ImageIcon(arenaImages.squaresElementImg);
        arenaFonts = new letterFonts();
    }
}

