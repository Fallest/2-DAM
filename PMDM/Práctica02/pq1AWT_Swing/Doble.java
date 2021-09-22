package pq1AWT_Sswing;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import java.text.*;

public class Doble extends JFrame
{
    //Valores para los campos de texto
    private int numero = 0;
    private int doble = 0;
    // private NumberFormat integerFormatter;

    //Etiquetas para identificar los campos de texto
    private JLabel numeroLabel;
    private JLabel dobleLabel;

    //Cadenas para las etiquetas
    private static String numeroString = "Número: ";
    private static String dobleString = "El doble es: ";

    //Text fields para introducir números
    private TextField numeroField;
    private TextField dobleField;

    private boolean focusIsSet = false;

    public Doble() {
        super("Doble");

        //Crea las etiquetas.
        numeroLabel = new JLabel(numeroString);
        dobleLabel = new JLabel(dobleString);

        //Campo donde vamos a introducir el número
        numeroField = new TextField(10);

        //Lo que hace cuando pulsamos Enter
        numeroField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String n = numeroField.getText();
                try {
                    numero = Integer.parseInt(n);
                    doble = numero * 2;
                    dobleField.setText(""+doble);
                } catch(NumberFormatException nfe) {
                    //Mensaje de error
                    System.err.println(
                         "No es un entero: '"+n+"'");
                }
            }
        });

        //Campo que nos muestra el doble del numero introducido
        dobleField = new TextField(10);
        dobleField.setEditable(false);
        dobleField.setForeground(Color.red);

        //Dispone la geometría de las etiquetas en un panel
        JPanel labelPane = new JPanel();
        labelPane.setLayout(new GridLayout(0, 1));
        labelPane.add(numeroLabel);
        labelPane.add(dobleLabel);

        //Dispone los campos de texto en otro panel
        JPanel fieldPane = new JPanel();
        fieldPane.setLayout(new GridLayout(0, 1));
        fieldPane.add(numeroField);
        fieldPane.add(dobleField);

        //Incluye los dos paneles en otro panel,
        //etiquetas a la izquierda
        //y campos de texto a la derecha.
        JPanel contentPane = new JPanel();
        contentPane.setBorder(
               BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(labelPane, BorderLayout.CENTER);
        contentPane.add(fieldPane, BorderLayout.EAST);

        setContentPane(contentPane);  //this.setContentPane(contentPane);
          // es set, o sea, machado el contenedor del objeto "Doble", hijo de JFrame

    }

    public static void main(String[] args) {
        
        final Doble app = new Doble();

        //Lo que pasa si cerramos la ventana
        app.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            //Pone el focus (foco de atencion)
            public void windowActivated(WindowEvent e) {
                app.setFocus();
            }
        });
        app.pack();
        app.setVisible(true);
    }

    private void setFocus() {
        if (!focusIsSet) {
            numeroField.requestFocus();
            focusIsSet = true;
        }
    }

}

