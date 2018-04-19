package com.example.fauricio.proyecto_1_moviles.Modelo;

/**
 * Created by fauricio on 29/03/18.
 */

public class Chofer extends Usuario{
    private short rating;
    private Empresa empresa;
    private String placa;

    public Chofer(int ID_usuario, String nombre, Nivel_usuario nivel) {
        super(ID_usuario, nombre, nivel);
    }

    public Chofer(int ID_usuario, String nombre, Nivel_usuario nivel, short rating, Empresa empresa, String placa) {
        super(ID_usuario, nombre, nivel);
        this.rating = rating;
        this.empresa = empresa;
        this.placa = placa;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Chofer{" +
                "rating=" + rating +
                ", empresa=" + empresa +
                ", placa='" + placa + '\'' +
                '}';
    }
}
