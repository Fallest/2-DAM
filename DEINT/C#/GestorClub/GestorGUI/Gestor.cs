using System;
using System.Collections.Generic;
using GestorGUI.Objetos;

namespace GestorGUI
{
public class Gestor
    {
        // Inicialización del Fondo y de 3 socios.
        private static Socio[] _socios = {
            new Socio("1", "Andrés Pineda"),
            new Socio("2", "María Antonieta"),
            new Socio("3", "David Kaye")
        };

        private static Fondo _fondo = new Fondo(_socios);

        // Leemos los datos almacenados
        public void Init()
        {
            DataManager.LeerDatos(_fondo);
            _fondo.Mostrar();
        }

        public void Add(String tipo, String titulo, String genero, String attr)
        {
            if (tipo.Equals("videojuego")) {
                _fondo.Add(new Videojuego(titulo, genero, "true", "0", attr));
            }
            else
            {
                _fondo.Add(new Pelicula(titulo, genero, "true", "0", attr));
            }
            _fondo.Mostrar();
        }

        public void Remove(int id) {
            _fondo.Remove(id);
        }

        public void Prestar(int id, int socioId) {
            _fondo.Prestar(id, socioId);
        }

        public void Devolver(int id) {
            _fondo.Devolver(id);
        }

        public Ejemplar[] GetEjemplares() {
            return _fondo.GetEjemplares();
        }

        public List<Ejemplar> Filtrar(List<Tuple<int, string>> lista) {
            return _fondo.Filtrar(lista);
        }
    }
}
