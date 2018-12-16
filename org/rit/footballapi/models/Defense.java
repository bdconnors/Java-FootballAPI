package org.rit.footballapi.models;

import org.rit.footballapi.util.DLException;

import java.util.ArrayList;

    public class Defense extends Team {

        public String pa;
        public String sck;
        public String intc;
        public String fum;
        public String sfty;
        public String inttd;
        public String fumtd;
        public String krtd;
        public String prtd;
        public String kblk;
        public String xpblk;

        public Defense(String team) {
            setAbrv(team);
            query = "defensecumulative.sql";
            bindValues = new ArrayList<String>() {{
                add(getAbrv());
            }};
        }

        public Defense(String team, String gameid) {
            setAbrv(team);
            setGameID(gameid);
            query = "defensegamespecific.sql";
            bindValues = new ArrayList<String>() {{
                add(getAbrv());
                add(getGameID());
            }};
        }
        public Defense(String[] stats)
        {
            setStats(stats);
        }
        public void fetch()throws DLException
        {
            try {
                setQuery(query);
                setBindValues(bindValues);
                super.fetch();
                setStats(super.getResults().get(0));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        public void setStats(String[] stats){

            if (getGameID() == null) {
               setTeam(stats[0]);setPa(stats[1]);setSck(stats[2]);setIntc(stats[3]);setFum(stats[4]);setSfty(stats[5]);setInttd(stats[6]);
               setFumtd(stats[7]);setKrtd(stats[8]);setPrtd(stats[9]);setKblk(stats[10]);setXpblk(stats[11]);

            } else {

                setDate(stats[0]);setHomeTeam(stats[1]);setAwayTeam(stats[2]);setPa(stats[3]);setSck(stats[4]);
                setIntc(stats[5]);setFum(stats[6]);setSfty(stats[7]);setInttd(stats[8]);setFumtd(stats[9]);
                setKrtd(stats[10]);setPrtd(stats[11]);setKblk(stats[12]);setXpblk(stats[13]);

            }
        }

        public String getPa() {
            return pa;
        }

        public void setPa(String pa) {
            this.pa = pa;
        }

        public String getSck() {
            return sck;
        }

        public void setSck(String sck) {
            this.sck = sck;
        }

        public String getSfty() {
            return sfty;
        }

        public void setSfty(String sfty) {
            this.sfty = sfty;
        }

        public String getInttd() {
            return inttd;
        }

        public void setInttd(String inttd) {
            this.inttd = inttd;
        }

        public String getFumtd() {
            return fumtd;
        }

        public void setFumtd(String fumtd) {
            this.fumtd = fumtd;
        }

        public String getIntc() {
            return intc;
        }

        public void setIntc(String intc) {
            this.intc = intc;
        }

        public String getFum() {
            return fum;
        }

        public void setFum(String fum) {
            this.fum = fum;
        }

        public String getKrtd() {
            return krtd;
        }

        public void setKrtd(String krtd) {
            this.krtd = krtd;
        }

        public String getPrtd() {
            return prtd;
        }

        public void setPrtd(String prtd) {
            this.prtd = prtd;
        }

        public String getKblk() {
            return kblk;
        }

        public void setKblk(String kblk) {
            this.kblk = kblk;
        }

        public String getXpblk() {
            return xpblk;
        }

        public void setXpblk(String xpblk) {
            this.xpblk = xpblk;
        }
    }
