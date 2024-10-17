package models;

import javax.swing.*;
import java.awt.event.ActionListener;

public class square {

    public int objectIn;
    public JButton button;
    public square nextSquare;
    public int id;
    public ActionListener action;
    public Character character;
    public Tower tower;

    public square(int newObject, JButton newButton, int newId){

        objectIn = newObject;
        button = newButton;
        id = newId;
        nextSquare = null;
        action = null;
        character = null;
        tower = null;

    }

    public void setSquareObject (Object newObject) {
        if (newObject instanceof Character) {
            character = (Character) newObject;
        }
        else {
            tower = (Tower) newObject;
        }
    }

    public void killObject () {
        tower = null;
        character = null;
    }

    public void newActionListener(ActionListener newAction){
        action = newAction;
    }
}
