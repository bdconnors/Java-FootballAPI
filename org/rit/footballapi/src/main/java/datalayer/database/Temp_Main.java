package main.java.datalayer.database;

public class Temp_Main {

    public static void main(String[] args)
    {
        LoadDatabase ldb = new LoadDatabase();

        try
        {
            ldb.loadAllPlayerRush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
