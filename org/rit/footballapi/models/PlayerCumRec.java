package org.rit.footballapi.models;

import org.rit.footballapi.util.DBInterface;
import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class PlayerCumRec extends DBInterface {
    private String playerid;
    private int rec;
    private int recYds;
    private int recTd;
    private int rec2pt;


    public PlayerCumRec(int[] stats) {
        playerid = String.valueOf(stats[0]);
        rec = stats[1];
        recYds = stats[2];
        recTd = stats[3];
        rec2pt = stats[4];
    }
    public PlayerCumRec(String playerid)
    {
        this.playerid = playerid;

    }
    public PlayerCumRec() {


    }
    public void fetch() throws DLException {
        //SQL query string
        String query = "SELECT rec,recyds,rectd,rec2pt FROM playercumrec WHERE playerid= ?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(playerid);

        try {
            //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
            ArrayList<String[]> info = getData(query, values);
            String[] fields = info.get(1);
            //set name to the first field value
            rec = Integer.parseInt(fields[0]);
            recYds = Integer.parseInt(fields[1]);
            recTd = Integer.parseInt(fields[2]);
            rec2pt = Integer.parseInt(fields[3]);
        } catch (Exception e) {
            System.out.println("No Record Found");

        }

    }
    public int post() throws DLException {

        //effected records
        int effected;
        ArrayList<String> values = new ArrayList<>();
        //SQL Insert String
        String insert = "INSERT INTO playercumrec(playerid,rec,recyds,rectd,rec2pt)VALUES(?,?,?,?,?);";
        //bind values

        values.add(playerid);
        values.add(String.valueOf(rec));
        values.add(String.valueOf(recYds));
        values.add(String.valueOf(recTd));
        values.add(String.valueOf(rec2pt));
        try {
            //perform insert and return number of effected
            effected = setData(insert, values);

        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();
        }

        return effected;
    }
    public int put() throws DLException {  //effected records
        int effected;
        //SQL Update String
        String update = "UPDATE playercumrec SET rec=?,recyds=?,rectd=?,rec2pt=? WHERE playerid=?;";
        ArrayList<String> values = new ArrayList<>();

        values.add(String.valueOf(rec));
        values.add(String.valueOf(recYds));
        values.add(String.valueOf(recTd));
        values.add(String.valueOf(rec2pt));
        values.add(playerid);

        try {
            //perform update and return number of effected records
            effected = setData(update, values);
        } catch (DLException e) {
            effected = -1;
        }

        return effected;
    }
    public int delete() throws DLException {

        //effected records
        int effected;
        //SQL delete string
        String delete = "DELETE FROM playercumrec WHERE playerid=?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(playerid);


        try {

            //perform delete and return number of effected records
            effected = setData(delete, values);

        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();

        }

        return effected;
    }

    @Override
    public String toString() {
        return "PlayerCumRec{" +
                "playerid='" + playerid + '\'' +
                ", rec=" + rec +
                ", recYds=" + recYds +
                ", recTd=" + recTd +
                ", rec2pt=" + rec2pt +
                '}';
    }

    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    public int getRec() {
        return rec;
    }

    public void setRec(int rec) {
        this.rec = rec;
    }

    public int getRecYds() {
        return recYds;
    }

    public void setRecYds(int recYds) {
        this.recYds = recYds;
    }

    public int getRecTd() {
        return recTd;
    }

    public void setRecTd(int recTd) {
        this.recTd = recTd;
    }

    public int getRec2pt() {
        return rec2pt;
    }

    public void setRec2pt(int rec2pt) {
        this.rec2pt = rec2pt;
    }
}