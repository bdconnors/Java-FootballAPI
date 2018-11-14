package models;//----------------------------------------------------------------------------------------------
//Dev Name: Brandon Connors
//Filename: Player.Java
//Date: 11/8/18
//Program Description: Player Object to hold data on a player for a fantasy football database
//----------------------------------------------------------------------------------------------
//--------------Import Statements---------------------------------------------------------------

import java.util.ArrayList;

//----------------------------------------------------------------------------------------
//Class Name: Team
//Description: This class has 16 methods. 
//post(),put(),delete() and toString()
//6 set mutators for its class variables
//6 get mutators for its class variables
//----------------------------------------------------------------------------------------
public class Game {  //--------------Class Variables---------------------------------------------------------
    public String gameID;
    public String date;
    public String time;
    public FootballDatabase db = new FootballDatabase();

    //--------------------------------------------------------------------------------------
    //--------------------Constructor------------------------------------------------------
    //Parameter Type: String[]
    //Description:takes in a string[] of player data and sets team variables
    //------------------------------------------------------------------------------------
    public Game(String[] game) {
        gameID = game[0];
        date = game[1];
        time = game[2];

    }

    //--------------------Constructor------------------------------------------------------
    //Parameter Type: String
    //Description:takes in a team name and sets name variable
    //------------------------------------------------------------------------------------
    public Game(String gameID) {
        this.gameID = gameID;
    }

    //--------------------Constructor------------------------------------------------------
    //Parameter Type: none
    //Description:default constructor
    //------------------------------------------------------------------------------------
    public Game() {

    }

    //-----------------------------------------------------------------------------------------------------
    //Method Name: fetch
    //Description: Issues a query to the database returning player data associated with
    //this team obejects set name and sets the remaining class variables equal to the returned data
    //----------------------------------------------------------------------------------------------------
    public void fetch() throws DLException {  //SQL query string
        String query = "SELECT date,time FROM game WHERE gameid=?;";
        ArrayList<String> values = new ArrayList<String>();
        values.add(gameID);
        try { //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
            ArrayList<String[]> info = db.getData(query, values);
            String[] fields = info.get(1);
            //set name to the first field value
            gameID = fields[0];
            //set abrv to the second field value
            date = fields[1];
            time = fields[2];
        } catch (Exception e) {
            System.out.println("No Record Found");
        }
    }

    //---------------------------------------------------------------------------------------------
    //Method Name: post
    //Description: inserts a record into the database using this objects class variables for
    //the information in the corresponding fields
    //---------------------------------------------------------------------------------------------
    public int post() throws DLException {  //effected records
        int effected = 0;
        ArrayList<String> values = new ArrayList<String>();
        //SQL Insert String
        String insert = "INSERT INTO game(gameid,date,time)VALUES(?,?,?);";
        //bind values
        values.add(gameID);
        values.add(date);
        values.add(time);
        try { //perform insert and return number of effected
            effected = db.setData(insert, values);
        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();
        }
        return effected;
    }

    //updates database record that corresponds to this object's 'ID' variable and then returns the number of records effected in the database
    public int put() throws DLException {  //effected records
        int effected = 0;
        //SQL Update String
        String update = "UPDATE game SET date=?,time = ? WHERE gameid = ?;";
        ArrayList<String> values = new ArrayList<String>();
        values.add(date);
        values.add(time);
        values.add(gameID);
        try {  //perform update and return number of effected records
            effected = db.setData(update, values);
        } catch (DLException e) {
            effected = -1;
        }
        return effected;
    }

    //---------------------------------------------------------------------------------------------
    //Method Name: delete
    //Description:deletes team record from database using their name
    //---------------------------------------------------------------------------------------------
    public int delete() throws DLException { //effected records
        int effected = 0;
        //SQL delete string
        String delete = "DELETE FROM game WHERE gameid = ?;";
        ArrayList<String> values = new ArrayList<String>();
        values.add(gameID);
        try {//perform delete and return number of effected records
            effected = db.setData(delete, values);
        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();
        }
        return effected;
    }

    //toString
    public String toString() {
        return "GameID: " + getGameID() + "\n" + "Date: " + getDate() + "\n" + "Time: " + getTime() + "\n";
    }

    //getters
    public String getGameID() {
        return gameID;
    }

    //setters
    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}//end player class