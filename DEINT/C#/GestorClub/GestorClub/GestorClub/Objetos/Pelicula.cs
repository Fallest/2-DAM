using System;

namespace GestorClub.Objetos {
class Pelicula : Ejemplar {
    // Atributos
    private Fecha _fechaEstreno;

    /*-------------------------------------------------------------------------------*/
    // Constructor
    public Pelicula(string titulo, string genero, string disponible, string socioId, string fechaEstreno) 
        : base(titulo, genero, disponible, socioId) {
        SetFechaEstreno(fechaEstreno);
    }
    
    public Pelicula(string titulo, string genero, bool disponible, int socioId, Fecha fechaEstreno) 
        : base(titulo, genero, disponible, socioId) {
        SetFechaEstreno(fechaEstreno);
    }

    /*-------------------------------------------------------------------------------*/
    // Setters
    public void SetFechaEstreno(string fecha) {
        _fechaEstreno = Fecha.ParseFecha(fecha);
    }
    
    public void SetFechaEstreno(Fecha fechaEstreno) {
        _fechaEstreno = fechaEstreno;
    }
    
    /*-------------------------------------------------------------------------------*/
    // Getters
    public Fecha GetFecha() {
        return _fechaEstreno;
    }
    
    /*-------------------------------------------------------------------------------*/
    // Métodos ToString.
    public override string ToString() {
        return "Pelicula;" + Titulo + ";" + Genero + ";" + Disponible + ";" 
               + SocioId + ";" + _fechaEstreno;
    }
}
}