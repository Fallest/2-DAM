using System;
using System.Collections.Generic;

namespace GestorClub.Objetos {

public class Socio {
    // Atributos
    private int _id;
    private string _nombre;
    private static List<int> _idList;

    // Constructor
    public Socio(string id, string nombre) {
        SetId(id);
        SetNombre(nombre);
        _idList.Add(_id);
    }

    

    // Setters
    private void SetId(string id) {
        if (!(Int32.TryParse(id, out _id))) 
            throw new FormatException();
        
    }
    
    private void SetNombre(string nombre) {
        if (nombre.Length > 30)
            throw new FormatException();
        _nombre = nombre;
    }
    
    // Getters
    public int GetId() {
        return _id;
    }

    public string GetNombre() {
        return _nombre;
    }
}
}