package services;

import models.FootballClub;
import models.Match;
import utils.Date;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class PremierLeagueService {

    PremierLeagueManager premierLeagueManager;
    List<FootballClub> leagueList;
    List<Match> matchList;

    public PremierLeagueService(){
        premierLeagueManager=PremierLeagueManager.getInstance();
        try {
            premierLeagueManager.loadFromFile("league.txt","match.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<FootballClub> getClubList(){
        leagueList=premierLeagueManager.getFootballClubs();

        return leagueList;
    }
    public List<Match> getMatchList(){
        matchList=premierLeagueManager.getMatchList();
        return matchList;
    }

    public Match addRandomMatch(){
        return premierLeagueManager.addRandomMatch();
    }

    public Match getMatchByDate(Date date){
        return premierLeagueManager.getMatchByDate(date);
    }


    ///////////

    public List<FootballClub> sortByGoalsScore(){
        leagueList.sort(premierLeagueManager.sortByGoalsCount().reversed());
        return leagueList;
    }
    public List<FootballClub> sortByWinCount(){
        leagueList.sort(premierLeagueManager.sortByWinCount().reversed());
        return leagueList;
    }
    public List<Match> sortByDate(){
        Collections.sort(matchList);

        return matchList;
    }
    //////////




    public static void main(String[] args) {
        PremierLeagueService p=new PremierLeagueService();
        System.out.println(p.getClubList());
            Date date=new Date(10,2);
        System.out.println(p.getMatchByDate(date));
        System.out.println(p.addRandomMatch());
    }

}
