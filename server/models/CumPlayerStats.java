package server.models;//----------------------------------------------------------------------------------------------
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
public class CumPlayerStats {
    //--------------Class Variables---------------------------------------------------------
    private String playerid;
    private int gplayed;
    private int passAtt;
    private int passComp;
    private int passYds;
    private int passTds;
    private int pass2pt;
    private int rushAtt;
    private int rushYds;
    private int rush2pt;
    private int rec;
    private int recYds;
    private int recTd;
    private int rec2pt;
    private int fgAtt;
    private int fgMd;
    private int xpAtt;
    private int xpMd;
    private int intThr;
    private int fum;
    private int krTD;
    private int prTD;
    private FootballDatabase db = new FootballDatabase();

    //--------------------Constructor------------------------------------------------------
    //Parameter Type: String[]
    //Description:takes in a string[] of player data and sets team variables
    //------------------------------------------------------------------------------------
    CumPlayerStats(int[] stats) {
        playerid = String.valueOf(stats[0]);
        gplayed = stats[1];
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
    public CumPlayerStats() {


    }


    //-----------------------------------------------------------------------------------------------------
    //Method Name: fetch
    //Description: Issues a query to the database returning player data associated with
    //this team obejects set name and sets the remaining class variables equal to the returned data
    //----------------------------------------------------------------------------------------------------
    public void fetch() throws DLException {
        //SQL query string
        String query = "SELECT gplayed,passatt,passcomp,passyds,passtds,pass2pt,rushatt,rushyds,rush2pt,rec,recyds,rectd,rec2pt,fgatt,fgmd,xpatt,xpmd,intthr,fum,krtd,prtd FROM cumplayerstats WHERE playerid= ?;";

        ArrayList<String> values = new ArrayList<>();
        try {
            //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
            ArrayList<String[]> info = db.getData(query, values);
            String[] fields = info.get(1);
            //set name to the first field value
            gplayed = Integer.parseInt(fields[1]);
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
    int post() throws DLException {

        //effected records
        int effected;
        ArrayList<String> values = new ArrayList<>();
        //SQL Insert String
        String insert = "INSERT INTO cumplayerstats(playerid,gplayed,passatt,passcomp,passyds,passtds,pass2pt,rushatt,rushyds,rush2pt,rec,recyds,rectd,rec2pt,fgatt,fgmd,xpatt,xpmd,intthr,fum,krtd,prtd)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        //bind values

        values.add(playerid);
        values.add(String.valueOf(gplayed));
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
        int effected;
        //SQL Update String
        String update = "UPDATE cumplayerstats SET gplayerd =?,passatt=?,passcomp=?,passyds=?,passtds=?,pass2pt=?,rushatt=?,rushyds=?,rush2pt=?,rec=?,recyds=?,rectd=?,rec2pt=?,fgatt=?,fgmd=?,xpatt=?,xpmd=?,intthr=?,fum=?,krtd=?,prtd=?WHERE playerid=?;";
        ArrayList<String> values = new ArrayList<>();

        values.add(String.valueOf(gplayed));
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
        int effected;
        //SQL delete string
        String delete = "DELETE FROM cumplayerstats WHERE playerid=?;";
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
        return "CumPlayerStats{" +
                "playerid='" + playerid + '\'' +
                ", gplayed=" + gplayed +
                ", passAtt=" + passAtt +
                ", passComp=" + passComp +
                ", passYds=" + passYds +
                ", passTds=" + passTds +
                ", pass2pt=" + pass2pt +
                ", rushAtt=" + rushAtt +
                ", rushYds=" + rushYds +
                ", rush2pt=" + rush2pt +
                ", rec=" + rec +
                ", recYds=" + recYds +
                ", recTd=" + recTd +
                ", rec2pt=" + rec2pt +
                ", fgAtt=" + fgAtt +
                ", fgMd=" + fgMd +
                ", xpAtt=" + xpAtt +
                ", xpMd=" + xpMd +
                ", intThr=" + intThr +
                ", fum=" + fum +
                ", krTD=" + krTD +
                ", prTD=" + prTD +
                ", db=" + db +
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

    public int getRushAtt() {
        return rushAtt;
    }

    public void setRushAtt(int rushAtt) {
        this.rushAtt = rushAtt;
    }

    public int getRushYds() {
        return rushYds;
    }

    public void setRushYds(int rushYds) {
        this.rushYds = rushYds;
    }

    public int getRush2pt() {
        return rush2pt;
    }

    public void setRush2pt(int rush2pt) {
        this.rush2pt = rush2pt;
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
}//end player class