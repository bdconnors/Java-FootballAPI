package org.rit.footballapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.rit.footballapi.util.DBInterface;
import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class User extends DBInterface {

    public String userid;
    private String userName;
    private int password;
    private String accessLevel;

    public User(String userName,int password)
    {
        this.userName = userName;
        this.password = password;
    }
    public User(String userid)
    {
        this.userid = userid;
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
        {   prepareQuery("getuser.sql",userName);
            fetch();
            getQueryResult()[0].equals(String.valueOf(password));
            accessLevel = getQueryResult()[1];
            userid = getQueryResult()[2];
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
        { prepareQuery("createuser.sql", userName, String.valueOf(password), accessLevel);
          startTrans();
          successfulUserCreation = successUpdate(post());
          endTrans();
        }
        catch(Exception e){rollbackTrans(); e.printStackTrace();}

         return successfulUserCreation;
    }
    public boolean tradeRequest(String teamid,String partnerid, String toTrade,String toReceive)throws DLException
    {
        login();
        prepareQuery("traderequest.sql",teamid,partnerid,toTrade,toReceive);
        return successUpdate(post());

    }
    public boolean respondToTradeRequest(String tradeid,boolean accept)throws DLException
    {
        boolean succesfulTrade = false;
        login();
        prepareQuery("gettraderequest.sql",tradeid);
        fetch();
        String[] request = getQueryResult();
        startTrans();
        prepareQuery("deletetraderequest.sql",tradeid);
        succesfulTrade = successUpdate(put());
        if(accept == true)
        {
            prepareQuery("accepttrade.sql", request[0], request[3]);
            put();
            prepareQuery("accepttrade.sql", request[1], request[2]);
            succesfulTrade = successUpdate(put());
        }
        endTrans();

        return succesfulTrade;
    }
    public boolean leagueRequest(String leagueid,String teamname)throws DLException
    {
        boolean succesfulLeagueRequest = false;
       try {
             prepareQuery("leaguerequest.sql",userid,leagueid,teamname);
             succesfulLeagueRequest = successUpdate(post());

       }
       catch (Exception e)
       {
           rollbackTrans();
           e.printStackTrace();
       }
        return succesfulLeagueRequest;
    }
    public boolean respondToLeagueRequest(String requestid,boolean accept)throws DLException
    {
        boolean responseSuccesful = false;
        try
        {      login();
               if(!(accessLevel.equals("STD")))
               {
                   startTrans();
                   prepareQuery("getleaguerequest.sql",requestid);
                   fetch();
                   prepareQuery("deleterequest.sql", requestid);
                   delete();
                   if(accept == true) {
                       prepareQuery("createuserteam.sql",getQueryResult()[0], getQueryResult()[1],getQueryResult()[2]);
                       responseSuccesful = successUpdate(post());
                   }else
                   {
                       responseSuccesful = true;
                   }
                   endTrans();
               }
        }
        catch (Exception e)
        {   rollbackTrans();
            e.printStackTrace();
        }
        return responseSuccesful;
    }
    public boolean addPlayer(String teamid,String playerid)throws DLException
    {
       Roster roster = new Roster(teamid,playerid);
       return successUpdate(roster.post());
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
