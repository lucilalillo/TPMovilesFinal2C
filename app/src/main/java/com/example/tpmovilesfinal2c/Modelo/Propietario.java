package com.example.tpmovilesfinal2c.Modelo;

import java.io.Serializable;
import java.util.Objects;

public class Propietario implements Serializable {

    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private String mail;
    private String claveProp;
    private String telefono;
    private String avatar;

    public Propietario() {}
    public Propietario(int id, String dni, String nombre, String apellido, String email, String contraseña, String telefono, String avatar) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = email;
        this.claveProp = contraseña;
        this.telefono = telefono;
        this.avatar=avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) { this.dni = dni; }

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

    public String getEmail() {
        return mail;
    }

    public void setEmail(String email) {
        this.mail = email;
    }

    public String getContraseña() {
        return claveProp;
    }

    public void setContraseña(String contraseña) {
        this.claveProp = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Propietario that = (Propietario) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
