<<<<<<< HEAD
package ritdatabaseproject.server.models;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    private String email;
    private String password;
    private String username;
    private String teamName;
    private String ownerName;
    private String leagueName;
    private int totalPoints;
    private int weekPoints;
    private int currStanding;
    private boolean leagueManager = false;
    private boolean admin = false;


    public User(String[] user) {
        email = user[0];
        password = user[1];
        username = user[2];
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String fTeamName) {
        this.teamName = fTeamName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String fOwnerName) {
        this.ownerName = fOwnerName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String fLeagueName) {
        this.leagueName = fLeagueName;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int tPoints) {
        this.totalPoints = tPoints;
    }

    public int getWeekPoints() {
        return weekPoints;
    }

    public void setWeekPoints(int wPoints) {
        this.weekPoints = wPoints;
    }

    public int getCurrStanding() {
        return currStanding;
    }

    public void setCurrStanding(int standing) {
        this.currStanding = standing;
    }

    public boolean getLeagueManager() {
        return leagueManager;
    }

    public void setLeagueManager(boolean lm) {
        this.leagueManager = lm;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean ad) {
        this.admin = ad;
    }

    public void requestTrade() throws DLException {
        Scanner reader = new Scanner(System.in);
        System.out.println("What is the team you would like to trade with?");
        String tradeTeam = reader.next();
        System.out.println("What are the names of the players you would like from their team? seperate by commas.");
        String opposingPlayers = reader.next();
        System.out.println("What are the names of the players from your team you would like to trade? seperate by commas.");
        String myPlayers = reader.next();

        String sqlQuery = "INSERT INTO pendingTrades offeringTeam, respondingTeam, offeringPlayers, respondingPlayers VALUES ?,?,?,?;";
        ArrayList<String> vals = new ArrayList<>();
        vals.add(this.teamName);
        vals.add(tradeTeam);
        vals.add(myPlayers);
        vals.add(opposingPlayers);

        FootballDatabase fb = new FootballDatabase();
        fb.setData(sqlQuery, vals);

    }
    public void requestName() throws DLException {
        System.out.println("What is the new name you would like?");
        Scanner reader = new Scanner(System.in);
        String response = reader.next();
        reader.close();

        String sqlQuery = "INSERT INTO requestName oldName, newName Values ?,?";
        ArrayList<String> values = new ArrayList<>();
        values.add(this.teamName);
        values.add(response);

        FootballDatabase fb = new FootballDatabase();
        fb.setData(sqlQuery, values);
    }
    public void requestJoinLeague() throws DLException {
        System.out.println("What is the Name of the league you would like to join?");
        Scanner reader = new Scanner(System.in);
        String response = reader.next();
        reader.close();

        String sqlQuery = "INSERT INTO requestLeague teamName,leagueName VALUES ?,?;";
        ArrayList<String> vals = new ArrayList<>();
        vals.add(this.teamName);
        vals.add(response);
        FootballDatabase fb = new FootballDatabase();
        fb.setData(sqlQuery, vals);
    }
    public void respondTrade(String[] theirPlayers, String[] myPlayers, String theirTeam, String tradeID) throws DLException {
        System.out.println("Requested trade from " + theirTeam);
        System.out.println(theirTeam + " is offering: ");
        for (String player : theirPlayers) {
            System.out.println(player);
        }
        System.out.println("In exchange for: ");
        for (String player : myPlayers) {
            System.out.println(player);
        }
        System.out.println("Will you accept their trade? (y/n)");
        Scanner reader = new Scanner(System.in);
        String response = reader.next();
        reader.close();

        FootballDatabase fb = new FootballDatabase();
        String sqlQuery;
        ArrayList<String> responseArray = new ArrayList<>();
        if ("y".equals(response)) {
            sqlQuery = "UPDATE pendingTrade SET approval = ?, WHERE tradeID = ?;";
            responseArray.add("true");
            responseArray.add(tradeID);
        } else {
            sqlQuery = "DELETE FROM pendingTrade WHERE tradeID = ?;";
            responseArray.add(tradeID);
        }
        fb.setData(sqlQuery, responseArray);
    }// end respondTrade


