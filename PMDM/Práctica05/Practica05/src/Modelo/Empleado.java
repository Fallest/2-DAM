package Modelo;

import java.util.Date;

public final class Empleado {

    /**
     * Clase asociada a los elementos de la base de datos. Tiene que tener los
     * mismos atributos que la tabla Empleado.
     *
     * Los atributos son los siguientes: -Número de empleado (entero, no puede
     * ser nulo). -Nombre del empleado (string, longitud limitada a 20).
     * -Apellido del empleado (string, longitud limitada a 20). -Nombre del
     * archivo de la foto (string, long. limitada a 20). -Sueldo (float, 6
     * cifras enteras y 2 decimales). -Sueldo máximo (float, 6 cifras enteras, 2
     * decimales, mínimo 1000). -Fecha de alta, tipo Fecha.
     */
    // Atributos
    private int número;
    private String nombre;
    private String apellido;
    private String foto;
    private float sueldo;
    private float sueldoMax;
    private Date fechaAlta;

    /*------------------------------------------------------------------------*/
    // Constructor
    public Empleado(int num, String nom, String ape, String foto, float sueldo,
            float sueldoMax, Date fechaAlta) {
        setNúmero(num);
        setNombre(nom);
        setApellido(ape);
        setFoto(foto);
        setSueldo(sueldo);
        setSueldoMax(sueldo);
        setFechaAlta(fechaAlta);
    }

    /*------------------------------------------------------------------------*/
    // Setters
    public void setNúmero(int num) {
        /**
         * Hay que comprobar que el número introducido no sea negativo.
         */
        if (num <= 0) {
            this.número = num;
        }
    }

    public void setNombre(String n) {
        /**
         * Hay que comprobar que no es nulo, no tiene longitud 0, ni tiene una
         * longitud mayor que 20.
         */
        if (n != null && n.length() != 0 && n.length() <= 20) {
            this.nombre = n;
        }
    }

    public void setApellido(String a) {
        /**
         * Hay que comprobar que no es nulo ni tiene una longitud mayor que 20.
         */
        if (a != null && a.length() <= 20) {
            this.apellido = a;
        }
    }

    public void setFoto(String f) {
        /**
         * Hay que comprobar que no es nulo ni tiene una longitud mayor que 20.
         */
        if (f != null && f.length() <= 20) {
            this.foto = f;
        }
    }

    public void setSueldo(float s) {
        /**
         * Hay que comprobar que el sueldo no es negativo, ni mayor que el
         * sueldo máximo, ni mayor que un número de 6 cifras.
         */
        if (s > 0 && s < sueldoMax && s < 1000000) {
            this.sueldo = s;
        }
    }

    public void setSueldoMax(float s) {
        /**
         * Hay que comprobar que el sueldo máximo no es menor que 1000.
         */
        if (s >= 1000) {
            this.sueldoMax = s;
        }
    }

    public void setFechaAlta(Date fecha) {
        this.fechaAlta = fecha;
    }

    /*------------------------------------------------------------------------*/
    // Getters
    public int getNúmero() {
        return número;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFoto() {
        return foto;
    }

    public float getSueldo() {
        return sueldo;
    }

    public float getSueldoMax() {
        return sueldoMax;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    /*------------------------------------------------------------------------*/
}
