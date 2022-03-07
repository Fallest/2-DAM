package Ejercicio1;

import java.io.Serializable;

public class Numeros implements Serializable {

    int numero;
    long cuadrado;
    long factorial;

    public Numeros() {
        super();
    }

    public Numeros(int numero) {
        super();
        this.numero = numero;
    }

    public Numeros(int numero, long cuadrado, long factorial) {
        super();
        this.numero = numero;
        this.cuadrado = cuadrado;
        this.factorial = factorial;
    }

    public int getNumero() {
        return numero;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public long getfactorial() {
        return factorial;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public void setfactorial(long factorial) {
        this.factorial = factorial;
    }

    @Override
    public String toString() {
        return "Numeros{" + "numero=" + numero + ", cuadrado=" + cuadrado + ", factorial=" + factorial + '}';
    }
    
    
}
