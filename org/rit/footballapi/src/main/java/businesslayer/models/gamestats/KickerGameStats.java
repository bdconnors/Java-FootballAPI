package main.java.businesslayer.models.gamestats;

import main.java.datalayer.database.DLException;
import main.java.datalayer.models.player.playerstats.playergamestats.PlayerGameKick;

public class KickerGameStats {

    private String gameid;
    private String playerid;
    private int fgAtt;
    private int fgMd;
    private int xpAtt;
    private int xpMd;

    public KickerGameStats(String gameid,String playerid){

        this.gameid = gameid;
        this.playerid = playerid;
    }
    public KickerGameStats()
    {

    }
    public void fetch()
    {
        try
        {
            PlayerGameKick pgk = new PlayerGameKick(playerid,gameid);
            pgk.fetch();
            setFgAtt(pgk.getFgAtt());
            setFgMd(pgk.getFgMd());
            setXpAtt(pgk.getXpAtt());
            setXpMd(pgk.getXpMd());
        }
        catch(DLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "KickerGameStats{" +
                "gameid='" + gameid + '\'' +
                ", playerid='" + playerid + '\'' +
                ", fgAtt=" + fgAtt +
                ", fgMd=" + fgMd +
                ", xpAtt=" + xpAtt +
                ", xpMd=" + xpMd +
                '}';
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    public int getFgAtt() {
        return fgAtt;
    }

    public void setFgAtt(int fgAtt) {
        this.fgAtt = fgAtt;
    }

    public int getFgMd() {
        return fgMd;
    }

    public void setFgMd(int fgMd) {
        this.fgMd = fgMd;
    }

    public int getXpAtt() {
        return xpAtt;
    }

    public void setXpAtt(int xpAtt) {
        this.xpAtt = xpAtt;
    }

    public int getXpMd() {
        return xpMd;
    }

    public void setXpMd(int xpMd) {
        this.xpMd = xpMd;
    }
}
