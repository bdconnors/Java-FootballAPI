package org.rit.footballapi.models;

import org.rit.footballapi.util.DBInterface;
import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class Scoring extends DBInterface {

    public String format;
    public String tdpass;
    public String passyds;
    public String passcomp;
    public String tdrush;
    public String rushyds;
    public String rushatt;
    public String tdrec;
    public String recyds;
    public String rec;
    public String fgmd;
    public String xpmd;
    public String fgmiss;
    public String inter;
    public String fum;

    public Scoring(String format)
    {
        this.format = format;
    }

    public void fetch()throws DLException
    {
        setQuery("getscoring.sql");
        setBindValues(new ArrayList<String>(){{add(format);}});
        super.fetch();
        String[] results = getResults().get(0);
        format = results[0];
        tdpass = results[1];
        passyds = results[2];
        passcomp = results[3];
        tdrush = results[4];
        rushyds = results[5];
        rushatt = results[6];
        tdrec = results[7];
        recyds = results[8];
        rec = results[9];
        fgmd = results[10];
        xpmd = results[11];
        fgmiss = results[12];
        inter = results[13];
        fum = results[14];
    }
}
