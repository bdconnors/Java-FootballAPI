package org.rit.footballapi.services;


import org.rit.footballapi.util.DLException;

public class UserService extends org.rit.footballapi.models.User {

    public boolean successfullLogin;
    public boolean successfullUserCreation;
    public boolean successfullLeagueRequest;
    public boolean successfullLeagueRequestResponse;
    public int successfullPlayerAdd;
    public boolean successfullRosterSet;
    public boolean successfullTradeRequest;
    public boolean successfullTradeRequestReponse;

    public UserService(String userid) { super(userid); }
    public UserService(String userName, String password)
    {
        super(userName,password.hashCode());
    }
    public UserService(String userName, String password, String AccessLevel)
    {
        super(userName,password.hashCode(),AccessLevel);
    }
    public boolean login()throws DLException
    {
        try
        {
            if(super.login()) {
                successfullLogin = true;
                userid = super.getUserid();
            }
            else successfullLogin = false;
        }
        catch (Exception e)
        {
            successfullLogin = false;
            e.printStackTrace();
        }
        return successfullLogin;
    }
    public boolean createUser()throws DLException
    {
       return successfullUserCreation = super.createUser();
    }
    public boolean leagueRequest(String leagueid,String teamname)throws DLException
    {
        return successfullLeagueRequest = super.leagueRequest(leagueid,teamname);
    }
    public boolean respondToLeagueRequest(String requestid,boolean accept)throws DLException
    {
        return successfullLeagueRequestResponse = super.respondToLeagueRequest(requestid,accept);
    }
    public boolean addPlayer(String teamid,String playerid)throws DLException
    {
        if(super.addPlayer(teamid,playerid))
        { successfullPlayerAdd++; }
        return true;
    }
      public boolean tradeRequest(String teamid, String partnerid, String toTrade,String toReceive)throws DLException
    {
        return successfullTradeRequest = super.tradeRequest(teamid,partnerid,toTrade,toReceive);
    }
    public boolean respondToTradeRequest(String tradeid,boolean accept)throws DLException
    {
        return successfullTradeRequestReponse = super.respondToTradeRequest(tradeid,accept);
    }
    public boolean setRoster(String teamid)throws DLException
    {
        return successfullRosterSet = super.setRoster(teamid);
    }


}
