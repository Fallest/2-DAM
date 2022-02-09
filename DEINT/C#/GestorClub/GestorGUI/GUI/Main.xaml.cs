using System;
using System.Diagnostics;
using System.Windows;

namespace GestorGUI.GUI {
public partial class Main : Window {
    public static Gestor gestor = new Gestor();
    // Variables para almacenar las ventanas.
    public static Main main;
    public static Listado Listado;
    public static Agregar Agregar;
    public static Eliminar Eliminar;
    public static SolDev SolDev;

    public Main() {
        InitializeComponent();
        Debug.Write("Patata si funciona");
        Show();
        main = this;
        Listado = new Listado();
        Agregar = new Agregar();
        Eliminar = new Eliminar();
        SolDev = new SolDev();
        gestor.Init();
    }

    // Manejo de eventos
    private void BotonSalir_OnClick(object sender, RoutedEventArgs e) {
        // Botón salir
        Environment.Exit(0);
    }
    private void BotonListado_OnClick(object sender, RoutedEventArgs e) {
        // Botón para cambiar a la ventana de listado
        HideForm(main);
        ShowForm(Listado);
    }
    private void BotonSolDev_OnClick(object sender, RoutedEventArgs e) {
        // Botón para cambiar a la ventana de Solicitudes y devoluciones
        HideForm(main);
        ShowForm(SolDev);
    }
    private void BotonAñadir_OnClick(object sender, RoutedEventArgs e) {
        // Botón para cambiar a la ventana de Agregar
        HideForm(main);
        ShowForm(Agregar);
     }
 
     private void BotonEliminar_OnClick(object sender, RoutedEventArgs e) {
         // Botón para cambiar a la ventana de Eliminar
         HideForm(main);
         ShowForm(Eliminar);
     }
    //--------------------------------------------------
    // Variables y métodos para cambiar entre ventanas.
    private static int _height;
    private static int _width;
    private static double _positionX;
    private static double _positionY;
    
    public static void ShowForm(Window form)
    {
        form.Visibility = Visibility.Visible;
        form.Height = _height;
        form.Width = _width;
        form.Left = _positionX;
        form.Top = _positionY;
    }

    public static void HideForm(Window form)
    {
        _height = (int) form.Height;
        _width = (int) form.Width;
        _positionX = form.Left;
        _positionY = form.Top;

        form.Height = 0;
        form.Width = 0;
        
        form.Visibility = Visibility.Hidden;
    }
}
}