SELECT playercummisc.gplayed,jerseynumber,pos,player.firstname,player.lastname,team,passatt,passcomp,passyds,passtds,intthr,rushatt,rushyds,rushtd,fum
 FROM player
 INNER JOIN playercumpass ON player.playerid = playercumpass.playerid
 INNER JOIN playercumrush ON playercumpass.playerid = playercumrush.playerid
 INNER JOIN playercummisc ON player.playerid = playercummisc.playerid
 WHERE pos = "Qb"
 Order BY playercumpass.passyds DESC;