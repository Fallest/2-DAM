using System.Windows;

namespace GestorGUI.GUI {
public partial class Main : Window {
    public static Gestor gestor = new Gestor();

    public Main() {
        InitializeComponent();
        gestor.Init();
    }
}
}