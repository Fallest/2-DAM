using System;

namespace GestorGUI.Objetos {
public abstract class Ejemplar {
    // Atributos
    protected static int CountId;
    protected int Id;
    protected string Titulo;
    protected string Genero;
    protected bool Disponible;
    protected int SocioId;
    /*-------------------------------------------------------------------------------*/
    // Constructor
    protected Ejemplar(string id, string titulo, string genero, string disponible, string socioId) {
        try {
            SetId(id);
            SetTitulo(titulo);
            SetGenero(genero);
            SetDisponible(disponible);
            SetSocioId(socioId);
        }
        catch (FormatException) {
            Console.WriteLine("\t\tSystem: ID para el elemento no válido. Introduzca un ID válido.");
        }
    }
    protected Ejemplar(string titulo, string genero, string disponible, string socioId) {
        try {
            SetId();
            SetTitulo(titulo);
            SetGenero(genero);
            SetDisponible(disponible);
            SetSocioId(socioId);
        }
        catch (FormatException) {
            Console.WriteLine("\t\tSystem: No se admiten más elementos. El sistema necesita ser reiniciado.");
        }
    }

    protected Ejemplar(string titulo, string genero, bool disponible, int socioId) {
        SetTitulo(titulo);
        SetGenero(genero);
        SetDisponible(disponible);
        SetSocioId(socioId);
    }

    /*-------------------------------------------------------------------------------*/
    // Setters
    public void SetId() {
        if (CountId < 100)
            Id = ++CountId;
        else {
            Console.WriteLine("\t\tSystem: El ID no puede ser superior a 100.");
            throw new FormatException();
        }
    }

    public void SetId(string id) {
        int _id;
        if (!Int32.TryParse(id.Trim(), out _id))
            throw new FormatException();
        
        if (_id < 100) {
            Id = _id;
            if (_id > CountId)
                CountId = _id;
        }
        else {
            Console.WriteLine("\t\tSystem: El ID no puede ser superior a 100.");
            throw new FormatException();
        }
    }
    
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
            case "true":
                Disponible = true;
                break;
            case "false":
                Disponible = false;
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
        if (!(Int32.TryParse(socioId.Trim(), out SocioId))) 
            throw new FormatException();
        if (!Disponible) SocioId = 0;
    }
    
    /*-------------------------------------------------------------------------------*/
    // Getters
    public int GetId() {
        return Id;
    }

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

    public abstract string ToString(string s);
    
    // Método parse

    public static Ejemplar Parse(string s) {
        string[] array = s.Split(";");

        if (array[0].ToLower().Trim().Equals("pelicula")) 
            return new Pelicula(array[1], array[2], array[3],
                array[4], array[5], array[6]);
        
        if (array[0].ToLower().Trim().Equals("videojuego")) 
            return new Videojuego(array[1], array[2], array[3],
                array[4], array[5], array[6]);

        // En el caso de que la cadena no tenga el formato adecuado
        throw new FormatException();
    }
}
}