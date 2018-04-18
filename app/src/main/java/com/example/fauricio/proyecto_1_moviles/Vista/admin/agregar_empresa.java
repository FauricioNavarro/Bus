package com.example.fauricio.proyecto_1_moviles.Vista.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fauricio.proyecto_1_moviles.Controlador.gestor;
import com.example.fauricio.proyecto_1_moviles.R;

public class agregar_empresa extends AppCompatActivity {
    private EditText nombre,des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_empresa);
        nombre = findViewById(R.id.et_nombre_empresa);
        des = findViewById(R.id.et_detalle_empresa);
    }

    public void agregar_empresa(View view){
        String nomb_aux = nombre.getText().toString();
        String des_aux = des.getText().toString();

        if(!nomb_aux.equals("") && !des_aux.equals("")){
            String result = gestor.getInstance().registrar_empresa(nomb_aux,des_aux);
            if(result=="1"){
                Toast.makeText(getApplicationContext(),"Registro exitoso", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"Error al realizar registro", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Error al realizar registro", Toast.LENGTH_LONG).show();
        }
    }
}
