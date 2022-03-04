package Ejercicio3;

import java.io.*;

/**
 * Esta clase será el mensaje que contendrá el archivo y sus datos. Para ello
 * tendrá 3 atributos: 
 *  -La ruta del archivo (sin el nombre del archivo). 
 *  -El nombre del archivo. 
 *  -Un array de bytes que contenga los datos del fichero.
 */
public class MensajeTomaFichero implements Serializable {

    private String path;
    private String file;
    private byte[] data;

    // Constructor
    public MensajeTomaFichero(String path, String file, byte[] data) {
        this.path = path;
        this.file = file;
        this.data = data;
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
