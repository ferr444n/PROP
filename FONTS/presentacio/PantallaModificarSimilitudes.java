package FONTS.presentacio;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import FONTS.domini.Producto;
import FONTS.excepcions.ProducteNoExisteix;

public class PantallaModificarSimilitudes extends javax.swing.JFrame {

    private CtrlPresentacio cp;
    private String productoBase;

    public PantallaModificarSimilitudes(CtrlPresentacio cp, String productoBase) {
        this.cp = cp;
        this.productoBase = productoBase;
        initComponents();
        cargarSimilitudes();
    }

    private void cargarSimilitudes() {
        DefaultTableModel model = (DefaultTableModel) jTableSimilitudes.getModel();
        model.setRowCount(0);
    
        try {
            Map<String, Integer> similitudesMap = cp.getSimilitudesProducto(productoBase);
            for (Map.Entry<String, Integer> entry : similitudesMap.entrySet()) {
                String nombreProducto = entry.getKey();
                Integer similitud = entry.getValue();
                model.addRow(new Object[]{nombreProducto, similitud});
            }
        } catch (ProducteNoExisteix e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las similitudes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void initComponents() {

        jScrollPaneSimilitudes = new javax.swing.JScrollPane();
        jTableSimilitudes = new javax.swing.JTable();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setPreferredSize(new java.awt.Dimension(400, 300));
        setResizable(false);

        jTableSimilitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Producto", "Similitud"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneSimilitudes.setViewportView(jTableSimilitudes);

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneSimilitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCancelar))
                    .addComponent(jScrollPaneSimilitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) jTableSimilitudes.getModel();
    
        for (int i = 0; i < model.getRowCount(); i++) {
            String nombreProducto = (String) model.getValueAt(i, 0);
            Object similitudObject = model.getValueAt(i, 1);
    
            try {
                Integer similitudEntera;
                if (similitudObject instanceof Integer) {
                    similitudEntera = (Integer) similitudObject;
                } else if (similitudObject instanceof Double) {
                    similitudEntera = ((Double) similitudObject).intValue();
                } else {
                    throw new IllegalArgumentException("El valor de similitud no es válido.");
                }
                cp.modificarSimilitudEntreProductosPresentacio(productoBase, nombreProducto, similitudEntera);
            } catch (ProducteNoExisteix ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar similitud: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido para similitud en la fila " + i + ": " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(this, "Producto modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        PantallaGestioProductes gProd = new PantallaGestioProductes(cp);
        gProd.setVisible(true);
        this.dispose();
    }

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        PantallaSeleccionProducto modificar = new PantallaSeleccionProducto(cp);
        modificar.setVisible(true);
        this.dispose();
    }

    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JScrollPane jScrollPaneSimilitudes;
    private javax.swing.JTable jTableSimilitudes;
}
