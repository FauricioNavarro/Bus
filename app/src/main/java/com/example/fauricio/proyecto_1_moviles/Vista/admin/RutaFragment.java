package com.example.fauricio.proyecto_1_moviles.Vista.admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fauricio.proyecto_1_moviles.Controlador.gestor;
import com.example.fauricio.proyecto_1_moviles.Controlador.listRutaAdapter;
import com.example.fauricio.proyecto_1_moviles.Modelo.Ruta;
import com.example.fauricio.proyecto_1_moviles.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class RutaFragment extends Fragment {
    private View rootView;
    private FloatingActionButton nueva_ruta;
    private ListView rutas;
    private listRutaAdapter adapter;
    private ArrayList<Ruta> ArrayItem = null;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ruta,container,false);
        rutas = rootView.findViewById(R.id.LV_rutas);
        nueva_ruta = rootView.findViewById(R.id.FB_agregar);
        ArrayItem = new ArrayList<>();
        nueva_ruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),agregar_ruta.class);
                startActivity(intent);
            }
        });

        rutas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ruta temp = ArrayItem.get(i);
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id_ruta",temp.getID_ruta());
                editor.commit();
                Intent intent = new Intent(getContext(),detalle_ruta.class);
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
            JSONObject obj = gestor.getInstance().getLista_ruta();
            JSONArray json_rutas = obj.getJSONArray("objects");
            for(int i=0;i<json_rutas.length();i++){
                JSONObject object = (JSONObject) json_rutas.getJSONObject(i);
                ArrayItem.add(new Ruta(object.getInt("id"),object.getString("nombre"),object.getString("inicio"),object.getString("final"), (float) object.getDouble("costo")));
                adapter = new listRutaAdapter(ArrayItem, context);
                rutas.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
