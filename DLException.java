import java.sql.*;
import java.io.*;

public class DLException extends Exception
{
   private static File file = new File("ExceptionLog.txt");//file name
   //Constructor accepting Exception
   public DLException(Exception e)
   {  
   
   }
   //Constructor accepting Exception and varargs
   public DLException(Exception e,String ... values)
   {  
      //sets message with super constructor
      super(values[0]);
      //calls writelog method sending exception and varargs
      writeLog(e,values);
      
   }
   //Writelog method writes stacktrace and relevant information to file
   private void writeLog(Exception e,String...values)
   {
      
      try
      {  //timestamp for time
         Timestamp ts = new Timestamp(System.currentTimeMillis());
         //filewriter with file location
         FileWriter fw = new FileWriter(file,true);
         //printwriter using filewriter
         PrintWriter pw = new PrintWriter(fw,true);
         //write String value of timestamp to file
         pw.write(String.valueOf(ts));
         pw.println();
         pw.println();
         //write all relevant information sent in varargs to file
         for(int i = 1; i < values.length; i++)
         {
            String value = values[i];
            
            pw.write(value);
            pw.println();
         }
         //write stacktrace to file
         pw.println();
         e.printStackTrace(pw);
         pw.println();

      }
      catch(IOException ioe)
      {
         ioe.printStackTrace(); 
      }
   
   
   }



}