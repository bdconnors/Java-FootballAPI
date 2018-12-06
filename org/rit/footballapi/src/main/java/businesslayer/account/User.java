package main.java.businesslayer.account;

import main.java.datalayer.database.DLException;

public class User extends main.java.datalayer.account.account.User {

    public boolean successfulLogin;
    public boolean successfulUserCreation;
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
        return successfulLogin = super.login();

    }
    public boolean createUser()throws DLException
    {
       return successfulUserCreation = super.createUser();
    }



}
