using System;
using Microsoft.VisualBasic;

namespace GestorClub.Objetos {
abstract class Ejemplar {
    // Atributos
    protected string Titulo;
    protected string Genero;
    protected bool Prestado;
    protected int SocioId;
    /*-------------------------------------------------------------------------------*/
    // Constructor
    protected Ejemplar(string titulo, string genero, string prestado, string socioId) {
        SetTitulo(titulo);
        SetGenero(genero);
        SetPrestado(prestado);
        SetSocioId(socioId);
    }
    /*-------------------------------------------------------------------------------*/
    // Setters
    private void SetTitulo(string titulo) {
        if (titulo.Length > 30)
            throw new FormatException();
        Titulo = titulo;
    }
    
    private void SetGenero(string genero) {
        if (genero.Length > 30)
            throw new FormatException();
        Genero = genero;
    }

    private void SetPrestado(string prestado) {
        switch (prestado.ToLower().Trim()) {
            case "s":
                Prestado = true;
                break;
            case "n":
                Prestado = true;
                break;
            default:
                throw new FormatException();
        }
    }
    
    private void SetSocioId(string socioId) {
        if (!(Int32.TryParse(socioId, out SocioId))) 
            throw new FormatException();
    }
    
    /*-------------------------------------------------------------------------------*/
    // Getters
    private string GetTitulo() {
        return Titulo;
    }

    private string GetGenero() {
        return Genero;
    }

    private bool GetPrestado() {
        return Prestado;
    }

    private int GetSocioId() {
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