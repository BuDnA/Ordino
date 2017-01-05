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
public class SkupineAdapter extends CursorAdapter {

    Cursor skupina;
    Context context;
    DbHelper dbHelper;

    public SkupineAdapter(Context context, Cursor skupina){
        super(context, skupina);

        this.skupina = skupina;
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        return mInflater.inflate(R.layout.item_group, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        dbHelper = new DbHelper(context);
        String skupina = cursor.getString(cursor.getColumnIndex(DbHelper.CLANI_SKUPINE));


        TextView skupina_text = (TextView) view.findViewById(R.id.item_group_text);
        skupina_text.setText(skupina);




    }
}

