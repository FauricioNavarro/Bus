package com.example.fauricio.proyecto_1_moviles.Vista.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fauricio.proyecto_1_moviles.Controlador.DAO_api;
import com.example.fauricio.proyecto_1_moviles.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.example.fauricio.proyecto_1_moviles.Vista.activity_Login;

public class Main_cliente extends AppCompatActivity {

    private GoogleApiClient googleApiClient;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_empresa_cliente:
                    transaction.replace(R.id.frame_contenedor_cliente,new empresa_cliente()).commit();
                    return true;
                case R.id.navigation_rutas_cliente:
                    transaction.replace(R.id.frame_contenedor_cliente,new ruta_cliente()).commit();
                    return true;
                case R.id.navigation_paradas_cliente:
                    transaction.replace(R.id.frame_contenedor_cliente,new parada_cliente()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cliente);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_contenedor_cliente,new empresa_cliente()).commit();
        BottomNavigationView navigation_cliente = (BottomNavigationView) findViewById(R.id.navigation_cliente);
        navigation_cliente.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(), R.string.error_conexion, Toast.LENGTH_LONG).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()){
            GoogleSignInResult result = opr.get();
            probarLogin(result);
        }else{
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    probarLogin(googleSignInResult);
                }
            });
        }
    }

    public void probarLogin(GoogleSignInResult result){
        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            DAO_api dao= new DAO_api();
            dao.execute("GOOGLogin",account.getEmail(),"m5p9Neh+k2At!ZZRuD2YYCLs&@@47?wNq48jZ-GCc+x!%WVSHY^Z^e6rgdXL8pnxTH9D-PUu%99xqYB2!L*H",account.getGivenName(),account.getFamilyName());
            Log.i("AQUI", dao.response_usuario);
        }else{
            Intent intent = new Intent(this, activity_Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

}
