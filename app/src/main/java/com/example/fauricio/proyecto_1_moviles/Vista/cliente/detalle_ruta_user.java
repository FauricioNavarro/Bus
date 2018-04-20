package com.example.fauricio.proyecto_1_moviles.Vista.cliente;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.fauricio.proyecto_1_moviles.Controlador.Controlador;
import com.example.fauricio.proyecto_1_moviles.R;

import org.json.JSONException;
import org.json.JSONObject;

public class detalle_ruta_user extends AppCompatActivity {
    private TextView nombre,inicio,fin,costo,latitud,longitud;
    private int id_ruta_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ruta_user);
        nombre = findViewById(R.id.txt_nombre_rt);
        inicio = findViewById(R.id.txt_inicio_rt);
        fin = findViewById(R.id.txt_final_rt);
        costo = findViewById(R.id.txt_costo_rt);
        latitud = findViewById(R.id.txt_latitud_rt);
        longitud = findViewById(R.id.txt_longitud_rt);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        id_ruta_user = sharedPreferences.getInt("id_ruta_user",0);
        String ruta_user = Controlador.getInstance().get_ruta(String.valueOf(id_ruta_user));
        //Toast.makeText(getApplicationContext(),ruta_user,Toast.LENGTH_LONG).show();
        try {
            JSONObject json_ruta = new JSONObject(ruta_user);
            nombre.setText("Nombre: "+json_ruta.getString("nombre"));
            inicio.setText("Lugar inicial: "+json_ruta.getString("latitud_final"));
            fin.setText("Destino: "+json_ruta.getString("longitud_final"));
            costo.setText("Costo: "+json_ruta.getString("costo"));
            latitud.setText("Latitud: "+json_ruta.getString("latitud"));
            longitud.setText("Longitud: "+json_ruta.getString("longitud"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void mapa(View view){
        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
    }
}
