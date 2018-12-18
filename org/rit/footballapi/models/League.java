package org.rit.footballapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.rit.footballapi.util.DBInterface;
import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class League extends DBInterface {

    public String leagueid;
    public String scoring;
    public String leaguename;
    public String numTeams;
    public String mngr;

    public League(String leagueid)
    {
        this.leagueid = leagueid;
    }
    public League(String mngr,String leaguename,String scoring,String numteams)
    {
       this.mngr = mngr;
       this.scoring = scoring;
       this.numTeams = numteams;
       this.leaguename = leaguename;
    }
    public League(String[] leagueInfo)
    {
        setInfo(leagueInfo);
    }
    public League()
    {}
    public int post()throws DLException
    {
        setQuery("createleague.sql");
        setBindValues(new ArrayList<String>(){{add(scoring);add(leaguename);add(numTeams);add(mngr);}});
        return super.post();
    }
    public void setInfo(String[] leagueInfo)
    {
        setLeagueid(leagueInfo[0]);setScoring(leagueInfo[1]);
        setLeaguename(leagueInfo[2]);setNumTeams(leagueInfo[3]);
        setMngr(leagueInfo[4]);
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(String leagueid) {
        this.leagueid = leagueid;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getScoring() {
        return scoring;
    }

    public void setScoring(String scoring) {
        this.scoring = scoring;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getLeaguename() {
        return leaguename;
    }

    public void setLeaguename(String leaguename) {
        this.leaguename = leaguename;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getNumTeams() {
        return numTeams;
    }

    public void setNumTeams(String numTeams) {
        this.numTeams = numTeams;
    }

    public String getMngr() {
        return mngr;
    }

    public void setMngr(String mngr) {
        this.mngr = mngr;
    }
}
