package View;

import Controller.*;
import Model.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import net.sourceforge.jdatepicker.impl.*;

/**
 * TO-DO: -DatePicker para cambiar la fecha de registro (solo disponible en la
 * cuenta admin). -Posibilidad de cambiasr el NIF desde la cuenta admin,
 * validándolo.
 */
public class Profile extends javax.swing.JPanel {

    private static Profile profile = new Profile();
    private JDatePickerImpl datePicker;

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
     * Función para inicializar los valores de las etiquetas y botones
     * dependiendo del tipo de usuario.
     */
    public static void init() {
        // Añadimos el DatePicker
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        profile.datePicker = new JDatePickerImpl(datePanel);

        profile.add(profile.datePicker);
        profile.datePicker.setBounds(330, 300, 150, 30);
        profile.datePicker.setEnabled(false);

        User user = MainFrame.getUser();
        if (user != null && user.isDelivery()) {
            // Si es un repartidor:
            DeliveryPerson del = (DeliveryManager.select("where del_cod = "
                    + user.getUsrName())).get(0);

            // Asignamos valores a las labels
            profile.typeUser.setText("Delivery");
            profile.name.setText(del.getDel_name());
            profile.attr1Name.setText("Delivery Code:");
            profile.attr1.setText(String.valueOf(del.getDel_cod()));
            profile.attr2Name.setText("Company:");
            profile.attr2.setText(del.getCompany().toUpperCase());
            profile.image.setText("");
            profile.image.setIcon(new ImageIcon("./src/Data/" + del.getCompany().toLowerCase() + ".png"));

            // Desactivamos los botones que no puede usar y modificamos el botón
            // de "pedidos" a "repartos".
            profile.changePicture.setVisible(false);
            profile.changeClientNif.setVisible(false);
            profile.newTransaction.setVisible(false);
            profile.clientsSectionTitle.setVisible(false);
            profile.clientsList.setVisible(false);
            profile.clientsLabel.setVisible(false);
            profile.changeNifTField.setVisible(false);
            profile.changeRegDate.setVisible(false);
            profile.datePicker.setVisible(false);
            profile.orders.setText("My Deliveries");
        } else if (user != null && !user.isDelivery()) {
            // Si es un cliente
            Client cli = (ClientManager.select("where nif = "
                    + user.getUsrName())).get(0);
            profile.typeUser.setText("Client");
            profile.name.setText(String.valueOf(cli.getNif()));
            profile.attr1Name.setText("Address:");
            profile.attr1.setText(cli.getDir());
            profile.attr2Name.setText("Register Date:");
            profile.attr2.setText(cli.getReg_date().toString());
            profile.image.setText("");
            profile.image.setIcon(new ImageIcon("./src/Data/" + cli.getPic().trim() + ".jpg"));

            // Desactivamos los botones que no puede usar
            profile.clientsSectionTitle.setVisible(false);
            profile.clientsList.setVisible(false);
            profile.clientsLabel.setVisible(false);
            profile.changeNifTField.setVisible(false);
            profile.changeClientNif.setVisible(false);
            profile.changeRegDate.setVisible(false);
            profile.datePicker.setVisible(false);
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
            profile.orders.setText("All Orders");

            profile.changePicture.setVisible(false);
            profile.newTransaction.setVisible(false);

            // Rellenamos la lista de clientes con Clientes
            profile.fillClientsList();
        }

        MainFrame.orders.setEnabled(true);
        MainFrame.newTransaction.setEnabled(MainFrame.isAdmin() ? false : true);
        profile.updateUI();
    }

    public void setImage(File f) {
        // Cambia el icono de la imagen
        this.image.setIcon(new ImageIcon(f.getAbsolutePath()));
    }

