SELECT Pass_Points.format,tdpass,Pass_Points.yds,Pass_Points.comp,tdrush,Rush_Points.yds,att,tdrec,Rec_Points.yds,rec,fgmd,xpmd,fgmiss,`int`,Pass_Points.fum
  FROM Pass_Points
  INNER JOIN Rush_Points ON Pass_Points.format = Rush_Points.format
  INNER JOIN Rec_Points ON Rush_Points.format = Rec_Points.format
  INNER JOIN Kick_Points ON Rec_Points.format = Kick_Points.format
  WHERE Pass_Points.format = ?;