package FONTS.domini;

import FONTS.excepcions.*;
import java.util.*;

public class FuerzaBruta implements AlgoritmoEstanteria{
    private int num_filas;
    private int num_columnas;
    private String[][] disposicio_optima;
    private String[][] actual;
    private int sim_max;;
    
        @Override
        public Vector<Producto> ejecutarAlgoritmo(ArrayList<Producto> productos, int filas, int columnas) {
            String[][] resultadofuerza = fuerza_bruta(productos.size(), productos, 1, productos.size());
            
            Vector<Producto> ListaProductos = new Vector<>(filas * columnas);
            
            Map<String, Producto> indexar = new HashMap<>();
            for (Producto p : productos) {
                indexar.put(p.getNombre(), p);
            }
            
            for (String[] fila : resultadofuerza) {
                for (String col : fila) {
                    ListaProductos.add(indexar.get(col));
                }
            }
            
            return ListaProductos;
        }
    
        @Override
        public Integer getCostoMaximo() {
            return this.sim_max;
        }
    

    public String[][] fuerza_bruta(int n_productos, ArrayList<Producto> productos, int tam1, int tam2) {
        num_filas = tam1;
        num_columnas = tam2;
        sim_max = 0;
        disposicio_optima = new String[num_filas][num_columnas];
        actual = new String[num_filas][num_columnas];
        
        Boolean[] visitado = new Boolean[n_productos];
        Arrays.fill(visitado, false);  // Marca todos los productos como no visitados
        
        busqueda(actual, productos, visitado, 0, 0, 0, n_productos, null);
        
        return disposicio_optima;
    }

    private int busqueda(String [][] s, ArrayList<Producto> productos, Boolean[] visitado, int similitud, int columna, int fila, int n_productos, Producto prod_ant){
        if (fila == num_filas) {
            if(prod_ant != null && s[0][0] != null)similitud += prod_ant.getSimilitud(s[0][0]); // Similaridad con el primer producto 
            
            if(similitud > sim_max || n_productos == 1){
                sim_max = similitud;
                disposicio_optima = copiarMatriz(s);
            }

            return similitud;  
        }
    
        int siguienteColumna = columna + 1;
        int siguienteFila = fila;
        if (siguienteColumna == num_columnas) {
            siguienteColumna = 0;
            siguienteFila++;
        }
    
        int mejorSimilitud = similitud; 
    
        for (int i = 0; i < n_productos; i++) {
            
            if (!visitado[i]) {  
                s[fila][columna] = productos.get(i).getNombre();
                
                visitado[i] = true;
    
                int similitudTemporal = similitud;
                if (prod_ant != null) {
                    similitudTemporal += productos.get(i).getSimilitud(prod_ant.getNombre());
                }

                int resultadoRecursivo = busqueda(s, productos, visitado, similitudTemporal, siguienteColumna, siguienteFila, n_productos, productos.get(i));
    
                mejorSimilitud = Math.max(mejorSimilitud, resultadoRecursivo);
    
                visitado[i] = false;
            }
        }
        return mejorSimilitud; 
    }

    private String[][] copiarMatriz(String[][] matriz) {
        String[][] copia = new String[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
        System.arraycopy(matriz[i], 0, copia[i], 0, matriz[i].length);
        }
        return copia;
    }
}