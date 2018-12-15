package org.rit.footballapi.services;

import org.rit.footballapi.models.MultiplePlayers;
import org.rit.footballapi.models.Player;
import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class PlayerService {

    public ArrayList<Player> players;

    public PlayerService()
    {}
    public void getPlayersByPos(String pos)throws DLException
    {
        MultiplePlayers players = new MultiplePlayers(pos);
        this.players = players.getPlayer();
    }
    public void getAllPlayers()throws DLException
    {
        MultiplePlayers players = new MultiplePlayers();
        this.players = players.getPlayer();
    }
    public void getPlayerByID(String playerid)throws DLException
    {
        MultiplePlayers players = new MultiplePlayers(Integer.parseInt(playerid));
        this.players = players.getPlayer();
    }
    public void getPlayerByName(String pos,String fname,String lname)throws DLException
    {
        MultiplePlayers players = new MultiplePlayers(pos,fname,lname);
        this.players = players.getPlayer();
    }

}
