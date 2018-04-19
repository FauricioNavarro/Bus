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
import android.widget.Toast;

import com.example.fauricio.proyecto_1_moviles.R;

public class detalle_empresa extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_empresa);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = sharedPreferences.getInt("id_empresa",0);
        Toast.makeText(getApplicationContext(),String.valueOf(id),Toast.LENGTH_LONG);
        Toast.makeText(getApplicationContext(),"Hola",Toast.LENGTH_LONG);
        toolbar = findViewById(R.id.toolbar_empresa);
        toolbar.setTitle(R.string.txt_detalle_empresa);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlanco));
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
