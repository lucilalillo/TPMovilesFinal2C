package com.example.tpmovilesfinal2c.Modelo;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable {
    private int idInmueble;
    private String direccion;
    private String uso;
    private String tipo;
    private int ambientes;
    private int importe;
    private Propietario propietario;
    //En falso significa que el innmueble no está disponible por alguna falla en el mismo.
    private boolean estado;
    private String imagen;

    public Inmueble(int idInmueble, String direccion, String uso, String tipo, int ambientes, int precio, Propietario propietario, boolean estado, String imagen) {
        this.idInmueble = idInmueble;
        this.direccion = direccion;
        this.uso = uso;
        this.tipo = tipo;
        this.ambientes = ambientes;
        this.importe = precio;
        this.propietario = propietario;
        this.estado = estado;
        this.imagen = imagen;
    }
    public Inmueble() {

    }
    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public int getPrecio() {
        return importe;
    }

    public void setPrecio(int precio) {
        this.importe = precio;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble inmueble = (Inmueble) o;
        return idInmueble == inmueble.idInmueble;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInmueble);
    }
}
