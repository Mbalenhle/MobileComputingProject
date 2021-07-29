package com.example.simplefragments;

public class Teams {

    private String teamName;
    private int gamesWonByTeam;
    private int totalGamesPlayed;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getGamesWonByTeam() {
        return gamesWonByTeam;
    }

    public void setGamesWonByTeam(int gamesWonByTeam) {
        this.gamesWonByTeam = gamesWonByTeam;
    }

    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public void setTotalGamesPlayed(int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }
    public String getAverageWins(){
        double p = this.totalGamesPlayed;
        double w = this.gamesWonByTeam;
        double winAvg = Math.round((w/p)* 100.0);
        return winAvg+"";
    }

}
