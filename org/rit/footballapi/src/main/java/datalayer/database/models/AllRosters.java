package main.java.datalayer.database.models;

import main.java.datalayer.database.DBInterface;
import main.java.datalayer.database.DLException;
import main.java.datalayer.database.League;

import java.util.ArrayList;

public class AllRosters extends League {

    ArrayList<Roster> AllLeagueRosters = new ArrayList<>();

    public AllRosters(String leagueid)
    {
        this.leagueid = leagueid;
    }
    public AllRosters()
    {

    }
    public void fetch()throws DLException
    {
        setQuery("/user/getallleaguerosters.sql");
        setBindValues(new ArrayList<String>(){{add(getLeagueid());}});
        super.fetch();
        Roster roster = null;
        ArrayList<String[]> rosters = getResults();
        for(String[] players:rosters)
        {
            roster = new Roster(players);
            AllLeagueRosters.add(roster);
        }

    }

    public ArrayList<Roster> getAllLeagueRosters() {
        return AllLeagueRosters;
    }

    public void setAllLeagueRosters(ArrayList<Roster> allLeagueRosters) {
        AllLeagueRosters = allLeagueRosters;
    }
}
