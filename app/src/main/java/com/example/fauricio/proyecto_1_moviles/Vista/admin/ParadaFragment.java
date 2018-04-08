package com.example.fauricio.proyecto_1_moviles.Vista.admin;

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

import com.example.fauricio.proyecto_1_moviles.Controlador.listParadaAdapter;
import com.example.fauricio.proyecto_1_moviles.Modelo.Parada;
import com.example.fauricio.proyecto_1_moviles.R;

import java.util.ArrayList;


public class ParadaFragment extends Fragment {
    private View rootView;
    private FloatingActionButton nueva_parada;
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
        rootView = inflater.inflate(R.layout.fragment_parada,container,false);
        paradas = rootView.findViewById(R.id.LV_paradas);
        nueva_parada = rootView.findViewById(R.id.FB_agregar);
        ArrayItem = new ArrayList<>();
        nueva_parada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),agregar_parada.class);
                startActivity(intent);
            }
        });

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
