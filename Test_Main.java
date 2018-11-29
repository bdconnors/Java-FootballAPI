package ritdatabaseproject;
import ritdatabaseproject.server.models.*;


public class Test_Main
{
   public static void main(String[] args)throws Exception
   {
       FootballDatabase fdb = new FootballDatabase();
       fdb.loadAllCumStats();

   }
}
