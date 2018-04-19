package com.example.fauricio.proyecto_1_moviles.Vista.chofer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fauricio.proyecto_1_moviles.Controlador.gestor;
import com.example.fauricio.proyecto_1_moviles.Controlador.listRutaAdapter;
import com.example.fauricio.proyecto_1_moviles.Modelo.Ruta;
import com.example.fauricio.proyecto_1_moviles.R;

import java.util.ArrayList;

public class Main_chofer extends AppCompatActivity {
    private ListView rutas;
    private listRutaAdapter adapter;
    private ArrayList<Ruta> ArrayItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chofer);
        rutas = findViewById(R.id.LV_rutas);
        ArrayItem = new ArrayList<>();
        String m = String.valueOf(gestor.getInstance().getId_user());
        Toast.makeText(getApplicationContext(), m,Toast.LENGTH_LONG).show();

        rutas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),mapa_chofer.class);
                startActivity(intent);
            }
        });
        cargarLista(this);
    }

    public void cargarLista(Context context){
        for(int i = 0 ; i<12;i++){
            String msj = "Ruta"+String.valueOf(i);
            ArrayItem.add(new Ruta(i,msj,"Lugar Inicial"," <-> Lugar Final",1));
        }
        adapter = new listRutaAdapter(ArrayItem, context);
        rutas.setAdapter(adapter);
    }
}
