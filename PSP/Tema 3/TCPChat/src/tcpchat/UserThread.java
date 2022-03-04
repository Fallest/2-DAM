package tcpchat;

import java.io.*;
import java.net.Socket;

/**
 * Es una clase para los hilos de los usuarios conectados. Se creará un hilo
 * para cada usuario conectado al servidor. Cuando un usuario se conecta al
 * servidor, se crea un nuevo hilo con el Socket del cliente. A través de este
 * Socket se recibirán y enviarán los mensajes, usando como intermediaria la
 * clase ChatThread (común a todos los hilos). De forma que cuando se reciba un
 * mensaje, se enviará a ChatThread, y ChatThread enviará a todos los hilos el
 * mensaje recibido por ese UserThread.
 *
 */
public class UserThread extends Thread {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;
    private String str;
    private int msgCount = 0;
    private boolean running = true;

    public UserThread(Socket cli) throws IOException {
        this.socket = cli;
        in = new ObjectInputStream(cli.getInputStream());
        out = new ObjectOutputStream(cli.getOutputStream());
    }

    @Override
    public void run() {
        try {
            System.out.println("Conexión: " + socket.toString());

            while (this.running) {
                // En un bucle leemos los mensajes del cliente y le 
                // enviamos los mensajes del Chat.
                readUserMessage();
                sendUserMessageToChat();
                readChatMessage();
            }

            System.out.println("Desconexión: " + socket.toString());
            in.close();
            out.close();
            socket.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ERROR: Ha ocurrido un error en UserThread.run().");
        }
    }

    public synchronized void readUserMessage() throws IOException, ClassNotFoundException {
        // Lee un mensaje enviado por el cliente. Si la cadena recibida es "*"
        // se cierra la conexión.
        str = (String) in.readObject();
        if (str.equals("*")) {
            this.running = false;
            str = this.getName() + " se ha desconectado.";
        }
    }

    public synchronized void sendUserMessageToChat() {
        // Envía el mensaje del usuario al Chat
        Chat.addMessage(this.getName(), str);
        msgCount++;
    }
    
    public synchronized void readChatMessage() throws IOException {
        // Lee un mensaje del Chat, si hay alguno que leer, y lo envía
        while (msgCount < Chat.getMsgCount()) {
            str = Chat.getMessage(msgCount);
            sendChatMessageToUser();
            msgCount++;
        }
    }

    public synchronized void sendChatMessageToUser() throws IOException {
        // Envía un mensaje del Chat al usuario.
        out.writeObject(str);
    }
}
