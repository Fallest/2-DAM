package Ejercicio3;

import java.io.*;

/**
 * Esta clase va a ser el mensaje que pida el fichero al server. Tendrá dos
 * atributos: 
 *  -La ruta del archivo (sin el nombre del archivo). 
 *  -El nombre del archivo.
 */
public class MensajeDameFichero implements Serializable {

    private String path;
    private String file;

    // Constructor
    public MensajeDameFichero(String path, String file) {
        this.path = path;
        this.file = file;
    }

    // Getters y Setters
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

}
