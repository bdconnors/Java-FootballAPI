SELECT leagueid,userid,teamname,qb,rb1,rb2,wr1,wr2,te,flex,def,k
FROM roster
WHERE rosterid = ?;