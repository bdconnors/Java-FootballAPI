SELECT teamname,league.leaguename,playerid FROM teamplayers
 INNER JOIN userteam ON teamplayers.teamid = userteam.teamid
 INNER JOIN league ON userteam.leagueid = league.leagueid
 WHERE teamplayers.teamid = ?;