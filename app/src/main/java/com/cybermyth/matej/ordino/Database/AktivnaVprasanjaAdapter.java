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
public class AktivnaVprasanjaAdapter extends CursorAdapter {

    Cursor aktivna_vprasanja;
    Context context;
    DbHelper dbHelper;

    public AktivnaVprasanjaAdapter(Context context, Cursor aktivna_vprasanja){
        super(context, aktivna_vprasanja);

        this.aktivna_vprasanja = aktivna_vprasanja;
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        return mInflater.inflate(R.layout.item_aktivno_vprasanje, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        dbHelper = new DbHelper(context);
        String aktivno_vprasanje = cursor.getString(cursor.getColumnIndex(DbHelper.AKTIVNO_VPRASANJA));
        String aktivni_odgovori = cursor.getString(cursor.getColumnIndex(DbHelper.AKTIVNI_ODGOVORI));
        String aktivni_clani = cursor.getString(cursor.getColumnIndex(DbHelper.AKTIVNI_CLANI));


        TextView vprasaje_text = (TextView) view.findViewById(R.id.aktivno_vprasanje_text);
        vprasaje_text.setText(aktivno_vprasanje);

        TextView odgovor_text = (TextView) view.findViewById(R.id.akitvni_odgovori_text);
        odgovor_text.setText(aktivni_odgovori);

        TextView clani_text = (TextView) view.findViewById(R.id.aktivni_clani_text);
        clani_text.setText(aktivni_clani);

    }
}

