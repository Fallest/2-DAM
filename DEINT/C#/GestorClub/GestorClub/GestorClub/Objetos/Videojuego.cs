using System;
using System.Linq;
using System.Text;

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
        return "Videojuego;" + Id.ToString().PadRight(3) + ";" + Titulo.PadRight(30) + ";" 
               + Genero.PadRight(30) + ";" + Disponible.ToString().PadRight(5) + ";" 
               + SocioId + ";" + _plataforma.PadRight(30);
    }
    
    /*-------------------------------------------------------------------------------*/
    // Método ToBytes.
    public byte[] ToByteArray() {
        byte[] idB = BitConverter.GetBytes(Id);
        byte[] tituloB = Encoding.UTF8.GetBytes(Titulo);
        byte[] generoB = Encoding.UTF8.GetBytes(Titulo);
        byte[] disponibilidadB = BitConverter.GetBytes(Disponible);
        byte[] socioIdB = BitConverter.GetBytes(SocioId);
        byte[] fechaB = Encoding.UTF8.GetBytes(_plataforma);

        return (byte[]) idB.
            Concat(tituloB).
            Concat(generoB).
            Concat(disponibilidadB).
            Concat(socioIdB).
            Concat(fechaB);
    }
}
}