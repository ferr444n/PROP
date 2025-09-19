package FONTS.presentacio;

import FONTS.domini.CtrlDominio;
import FONTS.excepcions.*;


import java.io.IOException;
import java.util.*;


public class CtrlPresentacio {
    
    private CtrlDominio cd;
    private PantallaInici pi;
    
    public CtrlPresentacio(){
        cd = new CtrlDominio();
        pi = new PantallaInici(this);
    }
    
    public void inicializarPresentacio(){
        pi.setVisible(true);
    }
    
    public int getNProd(){
        return cd.getNumProd();
    }
    
    public ArrayList<String> getAllProductos(){
        return cd.getAllProductosNombres();
    }

    public Map<String,Integer> getSimilitudesProducto(String nombreProducto) throws ProducteNoExisteix{
        return cd.getSimilitudesProducto(nombreProducto);
    }

    public void afegirProductePresentacio(String nombreProducto, HashMap<String, Integer> similitudes) throws ProducteYaExisteix, FaltaSimilitud, SimilitudInvalida {
        cd.afegirProducte(nombreProducto, similitudes);
    }

    public void modificarSimilitudEntreProductosPresentacio(String n1, String n2, Integer valor) throws ProducteNoExisteix {
        cd.modificarSimilitudEntreProductosControl(n1, n2, valor);
    }

    public void eliminarProductoPresentacio(String nombreProducto) throws ProducteNoExisteix {
        cd.eliminarProducto(nombreProducto);
    }

    public void setProductosPresentacio(List<Map.Entry<String, HashMap<String, Integer>>> nombresConSimilitudes) throws FaltaSimilitud, ProducteYaExisteix, SimilitudInvalida {
    cd.setProductos(nombresConSimilitudes);
    }

    public Vector<String> calculaDistribucio(String algoritmo, ArrayList<String> productos, int files, int columnes) throws ProducteNoExisteix{
        Vector<String> v = cd.calcularAlgoritmo(algoritmo, productos, files, columnes);
        return v;
     }
    
    public void crearPrestatgeria(String id, Vector<String> distribucio, int files, int columnes, int indexSimilitud) throws ProducteNoExisteix{
        cd.crearPrestatgeria(id,distribucio,files,columnes,indexSimilitud);
    }
    
    public Vector<String> getDistribucio(String id){
        return cd.getDistribucio(id);
    }

    public void eliminarPrestatgeria(String id){
        cd.eliminarPrestatgeria(id);
    }

    public void modificarMidaPrestatgeria(String id, int files, int columnes){
        cd.modificarMidaPrestatgeria(id, files, columnes);
    }

    public int modificarDistribucioPrestatgeria(String id, Vector<String> distribucio) throws ProducteNoExisteix{
        return cd.modificarDistribucioPrestatgeria(id, distribucio);
    }

    public Set<String> getIDprestatgeries() {
        return cd.getIDPrestatgeries();
    }

    public int getSimilitud(String id) {
        return cd.getSimilitud(id);
    }

    public int getColumnes(String id) {
        return cd.getColumnes(id);
    }
    
    public int getFiles(String id) {
        return cd.getFiles(id);
    }

    public int calculaIndexSimilitud(Vector<String> distribucio) throws ProducteNoExisteix{
        return cd.calculaIndexSimilitud(distribucio);
    }

    public void guardarPrestatgeries() throws IOException{
        cd.guardarPrestatgeries();
        cd.guardarProductos();
    }

    public void carregarPrestatgeries() throws IOException{
        cd.cargarPrestatgeries("DATA/prestatgeriesGuardadas.json");
        cd.cargarProductos("DATA/productosGuardados.json");
    }
}