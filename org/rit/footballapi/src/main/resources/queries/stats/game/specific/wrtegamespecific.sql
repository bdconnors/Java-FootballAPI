SELECT game.date,game.hometeam,game.awayteam,player.jerseynumber,player.pos,player.firstname,player.lastname,player.team,rec,recyds,rectd,rushatt,rushyds,rushtd,fum
 FROM player
 INNER JOIN playergamerec ON player.playerid = playergamerec.playerid
 INNER JOIN playergamerush ON playergamerec.playerid = playergamerush.playerid AND playergamerec.gameid = playergamerush.gameid
 INNER JOIN playergamemisc ON player.playerid = playergamemisc.playerid AND playergamerec.gameid = playergamemisc.gameid
 INNER JOIN game ON game.gameid = playergamerec.gameid
 WHERE player.playerid = ? AND game.gameid = ?;