    public static void fillClientsList() {
        // Función que rellena la lsia de clientes con clientes
        DefaultListModel<String> model = new DefaultListModel<String>();

        ClientManager.select("").forEach(client -> {
            model.addElement(client.toString());
        });

        profile.clientsList.setModel(model);
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
        profile.newTransaction.setVisible(true);
        profile.clientsSectionTitle.setVisible(true);
        profile.clientsList.setVisible(true);
        profile.clientsLabel.setVisible(true);
        profile.datePicker.setVisible(true);
        profile.changeNifTField.setVisible(true);
        profile.changeNifTField.setEnabled(false);
        profile.changeClientNif.setEnabled(false);
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
        changeClientNif = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        clientsList = new javax.swing.JList<>();
        clientsSectionTitle = new javax.swing.JLabel();
        changeNifTField = new javax.swing.JTextField();
        clientsLabel = new javax.swing.JLabel();
        changeRegDate = new javax.swing.JButton();

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

        changeClientNif.setText("Change Client NIF");
        changeClientNif.setEnabled(false);
        changeClientNif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeClientNifActionPerformed(evt);
            }
        });

        clientsList.setBorder(null);
        clientsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        clientsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        clientsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientsListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(clientsList);

        clientsSectionTitle.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        clientsSectionTitle.setText("Clients");

        changeNifTField.setEnabled(false);

        clientsLabel.setText("Select a Client to modify their NIF or register date.");

        changeRegDate.setText("Change Register Date");
        changeRegDate.setEnabled(false);
        changeRegDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeRegDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(typeUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clientsSectionTitle))
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(orders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(newTransaction, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(clientsLabel)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 9, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(changeClientNif, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(changeRegDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(changeNifTField))
                                        .addGap(34, 34, 34)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(clientsSectionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(typeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(orders, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(newTransaction))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(name)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(attr1)
                            .addComponent(attr1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(attr2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(attr2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exit))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(changeClientNif)
                            .addComponent(changeNifTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(changeRegDate)))
                .addGap(8, 8, 8))
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
            // Aquí sería mejor actualizar la base de datos, indicando que la 
            // nueva imagen se encuentra en una dirección diferente, pero tendría
            // que cambiar cómo se accede y se usa este atributo en todas sus 
            // instancias, así que de momento no lo haré.
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

    private void changeClientNifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeClientNifActionPerformed
        // Cambiará el nif del cliente al indicado en el TextField
        String newNif = profile.changeNifTField.getText();
        // TO-DO: Código para comprobar que el nif es válido
        try {
            int nif = Integer.parseInt(newNif);
        } catch (NumberFormatException ex) {
            ExceptionManager.getError(2, "Profile.changeClientNifActionPerformed()");
        }
        if (newNif.length() != 8) {
            // El nif no es válido en longitud.
            ExceptionManager.getError(3, "");
        } else {
            String cliente = clientsList.getSelectedValue();
            String oldNif = cliente.substring(cliente.indexOf("nif") + 4,
                    cliente.indexOf("nif") + 12);
            OrderManager.update("nif_cli = " + newNif, "where nif_cli = " + oldNif);
            ClientManager.update("nif = " + newNif, "where nif = " + oldNif);

            Profile.fillClientsList();
        }

    }//GEN-LAST:event_changeClientNifActionPerformed

    private void clientsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientsListMouseClicked
        // Si se ha hecho click en la lista
        if (!clientsList.isSelectionEmpty()) {
            // Si se ha seleccionado algún elemento activamos los botones
            profile.changeNifTField.setEnabled(true);
            profile.changeClientNif.setEnabled(true);
            profile.datePicker.setEnabled(true);
            profile.changeRegDate.setEnabled(true);
        } else {
            // Si se ha seleccionado algún elemento activamos los botones
            profile.changeNifTField.setEnabled(false);
            profile.changeClientNif.setEnabled(false);
            profile.datePicker.setEnabled(false);
            profile.changeRegDate.setEnabled(false);
        }

    }//GEN-LAST:event_clientsListMouseClicked

    private void changeRegDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeRegDateActionPerformed
        // Comprobar la fecha seleccionada y si es correcta hacer un update
        Date selectedDate = (Date) profile.datePicker.getModel().getValue();
        // Si es despues de la fecha actual o antes de 2020.
        if (selectedDate.after(new Date())) {
            ExceptionManager.getError(4, "");
        } else {
            // Realizamos el update
            String cliente = clientsList.getSelectedValue();
            String nif = cliente.substring(cliente.indexOf("nif") + 4,
                    cliente.indexOf("nif") + 12);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormatter.format(selectedDate);
            ClientManager.update("reg_date = \'" + date + "\'",
                    "where nif = " + nif);

            Profile.fillClientsList();
        }
    }//GEN-LAST:event_changeRegDateActionPerformed

    // </editor-fold>
    private JFileChooser fileChooser;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel attr1;
    private javax.swing.JLabel attr1Name;
    private javax.swing.JLabel attr2;
    private javax.swing.JLabel attr2Name;
    private javax.swing.JButton changeClientNif;
    private javax.swing.JTextField changeNifTField;
    private javax.swing.JButton changePicture;
    private javax.swing.JButton changeRegDate;
    private javax.swing.JLabel clientsLabel;
    private javax.swing.JList<String> clientsList;
    private javax.swing.JLabel clientsSectionTitle;
    private javax.swing.JButton exit;
    private javax.swing.JLabel image;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel name;
    private javax.swing.JButton newTransaction;
    private javax.swing.JButton orders;
    private javax.swing.JLabel typeUser;
    // End of variables declaration//GEN-END:variables
}
