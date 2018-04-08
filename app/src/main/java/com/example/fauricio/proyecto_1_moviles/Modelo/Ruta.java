package com.example.fauricio.proyecto_1_moviles.Modelo;

import java.util.ArrayList;

/**
 * Created by fauricio on 29/03/18.
 */

public class Ruta {
    private int ID_ruta;
    private String Inicio;
    private String Final;
    private float Costo;

    public int getID_ruta() {
        return ID_ruta;
    }

    public void setID_ruta(int ID_ruta) {
        this.ID_ruta = ID_ruta;
    }

    public String getInicio() {
        return Inicio;
    }

    public void setInicio(String inicio) {
        Inicio = inicio;
    }

    public String getFinal() {
        return Final;
    }

    public void setFinal(String aFinal) {
        Final = aFinal;
    }

    public float getCosto() {
        return Costo;
    }

    public void setCosto(float costo) {
        Costo = costo;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    private String Empresa;

    public Ruta(int ID_ruta, String inicio, String aFinal, float costo, String empresa) {
        this.ID_ruta = ID_ruta;
        Inicio = inicio;
        Final = aFinal;
        Costo = costo;
        Empresa = empresa;
    }




}
