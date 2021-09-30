package BernalNavarreteDavid.Controlador;

public final class Exceptions extends Exception {
    
    public Exceptions(String s) {
        switch (s) {
            case "bool" -> {invalidBool();}
            case "attr" -> {invalidAttrName();}
            case "float" -> {invalidFloat();}
            case "dep" -> {invalidDep();}
        }
    }
    
    public void invalidDep() {
        System.out.println("\n\tError - Departamento no válido. Formato inválido.");
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