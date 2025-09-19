package FONTS.domini;

import java.util.ArrayList;
import java.util.Vector;

public class GA {
    private AlgoritmoEstanteria algoritmo;
    private Vector<Producto> listaProductos;
    private Integer costomax = 0;

    public GA() {
    }

    public Integer getCostMax() {
        return this.costomax;
    }

    public Vector<Producto> getListaProductos() {
        return this.listaProductos;
    }

    public void GestionAlgoritmo(String algoritmoNombre, ArrayList<Producto> productos, int filas, int columnas) {
        long tiempoInicio = System.nanoTime();

        switch (algoritmoNombre) {
            case "Algoritmo de aproximacion":
            case "Aproximacion":
                algoritmo = new Aproximacion();
                break;
            case "Algoritmo de fuerza bruta":
            case "Fuerzabruta":
                algoritmo = new FuerzaBruta();
                break;
            default:
                System.out.println("Algoritmo escogido incorrecto, vuelva a ingresar el algoritmo");
                return;
        }

        listaProductos = algoritmo.ejecutarAlgoritmo(productos, filas, columnas);
        
        // Print the products
        for (int i = 0; i < filas; ++i) {
            for (int j = 0; j < columnas; ++j) {
                if (i * columnas + j < listaProductos.size()) {
                    System.out.print(listaProductos.get(i * columnas + j).getNombre() + " ");
                }
            }
            System.out.println();
        }

        long tiempoFin = System.nanoTime();
        long duracion = tiempoFin - tiempoInicio;
        System.out.println("Duración en milisegundos: " + (duracion/1000000.0));

        costomax = algoritmo.getCostoMaximo();
        System.out.println("Costo maximo: " + costomax);
    }
}