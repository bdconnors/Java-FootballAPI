SELECT jerseynumber,pos,firstname,lastname,team,passatt,passcomp,passyds,passtds,intthr,rushatt,rushyds,rushtd,fum FROM player
RIGHT JOIN playergamepass ON player.playerid = playergamepass.playerid
RIGHT JOIN playergamemisc ON player.playerid = playergamemisc.playerid
RIGHT JOIN playergamerush ON player.playerid = playergamerush.playerid
WHERE player.playerid = ? AND playergamepass.gameid = ?
GROUP BY playergamepass.gameid;