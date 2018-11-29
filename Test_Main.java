<<<<<<< HEAD
package ritdatabaseproject;
import ritdatabaseproject.server.models.*;


public class Test_Main
{
   public static void main(String[] args)throws Exception
   {
       FootballDatabase fdb = new FootballDatabase();
       fdb.loadAllCumStats();

   }

=======
import server.models.FootballDatabase;

public class Test_Main
{
   public static void main(String[] args)throws Exception
   {
       FootballDatabase fdb = new FootballDatabase();
        fdb.loadAllDefenseStats();

   }

>>>>>>> d8b5dae7ef8acd4720539f72b9f88f54c8967cb4
}