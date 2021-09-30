package GUI;
// Hay que importar Objects.Videojuego porque está en otro paquete.
import Objects.Videojuego;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Llegamos al asunto.
 * Al crear una interfaz, diría que lo que más importa es organizarse, y qué mejor
 * que comentar? Así que si quieres aprender a organizarte, usa mis comentarios 
 * como una guía.
 * Al final, lo que importa es que sepas plasmar tus ideas en comentarios, para 
 * luego leer tu porpio comentario y entender exactamente qué quieres hacer y dónde.
 * 
 * Clase Interfaz, hereda de JFrame, lo que significa que no tendremos que crear 
 * un JFrame (puesto que lo crearemos con super() en este constructor).
 * Por si no sabéis lo que es aún, super es la clase de la que hereda esta (en este
 * caso, es JFrame). Si haces super() es como si hicieras JFrame(), es decir, llamas
 * al Constructor de JFrame.
 * 
 * Ahora, vamos en orden. 
 * Una clase tiene atributos, y los atributos van a ser las cosas que vas a necesitar.
 * Qué voy a necesitar? Pues la lista es larga, pero la vamos a resumir:
 * -Saber donde estoy en la lista, para poder recorrerla.
 * -Una lista que NO esté vacía, así que vamos a darle valores.
 * -Un constructor, para, tu sabes, hacer cosas.
 * 
 * -Un método main, que en realidad puede estar hasta en una clase diferente, 
 * pero aquí está bien.
 * 
 * Las dos primeras cosas son fáciles, lo tocho es el constructor.
 * Y en realidad, también es fácil, vamos a ver por qué.
 * Primero que nada, qué hace un constructor?
 * 
 * Exacto, construir, muy bien.
 * 
 * Pues vamos a ver, qué necesitas para construir algo?
 * 
 * Exacto, los materiales, muy bien.
 * 
 * Pues vamos a pensar qué necesitamos:
 * -Muchos botones.
 * -Muchas etiquetas.
 * -Muchos campos de texto.
 * -Paneles para colocar cada cosa encima.
 * 
 * Hasta ahí está bien de materiales. Ahora vamos a profundizar un poco en cada
 * uno:
 * Los botones tienen que tener:
 * -Un texto que muestren.
 * Y los botones tienen que hacer:
 * -Hacer lo que indican.
 * -Activarse o desactivarse, según lo que toque.
 * 
 * Las etiquetas tienen que tener:
 * -Un texto que muestran.
 * -Y poco más, solo hacen eso vaya.
 * 
 * Los campos de texto tienen que tener:
 * -Mostrar los datos del campo correspondiente cuando toque.
 * -Estar en blanco cuando toque.
 * 
 * Y los campos tienen que hacer:
 * -No ser editables cuando muestren datos.
 * -Ser editables cuando no muestren datos.
 * -Registrar los datos que se escriban en ellos cuando toque.
 * 
 * Hasta aquí espero que se entienda. Ahora viene lo que puede tener más complicación.
 * El tipo de programación que vamos a usar se llama Programación por Eventos.
 * Este tipo de programación se basa en uno (o varios) escuchadores (Listeners).
 * Estos listeners van a estar atentos a que algo ocurra. Cuando algo ocurre se 
 * le llama Evento. El programa va a reaccionar a estos eventos.
 * Un evento puede ser: hacer un clic, escribir, pasar el ratón por encima... cualquier
 * cosa básicamente.
 * 
 * Los eventos principales que vamos a registrar son...
 * Exacto! Clics. En concreto, clics en los botones.
 * Pues ya sabemos a qué aplicacarle estos "escuchadores": a los botones!
 * Se les añade con addActionListener(new ActionPerformed())
 * addActionListener es añadirle el escuchador, y ActionPerformed es el objeto
 * que se lanza cuando "algo" ocurre.
 * Esto de por sí va a hacer que el programa detecte cuando "algo" ocurre (en
 * este caso, un clic en el botón). Pero también queremos que haga algo cuando
 * lo detecte, no? Pues para decírselo hay que usar una clase anónima (esas tan
 * bonitas) para sobreescribir el método que existe en ActionPerformed (y que está
 * vacío) para decirle qué hacer cuando se presione el botón.
 * Entonces, dentro de esta clase anónima vamos a sobreescribir el método 
 * ActionPerformed(ActionEvent e).
 * Este ActionEvent es el clic en cuestión, pero a lo que vamos.
 * Dentro de este método vamos a hacer todos los ifs y lo que nos haga falta para
 * que los botones hagan lo que tienen que hacer, así que veamos cada caso a parte.
 * 
 * Qué hacer cuando queremos ir hacia atrás:
 * -Si estamos en el primero, desactivamos el botón (no podemos retroceder).
 * -En cualquier otro caso, retrocedemos (actual = anterior).
 * -Y actualizamos los datos que estamos mostrando (campoTexto.setText(nuevo texto)).
 * -Comprobamos si el botón "siguiente" está desactivado. Si lo está, lo volvemos 
 * a activar (si estaba desactivado, era porque estabamos en el final de la lista
 * y no había siguiente, sin embargo, al haber retrocedido, podemos volverlo a activar).
 * -Comprobamos si hemos llegado al primero (sí, otra vez), y si estamos en el primero,
 * desactivamos el botón "retroceder" (no se puede retroceder más).
 * 
 * Qué hacer cuando queremos avanzar: (pues lo contrario que cuando quiero retroceder)
 * -Si estamos en el último, desactivamos el botón (no podemos avanzar).
 * -En cualquier otro caso, avanzamos (actual = siguiente).
 * -Y actualizamos los datos que estamos mostrando (campoTexto.setText(nuevo texto)).
 * -Comprobamos si el botón "anterior" está desactivado. Si lo está, lo volvemos 
 * a activar (si estaba desactivado, era porque estabamos en el principio de la lista
 * y no había anterior, sin embargo, al haber avanzado, podemos volverlo a activar).
 * -Comprobamos si hemos llegado al último (sí, otra vez), y si estamos en el último,
 * desactivamos el botón "avanzar" (no se puede avanzar más).
 * 
 * (Como comprobaréis, son exactamente iguales)
 * 
 * Qué hacer cuando queremos crear un nuevo Videojuego (u objeto en cualquier caso):
 * -Tenemos, primero que nada, esconder los botones de avanzar, nuevo, y retroceder.
 * -Tenemos que limpiar los campos de texto.
 * -Tenemos que activar los campos de texto.
 * -Tenemos que activar los botones "cancelar" y "guardar".
 * -Y ea, ya está.
 * 
 * Vamos con guardar datos o no.
 * Qué hacer cuando queremos CANCELAR y no guardar lo que hemos escrito:
 * -Quitamos los botones "cancelar" y "guardar".
 * -Volvemos a poner los botones "avanzar", "retroceder", y "nuevo".
 * -Desactivamos los campos de texto.
 * -Rescatamos los datos que tenían los campos antes de darle a "nuevo" con el 
 * atributo "actual", y asignamos a los campos de texto los datos de ese juego.
 * -Y ea, terminado.
 * 
 * Qué hacer cuando queremos guardar los datos que has escrito: (casi igual a cancelar)
 * -Quitamos los botones "cancelar" y "guardar".
 * -Volvemos a poner los botones "avanzar", "retroceder", y "nuevo".
 * -Desactivamos los campos de texto.
 * -En vez de rescatar los datos de "actual", vamos a crear un nuevo juego. Al
 * crearlo, se inserta solo en la estructura (por la forma en la que hemos creado 
 * los videojuegos), y además se va a insertar en el principio (importante). Así que
 * en vez de crear una nueva variable Videojuego, y luego volver a asignar el actual
 * al inicio, directamente hacemos actual = new Videojuego(datos extraidos de los campos)
 * y así, al guardar, ya empezamos en el primero otra vez (que es el que tiene los
 * datos que acabamos de crear).
 * -Aginamos a los campos de texto los datos de nuestro nuevo juego.
 * -Profit (???).
 * 
 * Y nos queda un último botón, eh.
 * Y es ni nada más ni nada menos que esa X tan bonita que tienes arriba a tu derecha.
 * Si la pulsas en la interfaz, has parado la INTERFAZ, no el programa, que son dos
 * cosas diferentes. El programa invoca a la interfaz, pero son independientes.
 * Así que tenemos que indicarle al programa, que si la interfaz se ha cerrado, él
 * también. Eso se hace con un... Evento! Y este evento ahora está en main! Porque
 * en main tenemos la interfaz, y a ella le podemos añadir un escuchador también,
 * no solo a los botones. En este caso vamos a añadir un listener que se llama
 * WindowListener(new WindowAdapter()), y como antes, le vamos a meter una clase 
 * anónima para sobreescribir el método WindowClosed (que es cuando se cierra la
 * ventana). Dentro de ese método vamos a añadir el intricado código para terminar
 * el programa: System.exit(segundos hasta que se cierre).
 * 
 * Y ea, hasta aquí la explicación de cómo funciona la interfaz. Son 5€.
 */
