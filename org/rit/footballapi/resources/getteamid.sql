SELECT teamid,league.leagueid,teamname,league.leaguename
 FROM userteam
 INNER JOIN league ON userteam.leagueid = league.leagueid
 WHERE userid = ?;