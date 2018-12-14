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
            setgPlayed(stats[0]);setJerseyNumber(stats[1]);setPos(stats[2]);setfName(stats[3]);setlName(stats[4]);
            setTeam(stats[5]);setFgatt(stats[6]);setFgmd(stats[7]);setXpatt(stats[9]);
            setXpmd(stats[9]);
        } else {
            setDate(stats[0]);setHomeTeam(stats[1]);setAwayTeam(stats[2]);setJerseyNumber(stats[3]);setPos(stats[4]);
            setfName(stats[5]);setlName(stats[6]);setTeam(stats[7]);setFgatt(stats[8]);setFgmd(stats[9]);
            setXpatt(stats[10]);setXpmd(stats[11]);
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