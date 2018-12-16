select leaguerequest.requestid,leaguerequest.userid,leaguerequest.leagueid,users.userName,league.leaguename,leaguerequest.teamname
 from leaguerequest
 inner join users on leaguerequest.userid = users.userid
 inner join league on leaguerequest.leagueid = league.leagueid
 WHERE leaguerequest.leagueid = ?;