package models;

import javax.swing.*;

public class Tower {
    public int health;
    int location; // Array index 0 for "x" and 1 for "y"
    public int team;
    public square towerSquare;
    public JButton button;
    private boolean state;

    public Tower(int team) {
        health = 2000;
        state = true;
        this.team = team;
    }

    public int getTeam() {
        return team;
    }

    public boolean getState(){return state;}

    public void reduceHealth (int damage) {

        this.health -= damage;

        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void changeState(){
        if(state == true) {
            state = false;
            towerSquare = null;
            button = null;
        }
        else
            state = true;
    }

    public int getLocation() {
        return location;
    }

    public int getHealth() {
        return team;
    }
}
