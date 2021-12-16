package Modelo;

import java.util.Date;

/**
 * Clase para los movimientos asociados a una cuenta.
 */
public class Movimiento {
    /*
    numero INTEGER,
    cueNumero INTEGER CONSTRAINT movimiento_FK_cuenta REFERENCES cuenta,
    fecha DATE,
    importe NUMERIC(8,2),
    concepto VARCHAR(30),
    PRIMARY KEY (numero, cueNumero)
    */
    // Atributos
    private int numero;
    private int numCuenta;
    private Date fecha;
    private float importe;
    private String concepto;
    
    // Constructor
    public Movimiento(int numero, int numCuenta, Date fecha, float importe, String concepto) {
        this.numero = numero;
        this.numCuenta = numCuenta;
        this.fecha = fecha;
        this.importe = importe;
        this.concepto = concepto;
    }
    
    // Getters y Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    
    // ToString
    @Override
    public String toString() {
        return  this.numero + ";" + 
                this.numCuenta + ";" + 
                this.fecha + ";" + 
                this.importe + ";" + 
                this.concepto;
    }
}