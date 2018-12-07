package main.java.datalayer.database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import main.java.datalayer.database.*;
import java.util.ArrayList;

public class Player extends Game {
    private String gPlayed;
    private String playerid;
    private String fName;
    private String lName;
    private String team;
    private String pos;
    private String jerseyNumber;

    public Player(String[] player) {
        playerid = player[0];
        fName = player[1];
        lName = player[2];
        team = player[3];
        pos = player[4];
        jerseyNumber = player[5];

    }
    public Player(String playerid) {
        this.playerid = playerid;

    }

    public Player() {

    }
    @Override
    public String toString() {
        return "Player{" +
                "playerid='" + playerid + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", team='" + team + '\'' +
                ", pos='" + pos + '\'' +
                ", jerseyNumber='" + jerseyNumber + '\'' +
                '}';
    }
    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }
    public void setStats()throws DLException
    { }
    @JsonIgnore
    public ArrayList<String[]> getQBCumulative()throws DLException
    {   System.out.println("hitQB");
        setQuery("stats/cumulative/allqb.sql");
        super.fetch();
        ArrayList<String[]> results = getResults();
        return results;
    }
    @JsonIgnore
    public ArrayList<String[]> getWRCumulative()throws DLException
    {   System.out.println("hitWR");
        setQuery("stats/cumulative/allwr.sql");
       super.fetch();
        ArrayList<String[]> results = getResults();
        return results;
    }
    @JsonIgnore
    public ArrayList<String[]> getRBCumulative()throws DLException
    {   System.out.println("hitRB");
        setQuery("stats/cumulative/allrb.sql");
        super.fetch();
        ArrayList<String[]> results = getResults();
        return results;
    }
    @JsonIgnore
    public ArrayList<String[]> getTECumulative()throws DLException
    {   System.out.println("hitTE");
        setQuery("stats/cumulative/allte.sql");
        super.fetch();
        ArrayList<String[]> results = getResults();
        return results;
    }
    @JsonIgnore
    public ArrayList<String[]> getKCumulative()throws DLException
    {   System.out.println("hitK");
        setQuery("stats/cumulative/allk.sql");
        super.fetch();
        ArrayList<String[]> results = getResults();
        return results;
    }
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(String jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getgPlayed() { return gPlayed; }

    public void setgPlayed(String gPlayed) { this.gPlayed = gPlayed; }

}