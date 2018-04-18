package com.example.fauricio.proyecto_1_moviles.Controlador;

import java.util.concurrent.ExecutionException;

public class gestor {
    private static final gestor ourInstance = new gestor();

    public static gestor getInstance() {
        return ourInstance;
    }

    private gestor() {
    }

    public String registrar(String email,String password){
        try {
            String result = new DAO_api().execute("post",email,password).get();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

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
}
