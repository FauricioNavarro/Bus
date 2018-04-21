package com.example.fauricio.proyecto_1_moviles.Vista.admin;

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
import com.example.fauricio.proyecto_1_moviles.Controlador.listChoferAdapter;
import com.example.fauricio.proyecto_1_moviles.Modelo.item;
import com.example.fauricio.proyecto_1_moviles.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 */
public class ChoferFragment extends Fragment {
    private View rootview;
    private ListView choferes;
    private listChoferAdapter adapter;
    private ArrayList<item> ArrayItem = null;
    private SharedPreferences sharedPreferences;

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

        choferes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item user = ArrayItem.get(i);
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("id_chofer_aux",user.getTitulo());
                editor.putString("nombre_chofer",user.getContenido1());
                editor.putString("apellido_chofer",user.getGetContenido2());
                editor.commit();
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

        String obj = Controlador.getInstance().getUsers();
        try {
            JSONArray json_rutas = new JSONArray(obj);
            for(int i=0;i<json_rutas.length();i++){
                JSONObject object = (JSONObject) json_rutas.getJSONObject(i);
                if(!object.getString("chofer").equals("null")){
                    ArrayItem.add(new item(object.getString("id"),object.getString("first_name"),object.getString("last_name")));
                }
                adapter = new listChoferAdapter(ArrayItem, context);
                choferes.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*
        try {
            JSONArray json_rutas = obj.getJSONArray("objects");
            for(int i=0;i<json_rutas.length();i++){
                JSONObject object = (JSONObject) json_rutas.getJSONObject(i);
                if(!object.getString("chofer").equals("")){
                    ArrayItem.add(new item(object.getString("id"),object.getString("first_name"),object.getString("last_name")));
                }
                adapter = new listChoferAdapter(ArrayItem, context);
                choferes.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */
    }
}
