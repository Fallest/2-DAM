package Ejercicio1;

import java.io.IOException;
import java.net.Socket;

public class LocalCon {
    /**
     * En esta clase se simula la conexión de un Host local a un Host remoto 
     * (Servidor), conociendo el nombre del host y el puerto.
     */
    
    public static void main(String[] args) throws IOException {
        Socket localConn = new Socket("DESKTOP-L337VTB", 6000);
        
        System.out.println("Conexión realizada.");
        localConn.close();
    }
}
