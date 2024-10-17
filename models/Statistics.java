package models;

public class Statistics {
    private int matchesWon;
    private int destroyedTowers;
    private int defeatedEnemies;
    public Statistics(){
        matchesWon = 0;
        destroyedTowers = 0;
        defeatedEnemies = 0;
    }

    public int getMatchesWon(){return matchesWon;}
    public int getDestroyedTowers(){return destroyedTowers;}
    public int getDefeatedEnemies(){return defeatedEnemies;}

    public void addMatchWon(){
        matchesWon++;
    }
    public void addDestroyedTower(){
        destroyedTowers++;
    }
    public void addDefeatedEnemies(){
        defeatedEnemies++;
    }
}
