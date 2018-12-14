package main.java.businesslayer.account;

import main.java.datalayer.database.DLException;

public class User extends main.java.datalayer.account.User {

    public boolean successfullLogin;
    public boolean successfullUserCreation;
    public boolean successfullLeagueRequest;
    public boolean successfullLeagueRequestResponse;
    public int successfullPlayerAdd;
    public boolean successfullTradeRequest;
    public boolean isSuccessfullTradeRequestReponse;
    public User(String userName)
    {
        super(userName);
    }
    public User(String userName,String password)
    {
        super(userName,password.hashCode());
    }
    public User(String userName,String password,String AccessLevel)
    {
        super(userName,password.hashCode(),AccessLevel);
    }
    public boolean login()throws DLException
    {
        try
        {
            super.login();
            successfullLogin = true;
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
        return isSuccessfullTradeRequestReponse = super.respondToTradeRequest(tradeid,accept);
    }


}
