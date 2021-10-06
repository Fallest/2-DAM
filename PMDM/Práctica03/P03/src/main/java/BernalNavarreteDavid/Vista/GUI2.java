/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BernalNavarreteDavid.Vista;

/**
 *
 * @author David
 */
public class GUI2 extends javax.swing.JFrame {

    /**
     * Creates new form GUI2
     */
    public GUI2() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBienvenida = new javax.swing.JPanel();
        labelBienvenidaTitulo = new javax.swing.JLabel();
        labelBienvenidaAutor = new javax.swing.JLabel();
        labelBienvenidaAutor1 = new javax.swing.JLabel();
        mbarGUI = new javax.swing.JMenuBar();
        menuAcciones = new javax.swing.JMenu();
        mitemAlta = new javax.swing.JMenuItem();
        mitemListado = new javax.swing.JMenuItem();
        menuMas = new javax.swing.JMenu();
        mitenAutor = new javax.swing.JMenuItem();
        mitemVersion = new javax.swing.JMenuItem();

        labelBienvenidaTitulo.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        labelBienvenidaTitulo.setText("Listado de Empleados");

        labelBienvenidaAutor.setText("David Bernal Navarrete");

        labelBienvenidaAutor1.setText("Versión 1.0");

        javax.swing.GroupLayout panelBienvenidaLayout = new javax.swing.GroupLayout(panelBienvenida);
        panelBienvenida.setLayout(panelBienvenidaLayout);
        panelBienvenidaLayout.setHorizontalGroup(
            panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBienvenidaLayout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addGroup(panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelBienvenidaAutor1)
                    .addComponent(labelBienvenidaAutor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBienvenidaLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(labelBienvenidaTitulo)
                .addGap(25, 25, 25))
        );
        panelBienvenidaLayout.setVerticalGroup(
            panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBienvenidaLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(labelBienvenidaTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(labelBienvenidaAutor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelBienvenidaAutor1)
                .addGap(8, 8, 8))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuAcciones.setText("Acciones");

        mitemAlta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitemAlta.setText("Alta de empleados");
        menuAcciones.add(mitemAlta);

        mitemListado.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitemListado.setText("Listado de empleados");
        mitemListado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemListadoActionPerformed(evt);
            }
        });
        menuAcciones.add(mitemListado);

        mbarGUI.add(menuAcciones);

        menuMas.setText("Más..");

        mitenAutor.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        mitenAutor.setForeground(new java.awt.Color(204, 204, 204));
        mitenAutor.setText("David Bernal Navarrete");
        mitenAutor.setEnabled(false);
        menuMas.add(mitenAutor);

        mitemVersion.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        mitemVersion.setForeground(new java.awt.Color(204, 204, 204));
        mitemVersion.setText("Versión 1.0");
        mitemVersion.setEnabled(false);
        menuMas.add(mitemVersion);

        mbarGUI.add(menuMas);

        setJMenuBar(mbarGUI);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mitemListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemListadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mitemListadoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelBienvenidaAutor;
    private javax.swing.JLabel labelBienvenidaAutor1;
    private javax.swing.JLabel labelBienvenidaTitulo;
    private javax.swing.JMenuBar mbarGUI;
    private javax.swing.JMenu menuAcciones;
    private javax.swing.JMenu menuMas;
    private javax.swing.JMenuItem mitemAlta;
    private javax.swing.JMenuItem mitemListado;
    private javax.swing.JMenuItem mitemVersion;
    private javax.swing.JMenuItem mitenAutor;
    private javax.swing.JPanel panelBienvenida;
    // End of variables declaration//GEN-END:variables
}
