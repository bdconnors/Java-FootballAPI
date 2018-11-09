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
//Class Name: Team
//Description: This class has 16 methods. 
//post(),put(),delete() and toString()
//6 set mutators for its class variables
//6 get mutators for its class variables
//----------------------------------------------------------------------------------------
public class DefenseStats
{
   //--------------Class Variables---------------------------------------------------------
   public String statsID;
   public String gameID;
   public String team;
   public String pa;
   public String sck;
   public String sfty;
   public String dTD;
   public String rTD;
   public String intc;
   public String fum;
   public String blk;
   public FootballDatabase db = new FootballDatabase();
   //--------------------------------------------------------------------------------------
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: JsonNode
   //Description:takes in a json array node of player and sets team variables
   //------------------------------------------------------------------------------------
   public DefenseStats(JsonNode node)
   {  


  
   }
    //--------------------Constructor------------------------------------------------------
    //Parameter Type: String[]
    //Description:takes in a string[] of player data and sets team variables
   //------------------------------------------------------------------------------------
   public DefenseStats(String[] team)
   {
   
     
   }
   
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: 2 Strings
   //Description:takes in individual strings of team data and sets team variables
   //------------------------------------------------------------------------------------
   public DefenseStats()
   {
      
  
   
   
   }
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: String
   //Description:takes in a team name and sets name variable
   //------------------------------------------------------------------------------------
   public DefenseStats(String statsID)
   {
      this.statsID = statsID;    
   
   }
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: none
   //Description:default constructor
   //------------------------------------------------------------------------------------

   //-----------------------------------------------------------------------------------------------------
   //Method Name: fetch
   //Description: Issues a query to the database returning player data associated with 
   //this team obejects set name and sets the remaining class variables equal to the returned data
   //----------------------------------------------------------------------------------------------------
   /*public void fetch()throws DLException
   {  
      //SQL query string
      String query = "SELECT name,abbreviation FROM team WHERE abbreviation=?;";
      
      ArrayList<String> values = new ArrayList<String>();
      values.add(abrv);

      try
      { 
         //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
         ArrayList<String[]> info = db.getData(query,values); 
         String[] fields = info.get(1);   
         //set name to the first field value
         name = fields[0];
         //set abrv to the second field value
         abrv = fields[1];

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
      String insert = "INSERT INTO team(name,abbreviation)VALUES(?,?);";
      //bind values
      values.add(name);
      values.add(abrv);

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
      String update = "UPDATE team SET name = ? WHERE abbreviation = ?;";
      ArrayList<String> values = new ArrayList<String>();
 
      values.add(name);
      values.add(abrv);

      
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
   //Description:deletes team record from database using their name
   //---------------------------------------------------------------------------------------------
   public int delete()throws DLException
   {
   
    //effected records
      int effected = 0;
    //SQL delete string
      String delete = "DELETE FROM team WHERE abbreviation =?;";
      ArrayList<String> values = new ArrayList<String>();
      values.add(abrv);
      
    
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
      return "Team Name: " + getName() + "\n" + "Abbreviation: " + getAbrv() +"\n";
   
   }
   
   //getters
   public String getName()
   {
      return name;
   }
   public String getAbrv()
   {
      return abrv;
   
   }

   //setters
   public void setName(String name)
   {
      this.name = name;
   
   }
   public void setAbrv(String abrv)
   {
      this.abrv = abrv;
   
   }*/


}//end player class