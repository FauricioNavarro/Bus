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

    protected Void doInBackground(Void... params) {

        try {

            final HttpResponse<String> response = Unirest.get("https://bus-api-moviles.herokuapp.com/api/empresa/")
                    .header("content-type", "application/json")
                    .header("cache-control", "no-cache")
                    .header("postman-token", "7abae682-b744-fc07-c986-06eeb5558724")
                    //.body("{\n\t\"nombre\":\"MAMENDEZ COMPANY\",\n\t\"descripcion\":\"Compañia de buses\"\n}")
                    .asString();


            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    String body = response.getBody().toString();
                    //Integer last = body.indexOf("token_type") - 3;
                    //String token = body.substring(17,last);
                    Log.d("Token",body);
                    //api.setAccessToken(token);
                }
            });


        }  catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void runOnUiThread(Runnable token) {
    }

    @Override
    protected void onPostExecute(Void result) {

    }
}
