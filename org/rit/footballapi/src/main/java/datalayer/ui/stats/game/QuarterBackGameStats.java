package main.java.datalayer.ui.stats.game;

import main.java.datalayer.database.DLException;
import main.java.datalayer.database.FootballDatabase;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class QuarterBackGameStats {

    private String gameid;
    private String playerid;
    private String fName;
    private String lName;
    private String team;
    private String position;
    private String jNumber;
    private int passAtt;
    private int passComp;
    private int passYds;
    private int passTds;
    private int intThr;
    private int rushAtt;
    private int rushYds;
    private int rushTd;
    private int fum;
    private static FootballDatabase db = new FootballDatabase();


    public QuarterBackGameStats(String gameid,String playerid){

        this.gameid = gameid;
        this.playerid = playerid;

    }
    public QuarterBackGameStats()
    {

    }
    public void fetch()throws DLException
    {
        try
        {
            String query = getQuery("qbgamestats","main/resources/queries/");
            ArrayList<String[]> stats = db.getData(query,new ArrayList<String>() {{add(playerid);add(gameid);}});

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    private String getQuery(String filename,String filepath)throws IOException
    {
        String query = null;
        try {
            BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(filepath+filename+".sql")));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null)
            {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            query = sb.toString();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return query;
    }
    @Override
    public String toString() {
        return "QuarterBackGameStats{" +
                "gameid='" + gameid + '\'' +
                ", playerid='" + playerid + '\'' +
                ", passAtt=" + passAtt +
                ", passComp=" + passComp +
                ", passYds=" + passYds +
                ", passTds=" + passTds +
                ", intThr=" + intThr +
                ", rushAtt=" + rushAtt +
                ", rushYds=" + rushYds +
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
    public int getRushTd() {
        return rushTd;
    }

    public void setRushTd(int rushTd) {
        this.rushTd = rushTd;
    }
}
