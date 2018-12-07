package main.java.businesslayer.controllers;

import org.springframework.web.bind.annotation.*;
import main.java.datalayer.database.models.Team;


@RestController
@RequestMapping(TeamController.BASE_URL)
public class TeamController{

    public static final String BASE_URL = "api/teams";

    @CrossOrigin(origins = "http://129.21.17.50:8080", maxAge = 3600)
    @RequestMapping(value="{teamabrv}", method = RequestMethod.GET)
    @ResponseBody
    public Team getTeam(@PathVariable(value = "teamabrv")String teamarbv) 
    {
        Team team = new Team(teamarbv);
        
        try 
        {
            team.fetch();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return team;
    }
 

}
