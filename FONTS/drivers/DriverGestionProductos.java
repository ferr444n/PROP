package FONTS.drivers;

import FONTS.domini.Producto;
import FONTS.excepcions.FaltaSimilitud;
import FONTS.excepcions.ProducteNoExisteix;
import FONTS.excepcions.ProducteYaExisteix;
import FONTS.excepcions.SimilitudInvalida;
import FONTS.domini.GestionProductos;

import java.util.*;

public class DriverGestionProductos {
    private Scanner s;
    private GestionProductos gp;

    //Constructora
    public DriverGestionProductos() {
        this.s = new Scanner(System.in);
        this.gp = new GestionProductos();
    }

    public static void main(String[] args) {
        DriverGestionProductos dgp = new DriverGestionProductos();
        System.out.println("Driver de GestionProductos");
        boolean continuar = true;
        int opcio;
        while (continuar) {
            mostra_menu();
            try {
                opcio = dgp.s.nextInt();
                dgp.s.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada incorrecta, introdueix un enter");
                dgp.s.nextLine();
                continue;
            }
            switch(opcio) {
                case 0:
                    continuar = false;
                    break;
                case 1:
                    dgp.testGetnumero();
                    break;
                case 2:
                    dgp.testAfegirProducte();
                    break;
                case 3:
                    dgp.testTreureProducte();
                    break;
                case 4:
                    dgp.testLlistarProductes();
                    break;
                case 5:
                    dgp.testSetProductes();
                    break;

                case 6:
                    dgp.testcambiarsimilituds();
                    break;
                default: 
                    System.out.println("Opcio incorrecta");
                    break;
            }
            if (opcio != 0) dgp.tornar_menu();
        }
    }

    private void testcambiarsimilituds() {
        System.out.println("Nom del producte 1");
        String nombreProducto1 = s.nextLine();
        System.out.println("Nom del producte 2");
        String nombreProducto2 = s.nextLine();
        System.out.println("Introdueix el valor de la similitud:");
        Integer valor;
        try {
            valor = Integer.parseInt(s.nextLine()); 
        } catch (NumberFormatException e) {
            System.out.println("Similitud incorrecta. Introdueix un numero valid.");
            return;
        }
        try {
            gp.modificarSimilitudEntreProductos(nombreProducto1, nombreProducto2, valor);
        } catch (ProducteNoExisteix e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Similitud modificada correctament");
    }

    private void testSetProductes() {
        int num;
        while (true) { 
            System.out.println("Indica el numero de productes:");
            try {
                num = s.nextInt();
                s.nextLine();
                if (num <= 0) {
                    System.out.println("Ingresa un numero superior a 0.");
                    continue;
                }
                break;
                } catch (InputMismatchException e) { 
                    System.out.println("Error: Entrada incorrecta, introdueix un enter");
                    s.nextLine(); 
            }
        }
        ArrayList<Producto> nuevosProductos = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            System.out.println("Nom del producte " + (i + 1) + ":");
            String nomProd = s.nextLine();
    
            // Crea un mapa para las similitudes del nuevo producto
            HashMap<String, Integer> simil = new HashMap<>();
            for (Producto productoExistente : nuevosProductos) {
                boolean entradaValida = false;
                while (!entradaValida) {
                    System.out.println("Similitud amb el producte " + productoExistente.getNombre() + ":");
                    try {
                        int valor = Integer.parseInt(s.nextLine());
                        simil.put(productoExistente.getNombre(), valor);
                        // Añade la relación simétrica en el producto existente
                        productoExistente.addSimilitudes(nomProd, valor);
                        entradaValida = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Similitud invalida. Introdueix un número vàlid.");
                    }
                }
            }
    
            Producto producte = new Producto(nomProd, simil);
            nuevosProductos.add(producte);
        }
    
        try {
            gp.setProductos(nuevosProductos);
            System.out.println("Productos añadidos correctamente.");
        } catch (FaltaSimilitud e) {
            System.out.println(e.getMessage());
        }
        catch (SimilitudInvalida e) {
            System.out.println(e.getMessage());
        }
        catch (ProducteYaExisteix e) {
            System.out.println(e.getMessage());
        }
        
    }
    

    private void testLlistarProductes() {
        List<Producto> productos = gp.getProductos();
        if (productos.isEmpty()) {
            System.out.println("No hi ha productes disponibles.");
            return;
        }
        
        System.out.println("Llistat de productes i les seves similituds:");
        for (Producto producto : productos) {
            System.out.println("Producte: " + producto.getNombre());
            imprimir_similitud(producto.getSimilitudes());
            System.out.println();
        }
    }

    private void imprimir_similitud(HashMap<String, Integer> similitudes) {
        if (similitudes.isEmpty()) {
            System.out.println("No hi ha similituds definides.");
            return;
        }
        for (Map.Entry<String, Integer> entrada : similitudes.entrySet()) {
            System.out.println(entrada.getKey() + " -> " + entrada.getValue());
        }
    }

    private void testTreureProducte() {
        System.out.println("Nom del producte:");
        String nomProd = s.nextLine();
        try {
            Producto p = gp.getProducto(nomProd);
            gp.deleteProducto(p);
            System.out.println("Producte eliminat correctament.");
        } catch (ProducteNoExisteix e) {

        System.out.println(e.getMessage());
        }
    }


    private void testAfegirProducte() {
        System.out.println("Nom del producte:");
        String nomProd = s.nextLine();
        HashMap<String, Integer> simil = crearSimilitudes();
        Producto producte = new Producto(nomProd, simil);
        try {
            gp.addProducto(producte);
        } catch (ProducteYaExisteix e) {
        System.out.println(e.getMessage());
        return;
        } catch (FaltaSimilitud e) {
        System.out.println(e.getMessage());
        return;
        } catch (SimilitudInvalida e) {
        System.out.println(e.getMessage());
        return;
        }       
    System.out.println("Producte afegit correctament.");
}

private HashMap<String, Integer> crearSimilitudes() {
    HashMap<String, Integer> similitudes = new HashMap<>();
    ArrayList<Producto> productosExistentes = gp.getProductos();

    if (productosExistentes.isEmpty()) {
        System.out.println("No hi ha productes existents per establir similituds.");
        return similitudes;
    }
    for (Producto producto : productosExistentes) {
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.println("Similitud amb el producte " + producto.getNombre() + ":");
            Integer valor;
            try {
                valor = Integer.parseInt(s.nextLine());
                entradaValida = true;
                similitudes.put(producto.getNombre(), valor);
            } catch (NumberFormatException e) {
                System.out.println("Similitud invalida. Introdueix un numero valid.");
            }
        }
    }
    return similitudes;
}


    private void testGetnumero() {
        System.out.println("Hi han " + gp.getNumProd() + " productes");
    }

    private static void  mostra_menu() {
        System.out.println("Escull operacio a realitzar");
        System.out.println("0 -> Sortir");
        System.out.println("1 -> Saber quantitat productes");
        System.out.println("2 -> Afegir Producte");
        System.out.println("3 -> Treure Producte");
        System.out.println("4 -> Llistar Productes");
        System.out.println("5 -> Set Productes");
        System.out.println("6 -> Cambiar similitud entre dos Productes");
    }

    private void  tornar_menu() {
        System.out.println("Prem Enter per tornar al menu principal");
        s.nextLine();
        
    }
}