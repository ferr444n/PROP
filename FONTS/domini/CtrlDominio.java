package FONTS.domini;

import java.io.IOException;
import java.util.*;

import FONTS.excepcions.*;
import FONTS.persistencia.CtrlPersistencia;


public class CtrlDominio {
    private GA ga;
    private GestionProductos gp;
    private GestioPrestatgeries gPres;
    private CtrlPersistencia ctrlPersistencia;

    public CtrlDominio() {
        this.ga = new GA();
        this.gp = new GestionProductos();
        this.gPres = new GestioPrestatgeries();
        this.ctrlPersistencia = new CtrlPersistencia();
    }

    public CtrlDominio(GA gestAlgoritmo, GestionProductos gestProductos, GestioPrestatgeries gestPrestatgeries) {
        this.ga = gestAlgoritmo;
        this.gp = gestProductos;
        this.gPres = gestPrestatgeries;
    }

    public int getNumProd() {
        return gp.getNumProd();
    }

    public void afegirProducte(String nombreProducto, HashMap<String, Integer> similitudes) throws ProducteYaExisteix, FaltaSimilitud, SimilitudInvalida {
        Producto nuevoProducto = new Producto(nombreProducto, similitudes);
        gp.addProducto(nuevoProducto);
    }
    
    public void eliminarProducto(String nombreProducto) throws ProducteNoExisteix {
        Producto producto = gp.getProducto(nombreProducto);
        gp.deleteProducto(producto);
    }

    public ArrayList<Producto> getProductosControl() {
        return gp.getProductos();
    }
    
    public ArrayList<String> getAllProductosNombres() {
        ArrayList<Producto> p = gp.getProductos();
        ArrayList<String> result = new ArrayList<String>();
        for (Producto prod: p){
            result.add(prod.getNombre());
        }
        return result;
    } 

    public Producto getProductoControl(String name) throws ProducteNoExisteix {
        return gp.getProducto(name);
    }

    public Map<String,Integer> getSimilitudesProducto(String nombreProducto) throws ProducteNoExisteix{
        return gp.getSimilitudesProducto(nombreProducto);
    }

    public void setProductos(List<Map.Entry<String, HashMap<String, Integer>>> nombresConSimilitudes) throws FaltaSimilitud, ProducteYaExisteix, SimilitudInvalida {
        ArrayList<Producto> productos = new ArrayList<>();
    
        // Recorremos la lista y creamos los productos
        for (Map.Entry<String, HashMap<String, Integer>> entry : nombresConSimilitudes) {
            String nombreProducto = entry.getKey();
            HashMap<String, Integer> similitudes = entry.getValue();
    
            Producto nuevoProducto = new Producto(nombreProducto, similitudes);
            productos.add(nuevoProducto);
        }
    
        gp.setProductos(productos);
    }

    public void modificarSimilitudEntreProductosControl(String n1, String n2, Integer valor) throws ProducteNoExisteix {
        gp.modificarSimilitudEntreProductos(n1, n2, valor);
        gPres.actualitzarSimilitud(n1 , n2);
    }

    public Vector<String> calcularAlgoritmo(String algoritm, ArrayList<String> productos, int filas, int columnas) throws ProducteNoExisteix {
        ArrayList<Producto> prod = new ArrayList<>();
        
        for (String p : productos){
            Producto producto = getProductoControl(p);
            prod.add(producto);
        }
        
        if (filas * columnas < productos.size()) {
            System.out.println("Error: filas * columnes ha de ser superior al numero de prodcutes (productos.size())");
            //return;
        }
        ga.GestionAlgoritmo(algoritm, prod, filas, columnas);
        Vector<Producto> distribucioDomini = ga.getListaProductos();
        Vector<String> result = new Vector<>();
        for(Producto p : distribucioDomini){
            result.add(p.getNombre());
        }
        return result;
    }

    //PRESTATGERIES

    public Vector<String> getDistribucio(String id){
        Vector<String> v = gPres.getDistribucio(id);
        return v;
    }

