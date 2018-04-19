package com.example.fauricio.proyecto_1_moviles.Vista.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fauricio.proyecto_1_moviles.R;

public class detalle_parada extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private EditText nombre,inicio,fin,costo,latitud,longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_parada);
        toolbar = findViewById(R.id.toolbar_parada);
        toolbar.setTitle(R.string.txt_detalle_parada);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlanco));
        setSupportActionBar(toolbar);
        nombre = findViewById(R.id.et_nombre_rt);
        inicio = findViewById(R.id.et_inicio_rt);
        fin = findViewById(R.id.et_final_rt);
        costo = findViewById(R.id.et_costo_rt);
        latitud = findViewById(R.id.et_latitud_rt);
        longitud = findViewById(R.id.et_longitud_rt);
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
                Toast.makeText(getApplicationContext(), "EDITAR_", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.eliminar:
                Toast.makeText(getApplicationContext(), "ELIMINAR", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
