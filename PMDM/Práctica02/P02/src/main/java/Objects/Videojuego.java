package Objects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase Videojuego.
 * En esta clase hay dos cosas importantes:
 * -El propio objeto videojuego
 * -La estructura de datos, que en este caso también son los propios videojuegos.
 * Es decir, los videojuegos hacen a la vez la función de objeto para guardar datos
 * y nodo para organizar la estructura de datos.
 * Generalmente, los nodos y los datos van por separado.
 * Para que os hagáis una idea: Los datos son personas, los nodos son los coches
 * en los que van, y la estructura es el orden en el que los coches están en un 
 * atasco. Cada coche sabe quién es él, quien es el anterior, y quien es el siguiente.
 * Si cada coche sabe quien es su siguiente y su anterior, se puede saber el orden
 * en el que están si conocer cada elemento en cada instante: podemos ir uno a uno,
 * preguntando quién tiene delante o detrás.
 */

public final class Videojuego {
    // Atributos para nodos
    private Videojuego next = null; // Equivalente al siguiente nodo
    private Videojuego prev = null; // Equivalente al anterior nodo
    private static Videojuego first = null; // El primer nodo
    // Atributos para los datos
    private String titulo;
    private GregorianCalendar fecha;
    private String desarrolladora;
    private float precio;
    private boolean enOferta;
    
    // Esto es para convertir la fecha de GregorianCalendar a String.
    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    /*---------------------------------------------------------------*/
    // Constructor:
    /**
     * Este constructor toma como parámetro todos los valores necesarios para crear
     * un objeto de tipo Videojuego.
     * Los valores de los atributos de los nodos se van asignando automáticamente.
     */
    public Videojuego(String titulo, String desarrolladora, String fecha, String precio, String oferta) {
        // Comprobamos si es el primer elemento en introducirse.
        if (first == null) first = this;
        // SI no es el primer elemento, tenemos que introducirlo al principio.
        else {
            /**
             * Imaginemos este cambio:
             * hay dos coches en línea, y uno entra al final de la cola.
             * Tenemos los coches A, B, y C, siendo este último el último en llegar.
             * Tenemos que imaginar que el orden de la fila es al revés, es decir,
             * el último es en realidad el primero.
             * [B] -> [A] |8| <- Esta barra es un semáforo.>
             * Ahora llega el coche C:
             * [C]    [B] -> [A] |8|
             * Pues ahora el primero no será B, sino que será C.
             * [C] -> [B] -> [A] |8|
             * De esto podemos deducir que:
             * El siguiente de C es B. [C] -> [B]
             * El anterior de B es C.  [C] ← [B]                               >
             * Ahora C es el primero.  [ ] ← [C]
             * Teniendo en cuenta que C es this, tenemos el siguiente código
             */
            this.next = Videojuego.first;
            this.next.setPrevious(this);
            Videojuego.first = this;
        }
        
        setTitulo(titulo);
        setDesarrolladora(desarrolladora);
        setFecha(fecha);
        setPrecio(precio);
        setOferta(oferta);
    }
    
    /*---------------------------------------------------------------*/ 
    // Setters:
    public void setTitulo(String s) {
        // String.trim() recorta los espacios en blanco que haya delante y detrás
        // de la cadena en cuestión.
        this.titulo = s.trim();
    }
    /*------------------------------*/
    public void setDesarrolladora(String s) {
        // String.trim() recorta los espacios en blanco que haya delante y detrás
        // de la cadena en cuestión.
        this.desarrolladora = s.trim();
    }
    /*------------------------------*/
    public void setFecha(String s) {
        /** s debe tener el formato (DD/MM/YYYY)
         * Vamos a crear un nuevo calendario usando el constructor que toma la 
         * siguiente forma:
         * GregorianCalendar(int año, int mes, int dia)
         * Para extraer los números de una cadena, tenemos que estraer cada número
         * independiente en forma de cadena, y luego convertirlo a entero, y LUEGO
         * se lo podemos pasar al constructor.
         * Para el primer paso, usamos s.substring(n, m), y va a extraer la cadena
         * que haya entre las posiciones n y n+m (hay que tener en cuenta que si
         * tenemos "hola" y hacemos "hola".substring(2, 1) vamos a extraer "a", 
         * es decir, la primera posición es 0).
         * Para convertir la subcadena en un número, lo hacemos con
         * Integer.parseInt(cadena).
         * Hay una nota en la posición del entero del mes, esto es porque los meses
         * los pido (en el formato) de 1 a 12, y GregorianCalendar los toma de 0 a 11,
         * lo que significa que al dato que yo pida hay que quitarle 1.
         * 
         * Luego, hay una maravillosa clase anónima justo después del constructor
         * de GregorianCalendar.
         * Por qué?
         * Porque quiero poder convertir un objeto de GregorianCalendar a una String.
         * Por eso, uso una clase anónima para sobreescribir el método toString
         * de GregorianCalendar (que a su vez es el método de la superclase Calendar, 
         * de la cual hereda GregorianCalendar) y así poder convertir de GregorianCalendar
         * a String sin tener que hacer mucho más lío que esto.
         * No es necesario comprender el código dentro del método toString, pero
         * lo voy a explicar porque puedo.
         * 
         * Básicamente va a crear un objeto de tipo Date (la versión más facil de 
         * usar de GregorianCalendar), y va a pasar de usar un GregorianCalendar
         * a un Date, y lo va a convertir, usando el formato declarado justo después
         * de los atributos, a una cadena.
         */
        this.fecha = new GregorianCalendar(
                Integer.parseInt(s.substring(6, 10)),
                Integer.parseInt(s.substring(3, 5)) - 1, // Porque el calendario va de 0 a 11
                Integer.parseInt(s.substring(0, 2))) {
                    @Override
                    public String toString()
                    {
                        Date thisDate = this.getTime();
                        return sdf.format(thisDate);
                    }
                };
    }
    /*------------------------------*/
    public void setPrecio(String s) {
        // Float.parseFloat(cadena) convierte la cadena a un Float.
        this.precio = Float.parseFloat(s.trim());
    }
    
