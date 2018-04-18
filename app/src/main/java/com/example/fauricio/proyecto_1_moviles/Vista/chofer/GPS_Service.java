package com.example.fauricio.proyecto_1_moviles.Vista.chofer;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

public class GPS_Service extends Service {

    private LocationListener listener;
    private LocationManager locationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // AQUI SE HACE EL REQUEST
                Log.i("ubicacion",location.getLatitude()+" "+ location.getLongitude());
                Intent i = new Intent("gps_service");
                i.putExtra("msg", "La ruta ha empezado y se está compartiendo su ubicación. ");
                i.putExtra("latitude", location.getLatitude());
                i.putExtra("longitude",  location.getLongitude());
                sendBroadcast(i);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 300,0,listener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(locationManager!= null){
            locationManager.removeUpdates(listener);
            Intent i = new Intent("gps_service");
            i.putExtra("msg", "La ruta no ha empezado.");
            sendBroadcast(i);
        }
    }
}
