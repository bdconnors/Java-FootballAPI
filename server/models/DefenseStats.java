package server.models;

import java.util.*;
/**Represents defensive statistics acrued by an NFL team during a game
 * @author Brandon Connors
 * @author bdc5435@rit.edu
 */
public class DefenseStats {

    private String gameid;
    private String team;
    private int pa;
    private int sck;
    private int sfty;
    private int intTD;
    private int fumTD;
    private int krTD;
    private int prTD;
    private int intc;
    private int fum;
    private int kBlk;
    private int xpBlk;
    private FootballDatabase db = new FootballDatabase();

    /**Creates a set of defensive statistics from a game played by an NFL team with specified stats and gameid
     * @param stats An int[] containing statistics acrued by an NFL team during one game
     */
    DefenseStats(int[] stats) {
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
    /**Creates an empty set of defensive statistics from a specified game played by a specified NFL team
     * @param team A String containing an NFL team 2 or 3 letter abbreviation (ex. 'NYG' is 'New York Giants')
     */
    public DefenseStats(String team, String gameid) {
        this.team = team;
        this.gameid = gameid;

    }

    /**
     * Default Constructor
     */
    public DefenseStats() {


    }

    /**
     * Retrieves defensive statistics of the objects current team and current gameid and sets them as class variables
     * @throws e Layer Exception if data cannot be fetched
     */
    public void fetch() throws Exception {
        String query = "SELECT pa,sck,sfty,inttd,fumtd,krtd,prtd,intc,fum,kblk,xpblk FROM defensestats WHERE gameid=? AND team = ?;";
        ArrayList<String> values = new ArrayList<>();
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

    /**
     * Inserts the objects current NFL Team, GameID and Defensive statistics variables into the database as a record
     * @return An int representing the number of rows effected
     * @throws Throws Data Layer Exception if data cannot be inserted
     */
    int post() throws DLException {
        int effected;
        ArrayList<String> values = new ArrayList<>();

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
    /**
     * Updates a record in the database with the objects current NFL Team, GameID and Defensive statistics variables
     * @return An int representing the number of rows effected
     * @throws Throws Data Layer Exception if update cannot be completed
     */
    public int put() throws DLException {
        int effected;
        String update = "UPDATE defensestats SET pa=?,sck=?,sfty=?,intTD=?,fumTD=?,krTD=?,prTD=?,intc=?,fum=?,kBlk=?,xpBlk=?WHERE gameid = ? AND team=?;";
        ArrayList<String> values = new ArrayList<>();
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
    /**
     * Deletes a record from the database whos team and gameid are equal to the current team and gameid of this object
     * @return An int representing the number of rows effected
     * @throws Throws Data Layer Exception if the deletion cannot be executed
     */
    public int delete() throws DLException {
        int effected;
        String delete = "DELETE FROM defensestats WHERE gameid =? AND team=?;";
        ArrayList<String> values = new ArrayList<>();
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

    @Override
    public String toString() {
        return "DefenseStats{" +
                "gameid='" + gameid + '\'' +
                ", team='" + team + '\'' +
                ", pa=" + pa +
                ", sck=" + sck +
                ", sfty=" + sfty +
                ", intTD=" + intTD +
                ", fumTD=" + fumTD +
                ", krTD=" + krTD +
                ", prTD=" + prTD +
                ", intc=" + intc +
                ", fum=" + fum +
                ", kBlk=" + kBlk +
                ", xpBlk=" + xpBlk +
                '}';
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
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

    public int getkBlk() {
        return kBlk;
    }

    public void setkBlk(int kBlk) {
        this.kBlk = kBlk;
    }

    public int getXpBlk() {
        return xpBlk;
    }

    public void setXpBlk(int xpBlk) {
        this.xpBlk = xpBlk;
    }
}//end DefenseStats class