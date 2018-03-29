package com.example.fauricio.proyecto_1_moviles.Modelo;

/**
 * Created by fauricio on 29/03/18.
 */

public class Cliente extends Usuario {
    private int Imagen;

    public Cliente(int ID_usuario, String nombre, Nivel_usuario nivel, int imagen) {
        super(ID_usuario, nombre, nivel);
        Imagen = imagen;
    }

    public Cliente(int ID_usuario, String nombre, Nivel_usuario nivel) {
        super(ID_usuario, nombre, nivel);
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }
}
