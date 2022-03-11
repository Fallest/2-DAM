package chatTCP;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ClienteChat extends JFrame implements ActionListener, Runnable {

    // <editor-fold desc="Declaración de variables">
    private static final long serialVersionUID = 1L;
    Socket socket = null;

    DataInputStream in;
    DataOutputStream out;
    String name;
    static JTextField msg = new JTextField();
    private JScrollPane msgArea;
    static JTextArea tArea;
    JButton buttonSend = new JButton(">");
    JButton buttonExit = new JButton("Salir");
    boolean ask = true;
    
    // </editor-fold>

    // <editor-fold desc="Constructor">
    public ClienteChat(Socket s, String nick) {
        // Constructor
        // Crea la ventana para el cliente
        super(" Conexión al chat: " + nick);
        setLayout(null);
        msg.setBounds(10, 10, 400, 30);
        add(msg);
        tArea = new JTextArea();
        msgArea = new JScrollPane(tArea);
        msgArea.setBounds(10, 50, 400, 300);
        add(msgArea);
        buttonSend.setBounds(420, 10, 100, 30);
        add(buttonSend);
        buttonExit.setBounds(420, 50, 100, 30);
        add(buttonExit);
        tArea.setEditable(false);
        buttonSend.addActionListener(this);
        buttonExit.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        socket = s;
        this.name = nick;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            String texto = "--- " + nick + " se ha unido ---";
            out.writeUTF(texto);
        } catch (IOException e) {
            System.out.println("ERROR - Ha ocurrido un error desconocido.");
            e.printStackTrace();
            System.exit(0);
        }
    }
    // </editor-fold>

    // <editor-fold desc="Event Listeners">
    @Override
    public void actionPerformed(ActionEvent e) {
        //Boton Enviar
        if (e.getSource() == buttonSend) {
            if (msg.getText().trim().length() == 0) {
                return;
            }
            String texto = name + ": " + msg.getText();
            try {
                msg.setText("");
                out.writeUTF(texto);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        //Boton salir
        if (e.getSource() == buttonExit) {
            String texto = "*" + name+" ha abandonado el chat.*";
            try {
                out.writeUTF(texto);
                out.writeUTF("*");
                ask = false;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    // </editor-fold>

    // <editor-fold desc="Método Run y Main">
    @Override
    public void run() {
        String text = "";
        while (ask) {
            try {
                text = in.readUTF();
                tArea.setText(text);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR - No se ha podido \n" +
                    "establecer una conexión con el servidor.\n");
                ask = false;
            }
        }
        try {
            socket.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        int puerto = 6000;
        Socket conn = null;
        String name = JOptionPane.showInputDialog("Introduzca su nickname:");
        if (name.trim().length() == 0) {
            System.out.println("ERROR: Introduce un nombre válido.");
            return;
        }
        try {
            conn = new Socket("localhost", puerto);
            ClienteChat client = new ClienteChat(conn, name);
            client.setBounds(0, 0, 540, 400);
            client.setVisible(true);
            new Thread(client).start();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR - No se ha podido "
                    + "establecer una conexión con el servidor.\n");
        }
    }
    // </editor-fold>
}
