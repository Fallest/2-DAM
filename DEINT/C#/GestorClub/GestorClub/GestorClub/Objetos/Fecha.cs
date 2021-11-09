using System;

namespace GestorClub.Objetos {
class Fecha {
    // Atributos
    private int _dia;
    private int _mes;
    private int _año;

    /*---------------------------------------------------------------*/ // 1/1
    // Constructor:
    public Fecha(int dia, int mes, int año) {

        if (!CheckFecha(dia, mes, año))
            throw new FormatException();

        this._dia = dia;
        this._mes = mes;
        this._año = año;
    }

    /*---------------------------------------------------------------*/ // 3/3
    // Setters:
    public void SetDia(int dia) {

        if (!CheckFecha(dia, this._mes, this._año))
            throw new FormatException();

        this._dia = dia;
    }

    public void SetMes(int mes) {

        if (!CheckFecha(this._dia, mes, this._año))
            throw new FormatException();

        this._mes = mes;
    }

    public void SetAño(int año) {

        if (!CheckFecha(_dia, this._mes, año))
            throw new FormatException();

        this._año = año;
    }

    /*---------------------------------------------------------------*/ // 3/3
    // Getters:
    public int GetDia() {
        return this._dia;
    }

    public int GetMes() {
        return this._mes;
    }

    public int GetAño() {
        return this._año;
    }

    /*---------------------------------------------------------------*/ // 4/4
    // Funciones auxiliares:

    // Método checkFecha:
    private bool CheckFecha(int dia, int mes, int año) {
        // Va a devolver false si la fecha no es válida.
        bool esValido = true;

        // Comprobación básica de los días, meses y años.
        if ((dia < 1 || dia > 31) || (mes < 1 || mes > 12) || (año < 1 || año > 2030))
            esValido = false;

        // Tratamiento de años bisiestos (solo febrero).
        switch (año % 4) {
            case 0: {
                if (mes == 2 && dia > 29)
                    esValido = false;
                break;
            }
            default: {
                if (mes == 2 && dia > 28)
                    esValido = false;
                break;
            }
        }

        // Tratamiento del resto de meses.
        if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30)
            esValido = false;

        return esValido;
    }

    // Método toInt:
    public static int ToInt(Fecha fecha) {
        int diasTotales, i;

        /*
        Para calcular los días correspondientes a los años, multiplicamos el año
        por 365, y para sumar los años bisiestos, solo calculamos la cantidad de
        años bisiestos que hay, y sumamos +1 por cada año bisiesto, porque tienen
        un día más.
        Al hacer esto nos ahorramos tener que tratar el febrero de los años bisiestos
        al sumar los días correspondientes a los meses.
        */
        diasTotales = (fecha.GetAño() * 365 + fecha.GetAño() / 4);

        /*
        Para calcular los días correspondientes a los meses, vamos a usar un bucle
        for, en el que vamos a ir sumando la cantidad de días del mes correspondiente
        empezando desde un mes anterior al de la fecha. La razón para empezar por el
        mes anterior, es que si por ejemplo tenemos 20/3, comenzará por el 3, y sumará
        31, en vez de 20. La suma de los días de el mes de la fecha se hace después.
        */
        for (i = fecha.GetMes() - 1; i > 0; i--) {
            // Meses de 31 días.
            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12)
                diasTotales += 31;
            // Febrero.
            else if (i == 2)
                diasTotales += 28; // Siempre 28, porque el día extra del año bisiesto
            // ya se añade con los años.
            // Meses de 30 días.
            else
                diasTotales += 30;
        }

        // Suma de los días de la fecha
        diasTotales += fecha.GetDia();

        return diasTotales;
    }

    // Método toString:
    public string ToString(Fecha fecha) {
        return fecha._dia + "/" + fecha._mes + "/" + fecha._año;
    }
    
    // Método parseFecha
    public static Fecha ParseFecha(string s) {
        // Va a recibir una cadena con el formato "DD/MM/YYYY".
        string[] aux = s.Split("/");
        int d, m = 0, a = 0;
        if (!(Int32.TryParse(aux[0], out d)
              && Int32.TryParse(aux[1], out m)
              && Int32.TryParse(aux[2], out a))) {
            return new Fecha(d, m, a);
        }
        else {
            throw new FormatException();
        }
    }

    public static bool TryParse(string s, out Fecha fecha) {
        try {
            fecha = ParseFecha(s);
            return true;
        }
        catch (FormatException fex) {
            fecha = null;
            return false;
        }
    }
    
    public static bool TryParse(string s) {
        try {
            ParseFecha(s);
            return true;
        }
        catch (FormatException fex) {
            return false;
        }
    }
}
}