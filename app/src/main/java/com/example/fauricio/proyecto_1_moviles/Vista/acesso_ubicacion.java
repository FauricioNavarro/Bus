package com.example.fauricio.proyecto_1_moviles.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fauricio.proyecto_1_moviles.R;

public class acesso_ubicacion extends AppCompatActivity {
    private Button encender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acesso_ubicacion);
        encender = findViewById(R.id.btn_encender_gps);

    }

    public void Encender_gps(View view){

    }
}