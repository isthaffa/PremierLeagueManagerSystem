package models;

import utils.Date;

import java.io.Serializable;

public class Match implements Serializable,Comparable<Match> {
    private Date date;
    private FootballClub homeTeam;  //team A
    private FootballClub awayTeam; //team B
    private int homeTeamScore;    // team A' s score
    private int awayTeamScore;    //team b' s score

    public Match(Date date, FootballClub homeTeam, FootballClub awayTeam, int homeTeamScore, int awayTeamScore) {
        this.date = date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHomeTeam(FootballClub homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(FootballClub awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public Date getDate() {
        return date;
    }

    public FootballClub getHomeTeam() {
        return homeTeam;
    }

    public FootballClub getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    @Override
    public String toString() {
        return "=Match statistics :\n"+
                date.toString()+"\n"+
                "home team(team A) :" +homeTeam+
                " goals scored in the match :"+homeTeamScore+"\n"+
                "away team(team B) :" +awayTeam+
                " goals scored in the match :"+ awayTeamScore;
    }

    @Override
    public int compareTo(Match o) {

        return this.getDate().compareTo(o.getDate()) ;
    }
}
