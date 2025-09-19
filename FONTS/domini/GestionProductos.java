package FONTS.domini;

import FONTS.excepcions.ProducteYaExisteix;
import FONTS.excepcions.FaltaSimilitud;
import FONTS.excepcions.ProducteNoExisteix;
import FONTS.excepcions.SimilitudInvalida;
import java.util.*;

public class GestionProductos {
    
    private int numProd = 0;
    private ArrayList<Producto> productos;

    public GestionProductos() {
        this.productos = new ArrayList<>();
    }

    public int getNumProd() {
        return numProd;
    }

    public void addProducto(Producto p) throws ProducteYaExisteix, FaltaSimilitud, SimilitudInvalida {
        if (productoYaExiste(p)) {
            throw new ProducteYaExisteix("Error: El producte ja existeix.");
        }
        verificarSimilitudesValidas(p);

        verificarSimilitudesConProductosExistentes(p);

        productos.add(p);
        numProd++;

        actualizarSimilitudesDeProductosExistentes(p);
    }

    public Map<String,Integer> getSimilitudesProducto(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equals(nombre)) {
                return p.getSimilitudes();
            }
        }
        return null;
    }

    private void verificarSimilitudesValidas(Producto p) throws SimilitudInvalida {
        for (String nombreProducto : p.getSimilitudes().keySet()) {
            boolean existe = false;
            for (Producto productoExistente : productos) {
                if (productoExistente.getNombre().equals(nombreProducto)) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                throw new SimilitudInvalida("Error: El producte te una similitud amb un producte inexistent: " + nombreProducto);
            }
        }
    }

    private void verificarSimilitudesConProductosExistentes(Producto p) throws FaltaSimilitud {
        for (Producto productoExistente : productos) {
            if (!p.tieneSimilitudCon(productoExistente)) {
                throw new FaltaSimilitud("Error: El producte no te similitud amb " + productoExistente.getNombre());
            }
        }
    }
    
    private void actualizarSimilitudesDeProductosExistentes(Producto nuevoProducto) {
        for (Producto productoExistente : productos) {
            if (!productoExistente.equals(nuevoProducto)) {
                Integer similitud = nuevoProducto.getSimilitud(productoExistente.getNombre());
                productoExistente.addSimilitudes(nuevoProducto.getNombre(), similitud);
            }
        }
    }

    public void deleteProducto(Producto p) throws ProducteNoExisteix {
        if (!productoYaExiste(p)) {
            throw new ProducteNoExisteix("Error: El producte no existeix.");
        }
        eliminarSimilitudesConProducto(p);
        productos.remove(p);
        numProd--;
    }

    private void eliminarSimilitudesConProducto(Producto p) {
        for (Producto productoExistente : productos) {
            if (!productoExistente.equals(p)) {
                productoExistente.eliminarSimilitudCon(p); 
            }
        }
    }
    
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public Producto getProducto(String nombre)  throws ProducteNoExisteix{
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        throw new ProducteNoExisteix("Error: El producte no existeix.");
    }

    public void setProductos(ArrayList<Producto> nuevosProductos) throws FaltaSimilitud, ProducteYaExisteix, SimilitudInvalida {
        verificarNoSeRepitenProductos(nuevosProductos);
        for (Producto p : nuevosProductos) {
            verificarSimilitudesValidas(p, nuevosProductos);
            verificarNoSimilitudConsigoMismo(p);
        }
        verificarSimilitudesEntreProductos(nuevosProductos);
        this.productos = nuevosProductos;
        this.numProd = nuevosProductos.size();
    }

    private void verificarNoSimilitudConsigoMismo(Producto p) throws SimilitudInvalida {
        if (p.getSimilitudes().containsKey(p.getNombre())) {
            throw new SimilitudInvalida("Error: El producte " + p.getNombre() + " no pot tenir similitud amb si mateix.");
        }
    }

    private void verificarSimilitudesValidas(Producto p, ArrayList<Producto> nuevosProductos) throws SimilitudInvalida {
        for (Map.Entry<String, Integer> similitud : p.getSimilitudes().entrySet()) {
            String nombreProductoSimilitud = similitud.getKey();
            boolean existeSimilitudConProductoValido = false;
            for (Producto productoExistente : nuevosProductos) {
                if (productoExistente.getNombre().equals(nombreProductoSimilitud)) {
                    existeSimilitudConProductoValido = true;
                    break;
                }
            }
            if (!existeSimilitudConProductoValido) {
                throw new SimilitudInvalida("Error: El producte " + p.getNombre() + " te una similitud amb un producte que no esta a la  llista: " + nombreProductoSimilitud);
            }
        }
    }

    private void verificarNoSeRepitenProductos(ArrayList<Producto> nuevosProductos) throws ProducteYaExisteix {
        Set<String> nombresProductos = new HashSet<>();
        for (Producto producto : nuevosProductos) {
            String nombreProducto = producto.getNombre(); 
            if (nombresProductos.contains(nombreProducto)) {
                throw new ProducteYaExisteix("Error: El producte amb nom '" + nombreProducto + "' esta repetit");
            }
            nombresProductos.add(nombreProducto);
        }
    }

    private void verificarSimilitudesEntreProductos(ArrayList<Producto> productos) throws FaltaSimilitud, SimilitudInvalida {
        for (int i = 0; i < productos.size(); i++) {
            Producto p1 = productos.get(i);
            for (int j = i + 1; j < productos.size(); j++) {
                Producto p2 = productos.get(j);

                if (!p1.tieneSimilitudCon(p2)) {
                    throw new FaltaSimilitud("Error: Els productes " + p1.getNombre() + " i " + p2.getNombre() + " no tenen similitud.");
                }
                Integer similitudP1P2 = p1.getSimilitud(p2.getNombre());
                Integer similitudP2P1 = p2.getSimilitud(p1.getNombre());
                if (similitudP1P2 == null || similitudP2P1 == null || !similitudP1P2.equals(similitudP2P1)) {
                    throw new SimilitudInvalida("Error: Les similituds entre els productes " + p1.getNombre() + " i " + p2.getNombre() + " no son simetriques.");
                }
            }
        }
    }

    private boolean productoYaExiste(Producto p) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(p.getNombre())) {
                return true;
            }
        }
        return false;
    }

    public void modificarSimilitudEntreProductos(String nombreProducto1, String nombreProducto2, Integer valorSimilitud) throws ProducteNoExisteix {
        Producto producto1 = null;
        Producto producto2 = null;
        for (Producto p : productos) {
            if (p.getNombre().equals(nombreProducto1)) {
                producto1 = p;
            }
            if (p.getNombre().equals(nombreProducto2)) {
                producto2 = p;
            }
        }
        if (producto1 != null && producto2 != null) {
            producto1.modificarSimilitud(nombreProducto2, valorSimilitud);
            producto2.modificarSimilitud(nombreProducto1, valorSimilitud);
        } else {
            throw new ProducteNoExisteix("Error: El producte no existeix.");
        }
    }
}