    /*------------------------------*/
    public void setOferta(String oferta) {
        /**
         * Como el método equals() ya de por sí devuelve un boolean, podemos
         * usarlo a palo seco para asignar el valor a oferta, comprobando
         * si vale "Sí" o cualquier otra cosa.
         */
        this.enOferta = oferta.trim().equals("Sí");
    }
    
    /*------------------------------*/
    public void setNext(Videojuego next) {
        // De apellido next dice
        this.next = next;
    }
    
    /*------------------------------*/
    public void setPrevious(Videojuego prev) {
        // Si de verdad no entiendes esto, háblame por privado por favor.
        this.prev = prev;
    }
    
    /*---------------------------------------------------------------*/
    // Getters: 7/7
    public String getTitulo() {
        return this.titulo;
    }
    
    public String getDesarrolladora() {
        return this.desarrolladora;
    }
    
    public String getFecha() {
        return this.fecha.toString();
    }
    
    public float getPrecio() {
        return this.precio;
    }
    
    public String getOferta() {
        if(enOferta) return "Sí";
        else return "No";
    }
    
    public Videojuego getNext() {
        return this.next;
    }
    
    public Videojuego getPrevious() {
        return this.prev;
    }
    
    public static Videojuego getFirst() {
        return Videojuego.first;
    }
    
    /*---------------------------------------------------------------*/
    // Funciones auxiliares: 3/3
    
        // Mostrar los datos:
    @Deprecated
    public void mostrarDatos() {
        // ESTA FUNCIÓN ESTÁ EN DESUSO
        System.out.println("\tTítulo: " + this.titulo);
        System.out.println("\tDesarrolladora: " + this.desarrolladora);
        System.out.println("\tPrecio: " + this.precio + "€");
        System.out.println("\tFecha de lanzamiento: " + this.fecha);
        if (this.enOferta) System.out.println("\tEn oferta : Sí");
        else System.out.println("\tEn oferta : No");
    }
        // Método toString:
    @Override
    public String toString() {
        /*
        * Convierte un objeto Videojuego en una cadena con formato:
        * "Titulo;Desarrolladora;Fecha;Precio;Oferta"
        */
        String cad;
        
        cad = this.titulo + ";";
        cad += this.desarrolladora + ";";
        cad += this.fecha + ";";
        cad += this.precio + ";";
        if (this.enOferta) cad += "s";
        else cad += "n";
        
        return cad;
    }
    
        // Método parseJuego:
    public static Videojuego parseJuego(String s) {
        /** 
        * Covierte una cadena s en un objeto Videojuego.
        * La cadena s es una cadena con el siguiente formato:
        * "Titulo;Desarrolladora;Fecha;Precio;Oferta"
        * 
        * Como sabemos por qué carácter van a estar separados los datos, podemos
        * usar la función split(delimitante) de String. Esta función va a
        * devolver un array de Strings, en el orden en el que ha separado los
        * datos.
        * Por eso luego puedo acceder a ellos directamente con los índices del array.
        */
        String datos[] = s.split(";");
        
        return new Videojuego(datos[0], datos[1], datos[2], datos[3], datos[4]);
    }
}