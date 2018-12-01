package main.java.datalayer.ui.stats.game;

import main.java.datalayer.database.DLException;

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
