package main.java.datalayer.database.models;

import main.java.datalayer.database.*;

import java.util.ArrayList;

public class Team extends Game{

    private String team;
    private String abrv;

    public Team(String[] team) {
        this.team = team[0];
        abrv = team[1];
    }
    public Team(String team, String abrv) {
        this.team = team;
        this.abrv = abrv;
    }
    public Team(String abrv) {
        this.abrv = abrv;
    }

    public Team() {

    }
    public String toString() {
        return "Team{" +
                "team='" + team + '\'' +
                ", abrv='" + abrv + '\'' +
                '}';
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getAbrv() {
        return abrv;
    }

    public void setAbrv(String abrv) {
        this.abrv = abrv;
    }
}