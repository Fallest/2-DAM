package BernalNavarreteDavid.Controlador;

/**
 * La clase Lista se va a encargar de:
 * -El primer nodo.
 * -Avanzar y retroceder.
 * -Obtener los datos de un nodo concreto.
 * -Añadir y eliminar nodos.
 * 
 */
public class Lista {
    // Nodos inicial y actual
    private static Nodo inicial;
    private static Nodo iter;
    
    public void añadirNodo(Nodo nodo) {
        /**
         * Función para añadir un nodo.
         * Si es el primer elemento (inicial == null), lo añadimos directamente.
         * Si no es el primer elemento, lo insertamos al inicio de la lista.
         */
        if (inicial == null) inicial = nodo;
        else {
            nodo.setSiguiente(inicial);
            inicial.getSiguiente().setPrev(nodo);
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
         */
        resetIter();
        while(iter.getSiguiente() != null) {
            if (iter == nodo) {
                iter.getSiguiente().setAnterior(iter.getAnterior());
                iter.getAnterior().setSiguiente(iter.getSiguiente());
                resetIter();
                System.gc();
            }
        }
    }
    
    public void crearNodo(Object objeto) {
        /**
         * Función para crear un nodo e insertarlo en la estructura.
         * -Va a recibir un objeto (de cualquier tipo), y se va a crear un nuevo
         * nodo con ese objeto como parámetro.
         * -Una vez creado, se añade a la estructura con añadir().
         */
         Nodo nuevo = new Nodo(objeto);
         añadirNodo(nuevo);
    }
    
    public void siguiente() {}
    
    public void atrás() {}
    
    public void primero() {}
    
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


