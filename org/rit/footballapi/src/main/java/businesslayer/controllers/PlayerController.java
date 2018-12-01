package main.java.businesslayer.controllers;

import org.springframework.web.bind.annotation.*;
import main.java.datalayer.database.tables.Player;


@RestController
@RequestMapping(PlayerController.BASE_URL)
public class PlayerController{

    public static final String BASE_URL = "api/players";

    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="{playerid}", method = RequestMethod.GET)
    @ResponseBody
    public Player getPlayer(@PathVariable(value = "playerid")String playerid) 
    {
        Player player = new Player(playerid);
        
        try 
        {
            player.fetch();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return player;
    }
 

}
