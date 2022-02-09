using System.Windows;

namespace GestorGUI.GUI {
    public partial class SolDev : Window {
        public SolDev() {
            InitializeComponent();
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
    }
}