package Exceptions;

/**
 * Clase para manejar los errores.
 * Hereda de Exception (la clase de Java para las escepciones).
 * Va a tener métodos para tres tipos de excepciones:
 * -Excepciones para un boolean que no se ha convertido (parseado) bien.
 * -Excepciones para un attributo que no se ha convertido (parseado) bien.
 * -Excepciones para un float que no se ha convertido (parseado) bien.
 * 
 * Su funcionamiento es muy sencillo: 
 * En el constructor tiene un switch, que dependiendo de la cadena que se haya 
 * usado para crear la excepción, llamará a un método u otro.
 */

public final class Exceptions extends Exception {
    
    public Exceptions(String s) {
        switch (s) {
            case "bool" -> {invalidBool();}
            case "attr" -> {invalidAttrName();}
            case "float" -> {invalidFloat();}
        }
        
    }
    
    public void invalidBool() {
        System.out.println("\n\tError - Evaluación de booleano no válido.\n Introduzca una \"s\" o una \"n\".");
    }
    
    public void invalidAttrName() {
        System.out.println("\n\tError - No es un nombre de atributo. \nEl segundo parámetro debe ser un nombre de atributo.");
    }

    private void invalidFloat() {
        System.out.println("\n\tError - Formato float no válido. \n Usa \".\" como separador decimal.");
    }
}