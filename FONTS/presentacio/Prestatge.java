package FONTS.presentacio;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Prestatge extends JPanel{
    
    private int files;
    private int columnes;
    private Vector<String> distribucio;
    private int mode;
    
    private JTextField[][] listaTxt;
    private int txtAncho;
    private int txtAltura;
    private int txtMargen;
    private int txtTamañoLetra;
    private Color panelBackground;
    private Color txtBackground1;
    private Color txtForeground1;
    private Color txtBackground2;
    private Color txtForeground2;
    private ArrayList<JTextField> listaTxtSeleccionats;
    
    public Prestatge(int files, int columnes, Vector<String> distribucio, int mode){
        this.files = files;
        this.columnes = columnes;
        this.distribucio = distribucio;
        this.mode = mode;
        iniciarComponentes();
    }
    
    public void iniciarComponentes(){
        listaTxt = new JTextField[files][columnes];
        txtAncho = 35;
        txtAltura = 35;
        txtMargen = 4;
        txtTamañoLetra = 27;
        panelBackground = Color.BLACK;
        txtBackground1 = Color.WHITE;
        txtForeground1 = Color.BLACK;
        txtBackground2 = Color.WHITE;
        txtForeground2 = Color.BLACK;
        listaTxtSeleccionats = new ArrayList<>();
    }
    
    public void crearPrestatgeria(){
        this.setLayout(null);
        this.setSize(txtAncho*columnes+8,files*(txtAltura+2)+8);
        this.setBackground(panelBackground);
        crearCamposTxt();
    }
    
    public void crearCamposTxt(){
        int x = txtMargen;
        int y = txtMargen;
        int it = 0;
        
        for (int i = 0; i < files; i++){
            for (int j = 0; j < columnes; j++){
                JTextField txt = new JTextField();
                this.add(txt);
                txt.setBounds(x, y, txtAncho, txtAltura);
                txt.setBackground(txtBackground1);
                txt.setForeground(txtForeground1);
                txt.setFont(new Font("Montserrat",Font.BOLD,txtTamañoLetra));
                txt.setEditable(false);
                txt.setCursor(new Cursor(Cursor.HAND_CURSOR));
                if (it < distribucio.size()){
                    txt.setText(distribucio.get(it));
                    it++;
                }
                else{
                    txt.setForeground(txtBackground1);
                    txt.setText("-");
                }
                                
                txt.setVisible(true);
                
                listaTxt[i][j] = txt;
                generarEventos(txt);
                
                if (i%2 == 0) x += txtAncho;
                else x -= txtAncho;
            }
            y += txtAltura + 2;
            if (i%2 == 0) x -= txtAncho;
            else x += txtAncho;
        }
    }

    public void generarEventos(JTextField txt){
        MouseListener event = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed(txt);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        };
        
        txt.addMouseListener(event);
    }
    
    public void pressed(JTextField txt){
        if ("-".equals(txt.getText())) {
            return;
        }
        
        if (mode != 1 || listaTxtSeleccionats.size() == 2){
            JTextField jtxt = listaTxtSeleccionats.get(0);
            jtxt.setBackground(txtBackground1);
            jtxt.setForeground(txtForeground1);
            listaTxtSeleccionats.remove(0);
        }
        
        int midax = listaTxt.length;
        int miday = listaTxt[0].length;
        
        for (int i = 0; i < midax; i++){
            for (int j = 0; j < miday; j++){
                if (listaTxt[i][j] == txt){
                    listaTxt[i][j].setBackground(txtBackground2);
                    listaTxt[i][j].setForeground(txtForeground2);
                    listaTxtSeleccionats.add(listaTxt[i][j]);
                    return;
                }
            }
        }
    }
    
    public int getFiles() {
        return files;
    }

    public void setFiles(int files) {
        this.files = files;
    }

    public int getColumnes() {
        return columnes;
    }

    public void setColumnes(int columnes) {
        this.columnes = columnes;
    }

    public JTextField[][] getListaTxt() {
        return listaTxt;
    }

    public void setListaTxt(JTextField[][] listaTxt) {
        this.listaTxt = listaTxt;
    }

    public int getTxtAncho() {
        return txtAncho;
    }

    public void setTxtAncho(int txtAncho) {
        this.txtAncho = txtAncho;
    }

    public int getTxtAltura() {
        return txtAltura;
    }

    public void setTxtAltura(int txtAltura) {
        this.txtAltura = txtAltura;
    }

    public int getTxtMargen() {
        return txtMargen;
    }

    public void setTxtMargen(int txtMargen) {
        this.txtMargen = txtMargen;
    }

    public int getTxtTamañoLetra() {
        return txtTamañoLetra;
    }

    public void setTxtTamañoLetra(int txtTamañoLetra) {
        this.txtTamañoLetra = txtTamañoLetra;
    }

    public Color getPanelBackground() {
        return panelBackground;
    }

    public void setPanelBackground(Color panelBackground) {
        this.panelBackground = panelBackground;
    }

    public Color getTxtBackground1() {
        return txtBackground1;
    }

    public void setTxtBackground1(Color txtBackground1) {
        this.txtBackground1 = txtBackground1;
    }

    public Color getTxtForeground1() {
        return txtForeground1;
    }

    public void setTxtForeground1(Color txtForeground1) {
        this.txtForeground1 = txtForeground1;
    }

    public Color getTxtBackground2() {
        return txtBackground2;
    }

    public void setTxtBackground2(Color txtBackground2) {
        this.txtBackground2 = txtBackground2;
    }

    public Color getTxtForeground2() {
        return txtForeground2;
    }

    public void setTxtForeground2(Color txtForeground2) {
        this.txtForeground2 = txtForeground2;
    }
    
    public Vector<String> getDistribucio() {
        return distribucio;
    }

    public void setDistribucio(Vector<String> distribucio) {
        this.distribucio = distribucio;
    }

    public ArrayList<JTextField> getListaTxtSeleccionats() {
        return listaTxtSeleccionats;
    }

    public void setListaTxtSeleccionats(ArrayList<JTextField> listaTxtSeleccionats) {
        this.listaTxtSeleccionats = listaTxtSeleccionats;
    }
}
