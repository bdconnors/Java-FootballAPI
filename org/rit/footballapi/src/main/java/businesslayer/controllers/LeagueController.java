package main.java.businesslayer.controllers;

import main.java.datalayer.database.League;
import main.java.datalayer.database.models.AllLeagueRequests;
import main.java.datalayer.database.models.AllLeagues;
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

}
