package com.example.fauricio.proyecto_1_moviles.Vista.admin;
import com.example.fauricio.proyecto_1_moviles.Controlador.listChoferAdapter;
import com.example.fauricio.proyecto_1_moviles.Modelo.*;

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

import com.example.fauricio.proyecto_1_moviles.R;

import java.util.ArrayList;


/**
 */
public class ChoferFragment extends Fragment {
    private View rootview;
    private FloatingActionButton nuevo_chofer;
    private ListView choferes;
    private listChoferAdapter adapter;
    private ArrayList<item> ArrayItem = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_chofer,container,false);
        choferes = rootview.findViewById(R.id.LV_choferes);
        nuevo_chofer = rootview.findViewById(R.id.FB_agregar);
        ArrayItem = new ArrayList<>();

        nuevo_chofer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),agregar_choferActivity.class);
                startActivity(intent);
            }
        });

        choferes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),detalle_chofer.class);
                startActivity(intent);
            }
        });
        cargarLista(rootview.getContext());
        return rootview;
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
            String msj = "Chofer"+String.valueOf(i);
            ArrayItem.add(new item(msj,msj,msj));
        }
        adapter = new listChoferAdapter(ArrayItem, context);
        choferes.setAdapter(adapter);
    }
}
