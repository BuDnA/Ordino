package com.cybermyth.matej.ordino;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cybermyth.matej.ordino.Database.AktivnaVprasanjaAdapter;
import com.cybermyth.matej.ordino.Database.DbHelper;
import com.cybermyth.matej.ordino.Database.DbMozniOdgovor;
import com.cybermyth.matej.ordino.Database.DbUporabnik;
import com.cybermyth.matej.ordino.Database.DbVprasanje;
import com.cybermyth.matej.ordino.Database.MozniOdgovorAdapter;
import com.cybermyth.matej.ordino.Database.SkupineAdapter;
import com.cybermyth.matej.ordino.Database.UporabnikiAdapter;

public class juhej extends AppCompatActivity {


    private Button shrani, dodaj_odgovor;
    private Context context;
    private DbHelper dbHelper;
    private EditText editTextVprasanje, editTextOdgovor;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vprasanje);






        context = this;
        dbHelper = new DbHelper(context);

        shrani = (Button) findViewById(R.id.Bshrani);
        dodaj_odgovor = (Button) findViewById(R.id.dodaj_odgovor);
        editTextVprasanje = (EditText) findViewById(R.id.EVprasanje);
        listView = (ListView) findViewById(R.id.list_mozni_odgovori);

        Cursor cursor = dbHelper.getMozniOdgovori();
        MozniOdgovorAdapter pAdapter2= new MozniOdgovorAdapter(this, cursor);
        listView.setAdapter(pAdapter2);

        dodaj_odgovor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                odgovor_dialog();
            }
        });

        shrani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vprasanje = editTextVprasanje.getText().toString();
                String odgovor = ButtonClick();
                if (vprasanje.matches("")) {
                    Toast.makeText(getApplicationContext(), "Niste vnesli vprašanja", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(odgovor.length()== 0){
                    Toast.makeText(getApplicationContext(), "Prosim označite oz. dodajte vsaj en odgovor", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!vprasanje.matches("") && !odgovor.matches("")){
                    dbHelper.addVprasanja(new DbVprasanje(vprasanje, odgovor));
                    Toast.makeText(getApplicationContext(), "Vprasanje uspešno dodano", Toast.LENGTH_SHORT).show();
                    finish();
                }
                finish();

            }
        });
    }

    private String ButtonClick() {
        View v;
        TextView et;
        CheckBox ch;
        String odgovor = "";
        for (int i = 0; i < listView.getCount(); i++) {
            v = listView.getChildAt(i);
            ch = (CheckBox) v.findViewById(R.id.odgovor_check);
            et = (TextView) v.findViewById(R.id.mozni_odgovor_text);
            if(ch.isChecked()){
                odgovor += et.getText().toString() + ",";
                Log.e(et.getText().toString(), et.getText().toString());
            }

        }
        return odgovor;
    }


    public void onResume() {
        super.onResume();
        // This needs to be done in onResume to refresh listView after returning from adding car
        DbHelper dbHelper = new DbHelper(this);
        Cursor cursor = dbHelper.getMozniOdgovori();
        MozniOdgovorAdapter pAdapter2= new MozniOdgovorAdapter(this, cursor);
        listView.setAdapter(pAdapter2);
    }

    public void odgovor_dialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        final LayoutInflater inflater = this.getLayoutInflater();
        final View inflator = inflater.inflate(R.layout.odgovori_dialog, null);

        builder.setView(inflator);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                EditText odgovor = (EditText)inflator.findViewById(R.id.odgovor);
                DbHelper dbHelper = new DbHelper(context);
                dbHelper.addMozniOdgovor(new DbMozniOdgovor(odgovor.getText().toString()));
                onResume();
                dialog.dismiss();
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }
}
