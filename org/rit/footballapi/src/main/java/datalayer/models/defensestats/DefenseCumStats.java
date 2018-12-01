package main.java.datalayer.models.defensestats;

import main.java.datalayer.database.*;

import java.util.ArrayList;

public class DefenseCumStats {

    private String team;
    private int pa;
    private int sck;
    private int sfty;
    private int intTD;
    private int fumTD;
    private int intc;
    private int fum;
    private static FootballDatabase db = new FootballDatabase();

    public DefenseCumStats(String team,int[] stats)
    {
        this.team = team;
        pa = stats[0];
        sck = stats[1];
        sfty = stats[2];
        intTD = stats[3];
        fumTD = stats[4];
        intc = stats[5];
        fum = stats[6];
    }
    public DefenseCumStats(String team)
    {
        this.team = team;
    }
    public void fetch() throws Exception {
        String query = "SELECT pa,sck,sfty,inttd,fumtd,intc,fum FROM defensecumstats WHERE team = ?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(team);
        try {
            ArrayList<String[]> info = db.getData(query, values);
            String[] fields = info.get(1);
            pa = Integer.parseInt(fields[0]);
            sck = Integer.parseInt(fields[1]);
            sfty = Integer.parseInt(fields[2]);
            intTD = Integer.parseInt(fields[3]);
            fumTD = Integer.parseInt(fields[4]);
            intc = Integer.parseInt(fields[5]);
            fum = Integer.parseInt(fields[6]);

        } catch (Exception e) {
            System.out.println("No Record Found");
        }
    }
    /**
     * Inserts the objects current NFL Team, GameID and Defensive statistics variables into the database as a record
     * @return An int representing the number of rows effected
     */
    public int post() throws DLException {
        int effected;
        ArrayList<String> values = new ArrayList<>();

        String insert = "INSERT INTO defensecumstats(team,pa,sck,sfty,inttd,fumtd,intc,fum)VALUES(?,?,?,?,?,?,?,?);";
        values.add(String.valueOf(team));
        values.add(String.valueOf(pa));
        values.add(String.valueOf(sck));
        values.add(String.valueOf(sfty));
        values.add(String.valueOf(intTD));
        values.add(String.valueOf(fumTD));
        values.add(String.valueOf(intc));
        values.add(String.valueOf(fum));
        try {
            effected = db.setData(insert, values);
        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();
        }
        return effected;
    }
    /**
     * Updates a record in the database with the objects current NFL Team, GameID and Defensive statistics variables
     * @return An int representing the number of rows effected
     */
    public int put() throws DLException {
        int effected;
        String update = "UPDATE defensecumstats SET pa=?,sck=?,sfty=?,intTD=?,fumTD=?,intc=?,fum=? WHERE team=?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(pa));
        values.add(String.valueOf(sck));
        values.add(String.valueOf(sfty));
        values.add(String.valueOf(intTD));
        values.add(String.valueOf(fumTD));
        values.add(String.valueOf(intc));
        values.add(String.valueOf(fum));
        values.add(String.valueOf(team));
        try {
            effected = db.setData(update, values);
        } catch (DLException e) {
            effected = -1;
        }
        return effected;
    }
    /**
     * Deletes a record from the database whos team and gameid are equal to the current team and gameid of this object
     * @return An int representing the number of rows effected
     */
    public int delete() throws DLException {
        int effected;
        String delete = "DELETE FROM defensecumstats WHERE team=?;";
        ArrayList<String> values = new ArrayList<>();
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
    @Override
    public String toString() {
        return "DefenseCumStats{" +
                "team='" + team + '\'' +
                ", pa=" + pa +
                ", sck=" + sck +
                ", sfty=" + sfty +
                ", intTD=" + intTD +
                ", fumTD=" + fumTD +
                ", intc=" + intc +
                ", fum=" + fum +
                '}';
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
}
