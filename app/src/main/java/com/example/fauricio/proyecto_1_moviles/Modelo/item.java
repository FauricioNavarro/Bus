package com.example.fauricio.proyecto_1_moviles.Modelo;

/**
 * Created by fauricio on 05/04/18.
 */

public class item {
    private String titulo;
    private String contenido1;
    private String getContenido2;

    public item(String titulo, String contenido1, String getContenido2) {
        this.titulo = titulo;
        this.contenido1 = contenido1;
        this.getContenido2 = getContenido2;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido1() {
        return contenido1;
    }

    public void setContenido1(String contenido1) {
        this.contenido1 = contenido1;
    }

    public String getGetContenido2() {
        return getContenido2;
    }

    public void setGetContenido2(String getContenido2) {
        this.getContenido2 = getContenido2;
    }
}
