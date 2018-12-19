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
    public String accessLevel;
    public String teamid;
    public String leagueid;
    public String teamname;
    public String leaguename;

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
            if(getQueryResult()[0].equals(String.valueOf(password)));
            {   accessLevel = getQueryResult()[1];
                userid = getQueryResult()[2];
                successfulLogin = true;
                if(hasLeague())
                { leagueid =getQueryResult()[0];
                    if (hasTeam()) {
                        prepareQuery("getteamid.sql", userid);
                        fetch();
                        teamid = getQueryResult()[0];
                        teamname = getQueryResult()[2];
                        leaguename = getQueryResult()[3];

                    }
                }
            }

        }
        catch(Exception e)
        {
          successfulLogin = false;
        }
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
    public boolean hasTeam()throws DLException
    {
        boolean hasTeam = false;
        try {
            prepareQuery("getteamid.sql", userid);
            fetch();
            if(getQueryResult()!= null)
            {hasTeam = true;}
            else
            { hasTeam = false; }
        }
        catch(Exception e)
        {
           hasTeam = false;
        }

        return hasTeam;
    }
    public boolean tradeRequest(String teamid,String partnerid, String toTrade,String toReceive)throws DLException
    {
        prepareQuery("traderequest.sql",teamid,partnerid,toTrade,toReceive);
        return successUpdate(post());

    }
    public boolean createLeague(String leaguename,String numteams,String scoring)throws DLException
    {
        League league = new League(userid,leaguename,scoring,numteams);
        return successUpdate(league.post());
    }
    public boolean respondToTradeRequest(String tradeid,boolean accept)throws DLException
    {
        boolean succesfulTrade = false;

      try {
          prepareQuery("gettraderequest.sql", tradeid);
          fetch();
          String[] request = getQueryResult();
          super.startTrans();
          prepareQuery("deletetraderequest.sql", tradeid);
          succesfulTrade = successUpdate(delete());
          if (accept == true) {
              prepareQuery("accepttrade.sql", request[0], request[3]);
              put();
              prepareQuery("accepttrade.sql", request[1], request[2]);
              succesfulTrade = successUpdate(put());
              super.endTrans();
          }
          return succesfulTrade;
      }
      catch(Exception e)
      {
          rollbackTrans();
      }

        return succesfulTrade;
    }
    public boolean leagueRequest(String leagueid,String teamname)throws DLException
    {  boolean succesfulLeagueRequest = false;
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
        {
               if(isManager())
               {
                   startTrans();
                   prepareQuery("getleaguerequest.sql",requestid);
                   fetch();
                   String[] info = getQueryResult();
                   prepareQuery("deleterequest.sql", requestid);
                   delete();
                   if(accept == true) {
                       prepareQuery("createuserteam.sql",info[0], info[1],info[2]);
                       responseSuccesful = successUpdate(post());
                   }else
                   {
                       responseSuccesful = true;
                   }
                   endTrans();
               }
        }
        catch (Exception e)
        {
            rollbackTrans();
        }
        return responseSuccesful;
    }
    public boolean addPlayer(String teamid,String playerid)throws DLException
    {   Roster roster = null;
       if(isManager()) {
           roster = new Roster(teamid, playerid);
           return successUpdate(roster.post());
       }
       return false;
    }
    public boolean isManager()throws DLException
    {
      boolean isManager = false;
      prepareQuery("getuserbyid.sql",userid);
      fetch();
      if(getQueryResult()[0].equals("MNGR"))
      {
         isManager = true;
      }
      
      return isManager;
    
    }
    public boolean hasLeague()throws DLException
    {
        boolean hasLeague = false;
        prepareQuery("hasleague.sql",userid);
        fetch();
        if(getQueryResult()!= null)
        {hasLeague= true;}
        else
        { hasLeague = false; }
        return hasLeague;

    }
    public boolean setRoster(String teamid)throws DLException
    {
        prepareQuery("rosterset.sql",teamid);
        return successUpdate(put());

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

    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
