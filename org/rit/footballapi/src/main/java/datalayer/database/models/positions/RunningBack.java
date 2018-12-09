package main.java.datalayer.database.models.positions;

import main.java.datalayer.database.DLException;
import main.java.datalayer.database.models.Player;

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
        query = "stats/cumulative/rbcumulative.sql";
        bindValues = new ArrayList<String>(){{add(getPlayerid());}};
    }
    public RunningBack(String playerid, String gameid)
    {
        setPlayerid(playerid);
        setGameID(gameid);
        query = "stats/game/specific/rbgamespecific.sql";
        bindValues = new ArrayList<String>()
        {{add(getPlayerid());
            add(getGameID());
        }};
    }
    public RunningBack(String[] stats)
    {
        setStats(stats);
    }
    public void fetch()throws DLException
    {
        setQuery(query);
        setBindValues(bindValues);
        super.fetch();
        setStats(super.getResults().get(0));
    }
    public void setStats(String[] stats){


        if (getGameID() == null)
        {   setgPlayed(stats[0]);setJerseyNumber(stats[1]);setPos(stats[2]);setfName(stats[3]);setlName(stats[4]);
            setTeam(stats[5]);setRushatt(stats[6]);setRushyds(stats[7]);setRushtd(stats[8]);setRec(stats[9]);
            setRecyds(stats[10]);setRectd(stats[11]);setFum(stats[12]);
        }
        else
        {
            setDate(stats[0]);setHomeTeam(stats[1]);setAwayTeam(stats[2]);setJerseyNumber(stats[3]);setPos(stats[4]);
            setfName(stats[5]);setlName(stats[6]);setTeam(stats[7]);setRushatt(stats[8]);setRushyds(stats[9]);
            setRushtd(stats[10]);setRec(stats[11]);setRecyds(stats[12]);setRectd(stats[13]);setFum(stats[14]);
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
