package com.example.fauricio.proyecto_1_moviles.Vista.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fauricio.proyecto_1_moviles.Controlador.gestor;
import com.example.fauricio.proyecto_1_moviles.R;

public class agregar_parada extends AppCompatActivity {
    private EditText nombre,lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_parada);
        nombre = findViewById(R.id.et_nombre);
        lat = findViewById(R.id.et_latitud);
        lon = findViewById(R.id.et_longitud);
    }

    public void agregar_parada(View view){
        String nombre_aux = nombre.getText().toString();
        String lat_aux = lat.getText().toString();
        String lon_aux = lon.getText().toString();
        if(!nombre_aux.equals("") && !lat_aux.equals("") && !lon_aux.equals("")){
            String request = gestor.getInstance().registrar_parada(nombre_aux,lat_aux,lon_aux);
            if(!request.equals(null)){
                gestor.getInstance().actualizar_parada();
                Toast.makeText(getApplicationContext(),"Registro exitoso", Toast.LENGTH_LONG).show();
                Limpiar();
            }else {
                Toast.makeText(getApplicationContext(), "Error al realizar registro", Toast.LENGTH_LONG).show();
                Limpiar();
            }
        }else {
            Toast.makeText(getApplicationContext(), "Error al realizar registro", Toast.LENGTH_LONG).show();
            Limpiar();
        }
    }

    public void Limpiar(){
        nombre.setText("");
        lat.setText("");
        lon.setText("");
    }
}
