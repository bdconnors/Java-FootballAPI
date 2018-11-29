package server.models;

import java.util.ArrayList;

/**Represents defensive statistics acrued by an NFL team during a game
 * @author Brandon Connors
 * @author bdc5435@rit.edu
 */
public class MiscDefenseGameStats {

    private String gameid;
    private String team;
    private int krTD;
    private int prTD;
    private int kBlk;
    private int xpBlk;
    private static FootballDatabase db = new FootballDatabase();

    /**Creates a set of defensive statistics from a game played by an NFL team with specified stats and gameid
     * @param stats An int[] containing statistics acrued by an NFL team during one game
     */
    public MiscDefenseGameStats(String team,int[] stats) {
        gameid = String.valueOf(stats[0]);
        this.team = team;
        krTD = stats[1];
        prTD = stats[2];
        kBlk = stats[3];
        xpBlk = stats[4];
    }
    /**Creates an empty set of defensive statistics from a specified game played by a specified NFL team
     * @param team A String containing an NFL team 2 or 3 letter abbreviation (ex. 'NYG' is 'New York Giants')
     */
    public MiscDefenseGameStats(String team, String gameid) {
        this.team = team;
        this.gameid = gameid;

    }

    /**
     * Default Constructor
     */
    public MiscDefenseGameStats() {


    }
    /**
     * Retrieves defensive statistics of the objects current team and current gameid and sets them as class variables
     * @throws e Layer Exception if data cannot be fetched
     */
    public void fetch() throws Exception {
        String query = "krtd,prtd,kblk,xpblk FROM miscdefensegamestats WHERE gameid=? AND team = ?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(gameid);
        values.add(team);
        try {
            ArrayList<String[]> info = db.getData(query, values);
            String[] fields = info.get(1);
            krTD = Integer.parseInt(fields[0]);
            prTD = Integer.parseInt(fields[1]);
            kBlk = Integer.parseInt(fields[2]);
            xpBlk = Integer.parseInt(fields[3]);

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

        String insert = "INSERT INTO miscdefensegamestats(gameid,team,krtd,prtd,kblk,xpblk)VALUES(?,?,?,?,?,?);";
        values.add(String.valueOf(gameid));
        values.add(String.valueOf(team));
        values.add(String.valueOf(krTD));
        values.add(String.valueOf(prTD));
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
        String update = "UPDATE miscdefensegamestats SET krTD=?,prTD=?,kBlk=?,xpBlk=? WHERE gameid = ? AND team=?;";
        ArrayList<String> values = new ArrayList<>();;
        values.add(String.valueOf(krTD));
        values.add(String.valueOf(prTD));
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
        String delete = "DELETE FROM miscdefensegamestats WHERE gameid =? AND team=?;";
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
        return "MiscDefenseGameStats{" +
                "gameid='" + gameid + '\'' +
                ", team='" + team + '\'' +
                ", krTD=" + krTD +
                ", prTD=" + prTD +
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
}