package com.example.fauricio.proyecto_1_moviles.Vista.admin;

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
import android.widget.Toast;

import com.example.fauricio.proyecto_1_moviles.Controlador.DAO_api_empresa;
import com.example.fauricio.proyecto_1_moviles.Controlador.listEmpresaAdapter;
import com.example.fauricio.proyecto_1_moviles.Modelo.Empresa;
import com.example.fauricio.proyecto_1_moviles.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class EmpresaFragment extends Fragment {
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
        rootView = inflater.inflate(R.layout.fragment_empresa,container,false);
        empresas = rootView.findViewById(R.id.LV_empresas);
        nueva_empresa = rootView.findViewById(R.id.FB_agregar);
        ArrayItem = new ArrayList<>();
        nueva_empresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),agregar_empresa.class);
                startActivity(intent);
            }
        });

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
        try {
            String msj = new DAO_api_empresa().execute("get").get();
            JSONObject obj = new JSONObject(msj);
            JSONArray empresas = obj.getJSONArray("objects");
            Toast.makeText(getContext(),empresas.toString(), Toast.LENGTH_LONG).show();
            for(int i=0;i<empresas.length();i++){
                JSONObject m = (JSONObject) empresas.get(i);
                ArrayItem.add(new Empresa(i,m.get("nombre"),m.get("descripcion")));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*
        for(int i = 0 ; i<12;i++){
            String msj = "Empresa"+String.valueOf(i);
            ArrayItem.add(new Empresa(i,msj,"Descripción"));
        }*/
        adapter = new listEmpresaAdapter(ArrayItem, context);
        empresas.setAdapter(adapter);
    }
}
