//----------------------------------------------------------------------------------------------
//Dev Name: Brandon Connors
//Filename: FootballDatabase.Java
//Date: 11/8/18
//Program Description: Contains methods to interact with a MariaDB/mySQL 
//server containing fantasy football data
//----------------------------------------------------------------------------------------------
//--------------Import Statements--------------------------------------------------------
import java.util.*;
import java.sql.*;
import java.io.IOException;
import java.util.Iterator;
import com.fasterxml.jackson.core.JsonParseException;//JSON packages
import com.fasterxml.jackson.databind.JsonMappingException;//JSON packages
import com.fasterxml.jackson.databind.JsonNode;//JSON packages
import com.fasterxml.jackson.databind.ObjectMapper;//JSON packages
import com.fasterxml.jackson.annotation.*;//JSON packages
import java.sql.*;
import java.util.*;
//----------------------------------------------------------------------------------------
//Class Name: FootballDatabase
//Description: This class 13 methods. 
//connect(),close()
//getData(String,ArrayList<String>),getData(String)
//setData(String,ArrayList<String>),setData(String)
//prepare(String,ArrayList<String>)
//descTable(String)
//executeStmnt(String,ArrayList<String>)
//loadPlayersByPos(ArrayList<String>)
//deletePlayersByPos(ArrayList<String>)
//startTrans()
//endTrans()
//rollbackTrans()
//----------------------------------------------------------------------------------------
public class FootballDatabase
{
   //--------------Class Variables---------------------------------------------------------   
   private String uri;           
   private String user;
   private String driver;
   private String password;
   private Connection conn = null;
   //-------------------------------------------------------------------------------------
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: none
   //Description: create server object with predefined server info
   //------------------------------------------------------------------------------------
   public FootballDatabase()
   {
      uri = "jdbc:mariadb://localhost:3305/football";
      driver = "org.mariadb.jdbc:mariadb-java-client:2.3.0";
      user = "root";
      password = "student";
      
   }
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: 4 Strings
   //Description:create server with user supplied server information
   //-----------------------------------------------------------------------------------
   public FootballDatabase(String uri,String driver,String user,String password)
   {
      
      this.uri = uri;
      this.user = user;
      this.driver = driver;
      this.password = password;
      
   }
   //---------------------------------------------------------------------------------------------
   //Method Name: connect
   //Description: connect to server if not connected using supplied class variables as information
   //---------------------------------------------------------------------------------------------
   public boolean connect()throws DLException
   {
      boolean status = true;//status of server
       
      try
      {
         if(conn == null || conn.getAutoCommit())
         {  conn = DriverManager.getConnection(uri,user,password);
            //users supplied information to attempt connection
            status = true;
         }
      
      
      }
      catch(Exception e)
      {
         
         status = false;
         e.printStackTrace();
         //throws new DLException with error message and relevant information
         throw new DLException(e,"Could Not Connect to Server","uri: "+uri,"User: "+user,"Driver: "+driver);
         
      }
      //return success or fail
      return status;
   }//end connect
   
   //---------------------------------------------------------------------------------------------
   //Method Name: close
   //Description: closes connection to server if one exists
   //--------------------------------------------------------------------------------------------
   public boolean close()throws DLException
   {
      boolean status = true;
      
      try{
      
         if(conn != null && conn.getAutoCommit())
         {
         //atempt to close connection
            conn.close();
            status = true;
         }
      
      }
      catch(Exception e){
         
         status = false;
         e.printStackTrace();
         //throws new DLException with error message and relevant information
         throw new DLException(e,"Could Not Close Connection","uri: "+uri,"User: "+user,"Driver: "+driver);
      }
      
      return status;
      
   }//end close
   