    public Set<String> getIDPrestatgeries() {
        return gPres.getAllIdPrestatgeria();
    }

    public int getSimilitud(String id) {
        return gPres.getSimilitudPrestatgeria(id);
    }
    
    public int getColumnes(String id) {
        return gPres.getColumnes(id);
    }

    public int getFiles(String id) {
        return gPres.getFiles(id);
    }
    public void crearPrestatgeria(String id, Vector<String> distribucio, int files, int columnes, int indexSimilitut) throws ProducteNoExisteix{
        Vector<Producto> d = new Vector<>();
        for (String p : distribucio){
            Producto prod = getProductoControl(p);
            d.add(prod);
        }
        gPres.afegirPrestatgeria(id, d, files, columnes, indexSimilitut);
    }

    public void eliminarPrestatgeria(String id){
        gPres.eliminarPrestatgeria(id);
    }

    public void modificarMidaPrestatgeria(String id, int files, int columnes){
        gPres.modificarMidaPrestatgeria(id, files, columnes);
    }

    public int modificarDistribucioPrestatgeria(String id, Vector<String> distribucio) throws ProducteNoExisteix{
        Prestatgeria pres = gPres.getPrestatgeria(id);
        Vector<Producto> productos = new Vector<>();
        
        for (String nom : distribucio){
            Producto prod = gp.getProducto(nom);
            productos.add(prod);
        }
        
        pres.setDistribucio(productos);
        return pres.getIndexSimilitut();
    }
    
    public int calculaIndexSimilitud(Vector<String> distribucio) throws ProducteNoExisteix {
        int is = 0;
        int n = distribucio.size();

        for (int i = 0; i < n-1; i++){
 
            if (i == 0){
                is += gp.getProducto(distribucio.get(i)).getSimilitud(gp.getProducto(distribucio.get(n-1)).getNombre());
            }
            is += gp.getProducto(distribucio.get(i)).getSimilitud(gp.getProducto(distribucio.get(i+1)).getNombre());;
        }
        return is;
    }
    public void guardarProductos(String... customPath) throws IOException {
        ctrlPersistencia.guardaGestionProductos(gp, customPath);
    }

    public void cargarProductos(String path) throws IOException {
        GestionProductos productosCargados = ctrlPersistencia.carregaGestionProductos(path);

        try {
        // Intentamos reemplazar la lista de productos usando el método setProductos
            gp.setProductos(productosCargados.getProductos());
        } catch (FaltaSimilitud e) {
        // Manejo de la excepción FaltaSimilitud
            System.out.println("Error al cargar los productos debido a la falta de similitudes: " + e.getMessage());
        } catch (ProducteYaExisteix e) {
        // Manejo de la excepción ProducteYaExisteix
            System.out.println("Error al cargar los productos, algunos ya existen: " + e.getMessage());
        } catch (SimilitudInvalida e) {
        // Manejo de la excepción SimilitudInvalida
            System.out.println("Error al cargar los productos debido a similitudes inválidas: " + e.getMessage());
        }
    }

    public void guardarPrestatgeries() {
        ctrlPersistencia.guardaPrestatgeries(gPres);
    }

    public void cargarPrestatgeries(String path) {
        GestioPrestatgeries prestatgeriesCargadas = ctrlPersistencia.carregaPrestatgeries(path);

        for (String id : prestatgeriesCargadas.getAllIdPrestatgeria()) {
            if (!gPres.getPrestatgeries().containsKey(id)) {
            // Añadir prestatgeria nueva
                gPres.afegirPrestatgeria(
                    id,
                    prestatgeriesCargadas.getDistribucioP(id),
                    prestatgeriesCargadas.getFiles(id),
                    prestatgeriesCargadas.getColumnes(id),
                    prestatgeriesCargadas.getSimilitudPrestatgeria(id)
                );
            } 
            else {
            // Gestionar conflicto si la prestatgeria ya existe
            System.out.println("La prestatgeria amb ID '" + id + "' ja existeix. No s'ha afegit.");
            }
        }
    }
}

