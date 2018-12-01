package main.java.footballapidata.models;

import main.java.footballapidata.database.*;

import java.util.ArrayList;

public class User {

    private String userName;
    private String password;
    private String name;
    private String access;
    private static FootballDatabase db = new FootballDatabase();

    //default constructor
    public User()
    {

    }
    //constructor with only ID specified
    public User(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }
    //constructor with all class variables specified
    public User(String userName,String password,String name,String access)
    {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.access= access;

    }
    //querys database for a record that corresponds to this object's 'ID' variable,
    // then sets the rest of the class variables to the corresponding information of the returned record
    public void fetch()throws DLException
    {
        //SQL query string
        String query = "SELECT Name,Access FROM users WHERE UserName = ?;";
        ArrayList<String> values = new ArrayList<String>();
        values.add(userName);

        try
        {
            //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
            ArrayList<String[]> info = db.getData(query,values);
            String[] fields = info.get(1);
            //set capacity to the third field value
            name = fields[0];
            access = fields[1];
            //close connection
        }
        catch(Exception e)
        {
            System.out.println("No Record Found");

        }

    }
    //updates database record that corresponds to this object's 'ID' variable and then returns the number of records effected in the database
    public int put()throws DLException
    {  //effected records
        int effected = 0;
        //SQL Update String
        String update = "UPDATE users SET UserName = ? , Password = ? , Name = ?, Access = ? WHERE UserName = ?;";
        ArrayList<String> values = new ArrayList<String>();

        values.add(userName);
        values.add(password);
        values.add(name);
        values.add(access);

        try
        {
            //perform update and return number of effected records
            effected = db.setData(update,values);
        }
        catch(DLException e)
        {
            effected = -1;
        }

        return effected;
    }
    //inserts a record into the database using this objects class variables for the information in the corresponding fields
    public int post()throws DLException
    {

        //effected records
        int effected = 0;
        ArrayList<String> values = new ArrayList<String>();
        //SQL Insert String
        String insert = "INSERT INTO users(UserName,Password,Name,Access)VALUES(?,?,?,?);";
        values.add(userName);
        values.add(password);
        values.add(name);
        values.add(access);

        try
        {
            //perform insert and return number of effected records
            effected = db.setData(insert,values);

        }
        catch(DLException e)
        {
            effected = -1;
            e.printStackTrace();

        }

        return effected;

    }
    //deletes a record from the database that corresponds to this objects 'ID' variable
    public int delete()throws DLException
    {

        //effected records
        int effected = 0;
        //SQL delete string
        String delete = "DELETE FROM users WHERE UserName = ?;";
        ArrayList<String> values = new ArrayList<String>();
        values.add(userName);


        try
        {

            //perform delete and return number of effected records
            effected = db.setData(delete,values);

        }
        catch(DLException e)
        {
            effected = -1;
            e.printStackTrace();

        }

        return effected;
    }
    public void swap(int id)throws DLException
    {
        try
        {  //start transaction
            db.startTrans();

            //strings for query and update
            String query = "SELECT UserName FROM Equipment WHERE UserName= ?";
            String update ="UPDATE users SET UserName = ? WHERE UserName= ?";

            //values for query and update
            ArrayList<String> values1 = new ArrayList<String>();
            ArrayList<String> values2 = new ArrayList<String>();

            //add ID to values for query
            values1.add(userName);

            //get the data from equipment of specified id
            ArrayList<String[]> data = db.getData(query,values1);

            //get the name of new equipment from query
            String[] userData =  data.get(1);

            //add current name and add to update values
            values2.add(userName);
            //add the name of new equipment to update values
            values2.add(userData[0]);
            //change the name of equipment in database
            db.setData(update,values2);
            //change name of current equipment to name of new equipment from query
            name = userData[0];

            //end tranasaction
            db.endTrans();

        }
        catch(DLException e)
        {
            e.printStackTrace();
        }
    }
    public boolean login()throws DLException
    {
        boolean valid;
        String query = "SELECT UserName,Password,Name,Access FROM users WHERE UserName = ?";
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<String[]> data;

        try
        {
            values.add(userName);
            data = db.getData(query,values);

            String[] info = data.get(1);
            if(info[1].equals(password))
            {
                valid = true;
                name = info[2];
                access = info[3];
            }
            else {
                valid = false;
            }
        }
        catch(Exception e)
        {
            valid = false;

        }

        return valid;
    }
    @Override
    public String toString()
    {
        return "UserName: " +getUserName()+"\n"+"Password: "  +getPassword()+"\n"+ "Name: " + getName()+ "\n"+"Access: "+getAccess()+"\n";

    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setAccess(String access)
    {
        this.access = access;
    }


    public String getUserName()
    {
        return userName;
    }
    public String getPassword()
    {
        return password;
    }
    public String getName()
    {
        return name;
    }
    public String getAccess()
    {
        return access;
    }


}
