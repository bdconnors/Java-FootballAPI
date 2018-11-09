//----------------------------------------------------------------------------------------------
//Dev Name: Brandon Connors
//Filename: Player.Java
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
//Class Name: Player
//Description: This class has 16 methods. 
//post(),put(),delete() and toString()
//6 set mutators for its class variables
//6 get mutators for its class variables
//----------------------------------------------------------------------------------------
public class Team
{
   //--------------Class Variables---------------------------------------------------------
   public String name;
   public String abrv;
   public FootballDatabase db = new FootballDatabase();
   //--------------------------------------------------------------------------------------
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: JsonNode
   //Description:takes in a json array node of player and sets players variables
   //------------------------------------------------------------------------------------
   public Team(JsonNode node)
   {  


      name = node.findPath("City").asText()+" "+node.findPath("Name").asText();
      abrv = node.findPath("Abbreviation").asText();
   
   }
    //--------------------Constructor------------------------------------------------------
    //Parameter Type: String[]
    //Description:takes in a string[] of player data and sets players variables
   //------------------------------------------------------------------------------------
   public Team(String[] team)
   {
      name = team[0];
      abrv = team[1];
     
   }
   
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: 6 Strings
   //Description:takes in individual strings of player data and sets player variables
   //------------------------------------------------------------------------------------
   public Team(String name,String abrv)
   {
      
      this.name = name;
      this.abrv = abrv;
   
   
   }
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: String
   //Description:takes in a player id and sets players id variable
   //------------------------------------------------------------------------------------
   public Team(String name)
   {
      this.name= name;   
   
   }
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: none
   //Description:default constructor
   //------------------------------------------------------------------------------------
   public Team()
   {
   
   }  
   //-----------------------------------------------------------------------------------------------------
   //Method Name: fetch
   //Description: Issues a query to the database returning player data associated with 
   //this player obejects set playerID and sets the remaining class variables equal to the returned data
   //----------------------------------------------------------------------------------------------------
   /*public void fetch()throws DLException
   {  
      //SQL query string
      String query = "SELECT FirstName,LastName,Team,Pos,JerseyNumber FROM player WHERE PlayerID=?;";
      
      ArrayList<String> values = new ArrayList<String>();
      values.add(id);

      try
      { 
         //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
         ArrayList<String[]> info = db.getData(query,values); 
         String[] fields = info.get(1);   
         //set fname to the first field value
         fName = fields[0];
         //set lname to the second field value
         lName = fields[1];
         //set team to the third field value
         team = fields[2];
         //set position to the foruth field value
         position = fields[3];
         //set jnumber to the fifth field value
         jNumber = fields[4];
      }
      catch(Exception e)
      {  
         System.out.println("No Record Found");
        
      }
         
   }

   //---------------------------------------------------------------------------------------------
   //Method Name: post
   //Description: inserts a record into the database using this objects class variables for 
   //the information in the corresponding fields
   //---------------------------------------------------------------------------------------------
   public int post()throws DLException
   {  
     
     //effected records
      int effected = 0;
      ArrayList<String> values = new ArrayList<String>();
     //SQL Insert String
      String insert = "INSERT INTO player(PlayerID,FirstName,LastName,Team,Pos,JerseyNumber)VALUES(?,?,?,?,?,?);";
      //bind values
      values.add(id);
      values.add(fName);
      values.add(lName);
      values.add(team);
      values.add(position);
      values.add(jNumber);
     
      try
      {
       //perform insert and return number of effected
         effected = db.setData(insert,values);
      
      }
      catch(DLException e)
      {
         effected = -1;
         e.printStackTrace();
      
      }
    
      return effected;
   
   }
   //updates database record that corresponds to this object's 'ID' variable and then returns the number of records effected in the database
   public int put()throws DLException
   {  //effected records
      int effected = 0;
      //SQL Update String
      String update = "UPDATE player SET FirstName= ? , LastName = ? , Team = ?, Position = ?,JerseyNumber= ? WHERE PlayerID = ?;";
      ArrayList<String> values = new ArrayList<String>();
 
      values.add(fName);
      values.add(lName);
      values.add(team);
      values.add(position);
      values.add(jNumber);
      values.add(id);
      
      try
      { 
         //perform update and return number of effected records
         effected = db.setData(update,values);
      }
      catch(DLException e)
      {
         effected = -1;
      }
       
      return effected;    
   }

   //---------------------------------------------------------------------------------------------
   //Method Name: delete
   //Description:deletes player record from database using their id
   //---------------------------------------------------------------------------------------------
   public int delete()throws DLException
   {
   
    //effected records
      int effected = 0;
    //SQL delete string
      String delete = "DELETE FROM player WHERE PlayerID=?;";
      ArrayList<String> values = new ArrayList<String>();
      values.add(id);
      
    
      try
      {

      //perform delete and return number of effected records
         effected = db.setData(delete,values);
 
      }
      catch(DLException e)
      {
         effected = -1;
         e.printStackTrace();

      }
     
      return effected;
   }
   
   //toString 
   public String toString()
   {
      return "PlayerID: " + getID()+"\n"+"First Name: " + getFName() +"\n"+"Last Name: "
       +getLName()+"\n" +"Team: " + getTeam()+"\n" +"Position: "
       + getPosition() +"\n"+"Jersey Number: "+ getJNumber();
   
   }
   
   //getters
   public String getID()
   {
      return id;
   }
   public String getFName()
   {
      return fName;
   
   }
   public String getLName()
   {
      return lName;
   
   }
   public String getTeam()
   {
      return team;
   }
   public String getPosition()
   {
      return position;
   
   }
   public String getJNumber()
   {
      return jNumber;
   
   }
   //setters
   public void setID(String id)
   {
      this.id = id;
   
   }
   public void setFName(String fName)
   {
      this.fName = fName;
   
   }
   public void setLName(String lName)
   {
      this.lName = lName;
   
   }
   public void setTeam(String team)
   {
      this.team = team;
   
   }
   public void setPosition(String position)
   {
      this.position = position;
   
   }
   public void setJNumber(String jNumber)
   {
      this.jNumber = jNumber;
   
   }*/

}//end player class