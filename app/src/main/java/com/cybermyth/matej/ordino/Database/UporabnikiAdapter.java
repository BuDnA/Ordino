package com.cybermyth.matej.ordino.Database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.cybermyth.matej.ordino.R;


/**
 * Created by Jure on 16. jan 2016.
 */
public class UporabnikiAdapter extends CursorAdapter {

    Cursor uporabniki;
    Context context;
    DbHelper dbHelper;

    public UporabnikiAdapter(Context context, Cursor uporabniki){
        super(context, uporabniki);

        this.uporabniki = uporabniki;
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        return mInflater.inflate(R.layout.item_friend, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        dbHelper = new DbHelper(context);
        String uporanisko_ime = cursor.getString(cursor.getColumnIndex(DbHelper.UPORABNISKO_IME));


        TextView uporabnik_text = (TextView) view.findViewById(R.id.item_friend_text);
        uporabnik_text.setText(uporanisko_ime);


    }
}

