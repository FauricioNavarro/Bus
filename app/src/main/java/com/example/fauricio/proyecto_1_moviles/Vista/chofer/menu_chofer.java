package com.example.fauricio.proyecto_1_moviles.Vista.chofer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.fauricio.proyecto_1_moviles.R;
import com.example.fauricio.proyecto_1_moviles.Vista.admin.ChoferFragment;
import com.example.fauricio.proyecto_1_moviles.Vista.admin.EmpresaFragment;
import com.example.fauricio.proyecto_1_moviles.Vista.admin.ParadaFragment;
import com.example.fauricio.proyecto_1_moviles.Vista.admin.RutaFragment;

public class menu_chofer extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_chofer:
                    transaction.replace(R.id.frame_contenedor,new ChoferFragment()).commit();
                    return true;
                case R.id.navigation_empresa:
                    transaction.replace(R.id.frame_contenedor,new EmpresaFragment()).commit();
                    return true;
                case R.id.navigation_rutas:
                    transaction.replace(R.id.frame_contenedor,new RutaFragment()).commit();
                    return true;
                case R.id.navigation_paradas:
                    transaction.replace(R.id.frame_contenedor,new ParadaFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_chofer2);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
