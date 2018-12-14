package org.rit.footballapi.models;


import org.rit.footballapi.util.DBInterface;
import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

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
        setQuery("allwr.sql");
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
        setQuery("allqb.sql");
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
        setQuery("allte.sql");
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
        setQuery("allrb.sql");
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
        setQuery("allk.sql");
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


