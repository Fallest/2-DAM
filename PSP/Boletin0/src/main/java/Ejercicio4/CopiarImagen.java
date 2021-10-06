package Ejercicio4;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class CopiarImagen {
    private File imagen;
    private File imagenCopia;
    
    public CopiarImagen(String ruta, String archivo) {
        this.imagen = new File(ruta + "\\" + archivo) {
            /**
             * Clase anónima para escribirle un método a la clase File.
             * El método es para contar los Bytes que tiene un archivo.
             * Utiliza un FileInputStream para ir byte a byte.
             */
            public int calcularBytes() {
                int cont = 0;
                
                try {
                    FileInputStream arch = new FileInputStream(this);
                    
                    while(arch.read() != -1) {
                        cont++;
                    }
                    
                    arch.close();
                    System.out.println("Ha contado los bytes");
                }
                catch(IOException e) {}
                
                return cont;
            }
            
            public int[] extraerBytes() {
                int numBytes = this.calcularBytes();
                int data[] = new int[numBytes];
                
                
                try {
                    FileInputStream arch = new FileInputStream(this);
                    
                    for (int i = 0; i < numBytes; i++) {
                        data[i] = arch.read();
                    }
                    
                    arch.close();
                    System.out.println("Ha extraído los bytes");
                }
                catch (IOException e) {}
                
                return data;
            }
            
            public File copiar() {
                File copia = new File(ruta + "\\Copia\\" 
                        + archivo.substring(0, archivo.length() - 5) + "-copia.jpeg");
                try {
                    if(copia.createNewFile())
                        System.out.println("Copia creada.");
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }
                
                FileOutputStream flujoEscritura;
                try {
                    flujoEscritura = new FileOutputStream(copia);
                    
                    if (this.exists())
                    {
                        // Copiamos byte a byte
                        int[] byteArray = this.extraerBytes();
                        for (int i = 0; i < byteArray.length; i++)
                            flujoEscritura.write(byteArray[i]);
                    }
                    System.out.println("Ha escrito los bytes");
                    flujoEscritura.close();
                } catch (FileNotFoundException ex) {
                    System.out.println("Error: El archivo no existe.");
                } catch (IOException ex) {}
                
                return copia;
            }
        };
        
        try {
            /**
             * Usamos un objeto de tipo Method para extraer el método de la clase
             * anónima que acabamos de construir. Luego podemos acceder al método
             * "copiar" utilizando el método invoke.
             * ¿Por qué este lio?
             * Porque por alguna razon no puedo llamar a imagen.copiar().
             */
            Method m = imagen.getClass().getMethod("copiar");
            imagenCopia = (File) m.invoke(imagen);
        } 
        catch (NoSuchMethodException | SecurityException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException ex) 
        {System.out.println("Oops.");}
        
    }
    
    public static void main(String args[]) {
        String ruta = "C:\\Flujo\\Ejercicio4";
        String archivo = "imagen.jpeg";
        new CopiarImagen(ruta, archivo);
    }
}
