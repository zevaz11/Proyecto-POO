package models;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Character {
    public String name;
    static int staticHealth;
    public int health;
    static int staticMana;
    public int mana;
    public int level;
    static int staticAttack;
    public int attack;
    public int defense;
    public String vitalElement;
    public int boxMovement;
    public ArrayList <Abilitie> abilitiesList;
    public int team;
    public int location;
    public ImageIcon photoFileName;
    public square square;
    public JButton button;

    //Constructor
    public Character(String name, int health, int mana, int level, int attack, int defense, String vitalElement, int boxMovement, int team, ImageIcon image) {
        this.name = name;
        this.health = staticHealth = health ;
        this.mana = staticMana = mana;
        this.level = level;
        this.attack = staticAttack = attack;
        this.defense = defense;
        this.vitalElement = vitalElement;
        this.boxMovement = boxMovement;
        this.abilitiesList = new ArrayList<>();
        abilitiesList.add(new Abilitie("Basic Attack", attack, 20, new ImageIcon("src\\images\\abilitiesImages\\basicAttack.png")));
        this.team = team;
        this.location = 0;
        this.photoFileName = image;
    }
    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMana() {
        return this.mana;
    }

    public int getLevel() {
        return this.level;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public String getVitalElement() {
        return this.vitalElement;
    }

    public int getBoxMovement() {
        return this.boxMovement;
    }

    public ArrayList <Abilitie> getAbilitiesList() {
        return this.abilitiesList;
    }

    public void increaseLevel () {
        this.level++;
        attack += (int) (staticAttack * ((double) 25 / 100));
        health += (int) (staticHealth * ((double) 25 / 100));

    }

    public void refillMana(){
        mana += (int)(staticMana * ((double) 25 / 100));
    }

    public void addAbilitie(Abilitie newAbilitie){
        abilitiesList.add(newAbilitie);
    }

    public int getTeam() {
        return this.team;
    }

    public int getLocation() {
        return this.location;
    }

    public void reduceHealth (int damage) {
        damage -= new Random().nextInt(this.defense);
        if (damage <= 0) {
            return;
        }
        else {
            this.health -= damage;
        }

        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void reduceMana (int manaExpense) {
        this.mana -= manaExpense;
    }

    public void restoreInformation(){
        health = staticHealth;
        attack = staticAttack;
        mana = staticMana;
        level = 0;
    }

    public ImageIcon getPhotoFileName() {
        return this.photoFileName;
    }
}
