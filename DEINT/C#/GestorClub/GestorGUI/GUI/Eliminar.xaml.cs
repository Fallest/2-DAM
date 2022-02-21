using System;
using System.Collections.Generic;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using GestorGUI.Objetos;

namespace GestorGUI.GUI {
public partial class Eliminar : Window {
    private String EjemplarEliminar;
    public Eliminar() {
        InitializeComponent();
        ListaEjemplares.SelectionMode = SelectionMode.Single;
    }
    
    public void Fill() {
        // Llena la lista con todos los ejemplares en la base de datos.
        List<Ejemplar> aux = new List<Ejemplar>();
        List<String> lista = new List<string>();
        

        foreach (Ejemplar e in Main.gestor.GetEjemplares()) {
            if (e != null && e.GetDisponible())
                aux.Add(e);
        }
        
        if (aux == null) {
            // Si la lista está vacía
            MessageBoxButton button = MessageBoxButton.OK;
            MessageBoxImage icon = MessageBoxImage.Information;
            MessageBoxResult result;
            result = MessageBox.Show("No hay ejemplares disponibles.", "Sin ejemplares", button, icon,
                MessageBoxResult.Yes);
        }
        else {
                
            foreach (Ejemplar e in aux) {
                lista.Add(e.ToString("show"));
            }
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
        EjemplarEliminar = (String) ListaEjemplares.SelectedItem;
        if (EjemplarEliminar != null) {
            try {
                Main.gestor.Remove(
                    Int32.Parse(
                        EjemplarEliminar.Substring(EjemplarEliminar.IndexOf("Id") + 4, 1)
                        )
                    );
                
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
            MessageBoxResult result = MessageBox.Show("No has seleccionado ningún ejemplar.", "Error", MessageBoxButton.OK, MessageBoxImage.Error,
                MessageBoxResult.Yes);
        }
    }
}
}