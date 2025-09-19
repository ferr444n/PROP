package FONTS.drivers;

import FONTS.domini.GA;
import FONTS.domini.Producto;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class DriverGA {

    private GA ga = new GA();    

    private ArrayList<Producto> crearProductos() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Producto> productos = new ArrayList<>();
        
        // Pedir al usuario los datos para los productos
        System.out.println("Introduce el número de productos:");
        int numProductos = sc.nextInt();
        List<String> aux = new ArrayList<>();
        
        // Crear los productos con nombre
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
        return productos;
    }
    
    public void AproximacionTest() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Producto> ini = crearProductos();
        
        System.out.println("Introduce el número de filas de la matriz:");
        int tam1 = sc.nextInt();
        System.out.println("Introduce el número de columnas de la matriz:");
        int tam2 = sc.nextInt();
        
        System.out.println("La disposición óptima de los productos es:");
        ga.GestionAlgoritmo("Aproximacion", ini, tam1, tam2);
    }

    public void FuerzaBrutaTest() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Producto> ini = crearProductos();
        
        System.out.println("Introduce el número de filas de la matriz:");
        int tam1 = sc.nextInt();
        System.out.println("Introduce el número de columnas de la matriz:");
        int tam2 = sc.nextInt();
                
        System.out.println("La disposición óptima de los productos es:");
        ga.GestionAlgoritmo("Fuerzabruta", ini, tam1, tam2);
    }
    
    public void DobleAlgoritmoTest() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Producto> ini = crearProductos();
        
        System.out.println("Introduce el número de filas de la matriz:");
        int tam1 = sc.nextInt();
        System.out.println("Introduce el número de columnas de la matriz:");
        int tam2 = sc.nextInt();
        
        System.out.println("Escoge el algoritmo que quieres ejecutar : Aproximacion o Fuerzabruta");
        String eleccion = sc.next();
        
        ga.GestionAlgoritmo(eleccion, ini, tam1, tam2);
    }

    public void MismosCostosTest() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Producto> ini = crearProductos();
        
        System.out.println("Introduce el número de filas de la matriz:");
        int tam1 = sc.nextInt();
        System.out.println("Introduce el número de columnas de la matriz:");
        int tam2 = sc.nextInt();
        
        // Execute both algorithms
        ga.GestionAlgoritmo("Aproximacion", ini, tam1, tam2);
        int costoaprox = ga.getCostMax(); // Updated method name
        
        ga.GestionAlgoritmo("Fuerzabruta", ini, tam1, tam2);
        int costofuerza = ga.getCostMax(); // Updated method name
        
        System.out.println("El costo de los productos ejecutados con el algoritmo de Aproximacion es " + costoaprox);
        System.out.println("El costo de los productos ejecutados con el algoritmo de Fuerza Bruta es " + costofuerza);
        
        if (costoaprox == costofuerza) {
            System.out.println("Los costos son iguales!!");
        } else {
            System.out.println("Los costos no son iguales, los algoritmos tienen errores");
        }
    }

    public static void main(String[] args) {
        System.out.println("Driver de la clase Gestion de Algoritmos");
        DriverGA dGA = new DriverGA();
        Scanner sc = new Scanner(System.in);
        int func = 0;
        
        // Menú de opciones
        while (func != 5) {
            System.out.println("Selecciona una de las funcionalidades:\n" +
                "1- Obtener disposicion con Aproximacion\n" +
                "2- Obtener disposicion con Fuerza Bruta \n" +
                "3- Elegir algoritmo para obtener una disposicion\n" +
                "4- Obtener costos de los dos algoritmos\n" +
                "5- Finalizar el driver");
            func = sc.nextInt();
            
            switch (func) {
                case 1:
                    dGA.AproximacionTest();
                    break;
                case 2:
                    dGA.FuerzaBrutaTest();
                    break;
                case 3:
                    dGA.DobleAlgoritmoTest();
                    break;
                case 4:
                    dGA.MismosCostosTest();
                    break;
                case 5:
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