package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnglishMainActivity extends AppCompatActivity {
    private Button mapsButton;
    private Button contactButton;
    private Button myPointsButton;
    private Button allObjectsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_main);
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
        Intent intent = new Intent(this, EnglishMapsActivity.class);
        startActivity(intent);
    }

    public void openContacts(){
        Intent intent = new Intent(this, EnglishContactActivity.class);
        startActivity(intent);
    }
    public void openCounter(){
        Intent intent = new Intent(this, EnglishVisitedCounterActivity.class);
        startActivity(intent);
    }

    public void openListOfAllObjects(){
        Intent intent = new Intent(this, EnglishAllObjects.class);
        startActivity(intent);
    }

}