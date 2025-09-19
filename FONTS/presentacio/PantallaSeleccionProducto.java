package FONTS.presentacio;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PantallaSeleccionProducto extends javax.swing.JFrame {

    private CtrlPresentacio cp;

    public PantallaSeleccionProducto(CtrlPresentacio cp) {
        this.cp = cp;
        initComponents();
        cargarProductos();
    }

    private void cargarProductos() {
        DefaultTableModel model = (DefaultTableModel) jTableProductos.getModel();
        model.setRowCount(0);
        ArrayList<String> productos = cp.getAllProductos();
        // Añadir cada producto como una fila en la tabla
        for (String producto : productos) {
            model.addRow(new Object[]{producto});
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jButtonBack = new javax.swing.JButton();
        jScrollPaneProductos = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();
        jButtonSeleccionar = new javax.swing.JButton();

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

        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Productos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPaneProductos.setViewportView(jTableProductos);
        panelFondo.add(jScrollPaneProductos);
        jScrollPaneProductos.setBounds(10, 10, 200, 280);

        jButtonSeleccionar.setText("Seleccionar");
        jButtonSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonSeleccionar);
        jButtonSeleccionar.setBounds(220, 50, 170, 23);

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

    private void jButtonSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTableProductos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String productoSeleccionado = (String) jTableProductos.getValueAt(selectedRow, 0);
            PantallaModificarSimilitudes pantallaModificar = new PantallaModificarSimilitudes(cp, productoSeleccionado);
            pantallaModificar.setVisible(true);
            this.dispose();
        }
    }

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {
        // Volver a PantallaGestioProductes
        PantallaGestioProductes pantallaGestio = new PantallaGestioProductes(cp);
        pantallaGestio.setVisible(true);
        this.dispose();
    }

    private javax.swing.JPanel panelFondo;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JScrollPane jScrollPaneProductos;
    private javax.swing.JTable jTableProductos;
    private javax.swing.JButton jButtonSeleccionar;
}
