SELECT game.date,game.hometeam,game.awayteam,player.jerseynumber,player.pos,CONCAT(player.firstname,' ',player.lastname)AS PlayerName,player.team,rushatt,rushyds,rushtd,rec,recyds,rectd,fum
FROM player
INNER JOIN playergamerush ON player.playerid = playergamerush.playerid
INNER JOIN playergamerec ON playergamerush.playerid = playergamerec.playerid AND playergamerush.gameid = playergamerec.gameid
INNER JOIN playergamemisc ON playergamerec.playerid = playergamemisc.playerid AND playergamerec.gameid = playergamemisc.gameid
INNER JOIN game ON playergamerush.gameid = game.gameid
WHERE player.firstname = ? AND player.lastname = ? AND game.awayteam = ? AND game.hometeam = ?;