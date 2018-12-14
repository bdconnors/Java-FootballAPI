package org.rit.footballapi.controllers;

import org.rit.footballapi.models.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(PlayerController.BASE_URL)
public class PlayerController {

    public static final String BASE_URL = "footballapi";

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/Players", method = RequestMethod.GET)
    @ResponseBody
    public AllPlayersCumulative getAllPlayers() {

        AllPlayersCumulative players = new AllPlayersCumulative();

        try {

            players.fetch();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return players;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "Players/{pos}", method = RequestMethod.GET)
    @ResponseBody
    public AllPlayersCumulative getPos(@PathVariable(required = false, value = "pos") String pos) {

        AllPlayersCumulative players = new AllPlayersCumulative();

        try {
            if (pos.equalsIgnoreCase("wr")) {
                players.allWR();
            } else if (pos.equalsIgnoreCase("qb")) {
                players.allQB();
            } else if (pos.equalsIgnoreCase("te")){
                players.allTE();
            } else if (pos.equalsIgnoreCase("rb")) {
                players.allRB();
            } else if (pos.equalsIgnoreCase("k")) {
                players.allK();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return players;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "Players/Player", method = RequestMethod.GET)
    @ResponseBody
    public Player getPlayer(
            @RequestParam(value = "id") String id,
            @RequestParam(required = false, value = "gameid") String gameid
    ) {
        Player player = null;

        try {
            if (gameid == null) {
                player = new Receiver(id);
            } else {
                player = new Receiver(id, gameid);
            }

            player.fetch();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return player;
    }
    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="Players/{pos}/{fname}/{lname}", method = RequestMethod.GET)
    @ResponseBody
    public Player getPlayerByName(
            @PathVariable(value ="pos")String pos,
            @PathVariable(value ="fname") String fname,
            @PathVariable(value ="lname") String lname
    )
    {
        Player player = null;

        try
        {
            if(pos.equals("wr")||pos.equals("te")) {
                player = new Receiver(fname,lname);
            }else if(pos.equals("qb"))
            {
                player = new QuarterBack(fname,lname);
            }else if(pos.equals("rb"))
            {
                player = new RunningBack(fname,lname);
            }else if(pos.equals("k"))
            {
                player = new Kicker(fname,lname);
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