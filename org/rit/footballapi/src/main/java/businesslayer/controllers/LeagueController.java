package main.java.businesslayer.controllers;

import main.java.datalayer.database.League;
import main.java.datalayer.database.models.AllLeagueRequests;
import main.java.datalayer.database.models.AllLeagues;
import main.java.datalayer.database.models.AllRosters;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(LeagueController.BASE_URL)
public class LeagueController {

    public static final String BASE_URL = "footballapi";

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "Leagues", method = RequestMethod.GET)
    @ResponseBody
    public AllLeagues getAllLeagues() {

        AllLeagues leagues = new AllLeagues();
        try {

            leagues.fetch();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return leagues;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "Leagues/requests", method = RequestMethod.GET)
    @ResponseBody
    public AllLeagueRequests getAllRequests() {

        AllLeagueRequests lr = new AllLeagueRequests();
        try {

            lr.fetch();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lr;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "Leagues/{leagueid}", method = RequestMethod.GET)
    @ResponseBody
    public AllRosters getAllRostersByLeague(@PathVariable(value="leagueid")String leagueid) {

        AllRosters rosters = new AllRosters(leagueid);
        try {

            rosters.fetch();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rosters;
    }

}
