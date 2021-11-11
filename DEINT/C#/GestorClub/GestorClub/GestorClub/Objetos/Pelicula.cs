using System;
using System.Linq;
using System.Text;

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
        return "Pelicula  ;" + Id.ToString().PadRight(3) + ";" + Titulo.PadRight(30) + ";" 
               + Genero.PadRight(30) + ";" + Disponible.ToString().PadRight(5) + ";" 
               + SocioId.ToString() + ";" + _fechaEstreno;
    }
    
    /*-------------------------------------------------------------------------------*/
    // Método ToBytes.
    public byte[] ToByteArray() {
        byte[] idB = BitConverter.GetBytes(Id);
        byte[] tituloB = Encoding.UTF8.GetBytes(Titulo);
        byte[] generoB = Encoding.UTF8.GetBytes(Titulo);
        byte[] disponibilidadB = BitConverter.GetBytes(Disponible);
        byte[] socioIdB = BitConverter.GetBytes(SocioId);
        byte[] fechaB = Encoding.UTF8.GetBytes(_fechaEstreno.ToString() ?? string.Empty);

        return (byte[]) idB.
            Concat(tituloB).
            Concat(generoB).
            Concat(disponibilidadB).
            Concat(socioIdB).
            Concat(fechaB);
    }
}
}