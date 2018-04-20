package com.example.fauricio.proyecto_1_moviles.Vista.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.fauricio.proyecto_1_moviles.Controlador.Controlador;
import com.example.fauricio.proyecto_1_moviles.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class detalle_ruta extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private EditText nombre,inicio,fin,costo,latitud,longitud;
    private int id_ruta;
    private JSONArray paradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ruta);
        toolbar = findViewById(R.id.toolbar_ruta);
        toolbar.setTitle(R.string.txt_detalle_ruta);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlanco));
        nombre = findViewById(R.id.et_nombre_rt);
        inicio = findViewById(R.id.et_inicio_rt);
        fin = findViewById(R.id.et_final_rt);
        costo = findViewById(R.id.et_costo_rt);
        latitud = findViewById(R.id.et_latitud_rt);
        longitud = findViewById(R.id.et_longitud_rt);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        id_ruta = sharedPreferences.getInt("id_ruta",0);
        String ruta = Controlador.getInstance().get_ruta(String.valueOf(id_ruta));
        try {
            JSONObject json_ruta = new JSONObject(ruta);
            nombre.setText(json_ruta.getString("nombre"));
            inicio.setText(json_ruta.getString("inicio"));
            fin.setText(json_ruta.getString("final"));
            costo.setText(json_ruta.getString("costo"));
            latitud.setText(json_ruta.getString("latitud"));
            longitud.setText(json_ruta.getString("longitud"));
            paradas =json_ruta.getJSONArray("paradas");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setSupportActionBar(toolbar);
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
                Controlador.getInstance().put_ruta(String.valueOf(id_ruta),nombre.getText().toString(),costo.getText().toString(),inicio.getText().toString(),fin.getText().toString(),latitud.getText().toString(),longitud.getText().toString(),paradas);
                Controlador.getInstance().actualizar_ruta();
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.eliminar:
                //Toast.makeText(getApplicationContext(), "ELIMINAR", Toast.LENGTH_SHORT).show();
                String request = Controlador.getInstance().delete_ruta(String.valueOf(id_ruta));
                Controlador.getInstance().actualizar_ruta();
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
