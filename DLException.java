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
public class Defense
{
   //--------------Class Variables---------------------------------------------------------
   
   public String id;
   public String team;
   public FootballDatabase db = new FootballDatabase();
    //--------------------Constructor------------------------------------------------------
    //Parameter Type: String[]
    //Description:takes in a string[] of player data and sets players variables
   //------------------------------------------------------------------------------------
   public Defense(String[] def)
   {
    
   }
   
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: 6 Strings
   //Description:takes in individual strings of player data and sets player variables
   //------------------------------------------------------------------------------------
   public Defense(String id,String team)
   {
      
    
   
   }
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: String
   //Description:takes in a player id and sets players id variable
   //------------------------------------------------------------------------------------
   public Defense(String id)
   {
      this.id= id;   
   
   }
   //--------------------Constructor------------------------------------------------------
   //Parameter Type: none
   //Description:default constructor
   //------------------------------------------------------------------------------------
   public Defense()
   {
   
   }  
   //-----------------------------------------------------------------------------------------------------
   //Method Name: fetch
   //Description: Issues a query to the database returning player data associated with 
   //this player obejects set playerID and sets the remaining class variables equal to the returned data
   //----------------------------------------------------------------------------------------------------
   public void fetch()throws DLException
   {  
      //SQL query string
      String query = "SELECT defenseid FROM defense WHERE team = ?";
      
      ArrayList<String> values = new ArrayList<String>();
      values.add(team);

      try
      { 
         //returns a ArrayList<String[]> filled with info that corresponds to the query statement and number of fields
         ArrayList<String[]> info = db.getData(query,values); 
         String[] fields = info.get(1);   
         //set fname to the first field value
         id = fields[0];
   
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
      String insert = "INSERT INTO defense(team)VALUES(?);";
      //bind values
      values.add(team);

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
      String update = "UPDATE defense SET team = ? WHERE defenseid = ?;";
      ArrayList<String> values = new ArrayList<String>();
 
      values.add(team);
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
      String delete = "DELETE FROM defense WHERE defenseid=?;";
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
      return "DefenseID: "+getID()+"\n"+"Team: "+getTeam()+"\n";
   
   }
   
   //getters
   public String getID()
   {
      return id;
   }
   public String getTeam()
   {
      return team;
   
   }
   
   public void setID(String id)
   {
      this.id = id;
 
   }
   public void setTeam(String team)
   {
      this.team = team;
   
   }

}//end player class