package main.java.datalayer.account.account;

import main.java.datalayer.database.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User extends DBInterface {

    private String userName;
    private int password;
    private String accessLevel;

    public User(String userName,int password)
    {
        this.userName = userName;
        this.password = password;
    }
    public User(String userName,int password,String accessLevel)
    {
        this.userName = userName;
        this.password = password;
        this.accessLevel = accessLevel;
    }
    public User()
    {}
    public boolean login()throws DLException
    {
        boolean successfulLogin = false;
        try {
            setQuery("/user/getuser.sql");
            setBindValues(new ArrayList<String>() {{
                add(userName);
            }});
            fetch();
            ArrayList<String[]> results = getResults();
            String[] info = results.get(0);
            if (info[0].equals(String.valueOf(password))) {
                accessLevel = info[1];
                successfulLogin = true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return successfulLogin;
    }
    public boolean createUser()throws DLException
    {
      boolean succesfulUserCreation = false;
      setQuery("user/createuser.sql");
      setBindValues(new ArrayList<String>(){{add(userName);add(String.valueOf(password));add(accessLevel);}});
      int effected = post();
      if(effected == 1)
      {
          succesfulUserCreation = true;
      }
      else
      {
          succesfulUserCreation = false;
      }

        return succesfulUserCreation;
    }




}