   //----------------------------------------------------------------------------------------------------
   //Method Name: getData
   //Description:send in query and arraylist of values to get data from database using prepared statement
   //----------------------------------------------------------------------------------------------------
   public ArrayList<String[]> getData(String query, ArrayList<String> list)throws DLException
   {  
      //arraylist to hold data returned from database
      ArrayList<String[]> data = new ArrayList<String[]>();
      //connect to database
      connect();
      try
      {
         //calls prepare method to create prepared statement
         PreparedStatement stmnt = prepare(query,list); 
         //calls execute method to return data from database into resultset
         ResultSet rs = stmnt.executeQuery();
         //puts the metadata from the resultset into ResultSetMetaData object
         ResultSetMetaData rsmd = rs.getMetaData();
         //gets the number of columns in the result set
         int colCount = rsmd.getColumnCount();
         //creates String[] row with a size equal to the number of columns
         String[] colNameRow = new String[colCount];
         
         for(int i = 0; i < colCount; i++)
         {  
            //loads the first row with the column names
            colNameRow[i] = rsmd.getColumnName(i+1);
         }
         //adds the columns names as the first row in the arraylist containing returned data
         data.add(colNameRow);
         //whiles the resultset still contains data
         while(rs.next())
         {  
            //create String[] row with a size equal to the number of columns
            String[] valueRow = new String[colCount];
            //for every column 
            for(int i=1; i<= colCount; i++)
            {  //get the value in that column
               valueRow[i-1]=rs.getString(i);
            }
            //add the row to the arraylist containing returned data
            data.add(valueRow);
         }
         
         
      }
      catch(Exception e)
      {
         e.printStackTrace();
         
      }
      //close connection
      close();
      //return arraylist containing returned data
      return data;
      
   }//end getData
   
   //---------------------------------------------------------------------------------------------
   //Method Name: getData
   //Description:gets data from database with query string, no prepared statement
   //---------------------------------------------------------------------------------------------
   public ArrayList<String[]> getData(String query)throws DLException
   {  
      connect();
      ArrayList<String[]> data = new ArrayList<String[]>();
      int numFields =0;
      
      try
      {  //creates statement to send
         Statement stmnt= conn.createStatement();
         //loads statement with query
         ResultSet rs = stmnt.executeQuery(query);
         
         //iterates through result set inserting results into 2d arraylist
         while(rs.next())
         {  
            ResultSetMetaData rsmd = rs.getMetaData();
            numFields = rsmd.getColumnCount();
            String[] row = new String[numFields];
          
            
            for(int i=1; i<=numFields; i++)
            {
               row[i-1]=rs.getString(i);
            
            }
            data.add(row);
         }
         close();
      }
      catch(Exception e)
      {
         //throw new DLException sending error message and relevant information
         throw new DLException(e,"Could Not Retrieve Data","query: "+query,"numFields: "+ String.valueOf(numFields));
         
      }
      
      return data;
   }//end getData
   
   //---------------------------------------------------------------------------------------------------
   //Method Name: setData
   //Description:takes in ArrayList and string to set data in the database with update, delete or insert 
   //and returns effected number of rows as an int, uses prepared statement
   //---------------------------------------------------------------------------------------------------
   public int setData(String update,ArrayList<String> list) throws DLException
   {  //connect to db
      connect();
      //effected rows
      int effected = 0;
      
      try
      {  
         //execute update 
         effected = executeStmnt(update,list);
      }
      catch(Exception e)
      {
         effected = -1;
         throw new DLException(e,"Could Not Set Data","Query: "+update);
         
      }
      //close connection
      close();
      //return rows effected
      return effected;
   }//end setData
   
   //---------------------------------------------------------------------------------------------------
   //Method Name: setData
   //Description:takes in string to set data in the database with update, delete or insert 
   //and returns effected rows, no prepared statement
   //---------------------------------------------------------------------------------------------------
   public int setData(String update) throws DLException
   {
      connect();
      int effected = 0;
      
      try
      {
         Statement stmnt= conn.createStatement();
         effected = stmnt.executeUpdate(update);
      }
      catch(Exception e)
      {  
         e.printStackTrace();
         effected = -1;
         throw new DLException(e,"Could Not Set Data","Query: "+update);
         
      }
   
      return effected;
   }//end setData
   
   //---------------------------------------------------------------------------------------------------
   //Method Name: prepare
   //Description: takes in a sql string and list of values to return a prepared statement
   //---------------------------------------------------------------------------------------------------
   public PreparedStatement prepare(String query,ArrayList<String> list)throws DLException
   {  
   
      try
      {  
         //creates prepared stmnt with sql string
         PreparedStatement stmnt = conn.prepareStatement(query);
         
         //for every value in the list bind the value to the prepared statement
         for(int i = 0; i < list.size(); i++)
         {
            
            String value = list.get(i);
            stmnt.setString(i+1,value);
         }
         //return prepared statement
         return stmnt;
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new DLException(e,query,"Could Not Prepare Statement");
         
      }
      
   }//end prepare
   
