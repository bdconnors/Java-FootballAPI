package ritdatabaseproject.server.models;

import java.sql.*;
import java.util.ArrayList;
/** Object used to perform operations on MySQL football statistics database.
 * @author Brandon Connors
 * @author bdc5435@rit.edu
 * @version 2.0
 * @since 1.0
 */
public class FootballDatabase {

   private String uri;
   private String user;
   private String driver;
   private String password;
   private static Connection conn = null;
   private MySportsFeeds msf = new MySportsFeeds();
   private String[] position;
   private String[] teams;
   /** Creates a FootballDatabase with predefined information
    */
   public FootballDatabase() {
      uri = "jdbc:mysql://localhost/iste330t03?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
      driver = "com.mysql.cj.jdbc.Driver";
      user = "iste330t03";
      password = "reallysudden";
      position = new String[]{"WR", "TE", "RB", "QB", "K"};
      teams = new String[]{"NYG","WAS","DAL","PHI","NO","ATL","CAR","TB","LA","SEA","SF","ARI","GB","DET","CHI",
             "MIN","BUF","NE","MIA","NYJ","OAK","DEN","KC","LAC","PIT","CIN","CLE","BAL","JAX","TEN","IND","HOU"};
   }
   /** Creates a FootballDatabase database with user specified information
    * @param uri The connection string used to connect to database
    * @param driver The driver used to connect to database
    * @param user The user name used to connect to database
    * @param password The password used to connect to database
    */
   public FootballDatabase(String uri, String driver, String user, String password) {
      this.uri = uri;
      this.user = user;
      this.driver = driver;
      this.password = password;
      position = new String[]{"WR", "TE", "RB", "QB", "K"};
      teams = new String[]{"NYG","WAS","DAL","PHI","NO","ATL","CAR","TB","LA","SEA","SF","ARI","GB","DET","CHI",
             "MIN","BUF","NE","MIA","NYJ","OAK","DEN","KC","LAC","PIT","CIN","CLE","BAL","JAX","TEN","IND","HOU"};
   
   }
   /** Creates a connection to database
    * @throws Throws Data Layer Exception if connection unsuccessful
    * @return A boolean representing a successful or unsuccessful connection
    */
   private boolean connect() throws DLException {
      boolean status = false;
      try {
         if (conn == null || conn.getAutoCommit()) {
            conn = DriverManager.getConnection(uri, user, password);
            status = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, "Could Not Connect to Server", "uri: " + uri, "User: " + user, "Driver: " + driver);
      }
      return status;
   }
   /** Closes the connection to database
    * @throws Throws Data Layer Exception if close is unsuccesful
    * @return A boolean representing successfull or unsuccessful close
    */
   private boolean close() throws DLException {
      boolean status = false;
      try {
         if (conn != null && conn.getAutoCommit()) {
            conn.close();
            status = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, "Could Not Close Connection", "uri: " + uri, "User: " + user, "Driver: " + driver);
      }
      return status;
   }
   /**Retrieves data from database using a PreparedStatement
    * @throws Throws Data Layer Exception if data cannot be retrieved
    * @param query A String containing an SQL query statement
    * @param list An ArrayList of String containing query values
    * @return An ArrayList of String[] containing data requested
    */
   ArrayList<String[]> getData(String query, ArrayList<String> list) throws DLException {
      ArrayList<String[]> data = new ArrayList<>();
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
   /**Retrieves data from database
    * @throws Throws Data Layer Exception if data cannot be retrieved
    * @param query A String containing an SQL query statement
    * @return An ArrayList of String[] containing data requested
    */
   private ArrayList<String[]> getData(String query) throws DLException {
      connect();
      ArrayList<String[]> data = new ArrayList<>();
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
         e.printStackTrace();
         throw new DLException(e, "Could Not Retrieve Data", "query: " + query, "numFields: " + String.valueOf(numFields));
      }
      return data;
   }
   /**Sets data in database
    * @throws Throws Data Layer Exception if data cannot be set
    * @param update A String containing an SQL update statement
    * @param list An ArrayList of String containing update values
    * @return An int representing the number of rows effected
    */
   int setData(String update, ArrayList<String> list) throws DLException {
      connect();
      int effected;
      try {
         effected = executeStmnt(update, list);
      } catch (Exception e) {
         throw new DLException(e, "Could Not Set Data", "Query: " + update);
      }
      close();
   
      return effected;
   }
   /**Sets data in database
    * @throws Throws Data Layer Exception if data cannot be set
    * @param update A String containing an SQL update statement
    * @return An int representing the number of rows effected
    */
   public int setData(String update) throws DLException {
      connect();
      int effected;
      try {
         Statement stmnt = conn.createStatement();
         effected = stmnt.executeUpdate(update);
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, "Could Not Set Data", "Query: " + update);
      }
      return effected;
   }
   /**Creates a PreparedStatement from String and an ArrayList of values
    * @throws Throws Data Layer Exception if statement cannot be created
    * @param query A String containing an SQL statement
    * @return A PreparedStatement object
    */
   private PreparedStatement prepare(String query, ArrayList<String> list) throws DLException {
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
   /**Prepares a statement from an SQL update String and an ArrayList of values and executes it
    * @throws Throws Data Layer Exception if statement cannot be executed
    * @param update A String containing an SQL statement
    * @return An int representing the number of rows effected
    */
   private int executeStmnt(String update, ArrayList<String> list) throws DLException {
      PreparedStatement stmnt;
      int effected;
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
   /**Prints to console a table containing requested data
    * @throws Throws Data Layer Exception if statement cannot be created
    * @param query A String containing an SQL query statement
    */
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
   /**Retrieves data on players of the specified position from an API and loads it into database
    * @throws Throws Data Layer Exception if player data could not be loaded
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
    * @throws Throws Data Layer Exception if team data could not be loaded
    */
   public void loadAllTeams() throws DLException {
      try {
         startTrans();
         String[] curTeam;
         ArrayList<String[]> teams = msf.getAllTeams();
         for (String[] team1 : teams) {
            curTeam = team1;
            Team team = new Team(curTeam);
            team.post();
         }
         endTrans();
      } catch (DLException e) {
         e.printStackTrace();
         throw new DLException(e, "cannot load teams");
      }
   }
   /**Retrieves data on all games played by a specified NFL team and loads them into database
    * @throws Throws Data Layer Exception if game data could not be loaded
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
    * @throws Throws Data Layer Exception if player statistics could not be loaded
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
               
                  int[] rushStats = new int[]{curPlayer[0], curPlayer[1], curPlayer[7], curPlayer[8], curPlayer[9]};
                  PlayerGameRush rush = new PlayerGameRush(rushStats);
                  rush.post();
               
                  int[] recStats = new int[]{curPlayer[0], curPlayer[1], curPlayer[10], curPlayer[11], curPlayer[12], curPlayer[13]};
                  PlayerGameRec rec = new PlayerGameRec(recStats);
                  rec.post();
               
                  int[] kickStats = new int[]{curPlayer[0], curPlayer[1], curPlayer[14], curPlayer[15], curPlayer[16], curPlayer[17]};
                  PlayerGameKick kick = new PlayerGameKick(kickStats);
                  kick.post();
               
                  int[] miscStats = new int[]{curPlayer[0], curPlayer[1], curPlayer[18], curPlayer[19], curPlayer[20], curPlayer[21]};
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
            
               int[] rushStats = {curPlayer[0], curPlayer[6], curPlayer[7], curPlayer[8]};
               PlayerCumRush rush = new PlayerCumRush(rushStats);
               rush.post();
            
               int[] recStats = {curPlayer[0], curPlayer[9], curPlayer[10], curPlayer[11], curPlayer[12]};
               PlayerCumRec rec = new PlayerCumRec(recStats);
               rec.post();
            
               int[] kickStats = {curPlayer[0], curPlayer[13], curPlayer[14], curPlayer[15], curPlayer[16]};
               PlayerCumKick kick = new PlayerCumKick(kickStats);
               kick.post();
            
               int[] miscStats = {curPlayer[0], curPlayer[17], curPlayer[18], curPlayer[19], curPlayer[20], curPlayer[21]};
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
    * @throws Throws Data Layer Exception if Defensive statistics could not be loaded
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
   public void loadAllPlayers()throws Exception {
   
      try {
         startTrans();
         System.out.println("Loading Active Player Data...This May Take A While...");
         double loading = 0.0;
         double time = 50000;
      
         for(String pos : position)
         {
            double perc = loading / 5 * 100;
            double minutes = (time / 1000) / 60;
            loadPlayersByPos(pos);
            loading++;
            System.out.println("Loading...." + perc + "%");
            System.out.println("Time Remaining: " + minutes + " minutes");
            Thread.sleep(15000);
            time -= 15000;
         }
         endTrans();
         System.out.println("Loading...100%");
         System.out.println("Active Player Data Loaded Successfully!");
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
   public void loadAllGames()throws Exception {
      try {
         startTrans();
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
         endTrans();
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
      {  startTrans();
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
         endTrans();
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
      {  startTrans();
         for(String pos : position)
         {
            System.out.println("Now Loading " + pos);
            loadCumPlayerStats(pos);
            Thread.sleep(10000);
         }
         endTrans();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
   public void loadAllDefenseStats()throws Exception{
   
      try {
         startTrans();
         for (String team : teams) {
            System.out.println("Now Loading Defenses stats for team: " + team);
            loadDefStats(team);
            Thread.sleep(10000);
         }
         endTrans();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
   public void loadAllDefenseCumStats()throws Exception{
   
      try {
         startTrans();
         for (String team : teams) {
            System.out.println("Now Loading Cumulative Defensive stats for team: " + team);
            loadDefCumStats(team);
            Thread.sleep(10000);
         }
         endTrans();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
   /**Querys the database to check if a record exists
    * @throws Throws Data Layer Exception if check cannot be excuted
    * @param query A String containing an SQL query statement
    * @return A boolean indicating if the record exists
    */
   private boolean existsInDB(String query) throws DLException {
      boolean exists;
   
      try {
         ArrayList<String[]> data = getData(query);
         exists = !data.isEmpty();
      } catch (DLException e) {
         e.printStackTrace();
         throw new DLException(e, "could not check if exists");
      }
      return exists;
   }
   /**Begins a transaction, setting auto commit to false
    * @throws Throws Data Layer Exception if transaction cannot be started
    */
   private void startTrans() throws DLException {
      try {
         connect();
         conn.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, "Could not start transaction");
      }
   }
   /**Ends a transaction, setting auto commit to true
    * @throws Throws Data Layer Exception if transaction cannot end
    */
   private void endTrans() throws DLException {
      try {
         conn.commit();
         conn.setAutoCommit(true);
         close();
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, "Could not end transaction");
      }
   }
   /**Rolls a transaction back
    * @throws Throws Data Layer Exception if transaction cannot be rolled back
    */
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