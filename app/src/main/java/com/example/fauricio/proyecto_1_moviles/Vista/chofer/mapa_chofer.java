package com.example.fauricio.proyecto_1_moviles.Vista.chofer;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fauricio.proyecto_1_moviles.Controlador.Controlador;
import com.example.fauricio.proyecto_1_moviles.R;

import org.json.JSONException;
import org.json.JSONObject;

public class mapa_chofer extends AppCompatActivity {

    private Button btn_empezar, btn_terminar;
    private TextView txt_estado;
    private BroadcastReceiver broadcastReceiver;
    private int id_ruta;
    private JSONObject ruta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_chofer);

        btn_empezar = findViewById(R.id.empezar);
        btn_terminar = findViewById(R.id.terminar);
        txt_estado = findViewById(R.id.txt_Estado);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        id_ruta = sharedPreferences.getInt("id_ruta_chofer",0);

        String ruta_aux = Controlador.getInstance().get_ruta(String.valueOf(id_ruta));
        try {
            ruta = new JSONObject(ruta_aux);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(!runtime_permissions())
            enable_buttons();
    }

    private void enable_buttons() {
        btn_empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent direcciones = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/?api=1&destination=9.961372,-84.048901"));
                Intent service = new Intent(getApplicationContext(), GPS_Service.class);
                startService(service);
                startActivity(direcciones);
            }
        });

        btn_terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GPS_Service.class);
                stopService(i);
            }
        });

    }

    private boolean runtime_permissions(){
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                enable_buttons();
            }else{
                runtime_permissions();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(broadcastReceiver==null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    txt_estado.setText(String.valueOf(intent.getExtras().get("msg")));
                    Double latitud = (Double) intent.getExtras().get("latitude");
                    Double longitud = (Double) intent.getExtras().get("longitude");
                    actualizar_ubicacion(latitud,longitud);
                }
            };
        }
        registerReceiver(broadcastReceiver, new IntentFilter("gps_service"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver!=null){
            unregisterReceiver(broadcastReceiver);
        }
    }

    public void actualizar_ubicacion(Double lat,Double lon){
        try {
            Controlador.getInstance().put_ruta(String.valueOf(id_ruta),
                    ruta.getString("nombre"),ruta.getString("costo"),ruta.getString("latitud_final"),
                    ruta.getString("longitud_final"),String.valueOf(lat),String.valueOf(lon), ruta.getJSONArray("paradas"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
