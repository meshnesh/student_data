package com.example.entsfrank.datacollection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SingleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.singleViewContainer, new InfoFragment())
                .commit();
    }
}
