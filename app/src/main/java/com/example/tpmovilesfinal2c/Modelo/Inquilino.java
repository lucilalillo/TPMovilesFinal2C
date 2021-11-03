package com.example.tpmovilesfinal2c.Modelo;

import java.io.Serializable;

public class Inquilino implements Serializable {
    private int idInquilino;
    private String dni;
    private String nombre;
    private String apellido;
    private String lugarDeTrabajo;
    private String mail;
    private String telefono;
    private String nombreGarante;
    private String telefonoGarante;

    public Inquilino() {}

    public Inquilino(int idInquilino, String dni, String nombre, String apellido, String lugarDeTrabajo, String email, String telefono, String nombreGarante, String telefonoGarante) {
        this.idInquilino = idInquilino;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.lugarDeTrabajo = lugarDeTrabajo;
        this.mail = email;
        this.telefono = telefono;
        this.nombreGarante = nombreGarante;
        this.telefonoGarante = telefonoGarante;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public String getDNI() {
        return dni;
    }

    public void setDNI(String DNI) {
        this.dni = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLugarDeTrabajo() {
        return lugarDeTrabajo;
    }

    public void setLugarDeTrabajo(String lugarDeTrabajo) {
        this.lugarDeTrabajo = lugarDeTrabajo;
    }

    public String getEmail() {
        return mail;
    }

    public void setEmail(String email) {
        this.mail = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreGarante() {
        return nombreGarante;
    }

    public void setNombreGarante(String nombreGarante) {
        this.nombreGarante = nombreGarante;
    }

    public String getTelefonoGarante() {
        return telefonoGarante;
    }

    public void setTelefonoGarante(String telefonoGarante) {
        this.telefonoGarante = telefonoGarante;
    }
}
