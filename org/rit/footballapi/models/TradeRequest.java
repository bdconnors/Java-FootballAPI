package org.rit.footballapi.models;

import org.rit.footballapi.util.DBInterface;
import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class TradeRequest extends DBInterface {


    public String tradeid;
    public String teamid;
    public String partnerid;
    public String totrade;
    public String toreceive;

    public TradeRequest(String[] info)
    {
        setInfo(info);
    }
    public TradeRequest(String tradeid)
    {
        this.tradeid = tradeid;
    }
    public void fetch()throws DLException
    {
        setQuery("gettraderequest.sql");
        setBindValues(new ArrayList<String>(){{add(tradeid);}});
        super.fetch();
        setInfo(getResults().get(0));
    }
    public void setInfo(String[] info)
    {
        tradeid = info[0];
        teamid = info[1];
        partnerid = info[2];
        totrade = info[3];
        toreceive=info[4];
    }

    public String getTradeid() {
        return tradeid;
    }

    public void setTradeid(String tradeid) {
        this.tradeid = tradeid;
    }

    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getTotrade() {
        return totrade;
    }

    public void setTotrade(String totrade) {
        this.totrade = totrade;
    }

    public String getToreceive() {
        return toreceive;
    }

    public void setToreceive(String toreceive) {
        this.toreceive = toreceive;
    }
}

