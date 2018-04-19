package com.example.fauricio.proyecto_1_moviles.Vista.cliente;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.example.fauricio.proyecto_1_moviles.R;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.example.fauricio.proyecto_1_moviles.Vista.cliente.dibujar_ruta.DrawRouteMaps;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Random r;
    private LatLng Ubicacion_Cliente;
    private Marker markerCliente;
    private Marker markerBus;


    ArrayList<Double[]> puntosRuta = new ArrayList<>(
            Arrays.asList(new Double[]{9.977357, -84.012290},new Double[]{9.968604, -84.033895},
                    new Double[]{9.945525, -84.055682},new Double[]{9.940985, -84.065143},
                    new Double[]{9.935122, -84.070505},new Double[]{9.935630, -84.077004}));


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 0) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.ACCESS_FINE_LOCATION }, 0);
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 2, locationListener);

            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Ubicacion_Cliente = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            actualizar_mapa();
        }

    }

    public void actualizar_mapa() {

        final ArrayList<LatLng> ruta = new ArrayList<>();
        ruta.add(new LatLng(9.980794, -84.023045));
        ruta.add(new LatLng(9.977357, -84.012290));
        ruta.add(new LatLng(9.968604, -84.033895));
        ruta.add(new LatLng(9.945525, -84.055682));
        ruta.add(new LatLng(9.940985, -84.065143));
        ruta.add(new LatLng(9.935122, -84.070505));
        ruta.add(new LatLng(9.935630, -84.077004));
        ruta.add(new LatLng(9.935622, -84.076650));


        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(LatLng parada : ruta){
            builder.include(parada);
        }
        LatLngBounds bounds = builder.build();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.12);

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);

        mMap.animateCamera(cu);

        for(int i = 0; i<ruta.size()-2; i++){
            DrawRouteMaps.getInstance(MapsActivity.this).draw(ruta.get(i),ruta.get(i+1), mMap);
            mMap.addMarker(new MarkerOptions().position(ruta.get(i)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stop3)));
        }
        mMap.addMarker(new MarkerOptions().position(ruta.get(ruta.size()-1)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stop3)));

        markerCliente = mMap.addMarker(new MarkerOptions().position(Ubicacion_Cliente).icon(BitmapDescriptorFactory.fromResource(R.drawable.ubicacion)));
        markerBus = mMap.addMarker(new MarkerOptions().position(getBusLocation()).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));

        //GET LAT LNG DEL BUS Y ACTUALIZA
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
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

        Double[] escogido = puntosRuta.get(r.nextInt(puntosRuta.size()));

        return new LatLng(escogido[0],escogido[1]);
    }
}

