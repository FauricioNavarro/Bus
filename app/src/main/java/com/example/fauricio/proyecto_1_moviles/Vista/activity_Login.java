package com.example.fauricio.proyecto_1_moviles.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fauricio.proyecto_1_moviles.R;

public class activity_Login extends AppCompatActivity {
    private TextView usuario,contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        usuario = findViewById(R.id.et_username);
        contraseña = findViewById(R.id.et_password);
    }

    public void login(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void registro(View view){
        Intent intent = new Intent(getApplicationContext(),Registro.class);
        startActivity(intent);
    }

    public void consulta_contraseña(View view){
    }
}
