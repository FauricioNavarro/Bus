package com.example.fauricio.proyecto_1_moviles.Controlador;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class gestor {
    private static final gestor ourInstance = new gestor();
    private JSONObject lista_empresa;
    private JSONObject lista_ruta;
    private JSONObject lista_user;

    public static gestor getInstance() {
        return ourInstance;
    }

    private gestor() {
        try {
            String request_empresa = new DAO_api_empresa().execute("get").get();
            lista_empresa = new JSONObject(request_empresa);
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

    public String registrar(String email,String password,String nombre,String apellido){
        try {
            String result = new DAO_api().execute("post",email,password,nombre,apellido).get();
            return result;
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
            JSONObject user = new JSONObject(result);
            String admin = user.getString("is_admin");
            String chofer = user.getString("chofer");
            Log.i("login auth", result);
            if (admin.equals("true")) {
                return 1;
            } else {
                if (!chofer.equals("")) {
                    return 2;
                } else {
                    return 3;
                }
            }
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

    public JSONObject getLista_user() {
        return lista_user;
    }

    public void setLista_empresa(JSONObject lista_empresa) {
        this.lista_empresa = lista_empresa;
    }

    public void setLista_ruta(JSONObject lista_ruta) {
        this.lista_ruta = lista_ruta;
    }

    public void setLista_user(JSONObject lista_user) {
        this.lista_user = lista_user;
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
}
