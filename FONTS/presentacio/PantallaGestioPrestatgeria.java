package FONTS.presentacio;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.util.*;

public class PantallaGestioPrestatgeria extends javax.swing.JFrame {

    private CtrlPresentacio cp;
    
    public PantallaGestioPrestatgeria(CtrlPresentacio cp) {
        this.cp = cp;
        initComponents();
        initTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jButtonBack = new javax.swing.JButton();
        jScrollPanePrestatgeries = new javax.swing.JScrollPane();
        jTablePrestatgeries = new javax.swing.JTable();
        jButtonVisualitzar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonCarregar = new javax.swing.JButton(); // Nuevo botón
        jButtonGuardar = new javax.swing.JButton();  // Nuevo botón
    
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
    
        jTablePrestatgeries.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "id Prestatgeria", "Similitud"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };
    
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
    
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPanePrestatgeries.setViewportView(jTablePrestatgeries);
    
        panelFondo.add(jScrollPanePrestatgeries);
        jScrollPanePrestatgeries.setBounds(10, 10, 200, 280);
    
        jButtonVisualitzar.setText("Visualitzar");
        jButtonVisualitzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisualitzarActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonVisualitzar);
        jButtonVisualitzar.setBounds(220, 40, 170, 23);
    
        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonModificar);
        jButtonModificar.setBounds(220, 80, 170, 23);
    
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonEliminar);
        jButtonEliminar.setBounds(220, 120, 170, 23);
    
        // Configuración del nuevo botón Guardar
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonGuardar);
        jButtonGuardar.setBounds(220, 160, 170, 23); // Posicionado debajo de Eliminar
    
        // Configuración del nuevo botón Cargar
        jButtonCarregar.setText("Carregar");
        jButtonCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCarregarActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonCarregar);
        jButtonCarregar.setBounds(220, 200, 170, 23); // Posicionado debajo de Guardar
    
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
    }
    
    // Acción para el botón Guardar
    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            cp.guardarPrestatgeries(); // Método para guardar prestatgeries en CtrlPresentacio
            JOptionPane.showMessageDialog(this, "Prestatgeries guardades correctament.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar les prestatgeries: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Acción para el botón Carregar
    private void jButtonCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCarregarActionPerformed
        // Mostrar advertencia al usuario
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Estas segur que vols carregar els productes? S'eliminaran els actuals.",
            "Advertència",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
    
        // Si el usuario confirma
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Cargar prestatgeria y productos
                cp.carregarPrestatgeries(); // Método que carga los productos
                JOptionPane.showMessageDialog(
                    this,
                    "Prestatgeria i productes carregats correctament.",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE
                );
                initTabla();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                    this,
                    "Error al carregar la prestatgeria i productes: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } else {
            // Acción cancelada
            JOptionPane.showMessageDialog(
                this,
                "Carregar prestatgeria i productes cancel·lat.",
                "Info",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
        
    }//GEN-LAST:event_jButtonCarregarActionPerformed
    

    private void initTabla() {
        // Obtener los IDs y similitudes desde CtrlPresentacio
        Set<String> ids = cp.getIDprestatgeries();
        DefaultTableModel modeloTabla = (DefaultTableModel) jTablePrestatgeries.getModel();

        // Limpiar cualquier dato previo
        modeloTabla.setRowCount(0);

        // Añadir filas con los datos
        for (String id : ids) {
            int similitud = cp.getSimilitud(id);
            modeloTabla.addRow(new Object[]{id, similitud});
        }
    }
    

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        
        PantallaInici ini = new PantallaInici(cp);
        ini.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonVisualitzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisualitzarActionPerformed
        int filaSeleccionada = jTablePrestatgeries.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una prestatgeria per visualitzar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object idValue = jTablePrestatgeries.getValueAt(filaSeleccionada, 0); // Columna ID
        Object similitudValue = jTablePrestatgeries.getValueAt(filaSeleccionada, 1); // Columna Similitud
        if (idValue == null && similitudValue == null) JOptionPane.showMessageDialog(this, "No existeix la prestatgeria","Error", JOptionPane.ERROR_MESSAGE);
        else {
            int f, c;
            f = cp.getFiles((String)idValue);
            c = cp.getColumnes((String)idValue);
            Vector<String> distri = cp.getDistribucio((String)idValue);
            PantallaPrestatgeria pres = new PantallaPrestatgeria(cp, (String)idValue, 3,f, c, (int)similitudValue, distri);
            pres.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonVisualitzarActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        int filaSeleccionada = jTablePrestatgeries.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una prestatgeria per modificar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object idValue = jTablePrestatgeries.getValueAt(filaSeleccionada, 0); // Columna ID
        Object similitudValue = jTablePrestatgeries.getValueAt(filaSeleccionada, 1); // Columna Similitud
        if (idValue == null && similitudValue == null) JOptionPane.showMessageDialog(this, "No existeix la prestatgeria","Error", JOptionPane.ERROR_MESSAGE);
        else {
            int f, c;
            f = cp.getFiles((String)idValue);
            c = cp.getColumnes((String)idValue);
            Vector<String> distri = cp.getDistribucio((String)idValue);
            PantallaPrestatgeria pres = new PantallaPrestatgeria(cp, (String)idValue, 1, f, c, (int)similitudValue, distri);
            pres.setVisible(true);
            this.dispose();
            initTabla();
        }
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        int filaSeleccionada = jTablePrestatgeries.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una prestatgeria per eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        Object idValue = jTablePrestatgeries.getValueAt(filaSeleccionada, 0); // Columna ID
        if (idValue == null) {
            JOptionPane.showMessageDialog(this, "No existeix la prestatgeria", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Personalizar los botones del cuadro de confirmación
        Object[] opciones = {"Sí", "No"};
        int confirm = JOptionPane.showOptionDialog(
            this,
            "Estas segur que vols eliminar la prestatgeria amb id \"" + idValue + "\"?",
            "Confirmació",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            opciones,
            opciones[1]
        );
    
        // Si el usuario selecciona "Sí"
        if (confirm == JOptionPane.YES_OPTION) {
            cp.eliminarPrestatgeria((String) idValue);
            JOptionPane.showMessageDialog(this, "Prestatgeria eliminada");
            initTabla(); // Actualiza la tabla después de eliminar
        } else {
            // Mensaje opcional si se cancela la acción
            JOptionPane.showMessageDialog(this, "Acció cancel·lada", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonVisualitzar;
    private javax.swing.JButton jButtonCarregar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JScrollPane jScrollPanePrestatgeries;
    private javax.swing.JTable jTablePrestatgeries;
    private javax.swing.JPanel panelFondo;
    // End of variables declaration//GEN-END:variables

}
