package main.java.datalayer.database;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DBInterface extends FootballDatabase {

    public String query = null;

    public ArrayList<String> bindValues = null;

    public ArrayList<String[]> results = null;


    public void fetch()throws DLException
    {
        try
        {
            results = getData(query,bindValues);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    public int post()throws DLException
    {   int effected = 0;
        try
        {
           effected = setData(query,bindValues);
        }
        catch(Exception e)
        {   effected = -1;
            e.printStackTrace();
        }

        return effected;
    }
    public int delete()throws DLException
    {   int effected = 0;
        try
        {
            effected = setData(query,bindValues);
        }
        catch(Exception e)
        {   effected = -1;
            e.printStackTrace();
        }

        return effected;
    }
    public int put()throws DLException
    {   int effected = 0;
        try
        {
            effected = setData(query,bindValues);
        }
        catch(Exception e)
        {   effected = -1;
            e.printStackTrace();
        }

        return effected;
    }
    @JsonIgnore
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) throws DLException {
        StringBuilder sb = new StringBuilder();
        String line;
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("org/rit/footballapi/src/main/resources/queries/" + query));
            while((line = bufferedReader.readLine()) !=null)
            {
                sb.append(line);
            }
        }
        catch(Exception e)
        {

            e.printStackTrace();
        }

        this.query = sb.toString();
    }
    @JsonIgnore
    public ArrayList<String> getBindValues() {
        return bindValues;
    }

    public void setBindValues(ArrayList<String> bindValues) {
        this.bindValues = bindValues;
    }
    @JsonIgnore
    public ArrayList<String[]> getResults() {
        return results;
    }

    public void setResults(ArrayList<String[]> results) {
        this.results = results;
    }

}
