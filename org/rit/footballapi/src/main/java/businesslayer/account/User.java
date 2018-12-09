package main.java.businesslayer.account;

import main.java.datalayer.database.DLException;

public class User extends main.java.datalayer.account.User {

    public boolean successfullLogin;
    public boolean successfullUserCreation;
    public boolean successfullLeagueRequest;
    public boolean successfullLeagueJoin;
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
        return successfullLogin = super.login();

    }
    public boolean createUser()throws DLException
    {
       return successfullUserCreation = super.createUser();
    }
    public boolean leagueRequest(String leagueid)throws DLException
    {
        return successfullLeagueRequest = super.leagueRequest(leagueid);
    }
    public boolean acceptRequest(String userid,String leagueid)throws DLException
    {
        return successfullLeagueJoin = super.acceptRequest(userid,leagueid);
    }


}