   //---------------------------------------------------------------------------------------------------
   //Method Name: executeStmnt
   //Description: takes in an sql string and list of values to execute an update,delete or insert 
   //statement returning effected number of rows as an int
   //---------------------------------------------------------------------------------------------------
   public int executeStmnt(String update,ArrayList<String> list)throws DLException
   {  
      //prepared statement 
      PreparedStatement stmnt = null;
      //effected number of rows to be returned
      int effected =  0;
      try
      { 
         //connect to db
         connect();
         //call prepare method and return prepared statement
         stmnt = prepare(update,list);
         //execute prepared statement
         effected = stmnt.executeUpdate();
         
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new DLException(e,update,"Could Not Execute Statement");
      }
   
      //return rows effected
      return effected;
   }//end executeStmnt
   
   //---------------------------------------------------------------------------------------------------
   //Method Name: descTable
   //Description: takes in sql query and prints out to console a table of the data requested by query
   //---------------------------------------------------------------------------------------------------
   public void descTable(String query) throws DLException
   {   
      
      try
      {  //create statement
         Statement stmnt = conn.createStatement();
         //return query data in result set
         ResultSet rs = stmnt.executeQuery(query);
         //gets data from query
         ArrayList<String[]> data = getData(query);
         //create table printer object sending in resultset and the data
         TablePrinter print = new TablePrinter(rs,data);
         //print the table
         print.printData();
         //print types from the table
         print.printTypes();
         
         
      }
      catch(Exception e)
      {
         e.printStackTrace();
         //throw new DLException sending error message and relevant information
         throw new DLException(e,"Could Not print meta data");
         
      }
   
   }//end descTable
   
