package FONTS.presentacio;

import FONTS.excepcions.ProducteNoExisteix;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

public class PantallaPrestatgeria extends javax.swing.JFrame {
    
    private Prestatge prestatgeria;
    
    private CtrlPresentacio cp;
    private String id;
    private int similitud;
    private int files;
    private int columnes;
    private Vector<String> distribucio;
    private int mode;
    //mode1 = modificar, mode2 = distribucio, mode3 = visualitzar

     public PantallaPrestatgeria(CtrlPresentacio cp, String id, int mode, int f, int c, int similitud, Vector<String> distribucio) {
        this.id = id;
        this.mode = mode;
        this.files = f;
        this.columnes = c;
        this.cp = cp;
        this.distribucio = distribucio;
        this.similitud = similitud;
        initComponents();
        iniciarPrestatgeria();
        if (mode == 3) componentsVisualitzar();
        else if (mode == 2) componentsDistribuir();
    }
    
    public void iniciarPrestatgeria(){
        prestatgeria = new Prestatge(files, columnes, distribucio, mode);
        prestatgeria.setTxtAltura(324/files);
        prestatgeria.setTxtAncho(324/columnes);
        prestatgeria.setTxtMargen(4);
        prestatgeria.setTxtTamañoLetra(27);
        
        prestatgeria.setPanelBackground(new Color(89, 43, 25));
        
        prestatgeria.setTxtBackground1(Color.WHITE);
        prestatgeria.setTxtForeground1(Color.BLACK);
        prestatgeria.setTxtBackground2(new Color(232, 186, 186));
        prestatgeria.setTxtForeground2(Color.BLACK);
        
        panelFondo.add(prestatgeria);
        prestatgeria.setLocation(20,20);
        prestatgeria.setVisible(true);
        prestatgeria.crearPrestatgeria();
    }
    
    private void componentsVisualitzar() {
        for (var component : new java.awt.Component[]{ 
            jButtonGuardar, jButtonGuardarComo, jButtonIntercanviar, jButtonModificarMida, jLabelColumnes, jLabelFiles, jTextFieldColumnes, jTextFieldFiles}) {
            component.setVisible(false);
        }
    }

