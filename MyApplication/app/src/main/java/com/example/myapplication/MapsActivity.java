package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.example.myapplication.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private ActivityMapsBinding binding;
    private GoogleMap map;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private boolean mLocationPermissionsGranted = false;
    LocationPermissions enableLocationPermissions = new LocationPermissions();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        enableLocationPermissions.getLocationPermission(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        addMarkers(map);
        enableLocationPermissions.enableLocation(map,this);

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

        mMap.addMarker(new MarkerOptions().position(jachtklubas).title("Jachtklubas"));
        mMap.addMarker(new MarkerOptions().position(pazaislis).title("Pažaislio vienuolynas"));
        mMap.addMarker(new MarkerOptions().position(suneliskiuKalnas).title("Šuneliškių kalnas"));
        mMap.addMarker(new MarkerOptions().position(lakstingaluSlenis).title("Lakštingalų slėnis"));
        mMap.addMarker(new MarkerOptions().position(kaunoMariuRegioninisParkas).title("Kauno marių regioninis parkas"));
        mMap.addMarker(new MarkerOptions().position(meilesIlanka).title("Meilės įlanka"));
        mMap.addMarker(new MarkerOptions().position(apleistaStovykla).title("Kauno marių apleista stovyklą"));
        mMap.addMarker(new MarkerOptions().position(gastilioniuAtodanga).title("Gastilionių atodanga"));
        mMap.addMarker(new MarkerOptions().position(rumsiskiuMuziejus).title("Rumšiškių liaudies buities muziejus"));
        mMap.addMarker(new MarkerOptions().position(rumsiskiuPrieplauka).title("Rumšiškių prieplauka"));
        mMap.addMarker(new MarkerOptions().position(kapitoniskiuPazintinisTakas).title("Kapitoniškių pažintinis takas"));
        mMap.addMarker(new MarkerOptions().position(mergakalnis).title("Mergakalnio apžvalgos aikštelė"));
        mMap.addMarker(new MarkerOptions().position(kruonioHAE).title("Kruonio HAE"));
        mMap.addMarker(new MarkerOptions().position(zigosIlanka).title("Žiglos įlanka"));
        mMap.addMarker(new MarkerOptions().position(skulpturuParkas).title("Skulptūrų parkas"));
        mMap.addMarker(new MarkerOptions().position(ziegzdzriuTakas).title("Žiegždrių takas"));
        mMap.addMarker(new MarkerOptions().position(laumenuPazintinisTakas).title("Laumėnų pažintinis takas"));
        mMap.addMarker(new MarkerOptions().position(pakalniskiuPazintinisTakas).title("Pakalniškių pažintinis takas"));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                Intent intent;
                switch (marker.getTitle()){
                    case "Jachtklubas":
                        intent = new Intent(MapsActivity.this, JachklubasActivity.class);
                        startActivity(intent);
                        break;
                    case "Pažaislio vienuolynas":
                        intent = new Intent(MapsActivity.this, PazaislioActivity.class);
                        startActivity(intent);
                        break;
                    case "Šuneliškių kalnas":
                        intent = new Intent(MapsActivity.this, SuneliskiuActivity.class);
                        startActivity(intent);
                        break;
                    case "Lakštingalų slėnis":
                        intent = new Intent(MapsActivity.this, LakstingaluActivity.class);
                        startActivity(intent);
                        break;
                    case "Kauno marių regioninis parkas":
                        intent = new Intent(MapsActivity.this, RegioninisActivity.class);
                        startActivity(intent);
                        break;
                    case "Gastilionių atodanga":
                        intent = new Intent(MapsActivity.this, GastilioniuActivity.class);
                        startActivity(intent);
                        break;
                    case "Meilės įlanka":
                        intent = new Intent(MapsActivity.this, MeilesActivity.class);
                        startActivity(intent);
                        break;
                    case "Kauno marių apleista stovyklą":
                        intent = new Intent(MapsActivity.this, StovyklaActivity.class);
                        startActivity(intent);
                        break;
                    case "Rumšiškių liaudies buities muziejus":
                        intent = new Intent(MapsActivity.this, RumsiskiuMuziejusActivity.class);
                        startActivity(intent);
                        break;
                    case "Rumšiškių prieplauka":
                        intent = new Intent(MapsActivity.this, RumsiskiuPrieplaukaActivity.class);
                        startActivity(intent);
                        break;
                    case "Kapitoniškių pažintinis takas":
                        intent = new Intent(MapsActivity.this, KapitoniskiuActivity.class);
                        startActivity(intent);
                        break;
                    case "Kruonio HAE":
                        intent = new Intent(MapsActivity.this, KruonioActivity.class);
                        startActivity(intent);
                        break;
                    case "Žiglos įlanka":
                        intent = new Intent(MapsActivity.this, ZiglosActivity.class);
                        startActivity(intent);
                        break;
                    case "Skulptūrų parkas":
                        intent = new Intent(MapsActivity.this, SkulpturuActivity.class);
                        startActivity(intent);
                        break;
                    case "Žiegždrių takas":
                        intent = new Intent(MapsActivity.this, ZiegzdriuActivity.class);
                        startActivity(intent);
                        break;
                    case "Laumėnų pažintinis takas":
                        intent = new Intent(MapsActivity.this, LaumenuTakasActivity.class);
                        startActivity(intent);
                        break;
                    case "Pakalniškių pažintinis takas":
                        intent = new Intent(MapsActivity.this, PakalniskiuActivity.class);
                        startActivity(intent);
                        break;
                    case "Mergakalnio apžvalgos aikštelė":
                        intent = new Intent(MapsActivity.this, MergakalnioActivity.class);
                        startActivity(intent);
                        break;

                }
                return false;
            }
        });
    }
}