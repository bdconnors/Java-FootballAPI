package org.rit.footballapi.controllers;

import org.rit.footballapi.services.UserService;
import org.rit.footballapi.util.DLException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",allowedHeaders ="*")
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "footballapi";
    public UserService user = null;

    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="User/login", method = RequestMethod.GET)
    @ResponseBody
    public UserService login(@RequestParam(required = false,value ="userName")String userName, @RequestParam(required = false,value ="password") String password)
    {
        UserService user = new UserService(userName,password);
        try {
            user.login();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return user;
    }
    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="User/create", method = RequestMethod.GET)
    @ResponseBody
    public UserService create(
            @RequestParam(value ="userName")String userName,
            @RequestParam(value ="password") String password,
            @RequestParam(value ="accessLevel") String accessLevel)
    {
        user = new UserService(userName,password,accessLevel);
        try {
            user.createUser();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return user;
    }
    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="User/leagueReq", method = RequestMethod.GET)
    @ResponseBody
    public UserService leagueReq(
            @RequestParam(value ="userName")String userName,
            @RequestParam(value ="pass")String pass,
            @RequestParam(value ="leagueid") String leagueid,
            @RequestParam(value ="teamname")String teamname)
    {
        user = new UserService(userName,pass);
        try {
            user.leagueRequest(leagueid,teamname);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return user;
    }
    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="User/respondToLeagueReq", method = RequestMethod.GET)
    @ResponseBody
    public UserService acceptRequest(
            @RequestParam(value ="userName")String userName,
            @RequestParam(value ="pass") String pass,
            @RequestParam(value ="requestid") String requestid,
            @RequestParam(value ="accept")boolean accept
    )throws DLException
    {
        user = new UserService(userName,pass);
        try {
            user.respondToLeagueRequest(requestid,accept);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return user;
    }
    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="User/addPlayers", method = RequestMethod.GET)
    @ResponseBody
    public UserService loadRoster(
            @RequestParam(value ="userName")String userName,
            @RequestParam(value ="pass")String pass,
            @RequestParam(value ="teamid")String teamid,
            @RequestParam(value ="players")String[] players
    )
    {


        UserService user = new UserService(userName,pass);
        try
        {
            user.login();
            for(String player:players)
            {
                user.addPlayer(teamid,player);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return user;
    }
    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="User/tradeReq", method = RequestMethod.GET)
    @ResponseBody
    public UserService tradeReq(@RequestParam(value ="userName")String userName,
                                @RequestParam(value ="pass") String pass,
                                @RequestParam(value ="teamid") String teamid,
                                @RequestParam(value ="partnerid")String partnerid,
                                @RequestParam(value ="toTrade")String toTrade,
                                @RequestParam(value ="toReceive")String toReceive)
    {
        user = new UserService(userName,pass);
        try {
            user.tradeRequest(teamid,partnerid,toTrade,toReceive);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return user;
    }

    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="User/respondToTradeReq", method = RequestMethod.GET)
    @ResponseBody
    public UserService respondToTradeReq(@RequestParam(value ="userName")String userName,
                                         @RequestParam(value ="pass") String pass,
                                         @RequestParam(value ="tradeid")String tradeid,
                                         @RequestParam(value ="accept")boolean accept)
    {
        user = new UserService(userName,pass);
        try {
            user.respondToTradeRequest(tradeid,accept);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return user;
    }

}