public class Interfaz extends JFrame{
    // Atributos:
    
        // Esto es para saber dónde estoy actualmente.
    private static Videojuego actual;
    
        // Esto es para no tener una lista vacía
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
    
    /*---------------------------------------------------------------*/
    // Constructor: Esto es para tener algo con lo que trabajar.
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
        anteriorButton.setFont(new Font("Arial", Font.PLAIN, 15));
        nuevoButton.setFont(new Font("Arial", Font.PLAIN, 15));    
        siguienteButton.setFont(new Font("Arial", Font.PLAIN, 15));
        cancelarButton.setFont(new Font("Arial", Font.PLAIN, 15));
        aceptarButton.setFont(new Font("Arial", Font.PLAIN, 15));
        
        
        // Creamos las JLabels
        JLabel tituloLabel = new JLabel("Título: ");
        JLabel fechaLabel = new JLabel("Fecha de lanzamiento (DD/MM/YYYY): ");
        JLabel desarrolladoraLabel = new JLabel("Desarroladora: ");
        JLabel precioLabel = new JLabel("Precio: ");
        JLabel ofertaLabel = new JLabel("En oferta: ");
        tituloLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        fechaLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        desarrolladoraLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        precioLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        ofertaLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        
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
        tituloField.setFont(new Font("Arial", Font.PLAIN, 20));
        
