package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myapplication.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addMarkers(mMap);
    }

    public void addMarkers(GoogleMap mMap){
        LatLng jachtklubas = new LatLng(54.885412, 24.024804);
        LatLng pazaislis = new LatLng(54.876222, 24.021416);
        LatLng suneliskiuKalnas = new LatLng(54.899165, 24.050468);
        LatLng lakstingaluSlenis = new LatLng(54.909123, 24.064016);
        LatLng kaunoMariuRegioninisParkas = new LatLng(54.916187, 24.088728);
        LatLng meilesIlanka = new LatLng(54.89165, 24.126413);
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
        LatLng laumenuParkas = new LatLng(54.874337, 24.049471);
        LatLng laumenuPazintinisTakas = new LatLng(54.863047, 24.043927);
        LatLng pakalniskiuPazintinisTakas = new LatLng(54.855207, 24.017669);

        mMap.addMarker(new MarkerOptions().position(jachtklubas).title("Jachklubas"));
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
        mMap.addMarker(new MarkerOptions().position(zigosIlanka).title("Žigos įlanka"));
        mMap.addMarker(new MarkerOptions().position(skulpturuParkas).title("Skulptūrų parkas"));
        mMap.addMarker(new MarkerOptions().position(ziegzdzriuTakas).title("Žiegždrių takas"));
        mMap.addMarker(new MarkerOptions().position(laumenuParkas).title("Laumenų parkas"));
        mMap.addMarker(new MarkerOptions().position(laumenuPazintinisTakas).title("Laumenų pažintinis takas"));
        mMap.addMarker(new MarkerOptions().position(pakalniskiuPazintinisTakas).title("Pakalniškių pažintinis takas"));


    }
}