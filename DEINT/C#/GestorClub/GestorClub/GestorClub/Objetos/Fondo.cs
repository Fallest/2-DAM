using System;

namespace GestorClub.Objetos {
class Fondo {
    // Array principal, que al inicio tiene nada más que 10 posiciones.
    private Ejemplar[] _fondo;

    /*-------------------------------------------------------------------------------*/
    // Constructor
    public Fondo() {
        _fondo = new Ejemplar[10];
    }
    
    /*-------------------------------------------------------------------------------*/
    // Operaciones añadir y eliminar.
    public void add(Ejemplar e) {
        throw new NotImplementedException();
    }

    public void remove(string titulo, string tipo) {
        throw new NotImplementedException();
    }
    
    /*-------------------------------------------------------------------------------*/
    // Operacion para ordenar.
}
}