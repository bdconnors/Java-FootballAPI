import java.util.*;
import java.sql.*;
import java.io.IOException;
import java.util.Iterator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MariaDB_Test
{
   public static void main(String[] args)
   {
       
      try
      {
         FootballDatabase fdb = new FootballDatabase();
         fdb.loadGamesByTeam("ten");
         
         

         
      }
      catch(Exception e)
      {
         e.printStackTrace();
      
      }
   
   
   }






}