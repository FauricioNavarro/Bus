package com.example.fauricio.proyecto_1_moviles.Modelo;

import java.util.ArrayList;

/**
 * Created by fauricio on 29/03/18.
 */

public class Empresa {
    private int ID_empresa;
    private String nombre;
    private ArrayList<Ruta> rutas = new ArrayList<>();
    private ArrayList<Chofer> choferes = new ArrayList<>();

    public Empresa() {
    }

    public Empresa(int ID_empresa, String nombre, ArrayList<Ruta> rutas, ArrayList<Chofer> choferes) {
        this.ID_empresa = ID_empresa;
        this.nombre = nombre;
        this.rutas = rutas;
        this.choferes = choferes;
    }
}
