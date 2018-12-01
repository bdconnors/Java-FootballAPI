import main.java.datalayer.database.LoadDatabase;

public class Temp_Main {

    public static void main(String[] args)throws Exception
    {
        LoadDatabase ldb = new LoadDatabase();
        ldb.loadAllRunningBacks();
    }
}
