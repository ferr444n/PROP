package FONTS.presentacio;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class PantallaGestioProductes extends javax.swing.JFrame {

    private CtrlPresentacio cp;
    
    public PantallaGestioProductes(CtrlPresentacio cp) {
        this.cp = cp;
        initComponents();
        cargarProductos();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jButtonBack = new javax.swing.JButton();
        jScrollPaneProductes = new javax.swing.JScrollPane();
        jTableProductes = new javax.swing.JTable();
        jButtonAfegir = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelFondo.setPreferredSize(new java.awt.Dimension(540, 420));
        panelFondo.setLayout(null);

        jButtonBack.setText("Back");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonBack);
        jButtonBack.setBounds(310, 270, 72, 23);

        jTableProductes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Productes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneProductes.setViewportView(jTableProductes);
        if (jTableProductes.getColumnModel().getColumnCount() > 0) {
            jTableProductes.getColumnModel().getColumn(0).setResizable(false);
        }

        panelFondo.add(jScrollPaneProductes);
        jScrollPaneProductes.setBounds(10, 10, 200, 280);

        jButtonAfegir.setText("Afegir");
        jButtonAfegir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAfegirActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonAfegir);
        jButtonAfegir.setBounds(220, 50, 170, 23);

        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonModificar);
        jButtonModificar.setBounds(220, 100, 170, 23);

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonEliminar);
        jButtonEliminar.setBounds(220, 150, 170, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void cargarProductos() {
        DefaultTableModel model = (DefaultTableModel) jTableProductes.getModel();
        model.setRowCount(0);
        ArrayList<String> productos = cp.getAllProductos();
        // Añadir cada producto como una fila en la tabla
        for (String producto : productos) {
            model.addRow(new Object[]{producto});
        }
    }


    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        
        PantallaInici ini = new PantallaInici(cp);
        ini.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        PantallaEliminar pe = new PantallaEliminar(cp);
        pe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonAfegirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAfegirActionPerformed
        
        PantallaProducte prod = new PantallaProducte(cp);
        prod.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButtonAfegirActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {                                               
        PantallaSeleccionProducto modificar = new PantallaSeleccionProducto(cp);
        modificar.setVisible(true);
        this.dispose();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAfegir;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JScrollPane jScrollPaneProductes;
    private javax.swing.JTable jTableProductes;
    private javax.swing.JPanel panelFondo;
    // End of variables declaration//GEN-END:variables
}
