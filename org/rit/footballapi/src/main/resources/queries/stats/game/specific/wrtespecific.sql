SELECT jerseynumber,pos,firstname,lastname,team,rec,recyds,rectd,rushatt,rushyds,rushtd,fum FROM player

RIGHT JOIN playergamerush ON player.playerid = playergamerush.playerid
RIGHT JOIN playergamemisc ON player.playerid = playergamemisc.playerid
WHERE player.playerid = ? AND playergamerec.gameid = ?
GROUP BY playergamerush.gameid;