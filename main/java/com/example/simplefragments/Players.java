package com.example.simplefragments;

public class Players {

    private int playerID;
    private String playerFirstName;
    private String playerLastName;
    private int playerGamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int goalsFor;
    private int goalsAgainst;

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerFirstName() {
        return playerFirstName;
    }

    public void setPlayerFirstName(String playerFirstName) {
        this.playerFirstName = playerFirstName;
    }

    public String getPlayerLastName() {
        return playerLastName;
    }

    public void setPlayerLastName(String playerLastName) {
        this.playerLastName = playerLastName;
    }

    public int getPlayerGamesPlayed() {
        return playerGamesPlayed;
    }

    public void setPlayerGamesPlayed(int playerGamesPlayed) {
        this.playerGamesPlayed = playerGamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public String winLossRatio(){
        return  this.gamesWon+" of "+this.playerGamesPlayed;
    }
}
