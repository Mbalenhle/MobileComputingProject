package com.example.simplefragments;

public class Games {

    private int gamesID;
    private String teamA;
    private String teamB;
    private int teamAScore;
    private int teamBScore;
    private String date;
    private String time;
    private String venue;
    private int teamAPlayer1, teamAPlayer2, teamAPlayer3, teamAPlayer4, teamAPlayer5;
    private int teamBPlayer1, teamBPlayer2, teamBPlayer3, teamBPlayer4, teamBPlayer5;

    public int getGamesID() {
        return gamesID;
    }

    public void setGamesID(int gamesID) {
        this.gamesID = gamesID;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public int getTeamAScore() {
        return teamAScore;
    }

    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }

    public int getTeamBScore() {
        return teamBScore;
    }

    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }

    public int getTeamAPlayer1() {
        return teamAPlayer1;
    }

    public void setTeamAPlayer1(int teamAPlayer1) {
        this.teamAPlayer1 = teamAPlayer1;
    }

    public int getTeamAPlayer2() {
        return teamAPlayer2;
    }

    public void setTeamAPlayer2(int teamAPlayer2) {
        this.teamAPlayer2 = teamAPlayer2;
    }

    public int getTeamAPlayer3() {
        return teamAPlayer3;
    }

    public void setTeamAPlayer3(int teamAPlayer3) {
        this.teamAPlayer3 = teamAPlayer3;
    }

    public int getTeamAPlayer4() {
        return teamAPlayer4;
    }

    public void setTeamAPlayer4(int teamAPlayer4) {
        this.teamAPlayer4 = teamAPlayer4;
    }

    public int getTeamAPlayer5() {
        return teamAPlayer5;
    }

    public void setTeamAPlayer5(int teamAPlayer5) {
        this.teamAPlayer5 = teamAPlayer5;
    }

    public int getTeamBPlayer1() {
        return teamBPlayer1;
    }

    public void setTeamBPlayer1(int teamBPlayer1) {
        this.teamBPlayer1 = teamBPlayer1;
    }

    public int getTeamBPlayer2() {
        return teamBPlayer2;
    }

    public void setTeamBPlayer2(int teamBPlayer2) {
        this.teamBPlayer2 = teamBPlayer2;
    }

    public int getTeamBPlayer3() {
        return teamBPlayer3;
    }

    public void setTeamBPlayer3(int teamBPlayer3) {
        this.teamBPlayer3 = teamBPlayer3;
    }

    public int getTeamBPlayer4() {
        return teamBPlayer4;
    }

    public void setTeamBPlayer4(int teamBPlayer4) {
        this.teamBPlayer4 = teamBPlayer4;
    }

    public int getTeamBPlayer5() {
        return teamBPlayer5;
    }

    public void setTeamBPlayer5(int teamBPlayer5) {
        this.teamBPlayer5 = teamBPlayer5;
    }
    public String getGameScore(){
        return this.teamAScore+" : "+this.teamBScore;
    }
}
