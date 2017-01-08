package com.cybermyth.matej.ordino.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by borut on 18.12.2016.
 */

public class DbHelper extends SQLiteOpenHelper {

    //Ime baze
    private static final String dbName = "Baza.db";

    //Verzija
    private static final int dbVersion = 8;

    //Stolpci za tabelo Vprasanja
    public static final String tabelaVprasanja = "Vprasanja";
    public static final String ID_VPRASANJA = "_id";
    public static final String VPRASANJE = "vprasanje";
    public static final String ODGOVORI = "odgovori";

    //Stolpci za tabelo Uporabniki
    public static final String tabelaUporabniki = "Uporabniki";
    public static final String ID_UPORABNIKA = "_id";
    public static final String UPORABNISKO_IME = "uporabnisko_ime";


    //Stolpci za tabelo Skupine
    public static final String tabelaSkupin = "Skupine";
    public static final String ID_SKUPINE = "_id";
    public static final String CLANI_SKUPINE = "clani_skupine";

    //Stolpci za tabelo MoznoOdgovori
    public static final String tabelaMozniOdgovori = "MozniOdgovori";
    public static final String ID_MOZNI_ODGOVORI = "_id";
    public static final String MOZNI_ODGOVORI = "mozni_odgovori";


    //Stolpci za tabelo AktivnaVprasanja
    public static final String tabelaAktivnaVprasanja = "AktivnaVprasanja";
    public static final String ID_AKTIVNA_VPRASANJA = "_id";
    public static final String AKTIVNO_VPRASANJA = "aktivno_vprasanja";
    public static final String AKTIVNI_ODGOVORI = "aktivni_odgovori";
    public static final String AKTIVNI_CLANI = "aktivni_clani";


    public DbHelper(Context context){
        super(context, dbName, null, dbVersion);
        Log.e("OPERACIJE BAZE", "Baza ustvarjena / odprta...");


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tabelaVprasanja + " (" +
                ID_VPRASANJA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VPRASANJE + " TEXT, " +
                ODGOVORI + " TEXT " +
                ");");
        Log.e("OPERACIJE BAZE", "Tabela 'Vprasanja' ustvarjena...");

        db.execSQL("CREATE TABLE " + tabelaUporabniki + " (" +
                ID_UPORABNIKA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UPORABNISKO_IME + " TEXT " +
                ");");
        Log.e("OPERACIJE BAZE", "Tabela 'Uporabniki' ustvarjena...");

        db.execSQL("CREATE TABLE " + tabelaSkupin + " (" +
                ID_SKUPINE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CLANI_SKUPINE + " TEXT " +
                ");");
        Log.e("OPERACIJE BAZE", "Tabela 'Skupin' ustvarjena...");

        db.execSQL("CREATE TABLE " + tabelaMozniOdgovori + " (" +
                ID_MOZNI_ODGOVORI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MOZNI_ODGOVORI + " TEXT " +
                ");");
        Log.e("OPERACIJE BAZE", "Tabela 'MozniOdgovori' ustvarjena...");

