package FONTS.drivers;
import FONTS.domini.FuerzaBruta;
import FONTS.domini.Producto;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class DriverFuerzaBruta {
    private FuerzaBruta fuerzabruta = new FuerzaBruta();
    
    public void getNomTest() {
        System.out.println("El nombre de la estrategia de cálculo de disposición es 'Fuerza Bruta'\n");
    }
    
    public void FuerzaBrutaTest() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Producto> productos = new ArrayList<>();
        
        System.out.println("Introduce el número de productos:");
        int numProductos = sc.nextInt();
        ArrayList<String> aux = new ArrayList<>();
        for (int i = 0; i < numProductos; i++) {
            System.out.println("Introduce el nombre del producto " + (i + 1) + ":");
            String nombre = sc.next();
            aux.add(nombre);
        }

        for (int i = 0; i < numProductos; i++) {
            HashMap<String, Integer> similitudes = new HashMap<>();
            for (int j = 0; j < numProductos; j++) {
                if(aux.get(i) != aux.get(j)){
                    System.out.println("Introduce la similitud del producto " + aux.get(i) + " con el producto " + aux.get(j) + ": (Integer)");
                    similitudes.put(aux.get(j), sc.nextInt());
                }
            }
            productos.add(new Producto(aux.get(i), similitudes));
        }

        
        System.out.println("Introduce el número de filas de la matriz:");
        int tam1 = sc.nextInt();
        System.out.println("Introduce el número de columnas de la matriz:");
        int tam2 = sc.nextInt();
        
        String[][] disposicion = fuerzabruta.fuerza_bruta(numProductos, productos, tam1, tam2);
        System.out.println("La similitud total es: " + fuerzabruta.getCostoMaximo());
        
        System.out.println("La disposición óptima de los productos es:");
        for (int i = 0; i < tam1; i++) {
            for (int j = 0; j < tam2; j++) {
                System.out.print(disposicion[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Driver de la clase Aproximacion");
        DriverFuerzaBruta dfuerzb = new DriverFuerzaBruta();
        Scanner sc = new Scanner(System.in);
        int func = 0;
        
        while (func != 3) {
            System.out.println("Selecciona una de las funcionalidades:\n1- Obtener nombre de la estrategia\n2- Ejecutar Fuerza Bruta con productos\n3- Finalizar el driver");
            func = sc.nextInt();
            
            switch (func) {
                case 1:
                    dfuerzb.getNomTest();
                    break;
                case 2:
                    dfuerzb.FuerzaBrutaTest();
                    break;
                case 3:

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
