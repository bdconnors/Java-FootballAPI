package main.java.footballapidata.models;

import main.java.footballapidata.database.*;

import java.util.ArrayList;

public class Team {

    private String name;
    private String abrv;
    private static FootballDatabase db = new FootballDatabase();

    public Team(String[] team) {
        name = team[0];
        abrv = team[1];
    }
    public Team(String name, String abrv) {
        this.name = name;
        this.abrv = abrv;
    }
    public Team(String abrv) {
        this.abrv = abrv;
    }

    public Team() {

    }
    public void fetch() throws DLException {   //SQL query string
        String query = "SELECT name,abbreviation FROM team WHERE abbreviation=?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(abrv);
        try { //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
            ArrayList<String[]> info = db.getData(query, values);
            String[] fields = info.get(1);
            //set name to the first field value
            name = fields[0];
            //set abrv to the second field value
            abrv = fields[1];
        } catch (Exception e) {
            System.out.println("No Record Found");
        }
    }
    public int post() throws DLException {  //effected records
        int effected;
        ArrayList<String> values = new ArrayList<>();
        //SQL Insert String
        String insert = "INSERT INTO team(name,abbreviation)VALUES(?,?);";
        //bind values
        values.add(name);
        values.add(abrv);
        try {
            //perform insert and return number of effected
            effected = db.setData(insert, values);
        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();
        }
        return effected;
    }
    public int put() throws DLException {  //effected records
        int effected;
        //SQL Update String
        String update = "UPDATE team SET name = ? WHERE abbreviation = ?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(name);
        values.add(abrv);
        try { //perform update and return number of effected records
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
        String delete = "DELETE FROM team WHERE abbreviation =?;";
        ArrayList<String> values = new ArrayList<>();
        values.add(abrv);
        try { //perform delete and return number of effected records
            effected = db.setData(delete, values);
        } catch (DLException e) {
            effected = -1;
            e.printStackTrace();
        }
        return effected;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", abrv='" + abrv + '\'' +
                ", db=" + db +
                '}';
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAbrv() { return abrv; }
    public void setAbrv(String abrv) {
        this.abrv = abrv;
    }
}