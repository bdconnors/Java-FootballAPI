package main.java.businesslayer.controllers;

import main.java.businesslayer.account.User;
import main.java.datalayer.database.models.Player;
import main.java.datalayer.database.models.positions.AllPlayersCumulative;
import main.java.datalayer.database.models.positions.Receiver;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",allowedHeaders ="*")
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "footballapi";
    public User user = null;

    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="User/login", method = RequestMethod.GET)
    @ResponseBody
    public User login(@RequestParam(required = false,value ="userName")String userName, @RequestParam(required = false,value ="password") String password)
    {
        User user = new User(userName,password);
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
    public User create(@RequestParam(value ="userName")String userName, @RequestParam(value ="password") String password, @RequestParam(value ="accessLevel") String accessLevel)
    {
        user = new User(userName,password,accessLevel);
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
    public User leagueReq(@RequestParam(value ="userName")String userName, @RequestParam(value ="leagueid") String leagueid)
    {
        user = new User(userName);
        try {
            user.leagueRequest(leagueid);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return user;
    }
    @CrossOrigin(origins = "*",allowedHeaders ="*")
    @RequestMapping(value="User/respondToReq", method = RequestMethod.GET)
    @ResponseBody
    public User acceptRequest(
            @RequestParam(value ="userName")String userName,
            @RequestParam(value ="pass") String pass,
            @RequestParam(value ="userid")String userid,
            @RequestParam(value ="leagueid") String leagueid,
            @RequestParam(value ="accept")boolean accept
    )
    {
        user = new User(userName,pass);
        try {
            user.respondToRequest(userid,leagueid,accept);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return user;
    }


}
