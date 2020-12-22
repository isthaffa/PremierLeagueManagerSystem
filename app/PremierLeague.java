import JavaFxGui.GUI;
import controllers.PremierLeagueController;
import services.PremierLeagueManager;
import javafx.application.Application;

import javax.naming.ldap.PagedResultsControl;
import java.io.IOException;
import java.util.Scanner;

public class PremierLeague {
    static PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        loadFromFile();
        boolean loop = true;
        while (loop) {
            menu();
            String option = sc.nextLine();
            switch (option.toLowerCase()) {
                case "a":
                    addFootballClub();
                    saveToFle();   //saving  to file in case after adding the club
                    break;
                case "d":
                    deleteFootballClub();
                    saveToFle();    //saving  to file in case after deleting the club
                    break;
                case "c":
                    displayStatisticsForClub();
                    break;
                case "s":
                    displayLeagueTable();
                    break;
                case "m":
                    addMatch();
                    saveToFle();         //saving  to file in case after adding the match
                    break;
                case "g":
                    try {
                        Application.launch(GUI.class, args);
                        saveToFle();
                        break;
                    } catch (IllegalStateException e) {
                        break;
                    }
                case "t":
//                    PremierLeagueController.premierLeagueManager=premierLeagueManager;
                    try
                    {
                        Runtime.getRuntime().exec("cmd /c start/min cmd.exe /K \"sbt run\"");
                        break;

                    }
                    catch (Exception e)
                    {
                        System.out.println("HEY Buddy ! something went wrong");
                        e.printStackTrace();
                        break;
                    }

                case "q":
                    saveToFle();
                    loop = false;
                    break;

                default:
                    System.out.println("invalid option ");
            }


        }


    }

    static void menu() {
        System.out.println("----------------------------------");
        System.out.println("Welcome to Premier League System  ");
        System.out.println("----------------------------------");
        System.out.println("A -> to add football club to the league ");
        System.out.println("D -> to delete a football club from the league ");
        System.out.println("C -> to display the statistics for the given club");
        System.out.println("S -> to display the premier league table");
        System.out.println("M -> to add a played match to the league");
        System.out.println("G -> to open JavaFx GUI");
        System.out.println("T -> to open Angular GUI");
        System.out.println("Q -> to save & quit");
        System.out.print("select a option from above :");

    }

    static void addFootballClub() {
        if (premierLeagueManager.leagueFull()) {
            System.out.println("League already has 20 clubs,can't add another club");
        } else {
            System.out.println("****************************************************************************");
            System.out.println("Add club to League(2020)");
            System.out.print("Enter the name to continue or (q) to return to menu : ");
            String clubName = premierLeagueManager.validateString("new club");
            if (clubName.equals("q")) {
                return;
            }
            System.out.print("Enter the Location to continue or (q) to return to menu : ");
            String location = premierLeagueManager.validateString("location");
            if (location.equals("q")) {
                return;
            }

            premierLeagueManager.addFootballClubToLeague(clubName, location);

        }


    }

    static void deleteFootballClub() {
        if (premierLeagueManager.emptyLeague()) {
            System.out.println("there are no clubs in the League to delete");

        } else {
            System.out.println("*********************************************************************************");
            premierLeagueManager.displayClub();
            System.out.println("enter the name of a club from above to delete ");
            System.out.print("Enter the name to continue or (q) to return to menu : ");
            String clubName = premierLeagueManager.validateString("search club");
            if (clubName.equals("q")) {

                return;
            }
            premierLeagueManager.deleteFootballClubFromLeague(clubName);
        }

    }

    static void displayStatisticsForClub() {
        if (premierLeagueManager.emptyLeague()) {
            System.out.println("there are no clubs in the League to show the statistics");
        } else {
            premierLeagueManager.displayClub();
            System.out.println("Enter the club name from the above list");
            System.out.print("club name : ");

            String clubName = premierLeagueManager.validateString("search club");
            if (clubName.equals("q")) {

                return;
            }
            premierLeagueManager.displayStatisiticForClub(clubName);

        }

    }

    static void displayLeagueTable() {
        premierLeagueManager.displayLeagueTable();
    }

    static void addMatch() {
        if (!premierLeagueManager.emptyLeague() && premierLeagueManager.getNumberOfClubs() >= 1) {
            System.out.println("Enter the Date (day & Month of the match) or (q) to return to main menu ");
            System.out.print("day :");
            int day = premierLeagueManager.validateInteger("day");
            if (day == 999) {      //if enter q it will return to main menu

                return;
            }

            System.out.print("Month :");
            int month = premierLeagueManager.validateInteger("month");
            if (month == 999) {      //if enter q it will return to main menu
                return;
            }
            System.out.println("Enter the Club details that played on " + day + "/" + month + "/" + "2020");

            premierLeagueManager.displayClub();
            System.out.println("select a club from above");

            System.out.println("Name of the Home team (team A):");

            String teamA;
            teamA = premierLeagueManager.validateString("search club");


            if (teamA.equals("q")) {
                return;
            }
            System.out.print("Name of the Away team (team B) :");
            String teamB;
            while (true) {
                teamB = premierLeagueManager.validateString("search club");
                if (teamB.equals("q")) {
                    return;
                }

                if (teamB.equals(teamA)) {
                    System.out.print("can't select the same team, select another team or (q) to return to menu :");


                } else {
                    break;
                }
            }
            System.out.println("Enter the Goals Scored in the Match");
            System.out.print("Home Teams Score :");
            int teamAScore = premierLeagueManager.validateInteger("score");
            if (teamAScore == 999) {      //if enter q it will return to main menu

                return;
            }
            System.out.print("Away Teams Score :");
            int teamBScore = premierLeagueManager.validateInteger("score");
            if (teamBScore == 999) {
                return;
            }
            premierLeagueManager.addMatch(day, month, teamA, teamB, teamAScore, teamBScore);
        } else {
            System.out.println("there are no clubs in the League to add a match");
            System.out.println("there has to be two clubs in the League to add a match");
        }

    }

    static void saveToFle() {
        try {
            premierLeagueManager.saveToFile("league.txt", "match.txt");
        } catch (IOException e) {
            System.out.println("error while saving to file");


        }
    }

    static void loadFromFile() {
        try {
            premierLeagueManager.loadFromFile("league.txt","match.txt");
        } catch (IOException e) {
            System.out.println("File not found or error while loading");

        }


    }

}

