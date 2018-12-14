SELECT game.date,game.hometeam,game.awayteam,pa,sck,intc,fum,sfty,inttd,fumtd,krtd,prtd,kblk,xpblk
 FROM defensegamestats
 INNER JOIN miscdefensegamestats ON defensegamestats.team = miscdefensegamestats.team AND defensegamestats.gameid = miscdefensegamestats.gameid
 INNER JOIN game ON defensegamestats.gameid = game.gameid
 WHERE defensegamestats.team = ? AND defensegamestats.gameid = ?;