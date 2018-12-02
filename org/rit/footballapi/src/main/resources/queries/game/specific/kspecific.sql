SELECT jerseynumber,pos,firstname,lastname,team,fgAtt,fgmd,xpatt,xpmd FROM player
RIGHT JOIN playergamekick ON player.playerid = playergamekick.playerid
WHERE player.playerid = ? AND playergamepass.gameid = ?
GROUP BY playergamekick.gameid;