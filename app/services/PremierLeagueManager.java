package services;

import models.FootballClub;
import models.Match;
import utils.Date;

import java.io.*;
import java.util.*;

public class PremierLeagueManager implements LeagueManager {
    private  int numberOfClubs;
    private final int MAXCLUB = 20; // A premier league can contain 20 teams in a season
    private final List<FootballClub> LeagueList;
    private final List<Match> MatchList;
    private final Scanner sc = new Scanner(System.in);

    private static PremierLeagueManager instance = null;

    private PremierLeagueManager() {
        LeagueList = new ArrayList<>();
        MatchList = new ArrayList<>();
    }


    public static PremierLeagueManager getInstance() {
//        if(instance==null) {      //use for multi threaded application
//           synchronized (Services.PremierLeagueManager.class) {
            if (instance == null) {
                instance = new PremierLeagueManager();
            }
//           }
//      }
            return instance;
        }


    public int getNumberOfClubs() {
        return this.numberOfClubs=LeagueList.size();
    }


    public boolean leagueFull(){
        return (LeagueList.size()==MAXCLUB);
    }
    public boolean emptyLeague(){
        return LeagueList.isEmpty();
    }


    @Override
    public void addFootballClubToLeague(String clubName,String location) {


            FootballClub footballClub = new FootballClub(clubName, location);

            LeagueList.add(footballClub);
            System.out.println("club " + clubName + " added to the league successfully");

    }

    @Override
    public void deleteFootballClubFromLeague(String clubName) {

            for (FootballClub footBallClub : LeagueList) {
                if (clubName.equals(footBallClub.getClubName())) {
                    LeagueList.remove(footBallClub);
                    System.out.println("club " + clubName + " deleted successfully from the league");

                    break;
                }


            }


    }

    @Override
    public void displayStatisiticForClub(String clubName) {
        System.out.println("statistics for "+clubName+" Club");


            FootballClub footballClub = null;
            for (FootballClub club : LeagueList) {
                if (club.getClubName().equals(clubName)) {
                    footballClub = club;
                    break;
                }

            }
            if (footballClub != null) {
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%5s %10s %15s %15s %20s %15s %15s %15s", "Club Name", "pts", "MatchesPlayed", "MatchesWon", "MatchesDefeated", "MatchesDrawn", "ScoredGoals", "GoalDifference");
                System.out.println();
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.format("%5s %12s %10d %16d %18d  %15d %15d %15d ",
                        footballClub.getClubName(), footballClub.getPoints(), footballClub.getMatchesPlayed(), footballClub.getMatchesWon(), footballClub.getMatchesDefeated(), footballClub.getMatchesDrawn(), footballClub.getScoredGoals(), footballClub.getGoalDifference());
                System.out.println();
            }


    }

