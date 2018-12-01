import main.java.datalayer.ui.stats.game.RunningBackGameStats;

public class Temp_Main {

    public static void main(String[] args)throws Exception
    {
        RunningBackGameStats rbgs = new RunningBackGameStats();
        rbgs.setPlayerid("8469 ");
        rbgs.setGameid("46032");
        rbgs.fetch();
        System.out.println(rbgs);
    }
}