=======
package server.models;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    private String email;
    private String password;
    private String username;
    private String teamName;
    private String ownerName;
    private String leagueName;
    private int totalPoints;
    private int weekPoints;
    private int currStanding;
    private boolean leagueManager = false;
    private boolean admin = false;


    public User(String[] user) {
        email = user[0];
        password = user[1];
        username = user[2];
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String fTeamName) {
        this.teamName = fTeamName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String fOwnerName) {
        this.ownerName = fOwnerName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String fLeagueName) {
        this.leagueName = fLeagueName;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int tPoints) {
        this.totalPoints = tPoints;
    }

    public int getWeekPoints() {
        return weekPoints;
    }

    public void setWeekPoints(int wPoints) {
        this.weekPoints = wPoints;
    }

    public int getCurrStanding() {
        return currStanding;
    }

    public void setCurrStanding(int standing) {
        this.currStanding = standing;
    }

    public boolean getLeagueManager() {
        return leagueManager;
    }

    public void setLeagueManager(boolean lm) {
        this.leagueManager = lm;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean ad) {
        this.admin = ad;
    }

    public void requestTrade() throws DLException {
        Scanner reader = new Scanner(System.in);
        System.out.println("What is the team you would like to trade with?");
        String tradeTeam = reader.next();
        System.out.println("What are the names of the players you would like from their team? seperate by commas.");
        String opposingPlayers = reader.next();
        System.out.println("What are the names of the players from your team you would like to trade? seperate by commas.");
        String myPlayers = reader.next();

        String sqlQuery = "INSERT INTO pendingTrades offeringTeam, respondingTeam, offeringPlayers, respondingPlayers VALUES ?,?,?,?;";
        ArrayList<String> vals = new ArrayList<>();
        vals.add(this.teamName);
        vals.add(tradeTeam);
        vals.add(myPlayers);
        vals.add(opposingPlayers);

        FootballDatabase fb = new FootballDatabase();
        fb.setData(sqlQuery, vals);

    }
    public void requestName() throws DLException {
        System.out.println("What is the new name you would like?");
        Scanner reader = new Scanner(System.in);
        String response = reader.next();
        reader.close();

        String sqlQuery = "INSERT INTO requestName oldName, newName Values ?,?";
        ArrayList<String> values = new ArrayList<>();
        values.add(this.teamName);
        values.add(response);

        FootballDatabase fb = new FootballDatabase();
        fb.setData(sqlQuery, values);
    }
    public void requestJoinLeague() throws DLException {
        System.out.println("What is the Name of the league you would like to join?");
        Scanner reader = new Scanner(System.in);
        String response = reader.next();
        reader.close();

        String sqlQuery = "INSERT INTO requestLeague teamName,leagueName VALUES ?,?;";
        ArrayList<String> vals = new ArrayList<>();
        vals.add(this.teamName);
        vals.add(response);
        FootballDatabase fb = new FootballDatabase();
        fb.setData(sqlQuery, vals);
    }
    public void respondTrade(String[] theirPlayers, String[] myPlayers, String theirTeam, String tradeID) throws DLException {
        System.out.println("Requested trade from " + theirTeam);
        System.out.println(theirTeam + " is offering: ");
        for (String player : theirPlayers) {
            System.out.println(player);
        }
        System.out.println("In exchange for: ");
        for (String player : myPlayers) {
            System.out.println(player);
        }
        System.out.println("Will you accept their trade? (y/n)");
        Scanner reader = new Scanner(System.in);
        String response = reader.next();
        reader.close();

        FootballDatabase fb = new FootballDatabase();
        String sqlQuery;
        ArrayList<String> responseArray = new ArrayList<>();
        if ("y".equals(response)) {
            sqlQuery = "UPDATE pendingTrade SET approval = ?, WHERE tradeID = ?;";
            responseArray.add("true");
            responseArray.add(tradeID);
        } else {
            sqlQuery = "DELETE FROM pendingTrade WHERE tradeID = ?;";
            responseArray.add(tradeID);
        }
        fb.setData(sqlQuery, responseArray);
    }// end respondTrade


>>>>>>> d8b5dae7ef8acd4720539f72b9f88f54c8967cb4
}// end class