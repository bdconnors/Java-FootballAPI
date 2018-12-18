package org.rit.footballapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.rit.footballapi.util.DBInterface;
import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class Roster extends DBInterface {
    /*
       Roster Class to hold information for each user's team rosters
    */
    public String teamid;
    public String teamname;
    public String leaguename;

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
    public Roster()
    {}
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
        setTeamname(playerids.get(0)[0]);
        setLeaguename(playerids.get(0)[1]);

        for(String[] playerid :playerids)
        {
            player = new Player(playerid[2]);
            player.fetch();
            player.setInfo();
            players.add(player);
        }
    }

    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getLeaguename() {
        return leaguename;
    }

    public void setLeaguename(String leaguename) {
        this.leaguename = leaguename;
    }
}

