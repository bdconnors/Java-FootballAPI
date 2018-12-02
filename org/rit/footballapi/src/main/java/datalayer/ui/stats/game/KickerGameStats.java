package main.java.datalayer.ui.stats.game;

import main.java.datalayer.database.DLException;
import main.java.datalayer.database.FootballDatabase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class KickerGameStats {

    private String gameid;
    private String playerid;
    private String jNumber;
    private String position;
    private String fName;
    private String lName;
    private String team;
    private int fgAtt;
    private int fgMd;
    private int xpAtt;
    private int xpMd;
    private static FootballDatabase db = new FootballDatabase();

    public KickerGameStats(String gameid,String playerid){

        this.gameid = gameid;
        this.playerid = playerid;
    }
    public KickerGameStats()
    {

    }
    public void fetch()throws DLException
    {
        try
        {
            String query = getQuery("qbgamestats","main/resources/queries/");
            ArrayList<String[]> stats = db.getData(query,new ArrayList<String>() {{add(playerid);add(gameid);}});
            String[] qbgamestats = stats.get(1);
            jNumber = qbgamestats[0];
            position = qbgamestats[1];
            fName = qbgamestats[2];
            lName = qbgamestats[3];
            team = qbgamestats[4];
            fgAtt = Integer.parseInt(qbgamestats[5]);
            fgMd = Integer.parseInt(qbgamestats[6]);
            xpAtt = Integer.parseInt(qbgamestats[7]);
            xpMd = Integer.parseInt(qbgamestats[8]);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private String getQuery(String filename,String filepath)throws IOException
    {
        String query = null;
        try {
            BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(filepath+filename+".sql")));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null)
            {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            query = sb.toString();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return query;
    }


}
