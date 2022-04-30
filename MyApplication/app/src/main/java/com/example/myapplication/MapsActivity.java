package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.example.myapplication.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private ActivityMapsBinding binding;
    private GoogleMap map;
    Button searchButton;
    ImageView typeMapsButton;
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
                PopupMenu difftype = new PopupMenu(MapsActivity.this, v);
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
                PopupMenu objectMenu = new PopupMenu(MapsActivity.this, v);
                objectMenu.getMenuInflater().inflate(R.menu.popup_menu, objectMenu.getMenu());
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

        mMap.addMarker(new MarkerOptions().position(jachtklubas).title("Jachtklubas").icon(BitmapDescriptorFactory.fromResource(R.drawable.jachtklubasicon)));
        mMap.addMarker(new MarkerOptions().position(pazaislis).title("Pažaislio vienuolynas").icon(BitmapDescriptorFactory.fromResource(R.drawable.pazaislisicon)));
        mMap.addMarker(new MarkerOptions().position(suneliskiuKalnas).title("Šuneliškių kalnas").icon(BitmapDescriptorFactory.fromResource(R.drawable.suneliskiuicon)));
        mMap.addMarker(new MarkerOptions().position(lakstingaluSlenis).title("Lakštingalų slėnis").icon(BitmapDescriptorFactory.fromResource(R.drawable.lakstingaluicon)));
        mMap.addMarker(new MarkerOptions().position(kaunoMariuRegioninisParkas).title("Kauno marių regioninis parkas").icon(BitmapDescriptorFactory.fromResource(R.drawable.regioninisicon)));
        mMap.addMarker(new MarkerOptions().position(meilesIlanka).title("Meilės įlanka").icon(BitmapDescriptorFactory.fromResource(R.drawable.loveicon)));
        mMap.addMarker(new MarkerOptions().position(apleistaStovykla).title("Kauno marių apleista stovykla").icon(BitmapDescriptorFactory.fromResource(R.drawable.apleistaicon)));
        mMap.addMarker(new MarkerOptions().position(gastilioniuAtodanga).title("Gastilionių atodanga").icon(BitmapDescriptorFactory.fromResource(R.drawable.suneliskiuicon)));
        mMap.addMarker(new MarkerOptions().position(rumsiskiuMuziejus).title("Rumšiškių liaudies buities muziejus").icon(BitmapDescriptorFactory.fromResource(R.drawable.liaudiesicon)));
        mMap.addMarker(new MarkerOptions().position(rumsiskiuPrieplauka).title("Rumšiškių prieplauka").icon(BitmapDescriptorFactory.fromResource(R.drawable.rumsiskiuicon)));
        mMap.addMarker(new MarkerOptions().position(kapitoniskiuPazintinisTakas).title("Kapitoniškių pažintinis takas").icon(BitmapDescriptorFactory.fromResource(R.drawable.lakstingaluicon)));
        mMap.addMarker(new MarkerOptions().position(mergakalnis).title("Mergakalnio apžvalgos aikštelė").icon(BitmapDescriptorFactory.fromResource(R.drawable.mergakalnioicon)));
        mMap.addMarker(new MarkerOptions().position(kruonioHAE).title("Kruonio HAE").icon(BitmapDescriptorFactory.fromResource(R.drawable.kruonioicon)));
        mMap.addMarker(new MarkerOptions().position(zigosIlanka).title("Žiglos įlanka").icon(BitmapDescriptorFactory.fromResource(R.drawable.kruonioicon)));
        mMap.addMarker(new MarkerOptions().position(skulpturuParkas).title("Skulptūrų parkas").icon(BitmapDescriptorFactory.fromResource(R.drawable.skulpturuicon)));
        mMap.addMarker(new MarkerOptions().position(ziegzdzriuTakas).title("Žiegždrių takas").icon(BitmapDescriptorFactory.fromResource(R.drawable.lakstingaluicon)));
        mMap.addMarker(new MarkerOptions().position(laumenuPazintinisTakas).title("Laumėnų pažintinis takas").icon(BitmapDescriptorFactory.fromResource(R.drawable.lakstingaluicon)));
        mMap.addMarker(new MarkerOptions().position(pakalniskiuPazintinisTakas).title("Pakalniškių pažintinis takas").icon(BitmapDescriptorFactory.fromResource(R.drawable.lakstingaluicon)));

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
                    case "Kauno marių apleista stovykla":
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