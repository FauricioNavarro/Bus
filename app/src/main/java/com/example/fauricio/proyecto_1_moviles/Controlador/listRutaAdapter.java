package com.example.fauricio.proyecto_1_moviles.Controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fauricio.proyecto_1_moviles.Modelo.Empresa;
import com.example.fauricio.proyecto_1_moviles.Modelo.Ruta;
import com.example.fauricio.proyecto_1_moviles.R;

import java.util.ArrayList;

/**
 * Created by fauricio on 07/04/18.
 */

public class listRutaAdapter extends BaseAdapter {
    private ArrayList<Ruta> arrayItems;
    private Context context;
    private LayoutInflater layoutInflater;

    public listRutaAdapter(ArrayList<Ruta> arrayItems, Context context) {
        this.arrayItems = arrayItems;
        this.context = context;
    }
    @Override
    public int getCount() {
        return arrayItems.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        String costo;
        View vistaItem = layoutInflater.inflate(R.layout.activity_lista_ruta, viewGroup, false);
        ImageView iv_imagen = (ImageView) vistaItem.findViewById(R.id.iv_imagen);
        TextView tv_titulo = (TextView) vistaItem.findViewById(R.id.tv_titulo);
        TextView tv_contenido = (TextView) vistaItem.findViewById(R.id.tv_contenido);
        TextView tv_contenido_1 = (TextView) vistaItem.findViewById(R.id.tv_contenido1);
        TextView tv_contenido_2 = (TextView) vistaItem.findViewById(R.id.tv_contenido2);
        iv_imagen.setImageResource(R.drawable.rutas);
        tv_titulo.setText(arrayItems.get(i).getNombre());
        tv_contenido.setText(arrayItems.get(i).getInicio());
        tv_contenido_1.setText(arrayItems.get(i).getFinal());
        costo = String.valueOf(arrayItems.get(i).getCosto());
        tv_contenido_2.setText(costo);

        return vistaItem;
    }
}
