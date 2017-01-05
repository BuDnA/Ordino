package com.cybermyth.matej.ordino.Database;

/**
 * Created by borut on 18.12.2016.
 */

public class DbVprasanje {

    private String vprasanje;
    private String odgovor;

    public DbVprasanje(String vprasanje, String odgovor) {
        this.vprasanje = vprasanje;
        this.odgovor = odgovor;
    }

    public String getVprasanje() {
        return vprasanje;
    }

    public void setVprasanje(String vprasanje) {
        this.vprasanje = vprasanje;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }
}
