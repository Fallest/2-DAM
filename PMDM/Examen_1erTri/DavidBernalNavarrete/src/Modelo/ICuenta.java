package Modelo;

import java.util.Calendar;

/**
 * Interfaz para castellanizar las constantes de la clase Calendar y añadir un 
 * método booleano para saber si la fecha de la cuenta tiene el mismo mes
 * que el actual.
 */

public interface ICuenta {
    public static final int DIA_DEL_MES = Calendar.DAY_OF_MONTH;
    public static final int MES = Calendar.MONTH;
    public static final int AÑO = Calendar.YEAR;
    
    public abstract boolean comprobarMes();
}
