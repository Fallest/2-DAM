package Main;

import java.io.*;
import java.net.*;

public class PortScanner {
    public static void main(String[] args) {
        System.out.println("Iniciando escaneo de puertos...");
        for(int i = 1; i <= 1024; i++) {
            try {
                Socket localConn = new Socket("localhost", i);
                System.out.println("El puerto " + i + " está abierto.");
            } catch (IOException ex) {
            }
        }
    }
}
