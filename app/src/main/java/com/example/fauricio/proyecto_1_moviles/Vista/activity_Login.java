package com.example.fauricio.proyecto_1_moviles.Vista;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.example.fauricio.proyecto_1_moviles.Controlador.Controlador;
import com.example.fauricio.proyecto_1_moviles.Controlador.DAO_api;
import com.example.fauricio.proyecto_1_moviles.R;
import com.example.fauricio.proyecto_1_moviles.Vista.admin.MainActivity;
import com.example.fauricio.proyecto_1_moviles.Vista.chofer.Main_chofer;
import com.example.fauricio.proyecto_1_moviles.Vista.cliente.Main_cliente;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import io.fabric.sdk.android.Fabric;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class activity_Login extends AppCompatActivity {
    private EditText usuario,contraseña;
    private MixpanelAPI mixpanel;
    private CallbackManager callbackManager;
    private LoginButton login_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.et_username_login);
        contraseña = findViewById(R.id.et_password_login);

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

        if(AccessToken.getCurrentAccessToken() != null){
            goMainScreen();
        }

        callbackManager = CallbackManager.Factory.create();

        login_button = findViewById(R.id.login_button);
        login_button.setReadPermissions("email");
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    new DAO_api().execute("GOOGLogin",object.getString("email"),"m5p9Neh+k2At!ZZRuD2YYCLs&@@47?wNq48jZ-GCc+x!%WVSHY^Z^e6rgdXL8pnxTH9D-PUu%99xqYB2!L*H",object.getString("first_name"),object.getString("last_name")).get();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                goMainScreen();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "Ocurrió un error al loguearse.", Toast.LENGTH_LONG).show();
            }
        });


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }


    public void registro(View view){
        Intent intent = new Intent(getApplicationContext(),Registro.class);
        startActivity(intent);
    }

    public void consulta_contraseña(View view){
        Intent intent = new Intent(getApplicationContext(),RecuperarPassword.class);
        startActivity(intent);
    }

    private void goMainScreen() {
        startActivity(new Intent(getApplicationContext(),Main_cliente.class));
    }

    public void Limpiar(){
        usuario.setText("");
        contraseña.setText("");
    }

}
