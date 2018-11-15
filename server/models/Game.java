package models;

import java.util.ArrayList;

public class Game {

    public String gameID;
    public String date;
    public String time;
    public FootballDatabase db = new FootballDatabase();

    public Game(String[] game) {
        gameID = game[0];
        date = game[1];
        time = game[2];

    }

    public Game(String gameID) {
        this.gameID = gameID;
    }

    public Game() {

    }

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

    public int post() throws DLException {
        int effected = 0;
        ArrayList<String> values = new ArrayList<String>();
        String insert = "INSERT INTO game(gameid,date,time)VALUES(?,?,?);";
        values.add(gameID);
        values.add(date);
        values.add(time);
        try {
            effected = db.setData(insert, values);
        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();
        }
        return effected;
    }
    public int put() throws DLException {  //effected records
        int effected = 0;
        String update = "UPDATE game SET date=?,time = ? WHERE gameid = ?;";
        ArrayList<String> values = new ArrayList<String>();
        values.add(date);
        values.add(time);
        values.add(gameID);
        try {
            effected = db.setData(update, values);
        } catch (DLException e) {
            effected = -1;
        }
        return effected;
    }

    public int delete() throws DLException { //effected records
        int effected = 0;
        String delete = "DELETE FROM game WHERE gameid = ?;";
        ArrayList<String> values = new ArrayList<String>();
        values.add(gameID);
        try {
            effected = db.setData(delete, values);
        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();
        }
        return effected;
    }

    public String toString() {
        return "GameID: " + getGameID() + "\n" + "Date: " + getDate() + "\n" + "Time: " + getTime() + "\n";
    }

    public String getGameID() {
        return gameID;
    }

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
}//end Game class