using System;
using System.Collections.Generic;
using System.Windows;
using GestorGUI.Objetos;

namespace GestorGUI.GUI {
    public partial class Listado : Window {
        public Listado() {
            InitializeComponent();
        }

        private void Fill() {
            // Llena la lista con todos los ejemplares en la base de datos.
            List<Ejemplar> lista = new List<Ejemplar>();
            List<Tuple<int, string>> filtro = new List<Tuple<int, string>>();

            switch (Disponibilidad.SelectedIndex) {
                case 0:
                    filtro.Add(new Tuple<int, string>(4, "ambos"));    
                    break;
                case 1:
                    filtro.Add(new Tuple<int, string>(4, "n"));
                    break;
                case 2:
                    filtro.Add(new Tuple<int, string>(4, "s"));
                    break;
            }

            filtro.Add(new Tuple<int, string>(2, Buscador.Text));

            switch (Tipo.SelectedIndex) {
                case 0:
                    filtro.Add(new Tuple<int, string>(1, "ambos"));    
                    break;
                case 1:
                    filtro.Add(new Tuple<int, string>(1, "videojuego"));
                    break;
                case 2:
                    filtro.Add(new Tuple<int, string>(1, "pelicula"));
                    break;
            }
            
            lista = Main.gestor.Filtrar(filtro);

            if (lista == null) {
                // Si la lista está vacía
                MessageBoxButton button = MessageBoxButton.OK;
                MessageBoxImage icon = MessageBoxImage.Information;
                MessageBoxResult result;
                result = MessageBox.Show("No hay datos para su búsqueda.", "Sin datos", button, icon,
                    MessageBoxResult.Yes);
            }
            
            ListaEjemplares.ItemsSource = lista;
        }

        private void MenuPrincipalBoton_OnClicklick(object sender, RoutedEventArgs e) {
            // Vuelve al menú principal
            Main.HideForm(Main.Listado);
            Main.ShowForm(Main.main);
        }

        private void BotonFiltrar_OnClick(object sender, RoutedEventArgs e) {
            Fill();
        }
    }
}