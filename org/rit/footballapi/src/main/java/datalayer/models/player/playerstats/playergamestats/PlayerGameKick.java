package main.java.datalayer.models.player.playerstats.playergamestats;

import main.java.datalayer.database.*;

import java.util.ArrayList;

public class PlayerGameKick {
    private String gameid;
    private String playerid;
    private int fgAtt;
    private int fgMd;
    private int xpAtt;
    private int xpMd;
    private static FootballDatabase db = new FootballDatabase();

    public PlayerGameKick(int[] stats)
    {
        gameid = String.valueOf(stats[0]);
        playerid = String.valueOf(stats[1]);
        fgAtt = stats[2];
        fgMd = stats[3];
        xpAtt = stats[4];
        xpMd = stats[5];

    }
    public PlayerGameKick(String playerid,String gameid) {

        this.playerid = playerid;
        this.gameid = gameid;


    }
    public PlayerGameKick()
    {

    }
    public void fetch() throws DLException {
        //SQL query string
        String query = "SELECT fgatt,fgmd,xpatt,xpmd FROM playergamekick WHERE gameid=? AND playerid= ?;";

        ArrayList<String> values = new ArrayList<>();
        try {
            //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
            ArrayList<String[]> info = db.getData(query, values);
            String[] fields = info.get(1);
            fgAtt = Integer.parseInt(fields[0]);
            fgMd = Integer.parseInt(fields[1]);
            xpAtt = Integer.parseInt(fields[2]);
            xpMd = Integer.parseInt(fields[3]);

        } catch (Exception e) {
            System.out.println("No Record Found");
        }

    }
    public int post() throws DLException {

        //effected records
        int effected;
        ArrayList<String> values = new ArrayList<>();
        //SQL Insert String
        String insert = "INSERT INTO playergamekick(gameid,playerid,fgatt,fgmd,xpatt,xpmd)VALUES(?,?,?,?,?,?);";
        //bind values

        values.add(gameid);
        values.add(playerid);
        values.add(String.valueOf(fgAtt));
        values.add(String.valueOf(fgMd));
        values.add(String.valueOf(xpAtt));
        values.add(String.valueOf(xpMd));

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
        String update = "UPDATE playergamekick SET fgatt=?,fgmd=?,xpatt=?,xpmd=? WHERE gameid = ? AND playerid=?;";
        ArrayList<String> values = new ArrayList<>();

        values.add(String.valueOf(fgAtt));
        values.add(String.valueOf(fgMd));
        values.add(String.valueOf(xpAtt));
        values.add(String.valueOf(xpMd));
        values.add(gameid);
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
        String delete = "DELETE FROM playergamekick WHERE gameid =? AND playerid=?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(gameid);
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
        return "PlayerGameKick{" +
                "gameid='" + gameid + '\'' +
                ", playerid='" + playerid + '\'' +
                ", fgAtt=" + fgAtt +
                ", fgMd=" + fgMd +
                ", xpAtt=" + xpAtt +
                ", xpMd=" + xpMd +
                '}';
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    public int getFgAtt() {
        return fgAtt;
    }

    public void setFgAtt(int fgAtt) {
        this.fgAtt = fgAtt;
    }

    public int getFgMd() {
        return fgMd;
    }

    public void setFgMd(int fgMd) {
        this.fgMd = fgMd;
    }

    public int getXpAtt() {
        return xpAtt;
    }

    public void setXpAtt(int xpAtt) {
        this.xpAtt = xpAtt;
    }

    public int getXpMd() {
        return xpMd;
    }

    public void setXpMd(int xpMd) {
        this.xpMd = xpMd;
    }
}