package main.java.datalayer.ui.stats.game;

import main.java.datalayer.database.DLException;
import main.java.datalayer.database.FootballDatabase;
import java.io.*;
import java.util.ArrayList;



public class RunningBackGameStats {

    private String gameid;
    private String playerid;
    private String jNumber;
    private String position;
    private String fName;
    private String lName;
    private String team;
    private int rushAtt;
    private int rushYds;
    private int rushTd;
    private int rec;
    private int recYds;
    private int recTd;
    private int fum;

    private static FootballDatabase db = new FootballDatabase();


    public RunningBackGameStats(String gameid, String playerid){

        this.gameid = gameid;
        this.playerid = playerid;

    }
    public RunningBackGameStats()
    {

    }
    public void fetch()throws DLException
    {
        try
        {
            String query = getQuery("rbgamestats","main/resources/queries/");
            ArrayList<String[]> stats = db.getData(query,new ArrayList<String>() {{add(playerid);add(gameid);}});
            String[] rbgamestats = stats.get(1);
            jNumber = rbgamestats[0];
            position = rbgamestats[1];
            fName = rbgamestats[2];
            lName = rbgamestats[3];
            team = rbgamestats[4];
            rushAtt = Integer.parseInt(rbgamestats[5]);
            rushYds = Integer.parseInt(rbgamestats[6]);
            rushTd = Integer.parseInt(rbgamestats[7]);
            rec = Integer.parseInt(rbgamestats[8]);
            recYds = Integer.parseInt(rbgamestats[9]);
            recTd = Integer.parseInt(rbgamestats[10]);
            fum = Integer.parseInt(rbgamestats[11]);
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
