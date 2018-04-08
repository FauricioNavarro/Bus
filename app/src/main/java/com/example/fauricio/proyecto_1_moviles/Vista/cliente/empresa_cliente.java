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

import com.example.fauricio.proyecto_1_moviles.Controlador.listEmpresaAdapter;
import com.example.fauricio.proyecto_1_moviles.Modelo.Empresa;
import com.example.fauricio.proyecto_1_moviles.R;
import com.example.fauricio.proyecto_1_moviles.Vista.admin.agregar_empresa;
import com.example.fauricio.proyecto_1_moviles.Vista.admin.detalle_empresa;

import java.util.ArrayList;

public class empresa_cliente extends Fragment {
    private View rootView;
    private FloatingActionButton nueva_empresa;
    private ListView empresas;
    private listEmpresaAdapter adapter;
    private ArrayList<Empresa> ArrayItem = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_empresa_cliente,container,false);
        empresas = rootView.findViewById(R.id.LV_empresas_cliente);
        ArrayItem = new ArrayList<>();

        empresas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),detalle_empresa.class);
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
            String msj = "Empresa"+String.valueOf(i);
            ArrayItem.add(new Empresa(i,msj,"Descripción"));
        }
        adapter = new listEmpresaAdapter(ArrayItem, context);
        empresas.setAdapter(adapter);
    }
}
