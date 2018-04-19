package com.example.fauricio.proyecto_1_moviles.Vista.cliente;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.fauricio.proyecto_1_moviles.R;

public class Main_cliente extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_empresa_cliente:
                    transaction.replace(R.id.frame_contenedor_cliente,new empresa_cliente()).commit();
                    return true;
                case R.id.navigation_rutas_cliente:
                    transaction.replace(R.id.frame_contenedor_cliente,new ruta_cliente()).commit();
                    return true;
                case R.id.navigation_paradas_cliente:
                    transaction.replace(R.id.frame_contenedor_cliente,new parada_cliente()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cliente);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_contenedor_cliente,new empresa_cliente()).commit();
        BottomNavigationView navigation_cliente = (BottomNavigationView) findViewById(R.id.navigation_cliente);
        navigation_cliente.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
