package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;


public class LoadingActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        getSupportActionBar().hide();

        progressBar    = (ProgressBar) findViewById(R.id.progress_bar);
        textView       = (TextView) findViewById(R.id.percentage_text);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();


    }

    public void progressAnimation()
    {
        ProgressBarAnimation anim = new ProgressBarAnimation(this, progressBar, textView, 0f, 100f);
        anim.setDuration(5000);
        progressBar.setAnimation(anim);
    }
}


