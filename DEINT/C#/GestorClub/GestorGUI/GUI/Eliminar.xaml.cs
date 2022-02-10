using System;
using System.Collections.Generic;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using GestorGUI.Objetos;

namespace GestorGUI.GUI {
public partial class Eliminar : Window {
    private object EjemplarEliminar;
    public Eliminar() {
        InitializeComponent();
        ListaEjemplares.SelectionMode = SelectionMode.Single;
    }
    
    public void Fill() {
        // Llena la lista con todos los ejemplares en la base de datos.
        List<Ejemplar> lista = new List<Ejemplar>();

        foreach (Ejemplar e in Main.gestor.GetEjemplares()) {
            if (e != null && e.GetDisponible())
                lista.Add(e);
        }
        
        if (lista == null) {
            // Si la lista está vacía
            MessageBoxButton button = MessageBoxButton.OK;
            MessageBoxImage icon = MessageBoxImage.Information;
            MessageBoxResult result;
            result = MessageBox.Show("No hay ejemplares disponibles.", "Sin ejemplares", button, icon,
                MessageBoxResult.Yes);
        }
            
        ListaEjemplares.ItemsSource = lista;
    }

    // Manejo de eventos
    private void MenuPrincipalBoton_OnClicklBoton_OnClick(object sender, RoutedEventArgs e) {
        // Vuelve al menú principal
        Main.HideForm(Main.Eliminar);
        Main.ShowForm(Main.main);
    }

    private void BotonCancelar_OnClick(object sender, RoutedEventArgs e) {
        // Vuelve al menú principal
        Main.HideForm(Main.Eliminar);
        Main.ShowForm(Main.main);
    }
    
    private void BotonEliminar_OnClick(object sender, RoutedEventArgs e) {
        // Si tenemos algún elemento seleccionado, lo eliminamos. Si no, mostramos un mensaje de error
        EjemplarEliminar = ListaEjemplares.SelectedItem;
        if (EjemplarEliminar != null) {
            try {
                if (EjemplarEliminar is Videojuego v)
                    Main.gestor.Remove(v.GetId());
                else if (EjemplarEliminar is Pelicula p)
                    Main.gestor.Remove(p.GetId());
                
                MessageBoxButton button = MessageBoxButton.OK;
                MessageBoxImage icon = MessageBoxImage.Information;
                MessageBoxResult result;
                result = MessageBox.Show("Ejemplar eliminado.", "Eliminado", button, icon,
                    MessageBoxResult.Yes);
                Fill();
            }
            catch (Exception ex) {
                MessageBoxButton button = MessageBoxButton.OK;
                MessageBoxImage icon = MessageBoxImage.Error;
                MessageBoxResult result;
                result = MessageBox.Show("Error al eliminar el ejemplar.", "Error", button, icon,
                    MessageBoxResult.Yes);
            }
        }
        else {
            MessageBoxButton button = MessageBoxButton.OK;
            MessageBoxImage icon = MessageBoxImage.Error;
            MessageBoxResult result;
            result = MessageBox.Show("No has seleccionado ningún ejemplar.", "Error", button, icon,
                MessageBoxResult.Yes);
        }
    }
}
}