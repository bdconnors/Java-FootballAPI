SELECT game.date,game.hometeam,game.awayteam,player.jerseynumber,player.pos,CONCAT(player.firstname,' ',player.lastname)AS PlayerName,player.team,rushatt,rushyds,rushtd,rec,recyds,rectd,fum
FROM player
INNER JOIN playergamerush ON player.playerid = playergamerush.playerid
INNER JOIN playergamerec ON playergamerush.playerid = playergamerec.playerid AND playergamerush.gameid = playergamerec.gameid
INNER JOIN playergamemisc ON player.playerid = playergamemisc.playerid AND playergamerush.gameid = playergamemisc.gameid
INNER JOIN game ON game.gameid = playergamerush.gameid
WHERE firstname = "saquon" AND lastname = "barkley"
GROUP BY game.date;
