package transmisionficheros;

import java.io.Serializable;

/**
 * Esta clase se va a usar para enviar los datos de un fichero. Es la
 * equivalente a la clase "MensajeTomaFichero".
 *
 * Sus atributos van a contener el nombre del fichero, la ruta, y los datos que
 * almacena.
 */
public class DataSend implements Serializable {

    private String file;    // Nombre del fichero. 
    private String path;    // Ruta del fichero
    private byte[] data;    // Array de bytes para almacenar los datos

    // Constructor
    public DataSend(String file, String path, byte[] data) {
        this.file = file;
        this.path = path;
        this.data = data;
    }

    // Getters y Setters
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
