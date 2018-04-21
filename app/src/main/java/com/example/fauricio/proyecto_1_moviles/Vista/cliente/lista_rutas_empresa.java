package com.example.fauricio.proyecto_1_moviles.Vista.cliente;

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

public class lista_rutas_empresa extends AppCompatActivity {
    private ListView lista;
    private int id_empresa_user;
    private listRutaAdapter adapter;
    private ArrayList<Ruta> ArrayItem = null;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_rutas_empresa);
        lista = findViewById(R.id.LV_rutas_empresa);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        id_empresa_user = sharedPreferences.getInt("id_empresa_user",0);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ruta temp = ArrayItem.get(i);
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id_ruta_user",temp.getID_ruta());
                editor.commit();
                Intent intent = new Intent(getApplicationContext(),detalle_ruta_user.class);
                startActivity(intent);
            }
        });
        ArrayItem = new ArrayList<>();
        cargarLista(getApplicationContext());
    }

    public void cargarLista(Context context){
        try {
            String result = Controlador.getInstance().get_empresa(String.valueOf(id_empresa_user));
            JSONObject obj = new JSONObject(result);
            JSONArray json_chofer = obj.getJSONArray("choferes");
            for(int i=0;i<json_chofer.length();i++){
                JSONObject object = (JSONObject) json_chofer.getJSONObject(i);
                JSONArray json_ruta = object.getJSONArray("rutas");
                for(int j=0;j<json_ruta.length();j++){
                    JSONObject r = json_ruta.getJSONObject(j);
                    ArrayItem.add(new Ruta(r.getInt("id"),
                            r.getString("nombre"),
                            r.getString("latitud_final"),
                            r.getString("longitud_final"),
                            (float) r.getDouble("costo")));
                }
            }
            adapter = new listRutaAdapter(ArrayItem, context);
            lista.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
