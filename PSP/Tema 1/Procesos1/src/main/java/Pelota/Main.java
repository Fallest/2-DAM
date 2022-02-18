package Pelota;

import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) {
        JFrame marco = new MarcoRebote();

        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        marco.setVisible(true);
        
        
    }
}

class Pelota {

    private static final int TAMX = 15;
    private static final int TAMY = 15;

    private double 
            x = 0,
            y = 0,
            dx = 1,
            dy = 1;

    public synchronized void mueve_pelota(Rectangle2D limites) {
        x += dx;
        y += dy;

        if (x < limites.getMinX()) {
            x = limites.getMinX();
            dx = -dx;
        }
        if (x + TAMX >= limites.getMaxX()) {
            x = limites.getMaxX() - TAMX;
            dx = -dx;
        }
        if (y < limites.getMinY()) {
            y = limites.getMinY();
            dy = -dy;
        }
        if (y + TAMY >= limites.getMaxY()) {
            y = limites.getMaxY() - TAMY;
            dy = -dy;
        }
    }

    public Ellipse2D getShape() {
        return new Ellipse2D.Double(x, y, TAMX, TAMY);
    }
}

class LaminaPelota extends JPanel {

    private static ArrayList<Pelota> pelotas = new ArrayList<Pelota>();

    public synchronized void add(Pelota b) {
        pelotas.add(b);
    }

    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Pelota p : pelotas) {
            g2.fill(p.getShape());
        }
    }
}

class MarcoRebote extends JFrame {
    
    private ArrayList<Thread> listaHilos = new ArrayList<Thread>();
    
    private int cont = 0;

    private LaminaPelota lamina;

    public MarcoRebote() {
        setBounds(600, 300, 700, 350);
        setTitle("Rebotes");

        lamina = new LaminaPelota();

        add(lamina, BorderLayout.CENTER);

        JPanel laminaBotones = new JPanel();

//        ponerBoton(laminaBotones, "Dale!", new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evento) {
//                comenzar_juego();
//            }
//        });

        ponerBoton(laminaBotones, "Crear H1", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                añadirPelota(1);
            }
        });
        
        ponerBoton(laminaBotones, "Crear H2", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                añadirPelota(2);
            }
        });
        
        ponerBoton(laminaBotones, "Crear H3", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                añadirPelota(3);
            }
        });

        ponerBoton(laminaBotones, "Salir", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                System.exit(0);
            }
        });
        
        ponerBoton(laminaBotones, "Eliminar H1", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                eliminarPelota(1);
            }
        });
        
        ponerBoton(laminaBotones, "Eliminar H2", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                eliminarPelota(2);
            }
        });
        
        ponerBoton(laminaBotones, "Eliminar H3", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                eliminarPelota(3);
            }
        });

        add(laminaBotones, BorderLayout.SOUTH);
    }

    //Ponemos botones
    public void ponerBoton(Container c, String titulo, ActionListener oyente) {
        JButton boton = new JButton(titulo);
        c.add(boton);
        boton.addActionListener(oyente);
    }
    
    public  void añadirPelota(int num) {
        Thread hilo = new Thread(new Hilo(lamina));
        hilo.setName("Hilo " + num);
        hilo.start();
        System.out.println("Creado el hilo: " + hilo.getName());

    }
    
    public void eliminarPelota(int num) {
        Set<Thread> hilos = (Thread.getAllStackTraces()).keySet();
        
        for (Thread t: hilos)
            if (t.getName().equals("Hilo " + num)) {
                t.interrupt();
                System.out.println("Se ha eliminado el hilo " + t.getName());
            }
    }        
    
    public void comenzar_juego() {
        cont++;
        Thread hilo = new Thread(new Hilo(lamina));
        hilo.setName("Hilo " + Integer.toString(cont));
        listaHilos.add(hilo);
        hilo.start();
        System.out.println("Creado el hilo: " + hilo.getName());
    }
    
    public void borrar_hilo() {
        Thread hiloEliminar = listaHilos.get(cont-1);
        System.out.println("Eliminado el hilo: " + hiloEliminar);
        listaHilos.remove(cont-1);
        hiloEliminar.interrupt();
        cont--;
    }
}

/* Ejercicio 2 */
class Hilo implements Runnable {
    
    private boolean running = true;
    private LaminaPelota lamina;

    Hilo(LaminaPelota lamina) {
        this.lamina = lamina;
    }

    @Override
    public void run() {
        Pelota pelota = new Pelota();

        lamina.add(pelota);

        while (running) {
            pelota.mueve_pelota(lamina.getBounds());
            lamina.paint(lamina.getGraphics());
            /* Ejercicio 1 */
            try {
                sleep(3);
            } catch (InterruptedException ex) {
                running = false;
            }
        }
    }   
}