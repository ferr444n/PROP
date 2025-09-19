package FONTS.domini;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class GestioPrestatgeries {
    
    private Map<String,Prestatgeria> prestatgeries;
    
    public GestioPrestatgeries() {
        this.prestatgeries = new HashMap<>();
    }

    public GestioPrestatgeries(Map<String, Prestatgeria> prestatgeries) {
        this.prestatgeries = prestatgeries;
    }

    public Map<String, Prestatgeria> getPrestatgeries() {
        return prestatgeries;
    }

    public void setPrestatgeries(Map<String, Prestatgeria> prestatgeries) {
        this.prestatgeries = prestatgeries;
    }

    public Set<String> getAllIdPrestatgeria(){
        Set<String> ids = prestatgeries.keySet();
        return ids;
    }
    
    public Prestatgeria getPrestatgeria(String id){
        return prestatgeries.get(id);
    }

    public int getSimilitudPrestatgeria(String id){
        return prestatgeries.get(id).getIndexSimilitut();
    }

    public int getColumnes(String id){
        Prestatgeria p = prestatgeries.get(id);
        return p.getColumnes();
    }

    public int getFiles(String id){
        Prestatgeria p = prestatgeries.get(id);
        return p.getFiles();
    }
    
    public Vector<String> getDistribucio(String id){
        Vector<String> v = new Vector<>();
        Prestatgeria p = prestatgeries.get(id);

        if (p == null) {
            throw new IllegalArgumentException("No s'ha trobat cap prestatgeria amb l'id especificat: " + id);
        }

        for (Producto prod : p.getDistribucio()){
            v.add(prod.getNombre());
        }
        return v;
    }
    public Vector<Producto> getDistribucioP(String id){
        Prestatgeria p = prestatgeries.get(id);
        return p.getDistribucio();
    }

    public void eliminarPrestatgeria(String id){
        prestatgeries.remove(id);
    }

    public boolean afegirPrestatgeria(String id, Vector<Producto> productes, int files, int columnes, int similitut){
        if (prestatgeries.containsKey(id)) return false;

        Prestatgeria p = new Prestatgeria(id, productes, files, columnes, similitut);
        prestatgeries.put(id, p);
        return true;
    }

    public void canviarIdPrestatgeria(String idAntic, String idNou){
        Prestatgeria p = prestatgeries.get(idAntic);
        p.setId(idNou);
        prestatgeries.remove(idAntic);
        prestatgeries.put(idNou, p);
    }

    public void modificarMidaPrestatgeria(String id, int files, int columnes){
        Prestatgeria p = prestatgeries.get(id);
        p.modificarMida(files,columnes);
    }

    public void actualitzarSimilitud(String n1, String n2) {
        for (Prestatgeria p: prestatgeries.values()) {
            if(p.conteProd(n1) && p.conteProd(n2)) {
                int aux = p.calculaIndexSimilitud();
            }
        }
    }   
}
