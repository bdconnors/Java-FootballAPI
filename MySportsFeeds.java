//----------------------------------------------------------------------------------------
//Dev Name: Brandon Connors
//Filename: MySportsFeeds.Java
//Date: 11/8/18
//Program Description: Contains methods to request data from MySportsFeeds.com API
//---------------------------------------------------------------------------------------
//--------------Import Statements--------------------------------------------------------
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import com.fasterxml.jackson.core.JsonParseException;//JSON Packages
import com.fasterxml.jackson.databind.JsonMappingException;//JSON Packages
import com.fasterxml.jackson.databind.JsonNode;//JSON Packages
import com.fasterxml.jackson.databind.ObjectMapper;//JSON Packages
//----------------------------------------------------------------------------------------
//Class Name: MySportsFeeds
//Description: This class has two methods. apiRequest() and getPlayersByPosition()
//----------------------------------------------------------------------------------------
public class MySportsFeeds{
   //--------------Class Variables---------------------------------------------------------
   //starting half of string for api requests
   private String feedString = "https://api.mysportsfeeds.com/v1.0/pull/nfl/2018-regular/";
   //add to feedString to request rostered players
   private String playerString ="roster_players.json?rosterstatus=assigned-to-roster";
   //add to playerString to request by position
   private String positionString="position=";
   //add to playerString to rquest by player
   private String pNameString="player=";
   private String teamString ="overall_team_standings.json";
   private String teamGameString ="team_gamelogs.json?team=";
   private String playerTeamGameString = "player_gamelogs.json?team=";
   //---------------------------------------------------------------------------------------------
   //Method Name: apiRequest
   //Description:Takes in a request string and uses API key to request data from MySportsFeeds.com
   //and returns an InputStream object which contains the data requested
   //---------------------------------------------------------------------------------------------
   public String apiRequest(String reqString)
   {     
      String jsonString = null;
      try
      {  //API key for MySportsFeeds.com
         String encode = "b4b138b1-909e-4ffa-80ee-baccda:iste330";
         //create URL object from request string
         URL url = new URL (reqString);
         //encode API key
         String encoding = Base64.getEncoder().encodeToString(encode.getBytes());
         //open HTTP connection
         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
         //set request method to GET
         connection.setRequestMethod("GET");
         //set output to true
         connection.setDoOutput(true);
         //send in authorization of encoded API key
         connection.setRequestProperty("Authorization", "Basic " + encoding);
         //load InputStream with requested data
         InputStream content = (InputStream)connection.getInputStream();
         //calls apiRequest sending in the requested string and returns InputStream into BufferedReader
         BufferedReader in = new BufferedReader(new InputStreamReader(content));
         //StringWriter to assemble Json string into single line
         StringWriter stringWriter = new StringWriter();
         //PrintWriter to wrap StringWriter
         PrintWriter writer = new PrintWriter(stringWriter, true);
         //String to read line being read in from buffered reader
         String line;
         //while line is still being read
         while ((line = in.readLine()) != null) 
         {  //write the line to stringWriter
            writer.write(line);
         }
         jsonString = stringWriter.toString();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      //return InputStream containing requested data
      return jsonString; 
   }//end of apiRequest method
   //---------------------------------------------------------------------------------------------
   //Method Name: getPlayersByPosition
   //Description:Takes in a string which contains desired position to request
   //and returns an ArrayList<String[]> of player data
   //---------------------------------------------------------------------------------------------
   public ArrayList<String[]> getPlayersByPosition(String pos)
   {  //ArrayList for holding String[6] of player data
      ArrayList<String[]> playerData = new ArrayList<String[]>();
      String reqString = feedString + playerString +"&"+ positionString + pos;    
      try 
      {  //Json obeject mapper to load Json string into Json array node
         ObjectMapper mapper = new ObjectMapper();
         String jsonString = apiRequest(reqString);
         //load Json string into Json array node
         JsonNode root = mapper.readTree(jsonString);
         //get the player entrys from array node and create player array node
         JsonNode players = root.findPath("playerentry");
         //for each player in the array node
         for(int i =0; i<players.size(); i++)
         {   //get the player assigned to index
            JsonNode curPlayer = players.get(i);
            if(curPlayer.findPath("JerseyNumber").isMissingNode())
            {  
               //if the player does not have a jersey number skip him
            }
            else
            {  //create string array to hold needed player data
               String[] player = new String[6];
               //store player id into first array element
               player[0] = curPlayer.findPath("ID").asText();
               //store player first name into  second array element
               player[1] = curPlayer.findPath("FirstName").asText();
               //store player last name into third array element
               player[2] = curPlayer.findPath("LastName").asText();
               //store player team into fourth array element
               player[3] = curPlayer.findPath("Abbreviation").asText();
               //store player position into fifth array element
               player[4] = curPlayer.findPath("Position").asText();
               //store player jersey number into last array element
               player[5] = curPlayer.findPath("JerseyNumber").asText();
               //add player to ArrayList of players
               playerData.add(player);
            }
         }     
      } 
      catch(Exception e) 
      {
         e.printStackTrace();
      }
      //return ArrayList of player data
      return playerData;
   }//end of getPlayersByPosition method
   public ArrayList<String[]> getAllTeams()
   { //ArrayList for holding String[6] of player data
      ArrayList<String[]> teamData = new ArrayList<String[]>();
      String reqString = feedString + teamString; 
      try 
      {  //Json obeject mapper to load Json string into Json array node
         ObjectMapper mapper = new ObjectMapper();
         String jsonString = apiRequest(reqString);
         //load Json string into Json array node
         JsonNode root = mapper.readTree(jsonString);
         //get the player entrys from array node and create player array node
         JsonNode teams = root.findPath("teamstandingsentry");
         for(int i = 0; i < teams.size(); i++)
         {  JsonNode curTeam = teams.get(i);
            String[] team = new String[2];
            team[0] = curTeam.findPath("City").asText()+" "+curTeam.findPath("Name").asText();
            team[1] = curTeam.findPath("Abbreviation").asText();
            teamData.add(team);
         }      
      } 
      catch(Exception e) 
      {
         e.printStackTrace();
      } 
      return teamData;
   }
   public ArrayList<String[]> getGamesByTeam(String team)
   {  ArrayList<String[]> gameData = new ArrayList<String[]>();
      String reqString = feedString + teamGameString+team;
      try
      {  ObjectMapper mapper = new ObjectMapper();
         String jsonString = apiRequest(reqString);
         JsonNode root = mapper.readTree(jsonString);
         JsonNode games = root.findPath("gamelogs");
         for(int i = 0; i < games.size(); i++)
         {  String[] game = new String[3];
            JsonNode curGame = games.get(i);
            game[0] = curGame.findPath("game").findPath("id").asText();
            game[1] = curGame.findPath("game").findPath("date").asText();
            game[2] = curGame.findPath("game").findPath("time").asText();
            gameData.add(game);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return gameData;
   }
   public ArrayList<int[]> getDefStatsByTeam(String team)
   {  ArrayList<int[]> defStats = new ArrayList<int[]>();
      String reqString = feedString + teamGameString+team;
      try
      {  ObjectMapper mapper = new ObjectMapper();
         String jsonString = apiRequest(reqString);
         JsonNode root = mapper.readTree(jsonString);
         JsonNode games = root.findPath("gamelogs");
         for(int i = 0; i < games.size(); i++)
         { 
            int[] stats= new int[12];
            JsonNode curGame = games.get(i);
            stats[0] = curGame.findPath("id").asInt();
            stats[1] = curGame.findPath("PointsAgainst").findPath("#text").asInt();
            stats[2] = curGame.findPath("Sacks").findPath("#text").asInt();
            stats[3] = curGame.findPath("Safeties").findPath("#text").asInt();
            stats[4] = curGame.findPath("IntTD").findPath("#text").asInt();
            stats[5] = curGame.findPath("FumTD").findPath("#text").asInt();
            stats[6] = curGame.findPath("KrTD").findPath("#text").asInt();
            stats[7] = curGame.findPath("PrTD").findPath("#text").asInt();
            stats[8] = curGame.findPath("Interceptions").findPath("#text").asInt();
            stats[9] = curGame.findPath("Fumbles").findPath("#text").asInt();
            stats[10] = curGame.findPath("KB").findPath("#text").asInt();
            stats[11] = curGame.findPath("XpBlk").findPath("#text").asInt();
            defStats.add(stats);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return defStats;
   }
   public ArrayList<int[]> getStatsByTeamPos(String team,String pos)
   {  ArrayList<int[]> pStats = new ArrayList<int[]>();
      String reqString=feedString+playerTeamGameString+team+"&"+positionString+pos;
      try
      {  ObjectMapper mapper = new ObjectMapper();
         String jsonString = apiRequest(reqString);
         JsonNode root = mapper.readTree(jsonString);
         JsonNode games = root.findPath("gamelogs");
   
         for(int i=0; i<games.size(); i++)
         {  
            JsonNode gamelog = games.get(i);
            int[] stats = new int[22];
            stats[0] = gamelog.findPath("game").findPath("id").asInt();
            stats[1] = gamelog.findPath("player").findPath("ID").asInt();
            stats[2] = gamelog.findPath("stats").findPath("PassAttempts").findPath("#text").asInt();
            stats[3] = gamelog.findPath("stats").findPath("PassCompletions").findPath("#text").asInt();
            stats[4] = gamelog.findPath("stats").findPath("PassYards").findPath("#text").asInt();
            stats[5] = gamelog.findPath("stats").findPath("PassTD").findPath("#text").asInt();
            stats[6] = gamelog.findPath("stats").findPath("TwoPtPassMade").findPath("#text").asInt();
            stats[7] = gamelog.findPath("stats").findPath("RushAttempts").findPath("#text").asInt();
            stats[8] = gamelog.findPath("stats").findPath("RushYards").findPath("#text").asInt();
            stats[9] = gamelog.findPath("stats").findPath("TwoPtRushMade").findPath("#text").asInt();
            stats[10] = gamelog.findPath("stats").findPath("Receptions").findPath("#text").asInt();
            stats[11] = gamelog.findPath("stats").findPath("RecYards").findPath("#text").asInt();
            stats[12] = gamelog.findPath("stats").findPath("RecTD").findPath("#text").asInt();
            stats[13] = gamelog.findPath("stats").findPath("TwoPtPassRec").findPath("#text").asInt();
            stats[14] = gamelog.findPath("stats").findPath("FgAtt").findPath("#text").asInt();
            stats[15] = gamelog.findPath("stats").findPath("FgMade").findPath("#text").asInt();
            stats[16] = gamelog.findPath("stats").findPath("XpAtt").findPath("#text").asInt();
            stats[17] = gamelog.findPath("stats").findPath("XpMade").findPath("#text").asInt();
            stats[18] = gamelog.findPath("stats").findPath("PassInt").findPath("#text").asInt();
            stats[19] = gamelog.findPath("stats").findPath("Fumbles").findPath("#text").asInt();
            stats[20] = gamelog.findPath("stats").findPath("KrTD").findPath("#text").asInt();
            stats[21] = gamelog.findPath("stats").findPath("PrTD").findPath("#text").asInt();
            pStats.add(stats);
         }
         
    
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return pStats;
   }

}//end of class