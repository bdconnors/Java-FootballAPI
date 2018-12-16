package org.rit.footballapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class Player extends Game {
    private String playerid;
    private String gPlayed;
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
        query="playerinfo.sql";
        bindValues = new ArrayList<String>(){{add(playerid);}};

    }

    public Player() {

    }
    @Override
    public void fetch() throws DLException {

        setQuery(query);
        setBindValues(bindValues);
        super.fetch();
    }

    public String getPlayerid() {
        return playerid;
    }
    public void setInfo()
    {
        playerid = getResults().get(0)[0];
        fName = getResults().get(0)[1];
        lName = getResults().get(0)[2];
        team= getResults().get(0)[3];
        pos = getResults().get(0)[4];
        jerseyNumber = getResults().get(0)[5];
    }
    public void setPlayerid(String playerid) {
        this.playerid = playerid;
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