    private void componentsDistribuir() {
        for (var component : new java.awt.Component[]{ 
            jButtonGuardar, jButtonModificarMida, jButtonIntercanviar, jLabelColumnes, jLabelFiles, jTextFieldColumnes, jTextFieldFiles}) {
            component.setVisible(false);
        }
    }
     
    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) throws Exception {
        // Obtener el conjunto de IDs existentes
        Set<String> idsExistentes = cp.getIDprestatgeries();

        // Verificar si el ID ya está en el conjunto
        if (idsExistentes.contains(id)) {
            throw new Exception("El ID '" + id + "' ya existe. Por favor, elige otro.");
        }

        // Si no está repetido, procede a asignar y crear la prestatgeria
        this.id = id;
        System.out.println("ID actualizado en PantallaPrestatgeria: " + id);

        // Crear la prestatgeria
        cp.crearPrestatgeria(this.id, this.distribucio, this.files, this.columnes, this.similitud);
        System.out.println("Prestatgeria creada con ID: " + this.id);
    }
                        
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jLabelColumnes = new javax.swing.JLabel();
        jLabelFiles = new javax.swing.JLabel();
        jTextFieldColumnes = new javax.swing.JTextField();
        jTextFieldFiles = new javax.swing.JTextField();
        jButtonModificarMida = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonGuardarComo = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        jLabelTitolSimilitud = new javax.swing.JLabel();
        jTextFieldSimilitud = new javax.swing.JTextField();
        jButtonIntercanviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelFondo.setPreferredSize(new java.awt.Dimension(540, 420));
        panelFondo.setLayout(null);

        jLabelColumnes.setText("Columnes");
        panelFondo.add(jLabelColumnes);
        jLabelColumnes.setBounds(460, 40, 60, 16);

        jLabelFiles.setText("Files");
        panelFondo.add(jLabelFiles);
        jLabelFiles.setBounds(380, 40, 60, 16);
        panelFondo.add(jTextFieldColumnes);
        jTextFieldColumnes.setBounds(380, 60, 60, 22);
        panelFondo.add(jTextFieldFiles);
        jTextFieldFiles.setBounds(460, 60, 60, 22);

        jButtonModificarMida.setText("Modificar Mida");
        jButtonModificarMida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarMidaActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonModificarMida);
        jButtonModificarMida.setBounds(380, 90, 140, 23);

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButtonGuardarActionPerformed(evt);
                } catch (ProducteNoExisteix ex) {
                    Logger.getLogger(PantallaPrestatgeria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panelFondo.add(jButtonGuardar);
        jButtonGuardar.setBounds(380, 180, 140, 23);

        jButtonGuardarComo.setText("Guardar Como");
        jButtonGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButtonGuardarComoActionPerformed(evt);
                } catch (ProducteNoExisteix ex) {
                    Logger.getLogger(PantallaPrestatgeria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panelFondo.add(jButtonGuardarComo);
        jButtonGuardarComo.setBounds(380, 210, 140, 23);

        jButtonBack.setText("Back");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });
        panelFondo.add(jButtonBack);
        jButtonBack.setBounds(450, 380, 72, 23);

        jLabelTitolSimilitud.setText("Similitud:");
        panelFondo.add(jLabelTitolSimilitud);
        jLabelTitolSimilitud.setBounds(20, 380, 60, 20);

        panelFondo.add(jTextFieldSimilitud);
        jTextFieldSimilitud.setBounds(100, 380, 64, 22);

        jButtonIntercanviar.setText("Intercanviar");
        jButtonIntercanviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButtonIntercanviarActionPerformed(evt);
                } catch (ProducteNoExisteix ex) {
                    Logger.getLogger(PantallaPrestatgeria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panelFondo.add(jButtonIntercanviar);
        jButtonIntercanviar.setBounds(380, 120, 140, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        jTextFieldSimilitud.setText(String.valueOf(similitud));
        jTextFieldSimilitud.setEditable(false);

        pack();
    }// </editor-fold>                        
    
    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if (mode == 1 || mode == 3){
            PantallaGestioPrestatgeria gPres = new PantallaGestioPrestatgeria(cp);
            gPres.setVisible(true);
            this.dispose();
        }
        if (mode == 2){
            PantallaGestioDistribucio gDis = new PantallaGestioDistribucio(cp);
            gDis.setVisible(true);
            this.dispose();
        }
    }                                           
                                               
    private void jButtonGuardarComoActionPerformed(java.awt.event.ActionEvent evt) throws ProducteNoExisteix {                                                   
        PantallaNouIdPrestatgeria idP = new PantallaNouIdPrestatgeria(this);
        idP.setVisible(true);
        PantallaPrestatgeria pp = new PantallaPrestatgeria(this.cp, this.id, this.mode, this.files, this.columnes, this.similitud, this.distribucio);
        pp.setVisible(true);
        this.dispose();
    }   
    
    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) throws ProducteNoExisteix {                                                   
        cp.modificarMidaPrestatgeria(id, files, columnes);
        cp.modificarDistribucioPrestatgeria(id, distribucio);
    }
    
    private void jButtonModificarMidaActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        try {
            int f = Integer.parseInt(jTextFieldColumnes.getText());
            int c = Integer.parseInt(jTextFieldFiles.getText());
            
            if (f > 0 && c > 0 && f*c >= distribucio.size()){
                this.files = f;
                this.columnes = c;
                
                System.out.println("Files: " + files + ", Columnes: " + columnes);

                panelFondo.remove(prestatgeria);
                iniciarPrestatgeria();
                panelFondo.repaint();
                panelFondo.revalidate();
                
                javax.swing.JOptionPane.showMessageDialog(this, 
                "Mida modificada correctament!", 
                "Informació", 
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
            
            else {
                throw new NumberFormatException("Dimensions incorrectes per al nombre total de productes.");
            }
            
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
            "Error en la modificació de la mida!", 
            "Error", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
        }  
    } 

    private void jButtonIntercanviarActionPerformed(java.awt.event.ActionEvent evt) throws ProducteNoExisteix {                                                    
        System.out.println("INTERCANVIAR");
        
        try {
            ArrayList<JTextField> seleccionats = prestatgeria.getListaTxtSeleccionats();
            if (seleccionats.size() != 2) {
                System.out.println("no hi ha 2 productes seleccionats");
                javax.swing.JOptionPane.showMessageDialog(this, 
                "Selecciona exactament dos productes per intercanviar!", 
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            
            else{
                String prod0 = seleccionats.get(0).getText();
                String prod1 = seleccionats.get(1).getText();
                int n = distribucio.size();
                int index0 = n + 1;
                int index1 = n + 1;
                boolean trobats = false;

                for (int i = 0; i < n && !trobats; i++){
                    if (distribucio.get(i).equals(prod0)) index0 = i;
                    else if (distribucio.get(i).equals(prod1)) index1 = i;
                    if (index0 != n+1 && index1 != n+1) trobats = true;
                }

                if (trobats){
                    System.out.println("pos1: " + index0 + " pos2: " + index1);
                    String aux = distribucio.get(index0);
                    this.distribucio.set(index0, distribucio.get(index1));
                    this.distribucio.set(index1, aux);
                    panelFondo.remove(prestatgeria);
                    iniciarPrestatgeria();
                    panelFondo.repaint();
                    panelFondo.revalidate();
                    this.similitud = cp.calculaIndexSimilitud(distribucio);
                    jTextFieldSimilitud.setText(String.valueOf(similitud));
                }
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
            "Error en l'intercanvi!", 
            "Error", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }                                                   

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonGuardarComo;
    private javax.swing.JButton jButtonIntercanviar;
    private javax.swing.JButton jButtonModificarMida;
    private javax.swing.JLabel jLabelColumnes;
    private javax.swing.JLabel jLabelFiles;
    private javax.swing.JLabel jLabelTitolSimilitud;
    private javax.swing.JTextField jTextFieldColumnes;
    private javax.swing.JTextField jTextFieldFiles;
    private javax.swing.JTextField jTextFieldSimilitud;
    private javax.swing.JPanel panelFondo;
    // End of variables declaration                   
}