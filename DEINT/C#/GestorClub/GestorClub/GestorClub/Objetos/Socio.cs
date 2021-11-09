using System;
using System.Collections.Generic;

namespace GestorClub.Objetos {

public class Socio {
    // Atributos
    private int _id;
    private string _nombre;

    /*-------------------------------------------------------------------------------*/
    // Constructor
    public Socio(string id, string nombre) {
        SetId(id);
        SetNombre(nombre);
    }
    
    /*-------------------------------------------------------------------------------*/
    // Setters
    public void SetId(string id) {
        if (!(Int32.TryParse(id, out _id))) 
            throw new FormatException();
        if (_id <= 0 || _id > 100) {
            _id = 0;
            throw new FormatException();
        }
    }
    
    public void SetNombre(string nombre) {
        if (nombre.Length > 30)
            throw new FormatException();
        _nombre = nombre;
    }
    
    /*-------------------------------------------------------------------------------*/
    // Getters
    public int GetId() {
        return _id;
    }

    public string GetNombre() {
        return _nombre;
    }
}
}