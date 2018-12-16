package org.rit.footballapi.models;

import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class AllRosters extends League {

    ArrayList<Roster> allLeagueRosters = new ArrayList<>();

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
            allLeagueRosters.add(roster);
        }


    }
    public void getUnset()throws DLException
    {
        setQuery("getunsetrosters.sql");
        setBindValues(new ArrayList<String>(){{add(leagueid);}});
        super.fetch();
        ArrayList<String[]> teams = getResults();
        Roster roster = null;
        for(String[] team : teams)
        {
            roster = new Roster();
            roster.setTeamid(team[0]);
            roster.setTeamname(team[1]);
            allLeagueRosters.add(roster);
        }
    }

    public ArrayList<Roster> getAllLeagueRosters() {
        return allLeagueRosters;
    }

    public void setAllLeagueRosters(ArrayList<Roster> allLeagueRosters) {
        this.allLeagueRosters = allLeagueRosters;
    }
}
