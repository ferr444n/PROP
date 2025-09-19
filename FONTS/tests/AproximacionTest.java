package FONTS.tests;

import FONTS.domini.Aproximacion;
import FONTS.domini.Producto;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AproximacionTest {

    private Aproximacion aproximacion;

    // Inicialización de FuerzaBruta antes de cada test
    @Before
    public void setUp() {
        aproximacion = new Aproximacion();
    }

    

    // Crear productos con similitudes predefinidas
    private ArrayList<Producto> crearProductos() {
        // Crear 4 productos con similitudes predefinidas entre ellos
        ArrayList<Producto> productos = new ArrayList<>();

        // Producto 0
        HashMap<String, Integer> similitudes0 = new HashMap<>();
        similitudes0.put("Producto1", 80);
        similitudes0.put("Producto2", 70);
        similitudes0.put("Producto3", 60);
        Producto prod0 = new Producto("Producto0", similitudes0);
        
        // Producto 1
        HashMap<String, Integer> similitudes1 = new HashMap<>();
        similitudes1.put("Producto0", 80);
        similitudes1.put("Producto2", 75);
        similitudes1.put("Producto3", 65);
        Producto prod1 = new Producto("Producto1", similitudes1);

        // Producto 2
        HashMap<String, Integer> similitudes2 = new HashMap<>();
        similitudes2.put("Producto0", 70);
        similitudes2.put("Producto1", 75);
        similitudes2.put("Producto3", 80);
        Producto prod2 = new Producto("Producto2", similitudes2);
        
        // Producto 3
        HashMap<String, Integer> similitudes3 = new HashMap<>();
        similitudes3.put("Producto0", 60);
        similitudes3.put("Producto1", 65);
        similitudes3.put("Producto2", 80);
        Producto prod3 = new Producto("Producto3", similitudes3);

        // Añadir productos a la lista
        productos.add(prod0);
        productos.add(prod1);
        productos.add(prod2);
        productos.add(prod3);
        
        return productos;
    }

    // Crear productos para el caso de 1 producto con similitudes triviales
    private ArrayList<Producto> crearProductoUnico() {
        ArrayList<Producto> productos = new ArrayList<>();
        HashMap<String, Integer> similitudes = new HashMap<>();
        productos.add(new Producto("Producto0", similitudes));
        return productos;
    }

    // Crear 9 productos nuevos con similitudes
    private ArrayList<Producto> crearNueveProductos() {
        ArrayList<Producto> productos = new ArrayList<>();

        // Producto 0
        HashMap<String, Integer> similitudes0 = new HashMap<>();
        similitudes0.put("Producto1", 50);
        similitudes0.put("Producto2", 60);
        similitudes0.put("Producto3", 70);
        similitudes0.put("Producto4", 65);
        Producto prod0 = new Producto("Producto0", similitudes0);

        // Producto 1
        HashMap<String, Integer> similitudes1 = new HashMap<>();
        similitudes1.put("Producto0", 50);
        similitudes1.put("Producto2", 55);
        similitudes1.put("Producto3", 80);
        similitudes1.put("Producto4", 60);
        Producto prod1 = new Producto("Producto1", similitudes1);

        // Producto 2
        HashMap<String, Integer> similitudes2 = new HashMap<>();
        similitudes2.put("Producto0", 60);
        similitudes2.put("Producto1", 55);
        similitudes2.put("Producto3", 65);
        similitudes2.put("Producto4", 70);
        Producto prod2 = new Producto("Producto2", similitudes2);

        // Producto 3
        HashMap<String, Integer> similitudes3 = new HashMap<>();
        similitudes3.put("Producto0", 70);
        similitudes3.put("Producto1", 80);
        similitudes3.put("Producto2", 65);
        similitudes3.put("Producto4", 55);
        Producto prod3 = new Producto("Producto3", similitudes3);

        // Producto 4
        HashMap<String, Integer> similitudes4 = new HashMap<>();
        similitudes4.put("Producto0", 65);
        similitudes4.put("Producto1", 60);
        similitudes4.put("Producto2", 70);
        similitudes4.put("Producto3", 55);
        Producto prod4 = new Producto("Producto4", similitudes4);

        // Producto 5
        HashMap<String, Integer> similitudes5 = new HashMap<>();
        similitudes5.put("Producto0", 55);
        similitudes5.put("Producto1", 60);
        similitudes5.put("Producto2", 65);
        similitudes5.put("Producto3", 70);
        Producto prod5 = new Producto("Producto5", similitudes5);

        // Producto 6
        HashMap<String, Integer> similitudes6 = new HashMap<>();
        similitudes6.put("Producto0", 80);
        similitudes6.put("Producto1", 70);
        similitudes6.put("Producto2", 55);
        similitudes6.put("Producto3", 60);
        Producto prod6 = new Producto("Producto6", similitudes6);

        // Producto 7
        HashMap<String, Integer> similitudes7 = new HashMap<>();
        similitudes7.put("Producto0", 65);
        similitudes7.put("Producto1", 75);
        similitudes7.put("Producto2", 70);
        similitudes7.put("Producto3", 80);
        Producto prod7 = new Producto("Producto7", similitudes7);

        // Producto 8
        HashMap<String, Integer> similitudes8 = new HashMap<>();
        similitudes8.put("Producto0", 60);
        similitudes8.put("Producto1", 55);
        similitudes8.put("Producto2", 80);
        similitudes8.put("Producto3", 65);
        Producto prod8 = new Producto("Producto8", similitudes8);

        productos.add(prod0);
        productos.add(prod1);
        productos.add(prod2);
        productos.add(prod3);
        productos.add(prod4);
        productos.add(prod5);
        productos.add(prod6);
        productos.add(prod7);
        productos.add(prod8);

        return productos;
    }

    @Test

    public void test4productos() {
        List<Producto> productos = crearProductos();

        List<Producto> resultado = aproximacion.ArbolMaximo(productos, productos.size());

        System.out.println("Similitud máxima para 4 productos: " + aproximacion.getCostoMaximo());
        assertEquals("La similitud máxima debe ser 295", 295, (int)aproximacion.getCostoMaximo());

    }

    // Test para el caso de 9 productos
    @Test
    public void testCasoNueveProductos() {
        // Crear 9 productos nuevos con similitudes predefinidas
        List<Producto> productos = crearNueveProductos();
        
        // Ejecutar el algoritmo de Fuerza Bruta con 9 productos
        List<Producto> resultado = aproximacion.ArbolMaximo(productos, productos.size());
        
        // Verificar que la similitud máxima sea mayor que 0
        System.out.println("Similitud máxima para 9 productos: " + aproximacion.getCostoMaximo());
        assertEquals("La similitud máxima debe ser 590", 590, (int)aproximacion.getCostoMaximo());
    }

    // Caso extremo: Sin productos
    @Test
    public void testCasoSinProductos() {
        // Lista vacía de productos
        List<Producto> productos = new ArrayList<>();
        
        // Ejecutar el algoritmo de Fuerza Bruta con 0 productos
        List<Producto> resultado = aproximacion.ArbolMaximo(productos, productos.size());
        
        // Verificar que la similitud máxima sea 0
        System.out.println("Similitud máxima para sin productos: " + aproximacion.getCostoMaximo());
        assertEquals("La similitud máxima debe ser 0", 0, (int)aproximacion.getCostoMaximo());
    }

    // Caso extremo: Un solo producto
    @Test
    public void testCasoUnSoloProducto() {
        // Crear 1 producto sin similitudes
        List<Producto> productos = crearProductoUnico();
        
        // Ejecutar el algoritmo de Fuerza Bruta con 1 producto
        List<Producto> resultado = aproximacion.ArbolMaximo(productos, productos.size());
        
        // Verificar que la similitud máxima sea 0
        System.out.println("Similitud máxima para un solo producto: " + aproximacion.getCostoMaximo());
        assertEquals("La similitud máxima debe ser 0", 0, (int)aproximacion.getCostoMaximo());
    }
}


