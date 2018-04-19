package com.example.fauricio.proyecto_1_moviles.Controlador;

import android.os.AsyncTask;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONException;
import org.json.JSONObject;

public class DAO_api extends AsyncTask<String, Void, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    protected String doInBackground(String... params) {
        String tipoEjecucion = params[0];
        switch (tipoEjecucion){
            case "get":{

            }break;
            case "post":{
                try {
                    JSONObject student1 = new JSONObject();
                    try {
                        student1.put("email", params[1]);
                        student1.put("password", params[2]);
                     } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    HttpResponse<String> response = Unirest.post("https://bus-api-moviles.herokuapp.com/api/user/create/")
                            .header("content-type", "application/json")
                            .header("cache-control", "no-cache")
                            .header("postman-token", "4f554794-9859-39ec-a223-b2b5cbc57610")
                            .body(String.valueOf(student1))
                            .asString();
                    return "1";
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }break;
            case "login":{
                try {
                    JSONObject login = new JSONObject();
                    try {
                        login.put("email", params[1]);
                        login.put("password", params[2]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    HttpResponse<String> response = Unirest.post("https://bus-api-moviles.herokuapp.com/api/user/login/")
                            .header("content-type", "application/json")
                            .header("cache-control", "no-cache")
                            .header("postman-token", "9285d672-f19a-0f5d-92d4-b6de9bf02303")
                            .body(String.valueOf(login))
                            .asString();
                    return response.toString();
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }break;
        }
        /*
        try {
            String resultado;
            HttpResponse<String> response = Unirest.get("https://bus-api-moviles.herokuapp.com/api/empresa/")
                    .header("content-type", "application/json")
                    .header("cache-control", "no-cache")
                    .header("postman-token", "7abae682-b744-fc07-c986-06eeb5558724")
                    //.body("{\n\t\"nombre\":\"MAMENDEZ COMPANY\",\n\t\"descripcion\":\"Compañia de buses\"\n}")
                    .asString();
            resultado =response.getBody().toString();
            Log.i("----- Antes de log","----- Antes de log---");
            Log.i("mensaje",resultado);
            return resultado;


        }  catch (UnirestException e) {
            e.printStackTrace();
        }*/
        return null;
    }
}
