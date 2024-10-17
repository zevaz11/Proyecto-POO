package Menu;

import models.Abilitie;
import models.Character;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class setImages {

    public String backGroundImg, profileImg, towerSquareImg, squaresElementImg, squareOff;
    public ImageIcon squareImg, playerImg;;

    // ----- Characters -----
    ImageIcon magicFrog;
    ImageIcon calamardo;
    ImageIcon spaceman;
    ImageIcon rataPastor;
    ImageIcon walterWhite;
    ImageIcon pinkPanther;
    ImageIcon Eduardo;
    ImageIcon cheemsSamurai;
    ImageIcon galloConTenis;
    ImageIcon venomousSnake;
    ImageIcon RyanTheGosling;

    // ----- Abilities -----
    Abilitie basicAttak, bigSword, fakeHand, fireHand, jellyFish, poison, sharkTank, spirits, trick, waterSword;

    // ----- Menu Buttons Images -----
    String newGameImg;
    String newCharacterImg;
    String newUserImg;
    String statisticsImg;
    String exitImg;
    String newGameImg2;
    String newCharacterImg2;
    String newUserImg2;
    String statisticsImg2;
    String exitImg2;

    // ----- Arena Buttons Images -----
    ImageIcon normalAttack;
    ImageIcon moveButton;
    ImageIcon passTurn;
    ImageIcon normalAttack2;
    ImageIcon moveButton2;
    ImageIcon passTurn2;
    ImageIcon attackSquare;
    ImageIcon moveSquare;

    public setImages(){

    }

    public void player1Configuration(int element, User player1){
        //Method that set the arena images for player 1

        //player1 profile
        playerImg = player1.picture;


        switch (element){

            case 1: { //water

                profileImg = "src\\images\\profileWater.png";
                backGroundImg = "src\\images\\waterArena2.jpg";
                towerSquareImg = "src\\images\\waterTower.png";
                squaresElementImg = "src\\images\\waterSquare.png";
                squareOff = "src\\images\\waterOff.png";
                break;
            }

            case 2: { //fire

                profileImg = "src\\images\\profileFire.png";
                backGroundImg = "src\\images\\fireArena2.jpg";
                towerSquareImg = "src\\images\\fireTower.png";
                squaresElementImg = "src\\images\\fireSquare.png";
                squareOff = "src\\images\\fireOff.png";
                break;
            }

            case 3: { //earth

                profileImg = "src\\images\\profileEarth.png";
                backGroundImg = "src\\images\\earthArena2.jpg";
                towerSquareImg = "src\\images\\earthTower.png";
                squaresElementImg = "src\\images\\earthSquare.png";
                squareOff = "src\\images\\earthOff.png";
                break;
            }

            case 0: { //air

                profileImg = "src\\images\\profileAir.png";
                backGroundImg = "src\\images\\airArena2.png";
                towerSquareImg = "src\\images\\AirTower.png";
                squaresElementImg = "src\\images\\AirSquare.png";
                squareOff = "src\\images\\airOff.png";
                break;
            }
        }

        squareImg = new ImageIcon("src\\images\\tower.png");
    }

    public void player2Configuration(int element, User player2){

        //enemie tower design
        squareImg = new ImageIcon("src\\images\\enemieTower.png");
        //enemie profile
        playerImg = player2.picture;

        switch (element){

            case 1: { //water

                profileImg = "src\\images\\profileWater2.png";
                towerSquareImg = "src\\images\\waterTower2.png";
                break;
            }

            case 2: { //fire

                profileImg = "src\\images\\profileFire2.png";
                towerSquareImg = "src\\images\\fireTower2.png";
                break;
            }

            case 3: { //earth

                profileImg = "src\\images\\profileEarth2.png";
                towerSquareImg = "src\\images\\earthTower2.png";
                break;
            }

            case 0: { //air

                profileImg = "src\\images\\profileAir2.png";
                towerSquareImg = "src\\images\\AirTower2.png";
                break;
            }
        }
    }

    public int changeTurn(int turn, int actualElement, User player1, User player2){
        int newTurn;
        if(turn == 1) {
            player2Configuration(actualElement, player2);
            newTurn = 2;
        }
        else {
            player1Configuration(actualElement, player1);
            newTurn = 1;
        }
        return newTurn;
    }

    public void charactersImages(){

        magicFrog = new ImageIcon("src\\images\\magicFrog.png");
        calamardo = new ImageIcon("src\\images\\calamardo.png");
        spaceman = new ImageIcon("src\\images\\spaceman.png");
        rataPastor = new ImageIcon("src\\images\\rataPapa.png");
        walterWhite = new ImageIcon("src\\images\\walterWhite.png");
        pinkPanther = new ImageIcon("src\\images\\pinkPanther.png");
        Eduardo = new ImageIcon("src\\images\\Eduardo.png");
        cheemsSamurai = new ImageIcon("src\\images\\Cheems Samurai.png");
//        cheemsSamurai = new ImageIcon("src\\images\\Cheems Samurai.png");
//        galloConTenis = new ImageIcon("src\\images\\Gallo con Tenis.png");
//        venomousSnake = new ImageIcon("src\\images\\Venomous Snake.png");
//        RyanTheGosling = new ImageIcon("src\\images\\Ryan the Gosling.png");
    }

    public void menuButtonsImages(){
        // Set buttons images
        newGameImg = "src/images/newGameButton.png";
        newCharacterImg = "src/images/newCharacterButton.png";
        newUserImg = "src/images/newUserButton.png";
        statisticsImg = "src/images/statisticsButton.png";
        exitImg = "src/images/exitButton.png";

        // Set images when the cursor is on the button
        newGameImg2 = "src/images/newGameButton2.png";
        newCharacterImg2 = "src/images/newCharacterButton2.png";
        newUserImg2 = "src/images/newUserButton2.png";
        statisticsImg2 = "src/images/statisticsButton2.png";
        exitImg2 = "src/images/exitButton2.png";
    }

    public void arenaButtonsImages(){
        // Set buttons images
        normalAttack = new ImageIcon("src/images/attack.png");
        moveButton = new ImageIcon("src/images/moveButton.png");
        passTurn = new ImageIcon("src/images/passTurn.png");

        // Set images when the cursor is on the button
        normalAttack2 = new ImageIcon("src/images/attack2.png");
        moveButton2 = new ImageIcon("src/images/moveButton2.png");
        passTurn2 = new ImageIcon("src/images/passTurn2.png");

        //Others
        attackSquare = new ImageIcon("src/images/attackSquare.png");
        moveSquare = new ImageIcon("src/images/moveSquare.png");
    }

    public ArrayList<Character> chargeCharacters(){
        ArrayList<Character> listOfCharacters = new ArrayList(Arrays.asList(
                new Character("Calamardo", 421, 100, 0, 441, 84, "WATER", 1, 0, calamardo),
                new Character("Magic Frog", 1, 100, 0, 12, 10, "FIRE", 1, 0,magicFrog),
                new Character("Eduardo", 400, 100, 0, 243, 53, "WATER", 1, 0, Eduardo),
                new Character("Cheems Samurai", 300, 100, 0, 143, 63, "AIR", 1, 0, cheemsSamurai),
                new Character("Pink Panther", 420, 100, 0, 254, 37, "EARTH", 1, 0, pinkPanther),
                new Character("Walter White", 421, 100, 0, 441, 84, "FIRE", 1, 0, walterWhite),
                new Character("Rata Papa", 421, 100, 0, 441, 84, "AIR", 1, 0, rataPastor),
                new Character("Space Man", 421, 100, 0, 441, 84, "AIR", 1, 0, spaceman)
//                new Character("Gato Mago", 421, 100, 0, 441, 84, "FIRE", 1, 0, GatoMago),
//                new Character("Venomous Snake", 421, 100, 0, 441, 84, "EARTH", 1, 0, venomousSnake),
//                new Character("Ryan The Gosling", 421, 100, 0, 441, 84, "WATER", 1, 0, RyanTheGosling),
//                new Character("Gallo Con Tenis", 421, 100, 0, 441, 84, "AIR", 1, 0, galloConTenis)
        ));

        return listOfCharacters;
    }

    public ArrayList<User> chargeUsers(){
        ArrayList<User> listOfUsers = new ArrayList<>(Arrays.asList(
                new User("Sebas", new ImageIcon("src\\images\\userImages\\wizard.png")),
                new User("Mario", new ImageIcon("src\\images\\userImages\\shyGuy.png")),
                new User("Lios", new ImageIcon("src\\images\\userImages\\Ryan Gosling.png"))
        ));

        return listOfUsers;
    }

    public void chargeAbilities() {

        basicAttak = new Abilitie("Basic Attack", 100, 20, new ImageIcon("src\\images\\abilitiesImages\\basicAttack.png"));
        spirits = new Abilitie("Spirits", 450, 60, new ImageIcon("src\\images\\abilitiesImages\\spirits.png"));
        poison = new Abilitie("Poison", 300, 40, new ImageIcon("src\\images\\abilitiesImages\\poison.png"));
        fireHand = new Abilitie("Fire Hand", 500, 70, new ImageIcon("src\\images\\abilitiesImages\\fireHand.png"));
        sharkTank = new Abilitie("Shark Tank", 500, 70, new ImageIcon("src\\images\\abilitiesImages\\sharkTank.png"));
        fakeHand = new Abilitie("Fake Hand", 200, 50, new ImageIcon("src\\images\\abilitiesImages\\fakeHand.png"));
        bigSword = new Abilitie("Big Sword", 600, 90, new ImageIcon("src\\images\\abilitiesImages\\bigSword.png"));
        jellyFish = new Abilitie("Jellyfish", 400, 65, new ImageIcon("src\\images\\abilitiesImages\\jellyFish.png"));
        trick = new Abilitie("Trick", 300, 50, new ImageIcon("src\\images\\abilitiesImages\\trick.png"));
        waterSword = new Abilitie("Water Sword", 500, 80, new ImageIcon("src\\images\\abilitiesImages\\waterSword.png"));
    }

    public ArrayList<Abilitie> getAbilities(){
        ArrayList<Abilitie> listOfUsers = new ArrayList<>(Arrays.asList(basicAttak, spirits, poison, fireHand,sharkTank,
                fakeHand, bigSword, jellyFish, trick, waterSword));
        return listOfUsers;
    }

    public void addAbilities(ArrayList<Character> temporal){
        //Calamardo
        temporal.getFirst().addAbilitie(fireHand);
        temporal.getFirst().addAbilitie(poison);
        //Magic Frog
        temporal.get(1).addAbilitie(fakeHand);
        temporal.get(1).addAbilitie(spirits);
        //Eduardo
        temporal.get(2).addAbilitie(waterSword);
        temporal.get(2).addAbilitie(sharkTank);
        //Ghost
        temporal.get(3).addAbilitie(spirits);
        temporal.get(3).addAbilitie(bigSword);
        //Pink Panther
        temporal.get(4).addAbilitie(trick);
        temporal.get(4).addAbilitie(fakeHand);
        //Walter White
        temporal.get(5).addAbilitie(fireHand);
        temporal.get(5).addAbilitie(poison);
        //Ratapapa
        temporal.get(6).addAbilitie(bigSword);
        temporal.get(6).addAbilitie(spirits);
        //Spaceman
        temporal.get(7).addAbilitie(jellyFish);
        temporal.get(7).addAbilitie(fakeHand);

    }
}