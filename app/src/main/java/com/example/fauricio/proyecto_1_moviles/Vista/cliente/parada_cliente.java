package com.example.fauricio.proyecto_1_moviles.Vista.cliente;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fauricio.proyecto_1_moviles.Controlador.listParadaAdapter;
import com.example.fauricio.proyecto_1_moviles.Modelo.Parada;
import com.example.fauricio.proyecto_1_moviles.R;
import com.example.fauricio.proyecto_1_moviles.Vista.admin.agregar_parada;
import com.example.fauricio.proyecto_1_moviles.Vista.admin.detalle_parada;

import java.util.ArrayList;


public class parada_cliente extends Fragment {
    private View rootView;
    private ListView paradas;
    private listParadaAdapter adapter;
    private ArrayList<Parada> ArrayItem = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_parada_cliente,container,false);
        paradas = rootView.findViewById(R.id.LV_paradas_cliente);
        ArrayItem = new ArrayList<>();
        paradas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),detalle_parada.class);
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
            String msj = "Parada"+String.valueOf(i);
            ArrayItem.add(new Parada(i,msj));
        }
        adapter = new listParadaAdapter(ArrayItem, context);
        paradas.setAdapter(adapter);
    }
}
