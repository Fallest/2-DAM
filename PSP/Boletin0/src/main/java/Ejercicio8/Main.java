package Ejercicio8;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        int media;

        //CREACION E INSTANCIACION DEL ARRAY
        Nota notas[] = new Nota[6];

        notas[0] = new Nota("Programacion");
        notas[1] = new Nota("Lenguajes de Marcas");
        notas[2] = new Nota("Bases de Datos");
        notas[3] = new Nota("Entornos de Desarrollo");
        notas[4] = new Nota("Sistemas Informaticos");
        notas[5] = new Nota("Formacion y Orientacion Laboral");

        //PEDIMOS LAS NOTAS AL ALUMNO PARA COMPLETAR EL ARRAY
        pedirNotas(notas);

        //CALCULAMOS LA MEDIA
        media = calcularMedia(notas);

        //VOLCAMOS EL ARRAY EN EL FICHERO JUNTO CON LA NOTA MEDIA
        volcarDatos(notas, media);

    }

    public static int calcularMedia(Nota[] notas) {
        int media = 0;
        for (Nota nota : notas) {
            media = media + nota.getValor();
        }

        media = media / 6;

        return media;
    }

    public static void pedirNotas(Nota[] notas) {
        int num;

        for (Nota nota : notas) {
            do {
                System.out.println("Indique la nota de " + nota.getAsignatura());
                num = Leer.datoInt("");
            } while (num > 10 || num < 0);
            nota.setValor(num);
        }
    }

    public static void volcarDatos(Nota[] notas, int media) {

        try {
            File fichero = new File("C:\\Flujo\\Ejercicio8\\notas.obj");

            if (!fichero.exists()) {
                fichero.createNewFile();
            }

            FileWriter fw = new FileWriter(fichero);

            for (Nota nota : notas) {
                fw.append(nota.toString() + "\n");
            }

            fw.append("La nota media del curso es: " + media);

            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