        fechaField.setEditable(false);
        fechaField.setFocusable(false);
        fechaField.setBackground(Color.LIGHT_GRAY);
        fechaField.setFont(new Font("Arial", Font.PLAIN, 20));
        
        desarrolladoraField.setEditable(false);
        desarrolladoraField.setFocusable(false);
        desarrolladoraField.setBackground(Color.LIGHT_GRAY);
        desarrolladoraField.setFont(new Font("Arial", Font.PLAIN, 20));
        
        precioField.setEditable(false);
        precioField.setFocusable(false);
        precioField.setBackground(Color.LIGHT_GRAY);
        precioField.setFont(new Font("Arial", Font.PLAIN, 20));
        
        ofertaField.setEditable(false);
        ofertaField.setFocusable(false);
        ofertaField.setBackground(Color.LIGHT_GRAY);
        ofertaField.setFont(new Font("Arial", Font.PLAIN, 20));
        
        // Creación de los paneles independientes
        
            // Panel de JLabels (Siempre va a estar presente)
        JPanel labelPane = new JPanel();
        labelPane.setLayout(new GridLayout(0, 1)); // 0 filas, 1 columna
        labelPane.setBackground(Color.getHSBColor(0.47f, 0.32f, 0.78f));
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
        moveButtonsPane.setBackground(Color.getHSBColor(0.47f, 0.32f, 0.78f));
        moveButtonsPane.add(anteriorButton, BorderLayout.WEST);
        moveButtonsPane.add(nuevoButton, BorderLayout.CENTER);
        moveButtonsPane.add(siguienteButton, BorderLayout.EAST);
        
            // Panel de JButtons "Cancelar" y "Aceptar"
            // (solo presente tras hacer clic en "Nuevo", y desaparecen
            // al hacer clic en "Cancelar" o en "Aceptar")
        JPanel actionButtonsPane = new JPanel();
        actionButtonsPane.setLayout(new BorderLayout());
        actionButtonsPane.setBackground(Color.getHSBColor(0.47f, 0.32f, 0.78f));
        actionButtonsPane.add(cancelarButton, BorderLayout.WEST);
        actionButtonsPane.add(aceptarButton, BorderLayout.EAST);
        
            // Incluye tres paneles en otro panel permanente.
            // Las etiquetas van a la izquierda y los textFields a la derecha.
            // Los botones van abajo
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(Color.getHSBColor(0.47f, 0.32f, 0.78f));
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
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }
}