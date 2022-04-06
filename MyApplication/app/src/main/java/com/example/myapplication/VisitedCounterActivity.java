package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.nio.file.Path;


public class VisitedCounterActivity extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";
    EditText mEditText;
    int[] intArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visited_counter);
        getSupportActionBar().hide();
        mEditText = findViewById(R.id.edit_text);
    }


    public void MyFunction(View v) {
        String text = mEditText.getText().toString();
        loadToArray(5,1);
        loadToArray(2,1);
        mEditText.setText(Integer.toString(getFlag(3)));

    }
    public int getFlag(int object) {
        FileInputStream fis = null;
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(getApplicationContext().getFilesDir(),FILE_NAME);
            if(file.exists())
            {
                fis = openFileInput(FILE_NAME);//skaitome is failo
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);

                String text;

                while ((text = br.readLine()) != null)
                {
                    sb.append(text).append("\n");
                }
                String text2=sb.toString();//parsiname ir dedame i masyva
                text2 = text2.replaceAll("\n", "");
                String[]arrOfStr = text2.split(";", 0);
                intArray=new int[arrOfStr.length];
                for(int i=0;i<arrOfStr.length;i++)
                {
                    intArray[i]=Integer.parseInt(arrOfStr[i]) ;
                }
            }
            else {
                intArray = new int[19];//HARDCODAS, Toks skaicius kiek mes objektu turim
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
    public void loadToArray(int object,int flag) {
        FileInputStream fis = null;
        String text3="";
        StringBuilder sb = new StringBuilder();
        try {

                File file = new File(getApplicationContext().getFilesDir(),FILE_NAME);
                if(file.exists())
                {
                    fis = openFileInput(FILE_NAME);//skaitome is failo
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);//jei failas egzistuoja, vadinasi nuskaitysim duomenis, sudesim i masyva ir pakeisim ta vieta kuria reikia ir vel sudesim i txt faila.

                    String text;

                    while ((text = br.readLine()) != null)
                    {
                        sb.append(text).append("\n");
                    }
                    String text2=sb.toString();//parsiname ir dedame i masyva
                    text2 = text2.replaceAll("\n", "");
                    String[]arrOfStr = text2.split(";", 0);
                    intArray=new int[arrOfStr.length];
                    for(int i=0;i<arrOfStr.length;i++)
                    {
                        intArray[i]=Integer.parseInt(arrOfStr[i]) ;
                    }
                    intArray[object]=flag;
                }
                else {
                    intArray=new int[19];//HARDCODAS, Toks skaicius kiek mes objektu turim
                    for(int i=0;i<intArray.length;i++)
                    {
                        intArray[i]=0;
                    }
                    intArray[object]=flag;
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
        //loadinimas atgal i faila
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text3.getBytes());

            mEditText.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void save(View v) {
        String text = mEditText.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

            mEditText.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void reset(View v) {
        String text = "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0";//Hardcodinta tiek nuliu kiek pas mus objektu
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

            mEditText.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void load(View v) {
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            mEditText.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
    }

}