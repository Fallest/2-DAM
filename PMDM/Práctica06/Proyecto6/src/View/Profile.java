package View;

import Controller.*;
import Model.*;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 * TO-DO:
 * -DatePicker para cambiar la fecha de registro (solo disponible en la cuenta admin).
 * -Posibilidad de cambiasr el NIF desde la cuenta admin, validándolo.
 */
public class Profile extends javax.swing.JPanel {

    private static Profile profile = new Profile();
    /**
     * Creates new form Profile
     */
    public Profile() {
        initComponents();
    }

    public static Profile getPane() {
        return profile;
    }

    /**
     * Función para inicializar los valores de las etiquetas y botones dependiendo
     * del tipo de usuario.
     */
    public static void init() {
        User user = MainFrame.getUser();
        if (user != null && user.isDelivery()) {
            // Si es un repartidor:
            DeliveryPerson del = (DeliveryManager.select("where del_cod = " + 
                    user.getUsrName())).get(0);
            
            // Asignamos valores a las labels
            profile.typeUser.setText("Delivery");
            profile.name.setText(del.getDel_name());
            profile.attr1Name.setText("Delivery Code:");
            profile.attr1.setText(String.valueOf(del.getDel_cod()));
            profile.attr2Name.setText("Company:");
            profile.attr2.setText(del.getCompany());
            profile.image.setText("");
            profile.image.setIcon(new ImageIcon("./src/Data/" + del.getCompany().toLowerCase() + ".png"));
            
            // Desactivamos los botones que no puede usar y modificamos el botón
            // de "pedidos" a "repartos".
            profile.changePicture.setVisible(false);
            profile.changeClientNif.setVisible(false);
            profile.changeRegDate.setVisible(false);
            profile.newTransaction.setVisible(false);
            profile.orders.setText("My Deliveries");
        } else if (user != null && !user.isDelivery()) {
            // Si es un cliente
            Client cli = (ClientManager.select("where nif = " + 
                    user.getUsrName())).get(0);
            profile.typeUser.setText("Client");
            profile.name.setText(String.valueOf(cli.getNif()));
            profile.attr1Name.setText("Address:");
            profile.attr1.setText(cli.getDir());
            profile.attr2Name.setText("Register Date:");
            profile.attr2.setText(cli.getReg_date().toString());
            profile.image.setText("");
            profile.image.setIcon(new ImageIcon("./src/Data/" + cli.getPic().trim() + ".jpg"));
            
            // Desactivamos los botones que no puede usar
            profile.changeClientNif.setVisible(false);
            profile.changeRegDate.setVisible(false);
        } 
        if (MainFrame.isAdmin()) {
            // Si es un administrador
            profile.typeUser.setText("Admin");
            profile.name.setText("Administrator");
            profile.attr1Name.setText("");
            profile.attr1.setText("");
            profile.attr2Name.setText("");
            profile.attr2.setText("");
            profile.image.setText("");
            profile.image.setIcon(new ImageIcon("./src/Data/default.jpg"));
        }
        
        MainFrame.orders.setEnabled(true);
        MainFrame.newTransaction.setEnabled(true);
    }
    
    public void setImage(File f) {
        // Cambia el icono de la imagen
        this.image.setIcon(new ImageIcon(f.getAbsolutePath()));
    }
    
