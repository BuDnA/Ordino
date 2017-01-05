package com.cybermyth.matej.ordino;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.cybermyth.matej.ordino.Database.AktivnaVprasanjaAdapter;
import com.cybermyth.matej.ordino.Database.DbHelper;
import com.cybermyth.matej.ordino.Database.DbUporabnik;
import com.cybermyth.matej.ordino.Database.DbVprasanje;
import com.cybermyth.matej.ordino.Database.VprasanjaAdapter;

import java.util.ArrayList;
import java.util.Arrays;


public class FormsFragment extends Fragment{

    private ListView listView;
    private View v;
    public FormsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        v = inflater.inflate(R.layout.forms_fragment, container, false);
        return v;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miProfile:
                user_dialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onResume() {
        super.onResume();
        // This needs to be done in onResume to refresh listView after returning from adding car
        DbHelper dbHelper = new DbHelper(getActivity());
        Cursor cursor = dbHelper.getAktivnaVprasanja();
        AktivnaVprasanjaAdapter pAdapter = new AktivnaVprasanjaAdapter(getActivity(), cursor);
        listView.setAdapter(pAdapter);
    }

    public void user_dialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View inflator = inflater.inflate(R.layout.user_dialog, null);

        builder.setView(inflator);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Context context = getContext();
                        EditText user_name = (EditText)inflator.findViewById(R.id.username);
                        String ime = user_name.getText().toString();
                        Log.e(ime, ime);
                        DbHelper dbHelper = new DbHelper(context);
                        dbHelper.addUporabnik(new DbUporabnik(ime));
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //emptyView = view.findViewById(android.R.id.empty);
        listView = (ListView) view.findViewById(R.id.aktivna_vprasanja_list);
        //listView.setEmptyView(emptyView);

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

            }
        });*/
    }

}