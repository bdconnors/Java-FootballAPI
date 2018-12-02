SELECT game.date,game.hometeam,game.awayteam,player.jerseynumber,player.pos,CONCAT(player.firstname,' ',player.lastname)AS PlayerName,player.team,rushatt,rushyds,rushtd,rec,recyds,rectd,fum
FROM player
RIGHT JOIN playergamerush ON player.playerid = playergamerush.playerid
INNER JOIN playergamerec ON playergamerush.playerid = playergamerec.playerid AND playergamerush.gameid = playergamerec.gameid
RIGHT JOIN playergamemisc ON player.playerid = playergamemisc.playerid AND playergamerush.gameid = playergamemisc.gameid
RIGHT JOIN game ON game.gameid = playergamerush.gameid
WHERE firstname = "Saquon" AND lastname = "Barkley"
GROUP BY game.date;
