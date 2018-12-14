package org.rit.footballapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.rit.footballapi.util.DBInterface;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Game extends DBInterface {

    private String gameID;
    private String date;
    private String homeTeam;
    private String awayTeam;


    public Game(String[] game) {
        gameID = game[0];
        date = game[1];
        homeTeam = game[2];
        awayTeam = game[3];

    }

    public Game(String gameID) {
        this.gameID = gameID;
    }

    public Game() {

    }
    @Override
    public String toString() {
        return "Game{" +
                "gameID='" + gameID + '\'' +
                ", date='" + date + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                '}';
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }
}