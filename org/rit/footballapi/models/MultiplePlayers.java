package org.rit.footballapi.models;


import org.rit.footballapi.util.DBInterface;
import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class MultiplePlayers extends DBInterface {
    public ArrayList<Player> player = new ArrayList<>();

    public MultiplePlayers(String pos)throws DLException {

        if(pos.equalsIgnoreCase("Wr"))
            allWR();
        else if(pos.equalsIgnoreCase("te"))
            allTE();
        else if(pos.equalsIgnoreCase("qb"))
            allQB();
        else if(pos.equalsIgnoreCase("k"))
            allK();

    }
    public MultiplePlayers()throws DLException {

        fetch();
    }
    public MultiplePlayers(int playerid)throws DLException
    {
        playerByID(String.valueOf(playerid));
    }
    public MultiplePlayers(String pos,String fname,String lname)throws DLException
    {
        playerByName(pos,fname,lname);
    }
    public void fetch()throws DLException
    {
        allQB();
        allRB();
        allWR();
        allTE();
        allK();
    }
    public void playerByName(String pos,String fname,String lname)throws DLException
    {
        Player player = null;
        if(pos.equalsIgnoreCase("wr")||pos.equalsIgnoreCase("te")) {
            player = new Receiver(fname,lname);
        }else if(pos.equalsIgnoreCase("qb"))
        {
            player = new QuarterBack(fname,lname);
        }else if(pos.equalsIgnoreCase("rb"))
        {
            player = new RunningBack(fname,lname);
        }else if(pos.equalsIgnoreCase("k"))
        {
            player = new Kicker(fname,lname);
        }
        player.fetch();
        this.player.add(player);
    }
    public void playerByID(String playerid)throws DLException
    {
        Player player = new Player(playerid);
        player.fetch();
        String pos = player.getPos();
        if(pos.equalsIgnoreCase("wr")||pos.equalsIgnoreCase("te")) {
            player = new Receiver(playerid);
        }else if(pos.equalsIgnoreCase("qb"))
        {
            player = new QuarterBack(playerid);
        }else if(pos.equalsIgnoreCase("rb"))
        {
            player = new RunningBack(playerid);
        }else if(pos.equalsIgnoreCase("k"))
        {
            player = new Kicker(playerid);
        }
        player.fetch();
        this.player.add(player);
    }

    public void allWR() throws DLException {

        ArrayList<String[]> players = new ArrayList<>();
        Player play = null;
        setQuery("allwr.sql");
        super.fetch();
        players = getResults();
        for(String[] player : players) {
            play = new Receiver(player);
        }       this.player.add(play);
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
            this.player.add(play);
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
            this.player.add(play);
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
            this.player.add(play);
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
            this.player.add(play);
        }
    }

    public ArrayList<Player> getPlayer() {
        return player;
    }

    public void setPlayer(ArrayList<Player> player) {
        this.player = player;
    }
}


