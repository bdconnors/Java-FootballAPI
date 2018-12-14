package org.rit.footballapi.models;

import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class Roster extends League {
    /*
       Roster Class to hold information for each user's team rosters
    */
    public String teamid;
    public ArrayList<Player> players= new ArrayList<>();

    public Roster(String teamid) {

        this.teamid=teamid;
        query = "getroster.sql";
        bindValues = new ArrayList<String>(){{add(teamid);}};
    }
    public Roster(String teamid,String playerid) {

        this.teamid=teamid;
        query = "addtoroster.sql";
        bindValues = new ArrayList<String>(){{add(teamid);add(playerid);}};
    }
    public void fetch()throws DLException
    {
       setQuery(query);
       setBindValues(bindValues);
       super.fetch();
       loadRoster(getResults());
    }
    public int post()throws DLException
    {
        setQuery(query);
        setBindValues(bindValues);
        return super.post();

    }
    public void loadRoster(ArrayList<String[]> playerids)throws DLException
    {
        Player player = null;

        for(String[] playerid :playerids)
        {
            player = new Player(playerid[0]);
            player.fetch();
            player.setInfo();
            players.add(player);
        }
    }

}

