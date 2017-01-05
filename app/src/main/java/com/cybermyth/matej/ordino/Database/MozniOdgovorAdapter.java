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
public class MozniOdgovorAdapter extends CursorAdapter {

    Cursor mozni_odgovor;
    Context context;
    DbHelper dbHelper;

    public MozniOdgovorAdapter(Context context, Cursor mozni_odgovor){
        super(context, mozni_odgovor);

        this.mozni_odgovor = mozni_odgovor;
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        return mInflater.inflate(R.layout.item_mozni_odgovor, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        dbHelper = new DbHelper(context);
        String odgovor = cursor.getString(cursor.getColumnIndex(DbHelper.MOZNI_ODGOVORI));



        TextView odgovor_text = (TextView) view.findViewById(R.id.mozni_odgovor_text);
        odgovor_text.setText(odgovor);


    }
}


