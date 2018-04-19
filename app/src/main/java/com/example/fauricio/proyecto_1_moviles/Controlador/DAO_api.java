package com.example.fauricio.proyecto_1_moviles.Controlador;

import android.os.AsyncTask;
import android.util.Log;

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
                        JSONObject json1 = new JSONObject(response.getBody().toString());
                        String Token = (String) json1.get("token");
                        HttpResponse<String> response1 = Unirest.get("https://bus-api-moviles.herokuapp.com/api/user/view")
                                .header("content-type", "application/json")
                                .header("authorization", "JWT "+Token)
                                .header("cache-control", "no-cache")
                                .asString();
                        JSONObject json2 = new JSONObject(response1.getBody().toString());
                        //GUARDAR ESTE ID EN SHARED PREFFERENCES
                        int ID = json2.getInt("id");
                        HttpResponse<String> finalresponse = Unirest.get("https://bus-api-moviles.herokuapp.com/api/users/"+String.valueOf(ID)+"/")
                            .header("cache-control", "no-cache")
                            .header("postman-token", "a9e5810d-45c1-4089-3bc0-c77e699d58dc")
                            .asString();
                        return finalresponse.getBody().toString();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (UnirestException e) {
                    e.printStackTrace();
                }

            }break;
        }
        return null;
    }
}
