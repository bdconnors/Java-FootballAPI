package main.java.businesslayer.account;

import main.java.datalayer.database.DLException;

public class User extends main.java.datalayer.account.User {

    public boolean successfullLogin;
    public boolean successfullUserCreation;
    public boolean successfullLeagueRequest;
    public boolean successfullRequestResponse;
    public boolean successfullRosterCreation;
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
    public boolean leagueRequest(String leagueid)throws DLException
    {
        return successfullLeagueRequest = super.leagueRequest(leagueid);
    }
    public boolean respondToRequest(String userid,String leagueid,boolean accept)throws DLException
    {
        return successfullRequestResponse = super.respondToRequest(userid,leagueid,accept);
    }
    public boolean createRoster(String[] players)throws DLException
    {
        return successfullRosterCreation = super.createRoster(players);
    }


}
