package BernalNavarreteDavid.Controlador;

/**
 * La clase Lista se va a encargar de:
 * -El primer nodo.
 * -Avanzar y retroceder.
 * -Obtener los datos de un nodo concreto.
 * -Añadir y eliminar nodos.
 * 
 * Hay una razón para que inicial e iter no sean static:
 * Vamos a crear más de una lista.
 * En el caso de que queramos filtrar los datos, vamos a crear una nueva lista
 * a la que vamos a añadir los Nodos que nos interese, y teniendo una lista a
 * parte podemos hacer más fluida la ejecución del programa (y hacernos más fácil
 * la vida). Sin embargo, tendremos que tener un poco más de cuidado al tratar con
 * ambos atributos fuera de esta clase.
 */
public class Lista {
    // Nodos inicial y actual
    private Nodo inicial;
    private Nodo iter;
    
    public void añadirNodo(Nodo nodo) {
        /**
         * Función para añadir un nodo.
         * Si es el primer elemento (inicial == null), lo añadimos directamente.
         * Si no es el primer elemento, lo insertamos al inicio de la lista.
         * Para el funcionamiento en detalle de esta lista, revisar la 
         * explicación en la Práctica02, en el constructor de la clase Videojuego.
         */
        if (inicial == null) inicial = nodo;
        else {
            nodo.setSiguiente(inicial);
            nodo.getSiguiente().setAnterior(nodo);
            inicial = nodo;
        }
    }
    
    public void eliminarNodo(Nodo nodo) {
        /**
         * Función para eliminar un nodo.
         * -Va a buscar el nodo, para lo cual usa el nodo auxiliar iter.
         * -Primero tenemos que llevar iter al inicio.
         * -Luego entramos en un bucle para avanzar hasta encontrar el nodo 
         * coincidente.
         * -Una vez encontrado reasignamos los nodos Siguiente y Anterior para 
         * sacarlo de la estructura.
         * -Volvemos a reiniciar iter, para que no haya nada apuntando a los datos 
         * a eliminar.
         * -Luego llamamos al System.gc() para eliminar los datos que no tienen
         * ninguna variable apuntando a ellos.
         * 
         * Explicación de cómo estoy sacándolo de la estructura:
         * Volvemos a los coches :).
         * Tenemos tres coches, A, B, y C:
         * esto es un semáforo, y marca el primero ->|8| [A] ↔ [B] ↔ [C]
         * B es un Honda Civic y no nos gusta, queremos que se lo lleve la grúa.
         * Y lo vamos a hacer desaparecer como hacemos que una persona deje de existir:
         * Nadie reconoce su existencia.
         * Para eliminarlo completamente de la consciencia social, tenemos que 
         * convencer a A y a C de que B es un constructo social. 
         * Vamos a ver cómo sabemos si B existe o no: A sabe que es el anterior a B,
         * y C sabe que es el posterior a B. Pues vamos a quitar a B de la ecuación:
         * Vamos a decirle a A que el que está después de él no es B, sino C; y a
         * C le vamos a decir que el que está detrás de ella no es B, sino A.
         * Entonces hemos hecho algo como esto:
         *           ┌──────────────↓
         *      |8| [A] ← [B] → [C]
         *           ↑──────────────┘
         * Así, el único que sabe que existe B, es él mismo, y nadie lo acepta
         * como un coche válido en esta sociedad. Como ya no hay nadie que lo
         * reconozca, podemos llamar efectivamente a la grúa, que se encargará
         * de recoger a las pobres almas perdidas: System.gc() (curiosamente el
         * nombre de la grúa es Garbage Collector; lo siento, coche B, eres basura).
         */          
        resetIter();
        while(iter.getSiguiente() != null) {
            if (iter == nodo) {
                iter.getSiguiente().setAnterior(iter.getAnterior());
                iter.getAnterior().setSiguiente(iter.getSiguiente());
                resetIter();
                System.gc();
                break;
            }
            iter = iter.getSiguiente();
        }
    }
    
    public void crearNodo(Object nuevoObjeto) {
        /**
         * Función para crear un nodo e insertarlo en la estructura.
         * -Va a recibir un objeto (de cualquier tipo), y se va a crear un nuevo
         * nodo con ese objeto como parámetro.
         * -Una vez creado, se añade a la estructura con añadir().
         */
         Nodo nuevoNodo = new Nodo(nuevoObjeto);
         añadirNodo(nuevoNodo);
    }
    
    public void siguiente() {
        /**
         * Va a avanzar el iter al siguiente.
         */
        iter = iter.getSiguiente();
    }
    
    public void atrás() {
        /**
         * Va a retroceder el iter al anterior.
         */
        iter = iter.getAnterior();
    }
    
    public Nodo primero() {
        return inicial;
    }
    
    public Nodo fin() {
        while (iter.getSiguiente() != null) iter = iter.getSiguiente();
        return iter;
    }
    
    public Object extraerDatos() {
        // Devuelve el objeto que está en el nodo actual.
        return iter.getObjeto();
    }
    
    public boolean vacía() {
        // Devuelve TRUE si está vacía (inicial es null).
        return inicial == null;
    }
    
    public void resetIter() {
        // Resetea el iterador para volver al inicio de la lista.
        iter = inicial;
    }
}


