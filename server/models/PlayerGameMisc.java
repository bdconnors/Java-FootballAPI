package ritdatabaseproject.server.models;

import java.util.ArrayList;

public class PlayerGameMisc {
    private String gameid;
    private String playerid;
    private int intThr;
    private int fum;
    private int krTD;
    private int prTD;
    private static FootballDatabase db = new FootballDatabase();

    PlayerGameMisc(int[] stats) {
        gameid = String.valueOf(stats[0]);
        playerid = String.valueOf(stats[1]);
        intThr = stats[2];
        fum = stats[3];
        krTD = stats[4];
        prTD = stats[5];

    }
    public PlayerGameMisc() {


    }
    public void fetch() throws DLException {
        //SQL query string
        String query = "SELECT intthr,fum,krtd,prtd FROM playergamemisc WHERE gameid=? AND playerid= ?;";

        ArrayList<String> values = new ArrayList<>();
        try {
            //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
            ArrayList<String[]> info = db.getData(query, values);
            String[] fields = info.get(1);
            //set name to the first field value
            intThr = Integer.parseInt(fields[0]);
            fum = Integer.parseInt(fields[1]);
            krTD = Integer.parseInt(fields[2]);
            prTD = Integer.parseInt(fields[3]);
        } catch (Exception e) {
            System.out.println("No Record Found");

        }

    }
    int post() throws DLException {

        //effected records
        int effected;
        ArrayList<String> values = new ArrayList<>();
        //SQL Insert String
        String insert = "INSERT INTO playergamemisc(gameid,playerid,intthr,fum,krtd,prtd)VALUES(?,?,?,?,?,?);";
        //bind values

        values.add(gameid);
        values.add(playerid);
        values.add(String.valueOf(intThr));
        values.add(String.valueOf(fum));
        values.add(String.valueOf(krTD));
        values.add(String.valueOf(prTD));

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
        String update = "UPDATE playergamemisc SET intthr=?,fum=?,krtd=?,prtd=? WHERE gameid = ? AND playerid=?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(intThr));
        values.add(String.valueOf(fum));
        values.add(String.valueOf(krTD));
        values.add(String.valueOf(prTD));
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
        String delete = "DELETE FROM playergamemisc WHERE gameid =? AND playerid=?;";
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
        return "PlayerStats{" +
                "gameid='" + gameid + '\'' +
                ", playerid='" + playerid + '\'' +
                ", intThr=" + intThr +
                ", fum=" + fum +
                ", krTD=" + krTD +
                ", prTD=" + prTD +
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

    public int getIntThr() {
        return intThr;
    }

    public void setIntThr(int intThr) {
        this.intThr = intThr;
    }

    public int getFum() {
        return fum;
    }

    public void setFum(int fum) {
        this.fum = fum;
    }

    public int getKrTD() {
        return krTD;
    }

    public void setKrTD(int krTD) {
        this.krTD = krTD;
    }

    public int getPrTD() {
        return prTD;
    }

    public void setPrTD(int prTD) {
        this.prTD = prTD;
    }
}