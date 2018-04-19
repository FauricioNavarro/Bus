package com.example.fauricio.proyecto_1_moviles.Modelo;

/**
 * Created by fauricio on 29/03/18.
 */

public abstract class Usuario {
    private int ID_usuario;
    private String nombre;
    private Nivel_usuario nivel;

    public Usuario(int ID_usuario, String nombre, Nivel_usuario nivel) {
        this.ID_usuario = ID_usuario;
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public int getID_usuario() {
        return ID_usuario;
    }

    public void setID_usuario(int ID_usuario) {
        this.ID_usuario = ID_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nivel_usuario getNivel() {
        return nivel;
    }

    public void setNivel(Nivel_usuario nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "ID_usuario=" + ID_usuario +
                ", nombre='" + nombre + '\'' +
                ", nivel=" + nivel +
                '}';
    }
}
