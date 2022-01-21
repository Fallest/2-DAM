package Ejercicio1;

import java.net.*;

/**
 * Ejercicio 1:
 * Es necesario importar java.net
 *  1. Definir un objeto InetAddress de nombre dir. Obtener la IP de la máquina local.
 *  2. Crear el método priv static void pruebaMetodos(InetAddress dir) que pruebe 
 *      los métodos GetByName(), getLocalHost(), getHostName(), getHostAdress(),
 *      toString(), y getCanonicalHostName().
 *  3. usar el objeto para obtener la IP de www.google.es.
 *  4. Usando el método getAllByName(), ver todas las IP asignadas a www.google.es.
 */

public class Ejercicio1 {
    public static void main(String[] args) {
        // Creación del objeto InetAddress y obtención de la IP de la máquina local.
        try {
            InetAddress dir = InetAddress.getLocalHost();
            System.out.println("Nombre/IP: " + dir.toString());
            
            // Prueba del método pruebaMetodos
            pruebaMetodos(dir);
            
            // Obtener la IP de www.google.es
            InetAddress google = InetAddress.getByName("www.google.es");
            System.out.println("IP de google: " + google.getHostAddress());
            
            // Uso del método getAllByName
            System.out.println("--------------------------------");
            InetAddress[] ips = InetAddress.getAllByName("www.google.com");
            
            int c = 0;
            for (InetAddress adr : ips) {
                c++;
                System.out.println("IP " + c + " de google: " + adr.getHostAddress());
                pruebaMetodos(adr);
            }
            
        } catch (UnknownHostException ex) {
            System.out.println("ERROR - Ha ocurrido un error al crear el objeto "
                    + "InetAddress.");
        }
        
        
        
    }
    
    public static void pruebaMetodos(InetAddress dir) throws UnknownHostException {
        System.out.println("----------pruebaMetodos----------");
        // Prueba de getByName y toString
        InetAddress d1 = InetAddress.getByName(dir.getHostName());
        System.out.println("Dirección original: " + dir.toString() 
                + " // Dirección nueva: " + d1.toString());
        
        // Prueba de getLocalHost
        System.out.println("Local host: " + InetAddress.getLocalHost());
        
        // Prueba de getHostName
        System.out.println("Host name: " + dir.getHostName());
        
        // Prueba de getHostAddress
        System.out.println("Host address: " + dir.getHostAddress());
        
        // Prueba de getCanonicalHostName
        System.out.println("Canonical Host Name: " + dir.getCanonicalHostName());
        System.out.println("--------------------------------");
    }
    
}
