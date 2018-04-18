package com.example.fauricio.proyecto_1_moviles.Controlador;

import android.os.AsyncTask;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONException;
import org.json.JSONObject;

public class DAO_api_empresa extends AsyncTask<String, Void, String> {
    private String url = "https://bus-api-moviles.herokuapp.com/api/empresa/";
    @Override
    protected String doInBackground(String... params) {
        String tipoEjecucion = params[0];
        switch (tipoEjecucion){
            case "get":{
                try {
                    HttpResponse<String> response = Unirest.get("https://bus-api-moviles.herokuapp.com/api/empresa/")
                            .header("content-type", "application/json")
                            .header("cache-control", "no-cache")
                            .header("postman-token", "c7b17c21-4bd4-2279-4d50-88e32cd40e6b")
                            .asString();
                    String resultado =response.getBody().toString();
                    return resultado;
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }break;
            case "post":{
                try {
                    JSONObject empresa = new JSONObject();
                    try {
                        empresa.put("nombre", params[1]);
                        empresa.put("descripcion", params[2]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    HttpResponse<String> response = Unirest.post(url)
                            .header("content-type", "application/json")
                            .header("cache-control", "no-cache")
                            .header("postman-token", "4f554794-9859-39ec-a223-b2b5cbc57610")
                            .body(String.valueOf(empresa))
                            .asString();
                    return "1";
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }break;
        }
        return null;
    }
}
