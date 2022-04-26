package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AllObjectsListActivity extends Activity implements AdapterView.OnItemSelectedListener{
    private static final String FILE_NAME = "example.txt";
    EditText mEditText;
    int[] intArray;
    //Object[] objectArray= new Object[19];

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





        //setContentView(R.layout.activity_allobjects);
        Spinner spinner = findViewById(R.id.spinner_ObjectOptions);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pasirinkimai, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        filter(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void filter(int flag) {
        List<String> temp= new ArrayList<String>();
        //makeSureNotZero();
        countPoints();
        for(int i=0;i<countryList.length;i++)
        {
            if(flag==0||getFlag(i)==flag-1)
            {
                temp.add(countryList[i]);
            }
        }

        String[] filtered = new String[temp.size()];
        for(int i = 0; i < temp.size(); i++) filtered[i] = temp.get(i);

        simpleList = (ListView) findViewById(R.id.mobile_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, filtered);
        simpleList.setAdapter(arrayAdapter);
    }
    public void makeSureNotZero() {
        File file = new File(getApplicationContext().getFilesDir(),FILE_NAME);
        if(file.exists())
        {
        }
        else {
            intArray=new int[19];
            for(int i=0;i<intArray.length;i++)
            {
                intArray[i]=0;
            }
        }
    }
    public int getFlag(int object) {
        FileInputStream fis = null;
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(getApplicationContext().getFilesDir(),FILE_NAME);
            if(file.exists())
            {
                fis = openFileInput(FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);

                String text;

                while ((text = br.readLine()) != null)
                {
                    sb.append(text).append("\n");
                }
                String text2=sb.toString();
                text2 = text2.replaceAll("\n", "");
                String[]arrOfStr = text2.split(";", 0);
                intArray=new int[arrOfStr.length];
                for(int i=0;i<arrOfStr.length;i++)
                {
                    intArray[i]=Integer.parseInt(arrOfStr[i]) ;
                }
            }
            else {
                intArray = new int[19];
                for (int i = 0; i < intArray.length; i++) {
                    intArray[i] = 0;
                }
            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return intArray[object];
    }
    public int countPoints(){
        FileInputStream fis = null;
        String text3="";
        StringBuilder sb = new StringBuilder();
        try {

            File file = new File(getApplicationContext().getFilesDir(),FILE_NAME);
            if(file.exists())
            {
                fis = openFileInput(FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);

                String text;

                while ((text = br.readLine()) != null)
                {
                    sb.append(text).append("\n");
                }
                String text2=sb.toString();
                text2 = text2.replaceAll("\n", "");
                String[]arrOfStr = text2.split(";", 0);
                intArray=new int[arrOfStr.length];
                for(int i=0;i<arrOfStr.length;i++)
                {
                    intArray[i]=Integer.parseInt(arrOfStr[i]) ;
                }
            }
            else {
                intArray=new int[19];
                for(int i=0;i<intArray.length;i++)
                {
                    intArray[i]=0;
                }
            }

            for(int i=0;i<intArray.length;i++)
            {
                text3+=Integer.toString(intArray[i])+";";
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        int pointsCounter=0;
        for(int i=0;i<intArray.length;i++)
        {
            pointsCounter+=intArray[i];
        }
        return pointsCounter;
    }
}