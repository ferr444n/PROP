package FONTS.presentacio;

public class PantallaInici extends javax.swing.JFrame {

    private CtrlPresentacio cp;
    
    public PantallaInici(CtrlPresentacio cp) {
        this.cp = cp;
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jButtonProductes = new javax.swing.JButton();
        jButtonPrestatgeria = new javax.swing.JButton();
        jButtonDistribucio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));
        setPreferredSize(new java.awt.Dimension(400, 300));
        setResizable(false);

        panelFondo.setBackground(new java.awt.Color(204, 204, 255));
        panelFondo.setPreferredSize(null);
        panelFondo.setLayout(null);

        jButtonProductes.setText("Productes");
        jButtonProductes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProductesActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonProductes);
        jButtonProductes.setBounds(120, 90, 150, 23);

        jButtonPrestatgeria.setText("Prestatgeries");
        jButtonPrestatgeria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrestatgeriaActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonPrestatgeria);
        jButtonPrestatgeria.setBounds(120, 140, 150, 23);

        jButtonDistribucio.setText("Crear Distrubució");
        jButtonDistribucio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDistribucioActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonDistribucio);
        jButtonDistribucio.setBounds(120, 190, 150, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDistribucioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDistribucioActionPerformed

        PantallaGestioDistribucio gDis = new PantallaGestioDistribucio(cp);
        gDis.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonDistribucioActionPerformed

    private void jButtonPrestatgeriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrestatgeriaActionPerformed

        PantallaGestioPrestatgeria gPres = new PantallaGestioPrestatgeria(cp);
        gPres.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonPrestatgeriaActionPerformed

    private void jButtonProductesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProductesActionPerformed

        PantallaGestioProductes gProd = new PantallaGestioProductes(cp);
        gProd.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonProductesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDistribucio;
    private javax.swing.JButton jButtonPrestatgeria;
    private javax.swing.JButton jButtonProductes;
    private javax.swing.JPanel panelFondo;
    // End of variables declaration//GEN-END:variables
}
