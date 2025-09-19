package FONTS.presentacio;

import FONTS.excepcions.ProducteNoExisteix;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PantallaGestioDistribucio extends javax.swing.JFrame {

    private CtrlPresentacio cp;
    private int nProd;
    private ArrayList<String> productos;
    private String algorismeSeleccionat;
    
    public PantallaGestioDistribucio(CtrlPresentacio cp) {
        this.cp = cp;
        this.algorismeSeleccionat = "";
        initComponents();
    }
    
    public int getSimilitud(Vector<String> distribucio) throws ProducteNoExisteix{
        return cp.calculaIndexSimilitud(distribucio);
    }
    
    public void setProductos(ArrayList<String> productos) {
        this.productos = productos;
        this.nProd = productos.size();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jButtonBack = new javax.swing.JButton();
        jLabelProdADis = new javax.swing.JLabel();
        jButtonTots = new javax.swing.JButton();
        jButtonSeleccionar = new javax.swing.JButton();
        jLabelProdAlgor = new javax.swing.JLabel();
        jButtonAFB = new javax.swing.JButton();
        jButtonAAprox = new javax.swing.JButton();
        jLabelProdMida = new javax.swing.JLabel();
        jLabelColumnes = new javax.swing.JLabel();
        jLabelFiles = new javax.swing.JLabel();
        jTextFieldColumnes = new javax.swing.JTextField();
        jTextFieldFiles = new javax.swing.JTextField();
        jButtonCrear = new javax.swing.JButton();

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

        jLabelProdADis.setText("Productes a distribuir:");
        panelFondo.add(jLabelProdADis);
        jLabelProdADis.setBounds(30, 20, 330, 16);

        jButtonTots.setText("Tots");
        jButtonTots.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTotsActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonTots);
        jButtonTots.setBounds(90, 50, 80, 20);

        jButtonSeleccionar.setText("Seleccionar");
        jButtonSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonSeleccionar);
        jButtonSeleccionar.setBounds(230, 50, 100, 23);

        jLabelProdAlgor.setText("Algorisme:");
        panelFondo.add(jLabelProdAlgor);
        jLabelProdAlgor.setBounds(30, 90, 330, 16);

        jButtonAFB.setText("Força Bruta");
        jButtonAFB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAFBActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonAFB);
        jButtonAFB.setBounds(60, 120, 120, 23);

        jButtonAAprox.setText("Aproximació");
        jButtonAAprox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAAproxActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonAAprox);
        jButtonAAprox.setBounds(220, 120, 120, 23);

        jLabelProdMida.setText("Mida:");
        panelFondo.add(jLabelProdMida);
        jLabelProdMida.setBounds(30, 160, 330, 16);

        jLabelColumnes.setText("Files");
        panelFondo.add(jLabelColumnes);
        jLabelColumnes.setBounds(60, 190, 40, 16);

        jLabelFiles.setText("Columnes");
        panelFondo.add(jLabelFiles);
        jLabelFiles.setBounds(200, 190, 70, 16);

        panelFondo.add(jTextFieldColumnes);
        jTextFieldColumnes.setBounds(100, 190, 50, 22);

        panelFondo.add(jTextFieldFiles);
        jTextFieldFiles.setBounds(280, 190, 50, 22);

        jButtonCrear.setText("Crear Prestatgeria");
        jButtonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonCrear);
        jButtonCrear.setBounds(130, 240, 130, 23);

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

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        PantallaInici ini = new PantallaInici(cp);
        ini.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonTotsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTotsActionPerformed
        productos = cp.getAllProductos();
        nProd = cp.getNProd();
    }//GEN-LAST:event_jButtonTotsActionPerformed

    private void jButtonSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarActionPerformed
        PantallaSeleccionar ps = new PantallaSeleccionar(cp, this);       
        ps.setVisible(true);
    }//GEN-LAST:event_jButtonSeleccionarActionPerformed

    private void jButtonAFBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAFBActionPerformed
        algorismeSeleccionat = "Fuerzabruta";
    }//GEN-LAST:event_jButtonAFBActionPerformed

    private void jButtonAAproxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAAproxActionPerformed
        algorismeSeleccionat = "Aproximacion";
    }//GEN-LAST:event_jButtonAAproxActionPerformed

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        
        try {
            if (productos == null || productos.isEmpty()) {
                throw new IllegalArgumentException("No hi ha productes seleccionats. Selecciona almenys un producte abans de continuar.");
            }

            int f = Integer.parseInt(jTextFieldColumnes.getText());
            int c = Integer.parseInt(jTextFieldFiles.getText());

            if (productos == null || productos.isEmpty()) {
            throw new IllegalArgumentException("No hi ha productes seleccionats. Selecciona almenys un producte abans de continuar.");
        }
            if (f * c < nProd) throw new IllegalArgumentException("El nombre total de caselles ha de ser igual al de productes escollits. Numero de casellas requerit: " + nProd);
            if (algorismeSeleccionat == "") throw new IllegalArgumentException("S'ha de seleccionar un tipus d'algorisme");
            
            
            Vector<String> distribucio = cp.calculaDistribucio(algorismeSeleccionat, productos, f, c);
            
            int similitud = getSimilitud(distribucio);
        
            
            PantallaPrestatgeria pres = new PantallaPrestatgeria(cp, "", 2, f, c, similitud, distribucio);
            pres.setVisible(true);
            this.dispose();
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
            "Introdueix nombres vàlids!", 
            "Error", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            e.getMessage(), 
            "Error", 
            javax.swing.JOptionPane.WARNING_MESSAGE);
        } catch (ProducteNoExisteix ex) {
            Logger.getLogger(PantallaGestioDistribucio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonCrearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAAprox;
    private javax.swing.JButton jButtonAFB;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JButton jButtonSeleccionar;
    private javax.swing.JButton jButtonTots;
    private javax.swing.JLabel jLabelColumnes;
    private javax.swing.JLabel jLabelFiles;
    private javax.swing.JLabel jLabelProdADis;
    private javax.swing.JLabel jLabelProdAlgor;
    private javax.swing.JLabel jLabelProdMida;
    private javax.swing.JTextField jTextFieldColumnes;
    private javax.swing.JTextField jTextFieldFiles;
    private javax.swing.JPanel panelFondo;
    // End of variables declaration//GEN-END:variables
}
