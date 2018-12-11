package main.java.datalayer.database.models;
import main.java.datalayer.database.DBInterface;
import main.java.datalayer.database.DLException;
import main.java.datalayer.database.League;

import java.util.ArrayList;

public class Roster extends League {
    /*
       Roster Class to hold information for each user's team rosters
    */
    public String rosterID;
    public String teamname;
    public String qb;
    public String rb1;
    public String rb2;
    public String wr1;
    public String wr2;
    public String te;
    public String flex;
    public String def;
    public String k;

    public Roster(String userID) {
        // default constructor for roster
        setUserid(userID);
        query = "/user/getroster.sql";
        bindValues = new ArrayList<String>() {{ add(userID); }};
    }

    public Roster(String[] players)
    {
        setRoster(players);
    }
    public void fetch()throws DLException
    {
        setQuery(query);
        setBindValues(bindValues);
        super.fetch();
        setRoster(super.getResults().get(0));
    }
    public int put()throws DLException
    {
        setQuery("/user/populateroster.sql");
        setBindValues(new ArrayList<String>(){{
            add(teamname);add(qb);add(rb1);add(rb2);
            add(wr1);add(wr2);add(te);add(flex);add(def);
            add(k);add(rosterID);
        }});
        return super.put();
    }
    public int post()throws DLException
    {
        setQuery("/user/createloadedroster.sql");
        setBindValues(new ArrayList<String>(){{
            add(getLeagueid());add(getUserid());add(teamname);
            add(qb);add(rb1);add(rb2);add(wr1);add(wr2);
            add(te);add(flex);add(def);add(k);
        }});
        return super.put();
    }
    public int delete()throws DLException
    {
        setQuery("/user/deleteroster.sql");
        setBindValues(new ArrayList<String>(){{add(rosterID);}});
        return super.delete();
    }
    public void setRoster(String[] players)
    {
        setRosterID(players[0]);setLeagueid(players[1]);setUserid(players[2]);
        setTeamname(players[3]);setQb(players[4]);setRb1(players[5]);setRb2(players[6]);
        setWr1(players[7]);setWr2(players[8]);setTe(players[9]);setFlex(players[10]);
        setDef(players[11]);setK(players[12]);
    }

    public String getRosterID() {
        return rosterID;
    }

    public void setRosterID(String rosterID) {
        this.rosterID = rosterID;
    }

    public String getQb() {
        return qb;
    }

    public void setQb(String qb) {
        this.qb = qb;
    }

    public String getRb1() {
        return rb1;
    }

    public void setRb1(String rb1) {
        this.rb1 = rb1;
    }

    public String getRb2() {
        return rb2;
    }

    public void setRb2(String rb2) {
        this.rb2 = rb2;
    }

    public String getWr1() {
        return wr1;
    }

    public void setWr1(String wr1) {
        this.wr1 = wr1;
    }

    public String getWr2() {
        return wr2;
    }

    public void setWr2(String wr2) {
        this.wr2 = wr2;
    }

    public String getTe() {
        return te;
    }

    public void setTe(String te) {
        this.te = te;
    }

    public String getFlex() {
        return flex;
    }

    public void setFlex(String flex) {
        this.flex = flex;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }
}

