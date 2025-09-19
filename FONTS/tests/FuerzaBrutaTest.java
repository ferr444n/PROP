package FONTS.tests;

import FONTS.domini.FuerzaBruta;
import FONTS.domini.Producto;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

public class FuerzaBrutaTest {

    private FuerzaBruta fuerzaBruta;

    // Inicialización de FuerzaBruta antes de cada test
    @Before
    public void setUp() {
        fuerzaBruta = new FuerzaBruta();
    }

    // Crear productos con similitudes predefinidas
    private ArrayList<Producto> crearProductos() {
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
        similitudes0.put("Producto5", 80);
        similitudes0.put("Producto6", 55);
        similitudes0.put("Producto7", 75);
        similitudes0.put("Producto8", 90);
        Producto prod0 = new Producto("Producto0", similitudes0);

        // Producto 1
        HashMap<String, Integer> similitudes1 = new HashMap<>();
        similitudes1.put("Producto0", 50);
        similitudes1.put("Producto2", 55);
        similitudes1.put("Producto3", 80);
        similitudes1.put("Producto4", 60);
        similitudes1.put("Producto5", 75);
        similitudes1.put("Producto6", 65);
        similitudes1.put("Producto7", 85);
        similitudes1.put("Producto8", 70);
        Producto prod1 = new Producto("Producto1", similitudes1);

        // Producto 2
        HashMap<String, Integer> similitudes2 = new HashMap<>();
        similitudes2.put("Producto0", 60);
        similitudes2.put("Producto1", 55);
        similitudes2.put("Producto3", 65);
        similitudes2.put("Producto4", 70);
        similitudes2.put("Producto5", 80);
        similitudes2.put("Producto6", 50);
        similitudes2.put("Producto7", 95);
        similitudes2.put("Producto8", 60);
        Producto prod2 = new Producto("Producto2", similitudes2);

        // Producto 3
        HashMap<String, Integer> similitudes3 = new HashMap<>();
        similitudes3.put("Producto0", 70);
        similitudes3.put("Producto1", 80);
        similitudes3.put("Producto2", 65);
        similitudes3.put("Producto4", 55);
        similitudes3.put("Producto5", 60);
        similitudes3.put("Producto6", 85);
        similitudes3.put("Producto7", 75);
        similitudes3.put("Producto8", 50);
        Producto prod3 = new Producto("Producto3", similitudes3);

        // Producto 4
        HashMap<String, Integer> similitudes4 = new HashMap<>();
        similitudes4.put("Producto0", 65);
        similitudes4.put("Producto1", 60);
        similitudes4.put("Producto2", 70);
        similitudes4.put("Producto3", 55);
        similitudes4.put("Producto5", 60);
        similitudes4.put("Producto6", 80);
        similitudes4.put("Producto7", 50);
        similitudes4.put("Producto8", 90);
        Producto prod4 = new Producto("Producto4", similitudes4);

        // Producto 5
        HashMap<String, Integer> similitudes5 = new HashMap<>();
        similitudes5.put("Producto0", 80);
        similitudes5.put("Producto1", 75);
        similitudes5.put("Producto2", 60);
        similitudes5.put("Producto3", 60);
        similitudes5.put("Producto4", 60);
        similitudes5.put("Producto6", 90);
        similitudes5.put("Producto7", 70);
        similitudes5.put("Producto8", 95);
        Producto prod5 = new Producto("Producto5", similitudes5);

        // Producto 6
        HashMap<String, Integer> similitudes6 = new HashMap<>();
        similitudes6.put("Producto0", 55);
        similitudes6.put("Producto1", 65);
        similitudes6.put("Producto2", 50);
        similitudes6.put("Producto3", 85);
        similitudes6.put("Producto4", 80);
        similitudes6.put("Producto5", 90);
        similitudes6.put("Producto7", 75);
        similitudes6.put("Producto8", 60);
        Producto prod6 = new Producto("Producto6", similitudes6);

        // Producto 7
        HashMap<String, Integer> similitudes7 = new HashMap<>();
        similitudes7.put("Producto0", 75);
        similitudes7.put("Producto1", 85);
        similitudes7.put("Producto2", 95);
        similitudes7.put("Producto3", 75);
        similitudes7.put("Producto4", 50);
        similitudes7.put("Producto5", 70);
        similitudes7.put("Producto6", 75);
        similitudes7.put("Producto8", 80);
        Producto prod7 = new Producto("Producto7", similitudes7);

        // Producto 8
        HashMap<String, Integer> similitudes8 = new HashMap<>();
        similitudes8.put("Producto0", 90);
        similitudes8.put("Producto1", 70);
        similitudes8.put("Producto2", 60);
        similitudes8.put("Producto3", 50);
        similitudes8.put("Producto4", 90);
        similitudes8.put("Producto5", 95);
        similitudes8.put("Producto6", 60);
        similitudes8.put("Producto7", 80);
        Producto prod8 = new Producto("Producto8", similitudes8);

        // Añadir productos a la lista
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
    public void testCasoNueveProductos() {
        // Crear 9 productos con similitudes completas
        ArrayList<Producto> productos = crearNueveProductos();
        
        // Ejecutar el algoritmo de Fuerza Bruta con 9 productos
        String[][] resultado = fuerzaBruta.fuerza_bruta(9, productos, 3, 3);
        
        // Verificar que la similitud máxima sea la correcta
        System.out.println("Similitud máxima para 9 productos: " + fuerzaBruta.getCostoMaximo());
        assertEquals(Long.valueOf(765L), Long.valueOf(fuerzaBruta.getCostoMaximo()));  // Cambio aquí a Long
        System.out.println("La disposicion es:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(resultado[i][j] + " ");
            }
            System.out.println();
        }

        // Matriz esperada de similitudes (3x3)
        String[][] matrizEsperada = {
            {"Producto0", "Producto5", "Producto2"},
            {"Producto7", "Producto1", "Producto3"},
            {"Producto6", "Producto4", "Producto8"}
        };
        // Validar la matriz de resultados
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[i].length; j++) {
                assertEquals(matrizEsperada[i][j], resultado[i][j]);
            }
        }
    }

    // Test para el caso de 4 productos
    @Test
    public void testCasoCuatroProductos() {
        // Crear 4 productos con similitudes predefinidas
        ArrayList<Producto> productos = crearProductos();
        
        // Ejecutar el algoritmo de Fuerza Bruta con 4 productos
        String[][] resultado = fuerzaBruta.fuerza_bruta(4, productos, 2, 2);
        
        // Verificar que la similitud máxima sea la correcta
        System.out.println("Similitud máxima para 4 productos: " + fuerzaBruta.getCostoMaximo());
        assertEquals(Long.valueOf(295L), Long.valueOf(fuerzaBruta.getCostoMaximo()));  // Cambio aquí a Long
        System.out.println("La disposicion es:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(resultado[i][j] + " ");
            }
            System.out.println();
        }
        
        // Comprobar la matriz de nombres de productos esperada
        String[][] matrizEsperada = {
            {"Producto0", "Producto1"},
            {"Producto2", "Producto3"}
        };
        
        // Comparar la matriz de resultados con la esperada
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[i].length; j++) {
                assertEquals(matrizEsperada[i][j], resultado[i][j]);
            }
        }
    }

    // Caso extremo: Sin productos
    @Test
    public void testCasoSinProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        String[][] resultado = fuerzaBruta.fuerza_bruta(0, productos, 0, 0);
        System.out.println("Similitud máxima para sin productos: " + fuerzaBruta.getCostoMaximo());
        assertEquals(Long.valueOf(0L), Long.valueOf(fuerzaBruta.getCostoMaximo()));  // Cambio aquí a Long
    }

    // Caso extremo: Un solo producto
    @Test
    public void testCasoUnSoloProducto() {
        ArrayList<Producto> productos = crearProductoUnico();
        String[][] resultado = fuerzaBruta.fuerza_bruta(1, productos, 1, 1);
        System.out.println("Similitud máxima para un solo producto: " + fuerzaBruta.getCostoMaximo());
        assertEquals(Long.valueOf(0L), Long.valueOf(fuerzaBruta.getCostoMaximo()));  // Cambio aquí a Long
        System.out.println("La disposicion es:");
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
                System.out.print(resultado[i][j] + " ");
            }
            System.out.println();
        }

        String[][] matrizEsperada = {
            {"Producto0"},
        };
        // Validar la matriz de resultados
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[i].length; j++) {
                assertEquals(matrizEsperada[i][j], resultado[i][j]);
            }
        }
    }

    // Test para el caso de 1 fila con 4 columnas
    @Test
    public void testCasoUnaFilaCuatroColumnas() {
        ArrayList<Producto> productos = crearProductos();
        String[][] resultado = fuerzaBruta.fuerza_bruta(4, productos, 1, 4);
        System.out.println("Similitud máxima para 1 fila y 4 columnas: " + fuerzaBruta.getCostoMaximo());
        assertEquals(Long.valueOf(295L), Long.valueOf(fuerzaBruta.getCostoMaximo()));  // Cambio aquí a Long
        System.out.println("La disposicion es:");
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(resultado[i][j] + " ");
            }
            System.out.println();
        }
    }
}
