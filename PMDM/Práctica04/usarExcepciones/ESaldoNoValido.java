package usarExcepciones;

public class ESaldoNoValido extends Exception {        
    public ESaldoNoValido() {}
    public ESaldoNoValido(String mensaje) {
        super(mensaje);
    };    
}
