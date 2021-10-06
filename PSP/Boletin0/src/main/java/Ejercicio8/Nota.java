package Ejercicio8;

public class Nota {
    private int valor;
    private String asignatura;

    public Nota(String asignatura) {
        this.valor=0;
        this.asignatura=asignatura;
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getAsignatura() {
        return this.asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }
    
    @Override
    public String toString(){
        return "Nota en " + this.asignatura + ": " + this.valor;
    }

    
}
