package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LithuanianMainActivity extends AppCompatActivity {

    private Button mapsButton;
    private Button contactButton;
    private Button myPointsButton;
    private Button allObjectsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        mapsButton     = (Button) findViewById(R.id.mapsButton);
        contactButton  = (Button) findViewById(R.id.contactButton);
        myPointsButton = (Button) findViewById(R.id.myPointsButton);
        allObjectsButton = (Button) findViewById(R.id.allObjectsButton);


        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMaps();
            }
        });

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContacts();
            }
        });

        myPointsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCounter();//open allObjectsList
           }
       });

        allObjectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListOfAllObjects();//open allObjectsList
            }
        });


    }

    public void openMaps(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void openContacts(){
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }
    public void openCounter(){
        Intent intent = new Intent(this, VisitedCounterActivity.class);
        startActivity(intent);
    }

    public void openListOfAllObjects(){
        Intent intent = new Intent(this, AllObjectsListActivity.class);
        startActivity(intent);
    }

}