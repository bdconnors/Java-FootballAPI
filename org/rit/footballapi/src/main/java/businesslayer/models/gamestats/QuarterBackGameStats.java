package main.java.businesslayer.models.gamestats;

import main.java.datalayer.database.DLException;
import main.java.datalayer.models.player.playerstats.playergamestats.PlayerGameMisc;
import main.java.datalayer.models.player.playerstats.playergamestats.PlayerGamePass;
import main.java.datalayer.models.player.playerstats.playergamestats.PlayerGameRush;

public class QuarterBackGameStats {

    private String gameid;
    private String playerid;
    private int passAtt;
    private int passComp;
    private int passYds;
    private int passTds;
    private int intThr;
    private int fum;
    private int rushAtt;
    private int rushYds;
    private int rushTd;

    public QuarterBackGameStats(String gameid,String playerid){

        this.gameid = gameid;
        this.playerid = playerid;

    }
    public QuarterBackGameStats()
    {

    }
    public void fetch()throws DLException
    {
        try
        {
            PlayerGamePass pgp = new PlayerGamePass(gameid,playerid);
            pgp.fetch();
            setPassAtt(pgp.getPassAtt());
            setPassComp(pgp.getPassComp());
            setPassYds(pgp.getPassYds());
            setPassTds(pgp.getPassTds());

            PlayerGameMisc pgm = new PlayerGameMisc(gameid,playerid);
            pgm.fetch();
            setIntThr(pgm.getIntThr());
            setFum(pgm.getFum());

            PlayerGameRush pgr = new PlayerGameRush(gameid,playerid);
            pgr.fetch();
            setRushAtt(pgr.getRushAtt());
            setRushYds(pgr.getRushYds());
        }
        catch(DLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return "QuarterBackGameStats{" +
                "gameid='" + gameid + '\'' +
                ", playerid='" + playerid + '\'' +
                ", passAtt=" + passAtt +
                ", passComp=" + passComp +
                ", passYds=" + passYds +
                ", passTds=" + passTds +
                ", intThr=" + intThr +
                ", rushAtt=" + rushAtt +
                ", rushYds=" + rushYds +
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

    public int getPassAtt() {
        return passAtt;
    }

    public void setPassAtt(int passAtt) {
        this.passAtt = passAtt;
    }

    public int getPassComp() {
        return passComp;
    }

    public void setPassComp(int passComp) {
        this.passComp = passComp;
    }

    public int getPassYds() {
        return passYds;
    }

    public void setPassYds(int passYds) {
        this.passYds = passYds;
    }

    public int getPassTds() {
        return passTds;
    }

    public void setPassTds(int passTds) {
        this.passTds = passTds;
    }

    public int getIntThr() {
        return intThr;
    }

    public void setIntThr(int intThr) {
        this.intThr = intThr;
    }

    public int getFum() {
        return fum;
    }

    public void setFum(int fum) {
        this.fum = fum;
    }

    public int getRushAtt() {
        return rushAtt;
    }

    public void setRushAtt(int rushAtt) {
        this.rushAtt = rushAtt;
    }

    public int getRushYds() {
        return rushYds;
    }

    public void setRushYds(int rushYds) {
        this.rushYds = rushYds;
    }
    public int getRushTd() {
        return rushTd;
    }

    public void setRushTd(int rushTd) {
        this.rushTd = rushTd;
    }
}
