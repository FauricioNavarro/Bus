package com.example.fauricio.proyecto_1_moviles.Controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fauricio.proyecto_1_moviles.Modelo.Empresa;
import com.example.fauricio.proyecto_1_moviles.R;

import java.util.ArrayList;

/**
 * Created by fauricio on 07/04/18.
 */

public class listEmpresaAdapter extends BaseAdapter {
    private ArrayList<Empresa> arrayItems;
    private Context context;
    private LayoutInflater layoutInflater;

    public listEmpresaAdapter(ArrayList<Empresa> arrayItems, Context context) {
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

        View vistaItem = layoutInflater.inflate(R.layout.activity_lista_empresa, viewGroup, false);
        ImageView iv_imagen = (ImageView) vistaItem.findViewById(R.id.iv_imagen);
        TextView tv_titulo = (TextView) vistaItem.findViewById(R.id.tv_titulo);
        TextView tv_contenido_1 = (TextView) vistaItem.findViewById(R.id.tv_contenido);
        iv_imagen.setImageResource(R.drawable.company);
        tv_titulo.setText(arrayItems.get(i).getNombre());
        tv_contenido_1.setText(arrayItems.get(i).getDescripcion());

        return vistaItem;
    }
}
