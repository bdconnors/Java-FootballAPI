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
            setgPlayed(stats[0]);setJerseyNumber(stats[1]);setPos(stats[2]);setfName(stats[3]);setlName(stats[4]);
            setTeam(stats[5]);setPassatt(stats[6]);setPasscomp(stats[7]);setPassyds(stats[8]);setPasstds(stats[9]);
            setIntthr(stats[10]);setRushatt(stats[11]);setRushyds(stats[12]);setRushtd(stats[13]);setFum(stats[14]);
        } else {
            setDate(stats[0]);setHomeTeam(stats[1]);setAwayTeam(stats[2]);setJerseyNumber(stats[3]);setPos(stats[4]);
            setfName(stats[5]);setlName(stats[6]);setTeam(stats[7]);setPassatt(stats[8]);setPasscomp(stats[9]);
            setPassyds(stats[10]);setPasstds(stats[11]);setIntthr(stats[12]);setRushatt(stats[13]);setRushyds(stats[14]);
            setRushtd(stats[15]);setFum(stats[16]);
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
