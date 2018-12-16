package org.rit.footballapi.models;

import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class RunningBack extends Player {

    public String rushatt;
    public String rushyds;
    public String rushtd;
    public String fum;
    public String rec;
    public String recyds;
    public String rectd;
    public String query;
    public ArrayList<String> bindValues;

    public RunningBack(String playerid)
    {
        setPlayerid(playerid);
        query = "rbcumulative.sql";
        bindValues = new ArrayList<String>(){{add(getPlayerid());}};
    }
    public RunningBack(String playerid, int gameid)
    {
        setPlayerid(playerid);
        setGameID(String.valueOf(gameid));
        query = "rbgamespecific.sql";
        bindValues = new ArrayList<String>()
        {{add(getPlayerid());
            add(getGameID());
        }};
    }
    public RunningBack(String fname,String lname)
    {
        setfName(fname);
        setlName(lname);
        query = "rbbyname.sql";
        bindValues = new ArrayList<String>()
        {{add(getfName());add(getlName());}};
    }
    public RunningBack(String[] stats)
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


        if (getGameID() == null)
        {   setPlayerid(stats[0]);setgPlayed(stats[1]);setJerseyNumber(stats[2]);setPos(stats[3]);setfName(stats[4]);
            setlName(stats[5]);setTeam(stats[6]);setRushatt(stats[7]);setRushyds(stats[8]);setRushtd(stats[9]);
            setRec(stats[10]);setRecyds(stats[11]);setRectd(stats[12]);setFum(stats[13]);
        }
        else
        {
            setPlayerid(stats[0]);setDate(stats[1]);setHomeTeam(stats[2]);setAwayTeam(stats[3]);setJerseyNumber(stats[4]);
            setPos(stats[5]);setfName(stats[6]);setlName(stats[7]);setTeam(stats[8]);setRushatt(stats[9]);setRushyds(stats[10]);
            setRushtd(stats[11]);setRec(stats[12]);setRecyds(stats[13]);setRectd(stats[14]);setFum(stats[15]);
        }
    }
    public String getRec() { return rec; }
    public void setRec(String rec) { this.rec = rec; }

    public String getRecyds() { return recyds; }
    public void setRecyds(String recyds) { this.recyds = recyds; }

    public String getRectd() { return rectd; }
    public void setRectd(String rectd) { this.rectd = rectd; }

    public String getRushatt() { return rushatt; }
    public void setRushatt(String rushatt) { this.rushatt = rushatt; }

    public String getRushyds() { return rushyds; }
    public void setRushyds(String rushyds) { this.rushyds = rushyds; }

    public String getRushtd() { return rushtd; }
    public void setRushtd(String rushtd) { this.rushtd = rushtd; }

    public String getFum() { return fum; }
    public void setFum(String fum) { this.fum = fum; }
}
