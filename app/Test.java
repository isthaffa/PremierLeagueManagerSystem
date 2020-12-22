import models.FootballClub;
import services.PremierLeagueManager;

import java.io.IOException;
import java.util.List;

public class Test {

        static PremierLeagueManager pl=PremierLeagueManager.getInstance();
    public static void main(String[] args) throws IOException {
        System.out.println(PremierLeagueManager.getInstance());
        pl.loadFromFile("league.txt","match.txt");
        System.out.println(pl.getFootballClubs());


        try
        {
            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"sbt run\"");

        }
        catch (Exception e)
        {
            System.out.println("HEY Buddy ! U r Doing Something Wrong ");
            e.printStackTrace();
        }

    }

}


