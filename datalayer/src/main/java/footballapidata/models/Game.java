package main.java.footballapi.models;

import main.java.footballapi.database.*;

import java.util.ArrayList;

public class Game {

    private String gameID;
    private String date;
    private String time;
    private String homeTeam;
    private String awayTeam;
    private String loc;
    private static FootballDatabase db = new FootballDatabase();

    public Game(String[] game) {
        gameID = game[0];
        date = game[1];
        time = game[2];
        homeTeam = game[3];
        awayTeam = game[4];
        loc = game[5];

    }

    public Game(String gameID) {
        this.gameID = gameID;
    }

    public Game() {

    }
    public void fetch() throws DLException {  //SQL query string
        String query = "SELECT date,time,homeTeam,awayTeam,loc FROM game WHERE gameid=?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(gameID);
        try { //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
            ArrayList<String[]> info = db.getData(query, values);
            String[] fields = info.get(1);
            //set abrv to the second field value
            date = fields[0];
            time = fields[1];
            homeTeam = fields[2];
            awayTeam = fields[3];
            loc = fields[4];
        } catch (Exception e) {
            System.out.println("No Record Found");
        }
    }

    public int post() throws DLException {
        int effected;
        ArrayList<String> values = new ArrayList<>();
        String insert = "INSERT INTO game(gameid,date,time,homeTeam,awayTeam,loc)VALUES(?,?,?,?,?,?);";
        values.add(gameID);
        values.add(date);
        values.add(time);
        values.add(homeTeam);
        values.add(awayTeam);
        values.add(loc);
        try {
            effected = db.setData(insert, values);
        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();
        }
        return effected;
    }
    public int put() throws DLException {  //effected records
        int effected;
        String update = "UPDATE game SET date=?,time = ?,homeTeam =?,awayTeam = ?,loc = ? WHERE gameid = ?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(date);
        values.add(time);
        values.add(homeTeam);
        values.add(awayTeam);
        values.add(loc);
        values.add(gameID);
        try {
            effected = db.setData(update, values);
        } catch (DLException e) {
            effected = -1;
        }
        return effected;
    }

    public int delete() throws DLException { //effected records
        int effected;
        String delete = "DELETE FROM game WHERE gameid = ?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(gameID);
        try {
            effected = db.setData(delete, values);
        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();
        }
        return effected;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameID='" + gameID + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", loc='" + loc + '\'' +
                '}';
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

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}