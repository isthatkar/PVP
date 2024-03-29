package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class EnglishKaunoMariuRegioninisParkasACtivity extends AppCompatActivity {



    TextView playerPositionIstorija,
            playerPositionFaktai1,
            playerDurationIstorija,
            playerDurationFaktai1,
            playerPositionFaktai2,
            playerDurationFaktai2;
    SeekBar seekBarIstorija,
            seekBarFaktai1,
            seekBarFaktai2;
    ImageView btPlayIstorija,
            btPlayFaktai1,
            btPlayFaktai2,
            btPauseIstorija,
            btPauseFaktai1,
            btPauseFaktai2;

    MediaPlayer mediaPlayerIstorija,
            mediaPlayerFaktai1,
            mediaPlayerFaktai2;
    Handler handlerIstorija = new Handler();
    Handler handlerFaktai1 = new Handler();
    Handler handlerFaktai2 = new Handler();
    Runnable runnableIstorija,
            runnableFaktai1,
            runnableFaktai2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_kauno_mariu_regioninis_parkas);

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
        methodForHistory();
        methodForFacts1();
        methodForFacts2();
    }


    public void methodForHistory()
    {
        playerPositionIstorija = findViewById(R.id.regioninisIstorijaPlayerPosition);
        playerDurationIstorija = findViewById(R.id.regioninisIstorijaPlayerDuration);
        seekBarIstorija      = findViewById(R.id.regioninisIstorijaSeekBar);
        btPlayIstorija         = findViewById(R.id.regioninisIstorijaPlay);
        btPauseIstorija        = findViewById(R.id.regioninisIstorijaPause);



        mediaPlayerIstorija = MediaPlayer.create(this, R.raw.engkaunomariuregioninisistorija);

        runnableIstorija = new Runnable() {
            @Override
            public void run() {
                seekBarIstorija.setProgress(mediaPlayerIstorija.getCurrentPosition());
                handlerIstorija.postDelayed(this, 500);
            }
        };

        int duration = mediaPlayerIstorija.getDuration();
        String sDuration = convertFormat(duration);
        playerDurationIstorija.setText(sDuration);

        btPlayIstorija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlayIstorija.setVisibility(View.GONE);
                mediaPlayerIstorija.start();
                seekBarIstorija.setMax(mediaPlayerIstorija.getDuration());
                handlerIstorija.postDelayed(runnableIstorija, 0);
                btPauseIstorija.setVisibility(View.VISIBLE);
            }
        });

        btPauseIstorija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlayIstorija.setVisibility(View.VISIBLE);
                mediaPlayerIstorija.pause();
                handlerIstorija.removeCallbacks(runnableIstorija);
                handlerIstorija.postDelayed(runnableIstorija, 0);
                btPauseIstorija.setVisibility(View.GONE);
            }
        });

        seekBarIstorija.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayerIstorija.seekTo(progress);
                }
                playerPositionIstorija.setText(convertFormat(mediaPlayerIstorija.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayerIstorija.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btPlayIstorija.setVisibility(View.VISIBLE);
                mediaPlayerIstorija.seekTo(0);
                btPauseIstorija.setVisibility(View.GONE);

            }
        });
    }

    public void methodForFacts1()
    {
        playerPositionFaktai1 = findViewById(R.id.regioninisFaktai1PlayerPosition);
        playerDurationFaktai1 = findViewById(R.id.regioninisFaktai1PlayerDuration);
        seekBarFaktai1        = findViewById(R.id.regioninisFaktai1SeekBar);
        btPlayFaktai1         = findViewById(R.id.regioninisFaktai1Play);
        btPauseFaktai1        = findViewById(R.id.regioninisFaktai1Pause);

        mediaPlayerFaktai1 = MediaPlayer.create(this, R.raw.engkaunomariuregioninisfaktai);

        runnableFaktai1 = new Runnable() {
            @Override
            public void run() {
                seekBarFaktai1.setProgress(mediaPlayerFaktai1.getCurrentPosition());
                handlerFaktai1.postDelayed(this, 500);
            }
        };

        int duration = mediaPlayerFaktai1.getDuration();
        String sDuration = convertFormat(duration);
        playerDurationFaktai1.setText(sDuration);

        btPlayFaktai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlayFaktai1.setVisibility(View.GONE);
                mediaPlayerFaktai1.start();
                seekBarFaktai1.setMax(mediaPlayerFaktai1.getDuration());
                handlerFaktai1.postDelayed(runnableFaktai1, 0);
                btPauseFaktai1.setVisibility(View.VISIBLE);
            }
        });

        btPauseFaktai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlayFaktai1.setVisibility(View.VISIBLE);
                mediaPlayerFaktai1.pause();
                handlerFaktai1.removeCallbacks(runnableFaktai1);
                handlerFaktai1.postDelayed(runnableFaktai1, 0);
                btPauseFaktai1.setVisibility(View.GONE);
            }
        });

        seekBarFaktai1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayerFaktai1.seekTo(progress);
                }
                playerPositionFaktai1.setText(convertFormat(mediaPlayerFaktai1.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayerFaktai1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btPlayFaktai1.setVisibility(View.VISIBLE);
                mediaPlayerFaktai1.seekTo(0);
                btPauseFaktai1.setVisibility(View.GONE);
            }
        });

    }

    public void methodForFacts2()
    {
        playerPositionFaktai2 = findViewById(R.id.regioninisFaktai2PlayerPosition);
        playerDurationFaktai2 = findViewById(R.id.regioninisFaktai2PlayerDuration);
        seekBarFaktai2        = findViewById(R.id.regioninisFaktai2SeekBar);
        btPlayFaktai2        = findViewById(R.id.regioninisFaktai2Play);
        btPauseFaktai2        = findViewById(R.id.regioninisFaktai2Pause);

        mediaPlayerFaktai2 = MediaPlayer.create(this, R.raw.engkaunomariuregioninisfaktai2);

        runnableFaktai2 = new Runnable() {
            @Override
            public void run() {
                seekBarFaktai2.setProgress(mediaPlayerFaktai2.getCurrentPosition());
                handlerFaktai2.postDelayed(this, 500);
            }
        };

        int duration = mediaPlayerFaktai2.getDuration();
        String sDuration = convertFormat(duration);
        playerDurationFaktai2.setText(sDuration);

        btPlayFaktai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlayFaktai2.setVisibility(View.GONE);
                mediaPlayerFaktai2.start();
                seekBarFaktai2.setMax(mediaPlayerFaktai2.getDuration());
                handlerFaktai2.postDelayed(runnableFaktai2, 0);
                btPauseFaktai2.setVisibility(View.VISIBLE);
            }
        });

        btPauseFaktai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlayFaktai2.setVisibility(View.VISIBLE);
                mediaPlayerFaktai2.pause();
                handlerFaktai2.removeCallbacks(runnableFaktai2);
                handlerFaktai2.postDelayed(runnableFaktai2, 0);
                btPauseFaktai2.setVisibility(View.GONE);
            }
        });

        seekBarFaktai2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayerFaktai2.seekTo(progress);
                }
                playerPositionFaktai2.setText(convertFormat(mediaPlayerFaktai2.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayerFaktai2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btPlayFaktai2.setVisibility(View.VISIBLE);
                mediaPlayerFaktai2.seekTo(0);
                btPauseFaktai2.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onBackPressed(){
        if(mediaPlayerFaktai1.isPlaying()){
            mediaPlayerFaktai1.stop();
        }
        else if(mediaPlayerFaktai2.isPlaying()){
            mediaPlayerFaktai2.stop();
        }
        else
            mediaPlayerIstorija.stop();
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
    int objectNr=4;    //###############################################################   0 tik jacht klubui
    int ToObjectDistance=10000; // Distance to object (if this is more than actual distance, button wont show)
    public void showIfUnvisited()
    {
        Button playButton = (Button) findViewById(R.id.button_addPoint4);  //##########################################################     cia pakeisti
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
                Button playButton = (Button) findViewById(R.id.button_addPoint4);//////////////////////////////////////////////////////////////////////////////////////////////////////////////
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