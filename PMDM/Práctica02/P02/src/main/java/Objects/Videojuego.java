package Objects;

import Libraries.Input;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public final class Videojuego {
    // Atributos
    private Videojuego next = null;
    private Videojuego prev = null;
    private static Videojuego first = null;
    private String titulo;
    private GregorianCalendar fecha;
    private String desarrolladora;
    private float precio;
    private boolean enOferta;
    
    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    /*---------------------------------------------------------------*/
    // Constructores: 2/2
    public Videojuego() {
        // Cambio del primer puntero
        if (first == null) first = this;
        else {
            // Con estas dos líneas sería suficiente para el cambio
            next = first;
            next.setPrevious(this);
            first = this;
            /*
            Nota: Sé que el nodo prev hace redundante la existencia de first.
            Sigue en el código porque es un requisito del enunciado (2.1).
            */
        }
        setTitulo();
        setDesarrolladora();
        setFecha();
        setPrecio();
        setOferta();
    }
    
    public Videojuego(String titulo, String desarrolladora, String fecha, String precio, String oferta) {
        // Cambio del primer puntero
        if (first == null) first = this;
        else {
            // Con estas dos líneas sería suficiente para el cambio
            next = first;
            next.setPrevious(this);
            first = this;
        }
        setTitulo(titulo);
        setDesarrolladora(desarrolladora);
        setFecha(fecha);
        setPrecio(precio);
        setOferta(oferta);
    }
    
    /*---------------------------------------------------------------*/ 
    // Setters: 12/12
    public void setTitulo(String s) {
        this.titulo = s.trim();
    }
    
    public void setTitulo() {
        this.titulo = Input.read("Introduzca el título del videojuego", "titulo");
    }
    /*------------------------------*/
    public void setDesarrolladora(String s) {
        this.desarrolladora = s.trim();
    }
    
    public void setDesarrolladora() {
        this.desarrolladora = Input.read("Introduzca el nombre de la empresa desarrolladora", "desarrolladora");
    }
    /*------------------------------*/
    public void setFecha(String s) {
        /* s debe tener el formato (DD/MM/YYYY)*/
        s = s.trim();
        this.fecha = new GregorianCalendar(
                Integer.parseInt(s.substring(6, 10)),
                Integer.parseInt(s.substring(3, 5)) - 1, // Porque el calendario va de 0 a 11
                Integer.parseInt(s.substring(0, 2))) {
                    public String toString()
                    {
                        Date thisDate = this.getTime();
                        return sdf.format(thisDate);
                    }
                };;
    }
    
    public void setFecha() {
        this.fecha = Input.readDate("Introduzca la fecha de lanzamiento del juego");
    }
    /*------------------------------*/
    public void setPrecio(String s) {
        this.precio = Float.parseFloat(s.trim());
    }
    
    public void setPrecio() {
        this.precio = Float.parseFloat(
                Input.read("Introduzca el precio del videojuego (separador decimal \".\")",
                        "precio"));
    }
    
    /*------------------------------*/
    public void setOferta() {
        this.enOferta = ("s".equals(Input.read("¿Está el juego en oferta? (s/n)", "oferta")));
    }
    
    public void setOferta(String oferta) {
        this.enOferta = oferta.equals("s");
    }
    
    /*------------------------------*/
    public void setNext(Videojuego next) {
        this.next = next;
    }
    
    /*------------------------------*/
    public void setPrevious(Videojuego prev) {
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
    public void mostrarDatos() {
        System.out.println("\tTítulo: " + this.titulo);
        System.out.println("\tDesarrolladora: " + this.desarrolladora);
        System.out.println("\tPrecio: " + this.precio + "€");
        System.out.println("\tFecha de lanzamiento: " + this.fecha);
        if (this.enOferta) System.out.println("\tEn oferta : Sí");
        else System.out.println("\tEn oferta : No");
    }
        // Método toString:
    public String toString() {
        /*
        * Convierte un objeto Videojuego en una cadena con formato:
        * "Titulo; Desarrolladora; Fecha; Precio; Oferta"
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
        /* 
        * Covierte una cadena s en un objeto Videojuego.
        * La cadena s es una cadena con el siguiente formato:
        * "Titulo; Desarrolladora; Fecha; Precio; Oferta"
        */
        String datos[] = s.split(";");
        
        return new Videojuego(datos[0], datos[1], datos[2], datos[3], datos[4]);
    }
}