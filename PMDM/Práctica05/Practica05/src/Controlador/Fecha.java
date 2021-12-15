package Controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Fecha {
    // Atributos
    private int _dia;
    private int _mes;
    private int _año;

    /*---------------------------------------------------------------*/ // 1/1
    // Constructor:
    public Fecha(int dia, int mes, int año) {

        if (!checkFecha(dia, mes, año))
            throw new NumberFormatException();

        this._dia = dia;
        this._mes = mes;
        this._año = año;
    }

    /*---------------------------------------------------------------*/ // 3/3
    // Setters:
    public void setDia(int dia) {

        if (!checkFecha(dia, this._mes, this._año))
            throw new NumberFormatException();

        this._dia = dia;
    }

    public void setMes(int mes) {

        if (!checkFecha(this._dia, mes, this._año))
            throw new NumberFormatException();

        this._mes = mes;
    }

    public void setAño(int año) {

        if (!checkFecha(this._dia, this._mes, año))
            throw new NumberFormatException();

        this._año = año;
    }

    /*---------------------------------------------------------------*/ // 3/3
    // Getters:
    public int getDia() {
        return this._dia;
    }

    public int getMes() {
        return this._mes;
    }

    public int getAño() { 
        return this._año;
    }

    /*---------------------------------------------------------------*/ // 4/4
    // Funciones auxiliares:

    // M�todo checkFecha:
    private static boolean checkFecha(int dia, int mes, int año) {
        // Va a devolver false si la fecha no es v�lida.
        boolean esValido = true;

        // Comprobaci�n b�sica de los d�as, meses y a�os.
        if ((dia < 1 || dia > 31) || (mes < 1 || mes > 12) || (año < 1000 || año > 2030))
            esValido = false;

        // Tratamiento de a�os bisiestos (solo febrero).
        switch (año % 4) {
            case 0 ->  {
                if (mes == 2 && dia > 29)
                    esValido = false;
            }
            default ->  {
                if (mes == 2 && dia > 28)
                    esValido = false;
            }
        }

        // Tratamiento del resto de meses.
        if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30)
            esValido = false;

        return esValido;
    }

    // M�todo toInt:
    public static int toInt(Fecha fecha) {
        int diasTotales, i;

        /*
        Para calcular los d�as correspondientes a los a�os, multiplicamos el a�o
        por 365, y para sumar los a�os bisiestos, solo calculamos la cantidad de
        a�os bisiestos que hay, y sumamos +1 por cada a�o bisiesto, porque tienen
        un d�a m�s.
        Al hacer esto nos ahorramos tener que tratar el febrero de los a�os bisiestos
        al sumar los d�as correspondientes a los meses.
        */
        diasTotales = (fecha.getAño() * 365 + fecha.getAño() / 4);

        /*
        Para calcular los d�as correspondientes a los meses, vamos a usar un bucle
        for, en el que vamos a ir sumando la cantidad de d�as del mes correspondiente
        empezando desde un mes anterior al de la fecha. La raz�n para empezar por el
        mes anterior, es que si por ejemplo tenemos 20/3, comenzar� por el 3, y sumar�
        31, en vez de 20. La suma de los d�as de el mes de la fecha se hace despu�s.
        */
        for (i = fecha.getMes() - 1; i > 0; i--) {
            // Meses de 31 d�as.
            switch (i) {
                case 1, 3, 5, 7, 8, 10, 12 -> diasTotales += 31;
                // Febrero.
                case 2 -> diasTotales += 28; // Siempre 28, porque el d�a extra del a�o bisiesto
                // ya se a�ade con los a�os.
                // Meses de 30 d�as.
                default -> diasTotales += 30;
            }
        }

        // Suma de los d�as de la fecha
        diasTotales += fecha.getDia();

        return diasTotales;
    }

    // M�todo toString:
    public String toString(Fecha fecha) {
        return fecha._dia + "/" + fecha._mes + "/" + fecha._año;
    }
    
    @Override
    public String toString() {
        return this._dia + "/" + this._mes + "/" + this._año;
    }
    
    // M�todo parseFecha
    public static Fecha parseFecha(String s) {
        // Va a recibir una cadena con el formato "DD/MM/YYYY".
        String aux[] = s.split("/");
        
        return new Fecha(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), Integer.parseInt(aux[2]));
    }
    
    public static boolean tryParse(String s) {
        try {
            String[] data = s.split("/");
            return checkFecha(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    // M�todos para calcular el tiempo transcurrido entre dos fechas
    public static int diasEntre(Fecha fecha1, Fecha... fechas) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        Date date1 = sdf.parse(fecha1.toString());
        Date date2 = fechas.length > 0 ? sdf.parse(fechas[0].toString()) : new Date();
        
        if (fechas.length > 1) throw new IllegalArgumentException();
        
        LocalDate fechaActual = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        LocalDate fechaEntrada = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        return (int) ChronoUnit.DAYS.between(fechaEntrada, fechaActual);
    }
    
    
    public static int mesesEntre(Fecha fecha1, Fecha... fechas) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        Date date1 = sdf.parse(fecha1.toString());
        Date date2 = fechas.length > 0 ? sdf.parse(fechas[0].toString()) : new Date();
        
        if (fechas.length > 1) throw new IllegalArgumentException();
        
        LocalDate fechaActual = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        LocalDate fechaEntrada = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        return (int) ChronoUnit.MONTHS.between(fechaEntrada, fechaActual);
    }
    
    
    public static int añosEntre(Fecha fecha1, Fecha... fechas) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        Date date1 = sdf.parse(fecha1.toString());
        Date date2 = fechas.length > 0 ? sdf.parse(fechas[0].toString()) : new Date();
        
        if (fechas.length > 1) throw new IllegalArgumentException();
        
        LocalDate fechaActual = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        LocalDate fechaEntrada = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        return (int) ChronoUnit.YEARS.between(fechaEntrada, fechaActual);
    }

    public String format(String format) {
        if (format.equalsIgnoreCase("mm/dd/yyyy"))
            return (this._mes + "/" + this._dia + "/" + this._año);
        else
            return toString();
    }
    
}
