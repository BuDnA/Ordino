package com.cybermyth.matej.ordino;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cybermyth.matej.ordino.Database.DbAktivnoVprasanje;
import com.cybermyth.matej.ordino.Database.DbHelper;
import com.cybermyth.matej.ordino.Database.DbSkupin;
import com.cybermyth.matej.ordino.Database.DbUporabnik;
import com.cybermyth.matej.ordino.Database.SkupineAdapter;
import com.cybermyth.matej.ordino.Database.UporabnikiAdapter;
import com.cybermyth.matej.ordino.Database.VprasanjaAdapter;

import java.util.ArrayList;
import java.util.Arrays;


public class FriendsFragment extends Fragment{

    private ListView listView, listView2;
    private Button pošlji;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    public FriendsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.friends_fragment, container, false);
        pošlji = (Button) v.findViewById(R.id.buttonPošlji);
        try {
            Bundle bundle = getArguments();
            final String vprasanje = bundle.getString("vprasanje");
            Log.e(vprasanje, vprasanje);

        } catch (NullPointerException e) {
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        }
        //String vprasanje = bundle.getString("vprasanje");
        //Log.e(vprasanje, vprasanje);
        pošlji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Želite dodati ljudi v skupino?");

                builder.setPositiveButton("Da", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Context context = getContext();
                        DbHelper dbHelper = new DbHelper(context);
                        dbHelper.addGroup(new DbSkupin(ButtonClick()));
                        onResume();
                        dbHelper.addAktivnoVprasanje(new DbAktivnoVprasanje("Ali greš danes na kosilo?", "Da, Ne, Mogoče", "Jaka Kokošar, Borut Budna, Matej Fistrovič"));
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                        startActivity(intent);
                        dialog.dismiss();
                    }

                });

                builder.setNegativeButton("Ne", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Bundle bundle = getArguments();
                            final String vprasanje = bundle.getString("vprasanje");
                            Log.e(vprasanje, vprasanje);

                        } catch (NullPointerException e) {
                            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
                        }
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        setHasOptionsMenu(true);
        return v;
    }

    private String ButtonClick() {
        View v;
        TextView et;
        CheckBox ch;
        StringBuilder skupina = new StringBuilder();
        for (int i = 0; i < listView.getCount(); i++) {
            v = listView.getChildAt(i);
            ch = (CheckBox) v.findViewById(R.id.friend_check);
            et = (TextView) v.findViewById(R.id.item_friend_text);
            if(ch.isChecked()){
                skupina.append(et.getText().toString() + ", ");
                Log.e(et.getText().toString(), et.getText().toString());
            }

        }
        if(skupina.charAt(skupina.length()-2) == ','){
            skupina.setCharAt(skupina.length()-2, ' ');
        }


        return String.valueOf(skupina);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            Bundle bundle = getArguments();
            final String vprasanje = bundle.getString("vprasanje");
            Log.e(vprasanje, vprasanje);

        } catch (NullPointerException e) {
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        }
        //emptyView = view.findViewById(android.R.id.empty);
        listView = (ListView) view.findViewById(R.id.list_friends);
        listView2 = (ListView) view.findViewById(R.id.list_groups);
        //listView.setEmptyView(emptyView);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

            }
        });*/
    }

    public void onResume() {
        super.onResume();
        // This needs to be done in onResume to refresh listView after returning from adding car
        DbHelper dbHelper = new DbHelper(getActivity());
        Cursor cursor = dbHelper.getUsers();
        Cursor cursor2 = dbHelper.getGroups();
        SkupineAdapter pAdapter2= new SkupineAdapter(getActivity(), cursor2);
        UporabnikiAdapter pAdapter = new UporabnikiAdapter(getActivity(), cursor);
        listView.setAdapter(pAdapter);
        listView2.setAdapter(pAdapter2);
        listView.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        listView2.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        //setListViewHeightBasedOnChildren(listView);
        //setListViewHeightBasedOnChildren(listView2);
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

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, RelativeLayout.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
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
