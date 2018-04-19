package com.example.fauricio.proyecto_1_moviles.Vista.cliente;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fauricio.proyecto_1_moviles.Controlador.listRutaAdapter;
import com.example.fauricio.proyecto_1_moviles.Modelo.Ruta;
import com.example.fauricio.proyecto_1_moviles.R;
import com.example.fauricio.proyecto_1_moviles.Vista.admin.detalle_ruta;

import java.util.ArrayList;


public class ruta_cliente extends Fragment {
    private View rootView;
    private FloatingActionButton nueva_ruta;
    private ListView rutas;
    private listRutaAdapter adapter;
    private ArrayList<Ruta> ArrayItem = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ruta_cliente,container,false);
        rutas = rootView.findViewById(R.id.LV_rutas_cliente);
        ArrayItem = new ArrayList<>();

        rutas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),MapsActivity.class);
                startActivity(intent);
            }
        });
        cargarLista(rootView.getContext());
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
