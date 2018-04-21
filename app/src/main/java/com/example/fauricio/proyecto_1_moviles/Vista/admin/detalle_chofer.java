package com.example.fauricio.proyecto_1_moviles.Vista.admin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.fauricio.proyecto_1_moviles.Controlador.Controlador;
import com.example.fauricio.proyecto_1_moviles.R;

import org.json.JSONException;
import org.json.JSONObject;

public class detalle_chofer extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private TextView nombre,apellido,placa,rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_chofer);
        toolbar = findViewById(R.id.toolbar_chofer);
        toolbar.setTitle(R.string.txt_detalle_chofer);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlanco));
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        nombre = findViewById(R.id.txt_nombre);
        apellido = findViewById(R.id.txt_apellido);
        placa = findViewById(R.id.txt_placa);
        rating = findViewById(R.id.txt_rating);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String id = sharedPreferences.getString("id_chofer_aux","");
        String nombre_aux = sharedPreferences.getString("nombre_chofer","");
        String apellido_aux = sharedPreferences.getString("apellido_chofer","");
        String obj = Controlador.getInstance().get_chofer_id(id);
        try {
            JSONObject u = new JSONObject(obj);
            String id_ch = u.getString("chofer");
            String ch = Controlador.getInstance().get_driver(id_ch);
            JSONObject chofer = new JSONObject(ch);
            nombre.setText(nombre_aux);
            apellido.setText(apellido_aux);
            placa.setText(chofer.getString("placa"));
            rating.setText(chofer.getString("rating"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
