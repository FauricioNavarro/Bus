package com.example.fauricio.proyecto_1_moviles.Vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fauricio.proyecto_1_moviles.Controlador.gestor;
import com.example.fauricio.proyecto_1_moviles.R;

public class Registro extends AppCompatActivity {
    private EditText user,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        user = findViewById(R.id.et_correo_reg);
        password = findViewById(R.id.et_password_reg);
    }

    public void registro(View view){
        String user_aux = user.getText().toString();
        String pass_aux = password.getText().toString();

        if(!user_aux.equals("") && !pass_aux.equals("")){
            String result = gestor.getInstance().registrar(user_aux,pass_aux);
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
