package main.java.businesslayer.controllers;

import main.java.datalayer.database.models.positions.Receiver;
import main.java.datalayer.database.models.positions.AllPlayersCumulative;
import org.springframework.web.bind.annotation.*;
import main.java.datalayer.database.models.Player;


@RestController
@RequestMapping(PlayerController.BASE_URL)
public class PlayerController{

    public static final String BASE_URL = "footballapi";

    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="/Players", method = RequestMethod.GET)
    @ResponseBody
    public Player getAllPlayers()
    {

        AllPlayersCumulative players = new AllPlayersCumulative();

        try {

            players.fetch();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return players;
    }
    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="Players/{pos}", method = RequestMethod.GET)
    @ResponseBody
    public Player getPos(@PathVariable(required = false,value ="pos")String pos)
    {

        AllPlayersCumulative players = new AllPlayersCumulative();

        try {
            if(pos.equals("wr")) {
                 players.allWR();
            }else if(pos.equals("qb"))
            {
                players.allQB();
            }else if(pos.equals("te"))
            {
                players.allTE();
            }else if(pos.equals("rb"))
            {
                players.allRB();
            }else if(pos.equals("k"))
            {
                players.allK();
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return players;
    }
    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="Players/Player", method = RequestMethod.GET)
    @ResponseBody
    public Player getPlayer(@RequestParam(value ="id")String id,@RequestParam(required = false,value ="gameid") String gameid)
    {
        Player player = null;

        try
        {
            if(gameid == null)
            {
                player = new Receiver(id);
            }
            else
            {
                player = new Receiver(id, gameid);
            }

            player.fetch();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return player;
    }

}
