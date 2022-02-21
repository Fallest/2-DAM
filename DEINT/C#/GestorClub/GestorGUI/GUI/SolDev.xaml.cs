using System;
using System.Collections.Generic;
using System.Windows;
using System.Windows.Controls;
using GestorGUI.Objetos;

namespace GestorGUI.GUI {
    public partial class SolDev : Window {
        public SolDev() {
            InitializeComponent();
            ListaEjemplares.SelectionMode = SelectionMode.Single;
        }
        
        public void Fill() {
            // Llena la lista con todos los ejemplares en la base de datos.
            List<Ejemplar> aux = new List<Ejemplar>();
            List<String> lista = new List<string>();
            // Variable para saber si queremos buscar los disponibles (estamos seleccionando "Solicitar", false) o
            // los prestados (estamos seleccionando "Devolver", true).
            bool estadoDisponibilidad = (bool) RadioSolicitar.IsChecked;

            foreach (Ejemplar e in Main.gestor.GetEjemplares()) {
                if (e != null) {
                    if (estadoDisponibilidad && e.GetDisponible()) {
                        // Queremos solicitar, así que buscamos los disponibles
                        aux.Add(e);
                    }
                    else if (!estadoDisponibilidad && !e.GetDisponible()) {
                        // Queremos devolver, así que buscamos los no disponibles.
                        aux.Add(e);
                    }
                }
            }
        
            if (aux == null) {
                // Si la lista está vacía
                MessageBoxButton button = MessageBoxButton.OK;
                MessageBoxImage icon = MessageBoxImage.Information;
                MessageBoxResult result;
                result = MessageBox.Show(!estadoDisponibilidad ? "No hay ejemplares disponibles." :
                        "No hay ejemplares prestados.",
                    "Sin ejemplares", button, icon, MessageBoxResult.Yes);
            }
            else {    
                foreach (Ejemplar e in aux) {
                    lista.Add(e.ToString("show"));
                }
            }
            
            ListaEjemplares.ItemsSource = lista;
        }

        private void MenuPrincipalBoton_OnClicknClick(object sender, RoutedEventArgs e) {
            // Vuelve al menú principal
            Main.HideForm(Main.SolDev);
            Main.ShowForm(Main.main);
        }

        private void BotonCancelar_OnClick(object sender, RoutedEventArgs e) {
            // Vuelve al menú principal
            Main.HideForm(Main.SolDev);
            Main.ShowForm(Main.main);
        }

        private void RadioSolicitar_OnClick(object sender, RoutedEventArgs e) {
            Fill();
            BotonSolicitar.IsEnabled = true;
            TboxId.IsEnabled = true;
            BotonDevolver.IsEnabled = false;
        }

        private void RadioDevolver_OnClick(object sender, RoutedEventArgs e) {
            Fill();
            BotonSolicitar.IsEnabled = false;
            TboxId.IsEnabled = false;
            BotonDevolver.IsEnabled = true;
        }

        private void BotonSolicitar_OnClick(object sender, RoutedEventArgs e) {
            int socioId;
            int id = 0;
            String ejemplarPrestar = (String) ListaEjemplares.SelectedItem;
            if (Int32.TryParse(TboxId.Text, out socioId)) {
                try {
                    id = Int32.Parse(
                        ejemplarPrestar.Substring(ejemplarPrestar.IndexOf("Id") + 4, 1)
                    );
                    try {
                        Main.gestor.Prestar(id, socioId);
                        MessageBoxResult result = MessageBox.Show("Se ha prestado el ejemplar.",
                            "Ejemplar prestado", MessageBoxButton.OK, MessageBoxImage.Information, MessageBoxResult.Yes);
                    }
                    catch (Exception ex) {
                        // Error: Se ha producido un error al prestar el ejemplar
                        MessageBoxResult result = MessageBox.Show("Se ha producido un error al prestar el ejemplar." +
                                                                  "Compruebe el ID del socio solicitante.",
                            "Error", MessageBoxButton.OK, MessageBoxImage.Error, MessageBoxResult.Yes);
                    }
                }
                catch (Exception ex) {
                    // Error: No se ha seleccionado ningún elemento que solicitar
                    MessageBoxResult result = MessageBox.Show("No se ha seleccionado ningún elemento que solicitar",
                        "Error", MessageBoxButton.OK, MessageBoxImage.Error, MessageBoxResult.Yes);
                }
            }
            else {
                // Error: No se ha introducido un ID de socio correcto
                MessageBoxResult result = MessageBox.Show("No se ha introducido un ID de socio correcto",
                    "Error", MessageBoxButton.OK, MessageBoxImage.Error, MessageBoxResult.Yes);
            }
            Fill();
        }

        private void BotonDevolver_OnClick(object sender, RoutedEventArgs e) {
            int id = 0;
            String ejemplarPrestar = (String) ListaEjemplares.SelectedItem;
            
            try {

                id = Int32.Parse(
                    ejemplarPrestar.Substring(ejemplarPrestar.IndexOf("Id") + 4, 1)
                );
                
                try {
                    Main.gestor.Devolver(id);
                    MessageBoxResult result = MessageBox.Show("Se ha devuelto el ejemplar.",
                        "Ejemplar devuelto", MessageBoxButton.OK, MessageBoxImage.Information, MessageBoxResult.Yes);
                }
                catch (Exception ex) {
                    // Error: Se ha producido un error al devolver el ejemplar
                    MessageBoxResult result = MessageBox.Show("Se ha producido un error al devolver el ejemplar.",
                        "Error", MessageBoxButton.OK, MessageBoxImage.Error, MessageBoxResult.Yes);
                }
            }
            catch (Exception ex) {
                // Error: No se ha seleccionado ningún elemento que solicitar
                MessageBoxResult result = MessageBox.Show("No se ha seleccionado ningún elemento que solicitar",
                    "Error", MessageBoxButton.OK, MessageBoxImage.Error, MessageBoxResult.Yes);
            }
            Fill();
        }
    }
}