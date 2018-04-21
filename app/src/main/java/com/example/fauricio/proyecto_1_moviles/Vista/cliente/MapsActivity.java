package com.example.fauricio.proyecto_1_moviles.Vista.cliente;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.fauricio.proyecto_1_moviles.Controlador.Controlador;
import com.example.fauricio.proyecto_1_moviles.R;
import com.example.fauricio.proyecto_1_moviles.Vista.cliente.dibujar_ruta.DrawRouteMaps;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Random r;
    private LatLng Ubicacion_Cliente;
    private Marker markerCliente;
    private Marker markerBus;
    private int id_ruta_user;
    private ArrayList<LatLng> paradas_ruta;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 0) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 2, locationListener);
                }
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        r = new Random();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        id_ruta_user = sharedPreferences.getInt("id_ruta_user",0);
        String ruta = Controlador.getInstance().get_ruta(String.valueOf(id_ruta_user));
        paradas_ruta  = new ArrayList<>();
        try {
            JSONObject r = new JSONObject(ruta);
            JSONArray paradas = r.getJSONArray("paradas");
            for(int i =0; i<paradas.length();i++){
                JSONObject parada = paradas.getJSONObject(i);
                paradas_ruta.add(new LatLng(parada.getDouble("latitud"), parada.getDouble("longitud")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Ubicacion_Cliente = new LatLng(location.getLatitude(), location.getLongitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ android.Manifest.permission.ACCESS_FINE_LOCATION }, 0);
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 2, locationListener);

            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Ubicacion_Cliente = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            actualizar_mapa();
        }

    }

//    private boolean runtime_permissions(){
//        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
//            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == 100){
//            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
//                enable_buttons();
//            }else{
//                runtime_permissions();
//            }
//        }
//    }

    public void actualizar_mapa() {

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        System.out.println(paradas_ruta);
        for(LatLng parada : paradas_ruta){
            builder.include(parada);
        }
        LatLngBounds bounds = builder.build();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.12);

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);

        mMap.animateCamera(cu);

        for(int i = 0; i<paradas_ruta.size()-1; i++){
            DrawRouteMaps.getInstance(MapsActivity.this).draw(paradas_ruta.get(i),paradas_ruta.get(i+1), mMap);
            mMap.addMarker(new MarkerOptions().position(paradas_ruta.get(i)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stop3)));
        }
        mMap.addMarker(new MarkerOptions().position(paradas_ruta.get(paradas_ruta.size()-1)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stop3)));

        markerCliente = mMap.addMarker(new MarkerOptions().position(Ubicacion_Cliente).icon(BitmapDescriptorFactory.fromResource(R.drawable.ubicacion)));
        markerBus = mMap.addMarker(new MarkerOptions().position(getBusLocation()).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));

        //GET LAT LNG DEL BUS Y ACTUALIZA
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            markerCliente.remove();
                            markerBus.remove();
                            markerCliente = mMap.addMarker(new MarkerOptions().position(Ubicacion_Cliente).icon(BitmapDescriptorFactory.fromResource(R.drawable.ubicacion)));
                            markerBus = mMap.addMarker(new MarkerOptions().position(getBusLocation()).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));

                        }
                    });
                }
            }
        }, 0, 5000);
    }

    public LatLng getBusLocation() {
        //AQUI VA EL REQUEST DE LA UBICACION DEL BUS
        LatLng location = null;
        String ruta = Controlador.getInstance().get_ruta(String.valueOf(id_ruta_user));
        try {
            JSONObject r = new JSONObject(ruta);
            Double lat = r.getDouble("latitud");
            Double lon = r.getDouble("longitud");
            location = new LatLng(lat,lon);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return location;
    }
}
