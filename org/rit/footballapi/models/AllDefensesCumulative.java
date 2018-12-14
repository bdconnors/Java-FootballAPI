package org.rit.footballapi.models;

import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

public class AllDefensesCumulative extends Team {
    public ArrayList<Defense> allDefenses = new ArrayList<>();

    public AllDefensesCumulative() {

    }
    public void fetch() throws DLException {

        ArrayList<String[]> defenses = new ArrayList<>();
        try {
            setQuery("alldefense.sql");
            super.fetch();
            defenses = getResults();
            Defense defense = null;
            for (String[] def : defenses) {
                defense = new Defense(def);
                allDefenses.add(defense);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public ArrayList<Defense> getAllDefenses() {
        return allDefenses;
    }
    public void setAllDefenses(ArrayList<Defense> allDefenses) {
        this.allDefenses = allDefenses;
    }
}


