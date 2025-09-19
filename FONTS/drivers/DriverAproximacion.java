package FONTS.drivers;
import FONTS.domini.Aproximacion;
import FONTS.domini.Producto;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class DriverAproximacion {
    private Aproximacion aprox = new Aproximacion();
    
    // Método para ejecutar la prueba de la fuerza bruta
    public void AproximacionTest() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Producto> productos = new ArrayList<>();
        
        // Pedir al usuario los datos para los productos
        System.out.println("Introduce el número de productos:");
        int numProductos = sc.nextInt();
        List<String> aux = new ArrayList<>();
        // Crear los productos con nombre y similitud (esto depende de cómo tengas la clase Producto)
        for (int i = 0; i < numProductos; i++) {
            System.out.println("Introduce el nombre del producto " + (i + 1) + ":");
            String nombre = sc.next();
            aux.add(nombre);
        }

        for (int i = 0; i < numProductos; i++) {
            HashMap<String, Integer> similitudes = new HashMap<>();
            for (int j = 0; j < numProductos; j++) {
                System.out.println("Introduce la similitud del producto " + aux.get(i) + " con el producto " + aux.get(j) + ":");
                similitudes.put(aux.get(j), sc.nextInt());
            }
            productos.add(new Producto(aux.get(i), similitudes));
        }

        
        // Pedir al usuario las dimensiones de la matriz
        System.out.println("Introduce el número de filas de la matriz:");
        int tam1 = sc.nextInt();
        System.out.println("Introduce el número de columnas de la matriz:");
        int tam2 = sc.nextInt();
        
        // Ejecutar el algoritmo de aproximacion
        long tiempoInicio = System.nanoTime();
        List<Producto> disposicion = aprox.ArbolMaximo(productos, productos.size());
        long tiempoFinal = System.nanoTime();
        Integer costo = aprox.getCostoMaximo();
        long diftiempo = tiempoFinal - tiempoInicio;
        // Imprimir la disposición óptima
        System.out.println("La disposición óptima de los productos es:");
        for (int i = 0; i < tam1; i++) {
            for (int j = 0; j < tam2; j++) {
                if ((i*tam2 +j)< disposicion.size())System.out.print(disposicion.get(i*tam2 + j).getNombre() + " ");
            }
            System.out.println();
        }
        System.out.println("Similitud maxima: " + costo);
        System.out.println("Tiempo de ejecucion del algortimo en milisegundos: " + diftiempo/1000000.0);
    }
    
    public static void main(String[] args) {
        System.out.println("Driver de la clase Aproximacion");
        DriverAproximacion daprox = new DriverAproximacion();
        Scanner sc = new Scanner(System.in);
        int func = 0;
        
        // Menú de opciones
        while (func != 2) {
            System.out.println("Selecciona una de las funcionalidades:\n1- Ejecutar 2aproximacion con productos\n2- Finalizar el driver");
            func = sc.nextInt();
            
            switch (func) {
                case 1:
                    daprox.AproximacionTest();
                    break;

                case 2:
                    System.out.println("Finalizando driver");
                    sc.close();
                    break;

                default:
                    System.out.println("Funcionalidad equivocada");
                    break;
            }
        }
    }
}