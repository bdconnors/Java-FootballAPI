SELECT jerseynumber,pos,firstname,lastname,team,rushatt,rushyds,rushtd,rec,recyds,rectd,fum FROM player
RIGHT JOIN playergamerush ON player.playerid = playergamerush.playerid
RIGHT JOIN playergamerec ON player.playerid = playergamerec.playerid
RIGHT JOIN playergamemisc ON player.playerid = playergamemisc.playerid
WHERE player.playerid = ? AND playergamerush.gameid = ?
GROUP BY playergamerush.gameid;