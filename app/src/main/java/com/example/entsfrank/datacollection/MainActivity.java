package com.example.entsfrank.datacollection;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fManager = getSupportFragmentManager();
        fManager.beginTransaction()
                .add(R.id.dataCollectionContainer, new DataListFragment())
                .commit();
    }
}
