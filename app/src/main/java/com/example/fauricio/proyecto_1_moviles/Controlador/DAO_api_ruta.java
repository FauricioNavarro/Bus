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
                        ruta.put("latitud_final", params[3]);
                        ruta.put("longitud_final", params[4]);
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
            case "get_ruta":{
                try {
                    HttpResponse<String> response = Unirest.get(url+params[1]+"/")
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
            case "delete":{
                try {
                    HttpResponse<String> response = Unirest.delete(url+params[1]+"/")
                            .header("content-type", "application/json")
                            .header("cache-control", "no-cache")
                            .header("postman-token", "02507d37-a158-478a-da11-eb83ae9fc4db")
                            .asString();
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }break;
            case "put":{
                try {
                    JSONObject ruta = new JSONObject();
                    try {
                        ruta.put("nombre", params[2]);
                        ruta.put("costo", params[3]);
                        ruta.put("latitud_final", params[4]);
                        ruta.put("longitud_final", params[5]);
                        ruta.put("latitud", params[6]);
                        ruta.put("longitud", params[7]);
                        ruta.put("paradas", params[8]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    HttpResponse<String> response = Unirest.put(url+params[1]+"/")
                            .header("content-type", "application/json")
                            .header("cache-control", "no-cache")
                            .header("postman-token", "0fddc3cd-dcd0-07ee-e3a6-f1d5116f4a3d")
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
