//----------------------------------------------------------------------------------------------
//Dev Name: Julian Arya
//Filename: LeagueManager.Java
//Date: 11/8/18
//Program Description: Player Object to hold data on a player for a fantasy football database
//----------------------------------------------------------------------------------------------
//--------------Import Statements---------------------------------------------------------------
import java.util.*;
import java.sql.*;
import java.io.IOException;
import java.util.Iterator;
import com.fasterxml.jackson.core.JsonParseException;//JSON packages
import com.fasterxml.jackson.databind.JsonMappingException;//JSON packages
import com.fasterxml.jackson.databind.JsonNode;//JSON packages
import com.fasterxml.jackson.databind.ObjectMapper;//JSON packages
import com.fasterxml.jackson.annotation.*;//JSON packages
//----------------------------------------------------------------------------------------
//Class Name: LeagueManager
//Description: This class has 16 methods. 
//post(),put(),delete() and toString()
//6 set mutators for its class variables
//6 get mutators for its class variables
//----------------------------------------------------------------------------------------
public class LeagueManager
{
   //--------------Class Variables---------------------------------------------------------
   public ArrayList<Team> teamList;
   public String username;
   public String password;
   public FootballDatabase db = new FootballDatabase();
   
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: none
   //Description:default constructor
   //------------------------------------------------------------------------------------
   public LeagueManager()
   {
      this.username = "";
      this.password = "";
   }     
   
   public LeagueManager(String username, String password)
   {
      this.username = username;
      this.password = password;
   }        //---------------------------------------------------------------------------------------------
   //Method Name: createTeam
   //Description: creates a team with the chosen team name and abbreviation 
   //---------------------------------------------------------------------------------------------
   public void createTeam(String name, String abr)
   {
      Team myTeam = new Team(name, abr);
      
      try{ myTeam.post(); }
      
      catch(Exception e){ e.printStackTrace(); }
   }
   //---------------------------------------------------------------------------------------------
   //Method Name: assignUser
   //Description: assigns user to an existing team
   //---------------------------------------------------------------------------------------------
   public void assignUser(String[] player, String team)throws DLException
   {
      String id = player[0];
      String fName = player[1];
      String lName = player[2];
      String position = player[4];
      String jNumber = player[5];  
      
      String newTeam = team;

      Player myPlayer = new Player(id, fName, lName, newTeam, position, jNumber);
      
      try{ myPlayer.put(); }
      catch(Exception e){ e.printStackTrace(); }
   }
   
   //---------------------------------------------------------------------------------------------
   //Method Name: pendingTrade
   //Description: updates the teams with new players if trade is approved, otherwise
   //remove trade from the database
   //---------------------------------------------------------------------------------------------
   public void pendingTrade(boolean reqStatus, String[] requestedPlayer, String[] exchangedPlayer)throws DLException
   {
//       if(reqStatus == true)
//       {
//          sqlQuery = "UPDATE pendingTrade SET approval = ?, WHERE tradeID = ?;";
//          responseArray.add("true");
//          responseArray.add(tradeID);          
//          
//       }
//       else  
//       {
//          
//       }
   }   //toString 
   
   //getters
   public String getUsername()
   {
      return username;
   }
   public String getPassword()
   {
      return password;
   }
   //setters
   public void setUsername(String username)
   {
      this.username = username;
   }
   public void setPassword(String password)
   {
      this.password = password;
   }
}//end player class