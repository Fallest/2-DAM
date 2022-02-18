package Ejercicio1;

import java.io.IOException;
import java.net.Socket;

public class TCPClienteBasico {
    /**
     * En esta clase se simula la conexión de un Host local a un Host remoto 
     * (Servidor), conociendo el nombre del host y el puerto.
     */
    
    public static void main(String[] args) throws IOException {
        // Socket localConn = new Socket("DESKTOP-L337VTB", 6000); // portatil
        Socket localConn = new Socket("DESKTOP-A2NJM1I", 6000);    // PC de casa
        
        
        System.out.println("Conexión realizada.");
        localConn.close();
    }
}
