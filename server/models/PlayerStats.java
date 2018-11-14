package models;//----------------------------------------------------------------------------------------------
//Dev Name: Brandon Connors
//Filename: Player.Java
//Date: 11/8/18
//Program Description: Player Object to hold data on a player for a fantasy football database
//----------------------------------------------------------------------------------------------
//--------------Import Statements---------------------------------------------------------------

import java.util.ArrayList;

//----------------------------------------------------------------------------------------
//Class Name: Team
//Description: This class has 16 methods. 
//post(),put(),delete() and toString()
//6 set mutators for its class variables
//6 get mutators for its class variables
//----------------------------------------------------------------------------------------
public class PlayerStats {
    //--------------Class Variables---------------------------------------------------------
    public String gameid;
    public String playerid;
    public int passAtt;
    public int passComp;
    public int passYds;
    public int passTds;
    public int pass2pt;
    public int rushAtt;
    public int rushYds;
    public int rush2pt;
    public int rec;
    public int recYds;
    public int recTd;
    public int rec2pt;
    public int fgAtt;
    public int fgMd;
    public int xpAtt;
    public int xpMd;
    public int intThr;
    public int fum;
    public int krTD;
    public int prTD;
    public FootballDatabase db = new FootballDatabase();

    //--------------------Constructor------------------------------------------------------
    //Parameter Type: String[]
    //Description:takes in a string[] of player data and sets team variables
    //------------------------------------------------------------------------------------
    public PlayerStats(int[] stats) {
        gameid = String.valueOf(stats[0]);
        playerid = String.valueOf(stats[1]);
        passAtt = stats[2];
        passComp = stats[3];
        passYds = stats[4];
        passTds = stats[5];
        pass2pt = stats[6];
        rushAtt = stats[7];
        rushYds = stats[8];
        rush2pt = stats[9];
        rec = stats[10];
        recYds = stats[11];
        recTd = stats[12];
        rec2pt = stats[13];
        fgAtt = stats[14];
        fgMd = stats[15];
        xpAtt = stats[16];
        xpMd = stats[17];
        intThr = stats[18];
        fum = stats[19];
        krTD = stats[20];
        prTD = stats[21];

    }

    //--------------------Constructor------------------------------------------------------
    //Parameter Type: 2 Strings
    //Description:takes in individual strings of team data and sets team variables
    //------------------------------------------------------------------------------------
    public PlayerStats() {


    }


    //-----------------------------------------------------------------------------------------------------
    //Method Name: fetch
    //Description: Issues a query to the database returning player data associated with
    //this team obejects set name and sets the remaining class variables equal to the returned data
    //----------------------------------------------------------------------------------------------------
    public void fetch() throws DLException {
        //SQL query string
        String query = "SELECT passatt,passcomp,passyds,passtds,pass2pt,rushatt,rushyds,rush2pt,rec,recyds,rectd,rec2pt,fgatt,fgmd,xpatt,xpmd,intthr,fum,krtd,prtd FROM playerstats WHERE gameid=? AND playerid= ?;";

        ArrayList<String> values = new ArrayList<String>();
        try {
            //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
            ArrayList<String[]> info = db.getData(query, values);
            String[] fields = info.get(1);
            //set name to the first field value

            passAtt = Integer.parseInt(fields[2]);
            passComp = Integer.parseInt(fields[3]);
            passYds = Integer.parseInt(fields[4]);
            passTds = Integer.parseInt(fields[5]);
            pass2pt = Integer.parseInt(fields[6]);
            rushAtt = Integer.parseInt(fields[7]);
            rushYds = Integer.parseInt(fields[8]);
            rush2pt = Integer.parseInt(fields[9]);
            rec = Integer.parseInt(fields[10]);
            recYds = Integer.parseInt(fields[11]);
            recTd = Integer.parseInt(fields[12]);
            rec2pt = Integer.parseInt(fields[13]);
            fgAtt = Integer.parseInt(fields[14]);
            fgMd = Integer.parseInt(fields[15]);
            xpAtt = Integer.parseInt(fields[16]);
            xpMd = Integer.parseInt(fields[17]);
            intThr = Integer.parseInt(fields[18]);
            fum = Integer.parseInt(fields[19]);
            krTD = Integer.parseInt(fields[20]);
            prTD = Integer.parseInt(fields[21]);
        } catch (Exception e) {
            System.out.println("No Record Found");

        }

    }

    //---------------------------------------------------------------------------------------------
    //Method Name: post
    //Description: inserts a record into the database using this objects class variables for
    //the information in the corresponding fields
    //---------------------------------------------------------------------------------------------
    public int post() throws DLException {

        //effected records
        int effected = 0;
        ArrayList<String> values = new ArrayList<String>();
        //SQL Insert String
        String insert = "INSERT INTO playerstats(gameid,playerid,passatt,passcomp,passyds,passtds,pass2pt,rushatt,rushyds,rush2pt,rec,recyds,rectd,rec2pt,fgatt,fgmd,xpatt,xpmd,intthr,fum,krtd,prtd)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        //bind values

        values.add(gameid);
        values.add(playerid);
        values.add(String.valueOf(passAtt));
        values.add(String.valueOf(passComp));
        values.add(String.valueOf(passYds));
        values.add(String.valueOf(passTds));
        values.add(String.valueOf(pass2pt));
        values.add(String.valueOf(rushAtt));
        values.add(String.valueOf(rushYds));
        values.add(String.valueOf(rush2pt));
        values.add(String.valueOf(rec));
        values.add(String.valueOf(recYds));
        values.add(String.valueOf(recTd));
        values.add(String.valueOf(rec2pt));
        values.add(String.valueOf(fgAtt));
        values.add(String.valueOf(fgMd));
        values.add(String.valueOf(xpAtt));
        values.add(String.valueOf(xpMd));
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

    //updates database record that corresponds to this object's 'ID' variable and then returns the number of records effected in the database
    public int put() throws DLException {  //effected records
        int effected = 0;
        //SQL Update String
        String update = "UPDATE playerstats SET passatt=?,passcomp=?,passyds=?,passtds=?,pass2pt=?,rushatt=?,rushyds=?,rush2pt=?,rec=?,recyds=?,rectd=?,rec2pt=?,fgatt=?,fgmd=?,xpatt=?,xpmd=?,intthr=?,fum=?,krtd=?,prtd=?WHERE gameid = ? AND playerid=?;";
        ArrayList<String> values = new ArrayList<String>();


        values.add(String.valueOf(passAtt));
        values.add(String.valueOf(passComp));
        values.add(String.valueOf(passYds));
        values.add(String.valueOf(passTds));
        values.add(String.valueOf(pass2pt));
        values.add(String.valueOf(rushAtt));
        values.add(String.valueOf(rushYds));
        values.add(String.valueOf(rush2pt));
        values.add(String.valueOf(rec));
        values.add(String.valueOf(recYds));
        values.add(String.valueOf(recTd));
        values.add(String.valueOf(rec2pt));
        values.add(String.valueOf(fgAtt));
        values.add(String.valueOf(fgMd));
        values.add(String.valueOf(xpAtt));
        values.add(String.valueOf(xpMd));
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

    //---------------------------------------------------------------------------------------------
    //Method Name: delete
    //Description:deletes team record from database using their name
    //---------------------------------------------------------------------------------------------
    public int delete() throws DLException {

        //effected records
        int effected = 0;
        //SQL delete string
        String delete = "DELETE FROM playerstats WHERE gameid =? AND playerid=?;";
        ArrayList<String> values = new ArrayList<String>();
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

    //toString
    public String toString() {
        return null;

    }


}//end player class