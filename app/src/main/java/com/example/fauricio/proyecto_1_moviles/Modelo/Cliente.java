package com.example.fauricio.proyecto_1_moviles.Modelo;

/**
 * Created by fauricio on 29/03/18.
 */

public class Cliente {
    private int Imagen;

    public Cliente(int ID_usuario, String nombre, int imagen) {
        Imagen = imagen;
    }
    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "Imagen=" + Imagen +
                '}';
    }
}
