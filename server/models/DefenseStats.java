package models;

import java.util.ArrayList;

public class DefenseStats {

    public String gameid;
    public String team;
    public int pa;
    public int sck;
    public int sfty;
    public int intTD;
    public int fumTD;
    public int krTD;
    public int prTD;
    public int intc;
    public int fum;
    public int kBlk;
    public int xpBlk;
    public FootballDatabase db = new FootballDatabase();

    public DefenseStats(int[] stats) {
        gameid = String.valueOf(stats[0]);
        pa = stats[1];
        sck = stats[2];
        sfty = stats[3];
        intTD = stats[4];
        fumTD = stats[5];
        krTD = stats[6];
        prTD = stats[7];
        intc = stats[8];
        fum = stats[9];
        kBlk = stats[10];
        xpBlk = stats[11];
    }

    public DefenseStats(String team, String gameID) {
        this.team = team;
        this.gameid = gameid;

    }

    public DefenseStats() {


    }

    public DefenseStats(String team, String gameID) {
        this.team = team;
        this.gameid = gameid;

    }

    public void fetch() throws DLException {
        String query = "SELECT pa,sck,sfty,inttd,fumtd,krtd,prtd,intc,fum,kblk,xpblk FROM defensestats WHERE gameid=? AND team = ?;";
        ArrayList<String> values = new ArrayList<String>();
        values.add(gameid);
        values.add(team);
        try {
            ArrayList<String[]> info = db.getData(query, values);
            String[] fields = info.get(1);
            pa = Integer.parseInt(fields[1]);
            sck = Integer.parseInt(fields[2]);
            sfty = Integer.parseInt(fields[3]);
            intTD = Integer.parseInt(fields[4]);
            fumTD = Integer.parseInt(fields[5]);
            krTD = Integer.parseInt(fields[6]);
            prTD = Integer.parseInt(fields[7]);
            intc = Integer.parseInt(fields[8]);
            fum = Integer.parseInt(fields[9]);
            kBlk = Integer.parseInt(fields[10]);
            xpBlk = Integer.parseInt(fields[11]);

        } catch (Exception e) {
            System.out.println("No Record Found");
        }
    }

    public int post() throws DLException {
        int effected = 0;
        ArrayList<String> values = new ArrayList<String>();
        String insert = "INSERT INTO defensestats(gameid,team,pa,sck,sfty,inttd,fumtd,krtd,prtd,intc,fum,kblk,xpblk)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        values.add(String.valueOf(gameid));
        values.add(String.valueOf(team));
        values.add(String.valueOf(pa));
        values.add(String.valueOf(sck));
        values.add(String.valueOf(sfty));
        values.add(String.valueOf(intTD));
        values.add(String.valueOf(fumTD));
        values.add(String.valueOf(krTD));
        values.add(String.valueOf(prTD));
        values.add(String.valueOf(intc));
        values.add(String.valueOf(fum));
        values.add(String.valueOf(kBlk));
        values.add(String.valueOf(xpBlk));
        try {
            effected = db.setData(insert, values);
        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();
        }
        return effected;
    }

    public int put() throws DLException {
        int effected = 0;
        String update = "UPDATE defensestats SET pa=?,sck=?,sfty=?,intTD=?,fumTD=?,krTD=?,prTD=?,intc=?,fum=?,kBlk=?,xpBlk=?WHERE gameid = ? AND team=?;";
        ArrayList<String> values = new ArrayList<String>();
        values.add(String.valueOf(pa));
        values.add(String.valueOf(sck));
        values.add(String.valueOf(sfty));
        values.add(String.valueOf(intTD));
        values.add(String.valueOf(fumTD));
        values.add(String.valueOf(krTD));
        values.add(String.valueOf(prTD));
        values.add(String.valueOf(intc));
        values.add(String.valueOf(fum));
        values.add(String.valueOf(kBlk));
        values.add(String.valueOf(xpBlk));
        values.add(String.valueOf(gameid));
        values.add(String.valueOf(team));
        try {
            effected = db.setData(update, values);
        } catch (DLException e) {
            effected = -1;
        }
        return effected;
    }

    public int delete() throws DLException {
        int effected = 0;
        String delete = "DELETE FROM defensestats WHERE gameid =? AND team=?;";
        ArrayList<String> values = new ArrayList<String>();
        values.add(gameid);
        values.add(team);
        try {
            //perform delete and return number of effected records
            effected = db.setData(delete, values);
        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();
        }
        return effected;
    }

    public String toString() {
        return null;
    }
    //getters
    public String getGameID() {
        return gameid;
    }
    //setters
    public void setGameID(String gameid) {
        this.gameid = gameid;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPa() {
        return pa;
    }

    public void setPa(int pa) {
        this.pa = pa;
    }

    public int getSck() {
        return sck;
    }

    public void setSck(int sck) {
        this.sck = sck;
    }

    public int getSfty() {
        return sfty;
    }

    public void setSfty(int sfty) {
        this.sfty = sfty;
    }

    public int getIntTD() {
        return intTD;
    }

    public void setIntTD(int intTD) {
        this.intTD = intTD;
    }

    public int getFumTD() {
        return fumTD;
    }

    public void setFumTD(int fumTD) {
        this.fumTD = fumTD;
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

    public int getIntc() {
        return intc;
    }

    public void setIntc(int intc) {
        this.intc = intc;
    }

    public int getFum() {
        return fum;
    }

    public void setFum(int fum) {
        this.fum = fum;
    }

    public int getKblk() {
        return kBlk;
    }

    public void setKblk(int kblk) {
        this.kBlk = kBlk;
    }

    public int getXpBlk() {
        return xpBlk;
    }

    public void setXpBlk(int xpblk) {
        this.xpBlk = xpBlk;
    }
}//end DefenseStats class