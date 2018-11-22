package server.models;

import java.util.ArrayList;

public class Player {

    private String id;
    private String fName;
    private String lName;
    private String team;
    private String position;
    private String jNumber;
    public PlayerCumPass cumPassStats;
    public PlayerCumRec cumRecStats;
    public PlayerCumRush cumRushStats;
    public PlayerCumKick cumKickStats;
    public PlayerCumMisc cumMiscStats;
    private FootballDatabase db = new FootballDatabase();

    Player(String[] player) {
        id = player[0];
        fName = player[1];
        lName = player[2];
        team = player[3];
        position = player[4];
        jNumber = player[5];
        cumPassStats = new PlayerCumPass(id);
        cumRecStats = new PlayerCumRec(id);
        cumRushStats = new PlayerCumRush(id);
        cumKickStats = new PlayerCumKick(id);
        cumMiscStats = new PlayerCumMisc(id);

    }

    public Player(String id) {
        this.id = id;
        cumPassStats = new PlayerCumPass(id);
        cumRecStats = new PlayerCumRec(id);
        cumRushStats = new PlayerCumRush(id);
        cumKickStats = new PlayerCumKick(id);
        cumMiscStats = new PlayerCumMisc(id);

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
            cumPassStats.fetch();
            cumRecStats.fetch();
            cumRushStats.fetch();
            cumKickStats.fetch();
            cumMiscStats.fetch();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No Record Found");

        }

    }

    int post() throws DLException {

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

    public PlayerCumPass getCumPassStats() {
        return cumPassStats;
    }

    public void setCumPassStats(PlayerCumPass cumPassStats) {
        this.cumPassStats = cumPassStats;
    }

    public PlayerCumRec getCumRecStats() {
        return cumRecStats;
    }

    public void setCumRecStats(PlayerCumRec cumRecStats) {
        this.cumRecStats = cumRecStats;
    }

    public PlayerCumRush getCumRushStats() {
        return cumRushStats;
    }

    public void setCumRushStats(PlayerCumRush cumRushStats) {
        this.cumRushStats = cumRushStats;
    }

    public PlayerCumKick getCumKickStats() {
        return cumKickStats;
    }

    public void setCumKickStats(PlayerCumKick cumKickStats) {
        this.cumKickStats = cumKickStats;
    }

    public PlayerCumMisc getCumMiscStats() {
        return cumMiscStats;
    }

    public void setCumMiscStats(PlayerCumMisc cumMiscStats) {
        this.cumMiscStats = cumMiscStats;
    }
}