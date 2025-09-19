package FONTS.presentacio;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PantallaSeleccionar extends javax.swing.JFrame {

    private CtrlPresentacio cp;
    private PantallaGestioDistribucio pantallaGestio;

    public PantallaSeleccionar(CtrlPresentacio cp, PantallaGestioDistribucio pantallaGestio) {
        this.cp = cp;
        this.pantallaGestio = pantallaGestio;
        initComponents();
        cargarProductos();
    }

    private void cargarProductos() {
        DefaultTableModel model = (DefaultTableModel) jTableProductos.getModel();
        model.setRowCount(0);
        ArrayList<String> productos = cp.getAllProductos();
        for (String producto : productos) {
            model.addRow(new Object[]{false, producto});
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jButtonBack = new javax.swing.JButton();
        jScrollPaneProductos = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();
        jButtonConfirmar = new javax.swing.JButton();

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
        jButtonBack.setBounds(290, 270, 100, 30);

        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Seleccionar", "Productos"
            }
        ) {
            Class[] types = new Class[] {
                Boolean.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPaneProductos.setViewportView(jTableProductos);
        panelFondo.add(jScrollPaneProductos);
        jScrollPaneProductos.setBounds(10, 10, 380, 250);

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonConfirmar);
        jButtonConfirmar.setBounds(10, 270, 100, 30); // Ahora está a la izquierda

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );

        pack();
    }

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<String> productosSeleccionados = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) jTableProductos.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            Boolean seleccionado = (Boolean) model.getValueAt(i, 0);
            if (seleccionado != null && seleccionado) {
                productosSeleccionados.add((String) model.getValueAt(i, 1));
            }
        }

        if (productosSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona al menos un producto.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            pantallaGestio.setProductos(productosSeleccionados);
            JOptionPane.showMessageDialog(this, "Productos seleccionados correctamente.");
            this.dispose();
        }
    }

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private javax.swing.JPanel panelFondo;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JScrollPane jScrollPaneProductos;
    private javax.swing.JTable jTableProductos;
    private javax.swing.JButton jButtonConfirmar;
}
