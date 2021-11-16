using System;
using System.Linq;
using System.Text;

namespace GestorClub.Objetos {
class Videojuego : Ejemplar {
    // Atributos
    private static int _longitudRegistro = 191; // Longitud en bytes del registro
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
    
    public int GetLongitudRegistro() {
        return _longitudRegistro;
    }

    /*-------------------------------------------------------------------------------*/
    // Métodos ToString.
    public override string ToString() {
        return "Videojuego;" + Id.ToString().PadRight(3) + ";" + Titulo.PadRight(30) + ";" 
               + Genero.PadRight(30) + ";" + Disponible.ToString().PadRight(5) + ";" 
               + SocioId.ToString() + ";" + _plataforma.PadRight(30);
    }
    
    /*-------------------------------------------------------------------------------*/
    // Método ToBytes.
    public byte[] ToByteArray() {
        // Byte array de longitud no definida (en UTF8 los caracteres son de entre 1 y 4 bytes)
        return Encoding.UTF8.GetBytes(ToString());
    }
}
}