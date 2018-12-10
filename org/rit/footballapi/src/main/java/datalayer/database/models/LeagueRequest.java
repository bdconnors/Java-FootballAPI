package main.java.datalayer.database.models;

import main.java.datalayer.database.DBInterface;

public class LeagueRequest extends DBInterface {

    public String requestid;
    public String userid;
    public String leagueid;
    public String userName;
    public String leagueName;

    public LeagueRequest(String requestid)
    {
        this.requestid = requestid;
    }
    public LeagueRequest(String[] requestinfo)
    {
        setInfo(requestinfo);
    }
    public void setInfo(String[] requestinfo)
    {
        setRequestid(requestinfo[0]);setUserid(requestinfo[1]);
        setLeagueid(requestinfo[2]);setUserName(requestinfo[3]);
        setLeagueName(requestinfo[4]);
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(String leagueid) {
        this.leagueid = leagueid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }
}

