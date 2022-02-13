package Ejercicio4;

import java.io.Serializable;

public class Numeros implements Serializable {

    private int numero;
    private long cuadrado;
    private long cubo;

    public Numeros(int n) {
        this.numero = n;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }

    @Override
    public String toString() {
        return "Numeros{" + "numero=" + numero + ", cuadrado=" + cuadrado + ", cubo=" + cubo + '}';
    }

}