package tcpchat;

import java.util.ArrayList;

/**
 * En esta clase se van a almacenar todos los mensajes que se envíen, junto con
 * el remitente.
 * Se usará para acceder a los mensajes.
 */

public class Chat extends Thread {
    private static int msgCount = 0;
    private static ArrayList<String> messages = new ArrayList<>();
    
    public static synchronized void addMessage(String user, String message) {
        messages.add(msgCount, user + ": " + message);
        msgCount++;
    }
    
    public static String getMessage(int index) {
        return messages.get(index-1);
    }
    
    public static synchronized int getMsgCount() {
        return Chat.msgCount;
    }
}
