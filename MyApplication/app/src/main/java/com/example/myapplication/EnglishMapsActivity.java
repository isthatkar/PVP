package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import com.example.myapplication.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class EnglishMapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String FILE_NAME = "example.txt";
    private ActivityMapsBinding binding;
    private GoogleMap map;
    Button searchButton;
    ImageView typeMapsButton;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private boolean mLocationPermissionsGranted = false;
    LocationPermissions enableLocationPermissions = new LocationPermissions();
    int[] intArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        typeMapsButton = (ImageView) findViewById(R.id.typeImage);

        View locationButton = ((View) findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        // position on right bottom
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rlp.setMargins(0,0,30,170);


        typeMapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu difftype = new PopupMenu(EnglishMapsActivity.this, v);
                difftype.getMenuInflater().inflate(R.menu.difftype_menu, difftype.getMenu());
                difftype.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.standart:
                                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                return true;
                            case R.id.satellite:
                                map.setMapType((GoogleMap.MAP_TYPE_SATELLITE));
                                return true;
                            case R.id.terrain:
                                map.setMapType((GoogleMap.MAP_TYPE_TERRAIN));
                                return true;
                            case R.id.hybrid:
                                map.setMapType((GoogleMap.MAP_TYPE_HYBRID));
                                return true;

                            default:
                                return false;
                        }
                    }
                });
                difftype.show();
            }
        });

        searchButton = (Button) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu objectMenu = new PopupMenu(EnglishMapsActivity.this, v);
                objectMenu.getMenuInflater().inflate(R.menu.enpopup, objectMenu.getMenu());
                objectMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.jachtklubas:
                                LatLng jachtklubas = new LatLng(54.885412, 24.024804);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(jachtklubas, 15));
                                return true;
                            case R.id.pazaislis:
                                LatLng pazaislis = new LatLng(54.876222, 24.021416);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(pazaislis, 15));
                                return true;
                            case R.id.suneliskiuKalnas:
                                LatLng suneliskiuKalnas = new LatLng(54.899165, 24.050468);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(suneliskiuKalnas, 15));
                                return true;
                            case R.id.lakstingaluSlenis:
                                LatLng lakstingaluSlenis = new LatLng(54.909123, 24.064016);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(lakstingaluSlenis, 15));
                                return true;
                            case R.id.kaunoMariuRegioninisParkas:
                                LatLng kaunoMariuRegioninisParkas = new LatLng(54.916187, 24.088728);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(kaunoMariuRegioninisParkas, 15));
                                return true;
                            case R.id.meilesIlanka:
                                LatLng meilesIlanka = new LatLng(54.897875, 24.126895);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(meilesIlanka, 15));
                                return true;
                            case R.id.apleistaStovykla:
                                LatLng apleistaStovykla = new LatLng(54.879261, 24.1531535);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(apleistaStovykla, 15));
                                return true;
                            case R.id.gastilioniuAtodanga:
                                LatLng gastilioniuAtodanga = new LatLng(54.871606, 24.150136);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(gastilioniuAtodanga, 15));
                                return true;
                            case R.id.rumsiskiuMuziejus:
                                LatLng rumsiskiuMuziejus = new LatLng(54.866331, 24.200702);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(rumsiskiuMuziejus, 15));
                                return true;
                            case R.id.rumsiskiuPrieplauka:
                                LatLng rumsiskiuPrieplauka = new LatLng(54.860661, 24.196769);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(rumsiskiuPrieplauka, 15));
                                return true;
                            case R.id.kapitoniskiuPazintinisTakas:
                                LatLng kapitoniskiuPazintinisTakas = new LatLng(54.859187, 24.213946);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(kapitoniskiuPazintinisTakas, 15));
                                return true;
                            case R.id.mergakalnis:
                                LatLng mergakalnis = new LatLng(54.824597, 24.243838);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(mergakalnis, 15));
                                return true;
                            case R.id.kruonioHAE:
                                LatLng kruonioHAE = new LatLng(54.799203, 24.247184);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(kruonioHAE, 15));
                                return true;
                            case R.id.zigosIlanka:
                                LatLng zigosIlanka = new LatLng(54.841290, 24.194681);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(zigosIlanka, 15));
                                return true;
                            case R.id.skulpturuParkas:
                                LatLng skulpturuParkas = new LatLng(54.858654, 24.114648);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(skulpturuParkas, 15));
                                return true;
                            case R.id.ziegzdzriuTakas:
                                LatLng ziegzdzriuTakas = new LatLng(54.889264, 24.076552);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(ziegzdzriuTakas, 15));
                                return true;
                            case R.id.laumenuPazintinisTakas:
                                LatLng laumenuPazintinisTakas = new LatLng(54.863047, 24.043927);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(laumenuPazintinisTakas, 15));
                                return true;
                            case R.id.pakalniskiuPazintinisTakas:
                                LatLng pakalniskiuPazintinisTakas = new LatLng(54.855207, 24.017669);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(pakalniskiuPazintinisTakas, 15));
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                objectMenu.show();
            }
        });


        enableLocationPermissions.getLocationPermission(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        addMarkers(map);
        enableLocationPermissions.enableLocation(map,this);

        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.retrostyle));
        float zoomLevel = 10.7f; //This goes up to 21
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(54.858654, 24.114648), zoomLevel));
    }
    public Bitmap setBmp(int flag, Bitmap src) {
        //makeSureNotZero();
        countPoints();
        if(getFlag(flag)==1)
        {
            // An added margin to the initial image
            int margin = 24;
            int halfMargin = margin / 2;
            // the glow radius
            int glowRadius = 20;
            // the glow color
            int glowColor = Color.rgb(64, 51, 255);
            // The original image to use
            // extract the alpha from the source image
            Bitmap alpha = src.extractAlpha();
            // The output bitmap (with the icon + glow)
            Bitmap bmp = Bitmap.createBitmap(src.getWidth() + margin,
                    src.getHeight() + margin, Bitmap.Config.ARGB_8888);
            // The canvas to paint on the image
            Canvas canvas = new Canvas(bmp);
            Paint paint = new Paint();
            paint.setColor(glowColor);
            // outer glow
            paint.setMaskFilter(new BlurMaskFilter(glowRadius, BlurMaskFilter.Blur.OUTER));
            canvas.drawBitmap(alpha, halfMargin, halfMargin, paint);
            // original icon
            canvas.drawBitmap(src, halfMargin, halfMargin, null);
            //((ImageView) findViewById(R.id.bmpImg)).setImageBitmap(bmp);
            return bmp;
        }
        return src;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionsGranted = true;
                    onMapReady(map);
                }
            }
        }
    }

    public void addMarkers(GoogleMap mMap) {
        LatLng jachtklubas = new LatLng(54.885412, 24.024804);
        LatLng pazaislis = new LatLng(54.876222, 24.021416);
        LatLng suneliskiuKalnas = new LatLng(54.899165, 24.050468);
        LatLng lakstingaluSlenis = new LatLng(54.909123, 24.064016);
        LatLng kaunoMariuRegioninisParkas = new LatLng(54.916187, 24.088728);
        LatLng meilesIlanka = new LatLng(54.897875, 24.126895);
        LatLng apleistaStovykla = new LatLng(54.879261, 24.153153);
        LatLng gastilioniuAtodanga = new LatLng(54.871606, 24.150136);
        LatLng rumsiskiuMuziejus = new LatLng(54.866331, 24.200702);
        LatLng rumsiskiuPrieplauka = new LatLng(54.860661, 24.196769);
        LatLng kapitoniskiuPazintinisTakas = new LatLng(54.859187, 24.213946);
        LatLng mergakalnis = new LatLng(54.824597, 24.243838);
        LatLng kruonioHAE = new LatLng(54.799203, 24.247184);
        LatLng zigosIlanka = new LatLng(54.841290, 24.194681);
        LatLng skulpturuParkas = new LatLng(54.858654, 24.114648);
        LatLng ziegzdzriuTakas = new LatLng(54.889264, 24.076552);
        LatLng laumenuPazintinisTakas = new LatLng(54.863047, 24.043927);
        LatLng pakalniskiuPazintinisTakas = new LatLng(54.855207, 24.017669);

        mMap.addMarker(new MarkerOptions().position(jachtklubas).title("Yach Club").icon(BitmapDescriptorFactory.fromBitmap(setBmp(0, BitmapFactory.decodeResource(getResources(),R.drawable.jachtklubasicon)))));
        mMap.addMarker(new MarkerOptions().position(pazaislis).title("Pažaislis monastery").icon(BitmapDescriptorFactory.fromBitmap(setBmp(1,BitmapFactory.decodeResource(getResources(),R.drawable.pazaislisicon)))));
        mMap.addMarker(new MarkerOptions().position(suneliskiuKalnas).title("Šuneliškės mountain").icon(BitmapDescriptorFactory.fromBitmap(setBmp(2,BitmapFactory.decodeResource(getResources(),R.drawable.suneliskiuicon)))));
        mMap.addMarker(new MarkerOptions().position(lakstingaluSlenis).title("Lakštingalos valley").icon(BitmapDescriptorFactory.fromBitmap(setBmp(3,BitmapFactory.decodeResource(getResources(),R.drawable.lakstingaluicon)))));
        mMap.addMarker(new MarkerOptions().position(kaunoMariuRegioninisParkas).title("Kauno marios regional park").icon(BitmapDescriptorFactory.fromBitmap(setBmp(4,BitmapFactory.decodeResource(getResources(),R.drawable.regioninisicon)))));
        mMap.addMarker(new MarkerOptions().position(meilesIlanka).title("Love bay").icon(BitmapDescriptorFactory.fromBitmap(setBmp(5,BitmapFactory.decodeResource(getResources(),R.drawable.loveicon)))));
        mMap.addMarker(new MarkerOptions().position(apleistaStovykla).title("Kauno marios abandoned camp").icon(BitmapDescriptorFactory.fromBitmap(setBmp(6,BitmapFactory.decodeResource(getResources(),R.drawable.apleistaicon)))));
        mMap.addMarker(new MarkerOptions().position(gastilioniuAtodanga).title("Gastilionai exposure").icon(BitmapDescriptorFactory.fromBitmap(setBmp(7,BitmapFactory.decodeResource(getResources(),R.drawable.suneliskiuicon)))));
        mMap.addMarker(new MarkerOptions().position(rumsiskiuMuziejus).title("Rumšiškės folk household museum").icon(BitmapDescriptorFactory.fromBitmap(setBmp(8,BitmapFactory.decodeResource(getResources(),R.drawable.liaudiesicon)))));
        mMap.addMarker(new MarkerOptions().position(rumsiskiuPrieplauka).title("Rumšiškės pier").icon(BitmapDescriptorFactory.fromBitmap(setBmp(9,BitmapFactory.decodeResource(getResources(),R.drawable.rumsiskiuicon)))));
        mMap.addMarker(new MarkerOptions().position(kapitoniskiuPazintinisTakas).title("Kapitoniškės cognitive path").icon(BitmapDescriptorFactory.fromBitmap(setBmp(10,BitmapFactory.decodeResource(getResources(),R.drawable.lakstingaluicon)))));
        mMap.addMarker(new MarkerOptions().position(mergakalnis).title("Mergakalnis viewpoint").icon(BitmapDescriptorFactory.fromBitmap(setBmp(11,BitmapFactory.decodeResource(getResources(),R.drawable.mergakalnioicon)))));
        mMap.addMarker(new MarkerOptions().position(kruonioHAE).title("Kruonis hydroelectric power plant").icon(BitmapDescriptorFactory.fromBitmap(setBmp(12,BitmapFactory.decodeResource(getResources(),R.drawable.kruonioicon)))));
        mMap.addMarker(new MarkerOptions().position(zigosIlanka).title("Žigla bay").icon(BitmapDescriptorFactory.fromBitmap(setBmp(13,BitmapFactory.decodeResource(getResources(),R.drawable.kruonioicon)))));
        mMap.addMarker(new MarkerOptions().position(skulpturuParkas).title("Skulptūrų park").icon(BitmapDescriptorFactory.fromBitmap(setBmp(14,BitmapFactory.decodeResource(getResources(),R.drawable.skulpturuicon)))));
        mMap.addMarker(new MarkerOptions().position(ziegzdzriuTakas).title("Žiegždrės path").icon(BitmapDescriptorFactory.fromBitmap(setBmp(15,BitmapFactory.decodeResource(getResources(),R.drawable.lakstingaluicon)))));
        mMap.addMarker(new MarkerOptions().position(laumenuPazintinisTakas).title("Laumėnai cognitive path").icon(BitmapDescriptorFactory.fromBitmap(setBmp(17,BitmapFactory.decodeResource(getResources(),R.drawable.lakstingaluicon)))));
        mMap.addMarker(new MarkerOptions().position(pakalniskiuPazintinisTakas).title("Pakalniškės cognitive path").icon(BitmapDescriptorFactory.fromBitmap(setBmp(18,BitmapFactory.decodeResource(getResources(),R.drawable.lakstingaluicon)))));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                Intent intent;
                switch (marker.getTitle()){
                    case "Yach Club":
                        intent = new Intent(EnglishMapsActivity.this, EnglishJachklubasActivity.class);
                        startActivity(intent);
                        break;
                    case "Pažaislis monastery":
                        intent = new Intent(EnglishMapsActivity.this, EnglishPazaislioVienuolynas.class);
                        startActivity(intent);
                        break;
                    case "Šuneliškės mountain":
                        intent = new Intent(EnglishMapsActivity.this, EnglishSuneliskesKalnasActivity.class);
                        startActivity(intent);
                        break;
                    case "Lakštingalos valley":
                        intent = new Intent(EnglishMapsActivity.this, EnglishLakstingaluSlenisActivity.class);
                        startActivity(intent);
                        break;
                    case "Kauno marios regional park":
                        intent = new Intent(EnglishMapsActivity.this, EnglishKaunoMariuRegioninisParkasACtivity.class);
                        startActivity(intent);
                        break;
                    case "Gastilionai exposure":
                        intent = new Intent(EnglishMapsActivity.this, EnglishGastilijonuAtodangaActivity.class);
                        startActivity(intent);
                        break;
                    case "Love bay":
                        intent = new Intent(EnglishMapsActivity.this, EnglishMeilesIlankaACtivity.class);
                        startActivity(intent);
                        break;
                    case "Kauno marios abandoned camp":
                        intent = new Intent(EnglishMapsActivity.this, EnglishKaunoMariuApleistaStovyklaActivity.class);
                        startActivity(intent);
                        break;
                    case "Rumšiškės folk household museum":
                        intent = new Intent(EnglishMapsActivity.this, EnglishRumsiskiuMuziejusActivity.class);
                        startActivity(intent);
                        break;
                    case "Rumšiškės pier":
                        intent = new Intent(EnglishMapsActivity.this, EnglishRumsiskiuPrieplaukaActivity.class);
                        startActivity(intent);
                        break;
                    case "Kapitoniškės cognitive path":
                        intent = new Intent(EnglishMapsActivity.this, EnglishKapitoniskiuPazintinisTakasEnglish.class);
                        startActivity(intent);
                        break;
                    case "Kruonis hydroelectric power plant":
                        intent = new Intent(EnglishMapsActivity.this, EnglishKruonioHAEActivity.class);
                        startActivity(intent);
                        break;
                    case "Žigla bay":
                        intent = new Intent(EnglishMapsActivity.this, EnglishZiglosIlankaActivity.class);
                        startActivity(intent);
                        break;
                    case "Skulptūrų park":
                        intent = new Intent(EnglishMapsActivity.this, EnglishSkulturuParkasActivity.class);
                        startActivity(intent);
                        break;
                    case "Žiegždrės path":
                        intent = new Intent(EnglishMapsActivity.this, EnglishZiegzdriuTakasEnglish.class);
                        startActivity(intent);
                        break;
                    case "Laumėnai cognitive path":
                        intent = new Intent(EnglishMapsActivity.this, EnglishLaumenuPazintinisTakasActivity.class);
                        startActivity(intent);
                        break;
                    case "Pakalniškės cognitive path":
                        intent = new Intent(EnglishMapsActivity.this, EnglishPakalniskiuPazintinisTakas.class);
                        startActivity(intent);
                        break;
                    case "Mergakalnis viewpoint":
                        intent = new Intent(EnglishMapsActivity.this, EnglishMergakalnioApzvalgosAiksteActivity.class);
                        startActivity(intent);
                        break;

                }
                return false;
            }
        });
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


}