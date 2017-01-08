package com.cybermyth.matej.ordino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailVprasanje extends AppCompatActivity {

    private TextView textViewClani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vprasanje);

        final String clani = getIntent().getStringExtra("clani");

        //textViewClani = (TextView) findViewById(R.id.detail_clani);
        //textViewClani.setText(clani);

        //detail_clani
    }
}