    @Override
    public void displayLeagueTable() {
        System.out.println("**********************************************************************************");
        System.out.println("League Clubs (2020)");
        System.out.println("____________________________________________________________________________________________");
        if (LeagueList.isEmpty()) {
            System.out.println("there are no clubs added to the league yet");
        } else {
            LeagueList.sort(new SortComparator().reversed());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%6s %20s %20s %10s %15s %15s %20s %15s %15s %15s", "Position", "ClubName", "Location", "pts", "MatchesPlayed", "MatchesWon", "MatchesDefeated", "MatchesDrawn", "ScoredGoals", "GoalDifference");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (int i = 0; i < LeagueList.size(); i++) {

                System.out.format("%6s %20s %20s %12s %10d %16d %18d  %15d %15d %15d ",
                        i + 1, LeagueList.get(i).getClubName(), LeagueList.get(i).getLocation(), LeagueList.get(i).getPoints(), LeagueList.get(i).getMatchesPlayed(), LeagueList.get(i).getMatchesWon(), LeagueList.get(i).getMatchesDefeated(), LeagueList.get(i).getMatchesDrawn(), LeagueList.get(i).getScoredGoals(), LeagueList.get(i).getGoalDifference());
                System.out.println();

            }
        }
    }

    @Override
    public void addMatch(int day,int month,String teamA,String teamB,int teamAScore,int teamBScore) {
        System.out.println("Adding Match to the League (2020)");
            Date date = new Date(day, month);
            FootballClub homeTeam = null;

            for (FootballClub home : LeagueList) {
                if (home.getClubName().equals(teamA)) {
                    homeTeam = home;
                    break;

                }

            }
            FootballClub awayTeam = null;


            for (FootballClub away : LeagueList) {
                if (away.getClubName().equals(teamB)) {
                    awayTeam = away;
                    break;
                }

            }



            if (homeTeam != null && awayTeam != null) {
                Match match = new Match(date, homeTeam, awayTeam, teamAScore, teamBScore);
                MatchList.add(match); // adding match to the match List
                homeTeam.setMatchesPlayed(1);
                awayTeam.setMatchesPlayed(1);
                homeTeam.setScoredGoals(teamAScore);
                awayTeam.setScoredGoals(teamBScore);
                homeTeam.setReceivedGolas(teamBScore);
                awayTeam.setReceivedGolas(teamAScore);
                homeTeam.setGoalDifference(homeTeam.getScoredGoals() - homeTeam.getReceivedGolas()); //calculating home teams gd
                awayTeam.setGoalDifference(awayTeam.getScoredGoals() - awayTeam.getReceivedGolas()); //calculating away teams gd
                System.out.println("------------------------------------");
                System.out.println(homeTeam.getClubName() + "  " + teamAScore + "::" + teamBScore + "  " + awayTeam.getClubName());
                System.out.println("------------------------------------");
                if (teamAScore > teamBScore) {
                    System.out.println(teamA + " won this match");
                    homeTeam.setPoints(3);
                    homeTeam.setMatchesWon(1);
                    awayTeam.setMatchesDefeated(1);
                } else if (teamAScore == teamBScore) {
                    System.out.println("match is drawn  ");
                    homeTeam.setPoints(1);
                    awayTeam.setPoints(1);
                    homeTeam.setMatchesDrawn(1);
                    awayTeam.setMatchesDrawn(1);


                } else {
                    System.out.println(teamB + " won this match");
                    awayTeam.setPoints(3);
                    awayTeam.setMatchesWon(1);
                    homeTeam.setMatchesDefeated(1);

                }
            }

    }

    @Override
    public void saveToFile(String league, String match) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(league);
        FileOutputStream fileOutputStream1 = new FileOutputStream(match);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);

        for (FootballClub fbc : LeagueList) {


            objectOutputStream.writeObject(fbc);
        }
        objectOutputStream.flush();
        fileOutputStream.close();
        objectOutputStream.close();


        for (Match mch : MatchList) {
            objectOutputStream1.writeObject(mch);

        }


        objectOutputStream1.flush();
        fileOutputStream1.close();
        objectOutputStream1.close();


    }

    @Override
    public void loadFromFile(String league,String match) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(league);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        for (; ; ) {
            try {
                FootballClub club = (FootballClub) objectInputStream.readObject();
                LeagueList.add(club);
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }

        }
        fileInputStream.close();
        objectInputStream.close();

        FileInputStream fileInputStream1 = new FileInputStream(match);
        ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);


        for (; ; ) {
            try {
                Match mch = (Match) objectInputStream1.readObject();
                MatchList.add(mch);


            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        fileInputStream1.close();
        objectInputStream1.close();


    }

    public void displayClub() {
        System.out.println("clubs in the League(2020)");
        for (FootballClub club : LeagueList) {
            System.out.println(club.getClubName());
        }
    }
//validating a string input
    public String validateString(String str) {
        String Name = "";
        boolean validate = true;
        while (validate) {
            Name = sc.nextLine().toLowerCase();
            if (Name.isEmpty()) {
                System.out.print("you must enter a name to continue or q to quit :");
                continue;
            }
            if (Name.equals("q")) { //q to return to main menu
                break;

            }


        //checking whether string contains numbers
            if (!Name.matches(".*[^A-Za-z0-9+]") && Name.chars().noneMatch(Character::isDigit)) {
                    boolean found = false;
                    for (FootballClub c : LeagueList) {
                        if (Name.equals(c.getClubName())) {
                            found = true;
                            if(str.equals("new club")) {
                                System.out.print("already there is a club in the same name, try again :");
                            }
                            if(str.equals("search club")){
                                validate=false;
                            }

                        }
                    }
                    if (!found) {
                        if(str.equals("new club")) {
                            validate = false;
                        }
                        if(str.equals("search club")){
                            System.out.print("there is no such club, try again or (q) to return to menu : ");
                        }
                    }

                if(str.equals("location")){
                    validate=false;
                }


            } else {
                System.out.print(" re enter the name ( must not contain numbers ) :");
            }

        }
        return Name;

    }
    public int validateInteger(String str){
        int number=0;
        boolean validate = true;
        while (validate) {


            while (!sc.hasNextInt()) {
                if (sc.next().toLowerCase().equals("q")) {
                    number=999;                  //if q pressed passing this code as a quit number to return to menu
                    return number;

                }
                System.out.print("enter a number or (q) to return to menu :");

            }
            int temp = sc.nextInt();

                if (temp >= 0) {
                    if(str.equals("score")) {
                    number = temp;
                    validate = false;
                    }
                    if(str.equals("day")){
                        if ( temp>=1&&temp <= 30) {
                            number = temp;
                            validate = false;

                        } else {
                            System.out.println("you have to enter a day between 1 and 30 , try again : ");
                        }
                    }
                    if(str.equals("month")){
                        if ( temp>=1&&temp <= 12) {
                            number = temp;
                            validate = false;

                        } else {
                            System.out.println("you have to enter a month between 1 and 12 ");
                        }

                    }

                } else {
                    System.out.print("you have to enter a positive number: ");
                }



        }
        return number;

    }

    //method to get all the clubs  from file

    public List<FootballClub> getFootballClubs() {
        LeagueList.sort(new SortComparator().reversed());
        return LeagueList;
    }
    //method to get all the matches  from file

    public List<Match > getMatchList(){
        Collections.sort(MatchList); //sorting match according to their date
        return MatchList;

    }
    //method for random club selecting
    public FootballClub getRandomClub(List<FootballClub> list) {

        int index = (int)(Math.random()*list.size());
        return list.get(index);

    }

    //add random match to league
    public Match addRandomMatch(){
        Random random=new Random();
        Match match = null;
        Date date;
        FootballClub teamA=null;
        FootballClub teamB=null;
        int teamAScore;
        int teamBScore;
            //creating a random date using random class
            int day = random.nextInt(30) + 1;
            int month = random.nextInt(12) + 1;
            date = new Date(day, month);

            //selecting a random club from the league
            if(!LeagueList.isEmpty()) {
                while (true) {
                    teamA = getRandomClub(LeagueList);
                    teamB = getRandomClub(LeagueList);

                    if (!teamA.equals(teamB)) {
                        break;

                    }
                }
            }

            teamAScore=random.nextInt(13) ; //random generated score are between 0 to 12
            teamBScore=random.nextInt(13);

        if (teamA != null && teamB != null) {
            match = new Match(date, teamA, teamB, teamAScore, teamBScore);
            MatchList.add(match); // adding random match to the match List
            teamA.setMatchesPlayed(1);
            teamB.setMatchesPlayed(1);
            teamA.setScoredGoals(teamAScore);
            teamB.setScoredGoals(teamBScore);
            teamA.setReceivedGolas(teamBScore);
            teamB.setReceivedGolas(teamAScore);
            teamA.setGoalDifference(teamA.getScoredGoals() - teamA.getReceivedGolas()); //calculating home teams gd
            teamB.setGoalDifference(teamB.getScoredGoals() - teamB.getReceivedGolas()); //calculating away teams gd
            if (teamAScore > teamBScore) {
                teamA.setPoints(3);
                teamA.setMatchesWon(1);
                teamB.setMatchesDefeated(1);
            } else if (teamAScore == teamBScore) {
                teamA.setPoints(1);
                teamB.setPoints(1);
                teamA.setMatchesDrawn(1);
                teamB.setMatchesDrawn(1);


            } else {
                teamB.setPoints(3);
                teamB.setMatchesWon(1);
                teamA.setMatchesDefeated(1);

            }
        }

        return match;

    }

    //method for search a match by date
    public Match getMatchByDate(Date date){
        Match match=null;
        boolean found=false;

        for (Match m:MatchList) {
            if(date.equals(m.getDate())){
                match=m;
                found=true;
            }

        }
        if(!found){
            System.out.println("No matches happened on the given Date");
            return null;
        }
        return match;


    }
    public Comparator<FootballClub> sortByWinCount(){
        return Comparator.comparingInt(FootballClub::getMatchesWon);

    }

    public Comparator<FootballClub> sortByGoalsCount(){
        return Comparator.comparingInt(FootballClub::getScoredGoals);


        }




}
