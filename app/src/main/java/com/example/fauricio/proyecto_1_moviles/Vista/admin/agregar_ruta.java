package com.example.fauricio.proyecto_1_moviles.Vista.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fauricio.proyecto_1_moviles.Controlador.gestor;
import com.example.fauricio.proyecto_1_moviles.R;

public class agregar_ruta extends AppCompatActivity {
    private EditText nombre,inicio,fin,costo,latitud,longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_ruta);
        nombre = findViewById(R.id.et_nombre_rt);
        inicio = findViewById(R.id.et_inicio_rt);
        fin = findViewById(R.id.et_final_rt);
        costo = findViewById(R.id.et_costo_rt);
        latitud = findViewById(R.id.et_latitud_rt);
        longitud = findViewById(R.id.et_longitud_rt);
    }

    public void agregar_ruta(View view){
        String nombre_aux = nombre.getText().toString();
        String inicio_aux = inicio.getText().toString();
        String fin_aux = fin.getText().toString();
        String costo_aux = costo.getText().toString();
        String lat = latitud.getText().toString();
        String lon = longitud.getText().toString();
        if(!nombre_aux.equals("") && !inicio_aux.equals("") && !fin_aux.equals("") && !costo_aux.equals("") && !lat.equals("") && !lon.equals("")){
            String request = gestor.getInstance().registrar_ruta(nombre_aux,inicio_aux,fin_aux,lat,lon,costo_aux);
            if(!request.equals(null)){
                gestor.getInstance().actualizar_ruta();
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
        inicio.setText("");
        fin.setText("");
        costo.setText("");
        latitud.setText("");
        longitud.setText("");
    }
}
