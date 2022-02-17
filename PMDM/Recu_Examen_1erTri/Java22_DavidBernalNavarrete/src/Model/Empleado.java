package Model;

import Controller.ExceptionManager;

public final class Empleado {

    // Atributos
    private int numero;
    private String nombre;
    private String apellido;
    private String foto;
    private float sueldoBase;
    private float sueldoMaximo;
    private float sueldoExtra;
    private float sueldoReal;
    private int jefeNumero;

    // Constructor
    public Empleado(int numero, String nombre, String apellido, String foto,
            float sueldoBase, float sueldoMaximo, float sueldoExtra, float sueldoReal,
            int jefeNumero) {
        this.numero = numero;
        this.nombre = nombre;
        this.apellido = apellido;
        this.foto = foto;
        this.sueldoBase = sueldoBase;
        this.sueldoMaximo = sueldoMaximo;
        this.sueldoExtra = sueldoExtra;
        setSueldoReal(sueldoReal);
        this.jefeNumero = jefeNumero;
    }

    // Getters y Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public float getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(float sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public float getSueldoMaximo() {
        return sueldoMaximo;
    }

    public void setSueldoMaximo(float sueldoMaximo) {
        this.sueldoMaximo = sueldoMaximo;
    }

    public float getSueldoExtra() {
        return sueldoExtra;
    }

    public void setSueldoExtra(float sueldoExtra) {
        this.sueldoExtra = sueldoExtra;
    }

    public float getSueldoReal() {
        return sueldoReal;
    }

    public void setSueldoReal(float s) {
        if (s <= this.sueldoMaximo) {
            this.sueldoReal = s;
        } else {
            ExceptionManager.getError(10, "");
            this.sueldoReal = sueldoMaximo;
        }
    }

    // toString
    @Override
    public String toString() {
        return "Empleado{" + "numero=" + numero + ", nombre=" + nombre
                + ", apellido=" + apellido + ", foto=" + foto + ", sueldoBase="
                + sueldoBase + ", sueldoMaximo=" + sueldoMaximo + ", sueldoExtra="
                + sueldoExtra + ", sueldoReal=" + sueldoReal + ", jefeNumero=" + jefeNumero + '}';
    }

}
