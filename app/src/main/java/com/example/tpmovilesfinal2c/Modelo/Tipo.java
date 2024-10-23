package com.example.tpmovilesfinal2c.Modelo;

import java.io.Serializable;

public class Tipo implements Serializable {
    private int id;
    private String descripcion;

    public Tipo() {
    }

    public Tipo(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
