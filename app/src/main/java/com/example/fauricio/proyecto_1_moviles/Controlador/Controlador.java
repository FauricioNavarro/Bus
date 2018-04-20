package com.example.fauricio.proyecto_1_moviles.Controlador;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Controlador {
    private static final Controlador ourInstance = new Controlador();
    private JSONObject lista_empresa;
    private JSONObject lista_ruta;
    private JSONObject lista_parada;
    private String users;
    private int id_user;

    public static Controlador getInstance() {
        return ourInstance;
    }

    private Controlador() {
        try {
            String request_empresa = new DAO_api_empresa().execute("get").get();
            lista_empresa = new JSONObject(request_empresa);
            String request_ruta = new DAO_api_ruta().execute("get").get();
            lista_ruta = new JSONObject(request_ruta);
            String request_parada = new DAO_api_parada().execute("get").get();
            lista_parada = new JSONObject(request_parada);
            users = new DAO_api_user().execute("get").get();
            //lista_user = new JSONObject(request_user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String registrar(String email,String password,String nombre,String apellido){
        try {
            String result = new DAO_api().execute("post",email,password,nombre,apellido).get();
            if(result!=null){
                return "1";
            }
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int login(String email,String password) {
        try {
            DAO_api e = new DAO_api();
            String result = e.execute("login", email, password).get();
            if(result!=null){
                JSONObject user = new JSONObject(result);
                String admin = user.getString("is_admin");
                String chofer = user.getString("chofer");
                Log.i("login auth", result);
                if (admin.equals("true")) {
                    return 1;
                } else {
                    if (!chofer.equals("null")) {
                        return 2;
                    } else {
                        return 3;
                    }
                }
            }
            return 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public JSONObject getLista_empresa() {
        return lista_empresa;
    }

    public JSONObject getLista_ruta() {
        return lista_ruta;
    }

    public JSONObject getLista_parada() {
        return lista_parada;
    }

    public String getUsers() {
        return users;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    // ========================= User ===================================
    public String get_chofer_id(String id){
        DAO_api_user e = new DAO_api_user();
        String out=null;
        try {
            String request = e.execute("get_user",id).get();
            out =  request;
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        return out;
    }
    // ========================= Driver ===================================
    public String get_driver(String id){
        DAO_api_chofer e = new DAO_api_chofer();
        String out=null;
        try {
            String request = e.execute("get_driver",id).get();
            out =  request;
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        return out;
    }


    // ========================= Empresa ==================================

    public String registrar_empresa(String nombre,String descripcion){
        try {
            String result = new DAO_api_empresa().execute("post",nombre,descripcion).get();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String put_empresa(String nombre,String descripcion,String id){
        try {
            String result = new DAO_api_empresa().execute("put",id,nombre,descripcion).get();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String get_empresa(String id){
        DAO_api_empresa e = new DAO_api_empresa();
        try {
            String request = e.execute("get_empresa",id).get();
            return request;
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public String delete_empresa(String id){
        DAO_api_empresa e = new DAO_api_empresa();
        try {
            String request = e.execute("delete",id).get();
            return request;
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public void actualizar_empresa(){
        try {
            String request_empresa = new DAO_api_empresa().execute("get").get();
            lista_empresa = new JSONObject(request_empresa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // ========================= Rutas ==================================

    public void actualizar_ruta(){
        try {
            String request_ruta = new DAO_api_ruta().execute("get").get();
            lista_ruta = new JSONObject(request_ruta);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String registrar_ruta(String nombre,String incio,String fin,String latitud,String longitud,String costo){
        try {
            String result = new DAO_api_ruta().execute("post",nombre,costo,incio,fin,latitud,longitud).get();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String get_ruta(String id){
        DAO_api_ruta e = new DAO_api_ruta();
        try {
            String request = e.execute("get_ruta",id).get();
            return request;
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public String delete_ruta(String id){
        DAO_api_ruta e = new DAO_api_ruta();
        try {
            String request = e.execute("delete",id).get();
            return request;
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public String put_ruta(String id, String nombre, String costo, String incio, String fin, String latitud, String longitud, JSONArray paradas){
        try {
            String result = new DAO_api_ruta().execute("put",id,nombre,costo,incio,fin,latitud,longitud,paradas.toString()).get();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ========================= Rutas ==================================

    public void actualizar_parada(){
        try {
            String request_parada = new DAO_api_parada().execute("get").get();
            lista_parada = new JSONObject(request_parada);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String registrar_parada(String nombre,String latitud,String longitud){
        try {
            String result = new DAO_api_parada().execute("post",nombre,latitud,longitud).get();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String get_parada(String id){
        DAO_api_parada e = new DAO_api_parada();
        try {
            String request = e.execute("get_parada",id).get();
            return request;
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public String delete_parada(String id){
        DAO_api_parada e = new DAO_api_parada();
        try {
            String request = e.execute("delete",id).get();
            return request;
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public String put_parada(String id,String nombre,String latitud,String longitud){
        try {
            String result = new DAO_api_parada().execute("put",id,nombre,latitud,longitud).get();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
