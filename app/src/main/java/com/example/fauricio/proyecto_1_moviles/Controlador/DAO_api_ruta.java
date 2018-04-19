package com.example.fauricio.proyecto_1_moviles.Controlador;

import android.os.AsyncTask;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONException;
import org.json.JSONObject;

public class DAO_api_ruta extends AsyncTask<String,Void,String> {
    private String url = "https://bus-api-moviles.herokuapp.com/api/ruta/";

    @Override
    protected String doInBackground(String... params) {
        String tipoEjecucion = params[0];
        switch (tipoEjecucion){
            case "get":{
                try {
                    HttpResponse<String> response = Unirest.get(url)
                            .header("content-type", "application/json")
                            .header("cache-control", "no-cache")
                            .header("postman-token", "99913e65-573b-f219-53f5-b7329a5a218d")
                            .asString();
                    String resultado =response.getBody().toString();
                    return resultado;
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }break;
            case "post":{
                try {
                    JSONObject ruta = new JSONObject();
                    try {
                        ruta.put("nombre", params[1]);
                        ruta.put("costo", params[2]);
                        ruta.put("final", params[3]);
                        ruta.put("inicio", params[4]);
                        ruta.put("latitud", params[5]);
                        ruta.put("longitud", params[6]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    HttpResponse<String> response = Unirest.post(url)
                            .header("content-type", "application/json")
                            .header("cache-control", "no-cache")
                            .header("postman-token", "4f554794-9859-39ec-a223-b2b5cbc57610")
                            .body(String.valueOf(ruta))
                            .asString();
                    return response.toString();
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }break;
        }
        return null;
    }
}
