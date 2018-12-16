package org.rit.footballapi.models;

import org.rit.footballapi.util.DLException;

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
        setQuery("getallteams.sql");
        setBindValues(new ArrayList<String>(){{add(getLeagueid());}});
        super.fetch();
        Roster roster = null;
        ArrayList<String[]> teams = getResults();
        for(String[] team:teams)
        {
            roster = new Roster(team[0]);
            roster.fetch();
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
