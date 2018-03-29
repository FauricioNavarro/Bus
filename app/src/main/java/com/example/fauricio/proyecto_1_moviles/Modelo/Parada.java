package com.example.fauricio.proyecto_1_moviles.Modelo;

/**
 * Created by fauricio on 29/03/18.
 */

public class Parada {
    private int ID_parada;
    private String nombre;

    public Parada(int ID_parada, String nombre) {
        this.ID_parada = ID_parada;
        this.nombre = nombre;
    }

    public int getID_parada() {
        return ID_parada;
    }

    public void setID_parada(int ID_parada) {
        this.ID_parada = ID_parada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Parada{" +
                "ID_parada=" + ID_parada +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
