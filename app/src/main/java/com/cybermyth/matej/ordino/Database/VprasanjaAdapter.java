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
public class VprasanjaAdapter extends CursorAdapter {

    Cursor vprasanja;
    Context context;
    DbHelper dbHelper;

    public VprasanjaAdapter(Context context, Cursor vprasanja){
        super(context, vprasanja);

        this.vprasanja = vprasanja;
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        return mInflater.inflate(R.layout.item_vprasanje, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        dbHelper = new DbHelper(context);
        String vprasanje = cursor.getString(cursor.getColumnIndex(DbHelper.VPRASANJE));
        String odgovor = cursor.getString(cursor.getColumnIndex(DbHelper.ODGOVORI));

        TextView vprasanje_text = (TextView) view.findViewById(R.id.item_vprasanje_text);
        vprasanje_text.setText(vprasanje);


        TextView odgovor_text = (TextView) view.findViewById(R.id.item_odgovor_text);
        odgovor_text.setText(odgovor);



    }
}

