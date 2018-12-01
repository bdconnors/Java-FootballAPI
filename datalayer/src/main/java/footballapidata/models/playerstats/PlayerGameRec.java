package main.java.footballapidata.models.playerstats;

import main.java.footballapidata.database.*;

import java.util.ArrayList;

public class PlayerGameRec {
    private String gameid;
    private String playerid;
    private int rec;
    private int recYds;
    private int recTd;
    private int rec2pt;
    private static FootballDatabase db = new FootballDatabase();

    public PlayerGameRec(int[] stats) {
        gameid = String.valueOf(stats[0]);
        playerid = String.valueOf(stats[1]);
        rec = stats[2];
        recYds = stats[3];
        recTd = stats[4];
        rec2pt = stats[5];
    }
    public PlayerGameRec() {


    }
    public void fetch() throws DLException {
        //SQL query string
        String query = "SELECT rec,recyds,rectd,rec2pt FROM playergamerec WHERE gameid=? AND playerid= ?;";

        ArrayList<String> values = new ArrayList<>();
        try {
            //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
            ArrayList<String[]> info = db.getData(query, values);
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
        String insert = "INSERT INTO playergamerec(gameid,playerid,rec,recyds,rectd,rec2pt)VALUES(?,?,?,?,?,?);";
        //bind values

        values.add(gameid);
        values.add(playerid);
        values.add(String.valueOf(rec));
        values.add(String.valueOf(recYds));
        values.add(String.valueOf(recTd));
        values.add(String.valueOf(rec2pt));
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
        String update = "UPDATE playergamerec SET rec=?,recyds=?,rectd=?,rec2pt=? WHERE gameid = ? AND playerid=?;";
        ArrayList<String> values = new ArrayList<>();

        values.add(String.valueOf(rec));
        values.add(String.valueOf(recYds));
        values.add(String.valueOf(recTd));
        values.add(String.valueOf(rec2pt));
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
        String delete = "DELETE FROM playergamerec WHERE gameid =? AND playerid=?;";
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
        return "PlayerGameRec{" +
                "gameid='" + gameid + '\'' +
                ", playerid='" + playerid + '\'' +
                ", rec=" + rec +
                ", recYds=" + recYds +
                ", recTd=" + recTd +
                ", rec2pt=" + rec2pt +
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