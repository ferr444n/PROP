package FONTS.presentacio;

import javax.swing.table.DefaultTableModel;

import FONTS.excepcions.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class PantallaProducte extends javax.swing.JFrame {

    private CtrlPresentacio cp;

    public PantallaProducte(CtrlPresentacio cp) {
        this.cp = cp;
        initComponents();
        cargarProductos();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jLabelNom = new javax.swing.JLabel();
        jTextFieldNom = new javax.swing.JTextField();
        jScrollPaneSimilituds = new javax.swing.JScrollPane();
        jTableSimilituds = new javax.swing.JTable();
        jButtonGuardar = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelFondo.setPreferredSize(new java.awt.Dimension(540, 420));
        panelFondo.setLayout(null);

        jLabelNom.setText("Nom:");
        panelFondo.add(jLabelNom);
        jLabelNom.setBounds(30, 30, 50, 16);

        panelFondo.add(jTextFieldNom);
        jTextFieldNom.setBounds(110, 30, 220, 22);

        jTableSimilituds.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"Producte", "Similitud"}
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPaneSimilituds.setViewportView(jTableSimilituds);

        panelFondo.add(jScrollPaneSimilituds);
        jScrollPaneSimilituds.setBounds(10, 70, 380, 180);

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonGuardar);
        jButtonGuardar.setBounds(200, 270, 72, 23);

        jButtonBack.setText("Back");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonBack);
        jButtonBack.setBounds(310, 270, 72, 23);

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
    }// </editor-fold>                        

    
    private void cargarProductos() {
        ArrayList<String> productos = cp.getAllProductos();
        DefaultTableModel model = (DefaultTableModel) jTableSimilituds.getModel();
        model.setRowCount(0);

        for (String producto : productos) {
            model.addRow(new Object[]{producto, null}); 
        }
    }

    // Acción del botón "Guardar"
    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        String nomProducte = jTextFieldNom.getText().trim();
    
        
        if (nomProducte.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nom no pot estar buit.");
            return;
        }
    
        DefaultTableModel model = (DefaultTableModel) jTableSimilituds.getModel();
        HashMap<String, Integer> similituds = new HashMap<>();
    
       
        for (int i = 0; i < model.getRowCount(); i++) {
            String producteExist = (String) model.getValueAt(i, 0);
            Object valor = model.getValueAt(i, 1);
    
            if (valor == null || valor.toString().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Totes les similituds han de ser omplertes.");
                return;
            }
            
            try {
                
                int similitud;
                if (valor instanceof Double) {
                    similitud = ((Double) valor).intValue();
                } else {
                    try {
                        similitud = Integer.parseInt(valor.toString().trim());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Similitud no vàlida. Ha de ser un número enter.");
                        return;
                    }
                }
                similituds.put(producteExist, similitud);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Similitud no vàlida. Ha de ser un nombre enter.");
                return;
            }
        }
        try {
            cp.afegirProductePresentacio(nomProducte, similituds);
            JOptionPane.showMessageDialog(this, "Producte guardat correctament.");
            
            PantallaGestioProductes gProd = new PantallaGestioProductes(cp);
            gProd.setVisible(true);
            this.dispose();
        } catch (ProducteYaExisteix e) {
            JOptionPane.showMessageDialog(this, "Aquest producte ja existeix: " + e.getMessage());
        } catch (FaltaSimilitud e) {
            JOptionPane.showMessageDialog(this, "Falta una similitud: " + e.getMessage());
        } catch (SimilitudInvalida e) {
            JOptionPane.showMessageDialog(this, "Similitud invàlida: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperat: " + e.getMessage());
        }
    }

    // Acción del botón "Back"
    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {
        PantallaGestioProductes gProd = new PantallaGestioProductes(cp);
        gProd.setVisible(true);
        this.dispose();
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JScrollPane jScrollPaneSimilituds;
    private javax.swing.JTable jTableSimilituds;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JPanel panelFondo;
    // End of variables declaration                   
}
