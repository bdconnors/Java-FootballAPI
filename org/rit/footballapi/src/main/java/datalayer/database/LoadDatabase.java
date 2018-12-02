package main.java.datalayer.database;

import java.util.ArrayList;

import main.java.datalayer.database.tables.Game;
import main.java.datalayer.database.tables.stats.cumulative.defense.DefenseCumStats;
import main.java.datalayer.database.tables.stats.cumulative.player.*;
import main.java.datalayer.database.tables.stats.game.defense.DefenseGameStats;
import main.java.datalayer.database.tables.stats.cumulative.defense.MiscDefenseCumStats;
import main.java.datalayer.database.tables.stats.game.defense.MiscDefenseGameStats;
import main.java.datalayer.database.tables.Player;
import main.java.datalayer.database.tables.Team;
import main.java.datalayer.database.tables.stats.game.player.*;

public class LoadDatabase {

    private static MySportsFeeds msf = new MySportsFeeds();
    private static FootballDatabase db = new FootballDatabase();
    private String[] teams;
    private String[] position;

    public LoadDatabase()
    {
        position = new String[]{"WR", "RB", "TE", "QB", "K"};
        teams = new String[]{"ARI","ATL","BAL","BUF","CAR","CHI","CIN","CLE","DAL","DEN","DET","GB","HOU","IND","JAX",
                "KC","LAC","LAR","MIA","MIN","NE","NO","NYG","NYJ","OAK","PHI","PIT","SEA","SF","TB","TEN","WAS"};
    }

