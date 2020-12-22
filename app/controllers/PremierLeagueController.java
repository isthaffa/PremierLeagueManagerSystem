package controllers;

import models.FootballClub;
import models.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.PremierLeagueManager;
import services.PremierLeagueService;
import utils.Date;

import java.util.List;


public class PremierLeagueController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");

    PremierLeagueService premierLeagueService=new PremierLeagueService();
    public Result index(){

        return ok("connected");
    }

    public Result getLeagueList()  {
        List<FootballClub> league;

        league= premierLeagueService.getClubList();

        logger.debug("In PremierLeagueController.getLeagueList(), get league list, list is {}",league);

        return ok(Json.toJson(league));



    }
    public Result getMatchList(){
        List<Match> match;
        match=premierLeagueService.getMatchList();
        return ok(Json.toJson(match));
    }
    public  Result getMatch(Integer day,Integer month){
        Date date=new Date(day,month);

        Match match=premierLeagueService.getMatchByDate(date);

        return ok(Json.toJson(match));
    }

    public Result addRandomMatch(){
        Match match=premierLeagueService.addRandomMatch();


        return ok(Json.toJson(match));
    }

}
