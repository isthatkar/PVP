package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LanguageSelection extends AppCompatActivity {

    private Button lithuanianButton;
    private Button englishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);
        getSupportActionBar().hide();

        lithuanianButton     = (Button) findViewById(R.id.languageLithuanian);
        englishButton  = (Button) findViewById(R.id.languageEnglish);

        lithuanianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLithuanian();
            }
        });


        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnglish();
            }
        });

    }

    public void openLithuanian(){
        Intent intent = new Intent(this, LithuanianMainActivity.class);
        startActivity(intent);
    }

    public void openEnglish(){
        Intent intent = new Intent(this, EnglishMainActivity.class);
        startActivity(intent);
    }
}