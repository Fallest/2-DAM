using System;

namespace GestorClub.Objetos {
class Pelicula : Ejemplar {
    // Atributos
    private Fecha _fechaEstreno;

    /*-------------------------------------------------------------------------------*/
    // Constructor
    public Pelicula(string titulo, string genero, string prestado, string socioId, string fechaEstreno) 
        : base(titulo, genero, prestado, socioId) {
        SetFechaEstreno(fechaEstreno);
    }

    /*-------------------------------------------------------------------------------*/
    // Setters
    public void SetFechaEstreno(string fecha) {
        _fechaEstreno = Fecha.ParseFecha(fecha);
    }
    
    /*-------------------------------------------------------------------------------*/
    // Getters
    public Fecha GetFecha() {
        return _fechaEstreno;
    }
    
    /*-------------------------------------------------------------------------------*/
    // Métodos ToString.
    public override string ToString() {
        return "Pelicula;" + Titulo + ";" + Genero + ";" + Prestado + ";" 
               + SocioId + ";" + _fechaEstreno;
    }
}
}