package main.java.datalayer.database;

import com.fasterxml.jackson.annotation.JsonInclude;
import main.java.datalayer.account.User;

public class League extends User {

    public String leagueid;
    public String scoring;
    public String leaguename;
    public String numTeams;

    public League(String leagueid)
    {
        this.leagueid = leagueid;
    }
    public League(String[] leagueInfo)
    {
        setInfo(leagueInfo);
    }
    public League()
    {}
    public void setInfo(String[] leagueInfo)
    {
        setLeagueid(leagueInfo[0]);setScoring(leagueInfo[1]);
        setLeaguename(leagueInfo[2]);setNumTeams(leagueInfo[3]);
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
}
