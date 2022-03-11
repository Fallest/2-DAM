package chatTCP;

import java.net.Socket;

public class ComunHilos {

    // <editor-fold desc="Variables">
    int connections;
    int actualConnections;
    int max;
    Socket[] users = new Socket[max];
    String msgs;
    // </editor-fold>

    // <editor-fold desc="Constructores">
    public ComunHilos(int maximo, int actuales, int conexiones, Socket[] tabla) {
        max = maximo;
        actualConnections = actuales;
        this.connections = conexiones;
        this.users = tabla;
        msgs = "";
    }

    public ComunHilos() {
        super();
    }
    // </editor-fold>

    // <editor-fold desc="Getters y Setters">
    public int getMax() {
        return max;
    }

    public void setMax(int maxusrs) {
        max = maxusrs;
    }

    public int getConnections() {
        return connections;
    }

    public synchronized void setConnections(int conexiones) {
        this.connections = conexiones;
    }

    public String getMessages() {
        return msgs;
    }

    public synchronized void setmessages(String mensajes) {
        this.msgs = mensajes;
    }

    public int getActualConnections() {
        return actualConnections;
    }

    public synchronized void setActualConnections(int actuales) {
        actualConnections = actuales;
    }

    public synchronized void addUsers(Socket s, int i) {
        users[i] = s;
    }

    public Socket getUser(int i) {
        return users[i];
    }

    // </editor-fold>
}
