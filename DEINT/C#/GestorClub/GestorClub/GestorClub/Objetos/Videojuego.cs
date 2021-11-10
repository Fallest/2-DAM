using System;

namespace GestorClub.Objetos {
class Videojuego : Ejemplar {
    // Atributos
    private string _plataforma;

    /*-------------------------------------------------------------------------------*/
    // Constructor
    public Videojuego(string titulo, string genero, string disponible, string socioId, string plataforma) 
        : base(titulo, genero, disponible, socioId) {
        SetPlataforma(plataforma);
    }
    
    public Videojuego(string titulo, string genero, bool disponible, int socioId, string plataforma) 
        : base(titulo, genero, disponible, socioId) {
        SetPlataforma(plataforma);
    }

    /*-------------------------------------------------------------------------------*/
    // Setters
    public void SetPlataforma(string plataforma) {
        if (plataforma.Length > 30)
            throw new FormatException();
        _plataforma = plataforma;
    }
    
    /*-------------------------------------------------------------------------------*/
    // Getters
    public string GetPlataforma() {
        return _plataforma;
    }

    /*-------------------------------------------------------------------------------*/
    // Métodos ToString.
    public override string ToString() {
        return "Videojuego;" + Titulo + ";" + Genero + ";" + Disponible + ";" 
               + SocioId + ";" + _plataforma;
    }
}
}