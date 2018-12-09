package main.java.datalayer.account;

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
    public User(String userName)
    {
        this.userName = userName;
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
      boolean successfulUserCreation = false;
      setQuery("user/createuser.sql");
      setBindValues(new ArrayList<String>(){{add(userName);add(String.valueOf(password));add(accessLevel);}});
      successfulUserCreation = successUpdate(post());


        return successfulUserCreation;
    }
    public boolean leagueRequest(String leagueid)throws DLException
    {
        boolean succesfulLeagueRequest = false;
        setQuery("user/getuser.sql");
        setBindValues(new ArrayList<String>(){{add(userName);}});
        fetch();
        ArrayList<String[]> results = getResults();
        String[] user = results.get(0);
        setQuery("user/leaguerequest.sql");
        setBindValues(new ArrayList<String>(){{add(user[2]);add(leagueid);}});
        succesfulLeagueRequest=successUpdate(post());

        return succesfulLeagueRequest;
    }
    public boolean acceptRequest(String userid,String leagueid)throws DLException
    {
        boolean acceptSuccessful = false;
        setQuery("user/getrequest.sql");
        setBindValues(new ArrayList<String>(){{add(leagueid);add(userid); }});
        fetch();
        ArrayList<String[]> results = getResults();
        String[] result = results.get(0);
        setQuery("user/deleterequest.sql");
        setBindValues(new ArrayList<String>(){{add(result[0]);}});
        if(successUpdate(delete()))
        {   setQuery("user/addusertoroster.sql");
            setBindValues(new ArrayList<String>() {{ add(leagueid);add(userid);}});
            if(acceptSuccessful = successUpdate(post()))
            {
                setQuery("user/getrosterid.sql");
                setBindValues(new ArrayList<String>(){{add(userid);add(leagueid);}});
                fetch();
                ArrayList<String[]> results2 = getResults();
                String[] result2 = results2.get(0);
                setQuery("user/addusertoleague.sql");
                setBindValues(new ArrayList<String>(){{add(result2[0]);add(leagueid);}});
                if(successUpdate(put()))
                {
                    acceptSuccessful = true;
                }else{
                    acceptSuccessful = false;
                }
            }
            else
            { acceptSuccessful = false; }
        }
        else
        { acceptSuccessful = false; }
        return acceptSuccessful;
    }    
    public boolean successUpdate(int update)
    {
        if(update == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
