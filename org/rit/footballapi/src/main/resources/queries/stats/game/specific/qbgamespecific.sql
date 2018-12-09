SELECT game.date,game.hometeam,game.awayteam,jerseynumber,pos,CONCAT(player.firstname,' ',player.lastname)AS PlayerName,team,passatt,passcomp,passyds,passtds,intthr,rushatt,rushyds,rushtd,fum
FROM player
INNER JOIN playergamepass ON player.playerid = playergamepass.playerid
INNER JOIN playergamerush ON playergamepass.playerid = playergamerush.playerid AND playergamepass.gameid = playergamerush.gameid
INNER JOIN playergamemisc ON player.playerid = playergamemisc.playerid AND playergamepass.gameid = playergamemisc.gameid
INNER JOIN game ON playergamepass.gameid = game.gameid
WHERE player.firstname = ? AND player.lastname = ? AND game.awayteam = ? AND game.hometeam = ?;