        db.execSQL("CREATE TABLE " + tabelaAktivnaVprasanja + " (" +
                ID_AKTIVNA_VPRASANJA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AKTIVNO_VPRASANJA + " TEXT, " +
                AKTIVNI_ODGOVORI + " TEXT, " +
                AKTIVNI_CLANI + " TEXT " +
                ");");
        Log.e("OPERACIJE BAZE", "Tabela 'AktivnaVprasanja' ustvarjena...");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + tabelaVprasanja);
        Log.e("OPERACIJE BAZE", "Tabela Vprasanja izbrisana");

        db.execSQL("DROP TABLE IF EXISTS " + tabelaUporabniki);
        Log.e("OPERACIJE BAZE", "Tabela Uporabniki izbrisana");

        db.execSQL("DROP TABLE IF EXISTS " + tabelaSkupin);
        Log.e("OPERACIJE BAZE", "Tabela Skupin izbrisana");

        db.execSQL("DROP TABLE IF EXISTS " + tabelaMozniOdgovori);
        Log.e("OPERACIJE BAZE", "Tabela MozniOdgovori izbrisana");

        db.execSQL("DROP TABLE IF EXISTS " + tabelaAktivnaVprasanja);
        Log.e("OPERACIJE BAZE", "Tabela AktivnaVprasanja izbrisana");

        onCreate(db);
    }

    //Vprasanje2
    public void addVprasanja(DbVprasanje vprasanje){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(VPRASANJE, vprasanje.getVprasanje());
        cv.put(ODGOVORI, vprasanje.getOdgovor());

        db.insert(tabelaVprasanja, null, cv);

        db.close();
    }

    public Cursor getVprasanje() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaVprasanja, null);
        c.moveToFirst();
        return c;
    }


    public void addUporabnik(DbUporabnik uporabnik){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(UPORABNISKO_IME, uporabnik.getUser_name());


        db.insert(tabelaUporabniki, null, cv);

        db.close();
    }

    public Cursor getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaUporabniki, null);
        c.moveToFirst();
        return c;
    }

    public void addGroup(DbSkupin skupin){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CLANI_SKUPINE, skupin.getClani());


        db.insert(tabelaSkupin, null, cv);

        db.close();
    }

    public Cursor getGroups() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaSkupin, null);
        c.moveToFirst();
        return c;
    }

    public void addMozniOdgovor(DbMozniOdgovor mozniOdgovor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MOZNI_ODGOVORI, mozniOdgovor.getOdgovor());


        db.insert(tabelaMozniOdgovori, null, cv);

        db.close();
    }

    public Cursor getMozniOdgovori() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaMozniOdgovori, null);
        c.moveToFirst();
        return c;
    }


    public void addAktivnoVprasanje(DbAktivnoVprasanje aktivnoVprasanje){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(AKTIVNO_VPRASANJA, aktivnoVprasanje.getAktivno_vprasanje());
        cv.put(AKTIVNI_ODGOVORI, aktivnoVprasanje.getAktivni_odgovori());
        cv.put(AKTIVNI_CLANI, aktivnoVprasanje.getAktivni_clani());


        db.insert(tabelaAktivnaVprasanja, null, cv);

        db.close();
    }

    public Cursor getAktivnaVprasanja() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaAktivnaVprasanja, null);
        c.moveToFirst();
        return c;
    }


    /*public List<String> getListVprasanjaAvto(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaAvto, null);
        c.moveToFirst();
        List<String> znamka = new ArrayList<>();
        while (!c.isAfterLast()) {
            String fuelType = c.getString(c.getColumnIndex(ZNAMKA_AVTA));

            znamka.add(fuelType);

            c.moveToNext();
        }
        return znamka;
    }

    public List<String> getListModelAvto(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaAvto, null);
        c.moveToFirst();
        List<String> model = new ArrayList<>();
        while (!c.isAfterLast()) {
            String fuelType = c.getString(c.getColumnIndex(MODEL_AVTA));

            model.add(fuelType);

            c.moveToNext();
        }
        return model;
    }

    public List<String> getListRegistracijske(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaAvto, null);
        c.moveToFirst();
        List<String> reg = new ArrayList<>();
        while (!c.isAfterLast()) {
            String id = c.getString(c.getColumnIndex(REG_ŠT));

            reg.add(id);

            c.moveToNext();
        }
        return reg;
    }

   public DbVprasanje getAvtoByReg(String reg){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] params = new String[]{String.valueOf(reg)};
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaAvto + " WHERE " + REG_ŠT + " = ?", params);
        c.moveToFirst();

        return new DbVprasanje(c.getString(c.getColumnIndex(ZNAMKA_AVTA)),
                c.getString(c.getColumnIndex(MODEL_AVTA)),
                c.getString(c.getColumnIndex(TIP_GORIVA)),
                c.getString(c.getColumnIndex(REG_ŠT)),
                c.getBlob(c.getColumnIndex(SLIKA)));
    }


    public void updateAvto(DbVprasanje avto, String reg){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ZNAMKA_AVTA, avto.getZnamka());
        cv.put(MODEL_AVTA, avto.getModel());
        cv.put(TIP_GORIVA, avto.getTipGoriva());
        cv.put(REG_ŠT, avto.getRegŠt());
        cv.put(SLIKA, avto.getSlika());

        db.update(tabelaAvto, cv, REG_ŠT + "=?", new String[]{toString().valueOf(reg)});

        db.close();

    }

    public void deleteAvto(String reg){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tabelaAvto, REG_ŠT + " = '" + reg + "'", null);
        db.delete(tabelaTocenje, TRACK_REG_AVTA_TOCENJE + " = '" + reg + "'", null);
        db.delete(tabelaServis, TRACK_REG_AVTA_SERVIS + " = '" + reg + "'", null);
        db.close();
    }*/

}
