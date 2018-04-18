package com.example.fauricio.proyecto_1_moviles.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fauricio.proyecto_1_moviles.R;
import com.example.fauricio.proyecto_1_moviles.Vista.admin.MainActivity;
import com.example.fauricio.proyecto_1_moviles.Vista.chofer.Main_chofer;
import com.example.fauricio.proyecto_1_moviles.Vista.cliente.Main_cliente;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class activity_Login extends AppCompatActivity {
    private EditText usuario,contraseña;
    private GoogleApiClient googleApiClient;
    public static final int CODIGO_SIGN_IN = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Asi funciona
        /*DAO_api n = new DAO_api();

        try {
            String resultado = n.execute().get();
            Toast.makeText(getApplicationContext(),resultado, Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        */

        usuario = findViewById(R.id.et_username_login);
        contraseña = findViewById(R.id.et_password_login);

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
        SignInButton signInButton = findViewById(R.id.bt_SignInGoogle);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });



    }

    public void login(View view){
        String user = usuario.getText().toString();
        Intent intent;
        if(user.equals("admin")){
            intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }else{
            if(user.equals("chofer")){
                intent = new Intent(getApplicationContext(),Main_chofer.class);
                startActivity(intent);
            }else{
                intent = new Intent(getApplicationContext(),Main_cliente.class);
                startActivity(intent);
            }
        }
    }

    public void registro(View view){
        Intent intent = new Intent(getApplicationContext(),Registro.class);
        startActivity(intent);
    }

    public void consulta_contraseña(View view){
        Intent intent = new Intent(getApplicationContext(),RecuperarPassword.class);
        startActivity(intent);
    }

    public void signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, CODIGO_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODIGO_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()){
                goMainScreen();
            }else{
                Toast.makeText(this, R.string.fallo_sign_in, Toast.LENGTH_LONG).show();
            }
        }
    }


    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
