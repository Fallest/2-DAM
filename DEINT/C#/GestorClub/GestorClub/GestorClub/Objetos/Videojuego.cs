using System;

namespace GestorClub.Objetos {
class Videojuego : Ejemplar {
    // Atributos
    private string _plataforma;

    /*-------------------------------------------------------------------------------*/
    // Constructor
    public Videojuego(string titulo, string genero, string prestado, string socioId, string plataforma) 
        : base(titulo, genero, prestado, socioId) {
        SetPlataforma(plataforma);
    }

    /*-------------------------------------------------------------------------------*/
    // Setters
    public void SetPlataforma(string plataforma) {
        if (plataforma.Length > 40)
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
        return "Videojuego;" + Titulo + ";" + Genero + ";" + Prestado + ";" 
               + SocioId + ";" + _plataforma;
    }
}
}