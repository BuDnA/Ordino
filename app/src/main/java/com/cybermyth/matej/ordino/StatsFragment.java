package com.cybermyth.matej.ordino;

import android.app.Dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cybermyth.matej.ordino.Database.DbHelper;
import com.cybermyth.matej.ordino.Database.VprasanjaAdapter;

import java.util.ArrayList;
import java.util.Arrays;


public class StatsFragment extends Fragment {


    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    private Button dodaj;
    private View emptyView;
    private ListView listView;


    public StatsFragment() {

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
        View rootView = inflater.inflate(R.layout.matej, container, false);

        // Inflate the layout for this fragment
        dodaj=(Button)rootView.findViewById(R.id.button_dodaj_vprasanje);

        dodaj.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), juhej.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                startActivity(intent);
            }
        });
        return rootView;

    }


    public void onResume() {
        super.onResume();
        // This needs to be done in onResume to refresh listView after returning from adding car
        DbHelper dbHelper = new DbHelper(getActivity());
        Cursor cursor = dbHelper.getVprasanje();
        VprasanjaAdapter pAdapter = new VprasanjaAdapter(getActivity(), cursor);
        listView.setAdapter(pAdapter);
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

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //emptyView = view.findViewById(android.R.id.empty);
        listView = (ListView) view.findViewById(R.id.list_vprasanja);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                Bundle bundle = new Bundle();
                String vprasanje =(String) ((TextView) view.findViewById(R.id.item_vprasanje_text)).getText();
                String name =(String) ((TextView) view.findViewById(R.id.item_odgovor_text)).getText();

                Log.e("BLAAAAAAAAAAAA", name);


                bundle.putString("vprasanje", vprasanje);
                Log.e(vprasanje, vprasanje);
                ViewPager viewPager = (ViewPager) MainActivity.mInstance.findViewById(R.id.viewpager);
                Integer next = viewPager.getCurrentItem() +1;

                viewPager.setCurrentItem(next);
                /*Fragment fragment = new FriendsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

            }
        });
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

            }
        });*/
    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.vprasanja, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }




    public void user_dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.user_dialog, null))
                // Add action buttons
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
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