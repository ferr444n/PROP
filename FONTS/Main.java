package FONTS;

import FONTS.presentacio.CtrlPresentacio;

public class Main {
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
		CtrlPresentacio mainWindow = new CtrlPresentacio();
		mainWindow.inicializarPresentacio();
            }
	});
    }
}