//----------------------------------------------------------------------------------------------
//Dev Name: Sam Evans
//Filename: User.Java
//Date: 11/9/18
//Program Description: User object to hold data for a user of this fantasy football application
//----------------------------------------------------------------------------------------------
import java.util.Scanner;
import java.util.*;
//----------------------------------------------------------------------------------------
//Class Name: User
//Description:  
/*
   The user class allows for the interaction between the user and the database
*/
//----------------------------------------------------------------------------------------
public class User{
    //--------------Class Variables---------------------------------------------------------
   public String email;
   public String password;
   public String username;
   public String teamName;
   public String ownerName;
   public String leagueName;
   public int totalPoints;
   public int weekPoints;
   public int currStanding;
   public boolean leagueManager = false;
   public boolean admin = false;
   //--------------------------------------------------------------------------------------
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: String[]
   //Description:takes in a string[] of user data and sets basic user variables
   //------------------------------------------------------------------------------------
    public User(String[] user)
   {
      email = user[0];
      password = user[1];
      username = user[2];
   }// end user constructor
   
   
   // get methods
   public String getEmail(){
      return email;
   }
   public String getPassword(){
      return password;
   }
   public String getTeamName(){
      return teamName;
   }
   public String getOwnerName(){
      return ownerName;
   }
   public String getLeagueName(){
      return leagueName;
   }
   public int getTotalPoints(){
      return totalPoints;
   }
   public int getWeekPoints(){
      return weekPoints;
   }
   public int getCurrStanding(){
      return currStanding;
   }
   public boolean getLeagueManager(){
      return leagueManager;
   }
   public boolean getAdmin(){
      return admin;
   }
   // set methods
   public void setTeamName(String fTeamName){
      this.teamName = fTeamName;
   }
   public void setOwnerName(String fOwnerName){
      this.ownerName = fOwnerName;
   }
   public void setLeagueName(String fLeagueName){
      this.leagueName = fLeagueName;  
   }
   public void setLeagueManager(boolean lm){
      this.leagueManager = lm;
   }
   public void setAdmin(boolean ad){
      this.admin = ad;
   }
   public void setTotalPoints(int tPoints){
      this.totalPoints = tPoints;
   }  
   public void setWeekPoints(int wPoints){
      this.weekPoints = wPoints;
   }
   public void setCurrStanding(int standing){
      this.currStanding = standing;
   }
   //---------------------------------------------------------------------------------------------
   //Method Name: requestTrade
   //Description: Prompts user for team names, offering players, and opposing players. creates new
   // object in requestTrade table 
   //---------------------------------------------------------------------------------------------
   public void requestTrade() throws DLException{
      Scanner reader = new Scanner(System.in);
      System.out.println("What is the team you would like to trade with?");
      String tradeTeam = reader.next();
      System.out.println("What are the names of the players you would like from their team? seperate by commas.");
      String opposingPlayers = reader.next();
      System.out.println("What are the names of the players from your team you would like to trade? seperate by commas.");
      String myPlayers = reader.next();
      
      String sqlQuery = "INSERT INTO pendingTrades offeringTeam, respondingTeam, offeringPlayers, respondingPlayers VALUES ?,?,?,?;";
      ArrayList<String> vals = new ArrayList<String>();
      vals.add(this.teamName);
      vals.add(tradeTeam);
      vals.add(myPlayers);
      vals.add(opposingPlayers);
      
      FootballDatabase fb = new FootballDatabase();
      fb.setData(sqlQuery,vals);
      
   }
   //---------------------------------------------------------------------------------------------
   //Method Name: requestName
   //Description: User is prompted to input new name and requestName field is updated in database
   // to reflect requested name change
   //---------------------------------------------------------------------------------------------
   public void requestName() throws DLException{
      System.out.println("What is the new name you would like?");
      Scanner reader = new Scanner(System.in);
      String response = reader.next();
      reader.close(); 
      
      String sqlQuery = "INSERT INTO requestName oldName, newName Values ?,?";
      ArrayList<String> values = new ArrayList<String>();
      values.add(this.teamName);
      values.add(response);
      
      FootballDatabase fb = new FootballDatabase();
      fb.setData(sqlQuery,values);
   }
   //---------------------------------------------------------------------------------------------
   //Method Name: requestJoinLeague
   //Description: User is prompted to request the name of the league they would like to join
   //an object in the requestLeague table is then created with the requested league and team name 
   //---------------------------------------------------------------------------------------------
   public void requestJoinLeague() throws DLException{
      System.out.println("What is the Name of the league you would like to join?");
      Scanner reader = new Scanner(System.in);
      String response = reader.next();
      reader.close();  
      
      String sqlQuery = "INSERT INTO requestLeague teamName,leagueName VALUES ?,?;";
      ArrayList<String> vals = new ArrayList<String>();
      vals.add(this.teamName);
      vals.add(response);
      FootballDatabase fb = new FootballDatabase();
      fb.setData(sqlQuery,vals);
   }
   //---------------------------------------------------------------------------------------------
   //Method Name: respondTrade
   //Description: User is given the option to accept or deny a trade and depending on answer the 
   // trade object is either updated to be true or the object is deleted from the database
   //---------------------------------------------------------------------------------------------
   public void respondTrade(String[] theirPlayers, String[] myPlayers, String theirTeam, String tradeID) throws DLException{
      System.out.println("Requested trade from " + theirTeam);
      System.out.println(theirTeam + " is offering: ");
      for(String player : theirPlayers){
         System.out.println(player);
      }
      System.out.println("In exchange for: ");
      for(String player : myPlayers){
         System.out.println(player);
      }
      System.out.println("Will you accept their trade? (y/n)");
      Scanner reader = new Scanner(System.in);
      String response = reader.next();
      reader.close();
      
      FootballDatabase fb = new FootballDatabase();
      String sqlQuery = new String();
      ArrayList<String> responseArray = new ArrayList<String>();
      if(response == "y"){
         sqlQuery = "UPDATE pendingTrade SET approval = ?, WHERE tradeID = ?;";
         responseArray.add("true");
         responseArray.add(tradeID);    
      }
      else{
         sqlQuery = "DELETE FROM pendingTrade WHERE tradeID = ?;";
         responseArray.add(tradeID);
      }
      fb.setData(sqlQuery,responseArray);
   }// end respondTrade
   
   
}// end class