package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnglishContactActivity extends AppCompatActivity {
    private Button sourcesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.english_activity_contact);

        getSupportActionBar().hide();

        sourcesButton     = (Button) findViewById(R.id.button_saltiniai);
        sourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSources();
            }
        });

    }
    public void openSources(){
        Intent intent = new Intent(this, EnglishSourcesActivity.class);
        startActivity(intent);
    }
}
