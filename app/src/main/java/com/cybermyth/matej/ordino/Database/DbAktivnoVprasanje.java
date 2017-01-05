package com.cybermyth.matej.ordino.Database;

/**
 * Created by borut on 4.1.2017.
 */

public class DbAktivnoVprasanje {

    private String aktivno_vprasanje, aktivni_odgovori, aktivni_clani;

    public DbAktivnoVprasanje(String aktivno_vprasanje, String aktivni_odgovori, String aktivni_clani) {
        this.aktivno_vprasanje = aktivno_vprasanje;
        this.aktivni_odgovori = aktivni_odgovori;
        this.aktivni_clani = aktivni_clani;
    }

    public String getAktivno_vprasanje() {
        return aktivno_vprasanje;
    }

    public void setAktivno_vprasanje(String aktivno_vprasanje) {
        this.aktivno_vprasanje = aktivno_vprasanje;
    }

    public String getAktivni_odgovori() {
        return aktivni_odgovori;
    }

    public void setAktivni_odgovori(String aktivni_odgovori) {
        this.aktivni_odgovori = aktivni_odgovori;
    }

    public String getAktivni_clani() {
        return aktivni_clani;
    }

    public void setAktivni_clani(String aktivni_clani) {
        this.aktivni_clani = aktivni_clani;
    }
}
