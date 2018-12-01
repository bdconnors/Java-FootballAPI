package main.java.datalayer.models.player.playerstats.playercumulativestats;
import main.java.datalayer.database.*;

import java.util.ArrayList;

public class PlayerCumPass {
    private String playerid;
    private int passAtt;
    private int passComp;
    private int passYds;
    private int passTds;
    private int pass2pt;
    private static FootballDatabase db = new FootballDatabase();

    public PlayerCumPass(int[] stats) {
        playerid = String.valueOf(stats[0]);
        passAtt = stats[1];
        passComp = stats[2];
        passYds = stats[3];
        passTds = stats[4];
        pass2pt = stats[5];
    }
    public PlayerCumPass(String playerid)
    {
        this.playerid = playerid;

    }
    public PlayerCumPass() {


    }
    public void fetch() throws DLException {
        //SQL query string
        String query = "SELECT passatt,passcomp,passyds,passtds,pass2pt FROM playercumpass WHERE playerid= ?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(playerid);

        try {
            //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
            ArrayList<String[]> info = db.getData(query, values);
            String[] fields = info.get(1);
            //set name to the first field value
            passAtt = Integer.parseInt(fields[0]);
            passComp = Integer.parseInt(fields[1]);
            passYds = Integer.parseInt(fields[2]);
            passTds = Integer.parseInt(fields[3]);
            pass2pt = Integer.parseInt(fields[4]);

        } catch (Exception e) {
            System.out.println("No Record Found");

        }

    }
    public int post() throws DLException {

        //effected records
        int effected;
        ArrayList<String> values = new ArrayList<>();
        //SQL Insert String
        String insert = "INSERT INTO playercumpass(playerid,passatt,passcomp,passyds,passtds,pass2pt)VALUES(?,?,?,?,?,?);";
        //bind values
        values.add(playerid);
        values.add(String.valueOf(passAtt));
        values.add(String.valueOf(passComp));
        values.add(String.valueOf(passYds));
        values.add(String.valueOf(passTds));
        values.add(String.valueOf(pass2pt));

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
        String update = "UPDATE playercumpass SET passatt=?,passcomp=?,passyds=?,passtds=?,pass2pt=? WHERE playerid=?;";
        ArrayList<String> values = new ArrayList<>();


        values.add(String.valueOf(passAtt));
        values.add(String.valueOf(passComp));
        values.add(String.valueOf(passYds));
        values.add(String.valueOf(passTds));
        values.add(String.valueOf(pass2pt));
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
        String delete = "DELETE FROM playercumpass WHERE playerid=?;";
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
        return "PlayerCumPass{" +
                "playerid='" + playerid + '\'' +
                ", passAtt=" + passAtt +
                ", passComp=" + passComp +
                ", passYds=" + passYds +
                ", passTds=" + passTds +
                ", pass2pt=" + pass2pt +
                '}';
    }

    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    public int getPassAtt() {
        return passAtt;
    }

    public void setPassAtt(int passAtt) {
        this.passAtt = passAtt;
    }

    public int getPassComp() {
        return passComp;
    }

    public void setPassComp(int passComp) {
        this.passComp = passComp;
    }

    public int getPassYds() {
        return passYds;
    }

    public void setPassYds(int passYds) {
        this.passYds = passYds;
    }

    public int getPassTds() {
        return passTds;
    }

    public void setPassTds(int passTds) {
        this.passTds = passTds;
    }

    public int getPass2pt() {
        return pass2pt;
    }

    public void setPass2pt(int pass2pt) {
        this.pass2pt = pass2pt;
    }
}