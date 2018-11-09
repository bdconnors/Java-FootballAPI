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
<<<<<<< HEAD
   private String playerString ="roster_players.json?rosterstatus=assigned-to-roster&";
   //add to playerString to request by position
   private String positionString="position=";
   //add to playerString to rquest by player
   private String pNameString="player=";
=======
   private String playerString ="roster_players.json?rosterstatus=assigned-to-roster";
   //add to playerString to request by position
   private String positionString="&position=";
   //add to playerString to rquest by player
   private String pNameString="&player=";
   private String teamString ="overall_team_standings.json";
   private String teamGameString ="team_gamelogs.json?team=";
>>>>>>> b85317045c508c10f7ad7673087d02e3c3128f89
   //---------------------------------------------------------------------------------------------
   //Method Name: apiRequest
   //Description:Takes in a request string and uses API key to request data from MySportsFeeds.com
   //and returns an InputStream object which contains the data requested
   //---------------------------------------------------------------------------------------------
<<<<<<< HEAD
   public InputStream apiRequest(String reqString)
   {  //InputSteam to be returned
      InputStream content = null;
=======
   public String apiRequest(String reqString)
   {  
      
      String jsonString = null;
>>>>>>> b85317045c508c10f7ad7673087d02e3c3128f89
      try
      {
         //API key for MySportsFeeds.com
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
<<<<<<< HEAD
         content = (InputStream)connection.getInputStream();
=======
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

>>>>>>> b85317045c508c10f7ad7673087d02e3c3128f89
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      //return InputStream containing requested data
<<<<<<< HEAD
      return content;
=======
      return jsonString;
>>>>>>> b85317045c508c10f7ad7673087d02e3c3128f89
      
   }//end of apiRequest method
   
   //---------------------------------------------------------------------------------------------
   //Method Name: getPlayersByPosition
   //Description:Takes in a string which contains desired position to request
   //and returns an ArrayList<String[]> of player data
   //---------------------------------------------------------------------------------------------
   public ArrayList<String[]> getPlayersByPosition(String pos)
   {
      //ArrayList for holding String[6] of player data
      ArrayList<String[]> playerData = new ArrayList<String[]>();
<<<<<<< HEAD
       
      try 
      {  
         //concatonates desired request into single string
         String reqString = feedString+playerString+positionString+pos;
         //calls apiRequest sending in the requested string and returns InputStream into BufferedReader
         BufferedReader in = new BufferedReader(new InputStreamReader(apiRequest(reqString)));
         //StringWriter to assemble Json string into single line
         StringWriter stringWriter = new StringWriter();
         //PrintWriter to wrap StringWriter
         PrintWriter writer = new PrintWriter(stringWriter, true);
         //Json obeject mapper to load Json string into Json array node
         ObjectMapper mapper = new ObjectMapper();
         //String to read line being read in from buffered reader
         String line;
         //while line is still being read
         while ((line = in.readLine()) != null) 
         {  //write the line to stringWriter
            writer.write(line);
         }
         //load Json string into Json array node
         JsonNode jsonString = mapper.readTree(stringWriter.toString());
         //get the player entrys from array node and create player array node
         JsonNode node = jsonString.findPath("playerentry");
         //for each player in the array node
         for(int i =0; i<node.size(); i++)
         {   //get the player assigned to index
            JsonNode curPlayer = node.get(i);
=======
      String reqString = feedString + playerString + positionString + pos;
       
      try 
      { 
         //Json obeject mapper to load Json string into Json array node
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
>>>>>>> b85317045c508c10f7ad7673087d02e3c3128f89
            //if the player does not have a jersey number skip him
            if(curPlayer.findPath("JerseyNumber").isMissingNode())
            {  
   
            }
            else
            {
<<<<<<< HEAD
              //create string array to hold needed player data
=======
               //create string array to hold needed player data
>>>>>>> b85317045c508c10f7ad7673087d02e3c3128f89
               String[] player = new String[6];
               //store player id into first array element
               player[0] = curPlayer.findPath("ID").asText();
               //store player first name into  second array element
               player[1] = curPlayer.findPath("FirstName").asText();
               //store player last name into third array element
               player[2] = curPlayer.findPath("LastName").asText();
               //store player team into fourth array element
<<<<<<< HEAD
               player[3] = curPlayer.findPath("City").asText()+" "+curPlayer.findPath("Name").asText();
=======
               player[3] = curPlayer.findPath("Abbreviation").asText();
>>>>>>> b85317045c508c10f7ad7673087d02e3c3128f89
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

<<<<<<< HEAD
=======
 public ArrayList<String[]> getAllTeams()
   {
      //ArrayList for holding String[6] of player data
      ArrayList<String[]> teamData = new ArrayList<String[]>();
      String reqString = feedString + teamString;
       
      try 
      { 
         //Json obeject mapper to load Json string into Json array node
         ObjectMapper mapper = new ObjectMapper();
         String jsonString = apiRequest(reqString);
         //load Json string into Json array node
         JsonNode root = mapper.readTree(jsonString);
         //get the player entrys from array node and create player array node
         JsonNode teams = root.findPath("teamstandingsentry");
         for(int i = 0; i < teams.size(); i++)
         {
            JsonNode curTeam = teams.get(i);
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
      //return ArrayList of player data
      return teamData;
   
   }//end of getPlayersByPosition method
   public ArrayList<String[]> getGamesByTeam(String team)
   {
      ArrayList<String[]> gameData = new ArrayList<String[]>();
      String reqString = feedString + teamGameString+team;
      try
      {
         ObjectMapper mapper = new ObjectMapper();
         String jsonString = apiRequest(reqString);
         JsonNode root = mapper.readTree(jsonString);
         JsonNode games = root.findPath("gamelogs");
         
         for(int i = 0; i < games.size(); i++)
         {
            String[] game = new String[3];
             
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


>>>>>>> b85317045c508c10f7ad7673087d02e3c3128f89
}//end of class 