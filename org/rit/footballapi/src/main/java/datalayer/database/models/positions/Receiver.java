package main.java.datalayer.database.models.positions;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnore;
import main.java.datalayer.database.DBInterface;
import main.java.datalayer.database.DLException;
import main.java.datalayer.database.models.Player;

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
          query = "stats/cumulative/wrtecumulative.sql";
          bindValues = new ArrayList<String>(){{add(getPlayerid());}};
    }
    public Receiver(String playerid, String gameid)
    {
        setPlayerid(playerid);
        setGameID(gameid);
        query = "stats/game/specific/wrtegamespecific.sql";
        bindValues = new ArrayList<String>()
         {{add(getPlayerid());
           add(getGameID());
         }};
    }
    public Receiver(String[] stats)
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
        { setgPlayed(stats[0]);setJerseyNumber(stats[1]);setPos(stats[2]);setfName(stats[3]);setlName(stats[4]);
          setTeam(stats[5]);setRec(stats[6]);setRecyds(stats[7]);setRectd(stats[8]);setRushatt(stats[9]);
          setRushyds(stats[10]);setRushtd(stats[11]);setFum(stats[12]);
        }
        else
        {
            setDate(stats[0]);setHomeTeam(stats[1]);setAwayTeam(stats[2]);setJerseyNumber(stats[3]);setPos(stats[4]);
            setfName(stats[5]);setlName(stats[6]);setTeam(stats[7]);setRec(stats[8]);setRecyds(stats[9]);
            setRectd(stats[10]);setRushatt(stats[11]);setRushyds(stats[12]);setRushtd(stats[13]);setFum(stats[14]);
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
