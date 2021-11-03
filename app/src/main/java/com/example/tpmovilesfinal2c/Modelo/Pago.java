package com.example.tpmovilesfinal2c.Modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Pago implements Serializable {
    private int id;
    private int numPago;
    private Contrato contrato;
    private double importe;
    private String fechaPago;

    public Pago() {}

    public Pago(int idPago, int numero, Contrato contrato, double importe, String fechaDePago) {
        this.id = idPago;
        this.numPago = numero;
        this.contrato = contrato;
        this.importe = importe;
        this.fechaPago = fechaDePago;
    }

    public int getIdPago() {
        return id;
    }

    public void setIdPago(int idPago) {
        this.id = idPago;
    }

    public int getNumero() {
        return numPago;
    }

    public void setNumero(int numero) {
        this.numPago = numero;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getFechaDePago() {
        return fechaPago;
    }

    public void setFechaDePago(String fechaDePago) {
        this.fechaPago = fechaDePago;
    }
}
