package main.java.datalayer.database;

public class League extends DBInterface {

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
    public String getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(String leagueid) {
        this.leagueid = leagueid;
    }

    public String getScoring() {
        return scoring;
    }

    public void setScoring(String scoring) {
        this.scoring = scoring;
    }

    public String getLeaguename() {
        return leaguename;
    }

    public void setLeaguename(String leaguename) {
        this.leaguename = leaguename;
    }

    public String getNumTeams() {
        return numTeams;
    }

    public void setNumTeams(String numTeams) {
        this.numTeams = numTeams;
    }
}