    //---------------------------------------------------------------------------------------------------
   //Method Name: loadPlayersByPos
   //Description: takes in a string of a players position and loads all players 
   //with that position into the database
   //---------------------------------------------------------------------------------------------------
   public void loadPlayersByPos(String pos)throws DLException
   {
      
      try
      {  
         //start transaction
         startTrans();
         //create object to request data from API
         MySportsFeeds feed = new MySportsFeeds();
         //gets player data from API feed and loads into ArrayList<String[]>
         ArrayList<String[]> players = feed.getPlayersByPosition(pos);
         //player object to hold information
         Player curPlayer = new Player();
         //for every player in the ArrayList
         for(int i = 0; i<players.size(); i++)
         {
            //get current player at index i
            String[] player = players.get(i);
            //set the player objects id to current players ID
            curPlayer.setID(player[0]);
            //set the player objects id to current players FirstName
            curPlayer.setFName(player[1]);
            //set the player objects id to current players LastName
            curPlayer.setLName(player[2]);
            //set the player objects id to current players Team
            curPlayer.setTeam(player[3]);
            //set the player objects id to current players Position
            curPlayer.setPosition(player[4]);
            //set the player objects id to current players JerseyNumber
            curPlayer.setJNumber(player[5]);
            //add player data to database when transaction ends
            curPlayer.post();
         }
         //end transaction
         endTrans();
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new DLException(e,"could not load player data");
      
      }
   }//end loadPlayersByPos
    //---------------------------------------------------------------------------------------------------
   //Method Name: deletePlayersByPos
   //Description:takes in a string of a players position and deletes all players 
   //with that position from the database
   //---------------------------------------------------------------------------------------------------
   public void deletePlayersByPos(String pos)throws DLException
   {  
      try
      {  //start transaction
         startTrans();
         //create object to request data from API
         MySportsFeeds feed = new MySportsFeeds();
         //gets player data from API feed and loads into ArrayList<String[]>
         ArrayList<String[]> players = feed.getPlayersByPosition(pos);
         //player object to hold information
         Player curPlayer = new Player();
         //for every player in the arraylist
         for(int i = 0; i<players.size(); i++)
         {  //get current player at index i
            String[] player = players.get(i);
            //set the player objects id to current players ID
            curPlayer.setID(player[0]);
            //set the player objects id to current players FirstName
            curPlayer.setFName(player[1]);
            //set the player objects id to current players LastName
            curPlayer.setLName(player[2]);
            //set the player objects id to current players Team
            curPlayer.setTeam(player[3]);
            //set the player objects id to current players Position
            curPlayer.setPosition(player[4]);
            //set the player objects id to current players JerseyNumber
            curPlayer.setJNumber(player[5]);
            //deletes current player from database at the end of transaciton
            curPlayer.delete();
         }
         //end transaction
         endTrans();
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new DLException(e,"could not load player data");
      
      }
   }//end deletePlayersByPos
   //---------------------------------------------------------------------------------------------------
   //Method Name: deleteAllPlayers
   //Description:takes in a string of a players position and deletes all players 
   //with that position from the database
   //---------------------------------------------------------------------------------------------------
   public int deleteAllPlayers()throws DLException
   {  
      int effected = 0;
      try
      {  //start transaction
         startTrans();
         effected = setData("DELETE FROM PLAYER;");
         endTrans();
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new DLException(e,"could not load player data");
      
      }
      return effected;
   }//end deleteAllPlayers
   public void loadAllTeams()throws DLException
   {
      try
      {
         startTrans();
         MySportsFeeds feed = new MySportsFeeds();
         ArrayList<String[]> teams = feed.getAllTeams();
         String[] curTeam = new String[2];
         Team team = new Team();
         
         for(int i = 0; i < teams.size(); i++)
         {
           curTeam = teams.get(i);
            team.setName(curTeam[0]);
            team.setAbrv(curTeam[1]);

            team.post(); 
         
         }
      
         endTrans();
      }
      catch(DLException e)
      {
         e.printStackTrace();
         throw new DLException(e,"could not load team data");
      }
   }
   public void loadGamesByTeam(String team)throws DLException
   {
      try
      {
         startTrans();
         MySportsFeeds feed = new MySportsFeeds();
         String[] curGame = new String[3];
         String gameCheck = null;
         Game game = new Game();
         ArrayList<String[]> games = feed.getGamesByTeam(team);
         for(int i = 0; i < games.size(); i++)
         {
            curGame = games.get(i);
            gameCheck = "select * from game where gameid = '" +curGame[0]+"';";
            if(existsInDB(gameCheck) == true)
            {
            
            }
            else
            {
               game.setGameID(curGame[0]);
               game.setDate(curGame[1]);
               game.setTime(curGame[2]);
               game.post(); 
            }
         }

         endTrans();
      }
      catch(DLException e)
      {
         e.printStackTrace();
         throw new DLException(e,"could not load team data");
      }
   }
   public boolean existsInDB(String query)throws DLException
   {
      boolean exists = false;
      
     try
     {
     
       ArrayList<String[]> data = getData(query);
      
      if(data.isEmpty())
      {
         exists = false;
      }
      else
      {
         exists = true;
      }
      }
      catch(DLException e)
      {
         e.printStackTrace();
         throw new DLException(e,"could not check if exists"); 
      }
      
      return exists;
   }
   //---------------------------------------------------------------------------------------------------
   //Method Name: startTrans
   //Description:Begins a transaction
   //---------------------------------------------------------------------------------------------------
   public void startTrans()throws DLException
   {
      try
      {  connect();
         conn.setAutoCommit(false);
      }
      catch(Exception e)
      {  
         e.printStackTrace();
         throw new DLException (e,"Could not start transaction");
      }
   }//end startrans
    //---------------------------------------------------------------------------------------------------
   //Method Name: startTrans
   //Description:Ends a transaction
   //---------------------------------------------------------------------------------------------------
   public void endTrans()throws DLException
   {
      try
      {
         conn.commit();
         conn.setAutoCommit(true);
         close();
         
      }
      catch(Exception e)
      {  
         e.printStackTrace();
         throw new DLException (e,"Could not end transaction");
      }
   }// end endtrans
    //---------------------------------------------------------------------------------------------------
   //Method Name: startTrans
   //Description:Rolls a transaction back
   //---------------------------------------------------------------------------------------------------
   public void rollbackTrans()throws DLException
   {
      try
      {
         conn.rollback();
         conn.setAutoCommit(true);
         
      }
      catch(Exception e)
      {  
         e.printStackTrace();
         throw new DLException (e,"Could not rollback transaction");
      }
      
   }//end rollbacktrans
   
}// end footballdatabase classs