package models;
import java.sql.*;
import java.util.ArrayList;
public class FootballDatabase {

    private String uri;
    private String user;
    private String driver;
    private String password;
    private Connection conn = null;

    public FootballDatabase() {
        uri = "jdbc:mariadb://localhost:3305/football";
        driver = "org.mariadb.jdbc:mariadb-java-client:2.3.0";
        user = "root";
        password = "student";
    }

    public FootballDatabase(String uri, String driver, String user, String password) {
        this.uri = uri;
        this.user = user;
        this.driver = driver;
        this.password = password;
    }

    public boolean connect() throws DLException {
        boolean status = true;
        try {
            if (conn == null || conn.getAutoCommit()) {
                conn = DriverManager.getConnection(uri, user, password);
                status = true;
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
            throw new DLException(e, "Could Not Connect to Server", "uri: " + uri, "User: " + user, "Driver: " + driver);
        }
        return status;
    }

    public boolean close() throws DLException {
        boolean status = true;
        try {
            if (conn != null && conn.getAutoCommit()) {
                conn.close();
                status = true;
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
            throw new DLException(e, "Could Not Close Connection", "uri: " + uri, "User: " + user, "Driver: " + driver);
        }
        return status;
    }

    public ArrayList<String[]> getData(String query, ArrayList<String> list) throws DLException {
        ArrayList<String[]> data = new ArrayList<String[]>();
        connect();
        try {
            PreparedStatement stmnt = prepare(query, list);
            ResultSet rs = stmnt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            String[] colNameRow = new String[colCount];
            for (int i = 0; i < colCount; i++) {
                colNameRow[i] = rsmd.getColumnName(i + 1);
            }
            data.add(colNameRow);
            while (rs.next()) {
                String[] valueRow = new String[colCount];
                for (int i = 1; i <= colCount; i++) {
                    valueRow[i - 1] = rs.getString(i);
                }
                data.add(valueRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        close();
        return data;
    }

    public ArrayList<String[]> getData(String query) throws DLException {
        connect();
        ArrayList<String[]> data = new ArrayList<String[]>();
        int numFields = 0;
        try {
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                numFields = rsmd.getColumnCount();
                String[] row = new String[numFields];
                for (int i = 1; i <= numFields; i++) {
                    row[i - 1] = rs.getString(i);
                }
                data.add(row);
            }
            close();
        } catch (Exception e) {
            throw new DLException(e, "Could Not Retrieve Data", "query: " + query, "numFields: " + String.valueOf(numFields));
        }
        return data;
    }

    public int setData(String update, ArrayList<String> list) throws DLException {
        connect();
        int effected = 0;
        try {
            effected = executeStmnt(update, list);
        } catch (Exception e) {
            effected = -1;
            throw new DLException(e, "Could Not Set Data", "Query: " + update);
        }
        close();

        return effected;
    }

    public int setData(String update) throws DLException {
        connect();
        int effected = 0;
        try {
            Statement stmnt = conn.createStatement();
            effected = stmnt.executeUpdate(update);
        } catch (Exception e) {
            e.printStackTrace();
            effected = -1;
            throw new DLException(e, "Could Not Set Data", "Query: " + update);
        }
        return effected;
    }

    public PreparedStatement prepare(String query, ArrayList<String> list) throws DLException {
        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            for (int i = 0; i < list.size(); i++) {
                String value = list.get(i);
                stmnt.setString(i + 1, value);
            }
            return stmnt;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DLException(e, query, "Could Not Prepare Statement");
        }

    }

    public int executeStmnt(String update, ArrayList<String> list) throws DLException {
        PreparedStatement stmnt = null;
        int effected = 0;
        try {
            connect();
            stmnt = prepare(update, list);
            effected = stmnt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DLException(e, update, "Could Not Execute Statement");
        }
        return effected;
    }

    public void descTable(String query) throws DLException {
        try {
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            ArrayList<String[]> data = getData(query);
            TablePrinter print = new TablePrinter(rs, data);
            print.printData();
            print.printTypes();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DLException(e, "Could Not print meta data");
        }
    }

    public void loadPlayersByPos(String pos) throws DLException {
        try {
            startTrans();
            MySportsFeeds feed = new MySportsFeeds();
            ArrayList<String[]> players = feed.getPlayersByPosition(pos);
            for (int i = 0; i < players.size(); i++) {
                String[] curPlayer = players.get(i);
                Player player = new Player(curPlayer);
                player.post();
            }
            endTrans();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DLException(e, "could not load player data");
        }
    }

    public void loadAllTeams() throws DLException {
        try {
            startTrans();
            MySportsFeeds feed = new MySportsFeeds();
            String[] curTeam = new String[2];
            ArrayList<String[]> teams = feed.getAllTeams();
            for (int i = 0; i < teams.size(); i++) {
                curTeam = teams.get(i);
                Team team = new Team(curTeam);
                team.post();
            }
            endTrans();
        } catch (DLException e) {
            e.printStackTrace();
            throw new DLException(e, "cannot load teams");
        }
    }

    public void loadGamesByTeam(String team) throws DLException {
        try {
            startTrans();
            MySportsFeeds feed = new MySportsFeeds();
            String[] curGame = new String[3];
            String gameCheck = null;
            ArrayList<String[]> games = feed.getGamesByTeam(team);
            for (int i = 0; i < games.size(); i++) {
                curGame = games.get(i);
                gameCheck = "select * from game where gameid = '" + curGame[0] + "';";
                if (existsInDB(gameCheck) == true) {

                } else {
                    Game game = new Game(curGame);
                    game.post();
                }
            }
            endTrans();
        } catch (DLException e) {
            e.printStackTrace();
            throw new DLException(e, "could not load team data");
        }
    }

    public void loadPlayerStats(String team, String pos) throws DLException {
        try {
            startTrans();
            MySportsFeeds feed = new MySportsFeeds();
            ArrayList<int[]> stats = feed.getStatsByTeamPos(team, pos);
            int[] curStats = new int[22];
            DefenseStats dfs = new DefenseStats();

            for (int i = 0; i < stats.size(); i++) {
                curStats = stats.get(i);
                PlayerStats pstats = new PlayerStats(curStats);
                pstats.post();
            }
            endTrans();
        } catch (DLException e) {
            e.printStackTrace();
            throw new DLException(e, "could not load team data");
        }
    }

    public void loadDefStats(String team) throws DLException {
        try {
            startTrans();
            MySportsFeeds feed = new MySportsFeeds();
            ArrayList<int[]> stats = feed.getDefStatsByTeam(team);
            int[] curStats = new int[12];
            DefenseStats dfs = new DefenseStats();

            for (int i = 0; i < stats.size(); i++) {
                curStats = stats.get(i);
                DefenseStats dstats = new DefenseStats(curStats);
                dstats.setTeam(team);
                dstats.post();
            }
            endTrans();
        } catch (DLException e) {
            e.printStackTrace();
            throw new DLException(e, "could not load team data");
        }
    }

    public boolean existsInDB(String query) throws DLException {
        boolean exists = false;

        try {
            ArrayList<String[]> data = getData(query);
            if (data.isEmpty()) {
                exists = false;
            } else {
                exists = true;
            }
        } catch (DLException e) {
            e.printStackTrace();
            throw new DLException(e, "could not check if exists");
        }
        return exists;
    }

    public void startTrans() throws DLException {
        try {
            connect();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DLException(e, "Could not start transaction");
        }
    }

    public void endTrans() throws DLException {
        try {
            conn.commit();
            conn.setAutoCommit(true);
            close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DLException(e, "Could not end transaction");
        }
    }

    public void rollbackTrans() throws DLException {
        try {
            conn.rollback();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DLException(e, "Could not rollback transaction");
        }
    }
}//end FootballDatabase