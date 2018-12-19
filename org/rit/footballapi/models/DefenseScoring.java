package org.rit.footballapi.models;

import org.rit.footballapi.util.DBInterface;
import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class DefenseScoring extends DBInterface {

    public String format;
    public String pa;
    public String sck;
    public String sfty;
    public String fum;
    public String inttd;
    public String fumtd;
    public String krtd;
    public String prtd;
    public String kblk;
    public String xpblk;

    public DefenseScoring(String format)
    {
        this.format = format;
    }
    public void fetch()throws DLException
    {
        setQuery("getDefenseScoring.sql");
        setBindValues(new ArrayList<String>(){{add(format);}});
        super.fetch();
        String[] results = getResults().get(0);
        format =results[0];
        pa =results[1];
        sck =results[2];
        sfty =results[3];
        fum =results[4];
        inttd =results[5];
        fumtd =results[6];
        krtd =results[7];
        prtd =results[8];
        kblk =results[9];
        xpblk =results[10];
    }
}
