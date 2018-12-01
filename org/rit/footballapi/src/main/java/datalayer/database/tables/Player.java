package main.java.datalayer.database.tables;

import main.java.datalayer.database.*;
import java.util.ArrayList;

public class Player {

    private String id;
    private String fName;
    private String lName;
    private String team;
    private String position;
    private String jNumber;
    private static FootballDatabase db = new FootballDatabase();

    public Player(String[] player) {
        id = player[0];
        fName = player[1];
        lName = player[2];
        team = player[3];
        position = player[4];
        jNumber = player[5];

    }

    public Player(String id) {
        this.id = id;

    }

    public Player() {

    }
    public void fetch() throws DLException {
        //SQL query string
        String query = "SELECT FirstName,LastName,Team,Pos,JerseyNumber FROM player WHERE playerid = ?;";

        ArrayList<String> values = new ArrayList<>();
        values.add(id);

        try {

            ArrayList<String[]> info = db.getData(query, values);
            String[] fields = info.get(1);
            fName = fields[0];
            lName = fields[1];
            team = fields[2];
            position = fields[3];
            jNumber = fields[4];

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No Record Found");

        }

    }

    public int post() throws DLException {

        int effected;
        ArrayList<String> values = new ArrayList<>();
        String insert = "INSERT INTO player(PlayerID,FirstName,LastName,Team,Pos,JerseyNumber)VALUES(?,?,?,?,?,?);";
        values.add(id);
        values.add(fName);
        values.add(lName);
        values.add(team);
        values.add(position);
        values.add(jNumber);

        try {
            effected = db.setData(insert, values);
        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();
        }

        return effected;
    }

    public int put() throws DLException {

        int effected;
        String update = "UPDATE player SET FirstName= ? , LastName = ? , Team = ?, Position = ?,JerseyNumber= ? WHERE PlayerID = ?;";
        ArrayList<String> values = new ArrayList<>();

        values.add(fName);
        values.add(lName);
        values.add(team);
        values.add(position);
        values.add(jNumber);
        values.add(id);

        try {
            //perform update and return number of effected records
            effected = db.setData(update, values);
        } catch (DLException e) {
            effected = -1;
        }

        return effected;
    }
    public int delete() throws DLException {

        //effected records
        int effected;
        //SQL delete string
        String delete = "DELETE FROM player WHERE PlayerID=?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(id);


        try {

            //perform delete and return number of effected records
            effected = db.setData(delete, values);

        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();

        }

        return effected;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", team='" + team + '\'' +
                ", position='" + position + '\'' +
                ", jNumber='" + jNumber + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getjNumber() {
        return jNumber;
    }

    public void setjNumber(String jNumber) {
        this.jNumber = jNumber;
    }
}