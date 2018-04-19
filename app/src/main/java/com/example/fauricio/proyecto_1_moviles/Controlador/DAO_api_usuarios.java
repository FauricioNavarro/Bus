package com.example.fauricio.proyecto_1_moviles.Controlador;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class DAO_api_usuarios extends AsyncTask<Void, Void, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    protected String doInBackground(Void... params) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.get("https://bus-api-moviles.herokuapp.com/api/users")
                    .header("cache-control", "no-cache")
                    .header("postman-token", "7f93d3ad-bf48-af39-4d83-914300868410")
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response.getBody().toString();

    }
}
