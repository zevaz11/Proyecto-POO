package models;

import javax.swing.*;
import java.awt.*;

public class User {
    public String name;
    public ImageIcon picture;
    public Statistics statistics;

    public User(String newName, ImageIcon newImage){
        name = newName;
        picture = newImage;
        statistics = new Statistics();
    }

    public String getName(){return name;}
    public ImageIcon getPicture(){return picture;}
}
