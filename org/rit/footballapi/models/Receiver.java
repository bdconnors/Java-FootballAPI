package org.rit.footballapi.models;

import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class Receiver extends Player {

    public String rec;
    public String recyds;
    public String rectd;
    public String rushatt;
    public String rushyds;
    public String rushtd;
    public String fum;
    public String query;

    public Receiver(String playerid)
    {
        setPlayerid(playerid);
        query = "wrtecumulative.sql";
        bindValues = new ArrayList<String>(){{add(getPlayerid());}};
    }
    public Receiver(String playerid, int gameid)
    {
        setPlayerid(playerid);
        setGameID(String.valueOf(gameid));
        query = "wrtegamespecific.sql";
        bindValues = new ArrayList<String>()
         {{add(getPlayerid());
           add(getGameID());
         }};
    }
    public Receiver(String fname,String lname)
    {
        setfName(fname);
        setlName(lname);
        query = "wrtebyname.sql";
        bindValues = new ArrayList<String>()
        {{add(getfName());add(getlName());}};
    }
    public Receiver(String[] stats)
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
        { setPlayerid(stats[0]);setgPlayed(stats[1]);setJerseyNumber(stats[2]);setPos(stats[3]);setfName(stats[4]);
          setlName(stats[5]);setTeam(stats[6]);setRec(stats[7]);setRecyds(stats[8]);setRectd(stats[9]);
          setRushatt(stats[10]);setRushyds(stats[11]);setRushtd(stats[12]);setFum(stats[13]);
        }
        else
        {
            setPlayerid(stats[0]);setDate(stats[1]);setHomeTeam(stats[2]);setAwayTeam(stats[3]);setJerseyNumber(stats[4]);
            setPos(stats[5]);setfName(stats[6]);setlName(stats[7]);setTeam(stats[8]);setRec(stats[9]);setRecyds(stats[10]);
            setRectd(stats[11]);setRushatt(stats[12]);setRushyds(stats[13]);setRushtd(stats[14]);setFum(stats[15]);
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
