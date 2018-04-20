package com.example.fauricio.proyecto_1_moviles.Vista.cliente;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fauricio.proyecto_1_moviles.Controlador.Controlador;
import com.example.fauricio.proyecto_1_moviles.Controlador.listParadaAdapter;
import com.example.fauricio.proyecto_1_moviles.Modelo.Parada;
import com.example.fauricio.proyecto_1_moviles.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class parada_cliente extends Fragment {
    private View rootView;
    private ListView paradas;
    private listParadaAdapter adapter;
    private ArrayList<Parada> ArrayItem = null;
    private SharedPreferences sharedPreferences;

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
                Parada temp = ArrayItem.get(i);
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id_parada_user",temp.getID_parada());
                editor.commit();
                Intent intent = new Intent(getContext(),detalle_parada_user.class);
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
        try {
            JSONObject obj = Controlador.getInstance().getLista_parada();
            JSONArray json_paradas = obj.getJSONArray("objects");
            for(int i=0;i<json_paradas.length();i++){
                JSONObject object = (JSONObject) json_paradas.getJSONObject(i);
                ArrayItem.add(new Parada(object.getInt("id"),object.getString("nombre")));
                adapter = new listParadaAdapter(ArrayItem, context);
                paradas.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
