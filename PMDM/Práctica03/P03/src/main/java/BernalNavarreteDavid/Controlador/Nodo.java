package BernalNavarreteDavid.Controlador;

/**
 * Clase Nodo:
 * Va a tener tres atributos: 
 * -El objeto que contiene.-El nodo siguiente.
 * -El nodo anterior.
 
 Va a tener que poder hacer lo siguiente:
 -Actualizar el siguiente.
 -Actualizar el anterior.
 -Actualizar el objeto.
 -Devolver el siguiente.
 -Devolver el anterior.
 -Devolver el objeto.
 * @param <Empleado>
 */

public class Nodo<Empleado> {
    // Atributos
    private Empleado objeto;
    private Nodo siguiente;
    private Nodo anterior;
    
    // Constructor
    public Nodo(Empleado nuevoObjeto) {
        this.objeto = nuevoObjeto;
    }
    
    // Métodos
    public void setSiguiente(Nodo nuevoSiguiente) {
        this.siguiente = nuevoSiguiente;
    }
    
    public void setAnterior(Nodo nuevoAnterior) {
        this.anterior = nuevoAnterior;
    }
    
    public void setObjeto(Empleado nuevoObjeto) {
        this.objeto = nuevoObjeto;
    }
    
    public Nodo getSiguiente() {
        return this.siguiente;
    }
    
    public Nodo getAnterior() {
        return this.anterior;
    }
    
    public Empleado getObjeto() {
        return this.objeto;
    }
}
