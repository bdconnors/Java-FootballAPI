package main.java.datalayer.database.models.positions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.java.datalayer.database.DBInterface;
import main.java.datalayer.database.DLException;
import main.java.datalayer.database.models.Player;
import main.java.datalayer.database.models.positions.Kicker;
import main.java.datalayer.database.models.positions.QuarterBack;
import main.java.datalayer.database.models.positions.Receiver;
import main.java.datalayer.database.models.positions.RunningBack;

import java.util.ArrayList;
import java.util.Collections;

public class AllPlayersCumulative extends DBInterface {
    public ArrayList<Player> allPlayers = new ArrayList<>();

    public AllPlayersCumulative() {

    }
    public void fetch()throws DLException
    {
        allQB();
        allRB();
        allWR();
        allTE();
        allK();
    }
    public void allWR() throws DLException {

        ArrayList<String[]> players = new ArrayList<>();
        Player play = null;
        setQuery("stats/cumulative/allwr.sql");
        super.fetch();
        players = getResults();
        for(String[] player : players)
        {
            play = new Receiver(player);
            allPlayers.add(play);
        }
    }
    public void allQB() throws DLException {

        ArrayList<String[]> players = new ArrayList<>();
        Player play = null;
        setQuery("stats/cumulative/allqb.sql");
        super.fetch();
        players = getResults();
        for(String[] player : players)
        {
            play = new QuarterBack(player);
            allPlayers.add(play);
        }
    }
    public void allTE() throws DLException {

        ArrayList<String[]> players = new ArrayList<>();
        Player play = null;
        setQuery("stats/cumulative/allte.sql");
        super.fetch();
        players = getResults();
        for(String[] player : players)
        {
            play = new Receiver(player);
            allPlayers.add(play);
        }
    }
    public void allRB() throws DLException {

        ArrayList<String[]> players = new ArrayList<>();
        Player play = null;
        setQuery("stats/cumulative/allrb.sql");
        super.fetch();
        players = getResults();
        for(String[] player : players)
        {
            play = new RunningBack(player);
            allPlayers.add(play);
        }
    }
    public void allK() throws DLException {

        ArrayList<String[]> players = new ArrayList<>();
        Player play = null;
        setQuery("stats/cumulative/allk.sql");
        super.fetch();
        players = getResults();
        for(String[] player : players)
        {
            play = new Kicker(player);
            allPlayers.add(play);
        }
    }
    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }
    public void setAllPlayers(ArrayList<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }
}


