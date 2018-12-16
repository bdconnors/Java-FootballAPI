package org.rit.footballapi.controllers;

import org.rit.footballapi.models.AllDefensesCumulative;
import org.rit.footballapi.models.Defense;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(DefenseController.BASE_URL)
public class DefenseController {

    public static final String BASE_URL = "footballapi";

    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="Players/Defenses", method = RequestMethod.GET)
    @ResponseBody
    public AllDefensesCumulative getAllDefenses()
    {

        AllDefensesCumulative defenses = new AllDefensesCumulative();

        try {

            defenses.fetch();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return defenses;
    }

    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="Players/Defenses/{team}", method = RequestMethod.GET)
    @ResponseBody
    public Defense getPlayer(@PathVariable(value ="team")String team, @RequestParam(required = false,value ="gameid") String gameid)
    {
        Defense defense = null;

        try
        {
            if(gameid == null)
            {
                defense = new Defense(team);
            }
            else
            {
                defense = new Defense(team, gameid);
            }

            defense.fetch();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return defense;
    }

}
