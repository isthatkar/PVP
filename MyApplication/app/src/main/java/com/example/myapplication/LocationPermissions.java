package com.example.myapplication;

import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import com.google.android.gms.maps.GoogleMap;


public class LocationPermissions{

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private boolean mLocationPermissionsGranted = false;

    public void enableLocation(GoogleMap googleMap, LithuanianMapsActivity lithuanianMapsActivity)
    {
        if (ActivityCompat.checkSelfPermission(lithuanianMapsActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(lithuanianMapsActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        googleMap.setMyLocationEnabled(true);
    }

    public void getLocationPermission(LithuanianMapsActivity lithuanianMapsActivity){
        String[] permissions = {android.Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ActivityCompat.checkSelfPermission(lithuanianMapsActivity.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.checkSelfPermission(lithuanianMapsActivity.getApplicationContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
            }else{
                ActivityCompat.requestPermissions(lithuanianMapsActivity,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(lithuanianMapsActivity,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }
    public void enableLocation(GoogleMap googleMap, EnglishMapsActivity lithuanianMapsActivity)
    {
        if (ActivityCompat.checkSelfPermission(lithuanianMapsActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(lithuanianMapsActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        googleMap.setMyLocationEnabled(true);
    }

    public void getLocationPermission(EnglishMapsActivity lithuanianMapsActivity){
        String[] permissions = {android.Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ActivityCompat.checkSelfPermission(lithuanianMapsActivity.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.checkSelfPermission(lithuanianMapsActivity.getApplicationContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
            }else{
                ActivityCompat.requestPermissions(lithuanianMapsActivity,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(lithuanianMapsActivity,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }
}
