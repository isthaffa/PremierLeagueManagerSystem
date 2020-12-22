package models;

import java.io.Serializable;

public class FootballClub extends SportsClub implements Comparable ,Serializable {
    private int points;
    private int matchesWon;
    private int matchesDrawn;
    private int matchesDefeated;
    private int scoredGoals;
    private int goalDifference;
    private int receivedGolas;
    private int matchesPlayed;

    public FootballClub() {
    }

    public FootballClub(String clubName, String location) {
        super(clubName, location);
    }

    public int getPoints() {
        return points;
    }

    public int getMatchesDrawn() {
        return matchesDrawn;
    }


    public int getMatchesWon() {
        return matchesWon;
    }
    public int getScoredGoals() {
        return scoredGoals;
    }
    public int getReceivedGolas() {
        return receivedGolas;
    }


    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getMatchesDefeated() {
        return matchesDefeated;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {                                     

        this.goalDifference = goalDifference;
    }

    public void setMatchesWon(int matchesWon) {

        this.matchesWon = getMatchesWon()+matchesWon;
    }
    public void setPoints(int points) {

        this.points = getPoints()+points;
    }


    public void setMatchesDrawn(int matchesDrawn) {
        this.matchesDrawn = getMatchesDrawn()+matchesDrawn;
    }


    public void setMatchesDefeated(int matchesDefeated) {
        this.matchesDefeated = getMatchesDefeated()+ matchesDefeated;
    }



    public void setScoredGoals(int scoredGoals) {

        this.scoredGoals = getScoredGoals()+scoredGoals;
    }


    public void setReceivedGolas(int receivedGolas) {
        this.receivedGolas = getReceivedGolas()+receivedGolas;
    }



    public void setMatchesPlayed(int matchesPlayed) {

        this.matchesPlayed = getMatchesPlayed()+matchesPlayed;
    }

    @Override
    public String toString() {
        return  super.toString() +"\n"+
                "statistics : "+
                "Points=" + points +
                ", Goal difference= "+goalDifference+
                ", Matches won=" + matchesWon +
                ", Matches drawn=" + matchesDrawn +
                ", Matches defeated=" + matchesDefeated +
                ", Scored goals=" + scoredGoals +
                ", Received goals=" + receivedGolas +
                ", Matches played =" + matchesPlayed;
    }




    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
