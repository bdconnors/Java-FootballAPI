package org.rit.footballapi.models;


import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class QuarterBack extends Player {
    public String passatt;
    public String passcomp;
    public String passyds;
    public String passtds;
    public String intthr;
    public String rushatt;
    public String rushyds;
    public String rushtd;
    public String fum;

    public QuarterBack(String playerid) {
        setPlayerid(playerid);
        query = "qbcumulative.sql";
        bindValues = new ArrayList<String>() {{
            add(getPlayerid());
        }};
    }

    public QuarterBack(String playerid, int gameid) {
        setPlayerid(playerid);
        setGameID(String.valueOf(gameid));
        query = "qbgamespecific.sql";
        bindValues = new ArrayList<String>() {{
            add(getPlayerid());
            add(getGameID());
        }};
    }
    public QuarterBack(String fname,String lname)
    {
        setfName(fname);
        setlName(lname);
        query = "qbbyname.sql";
        bindValues = new ArrayList<String>()
        {{add(getfName());add(getlName());}};
    }
    public QuarterBack(String[] stats)
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
            setlName(stats[5]);setTeam(stats[6]);setPassatt(stats[7]);setPasscomp(stats[8]);setPassyds(stats[9]);
            setPasstds(stats[10]);setIntthr(stats[11]);setRushatt(stats[12]);setRushyds(stats[13]);setRushtd(stats[14]);
            setFum(stats[15]);
        } else {
            setPlayerid(stats[0]);setDate(stats[1]);setHomeTeam(stats[2]);setAwayTeam(stats[3]);setJerseyNumber(stats[4]);
            setPos(stats[5]);setfName(stats[6]);setlName(stats[7]);setTeam(stats[8]);setPassatt(stats[9]);setPasscomp(stats[10]);
            setPassyds(stats[11]);setPasstds(stats[12]);setIntthr(stats[13]);setRushatt(stats[14]);setRushyds(stats[15]);
            setRushtd(stats[16]);setFum(stats[17]);
        }
    }

    public String getPassatt() {
        return passatt;
    }

    public void setPassatt(String passatt) {
        this.passatt = passatt;
    }

    public String getPasscomp() {
        return passcomp;
    }

    public void setPasscomp(String passcomp) {
        this.passcomp = passcomp;
    }

    public String getPasstds() {
        return passtds;
    }

    public void setPasstds(String passtds) {
        this.passtds = passtds;
    }

    public String getIntthr() {
        return intthr;
    }

    public void setIntthr(String intthr) {
        this.intthr = intthr;
    }

    public String getRushatt() {
        return rushatt;
    }

    public void setRushatt(String rushatt) {
        this.rushatt = rushatt;
    }

    public String getRushyds() {
        return rushyds;
    }

    public void setRushyds(String rushyds) {
        this.rushyds = rushyds;
    }

    public String getRushtd() {
        return rushtd;
    }

    public void setRushtd(String rushtd) {
        this.rushtd = rushtd;
    }

    public String getFum() {
        return fum;
    }

    public void setFum(String fum) {
        this.fum = fum;
    }

    public String getPassyds() {
        return passyds;
    }

    public void setPassyds(String passyds) {
        this.passyds = passyds;
    }
}
