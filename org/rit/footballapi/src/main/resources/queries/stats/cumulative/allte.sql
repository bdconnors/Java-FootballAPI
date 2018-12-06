SELECT playercummisc.gplayed,player.jerseynumber,player.pos,player.firstname,player.lastname,player.team,playercumrec.rec,playercumrec.recyds,playercumrec.rectd,playercumrush.rushatt,playercumrush.rushyds,playercumrush.rushtd,playercummisc.fum
 FROM player
 INNER JOIN playercumrec ON player.playerid = playercumrec.playerid
 INNER JOIN playercumrush ON playercumrec.playerid = playercumrush.playerid
 INNER JOIN playercummisc ON player.playerid = playercummisc.playerid
 WHERE pos = "te";

