package FONTS.tests;

import FONTS.domini.GA;
import FONTS.domini.Producto;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import static org.junit.Assert.*;

public class GATest {
    private GA ga;
    private ArrayList<Producto> productos;

    @Before
    public void setUp() {
        ga = new GA();
        productos = new ArrayList<>();

        // Crear productos con similitudes predefinidas
        HashMap<String, Integer> similitudes1 = new HashMap<>();
        similitudes1.put("Producto2", 50);
        similitudes1.put("Producto3", 30);
        similitudes1.put("Producto4", 90);
        productos.add(new Producto("Producto1", similitudes1));

        HashMap<String, Integer> similitudes2 = new HashMap<>();
        similitudes2.put("Producto1", 50);
        similitudes2.put("Producto3", 70);
        similitudes2.put("Producto4", 30);
        productos.add(new Producto("Producto2", similitudes2));

        HashMap<String, Integer> similitudes3 = new HashMap<>();
        similitudes3.put("Producto1", 30);
        similitudes3.put("Producto2", 70);
        similitudes3.put("Producto4", 70);
        productos.add(new Producto("Producto3", similitudes3));

        HashMap<String, Integer> similitudes4 = new HashMap<>();
        similitudes4.put("Producto1", 90);
        similitudes4.put("Producto2", 30);
        similitudes4.put("Producto3", 70);    
        productos.add(new Producto("Producto4", similitudes4));    
    }

    @Test
    public void testGestionAlgoritmoAproximacion() {
        // Ejecutar el algoritmo de aproximación
        int filas = 2;
        int columnas = 2;
        ga.GestionAlgoritmo("Aproximacion", productos, filas, columnas);
        Integer similitudMaxima = ga.getCostMax();

        // Verificar que la similitud máxima es correcta
        assertNotNull(similitudMaxima);
        assertTrue(similitudMaxima > 0);
        assertEquals("La similitud maximo ha de ser: 280",(Integer)280, similitudMaxima);

        // Verificar que el array Prestatge no es nulo
        Vector<Producto> Lista = new Vector<Producto>();
        Lista = ga.getListaProductos();
        assertNotNull(Lista);

        // Verificar dimensiones del prestatge
        assertEquals(filas * columnas, Lista.size());
        
    }

    @Test
    public void testGestionAlgoritmoFuerzaBruta() {
        // Ejecutar el algoritmo de fuerza bruta
        int filas = 2;
        int columnas = 2;
        ga.GestionAlgoritmo("Fuerzabruta", productos, filas, columnas);

        Integer similitudMaxima = ga.getCostMax();
        // Verificar que la similitud máxima es correcta        String[][] prestatge = ga.Prestatge;

        assertNotNull(similitudMaxima);
        assertTrue(similitudMaxima > 0);
        assertEquals("La similitud maximo ha de ser: 280",(Integer)280, similitudMaxima);

        // Verificar que el array Prestatge no es nulo
        Vector<Producto> Lista = new Vector<Producto>();
        Lista = ga.getListaProductos();
        assertNotNull(Lista);

        // Verificar dimensiones del prestatge
        assertEquals(filas * columnas, Lista.size());
        
    }
}
