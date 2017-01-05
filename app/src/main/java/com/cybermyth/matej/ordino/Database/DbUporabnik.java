package com.cybermyth.matej.ordino.Database;

/**
 * Created by borut on 4.1.2017.
 */

public class DbUporabnik {

    private String user_name;

    public DbUporabnik(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
