package com.example.fauricio.proyecto_1_moviles.Vista.chofer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fauricio.proyecto_1_moviles.Controlador.Controlador;
import com.example.fauricio.proyecto_1_moviles.Controlador.listRutaAdapter;
import com.example.fauricio.proyecto_1_moviles.Modelo.Ruta;
import com.example.fauricio.proyecto_1_moviles.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main_chofer extends AppCompatActivity {
    private ListView rutas;
    private listRutaAdapter adapter;
    private ArrayList<Ruta> ArrayItem = null;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chofer);
        rutas = findViewById(R.id.LV_rutas);
        ArrayItem = new ArrayList<>();

        rutas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ruta temp = ArrayItem.get(i);
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id_ruta_chofer",temp.getID_ruta());
                editor.commit();
                Intent intent = new Intent(getApplicationContext(),mapa_chofer.class);
                startActivity(intent);
            }
        });
        cargarLista(this);
    }

    public void cargarLista(Context context){
        String id = String.valueOf(Controlador.getInstance().getId_user());
        String chofer =  String.valueOf(Controlador.getInstance().get_chofer_id(id));
        try {
            JSONObject ch = new JSONObject(chofer);
            String id_chofer = ch.getString("chofer");
            String chofer_info = Controlador.getInstance().get_driver(id_chofer);
            JSONObject json_aux = new JSONObject(chofer_info);
            //Log.i("==>",json_aux.getJSONArray("rutas").toString());
            JSONArray json_rutas = json_aux.getJSONArray("rutas");
            for(int i=0;i<json_rutas.length();i++){
                JSONObject object = (JSONObject) json_rutas.getJSONObject(i);
                //Log.i("id ==> ",String.valueOf(object.getInt("id")));
                ArrayItem.add(new Ruta(object.getInt("id"),
                        object.getString("nombre"),
                        object.getString("latitud_final"),
                        object.getString("longitud_final"),
                        (float) object.getDouble("costo")));
            }
            adapter = new listRutaAdapter(ArrayItem, context);
            rutas.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
