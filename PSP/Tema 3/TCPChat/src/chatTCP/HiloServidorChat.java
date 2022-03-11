package chatTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class HiloServidorChat extends Thread {
    // <editor-fold desc="Varibles">
    DataInputStream in;
    Socket socket = null;
    ComunHilos commonThread;
    // </editor-fold>

    // <editor-fold desc="Constructor">
    public HiloServidorChat(Socket s, ComunHilos common) {
        this.socket = s;
        this.commonThread = common;
        try {
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("ERROR - Ha ocurrido una excepción en HiloServidorChat.HiloServidorChat().");
            e.printStackTrace();
        }
    }
    // </editor-fold>

    // <editor-fold desc="Run">
    @Override
    public void run() {
        System.out.println(" - Usuarios activos: " + commonThread.getActualConnections());
        String msg = commonThread.getMessages();
        sendMessage(msg);

        while (true) {
            String str = "";
            try {
                str = in.readUTF();
                if (str.trim().equals("*")) {
                    commonThread.setActualConnections(commonThread.getActualConnections() - 1);
                    System.out.println(" - Usuarios activos: " + commonThread.getActualConnections());
                    break;
                }
                commonThread.setmessages(commonThread.getMessages() + str + "\n");
                sendMessage(commonThread.getMessages());
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // </editor-fold>

    // <editor-fold desc="Método para enviar los mensajes al chat">
    private void sendMessage(String texto) {
        int i;

        for (i = 0; i < commonThread.getConnections(); i++) {
            Socket s = commonThread.getUser(i);
            if (!s.isClosed()) {
                try {
                    DataOutputStream fsalida = new DataOutputStream(s.getOutputStream());
                    fsalida.writeUTF(texto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // </editor-fold>
}

