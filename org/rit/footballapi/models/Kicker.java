package org.rit.footballapi.models;

import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class Kicker extends Player {
    public String fgatt;
    public String fgmd;
    public String xpatt;
    public String xpmd;

    public Kicker(String playerid) {
        setPlayerid(playerid);
        query = "kcumulative.sql";
        bindValues = new ArrayList<String>() {{
            add(getPlayerid());
        }};
    }

    public Kicker(String playerid, int gameid) {
        setPlayerid(playerid);
        setGameID(String.valueOf(gameid));
        query = "kgamespecific.sql";
        bindValues = new ArrayList<String>() {{
            add(getPlayerid());
            add(getGameID());
        }};
    }
    public Kicker(String fname,String lname)
    {
        setfName(fname);
        setlName(lname);
        query = "stats/cumulative/kbyname.sql";
        bindValues = new ArrayList<String>()
        {{add(getfName());add(getlName());}};
    }
    public Kicker(String[] stats)
    {
        setStats(stats);
    }
    public void fetch()throws DLException
    {
        super.query = this.query;
        super.bindValues = this.bindValues;
        super.fetch();
        setStats(super.getResults().get(0));
    }
    public void setStats(String[] stats){

        if (getGameID() == null) {
            setPlayerid(stats[0]);setgPlayed(stats[1]);setJerseyNumber(stats[2]);setPos(stats[3]);setfName(stats[4]);
            setlName(stats[5]);setTeam(stats[6]);setFgatt(stats[7]);setFgmd(stats[8]);setXpatt(stats[9]);
            setXpmd(stats[10]);
        } else {
            setPlayerid(stats[0]);setDate(stats[1]);setHomeTeam(stats[2]);setAwayTeam(stats[3]);setJerseyNumber(stats[4]);
            setPos(stats[5]);setfName(stats[6]);setlName(stats[7]);setTeam(stats[8]);setFgatt(stats[9]);setFgmd(stats[10]);
            setXpatt(stats[11]);setXpmd(stats[12]);
        }
    }

    public String getFgatt() {
        return fgatt;
    }

    public void setFgatt(String fgatt) {
        this.fgatt = fgatt;
    }

    public String getFgmd() {
        return fgmd;
    }

    public void setFgmd(String fgmd) {
        this.fgmd = fgmd;
    }

    public String getXpatt() {
        return xpatt;
    }

    public void setXpatt(String xpatt) {
        this.xpatt = xpatt;
    }

    public String getXpmd() {
        return xpmd;
    }

    public void setXpmd(String xpmd) {
        this.xpmd = xpmd;
    }
}