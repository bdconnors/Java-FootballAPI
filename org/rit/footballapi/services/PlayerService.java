package org.rit.footballapi.services;

import org.rit.footballapi.models.MultiplePlayers;
import org.rit.footballapi.models.Player;
import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class PlayerService {

    public MultiplePlayers players;
    public PlayerService()
    {}
    public void getPlayersByPos(String pos)throws DLException
    {
        players = new MultiplePlayers(pos);
    }
    public void getAllPlayers()throws DLException
    {
        players = new MultiplePlayers();
    }
    public void getPlayerByID(String playerid)throws DLException
    {
        players = new MultiplePlayers(Integer.parseInt(playerid));
    }
    public void getPlayerByName(String pos,String fname,String lname)throws DLException
    {
        players = new MultiplePlayers(pos,fname,lname);
    }
}
