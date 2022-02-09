using System;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace GestorGUI.GUI {
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class Agregar : Window {
        public Agregar() {
            InitializeComponent();
        }

        private void Label_MouseDown(object sender, MouseButtonEventArgs e) {
            if (((Label) sender).Name == "LabelVideojuego") {
                LabelPlataformas.Visibility = Visibility.Visible;
                TboxPlataformas.Visibility = Visibility.Visible;

                LabelFechaLanzamiento.Visibility = Visibility.Hidden;
                TboxFechaLanzamiento.Visibility = Visibility.Hidden;
            }
            else if (((Label) sender).Name == "LabelPelicula") {
                LabelFechaLanzamiento.Visibility = Visibility.Visible;
                TboxFechaLanzamiento.Visibility = Visibility.Visible;

                LabelPlataformas.Visibility = Visibility.Hidden;
                TboxPlataformas.Visibility = Visibility.Hidden;
            }
        }

        private void MenuPrincipalBoton_OnClick(object sender, RoutedEventArgs e) {
            // Vuelve al menú principal
            Main.HideForm(Main.Agregar);
            Main.ShowForm(Main.main);
        }

        private void BotonCancelar_OnClick(object sender, RoutedEventArgs e) {
            // Volver al menú principal
            Main.HideForm(Main.Agregar);
            Main.ShowForm(Main.main);
        }


        private void BotonLimpiar_OnClick(object sender, RoutedEventArgs e) {
            Limpiar();
        }

        private void BotonAgregar_OnClickAgregar_OnClick(object sender, RoutedEventArgs e) {
            // Código para añadir un ejemplar con los datos dados
            MessageBoxButton button = MessageBoxButton.OK;
            MessageBoxImage icon = MessageBoxImage.Information;
            MessageBoxResult result;
            
            
            if (TipoEjemplar.SelectedIndex == 0) {
                // Añadir un videojuego
                try {
                    Main.gestor.Add("videojuego", TboxTitulo.Text, TboxGenero.Text, TboxPlataformas.Text);
                    result = MessageBox.Show("Añadido un videojuego.", "Ejemplar añadido", button, icon,
                        MessageBoxResult.Yes);
                }
                catch (Exception ex) {
                    result = MessageBox.Show("Ha ocurrido un error. Compruebe que los datos son correctos",
                        "Error", button, MessageBoxImage.Error, MessageBoxResult.Yes);
                }
            }
            else if (TipoEjemplar.SelectedIndex == 1) {
                // Añadir una película
                try {
                    Main.gestor.Add("pelicula", TboxTitulo.Text, TboxGenero.Text, TboxFechaLanzamiento.Text);
                    result = MessageBox.Show("Añadido una película.", "Ejemplar añadido", button, icon,
                        MessageBoxResult.Yes);
                }
                catch (Exception ex) {
                    result = MessageBox.Show("Ha ocurrido un error. Compruebe que los datos son correctos",
                        "Error", button, MessageBoxImage.Error, MessageBoxResult.Yes);
                }
            }
            // Limpiamos los campos
            Limpiar();
        }

        private void Limpiar() {
            // Reseteamos el tipo
            TipoEjemplar.SelectedIndex = 0;
            LabelPlataformas.Visibility = Visibility.Visible;
            TboxPlataformas.Visibility = Visibility.Visible;
            LabelFechaLanzamiento.Visibility = Visibility.Hidden;
            TboxFechaLanzamiento.Visibility = Visibility.Hidden;

            // Limpiamos todos los campos
            TboxTitulo.Text = "";
            TboxGenero.Text = "";
            TboxPlataformas.Text = "";
            TboxFechaLanzamiento.Text = "DD/MM/YYY";
        }
    }
}