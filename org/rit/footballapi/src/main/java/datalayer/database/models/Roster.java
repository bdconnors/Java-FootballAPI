package main.java.datalayer.database.models;
import main.java.datalayer.database.DBInterface;
import main.java.datalayer.database.DLException;
import java.util.ArrayList;

public class Roster extends DBInterface {
    /*
       Roster Class to hold information for each user's team rosters
    */
    public String userID;
    public String leagueID;
    public String rosterID;
    public String qb;
    public String rb1;
    public String rb2;
    public String wr1;
    public String wr2;
    public String te;
    public String flex;
    public String def;
    public String kicker;

    public Roster(String userID) {
        // default constructor for roster
        setUserID(userID);
        query = "/user/roster.sql";
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

    public void setRoster(String[] players){


    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(String leagueID) {
        this.leagueID = leagueID;
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

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }
}

