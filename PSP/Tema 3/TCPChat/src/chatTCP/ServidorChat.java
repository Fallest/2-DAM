package chatTCP;

import java.io.*;
import java.net.*;

public class ServidorChat {
    // <editor-fold desc="Variables">
    static final int maxCon = 5;
    // </editor-fold>
    
    // <editor-fold desc="Main">
    public static void main(String args[]) throws IOException {
        int port = 6000;
        ServerSocket server = new ServerSocket(port);
        System.out.println("Servidor iniciado..");
        Socket users[] = new Socket[maxCon];
        ComunHilos commonThread = new ComunHilos(maxCon, 0, 0, users);
        while (commonThread.getConnections() < maxCon) {
            Socket socket = new Socket();
            socket = server.accept();// esperando cliente
            commonThread.addUsers(socket, commonThread.getConnections());
            commonThread.setActualConnections(commonThread.getActualConnections() + 1);
            commonThread.setConnections(commonThread.getConnections() + 1);
            HiloServidorChat thread = new HiloServidorChat(socket, commonThread);
            thread.start();
        }
        server.close();
    }
    // </editor-fold>
}