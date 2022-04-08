package com.example.myapplication;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class PazaislioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pazaislis);
        getSupportActionBar().hide();




        showIfUnvisited();
        setObjectData();

    }
    private static final int STORAGE_PERMISSION_CODE = 101;
    private static final String FILE_NAME = "example.txt";
    EditText mEditText;
    int[] intArray;
    Object[] objectArray= new Object[19];
    int objectNr=1;    //###############################################################   0 tik jacht klubui

    public void setObjectData(){
        objectArray[0]=new Object("Jachtklubas",54.885412,24.024804);
        objectArray[1]=new Object("Pažaislio vienuolynas",54.876222,24.021416);
        objectArray[2]=new Object("Šuneliškių kalnas",54.899165,24.050468);
        objectArray[3]=new Object("Lakštingalų slėnis",54.909123,24.064016);
        objectArray[4]=new Object("Kauno marių regioninis parkas",54.916187,24.088728);
        objectArray[5]=new Object("Meilės įlanka",54.897875,24.126895);
        objectArray[6]=new Object("Kauno marių apleista stovyklą",54.879261,24.153153);
        objectArray[7]=new Object("Gastilionių atodanga",54.871606,24.150136);
        objectArray[8]=new Object("Rumšiškių liaudies buities muziejus",54.866331,24.200702);
        objectArray[9]=new Object("Rumšiškių prieplauka",54.860661,24.196769);
        objectArray[10]=new Object("Kapitoniškių pažintinis takas",54.859187,24.213946);
        objectArray[11]=new Object("Mergakalnio apžvalgos aikštelė",54.824597,24.243838);
        objectArray[12]=new Object("Kruonio HAE",54.799203,24.247184);
        objectArray[13]=new Object("Žigos įlanka",54.841290,24.194681);
        objectArray[14]=new Object("Skulptūrų parkas",54.858654,24.114648);
        objectArray[15]=new Object("Žiegždrių takas",54.889264,24.076552);
        objectArray[16]=new Object("Laumėnų parkas",54.874337,24.049471);
        objectArray[17]=new Object("Laumėnų pažintinis takas",54.863047,24.043927);
        objectArray[18]=new Object("Pakalniškių pažintinis takas",54.855207,24.017669);

    }
    public void showIfUnvisited()
    {
        Button playButton = (Button) findViewById(R.id.button_addPoint1);  //##########################################################     cia pakeisti

        if(getFlag(objectNr)==0)//&&objectArray[objectNr].getLengtitude()-
        {
            playButton.setVisibility(View.VISIBLE);
            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //when "visit" is clicked hide button


                    checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);

                }
            });
        }

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
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(this, new String[] { permission }, requestCode);
        }
        else {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
            loadToArray(objectNr,1);
            Button playButton = (Button) findViewById(R.id.button_addPoint0);
            playButton.setVisibility(View.GONE);
        }
    }


    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}