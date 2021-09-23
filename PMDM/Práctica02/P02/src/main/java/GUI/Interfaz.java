package GUI;

import Objects.Videojuego;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Interfaz extends JFrame{
    // Atributos:
    
        // Nodo actual
    private static Videojuego actual;
    
        // Elementos de la estructura
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
    
        // Valores para los campos de texto
    private String titulo;
    private String fecha;
    private String desarrolladora;
    private String precio;
    private String oferta;
    
    /*---------------------------------------------------------------*/
    // Constructores: 
    public Interfaz() {
        
        // Primero tenemos que crear el JFrame con super()
        super("Listado de Videojuegos");
        
        // Asignamos el actual al primero de la estructura
        actual = Videojuego.getFirst();
        
        // Creamos los JButtons
        JButton anteriorButton = new JButton("Anterior"); 
        JButton nuevoButton = new JButton("Nuevo");
        JButton siguienteButton = new JButton("Siguiente");
        JButton cancelarButton = new JButton("Cancelar");
        JButton aceptarButton = new JButton("Aceptar");
        
        // Creamos las JLabels
        JLabel tituloLabel = new JLabel("Título: ");
        JLabel fechaLabel = new JLabel("Fecha de lanzamiento: ");
        JLabel desarrolladoraLabel = new JLabel("Desarroladora: ");
        JLabel precioLabel = new JLabel("Precio: ");
        JLabel ofertaLabel = new JLabel("En oferta: ");
        
        // Creamos los TextFields
        // Al inicio del programa, tienen los valores del primer elemento (actual)
        TextField tituloField = new TextField(actual.getTitulo(), 30);
        TextField fechaField = new TextField(actual.getFecha(), 10);
        TextField desarrolladoraField = new TextField(actual.getDesarrolladora(), 30);
        TextField precioField = new TextField(String.valueOf(actual.getPrecio()), 6);
        TextField ofertaField = new TextField(actual.getOferta(), 4);
        // Al inicio del programa, ninguno es editable, y se les modifica el
        // color para hacerlo visible
        tituloField.setEditable(false);
        tituloField.setFocusable(false);
        tituloField.setBackground(Color.LIGHT_GRAY);
        
        fechaField.setEditable(false);
        fechaField.setFocusable(false);
        fechaField.setBackground(Color.LIGHT_GRAY);
        
        desarrolladoraField.setEditable(false);
        desarrolladoraField.setFocusable(false);
        desarrolladoraField.setBackground(Color.LIGHT_GRAY);
        
        precioField.setEditable(false);
        precioField.setFocusable(false);
        precioField.setBackground(Color.LIGHT_GRAY);
        
        ofertaField.setEditable(false);
        ofertaField.setFocusable(false);
        ofertaField.setBackground(Color.LIGHT_GRAY);
        
        // Creación de los paneles independientes
        
            // Panel de JLabels (Siempre va a estar presente)
        JPanel labelPane = new JPanel();
        labelPane.setLayout(new GridLayout(0, 1)); // 0 filas, 1 columna
        labelPane.add(tituloLabel);
        labelPane.add(fechaLabel);
        labelPane.add(desarrolladoraLabel);
        labelPane.add(precioLabel);
        labelPane.add(ofertaLabel);

            // Panel de TextFields (Siempre va a estar presente)
        JPanel fieldPane = new JPanel();
        fieldPane.setLayout(new GridLayout(0, 1)); // 0 filas, 1 columna
        fieldPane.add(tituloField);
        fieldPane.add(fechaField);
        fieldPane.add(desarrolladoraField);
        fieldPane.add(precioField);
        fieldPane.add(ofertaField);
        
            // Panel de JButtons "Anterior", "Siguiente" y "Nuevo"
            // (solo presente mientras no se esté editando un nuevo registro)
        JPanel moveButtonsPane = new JPanel();
        moveButtonsPane.setLayout(new BorderLayout());
        moveButtonsPane.add(anteriorButton, BorderLayout.WEST);
        moveButtonsPane.add(nuevoButton, BorderLayout.CENTER);
        moveButtonsPane.add(siguienteButton, BorderLayout.EAST);
        
            // Panel de JButtons "Cancelar" y "Aceptar"
            // (solo presente tras hacer clic en "Nuevo", y desaparecen
            // al hacer clic en "Cancelar" o en "Aceptar")
        JPanel actionButtonsPane = new JPanel();
        actionButtonsPane.setLayout(new BorderLayout());
        actionButtonsPane.add(cancelarButton, BorderLayout.WEST);
        actionButtonsPane.add(aceptarButton, BorderLayout.EAST);
        
            // Incluye tres paneles en otro panel permanente.
            // Las etiquetas van a la izquierda y los textFields a la derecha.
            // Los botones van abajo
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(labelPane, BorderLayout.CENTER);
        contentPane.add(fieldPane, BorderLayout.EAST);
        contentPane.add(moveButtonsPane, BorderLayout.SOUTH); 
            // Al inicio del programa tenemos los botones de moveButtonsPane
        
            // Al inicio, el botón "Anterior" estará desactivado (no hay anterior)
        anteriorButton.setEnabled(false);
        
            // Añadimos el contenido al JFrame
        this.setContentPane(contentPane);
        
        // ----- FIN DE LA INICIALIZACIÓN DE OBJETOS ----- //
        /*-------------------------------------------------*/
        // ----- INICIO DEL CONTROL DE EVENTOS ----- //
        
        // Acciones de JButtons de moveButtonsPane
        
            // JButton "Anterior"
        anteriorButton.addActionListener(new ActionListener() {  // Evento de boton.
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Control de casos límite
                if (actual.getPrevious() == null) 
                    // Si estamos en el primero, desactiamos el botón
                    anteriorButton.setEnabled(false);
                else {
                    // Si estamos en cualquier otro, pasamos al anterior
                    actual = actual.getPrevious();
                    if (!siguienteButton.isEnabled())
                        // Si el botón "Siguiente" está desactivado
                        // hay que activarlo (puesto que hemos retrocido y ya
                        // volvería a existir un elemento siguiente)
                        siguienteButton.setEnabled(true);
                    if (actual.getPrevious() == null)
                        // Volvemos a comprobar si estamos en el primero
                        anteriorButton.setEnabled(false);

                }
                
                // Hay que actualizar el texto de los TextFields
                tituloField.setText(actual.getTitulo());
                fechaField.setText(actual.getFecha());
                desarrolladoraField.setText(actual.getDesarrolladora());
                precioField.setText(String.valueOf(actual.getPrecio()) + " €");
                ofertaField.setText(actual.getOferta());
            }
        });
        
            // JButton "Nuevo"
            nuevoButton.addActionListener(new ActionListener() {  // Evento de boton.
            @Override
            public void actionPerformed(ActionEvent e) {
                // Primero hacemos desaparecer moveButtonsPane
                contentPane.remove(moveButtonsPane);
                contentPane.validate();
                // Luego colocamos actionButtonsPane
                contentPane.add(actionButtonsPane, BorderLayout.SOUTH);
                contentPane.validate();
                
                // Cambiamos los TextFields por unos vacíos
                tituloField.setText("");
                fechaField.setText("");
                desarrolladoraField.setText("");
                precioField.setText("");
                ofertaField.setText("");
                // Activamos los TextFields y les cambiamos el color
                tituloField.setEditable(true);
                tituloField.setFocusable(true);
                tituloField.setBackground(Color.WHITE);

                fechaField.setEditable(true);
                fechaField.setFocusable(true);
                fechaField.setBackground(Color.WHITE);

                desarrolladoraField.setEditable(true);
                desarrolladoraField.setFocusable(true);
                desarrolladoraField.setBackground(Color.WHITE);

                precioField.setEditable(true);
                precioField.setFocusable(true);
                precioField.setBackground(Color.WHITE);

                ofertaField.setEditable(true);
                ofertaField.setFocusable(true);
                ofertaField.setBackground(Color.WHITE);
            }
        });
            
            // JButton "Siguiente"
        siguienteButton.addActionListener(new ActionListener() {  // Evento de boton.
            @Override
            public void actionPerformed(ActionEvent e) {
                // Comprobar límites
                if (actual.getNext() == null)
                    // Si estamos en el último elemento desactivamos el botón
                    siguienteButton.setEnabled(false);
                else {
                    // En caso de que sea cualquier otro, pasamos al siguiente
                    actual = actual.getNext();
                    if (!anteriorButton.isEnabled())
                        // Si el botón "Anterior" está desactivado, lo activamos
                        // porque al haber avanzado, ya existe uno posterior
                        anteriorButton.setEnabled(true);
                    if (actual.getNext() == null)
                        // Comprobamos ahora por si estamos en el último, de nuevo
                        siguienteButton.setEnabled(false);
                }

                // Hay que actualizar el texto de los TextFields
                tituloField.setText(actual.getTitulo());
                fechaField.setText(actual.getFecha());
                desarrolladoraField.setText(actual.getDesarrolladora());
                precioField.setText(String.valueOf(actual.getPrecio()) + " €");
                ofertaField.setText(actual.getOferta());
            }
        });
        
        // Acciones de JButtons de actionButtonsPane
        
            // JButton "Cancelar"
        cancelarButton.addActionListener(new ActionListener() {  // Evento de boton.
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tenemos que restablecer moveButtonsPane
                contentPane.remove(actionButtonsPane);
                contentPane.validate();
                contentPane.add(moveButtonsPane, BorderLayout.SOUTH);
                contentPane.validate();
                
                // Hay que actualizar el texto de los TextFields
                tituloField.setText(actual.getTitulo());
                fechaField.setText(actual.getFecha());
                desarrolladoraField.setText(actual.getDesarrolladora());
                precioField.setText(String.valueOf(actual.getPrecio()) + " €");
                ofertaField.setText(actual.getOferta());
                
                // Volvemos a desactivar los campos
                tituloField.setEditable(false);
                tituloField.setFocusable(false);
                tituloField.setBackground(Color.LIGHT_GRAY);

                fechaField.setEditable(false);
                fechaField.setFocusable(false);
                fechaField.setBackground(Color.LIGHT_GRAY);

                desarrolladoraField.setEditable(false);
                desarrolladoraField.setFocusable(false);
                desarrolladoraField.setBackground(Color.LIGHT_GRAY);

                precioField.setEditable(false);
                precioField.setFocusable(false);
                precioField.setBackground(Color.LIGHT_GRAY);

                ofertaField.setEditable(false);
                ofertaField.setFocusable(false);
                ofertaField.setBackground(Color.LIGHT_GRAY);
                
            }
        });
        
            // JButton "Aceptar"
        aceptarButton.addActionListener(new ActionListener() {  // Evento de boton.
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tenemos que restablecer moveButtonsPane
                contentPane.remove(actionButtonsPane);
                contentPane.validate();
                contentPane.add(moveButtonsPane, BorderLayout.SOUTH);
                contentPane.validate();
                
                // Creamos el nuevo elemento
                // Al crearlo se añade automáticamente a la estructura
                actual = new Videojuego(
                        tituloField.getText(),
                        desarrolladoraField.getText(),
                        fechaField.getText(),
                        precioField.getText(),
                        ofertaField.getText()
                );
                
                // Hay que actualizar el texto de los TextFields
                tituloField.setText(actual.getTitulo());
                fechaField.setText(actual.getFecha());
                desarrolladoraField.setText(actual.getDesarrolladora());
                precioField.setText(String.valueOf(actual.getPrecio()) + " €");
                ofertaField.setText(actual.getOferta());
                
                // Volvemos a desactivar los campos
                tituloField.setEditable(false);
                tituloField.setFocusable(false);
                tituloField.setBackground(Color.LIGHT_GRAY);

                fechaField.setEditable(false);
                fechaField.setFocusable(false);
                fechaField.setBackground(Color.LIGHT_GRAY);

                desarrolladoraField.setEditable(false);
                desarrolladoraField.setFocusable(false);
                desarrolladoraField.setBackground(Color.LIGHT_GRAY);

                precioField.setEditable(false);
                precioField.setFocusable(false);
                precioField.setBackground(Color.LIGHT_GRAY);

                ofertaField.setEditable(false);
                ofertaField.setFocusable(false);
                ofertaField.setBackground(Color.LIGHT_GRAY);
                
            }
        });
        
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
