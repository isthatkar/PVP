package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AllObjectsListActivity extends Activity {

    // Array of strings...
    ListView simpleList;
    String countryList[] = {"Jachtklubas",
            "Pažaislio vienuolynas",
            "Šuneliškių kalnas",
            "Lakštingalų slėnis",
            "Kauno marių regioninis parkas",
            "Meilės įlanka",
            "Kauno marių apleista stovykla",
            "Gastilionių atodanga",
            "Rumšiškių liaudies buities muziejus",
            "Kapitoniškių pažintinis takas",
            "Mergakalnio apžvalgos aikštelė",
            "Kruonio HAE",
            "Žiglos įlanka",
            "Skulptūrų parkas",
            "Žiegždrių takas",
            "Laumėnų parkas",
            "Laumėnų pažintinis takas",
            "Pakalniškių pažintinis takas"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allobjects);
        simpleList = (ListView) findViewById(R.id.mobile_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);
    }
}