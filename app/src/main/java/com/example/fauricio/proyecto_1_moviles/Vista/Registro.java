package com.example.fauricio.proyecto_1_moviles.Vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fauricio.proyecto_1_moviles.Controlador.gestor;
import com.example.fauricio.proyecto_1_moviles.R;

public class Registro extends AppCompatActivity {
    private EditText user,password,nombre,apellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        user = findViewById(R.id.et_correo_reg);
        password = findViewById(R.id.et_password_reg);
        nombre = findViewById(R.id.et_nombre_reg);
        apellido = findViewById(R.id.et_apellido_reg);
    }

    public void registro(View view){
        String user_aux = user.getText().toString();
        String pass_aux = password.getText().toString();
        String nomb_aux = nombre.getText().toString();
        String apellido_aux = apellido.getText().toString();
        if(!user_aux.equals("") && !pass_aux.equals("") && !nomb_aux.equals("") && !apellido_aux.equals("")){
            String result = gestor.getInstance().registrar(user_aux,pass_aux,nomb_aux,apellido_aux);
            if(result!=null){
                Toast.makeText(getApplicationContext(),"Registro exitoso", Toast.LENGTH_LONG).show();
                Limpiar();
            }else{
                Toast.makeText(getApplicationContext(),"Error al realizar registro", Toast.LENGTH_LONG).show();
                Limpiar();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Error al realizar registro", Toast.LENGTH_LONG).show();
            Limpiar();
        }
    }

    public void Limpiar(){
        user.setText("");
        password.setText("");
        nombre.setText("");
        apellido.setText("");
    }
}
