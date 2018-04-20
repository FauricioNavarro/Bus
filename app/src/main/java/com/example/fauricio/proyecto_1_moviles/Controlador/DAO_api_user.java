package com.example.fauricio.proyecto_1_moviles.Controlador;

import android.os.AsyncTask;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class DAO_api_user extends AsyncTask<String,Void,String> {
    private String url = "https://bus-api-moviles.herokuapp.com/api/users/";

    @Override
    protected String doInBackground(String... params) {
        String tipoEjecucion = params[0];
        String output=null;
        switch (tipoEjecucion){
            case "get":{
                try {
                    HttpResponse<String> response = Unirest.get("https://bus-api-moviles.herokuapp.com/api/users/")
                            .header("content-type", "application/json")
                            .header("cache-control", "no-cache")
                            .header("postman-token", "537f36a9-1d68-6dc0-91b4-cd573c5f1acf")
                            .asString();
                    output = response.getBody().toString();
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }break;
            case "get_user":{
                try {
                    HttpResponse<String> response = Unirest.get(url+params[1]+"/")
                            .header("content-type", "application/json")
                            .header("cache-control", "no-cache")
                            .header("postman-token", "aa85e34c-3547-9bbe-a715-5719a5372370")
                            .asString();
                    output = response.getBody().toString();
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }break;
        }
        return  output;
    }
}
