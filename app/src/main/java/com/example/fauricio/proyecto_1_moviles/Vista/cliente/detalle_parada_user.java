package com.example.fauricio.proyecto_1_moviles.Vista.cliente;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.fauricio.proyecto_1_moviles.Controlador.Controlador;
import com.example.fauricio.proyecto_1_moviles.R;

import org.json.JSONException;
import org.json.JSONObject;

public class detalle_parada_user extends AppCompatActivity {
    private TextView nombre,latitud,longitud;
    private int id_parada_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_parada_user);
        nombre = findViewById(R.id.txt_nombre);
        latitud = findViewById(R.id.txt_latitud);
        longitud = findViewById(R.id.txt_longitud);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        id_parada_user = sharedPreferences.getInt("id_parada_user",0);
        String parada = Controlador.getInstance().get_parada(String.valueOf(id_parada_user));
        try {
            JSONObject json_parada = new JSONObject(parada);
            nombre.setText("Nombre: "+json_parada.getString("nombre"));
            latitud.setText("Latitud: "+json_parada.getString("latitud"));
            longitud.setText("Longitud: "+json_parada.getString("longitud"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
