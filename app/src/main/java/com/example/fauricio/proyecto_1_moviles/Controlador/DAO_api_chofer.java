package com.example.fauricio.proyecto_1_moviles.Controlador;

import android.os.AsyncTask;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class DAO_api_chofer extends AsyncTask<String,Void,String> {
    private String url = "https://bus-api-moviles.herokuapp.com/api/driver/";

    @Override
    protected String doInBackground(String... params) {
        String tipoEjecucion = params[0];
        String output = null;
        switch (tipoEjecucion){
            case "get":{
                try {
                    HttpResponse<String> response = Unirest.get(url)
                            .header("content-type", "application/json")
                            .header("cache-control", "no-cache")
                            .header("postman-token", "118aeee3-f682-cdaa-003b-6578c589e67c")
                            .asString();
                    output = response.toString();
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }break;
            case "get_driver":{
                try {
                    HttpResponse<String> response = Unirest.get(url+params[1]+"/")
                            .header("content-type", "application/json")
                            .header("cache-control", "no-cache")
                            .header("postman-token", "118aeee3-f682-cdaa-003b-6578c589e67c")
                            .asString();
                    output = response.toString();
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }break;
        }
        return output;
    }
}
