package com.example.fauricio.proyecto_1_moviles.Vista;
import com.example.fauricio.proyecto_1_moviles.Controlador.listChoferAdapter;
import com.example.fauricio.proyecto_1_moviles.Modelo.Chofer;
import com.example.fauricio.proyecto_1_moviles.Modelo.Empresa;
import com.example.fauricio.proyecto_1_moviles.Modelo.Nivel_usuario;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fauricio.proyecto_1_moviles.R;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class ChoferFragment extends Fragment {
    private View rootview;
    private ListView choferes;
    private listChoferAdapter adapter;
    private ArrayList<Chofer> ArrayItem = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_chofer,container,false);
        choferes = rootview.findViewById(R.id.LV_choferes);
        ArrayItem = new ArrayList<>();

        for(int i=0;i<5;i++){
            String base = "item " + i;
            Empresa e = new Empresa();
            Chofer c = new Chofer(i,base,Nivel_usuario.ADMINISTRADOR, (short) i,e,base);
            ArrayItem.add(c);
        }
        adapter = new listChoferAdapter(ArrayItem,rootview.getContext());
        choferes.setAdapter(adapter);
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

}
