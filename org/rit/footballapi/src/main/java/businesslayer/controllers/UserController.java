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
        User user = new User(userName,password,accessLevel);
        try {
            user.createUser();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return user;
    }

}
