using System.Windows;

namespace GestorGUI.GUI {
public partial class Eliminar : Window {
    public Eliminar() {
        InitializeComponent();
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
}
}