package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        getSupportActionBar().hide();

        openLoadingScreen();


    }

    public void openLoadingScreen(){
        Thread welcomeThread = new Thread() {
            @Override
            public void run() {
                try {
                    super.run();
                    sleep(5000);
                } catch (Exception e) {

                } finally {
                    Intent loading = new Intent(LoadingActivity.this, MainActivity.class);
                    startActivity(loading);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }

}