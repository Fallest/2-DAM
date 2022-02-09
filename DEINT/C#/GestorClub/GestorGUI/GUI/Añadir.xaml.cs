using System;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace GestorGUI.GUI
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class AddWindow : Window
    {
        public AddWindow()
        {
            InitializeComponent();
        }

        private void Label_MouseDown(object sender, MouseButtonEventArgs e)
        {
            if ( ((Label) sender).Name == "LabelVideojuego" )
            {
                LabelPlataformas.Visibility = Visibility.Visible;
                TboxPlataformas.Visibility = Visibility.Visible;

                LabelFechaLanzamiento.Visibility = Visibility.Hidden;
                TboxFechaLanzamiento.Visibility = Visibility.Hidden;
            }
            else if ( ((Label)sender).Name == "LabelPelicula" )
            {
                LabelFechaLanzamiento.Visibility = Visibility.Visible;
                TboxFechaLanzamiento.Visibility = Visibility.Visible;

                LabelPlataformas.Visibility = Visibility.Hidden;
                TboxPlataformas.Visibility = Visibility.Hidden;
            }
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            if ( ((Button) sender).Content.ToString() == "Limpiar" )
            {
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

            else if (((Button)sender).Content.ToString() == "Cancelar")
            {
                // Código para volver a la pantalla del menú
            }

            else if (((Button)sender).Content.ToString() == "Añadir")
            {
                Console.Write("patata");
                // Código para añadir un ejemplar con los datos dados
                if (TipoEjemplar.SelectedIndex == 0)
                {
                    // Añadir un videojuego
                    Main.gestor.Add("videojuego", TboxTitulo.Text, TboxGenero.Text, TboxPlataformas.Text);
                }
                else if (TipoEjemplar.SelectedIndex == 1)
                {
                    // Añadir una película
                    Main.gestor.Add("pelicula", TboxTitulo.Text, TboxGenero.Text, TboxFechaLanzamiento.Text);
                }
            }
        }
    }
}
