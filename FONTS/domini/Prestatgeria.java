package FONTS.domini;

import java.util.*;

public class Prestatgeria {
    
    private String id;
    private Vector<Producto> distribucio;
    private Integer files;
    private Integer columnes;
    private Integer indexSimilitut;

    public Prestatgeria(String id, Vector<Producto> distribucio, Integer files, Integer columnes, Integer indexSimilitut) {
        this.id = id;
        this.distribucio = distribucio;
        this.files = files;
        this.columnes = columnes;
        this.indexSimilitut = indexSimilitut;
    }

    public void modificarMida(Integer files, Integer columnes){
        this.files = files;
        this.columnes = columnes;
    }

    public Integer intercanvia(int pos1, int pos2){
        if (pos1 < 0 || pos2 < 0 || pos1 >= distribucio.size() || pos2 >= distribucio.size()) {
            throw new IllegalArgumentException("Posicions incorrectes");
        }

        Producto aux = distribucio.get(pos1);
        distribucio.set(pos1, distribucio.get(pos2));
        distribucio.set(pos2, aux);

        Integer is = calculaIndexSimilitud();
        return is;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vector<Producto> getDistribucio() {
        return distribucio;
    }

    public void setDistribucio(Vector<Producto> distribucio) {
        this.distribucio = distribucio;
        this.indexSimilitut = calculaIndexSimilitud();
    }

    public Integer getFiles() {
        return files;
    }

    public void setFiles(Integer files) {
        this.files = files;
    }

    public Integer getColumnes() {
        return columnes;
    }

    public void setColumnes(Integer columnes) {
        this.columnes = columnes;
    }

    public Integer getIndexSimilitut() {
        return indexSimilitut;
    }

    public void setIndexSimilitut(Integer indexSimilitut) {
        this.indexSimilitut = indexSimilitut;
    }
  
    public boolean conteProd(String nom) {
        for (Producto p : distribucio) {
            if (p.getNombre().equals(nom)) return true;
        }
        return false;
    }

    public Integer calculaIndexSimilitud() {
        Integer is = 0;
        int n = distribucio.size();

        for (int i = 0; i < n-1; i++){
            // Corregir las llamadas a getSimilitud pasando el nombre del producto
            if (i == 0){
                is += distribucio.get(i).getSimilitud(distribucio.get(n-1).getNombre());
            }
            is += distribucio.get(i).getSimilitud(distribucio.get(i+1).getNombre());
        }

        this.indexSimilitut = is;
        return is;
    }
}
