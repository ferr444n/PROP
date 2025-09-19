package FONTS.presentacio;

public class PantallaNouIdPrestatgeria extends javax.swing.JFrame {
    
    private PantallaPrestatgeria pp;

    public PantallaNouIdPrestatgeria(PantallaPrestatgeria pp) {
        this.pp = pp;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFondo = new javax.swing.JPanel();
        jButtonGuardar = new javax.swing.JButton();
        jLabelId = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelFondo.setPreferredSize(new java.awt.Dimension(540, 420));
        jPanelFondo.setLayout(null);

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        jPanelFondo.add(jButtonGuardar);
        jButtonGuardar.setBounds(140, 260, 140, 23);

        jLabelId.setText("Nou Id Prestatgeria");
        jPanelFondo.add(jLabelId);
        jLabelId.setBounds(70, 130, 120, 16);
        jPanelFondo.add(jTextFieldId);
        jTextFieldId.setBounds(190, 130, 130, 22);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        String id = jTextFieldId.getText().trim();
        if (id.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "El camp ID està buit.", "Error", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        try {
            pp.setId(id); // Manejo de excepciones
            this.dispose(); // Cierra la ventana
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error al crear la prestatgeria: " + e.getMessage(), 
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JTextField jTextFieldId;
    // End of variables declaration//GEN-END:variables
}
