package FONTS.persistencia;

import FONTS.persistencia.PersistenciaGestionPrestatgeries;
import FONTS.persistencia.PersistenciaGestionProductos;
import FONTS.domini.GestioPrestatgeries;
import FONTS.domini.GestionProductos;
import java.io.IOException;

/*
 * Controlador de Persistencia para gestionar la persistencia de productos y prestatgeries.
 */
public class CtrlPersistencia {

    private final PersistenciaGestionPrestatgeries persistenciaPrestatgeries;

    
    private final PersistenciaGestionProductos persistenciaGestionProductos;

    public CtrlPersistencia() {
        persistenciaPrestatgeries = new PersistenciaGestionPrestatgeries();
        persistenciaGestionProductos = new PersistenciaGestionProductos();
    }

    /*
     * Guarda las prestatgeries en un archivo JSON.
     */
    public void guardaPrestatgeries(GestioPrestatgeries gestioPrestatgeries) {
        persistenciaPrestatgeries.guardarPrestatgeries(gestioPrestatgeries);
    }

    /*
     * Carga las prestatgeries desde un archivo JSON especificado por un path.
     */
    public GestioPrestatgeries carregaPrestatgeries(String path) {
        return persistenciaPrestatgeries.cargarPrestatgeries(path);
    }

    /*
     * Guarda la gestión de productos en un archivo JSON.
     */
    public void guardaGestionProductos(GestionProductos gestionProductos, String... customPath) throws IOException {
        persistenciaGestionProductos.guardarGestionProductos(gestionProductos, customPath);
    }


    /*
     * Carga la gestión de productos desde un archivo JSON especificado por un path.
     */
    public GestionProductos carregaGestionProductos(String path) throws IOException {
        return persistenciaGestionProductos.cargarGestionProductos(path);
    }
}
