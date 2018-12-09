package main.java.datalayer.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        try
        {   prepareQuery("/user/getuser.sql",userName);
            fetch();
            getQueryResult()[0].equals(String.valueOf(password));
            accessLevel = getQueryResult()[1];
            successfulLogin = true;
        }
        catch(Exception e)
        {   rollbackTrans();
            e.printStackTrace();}
        return successfulLogin;
    }
    public boolean createUser()throws DLException {
        boolean successfulUserCreation = false;

        try
        { prepareQuery("user/createuser.sql", userName, String.valueOf(password), accessLevel);
          startTrans();
          successfulUserCreation = successUpdate(post());
          endTrans();
        }
        catch(Exception e){rollbackTrans(); e.printStackTrace();}

         return successfulUserCreation;
    }
    public boolean leagueRequest(String leagueid)throws DLException
    {
        boolean succesfulLeagueRequest = false;
       try {
             prepareQuery("/user/getuser.sql", userName);
             fetch();
             prepareQuery("user/leaguerequest.sql", getQueryResult()[2], leagueid);
             startTrans();
             succesfulLeagueRequest = successUpdate(post());
             endTrans();
       }
       catch (Exception e)
       {
           rollbackTrans();
           e.printStackTrace();
       }
        return succesfulLeagueRequest;
    }
    public boolean acceptRequest(String userid,String leagueid)throws DLException
    {
        boolean acceptSuccessful = false;
        try
        {      startTrans();
               prepareQuery("user/getrequest.sql",leagueid,userid);
               fetch();
               prepareQuery("user/deleterequest.sql",getQueryResult()[0]);
               delete();
               prepareQuery("user/addusertoroster.sql", leagueid, userid);
               acceptSuccessful = successUpdate(post());
               endTrans();
        }
        catch (Exception e)
        {   rollbackTrans();
            e.printStackTrace();
        }
        return acceptSuccessful;
    }    
    public boolean successUpdate(int update)
    {
        if(update >= 1)
        {return true;}
        else
        { return false; }
    }
    public void prepareQuery(String query,String... args)throws DLException
    {
        ArrayList<String> bindValues = new ArrayList<>();
        for(String arg:args)
        { bindValues.add(arg); }
        setQuery(query);
        setBindValues(bindValues);
    }
    @JsonIgnore
    public String[] getQueryResult()
    {
        ArrayList<String[]> results = getResults();
        String[] info = results.get(0);
        return info;
    }

}
