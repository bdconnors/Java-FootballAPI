package main.java.datalayer.database.models;

import main.java.datalayer.database.DBInterface;
import main.java.datalayer.database.DLException;
import main.java.datalayer.database.League;

import java.util.ArrayList;

public class AllLeagues extends DBInterface {

    public ArrayList<League> allLeagues = new ArrayList<>();
    public AllLeagues() {
    }
    public void fetch()throws DLException
    {
        try
        {
            setQuery("user/getallleagues.sql");
            super.fetch();
            League league = null;
            ArrayList<String[]> leagues = getResults();
            for(String[] l: leagues)
            {
                league = new League(l);
                allLeagues.add(league);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public ArrayList<League> getAllLeagues() {
        return allLeagues;
    }

    public void setAllLeagues(ArrayList<League> allLeagues) {
        this.allLeagues = allLeagues;
    }
}
