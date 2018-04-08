package com.example.fauricio.proyecto_1_moviles.Modelo;

import java.util.ArrayList;

/**
 * Created by fauricio on 29/03/18.
 */

public class Empresa {
    private int ID_empresa;
    private String nombre;
    private String descripcion;

    public int getID_empresa() {
        return ID_empresa;
    }

    public void setID_empresa(int ID_empresa) {
        this.ID_empresa = ID_empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Empresa() {
    }

    public Empresa(int ID_empresa, String nombre, String descripcion) {
        this.ID_empresa = ID_empresa;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


}
