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

public class detalle_empresa_user extends AppCompatActivity {
    private int id;
    private TextView nombre,descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_empresa_user);
        nombre = findViewById(R.id.txt_nombre_empresa);
        descripcion = findViewById(R.id.txt_detalle_empresa);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        id = sharedPreferences.getInt("id_empresa_user",0);
        String empresa = Controlador.getInstance().get_empresa(String.valueOf(id));
        try {
            JSONObject json_empresa = new JSONObject(empresa);
            nombre.setText("Nombre: "+json_empresa.getString("nombre"));
            descripcion.setText("Descripción: "+json_empresa.getString("descripcion"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
