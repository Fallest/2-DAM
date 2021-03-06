package View;

import javax.swing.JPanel;

public final class MainFrame extends javax.swing.JFrame {

    // Variables para los paneles:
    private final static MainFrame mainFrame = new MainFrame();
    private static Login loginPanel;
    private static Empleados empleadosPanel;
    private static boolean userAccess = false;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();

        // Usamos referencias propias de los paneles para acceder a variables
        // y métodos no estáticos.
        loginPanel = Login.getPane();
        empleadosPanel = Empleados.getPane();

        // Desactivar botones que no se usan hasta estar logeado.
        MainFrame.login.setEnabled(false);
        MainFrame.empleados.setEnabled(false);

        // Mostrar el panel de Login.
        Login.init();
        this.setContentPane(loginPanel);
        this.pack();
    }

    // <editor-fold defaultstate="collapsed" desc="Métodos para cambiar paneles"> 
    public void changePanel(JPanel panel) {
        mainFrame.setContentPane(panel);
        mainFrame.pack();
    }

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    public static Login getLoginPanel() {
        return loginPanel;
    }

    public static Empleados getOrdersPanel() {
        return empleadosPanel;
    }

    public static void changeUserAccess(boolean b) {
        mainFrame.setUserAccess(b);
    }

    public void setUserAccess(boolean b) {
        MainFrame.userAccess = b;
    }

    public static boolean getUserAccess() {
        return MainFrame.userAccess;
    }

    // </editor-fold>
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar = new javax.swing.JMenuBar();
        login = new javax.swing.JMenu();
        empleados = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DavidBernalNavarrete");

        login.setText("Login");
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMouseClicked(evt);
            }
        });
        menuBar.add(login);

        empleados.setText("Empleados");
        empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empleadosMouseClicked(evt);
            }
        });
        menuBar.add(empleados);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseClicked
        // Cambia el panel del MainFrame al panel About.
        if (login.isEnabled()) {
            changePanel(MainFrame.getLoginPanel());
            MainFrame.login.setEnabled(false);
            MainFrame.empleados.setEnabled(false);

        }
    }//GEN-LAST:event_loginMouseClicked

    private void empleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empleadosMouseClicked
        // Desactivamos este menú y activamos los demás (menos login)
        if (empleados.isEnabled()) {
            changePanel(MainFrame.getOrdersPanel());
            MainFrame.empleados.setEnabled(false);
            Empleados.init();
        }
    }//GEN-LAST:event_empleadosMouseClicked

    public void resetMenu() {
        // Resetea los botones del menú
        MainFrame.login.setEnabled(false);
        MainFrame.empleados.setEnabled(false);
    }

    // </editor-fold>
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainFrame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JMenu empleados;
    public static javax.swing.JMenu login;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
}
