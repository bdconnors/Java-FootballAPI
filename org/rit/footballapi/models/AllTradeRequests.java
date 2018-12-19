package org.rit.footballapi.models;

import org.rit.footballapi.util.DBInterface;
import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class AllTradeRequests extends DBInterface {

    public ArrayList<TradeRequest> alltraderequests = new ArrayList<>();

    public AllTradeRequests()
    {

    }
    public void fetch()throws DLException
    {
        setQuery("getalltrades.sql");
        super.fetch();
        loadTrades(getResults());

    }
    public void loadTrades(ArrayList<String[]> trades)
    {
        TradeRequest tradeRequest = null;
        for(String[] trade :trades)
        {
            tradeRequest = new TradeRequest(trade);
            alltraderequests.add(tradeRequest);
        }
    }


}
