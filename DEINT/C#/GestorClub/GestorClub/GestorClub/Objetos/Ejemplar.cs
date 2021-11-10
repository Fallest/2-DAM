using System;
using System.Runtime.InteropServices;
using Microsoft.VisualBasic;

namespace GestorClub.Objetos {
abstract class Ejemplar {
    // Atributos
    protected string Titulo;
    protected string Genero;
    protected bool Disponible;
    protected int SocioId;
    /*-------------------------------------------------------------------------------*/
    // Constructor
    protected Ejemplar(string titulo, string genero, string disponible, string socioId) {
        SetTitulo(titulo);
        SetGenero(genero);
        SetDisponible(disponible);
        SetSocioId(socioId);
    }

    protected Ejemplar(string titulo, string genero, bool disponible, int socioId) {
        SetTitulo(titulo);
        SetGenero(genero);
        SetDisponible(disponible);
        SetSocioId(socioId);
    }

    /*-------------------------------------------------------------------------------*/
    // Setters
    public void SetTitulo(string titulo) {
        if (titulo.Length > 30)
            throw new FormatException();
        Titulo = titulo;
    }
    
    public void SetGenero(string genero) {
        if (genero.Length > 30)
            throw new FormatException();
        Genero = genero;
    }

    public void SetDisponible(string disponible) {
        switch (disponible.ToLower().Trim()) {
            case "s":
                Disponible = true;
                break;
            case "n":
                Disponible = true;
                break;
            default:
                throw new FormatException();
        }
    }
    
    public void SetDisponible(bool disponible) {
        Disponible = disponible;
    }
    
    public void SetSocioId(int socioId) {
        SocioId = socioId;
        if (!Disponible) SocioId = 0;
    }
    
    public void SetSocioId(string socioId) {
        if (!(Int32.TryParse(socioId, out SocioId))) 
            throw new FormatException();
        if (!Disponible) SocioId = 0;
    }
    
    /*-------------------------------------------------------------------------------*/
    // Getters
    public string GetTitulo() {
        return Titulo;
    }

    public string GetGenero() {
        return Genero;
    }

    public bool GetDisponible() {
        return Disponible;
    }

    public int GetSocioId() {
        return SocioId;
    }
    
    /*-------------------------------------------------------------------------------*/
    // Método abstracto ToString.
    public abstract override string ToString();
    
    // Método parse

    public Ejemplar Parse(string s) {
        string[] array = s.Split();

        if (array[0].ToLower().Trim().Equals("pelicula"))
            return new Pelicula(array[1], array[2], array[3],
                array[4], array[5]);
        if (array[0].ToLower().Trim().Equals("videojuego"))
            return new Videojuego(array[1], array[2], array[3],
                array[4], array[5]);

        return null;
    }
    
}
}