SELECT playercummisc.gplayed,player.jerseynumber,player.pos,player.firstname,player.lastname,player.team,rushatt,rushyds,rushtd,rec,recyds,rectd,fum
 FROM player
 INNER JOIN playercumrush ON player.playerid = playercumrush.playerid
 INNER JOIN playercumrec ON playercumrush.playerid = playercumrec.playerid
 INNER JOIN playercummisc ON playercumrec.playerid = playercummisc.playerid
 WHERE player.playerid = ?;
