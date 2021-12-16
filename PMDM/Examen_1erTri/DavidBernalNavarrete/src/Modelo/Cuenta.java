package Modelo;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Clase para las cuentas asociadas a los clientes
 */

public class Cuenta implements ICuenta{
    /*
    numero INTEGER,
    fecha DATE,
    saldo NUMERIC(8,2),
    saldoMinimo NUMERIC(8,2),
    interes NUMERIC(4,2),
    PRIMARY KEY (numero),
    cliCodigo INTEGER CONSTRAINT cuenta_FK_cliente REFERENCES cliente
    */
    // Atributos
    private int numero;
    private Date fecha;
    private float saldo;
    private float saldoMin;
    private float interes;
    private int codCli;
    
    // Constructor
    public Cuenta(int numero, Date fecha, float saldo, float saldoMin, float interes, int codCli) {
        this.numero = numero;
        this.fecha = fecha;
        this.saldo = saldo;
        this.saldoMin = saldoMin;
        this.interes = interes;
        this.codCli = codCli;
    }
    
    // Getters y Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) throws Excepciones {
        if (saldo >= this.saldoMin)
            this.saldo = saldo;
        else throw new Excepciones("saldo");
    }

    public float getSaldoMin() {
        return saldoMin;
    }

    public void setSaldoMin(float saldoMin) {
        if (saldoMin > 0)
            this.saldoMin = saldoMin;
        else throw new IllegalArgumentException();
    }

    public float getInteres() {
        return interes;
    }

    public void setInteres(float interes) {
        this.interes = interes;
    }

    public int getCodCli() {
        return codCli;
    }

    public void setCodCli(int codCli) {
        this.codCli = codCli;
    }
    
    // ToString

    @Override
    public String toString() {
        return this.numero + ";" + 
                this.codCli + ";" + 
                this.fecha + ";" + 
                this.saldo + ";" + 
                this.saldoMin + ";" + 
                this.interes;
    }

    // Comprobar mes.
    @Override
    public boolean comprobarMes() {
        // Devuelve True si están en el mismo mes.
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        Date date1 = this.fecha;
        Date date2 = new Date();
        
        LocalDate fechaEntrada = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        LocalDate fechaActual = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        return ((int) ChronoUnit.MONTHS.between(fechaEntrada, fechaActual)) == 0;
    }
}
