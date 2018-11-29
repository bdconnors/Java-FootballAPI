package ritdatabaseproject.server.models;

import java.util.ArrayList;

public class PlayerCumMisc {
    private String playerid;
    private int gplayed;
    private int intThr;
    private int fum;
    private int krTD;
    private int prTD;
    private static FootballDatabase db = new FootballDatabase();

    PlayerCumMisc(int[] stats) {
        playerid = String.valueOf(stats[0]);
        gplayed = stats[1];
        intThr = stats[2];
        fum = stats[3];
        krTD = stats[4];
        prTD = stats[5];

    }
    public PlayerCumMisc(String playerid)
    {
        this.playerid = playerid;

    }
    public PlayerCumMisc() {


    }
    public void fetch() throws DLException {
        //SQL query string
        String query = "SELECT gplayed,intthr,fum,krtd,prtd FROM playercummisc WHERE playerid= ?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(playerid);
        try {
            //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
            ArrayList<String[]> info = db.getData(query, values);
            String[] fields = info.get(1);
            //set name to the first field value
            gplayed =  Integer.parseInt(fields[0]);
            intThr = Integer.parseInt(fields[1]);
            fum = Integer.parseInt(fields[2]);
            krTD = Integer.parseInt(fields[3]);
            prTD = Integer.parseInt(fields[4]);
        } catch (Exception e) {
            System.out.println("No Record Found");

        }

    }
    int post() throws DLException {

        //effected records
        int effected;
        ArrayList<String> values = new ArrayList<>();
        //SQL Insert String
        String insert = "INSERT INTO playercummisc(playerid,gplayed,intthr,fum,krtd,prtd)VALUES(?,?,?,?,?,?);";
        //bind values

        values.add(playerid);
        values.add(String.valueOf(gplayed));
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
        String update = "UPDATE playercummisc SET gplayed=?,intthr=?,fum=?,krtd=?,prtd=? WHERE playerid=?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(gplayed));
        values.add(String.valueOf(intThr));
        values.add(String.valueOf(fum));
        values.add(String.valueOf(krTD));
        values.add(String.valueOf(prTD));
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
        String delete = "DELETE FROM playercummisc WHERE playerid=?;";
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
        return "PlayerCumMisc{" +
                "playerid='" + playerid + '\'' +
                ", gplayed=" + gplayed +
                ", intThr=" + intThr +
                ", fum=" + fum +
                ", krTD=" + krTD +
                ", prTD=" + prTD +
                '}';
    }

    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    public int getGplayed() {
        return gplayed;
    }

    public void setGplayed(int gplayed) {
        this.gplayed = gplayed;
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
