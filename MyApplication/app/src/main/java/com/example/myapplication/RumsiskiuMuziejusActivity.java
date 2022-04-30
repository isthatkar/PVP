package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import java.util.concurrent.TimeUnit;

public class RumsiskiuMuziejusActivity extends AppCompatActivity {

    TextView playerPositionIstorija1,
            playerPositionFaktai,
            playerDurationIstorija1,
            playerDurationFaktai,
            playerPositionIstorija2,
            playerDurationIstorija2;
    SeekBar seekBarIstorija1,
            seekBarIstorija2,
            seekBarFaktai;
    ImageView btPlayIstorija1,
            btPlayFaktai,
            btPauseIstorija1,
            btPauseFaktai,
            btPlayIstorija2,
            btPauseIstorija2;

    MediaPlayer mediaPlayerIstorija1, mediaPlayerIstorija2,
            mediaPlayerFaktai;
    Handler handlerIstorija1 = new Handler();
    Handler handlerIstorija2 = new Handler();
    Handler handlerFaktai = new Handler();
    Runnable runnableIstorija1,runnableIstorija2,
            runnableFaktai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rumsiskiu_muziejus);

        getSupportActionBar().hide();
        setObjectData();
        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            OnGPS();
        }
        else
        {
            getLocation();
        }
        showIfUnvisited();
        methodForFacts();
        methodForHistory1();
        methodForHistory2();
    }

    public void methodForHistory1()
    {
        playerPositionIstorija1 = findViewById(R.id.rumsiskesIstorija1PlayerPosition);
        playerDurationIstorija1 = findViewById(R.id.rumsiskesIstorija1PlayerDuration);
        seekBarIstorija1      = findViewById(R.id.rumsiskesIstorija1SeekBar);
        btPlayIstorija1         = findViewById(R.id.rumsiskesIstorija1Play);
        btPauseIstorija1        = findViewById(R.id.rumsiskesIstorija1Pause);



        mediaPlayerIstorija1 = MediaPlayer.create(this, R.raw.ltrumsiskiuliaudiesbuitiesmuziejusistorija);

        runnableIstorija1 = new Runnable() {
            @Override
            public void run() {
                seekBarIstorija1.setProgress(mediaPlayerIstorija1.getCurrentPosition());
                handlerIstorija1.postDelayed(this, 500);
            }
        };

        int duration = mediaPlayerIstorija1.getDuration();
        String sDuration = convertFormat(duration);
        playerDurationIstorija1.setText(sDuration);

        btPlayIstorija1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlayIstorija1.setVisibility(View.GONE);
                mediaPlayerIstorija1.start();
                seekBarIstorija1.setMax(mediaPlayerIstorija1.getDuration());
                handlerIstorija1.postDelayed(runnableIstorija1, 0);
                btPauseIstorija1.setVisibility(View.VISIBLE);
            }
        });

        btPauseIstorija1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlayIstorija1.setVisibility(View.VISIBLE);
                mediaPlayerIstorija1.pause();
                handlerIstorija1.removeCallbacks(runnableIstorija1);
                handlerIstorija1.postDelayed(runnableIstorija1, 0);
                btPauseIstorija1.setVisibility(View.GONE);
            }
        });

        seekBarIstorija1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayerIstorija1.seekTo(progress);
                }
                playerPositionIstorija1.setText(convertFormat(mediaPlayerIstorija1.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayerIstorija1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btPlayIstorija1.setVisibility(View.VISIBLE);
                mediaPlayerIstorija1.seekTo(0);
                btPauseIstorija1.setVisibility(View.GONE);

            }
        });
    }

    public void methodForHistory2()
    {
        playerPositionIstorija2 = findViewById(R.id.rumsiskesIstorija2PlayerPosition);
        playerDurationIstorija2 = findViewById(R.id.rumsiskesIstorija2PlayerDuration);
        seekBarIstorija2      = findViewById(R.id.rumsiskesIstorija2SeekBar);
        btPlayIstorija2         = findViewById(R.id.rumsiskesIstorija2Play);
        btPauseIstorija2        = findViewById(R.id.rumsiskesIstorija2Pause);



        mediaPlayerIstorija2 = MediaPlayer.create(this, R.raw.ltrumsiskiuliaudiesbuitiesmusiejusistorija2);

        runnableIstorija2 = new Runnable() {
            @Override
            public void run() {
                seekBarIstorija2.setProgress(mediaPlayerIstorija2.getCurrentPosition());
                handlerIstorija2.postDelayed(this, 500);
            }
        };

        int duration = mediaPlayerIstorija2.getDuration();
        String sDuration = convertFormat(duration);
        playerDurationIstorija2.setText(sDuration);

        btPlayIstorija2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlayIstorija2.setVisibility(View.GONE);
                mediaPlayerIstorija2.start();
                seekBarIstorija2.setMax(mediaPlayerIstorija2.getDuration());
                handlerIstorija2.postDelayed(runnableIstorija2, 0);
                btPauseIstorija2.setVisibility(View.VISIBLE);
            }
        });

        btPauseIstorija2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlayIstorija2.setVisibility(View.VISIBLE);
                mediaPlayerIstorija2.pause();
                handlerIstorija2.removeCallbacks(runnableIstorija2);
                handlerIstorija2.postDelayed(runnableIstorija2, 0);
                btPauseIstorija2.setVisibility(View.GONE);
            }
        });

        seekBarIstorija2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayerIstorija2.seekTo(progress);
                }
                playerPositionIstorija2.setText(convertFormat(mediaPlayerIstorija2.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayerIstorija2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btPlayIstorija2.setVisibility(View.VISIBLE);
                mediaPlayerIstorija2.seekTo(0);
                btPauseIstorija2.setVisibility(View.GONE);

            }
        });
    }


    public void methodForFacts()
    {
        playerPositionFaktai = findViewById(R.id.rumsiskesFaktaiPlayerPosition);
        playerDurationFaktai = findViewById(R.id.rumsiskesFaktaiPlayerDuration);
        seekBarFaktai        = findViewById(R.id.rumsiskesFaktaiSeekBar);
        btPlayFaktai         = findViewById(R.id.rumsiskesFaktaiPlay);
        btPauseFaktai        = findViewById(R.id.rumsiskesFaktaiPause);

        mediaPlayerFaktai = MediaPlayer.create(this, R.raw.ltrumsiskiuliaudiesbuitiesmuziejusfaktai);

        runnableFaktai = new Runnable() {
            @Override
            public void run() {
                seekBarFaktai.setProgress(mediaPlayerFaktai.getCurrentPosition());
                handlerFaktai.postDelayed(this, 500);
            }
        };

        int duration = mediaPlayerFaktai.getDuration();
        String sDuration = convertFormat(duration);
        playerDurationFaktai.setText(sDuration);

        btPlayFaktai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlayFaktai.setVisibility(View.GONE);
                mediaPlayerFaktai.start();
                seekBarFaktai.setMax(mediaPlayerFaktai.getDuration());
                handlerFaktai.postDelayed(runnableFaktai, 0);
                btPauseFaktai.setVisibility(View.VISIBLE);
            }
        });

        btPauseFaktai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlayFaktai.setVisibility(View.VISIBLE);
                mediaPlayerFaktai.pause();
                handlerFaktai.removeCallbacks(runnableFaktai);
                handlerFaktai.postDelayed(runnableFaktai, 0);
                btPauseFaktai.setVisibility(View.GONE);
            }
        });

        seekBarFaktai.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayerFaktai.seekTo(progress);
                }
                playerPositionFaktai.setText(convertFormat(mediaPlayerFaktai.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayerFaktai.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btPlayFaktai.setVisibility(View.VISIBLE);
                mediaPlayerFaktai.seekTo(0);
                btPauseFaktai.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onBackPressed(){
        if(mediaPlayerFaktai.isPlaying()){
            mediaPlayerFaktai.stop();
        }
        else if(mediaPlayerIstorija1.isPlaying()){
            mediaPlayerIstorija1.stop();
        }
        else
            mediaPlayerIstorija2.stop();
        super.onBackPressed();
    }

    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }


    private static  final int REQUEST_LOCATION=1;
    LocationManager locationManager;
    String latitude,longitude;

    private static final int STORAGE_PERMISSION_CODE = 101;
    private static final String FILE_NAME = "example.txt";
    EditText mEditText;
    int[] intArray;
    Object[] objectArray= new Object[19];
    int objectNr=8;    //###############################################################   0 tik jacht klubui
    int ToObjectDistance=7000; // Distance to object (if this is more than actual distance, button wont show)
    public void showIfUnvisited()
    {
        Button playButton = (Button) findViewById(R.id.button_addPoint8);  //##########################################################     cia pakeisti
        if(getFlag(objectNr)==0&&distance(Double.parseDouble(latitude),Double.parseDouble(longitude))<ToObjectDistance)//
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
    private void getLocation() {

        //Check Permissions again

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,

                        Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);
            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);
            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);
            }
            else
            {
                Toast.makeText(this, "Nepavyksta nustatyti vietovės", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
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
    //returns distance in meters between object koordinates and given point
    public double distance(double lat2, double lon2)
    {
        double lon1 = Math.toRadians(objectArray[objectNr].getPlattitude());
        lon2 = Math.toRadians(lon2);
        double lat1 = Math.toRadians(objectArray[objectNr].getLengtitude());
        lat2 = Math.toRadians(lat2);
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2),2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;
        return((c * r)*1000);
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
                intArray[object]=flag;
            }
            else {
                intArray=new int[19];//HARDCODED, ammount of objects, we currently have
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
            ActivityCompat.requestPermissions(this, new String[] { permission }, requestCode);
        }
        else {
            try {
                Toast.makeText(this, "Objektas aplankytas!", Toast.LENGTH_SHORT).show();
                Button playButton = (Button) findViewById(R.id.button_addPoint8);//////////////////////////////////////////////////////////////////////////////////////////////////////////////
                playButton.setVisibility(View.GONE);
                loadToArray(objectNr,1);
            }
            catch (Exception e) {

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Prieiga prie telefono duomenų suteikta", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Prieiga prie telefono duomenų atmesta", Toast.LENGTH_SHORT).show();
            }
        }
    }
}