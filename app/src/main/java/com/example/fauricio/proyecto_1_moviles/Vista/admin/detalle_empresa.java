package com.example.fauricio.proyecto_1_moviles.Vista.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.fauricio.proyecto_1_moviles.Controlador.gestor;
import com.example.fauricio.proyecto_1_moviles.R;

import org.json.JSONException;
import org.json.JSONObject;

public class detalle_empresa extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private int id;
    private EditText nombre,descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_empresa);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        nombre = findViewById(R.id.et_nombre_empresa);
        descripcion = findViewById(R.id.et_detalle_empresa);
        id = sharedPreferences.getInt("id_empresa",0);
        String empresa = gestor.getInstance().get_empresa(String.valueOf(id));
        Log.i("empresa",empresa);
        try {
            JSONObject json_empresa = new JSONObject(empresa);
            nombre.setText(json_empresa.getString("nombre"));
            descripcion.setText(json_empresa.getString("descripcion"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                //Toast.makeText(getApplicationContext(), "EDITAR_", Toast.LENGTH_SHORT).show();
                gestor.getInstance().put_empresa(nombre.getText().toString(),descripcion.getText().toString(),String.valueOf(id));
                gestor.getInstance().actualizar_empresa();
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.eliminar:
                //Toast.makeText(getApplicationContext(), "ELIMINAR", Toast.LENGTH_SHORT).show();
                String request = gestor.getInstance().delete_empresa(String.valueOf(id));
                gestor.getInstance().actualizar_empresa();
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
