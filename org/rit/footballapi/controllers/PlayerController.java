package org.rit.footballapi.controllers;

import org.rit.footballapi.models.*;
import org.rit.footballapi.services.PlayerService;
import org.rit.footballapi.util.DLException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(PlayerController.BASE_URL)
public class PlayerController {

    public static final String BASE_URL = "footballapi";

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/Players", method = RequestMethod.GET)
    @ResponseBody
    public PlayerService getAllPlayers(){

        PlayerService players = new PlayerService();

        try {

            players.getAllPlayers();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return players;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "Players/{pos}", method = RequestMethod.GET)
    @ResponseBody
    public PlayerService getPos(@PathVariable(value = "pos") String pos){

        PlayerService players = new PlayerService();

        try {

            players.getPlayersByPos(pos);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return players;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "Players/Player", method = RequestMethod.GET)
    @ResponseBody
    public PlayerService getPlayer(
            @RequestParam(value = "id") String id
    ) {
        PlayerService player = new PlayerService();

        try {

            player.getPlayerByID(id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return player;
    }
    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="Players/{pos}/{fname}/{lname}", method = RequestMethod.GET)
    @ResponseBody
    public PlayerService getPlayerByName(
            @PathVariable(value ="pos")String pos,
            @PathVariable(value ="fname") String fname,
            @PathVariable(value ="lname") String lname
    )
    {
        PlayerService player = new PlayerService();

        try
        {

            player.getPlayerByName(pos,fname,lname);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return player;
    }

}