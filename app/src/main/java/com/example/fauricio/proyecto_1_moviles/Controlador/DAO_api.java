package com.example.fauricio.proyecto_1_moviles.Controlador;

import android.os.AsyncTask;
import android.util.Log;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class DAO_api extends AsyncTask<Void, Void, Void> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    //Tiene que ser un do in background para que no caiga el sistema
    protected Void doInBackground(Void... params) {

        try {

            //Codigo generado por postman
		/*
                final HttpResponse<String> response = Unirest.post("https://accounts.spotify.com/api/token")
                        .header("authorization", "Basic NjRiMWRlZTljMzkzNGNhMzlhNzg0M2NlYWFkMjk2YmU6M2YwNTY2NWUwYjJhNGVkMzg4MjlhOTRhNzZjMmVkZjE=")
                        .header("content-type", "application/x-www-form-urlencoded")
                        .header("cache-control", "no-cache")
                        .header("postman-token", "90a872a2-c311-a323-8afe-d61f53c195b9")
                        .body("grant_type=client_credentials")
                        .asString();


            Log.i("===== AQUI  ======","ANTES");
            final HttpResponse<String> response = Unirest.get("https://bus-api-moviles.herokuapp.com/api/empresa/")
                    .header("authorization", "c)+19fevc5zpvw-n#n!4434j)v--i$!_qo33=97!i^t57wed-2")
                    .header("content-type", "application/json")
                    .header("cache-control", "no-cache")
                    .asString();*/

            Log.i("===== AQUI ======","DESPUES");
            final HttpResponse<String> response = Unirest.get("https://bus-api-moviles.herokuapp.com/api/empresa/").asString();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String body = response.getBody().toString();
                    Log.i("===== AQUI++ ======",body);
                }
            });
        }  catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void runOnUiThread(Runnable runnable) {
    }
}
