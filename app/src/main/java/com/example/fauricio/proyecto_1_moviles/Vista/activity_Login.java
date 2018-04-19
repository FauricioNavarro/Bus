package com.example.fauricio.proyecto_1_moviles.Vista;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fauricio.proyecto_1_moviles.Controlador.gestor;
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
    public LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

<<<<<<< HEAD
        // Solo Cambiar Datos :)
        //new DAO_api().execute("Login","juan_jop96@hotmail.com","Bases2018");
        //Asi funciona
        /*
        try {
            String resultado = n.execute().get();
            Toast.makeText(getApplicationContext(),resultado, Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        */

=======
>>>>>>> master
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
        Intent intent;
        String user = usuario.getText().toString();
        String pass = contraseña.getText().toString();
        if(!user.equals("") && !pass.equals("")){
            int login_auth = gestor.getInstance().login(user,pass);
            if(login_auth!=0){
                if(login_auth==1){
                    intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }else{
<<<<<<< HEAD
                    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    if(login_auth==2){
                        if(!locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)){
                            Toast.makeText(getApplicationContext(),"GPS apagado",Toast.LENGTH_LONG).show();
                            intent = new Intent(getApplicationContext(),acceso_ubicacion.class);
                            startActivity(intent);
                        }else{
                            intent = new Intent(getApplicationContext(),Main_chofer.class);
                            startActivity(intent);
                        }

                    }else{
                        if(!locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)){
                            Toast.makeText(getApplicationContext(),"GPS apagado",Toast.LENGTH_LONG).show();
                            intent = new Intent(getApplicationContext(),acceso_ubicacion.class);
                            startActivity(intent);
                        }else{
                            intent = new Intent(getApplicationContext(),Main_cliente.class);
                            startActivity(intent);
                        }
=======
                    if(login_auth==2){
                        intent = new Intent(getApplicationContext(),Main_chofer.class);
                        startActivity(intent);
                    }else{
                        intent = new Intent(getApplicationContext(),Main_cliente.class);
                        startActivity(intent);
>>>>>>> master
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

}
