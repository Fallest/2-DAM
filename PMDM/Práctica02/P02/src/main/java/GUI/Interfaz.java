package GUI;

import Objects.Videojuego;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Interfaz extends JFrame{
    // Atributos:
    
        // Nodo actual
    private static Videojuego actual;
    
        // Valores para los campos de texto
    private String titulo;
    private String fecha;
    private String desarrolladora;
    private String precio;
    private String oferta;
    
        // Botones para las acciones
    private JButton anteriorButton;
    private JButton siguienteButton;
    private JButton cancelarButton;
    private JButton aceptarButton;

        // Etiquetas para los campos del Videojuego
    private JLabel tituloLabel;
    private JLabel fechaLabel;
    private JLabel desarrolladoraLabel;
    private JLabel precioLabel;
    private JLabel ofertaLabel;

        // Strings para las etiquetas
    private static String tituloString = "Título: ";
    private static String fechaString = "Fecha de lanzamiento: ";
    private static String desarrolladoraString = "Desarrolladora: ";
    private static String precioString = "Precio: ";
    private static String ofertaString = "En oferta: ";
    
        // Strings para los botones
    private static String anteriorString = "Anterior";
    private static String siguienteString = "Siguiente";
    private static String cancelarString = "Cancelar";
    private static String aceptarString = "Aceptar";
    
        // Text fields para los atributos de los Videojuegos
    private TextField tituloField;
    private TextField fechaField;
    private TextField desarrolladoraField;
    private TextField precioField;
    private TextField ofertaField;

    /*---------------------------------------------------------------*/
    // Constructores: 
    public Interfaz() {
        super("Listado de Videojuegos");
        
            // Crear 10 juegos
        Videojuego vj1 = new Videojuego("League of legends", "Riot Games", "29/02/2020", "0.0", "s");
        Videojuego vj2 = new Videojuego("GOD EATER 3", "Marvelous", "08/02/2019", "60.0", "n");
        Videojuego vj3 = new Videojuego("Dying Light", "Techland", "27/01/2015", "20.0", "s");
        Videojuego vj4 = new Videojuego("Hacknet", "Matt Trobbiani", "23/06/2014", "1.0", "n");
        Videojuego vj5 = new Videojuego("Euro Truck Simulator 2", "SCS Software", "19/10/2012", " 18.0", "s");
        Videojuego vj6 = new Videojuego("Terraria", "Re-Logic", "16/05/2011", "12.0", "n");
        Videojuego vj7 = new Videojuego("Minecraft", "Mojang Studios", "17/05/2009", "24.0", "s");
        Videojuego vj8 = new Videojuego("Osu!", "Dean Herbert", "16/09/2007", "0.0", "s");
        Videojuego vj9 = new Videojuego("Portal 2", "Valve", "18/12/2006", "20.0", "n");
        Videojuego vj10 = new Videojuego("Forza Horizon 5", "Playground Games", "09/11/2021", "59.99", "n");

        actual = Videojuego.getFirst();
        // Crear los botones
        anteriorButton = new JButton(anteriorString); 
        siguienteButton = new JButton(siguienteString);
        cancelarButton = new JButton(cancelarString);
        aceptarButton = new JButton(aceptarString);
        
        // Crear las etiquetas
        tituloLabel = new JLabel(tituloString);
        fechaLabel = new JLabel(fechaString);
        desarrolladoraLabel = new JLabel(desarrolladoraString);
        precioLabel = new JLabel(precioString);
        ofertaLabel = new JLabel(ofertaString);

        // Dependiendo del nodo actual, serán modificables o no
        // Además, creamos los TextFields donde se mostrará el texto
        
        tituloField = new TextField(actual.getTitulo(), 40);
        fechaField = new TextField(actual.getFecha(), 40);
        desarrolladoraField = new TextField(actual.getDesarrolladora(), 40);
        precioField = new TextField(String.valueOf(actual.getPrecio()), 40);
        ofertaField = new TextField(actual.getOferta(), 40);

        tituloField.setEditable(false);
        tituloField.setBackground(Color.LIGHT_GRAY);
        fechaField.setEditable(false);
        fechaField.setBackground(Color.LIGHT_GRAY);
        desarrolladoraField.setEditable(false);
        desarrolladoraField.setBackground(Color.LIGHT_GRAY);
        precioField.setEditable(false);
        precioField.setBackground(Color.LIGHT_GRAY);
        ofertaField.setEditable(false);
        ofertaField.setBackground(Color.LIGHT_GRAY);
        
        // Coloca las etiquetas en un panel
        JPanel labelPane = new JPanel();
        labelPane.setLayout(new GridLayout(0, 1)); // 0 filas, 1 columna
        labelPane.add(tituloLabel);
        labelPane.add(fechaLabel);
        labelPane.add(desarrolladoraLabel);
        labelPane.add(precioLabel);
        labelPane.add(ofertaLabel);

        // Coloca los campos en otro panel
        JPanel fieldPane = new JPanel();
        fieldPane.setLayout(new GridLayout(0, 1)); // 0 filas, 1 columna
        fieldPane.add(tituloField);
        fieldPane.add(fechaField);
        fieldPane.add(desarrolladoraField);
        fieldPane.add(precioField);
        fieldPane.add(ofertaField);
        
        // Coloca los botones (atrás, siguiente) en otro panel
        JPanel moveButtonsPane = new JPanel();
        moveButtonsPane.setLayout(new BorderLayout()); // 1 fila, 0 columnas
        moveButtonsPane.add(anteriorButton, BorderLayout.WEST);
        moveButtonsPane.add(siguienteButton, BorderLayout.EAST);
        
        // Coloca los botones (cancelar, aceptar) en otro panel
        JPanel actionButtonsPane = new JPanel();
        actionButtonsPane.setLayout(new BorderLayout());
        actionButtonsPane.add(cancelarButton, BorderLayout.WEST);
        actionButtonsPane.add(aceptarButton, BorderLayout.EAST);

        // Coloca los 4 botones en un mismo panel
        JPanel buttons = new JPanel();
        buttons.setLayout(new BorderLayout());
        buttons.add(moveButtonsPane, BorderLayout.CENTER);
        
        // Incluye tres paneles en otro panel permanente.
        // Las etiquetas van a la izquierda y los textFields a la derecha.
        // Los botones van abajo
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(labelPane, BorderLayout.CENTER);
        contentPane.add(fieldPane, BorderLayout.EAST);
        contentPane.add(buttons, BorderLayout.SOUTH);
        
        setContentPane(contentPane);
        
        // Acciones al presionar Enter en los textFields (solo si están activos)
        tituloField.addActionListener((ActionEvent e) -> {
            String n = tituloField.getText();
            titulo = n;
        });
        fechaField.addActionListener((ActionEvent e) -> {
            String n = fechaField.getText();
            fecha = n;
        });
        desarrolladoraField.addActionListener((ActionEvent e) -> {
            String n = desarrolladoraField.getText();
            desarrolladora = n;
        });
        precioField.addActionListener((ActionEvent e) -> {
            String n = precioField.getText();
            precio = n;
        });
        ofertaField.addActionListener((ActionEvent e) -> {
            String n = ofertaField.getText();
            oferta = n;
        });
        
        // Al inicio
        anteriorButton.setEnabled(false);
        siguienteButton.setEnabled(true);
        
        // Acciones para los botones "anterior" y "siguiente" (si están activos)
        anteriorButton.addActionListener(new ActionListener() {  // Evento de boton.
            @Override
            public void actionPerformed(ActionEvent e) {
                actual = actual.getPrevious();
                // Comprobaciones para activar/desactivar el botón "Anterior"
                if (actual.getPrevious() == null) anteriorButton.setEnabled(false);
                else  {
                    anteriorButton.setEnabled(true);
                    
                    tituloField.setText(actual.getTitulo());
                    fechaField.setText(actual.getFecha());
                    desarrolladoraField.setText(actual.getDesarrolladora());
                    precioField.setText(String.valueOf(actual.getPrecio()) + " €");
                    ofertaField.setText(actual.getOferta());
                }
                
                siguienteButton.setEnabled(true);
                
                tituloField.setEditable(false);
                tituloField.setBackground(Color.LIGHT_GRAY);
                fechaField.setEditable(false);
                fechaField.setBackground(Color.LIGHT_GRAY);
                desarrolladoraField.setEditable(false);
                desarrolladoraField.setBackground(Color.LIGHT_GRAY);
                precioField.setEditable(false);
                precioField.setBackground(Color.LIGHT_GRAY);
                ofertaField.setEditable(false);
                ofertaField.setBackground(Color.LIGHT_GRAY);
                
                if (siguienteButton.isEnabled()) {
                    buttons.remove(actionButtonsPane);
                    buttons.validate();
                    contentPane.validate();
                }
            }
        });
        
        siguienteButton.addActionListener(new ActionListener() {  // Evento de boton.
            @Override
            public void actionPerformed(ActionEvent e) {
                actual = actual.getNext();
                // Comprobaciones para activar/desactivar el botón "Siguiente"
                if (actual == null) siguienteButton.setEnabled(false);
                else {
                    siguienteButton.setEnabled(true);

                    tituloField.setText(actual.getTitulo());
                    fechaField.setText(actual.getFecha());
                    desarrolladoraField.setText(actual.getDesarrolladora());
                    precioField.setText(String.valueOf(actual.getPrecio()) + " €");
                    ofertaField.setText(actual.getOferta());
                }
                anteriorButton.setEnabled(true);

                tituloField.setEditable(false);
                tituloField.setBackground(Color.LIGHT_GRAY);
                fechaField.setEditable(false);
                fechaField.setBackground(Color.LIGHT_GRAY);
                desarrolladoraField.setEditable(false);
                desarrolladoraField.setBackground(Color.LIGHT_GRAY);
                precioField.setEditable(false);
                precioField.setBackground(Color.LIGHT_GRAY);
                ofertaField.setEditable(false);
                ofertaField.setBackground(Color.LIGHT_GRAY);
                
                if (!siguienteButton.isEnabled()) {
                    buttons.add(moveButtonsPane, BorderLayout.CENTER);
                    buttons.add(actionButtonsPane, BorderLayout.SOUTH);
                    buttons.validate();
                    contentPane.validate();
                }
            }
        });
        
        
    }

    public static void main(String[] args) {
        // Crear la aplicación
        final Interfaz app = new Interfaz();
        
        // Terminar el programa al cerrar la ventana
        app.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        app.pack();
        app.setVisible(true);
    }
}
