package models;

import java.util.ArrayList;

public class Arena {
    private ArenaType arenaType;
    public ArrayList <Tower> towers;
    public ArrayList<Character> characters;
    private int [] size = new int [2];
    private ArrayList <Abilitie> abilitiesList;
    public User player1;
    public User player2;

    public Arena (ArenaType arenaType, ArrayList <Tower> towers, int [] size, ArrayList<Abilitie> skillList) {
        this.arenaType = arenaType;
        this.towers = towers;
        characters = new ArrayList();
        this.size = size;
        this.abilitiesList = skillList;
        player1 = null;
        player2 = null;
    }

    public ArenaType getArenaType() {
        return arenaType;
    }

    public int [] getArenaSize() {
        return size;
    }

    public ArrayList <Tower> getTowers() {
        return towers;
    }

    public ArrayList <Character> getCharacters() {
        return characters;
    }

    public void addCharacter (Character newCharacter) {
        characters.add(newCharacter);
    }

    public ArrayList <Abilitie> getAbilitiesList() {
        return abilitiesList;
    }
}
