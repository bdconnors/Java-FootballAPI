package main.java.datalayer.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

/** Object used to perform operations on MySQL football statistics database.
 * @author Brandon Connors
 * @author bdc5435@rit.edu
 * @version 2.0
 * @since 1.0
 */
public class FootballDatabase {

   private String uri;
   private String user;
   private String driver;
   private String password;
   private static Connection conn = null;
   /** Creates a FootballDatabase with predefined information
    */
   public FootballDatabase() {
      uri = "jdbc:mysql://localhost/iste330t03?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
      driver = "com.mysql.cj.jdbc.Driver";
      user = "iste330t03";
      password = "reallysudden";
   }
   /** Creates a FootballDatabase database with user specified information
    * @param uri The connection string used to connect to database
    * @param driver The driver used to connect to database
    * @param user The user name used to connect to database
    * @param password The password used to connect to database
    */
   public FootballDatabase(String uri, String driver, String user, String password) {
      this.uri = uri;
      this.user = user;
      this.driver = driver;
      this.password = password;
   
   }
   /** Creates a connection to database
    * @return A boolean representing a successful or unsuccessful connection
    */
   private boolean connect() throws DLException {
      boolean status = false;
      try {
         if (conn == null || conn.getAutoCommit()) {
            conn = DriverManager.getConnection(uri, user, password);
            status = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, "Could Not Connect to Server", "uri: " + uri, "User: " + user, "Driver: " + driver);
      }
      return status;
   }
   /** Closes the connection to database
    * @return A boolean representing successfull or unsuccessful close
    */
   private boolean close() throws DLException {
      boolean status = false;
      try {
         if (conn != null && conn.getAutoCommit()) {
            conn.close();
            status = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, "Could Not Close Connection", "uri: " + uri, "User: " + user, "Driver: " + driver);
      }
      return status;
   }
   /**Retrieves data from database using a PreparedStatement
    * @param query A String containing an SQL query statement
    * @param list An ArrayList of String containing query values
    * @return An ArrayList of String[] containing data requested
    */
   public ArrayList<String[]> getData(String query, ArrayList<String> list) throws DLException {
      ArrayList<String[]> data = new ArrayList<>();
      connect();
      try {
         PreparedStatement stmnt = prepare(query, list);
         ResultSet rs = stmnt.executeQuery();
         ResultSetMetaData rsmd = rs.getMetaData();
         int colCount = rsmd.getColumnCount();
         while (rs.next()) {
            String[] valueRow = new String[colCount];
            for (int i = 1; i <= colCount; i++) {
               valueRow[i - 1] = rs.getString(i);
            }
            data.add(valueRow);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      close();
      return data;
   }
   /**Retrieves data from database
    * @param query A String containing an SQL query statement
    * @return An ArrayList of String[] containing data requested
    */
   public ArrayList<String[]> getData(String query) throws DLException {
      connect();
      ArrayList<String[]> data = new ArrayList<>();
      int numFields = 0;
      try {
         Statement stmnt = conn.createStatement();
         ResultSet rs = stmnt.executeQuery(query);
         while (rs.next()) {
            ResultSetMetaData rsmd = rs.getMetaData();
            numFields = rsmd.getColumnCount();
            String[] row = new String[numFields];
            for (int i = 1; i <= numFields; i++) {
               row[i - 1] = rs.getString(i);
            }
            data.add(row);
         }
         close();
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, "Could Not Retrieve Data", "query: " + query, "numFields: " + String.valueOf(numFields));
      }
      return data;
   }
   /**Sets data in database
    * @param update A String containing an SQL update statement
    * @param list An ArrayList of String containing update values
    * @return An int representing the number of rows effected
    */
   public int setData(String update, ArrayList<String> list) throws DLException {
      connect();
      int effected;
      try {
         effected = executeStmnt(update, list);
      } catch (Exception e) {
         throw new DLException(e, "Could Not Set Data", "Query: " + update);
      }
      close();
   
      return effected;
   }
   /**Sets data in database
    * @param update A String containing an SQL update statement
    * @return An int representing the number of rows effected
    */
   public int setData(String update) throws DLException {
      connect();
      int effected;
      try {
         Statement stmnt = conn.createStatement();
         effected = stmnt.executeUpdate(update);
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, "Could Not Set Data", "Query: " + update);
      }
      return effected;
   }
   /**Creates a PreparedStatement from String and an ArrayList of values
    * @param query A String containing an SQL statement
    * @return A PreparedStatement object
    */
   private PreparedStatement prepare(String query, ArrayList<String> list) throws DLException {
      try {
         PreparedStatement stmnt = conn.prepareStatement(query);
         if(list != null) {
             for (int i = 0; i < list.size(); i++) {
                 String value = list.get(i);
                 stmnt.setString(i + 1, value);
             }
         }
         return stmnt;
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, query, "Could Not Prepare Statement");
      }
   
   }
   /**Prepares a statement from an SQL update String and an ArrayList of values and executes it
    * @param update A String containing an SQL statement
    * @return An int representing the number of rows effected
    */
   private int executeStmnt(String update, ArrayList<String> list) throws DLException {
      PreparedStatement stmnt;
      int effected;
      try {
         connect();
         stmnt = prepare(update, list);
         effected = stmnt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, update, "Could Not Execute Statement");
      }
      return effected;
   }
   /**Prints to console a table containing requested data
    * @param query A String containing an SQL query statement
    */
   public void descTable(String query) throws DLException {
      try {
         Statement stmnt = conn.createStatement();
         ResultSet rs = stmnt.executeQuery(query);
         ArrayList<String[]> data = getData(query);
         TablePrinter print = new TablePrinter(rs, data);
         print.printData();
         print.printTypes();
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, "Could Not print meta data");
      }
   }
   /**Begins a transaction, setting auto commit to false
    */
   public void startTrans() throws DLException {
      try {
         connect();
         conn.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, "Could not start transaction");
      }
   }
   /**Ends a transaction, setting auto commit to true
    */
   public void endTrans() throws DLException {
      try {
         conn.commit();
         conn.setAutoCommit(true);
         close();
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, "Could not end transaction");
      }
   }
   /**Rolls a transaction back
    */
   public void rollbackTrans() throws DLException {
      try {
         conn.rollback();
         conn.setAutoCommit(true);
      } catch (Exception e) {
         e.printStackTrace();
         throw new DLException(e, "Could not rollback transaction");
      }
   }
}   
   
