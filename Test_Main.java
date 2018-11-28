import server.models.FootballDatabase;
import server.models.Player;

public class Test_Main
{
   public static void main(String[] args)throws Exception
   {
       FootballDatabase fdb = new FootballDatabase();
       Player odell = new Player("7746");
       odell.fetch();
       System.out.println(odell);
       String asdfasjkldhfasdhjkfjklhasdfjklasdf;

   }

}