    /**Retrieves data on players of the specified position from an API and loads it into database
     * @param pos A String containing a specified player position (Ex.'WR','RB','TE','QB','K')
     */
    public void loadPlayersByPos(String pos) throws DLException {
        try {
            ArrayList<String[]> players = msf.getPlayersByPosition(pos);
            for (String[] curPlayer : players) {
                Player player = new Player(curPlayer);
                player.post();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DLException(e, "could not load player data");
        }
    }
    /**Retrieves data on all 32 NFL teams from an API and loads it into database
     */
    public void loadAllTeams() throws DLException {
        try {
            db.startTrans();
            String[] curTeam;
            ArrayList<String[]> teams = msf.getAllTeams();
            for (String[] team1 : teams) {

                curTeam = team1;
                Team team = new Team(curTeam);
                team.post();
            }
            db.endTrans();
        } catch (DLException e) {
            e.printStackTrace();
            throw new DLException(e, "cannot load teams");
        }
    }
    /**Retrieves data on all games played by a specified NFL team and loads them into database
     * @param team A String containing an NFL team 2 or 3 letter abbreviation (ex. 'NYG' is 'New York Giants')
     */
    public void loadGamesByTeam(String team) throws Exception {
        try {
            String[] curGame;
            String gameCheck;
            ArrayList<String[]> games = msf.getGamesByTeam(team);
            for (String[] g : games) {
                curGame = g;
                gameCheck = "select * from game where gameid = '" + curGame[0] + "';";
                if (!existsInDB(gameCheck)) {
                    Game game = new Game(curGame);
                    game.post();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DLException(e, "could not load team data");
        }
    }
    /**Retrieves all available game statistics of every player from specified position
     * on a specified team and loads them into database
     * @param team A String containing an NFL team 2 or 3 letter abbreviation (ex. 'NYG' is 'New York Giants')
     * @param pos A String containing a specified player position (Ex.'WR','RB','TE','QB','K')
     */
    public void loadPlayerStats(String team, String pos) throws Exception {
        try {
            ArrayList<int[]> stats = msf.getStatsByTeamPos(team, pos);

            for (int[] curPlayer : stats) {

                String playerQuery= "Select * from player where playerid = '"+curPlayer[1]+"';";
                String playerGameQuery = "Select * from playergamepass where gameid = '"+curPlayer[0]+"'AND playerid = '"+curPlayer[1]+"';";
                if(existsInDB(playerQuery))
                {
                    if (!existsInDB(playerGameQuery)) {
                        int[] passStats = new int[]{curPlayer[0], curPlayer[1], curPlayer[2], curPlayer[3], curPlayer[4], curPlayer[5], curPlayer[6]};
                        PlayerGamePass pass = new PlayerGamePass(passStats);
                        pass.post();

                        int[] rushStats = new int[]{curPlayer[0], curPlayer[1], curPlayer[7], curPlayer[8], curPlayer[9],curPlayer[10]};
                        PlayerGameRush rush = new PlayerGameRush(rushStats);
                        rush.post();

                        int[] recStats = new int[]{curPlayer[0], curPlayer[1], curPlayer[11], curPlayer[12], curPlayer[13], curPlayer[14]};
                        PlayerGameRec rec = new PlayerGameRec(recStats);
                        rec.post();

                        int[] kickStats = new int[]{curPlayer[0], curPlayer[1], curPlayer[15], curPlayer[16], curPlayer[17], curPlayer[18]};
                        PlayerGameKick kick = new PlayerGameKick(kickStats);
                        kick.post();

                        int[] miscStats = new int[]{curPlayer[0], curPlayer[1], curPlayer[19], curPlayer[20], curPlayer[21], curPlayer[22]};
                        PlayerGameMisc misc = new PlayerGameMisc(miscStats);
                        misc.post();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DLException(e, "could not load players game data");
        }
    }
    public void loadCumRushStats(String pos) throws DLException {
        try {

            ArrayList<int[]> stats = msf.getOverallPlayerStats(pos);

            for (int[] curPlayer : stats) {

                    int[] rushStats = {curPlayer[0], curPlayer[6], curPlayer[7], curPlayer[8]};
                    PlayerCumRush rush = new PlayerCumRush(rushStats);
                    rush.put();

                }

        } catch (DLException e) {
            e.printStackTrace();
            throw new DLException(e, "could not load players game data");
        }
    }
    public void loadCumPlayerStats(String pos) throws DLException {
        try {

            ArrayList<int[]> stats = msf.getOverallPlayerStats(pos);

            for (int[] curPlayer : stats) {

                String query = "Select * from player where playerid = '"+curPlayer[0]+"';";

                if(existsInDB(query))
                {
                    int[] passStats = {curPlayer[0], curPlayer[1], curPlayer[2], curPlayer[3], curPlayer[4],curPlayer[5]};
                    PlayerCumPass pass = new PlayerCumPass(passStats);
                    pass.post();

                    int[] rushStats = {curPlayer[0], curPlayer[6], curPlayer[7], curPlayer[8],curPlayer[9]};
                    PlayerCumRush rush = new PlayerCumRush(rushStats);
                    rush.post();

                    int[] recStats = {curPlayer[0], curPlayer[10], curPlayer[11], curPlayer[12], curPlayer[13]};
                    PlayerCumRec rec = new PlayerCumRec(recStats);
                    rec.post();

                    int[] kickStats = {curPlayer[0], curPlayer[14], curPlayer[15], curPlayer[16], curPlayer[17]};
                    PlayerCumKick kick = new PlayerCumKick(kickStats);
                    kick.post();

                    int[] miscStats = {curPlayer[0], curPlayer[18], curPlayer[19], curPlayer[20], curPlayer[21], curPlayer[22]};
                    PlayerCumMisc misc = new PlayerCumMisc(miscStats);
                    misc.post();
                }
            }
        } catch (DLException e) {
            e.printStackTrace();
            throw new DLException(e, "could not load players game data");
        }
    }
    /**Retrieves Defensive game statistics of a specified NFL team and loads them into database
     * @param team A String containing an NFL team 2 or 3 letter abbreviation (ex. 'NYG' is 'New York Giants')
     */
    public void loadDefStats(String team) throws DLException {
        try {
            ArrayList<int[]> defStats = msf.getDefStatsByTeam(team);

            for (int[] stats : defStats) {

                int[] dStats = {stats[0],stats[1],stats[2],stats[3],stats[4],stats[5],stats[8],stats[9]};
                DefenseGameStats dgs = new DefenseGameStats(team,dStats);
                dgs.post();

                int[] dStatsMisc = {stats[0],stats[6],stats[7],stats[10],stats[11]};
                MiscDefenseGameStats mdgs = new MiscDefenseGameStats(team,dStatsMisc);
                mdgs.post();

            }
        } catch (DLException e) {
            e.printStackTrace();
            throw new DLException(e, "could not load team data");
        }
    }
    public void loadDefCumStats(String team) throws DLException {
        try {

            int[] stats = msf.getCumDefStatsByTeam(team);

            int[] dStats = {stats[0],stats[1],stats[2],stats[3],stats[4],stats[7],stats[8]};
            DefenseCumStats dcs = new DefenseCumStats(team,dStats);
            dcs.post();

            int[] dStatsMisc = {stats[5],stats[6],stats[9],stats[10]};
            MiscDefenseCumStats mdcs = new MiscDefenseCumStats(team,dStatsMisc);
            mdcs.post();


        }
        catch (DLException e) {
            e.printStackTrace();
            throw new DLException(e, "could not load team data");
        }
    }
    public void loadAllGames()throws Exception {
        try {
            db.startTrans();
            System.out.println("Loading Game Data...This May Take A While...");
            double loading = 0.0;
            double time = 320000;

            for(String team: teams)
            {
                double perc = loading/32*100;
                double minutes = time/100000;
                loadGamesByTeam(team);
                loading++;
                System.out.println("Loading...."+perc+"%");
                System.out.println("Time Remaining: "+minutes+" minutes");
                Thread.sleep(10000);
                time -= 10000;
            }
            db.endTrans();
            System.out.println("Loading...100%");
            System.out.println("Game Data Loaded Successfully!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void loadAllPlayerStats()throws Exception {

        try
        {  db.startTrans();
            System.out.println("Loading Player Game Stats...This May Take A While...");
            double loading = 0.0;
            double time = 2400;
            for(String team: teams)
            {   System.out.println("Now Loading.... "+team+" players");
                for(String pos : position)
                {
                    double perc = loading / 160 * 100;
                    double minutes = time / 60;
                    loadPlayerStats(team,pos);
                    System.out.println(team+" "+pos+" have been successfully loaded");
                    loading++;
                    System.out.println("Loading...." + perc + "%");
                    System.out.println("Time Remaining: " + minutes + " minutes");
                    Thread.sleep(15000);
                    time -= 15;
                }
            }
            db.endTrans();
            System.out.println("Loading...100%");
            System.out.println("Player Game Stats Loaded Successfully!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void loadAllCumStats()throws Exception {

        try
        {  db.startTrans();
            for(String pos : position)
            {
                System.out.println("Now Loading " + pos);
                loadCumPlayerStats(pos);
                Thread.sleep(10000);
            }
            db.endTrans();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void loadAllDefenseStats()throws Exception{

        try {
            db.startTrans();
            for (String team : teams) {
                System.out.println("Now Loading Defenses stats for team: " + team);
                loadDefStats(team);
                Thread.sleep(10000);
            }
            db.endTrans();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void loadAllDefenseCumStats()throws Exception{

        try {
            db.startTrans();
            for (String team : teams) {
                System.out.println("Now Loading Cumulative Defensive stats for team: " + team);
                loadDefCumStats(team);
                Thread.sleep(10000);
            }
            db.endTrans();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    /**Querys the database to check if a record exists
     * @param query A String containing an SQL query statement
     * @return A boolean indicating if the record exists
     */
    private boolean existsInDB(String query) throws DLException {
        boolean exists;

        try {
            ArrayList<String[]> data = db.getData(query);
            exists = !data.isEmpty();
        } catch (DLException e) {
            e.printStackTrace();
            throw new DLException(e, "could not check if exists");
        }
        return exists;
    }
}
