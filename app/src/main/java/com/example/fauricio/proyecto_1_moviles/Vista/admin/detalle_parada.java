package com.example.fauricio.proyecto_1_moviles.Vista.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.fauricio.proyecto_1_moviles.Controlador.Controlador;
import com.example.fauricio.proyecto_1_moviles.R;

import org.json.JSONException;
import org.json.JSONObject;

public class detalle_parada extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private EditText nombre,latitud,longitud;
    private int id_parada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_parada);
        toolbar = findViewById(R.id.toolbar_parada);
        toolbar.setTitle(R.string.txt_detalle_parada);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlanco));
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        nombre = findViewById(R.id.et_nombre);
        latitud = findViewById(R.id.et_latitud);
        longitud = findViewById(R.id.et_longitud);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        id_parada = sharedPreferences.getInt("id_parada",0);
        String parada = Controlador.getInstance().get_parada(String.valueOf(id_parada));
        try {
            JSONObject json_parada = new JSONObject(parada);
            nombre.setText(json_parada.getString("nombre"));
            latitud.setText(json_parada.getString("latitud"));
            longitud.setText(json_parada.getString("longitud"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalle, menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.editar:
                //Toast.makeText(getApplicationContext(), "EDITAR_", Toast.LENGTH_SHORT).show();
                Controlador.getInstance().put_parada(String.valueOf(id_parada),nombre.getText().toString(),latitud.getText().toString(),longitud.getText().toString());
                Controlador.getInstance().actualizar_parada();
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.eliminar:
                //Toast.makeText(getApplicationContext(), "ELIMINAR", Toast.LENGTH_SHORT).show();
                String request = Controlador.getInstance().delete_parada(String.valueOf(id_parada));
                Controlador.getInstance().actualizar_parada();
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
