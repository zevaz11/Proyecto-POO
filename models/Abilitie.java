package models;

import javax.swing.*;

public class Abilitie {
    private String name;
    private int damage;
    private int manaExpense;
    private ImageIcon image;

    public Abilitie(String name, int damage, int manaExpense, ImageIcon newImage) {
        this.name = name;
        this.damage = damage;
        this.manaExpense = manaExpense;
        image = newImage;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaExpense() {
        return manaExpense;
    }
    public ImageIcon getImage(){return image;}
}