    public static void closeSession() {
        // Reseteamos valores de las labels
        profile.typeUser.setText("TypeOfUser");
        profile.name.setText("Name");
        profile.attr1Name.setText("Attr1:");
        profile.attr1.setText("attr1");
        profile.attr2Name.setText("Attr2:");
        profile.attr2.setText("attr2");
        profile.image.setText("Image");
        profile.image.setIcon(null);

        // Reseteamos los botones
        profile.changePicture.setVisible(true);
        profile.changeClientNif.setVisible(true);
        profile.changeRegDate.setVisible(true);
        profile.newTransaction.setVisible(true);
        profile.orders.setText("My Orders");

        // Cambiamos de panel y cerramos sesión
        MainFrame.getMainFrame().changePanel(MainFrame.getLoginPanel());
        MainFrame.setAdmin(false);
        MainFrame.changeUserAccess(false);
        MainFrame.setUser(null);
        MainFrame.getMainFrame().resetMenu();
        Login.init();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        typeUser = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        attr1Name = new javax.swing.JLabel();
        attr1 = new javax.swing.JLabel();
        attr2Name = new javax.swing.JLabel();
        attr2 = new javax.swing.JLabel();
        newTransaction = new javax.swing.JButton();
        orders = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        changePicture = new javax.swing.JButton();
        changeRegDate = new javax.swing.JButton();
        changeClientNif = new javax.swing.JButton();

        typeUser.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        typeUser.setText("TypeOfUser");

        image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image.setText("Image");
        image.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        name.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        name.setText("Name");

        attr1Name.setText("Attr1");

        attr1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        attr1.setText("attr1");

        attr2Name.setText("Attr2");

        attr2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        attr2.setText("attr2");
        attr2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        newTransaction.setText("New Transaction");
        newTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTransactionActionPerformed(evt);
            }
        });

        orders.setText("My Orders");
        orders.setFocusCycleRoot(true);
        orders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersActionPerformed(evt);
            }
        });

        exit.setText("Close session");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });

        changePicture.setText("Change picture");
        changePicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePictureActionPerformed(evt);
            }
        });

        changeRegDate.setText("Change Register Date");

        changeClientNif.setText("Change Client NIF");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typeUser)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(changePicture)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(attr2Name, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                            .addComponent(attr1Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(attr1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                            .addComponent(attr2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(exit))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(changeRegDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(changeClientNif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(orders, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(newTransaction, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap(353, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(typeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(orders, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newTransaction)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(name)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(attr1)
                        .addComponent(changeClientNif))
                    .addComponent(attr1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(attr2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(attr2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeRegDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exit)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    // <editor-fold defaultstate="collapsed" desc="Event Listeners">
    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // Cerrar sesión
        closeSession();
    }//GEN-LAST:event_exitMouseClicked

    private void changePictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePictureActionPerformed
        // Permite seleccionar otra imagen para ponerla de perfil
        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(false);
        int returnVal = fileChooser.showOpenDialog(MainFrame.getMainFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            MainFrame.getProfilePanel().setImage(file);
        }
    }//GEN-LAST:event_changePictureActionPerformed

    private void ordersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersActionPerformed
        // Cambia al panel Orders
        MainFrame.getMainFrame().changePanel(MainFrame.getOrdersPanel());
        MainFrame.orders.setEnabled(false);
        MainFrame.profile.setEnabled(true);
        Orders.init();
    }//GEN-LAST:event_ordersActionPerformed

    private void newTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newTransactionActionPerformed
        // Cambia al panel NewTransaction
        MainFrame.getMainFrame().changePanel(MainFrame.getNewTransactionPanel());
        MainFrame.profile.setEnabled(true);
        MainFrame.newTransaction.setEnabled(false);
        NewTransaction.init();
    }//GEN-LAST:event_newTransactionActionPerformed
    
    // </editor-fold>
    
    private JFileChooser fileChooser;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel attr1;
    private javax.swing.JLabel attr1Name;
    private javax.swing.JLabel attr2;
    private javax.swing.JLabel attr2Name;
    private javax.swing.JButton changeClientNif;
    private javax.swing.JButton changePicture;
    private javax.swing.JButton changeRegDate;
    private javax.swing.JButton exit;
    private javax.swing.JLabel image;
    private javax.swing.JLabel name;
    private javax.swing.JButton newTransaction;
    private javax.swing.JButton orders;
    private javax.swing.JLabel typeUser;
    // End of variables declaration//GEN-END:variables
}
