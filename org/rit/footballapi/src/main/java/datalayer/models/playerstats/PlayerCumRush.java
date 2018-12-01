package main.java.datalayer.models.playerstats;

import main.java.datalayer.database.*;

import java.util.ArrayList;

public class PlayerCumRush {
    private String playerid;
    private int rushAtt;
    private int rushYds;
    private int rush2pt;
    private static FootballDatabase db = new FootballDatabase();

    public PlayerCumRush(int[] stats) {
        playerid = String.valueOf(stats[0]);
        rushAtt = stats[1];
        rushYds = stats[2];
        rush2pt = stats[3];
    }
    public PlayerCumRush(String playerid)
    {
        this.playerid = playerid;

    }
    public PlayerCumRush() {


    }
    public void fetch() throws DLException {
        //SQL query string
        String query = "SELECT rushatt,rushyds,rush2pt FROM playergamerush WHERE playerid= ?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(playerid);

        try {
            //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
            ArrayList<String[]> info = db.getData(query, values);
            String[] fields = info.get(1);
            //set name to the first field value
            rushAtt = Integer.parseInt(fields[0]);
            rushYds = Integer.parseInt(fields[1]);
            rush2pt = Integer.parseInt(fields[2]);

        } catch (Exception e) {
            System.out.println("No Record Found");

        }

    }
    public int post() throws DLException {

        //effected records
        int effected;
        ArrayList<String> values = new ArrayList<>();
        //SQL Insert String
        String insert = "INSERT INTO playercumrush(playerid,rushatt,rushyds,rush2pt)VALUES(?,?,?,?);";
        //bind values
        values.add(playerid);
        values.add(String.valueOf(rushAtt));
        values.add(String.valueOf(rushYds));
        values.add(String.valueOf(rush2pt));

        try {
            //perform insert and return number of effected
            effected = db.setData(insert, values);

        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();

        }

        return effected;

    }
    public int put() throws DLException {  //effected records
        int effected;
        //SQL Update String
        String update = "UPDATE playercumrush SET rushatt=?,rushyds=?,rush2pt=? WHERE playerid=?;";
        ArrayList<String> values = new ArrayList<>();

        values.add(String.valueOf(rushAtt));
        values.add(String.valueOf(rushYds));
        values.add(String.valueOf(rush2pt));
        values.add(playerid);

        try {
            //perform update and return number of effected records
            effected = db.setData(update, values);
        } catch (DLException e) {
            effected = -1;
        }

        return effected;
    }
    public int delete() throws DLException {

        //effected records
        int effected;
        //SQL delete string
        String delete = "DELETE FROM playercumrush WHERE playerid=?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(playerid);


        try {

            //perform delete and return number of effected records
            effected = db.setData(delete, values);

        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();

        }

        return effected;
    }

    @Override
    public String toString() {
        return "PlayerCumRush{" +
                "playerid='" + playerid + '\'' +
                ", rushAtt=" + rushAtt +
                ", rushYds=" + rushYds +
                ", rush2pt=" + rush2pt +
                '}';
    }

    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    public int getRushAtt() {
        return rushAtt;
    }

    public void setRushAtt(int rushAtt) {
        this.rushAtt = rushAtt;
    }

    public int getRushYds() {
        return rushYds;
    }

    public void setRushYds(int rushYds) {
        this.rushYds = rushYds;
    }

    public int getRush2pt() {
        return rush2pt;
    }

    public void setRush2pt(int rush2pt) {
        this.rush2pt = rush2pt;
    }
}