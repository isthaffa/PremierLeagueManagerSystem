package services;

import java.io.IOException;

public interface LeagueManager {


    void addFootballClubToLeague(String clubName,String location);
    void deleteFootballClubFromLeague(String clubName);
    void displayStatisiticForClub(String clubName);
    void displayLeagueTable();
    void addMatch(int day,int month,String teamA,String teamB,int teamAScore,int teamBScore );
    void saveToFile(String league,String match) throws IOException;
    void loadFromFile(String league,String match) throws IOException, ClassNotFoundException;



}
