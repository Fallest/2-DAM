package Model;

import java.util.Date;

public class Trabajo {

    // Atributos
    private int numero;
    private String descripcion;
    private float valor;
    private Date fecha;
    private int empnumero;

    // Constructor
    public Trabajo(int numero, String descripcion, float valor, Date fecha, int empnumero) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
        this.empnumero = empnumero;
    }

    // Getters y Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEmpnumero() {
        return empnumero;
    }

    public void setEmpnumero(int empnumero) {
        this.empnumero = empnumero;
    }

    // toString
    @Override
    public String toString() {
        return "Trabajo{" + "numero=" + numero + ", descripcion=" + descripcion + ", valor=" + valor + ", fecha=" + fecha + ", empnumero=" + empnumero + '}';
    }
}
