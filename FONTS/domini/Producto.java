package FONTS.domini;

import java.util.*;


public class Producto {
    private String nombre;
    private HashMap<String, Integer> similitudes = new HashMap<>();

    public Producto (String n){
        nombre = n;
    }

    public Producto(String name, HashMap<String, Integer> similitudes) {
        this.nombre = name;
        this.similitudes = similitudes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getSimilitud (String p){
        return similitudes.getOrDefault(p, 0);
    }

    public Integer getSimilitud (Producto p){
        return similitudes.getOrDefault(p.getNombre(), 0);
    }
    
    public HashMap<String, Integer> getSimilitudes() {
        return similitudes;
    }

    public void setSimilitudes(HashMap<String, Integer> similitudes) {
        this.similitudes = similitudes;
    }

    public void addSimilitudes(String p, Integer sim) {
        similitudes.put(p,sim);
    }

    public void modificarSimilitud(String p, Integer sim) {
        similitudes.replace(p,sim);
    }
    public boolean tieneSimilitudCon(Producto otroProducto) {
        return similitudes.containsKey(otroProducto.getNombre());
    }

    public void eliminarSimilitudCon(Producto p) {
        this.similitudes.remove(p.getNombre());
    }
}

