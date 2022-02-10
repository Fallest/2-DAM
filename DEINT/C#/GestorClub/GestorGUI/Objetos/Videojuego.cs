using System;
using System.Text;

namespace GestorGUI.Objetos {
class Videojuego : Ejemplar {
    // Atributos
    private string _plataforma;

    /*-------------------------------------------------------------------------------*/
    // Constructor
    public Videojuego(string titulo, string genero, string disponible, string socioId, string plataforma) 
        : base(titulo, genero, disponible, socioId) {
        SetPlataforma(plataforma);
    }
    
    public Videojuego(string id, string titulo, string genero, string disponible, string socioId, string plataforma) 
        : base(id, titulo, genero, disponible, socioId) {
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
        return "Videojuego;" + Id.ToString().PadRight(3) + ";" + Titulo.PadRight(30) + ";" 
               + Genero.PadRight(30) + ";" + Disponible.ToString().PadRight(5) + ";" 
               + SocioId.ToString() + ";" + _plataforma.PadRight(30);
    }
    
    public override string ToString(string purpose) {
        if (purpose.Equals("show"))
            return "Videojuego [ "
                   + "Id: " + Id.ToString()
                   + "; Título: " + Titulo.Trim()
                   + "; Género: " + Genero.Trim()
                   + "; Disponible: " + Disponible.ToString()
                   + "; ID Socio: " + SocioId
                   + "; Plataforma: " + _plataforma.Trim() + " ]";
        else
            return ToString();
    }
    
    /*-------------------------------------------------------------------------------*/
    // Método ToBytes.
    public byte[] ToByteArray() {
        // Byte array de longitud no definida (en UTF8 los caracteres son de entre 1 y 4 bytes)
        return Encoding.UTF8.GetBytes(ToString());
    }
}
}