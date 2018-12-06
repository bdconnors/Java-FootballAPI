package main.java.businesslayer.controllers;

import org.springframework.web.bind.annotation.*;
import main.java.datalayer.database.models.Game;


@RestController
@RequestMapping(GameController.BASE_URL)
public class GameController{

    public static final String BASE_URL = "api/games";

    @CrossOrigin(origins = "http://129.21.17.50:8080", maxAge = 3600)
    @RequestMapping(value="{gameid}", method = RequestMethod.GET)
    @ResponseBody
    public Game getGame(@PathVariable(value = "gameid")String gameid) 
    {
        Game game = new Game(gameid);
        
        try 
        {
            game.fetch();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return game;
    }
 

}
