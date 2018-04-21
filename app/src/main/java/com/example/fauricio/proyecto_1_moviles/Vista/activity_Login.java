package com.example.fauricio.proyecto_1_moviles.Vista;

import android.content.Intent;
import android.media.midi.MidiDevice;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.example.fauricio.proyecto_1_moviles.Controlador.Controlador;
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

import com.mixpanel.android.mpmetrics.MixpanelAPI;

import io.fabric.sdk.android.Fabric;
import org.json.JSONException;
import org.json.JSONObject;


public class activity_Login extends AppCompatActivity {
    private EditText usuario,contraseña;
    private GoogleApiClient googleApiClient;
    public static final int CODIGO_SIGN_IN = 777;
    private MixpanelAPI mixpanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        setContentView(R.layout.activity_login);

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

        String projectToken = "1e3cae6b552813d8ab09fb98f6dccce2";
        mixpanel = MixpanelAPI.getInstance(this, projectToken);

        try{
            JSONObject props = new JSONObject();
            props.put("Gender", "Female");
            props.put("Logged in", false);
            mixpanel.track("Se prendió la aplicacion", props);
        } catch (JSONException e) {
            Log.e("MYAPP", "Unable to add properties to JSONObject");
        }
    }

    public void login(View view){
        Intent intent;
        String user = usuario.getText().toString();
        String pass = contraseña.getText().toString();
        if(!user.equals("") && !pass.equals("")){
            int login_auth = Controlador.getInstance().login(user,pass);
            if(login_auth!=0){
                if(login_auth==1){
                    intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }else{
                    if(login_auth==2){
                        intent = new Intent(getApplicationContext(),Main_chofer.class);
                        startActivity(intent);
                    }else{
                        intent = new Intent(getApplicationContext(),Main_cliente.class);
                        startActivity(intent);
                    }
                }
            }else{
                Toast.makeText(getApplicationContext(),"Error al iniciar sesión",Toast.LENGTH_LONG).show();
                Limpiar();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Error al iniciar sesión",Toast.LENGTH_LONG).show();
            Limpiar();
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
        Intent intent = new Intent(this, Main_cliente.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void Limpiar(){
        usuario.setText("");
        contraseña.setText("");
    }


//    @Override
//    protected void onDestroy() {
//        mixpanel.flush();
//        super.onDestroy();
//    }
}
