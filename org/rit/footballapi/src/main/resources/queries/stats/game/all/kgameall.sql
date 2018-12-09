SELECT game.date,game.hometeam,game.awayteam,jerseynumber,pos,CONCAT(player.firstname,' ',player.lastname)AS PlayerName,team,fgAtt,fgmd,xpatt,xpmd
FROM player
INNER JOIN playergamekick ON player.playerid = playergamekick.playerid
INNER JOIN game ON playergamekick.gameid = game.gameid
WHERE player.firstname = ? AND player.lastname = ?
GROUP BY game.date;