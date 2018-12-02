package main.java.datalayer.ui.stats.game;

import main.java.datalayer.database.DLException;
import main.java.datalayer.database.FootballDatabase;
import java.io.*;
import java.util.ArrayList;

public class ReceiverGameStats {

    private String gameid;
    private String playerid;
    private String jNumber;
    private String position;
    private String fName;
    private String lName;
    private String team;
    private int rec;
    private int recYds;
    private int recTd;
    private int rushAtt;
    private int rushYds;
    private int rushTd;
    private int fum;
    private String path = "main/resources/queries/stats/game/";
    private static FootballDatabase db = new FootballDatabase();


    public ReceiverGameStats(String gameid, String playerid) {

        this.gameid = gameid;
        this.playerid = playerid;

    }

    public ReceiverGameStats() {

    }

    public void fetch(String type) throws DLException {
        try {

            String filename = null;
            String filepath = null;

            if(type.equals("all"))
            {
                filename = "wrteall.sql";
                filepath = path+"all/";
            }
            if(type.equals("specific"))
            {
                filename ="wrtespecific.sql";
                filepath = path+"specific/";
            }
            String query = getQuery(filename,filepath);
            ArrayList<String[]> stats = db.getData(query, new ArrayList<String>(){{add(playerid);add(gameid);}});
            String[] rbgamestats = stats.get(1);
            jNumber = rbgamestats[0];
            position = rbgamestats[1];
            fName = rbgamestats[2];
            lName = rbgamestats[3];
            team = rbgamestats[4];
            rec = Integer.parseInt(rbgamestats[5]);
            recYds = Integer.parseInt(rbgamestats[6]);
            recTd = Integer.parseInt(rbgamestats[7]);
            rushAtt = Integer.parseInt(rbgamestats[8]);
            rushYds = Integer.parseInt(rbgamestats[9]);
            rushTd = Integer.parseInt(rbgamestats[10]);
            fum = Integer.parseInt(rbgamestats[11]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getQuery(String filename, String filepath) throws IOException {
        String query = null;
        try {
            BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(filepath + filename + ".sql")));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            query = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public String toString() {
        return "ReceiverGameStats{" +
                "gameid='" + gameid + '\'' +
                ", playerid='" + playerid + '\'' +
                ", jNumber='" + jNumber + '\'' +
                ", position='" + position + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", team='" + team + '\'' +
                ", rec=" + rec +
                ", recYds=" + recYds +
                ", recTd=" + recTd +
                ", rushAtt=" + rushAtt +
                ", rushYds=" + rushYds +
                ", rushTd=" + rushTd +
                ", fum=" + fum +
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

    public String getjNumber() {
        return jNumber;
    }

    public void setjNumber(String jNumber) {
        this.jNumber = jNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getRec() {
        return rec;
    }

    public void setRec(int rec) {
        this.rec = rec;
    }

    public int getRecYds() {
        return recYds;
    }

    public void setRecYds(int recYds) {
        this.recYds = recYds;
    }

    public int getRecTd() {
        return recTd;
    }

    public void setRecTd(int recTd) {
        this.recTd = recTd;
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

    public int getFum() {
        return fum;
    }

    public void setFum(int fum) {
        this.fum = fum;
    }
}