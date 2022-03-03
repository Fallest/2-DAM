package transmisionficheros;

import java.io.Serializable;

/**
 * Esta clase se usa para pedir al Servidor qué fichero quiere recibir. Es la
 * equivalente a "MensajeDameFichero".
 *
 * El constructor de esta clase recibe dos cadenas, que serán el nombre del
 * fichero y la ruta de éste.
 */
public class DataReceive implements Serializable {

    private String file;
    private String path;

    // Constructor
    public DataReceive(String file, String path) {
        this.file = file;
        this.path = path;
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

}
