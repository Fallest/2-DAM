using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace GestorGUI
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class AddWindow : Window
    {
        GestorClub.Gestor _gestor = new GestorClub.Gestor();
        public AddWindow()
        {
            InitializeComponent();
            _gestor.Init();
        }

        private void Label_MouseDown(object sender, MouseButtonEventArgs e)
        {
            if ( ((Label) sender).Name == "labelVideojuego" )
            {
                LabelPlataformas.Visibility = Visibility.Visible;
                TboxPlataformas.Visibility = Visibility.Visible;

                LabelFechaLanzamiento.Visibility = Visibility.Hidden;
                TboxFechaLanzamiento.Visibility = Visibility.Hidden;
            }
            else if ( ((Label)sender).Name == "labelPelicula" )
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
                // Código para añadir un ejemplar con los datos dados
                if (TipoEjemplar.SelectedIndex == 0)
                {
                    // Añadir un videojuego
                    _gestor.Add("videojuego", TboxTitulo.Text, TboxGenero.Text, TboxPlataformas.Text);
                }
                else if (TipoEjemplar.SelectedIndex == 1)
                {
                    // Añadir una película
                    _gestor.Add("pelicula", TboxTitulo.Text, TboxGenero.Text, TboxFechaLanzamiento.Text);
                }
            }
        }
    }
}
