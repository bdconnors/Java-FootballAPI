package org.rit.footballapi.controllers;

import org.rit.footballapi.models.AllLeagueRequests;
import org.rit.footballapi.models.AllLeagues;
import org.rit.footballapi.models.AllRosters;
import org.rit.footballapi.models.Roster;
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
    @RequestMapping(value = "Leagues/requests/{leagueid}", method = RequestMethod.GET)
    @ResponseBody
    public AllLeagueRequests getAllRequests(@PathVariable(value="leagueid")String leagueid) {

        AllLeagueRequests lr = new AllLeagueRequests(leagueid);
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
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "Leagues/Team/{teamid}", method = RequestMethod.GET)
    @ResponseBody
    public Roster getRoster(@PathVariable(value="teamid")String teamid){

        Roster roster = new Roster(teamid);
        try {

            roster.fetch();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return roster;
    }
}
