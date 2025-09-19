package FONTS.drivers;

import FONTS.domini.Producto;
import java.util.*;

public class DriverProducto {
    private Scanner s;
    private List<Producto> productos;

    //Constructora
    public DriverProducto() {
        this.s = new Scanner(System.in);
        this.productos = new ArrayList<Producto>();
    }

    public static void main(String[] args) {
        DriverProducto dp = new DriverProducto();
        System.out.println("Driver de Producto");
        boolean continuar = true;
        int opcio;
        while (continuar) {
            mostra_menu();

            try {
                opcio = dp.s.nextInt();
                dp.s.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada incorrecta, introdueix un enter");
                dp.s.nextLine();
                continue;
            }
            switch(opcio) {
                case 1:
                    dp.productos.add(dp.testConstructoraNom());
                    break;

                case 2:
                    dp.productos.add(dp.testConstructoraCompleta());
                    break;
                
                case 3:
                    dp.testSetNom();
                    break;
                case 4:
                    dp.testSetSimilituds();
                    break;
                case 5:
                    dp.afegirSimilitud();
                    break;

                case 6:
                    dp.modificarSimilitud();
                    break;

                case 7:
                    dp.comprobarSimilitud();
                    break;

                case 8:
                    dp.testEliminarSimilitud();
                    break;

                case 0:
                    continuar = false;
                    break;
        
                default: 
                    System.out.println("Opcio incorrecta");
                    break;
            }
            if (opcio != 0) dp.tornar_menu();
        }

    }

    private void comprobarSimilitud() {
        System.out.println("Escriu el nom del producte 1:");
        String nomInicial = s.nextLine();
        Producto p = buscarProductoPorNombre(nomInicial);
        if (p == null) {
            System.out.println("Producte no existeix");
            return;
        }
        System.out.println("Escriu el nom del producte amb el que te similitud:");
        String nomDos = s.nextLine();
        if (!p.getSimilitudes().containsKey(nomDos)) {
            System.out.println("No te similtud amb aquest producte");
            return;
        }
        Producto p2 = new Producto(nomDos);
        if (p.tieneSimilitudCon(p2)) System.out.println("Si existe la similitud");
        else System.out.println("No existe la similitud");
    }

    private void testEliminarSimilitud() {
        System.out.println("Escriu el nom del producte al que li vols treure una similitud:");
        String nomInicial = s.nextLine();
        Producto p = buscarProductoPorNombre(nomInicial);
        if (p == null) {
            System.out.println("Producte no existeix");
            return;
        }
        System.out.println("Escriu el nom del producte amb el  que vols eliminar la similitud:");
        String nomDos = s.nextLine();
        if (!p.getSimilitudes().containsKey(nomDos)) {
            System.out.println("No te similtud amb aquest producte");
            return;
        }
        Producto p2 = new Producto(nomDos);
        p.eliminarSimilitudCon(p2);
        System.out.println("S'ha elimant la similitud");
    }

    private static void  mostra_menu() {
        System.out.println("Escull operació a realitzar");
        System.out.println("0 -> Sortir");
        System.out.println("1 -> Crear Producte amb nom");
        System.out.println("2 -> Crear Producte complet");
        System.out.println("3 -> Cambiar Nom del Producte");
        System.out.println("4 -> Cambiar totes les similituds d'un producte");
        System.out.println("5 -> Afegir similitud a un producte");
        System.out.println("6 -> Cambiar similitud amb un producte");
        System.out.println("7 -> Comprobar similitud entre 2 productes");
        System.out.println("8 -> Eliminar similitud amb un producte");
    }

    private void  tornar_menu() {
        System.out.println("Prem Enter per tornar al menu principal");
        s.nextLine();
        
    }

    private Producto testConstructoraNom() {
        System.out.println("Nom del producte:");
        String nomProd = s.nextLine();
        Producto producte = new Producto(nomProd);
         // Confirmar la creació
         System.out.println("S'ha creat el producte amb nom: " + producte.getNombre());
         return producte;
    }

    private Producto testConstructoraCompleta() {
        System.out.println("Nom del producte:");
        String nomProd = s.nextLine();
        HashMap<String, Integer> simil = crearSimilitudes();
        Producto producte = new Producto(nomProd, simil);
         // Confirmar la creació
         System.out.println("S'ha creat el producte amb nom: " + producte.getNombre() + " i les seguents similituds");
         imprimir_similitud(producte.getSimilitudes());
         return producte;
    }

    private HashMap<String, Integer> crearSimilitudes() {
        HashMap<String, Integer> similitudes = new HashMap<>();
        boolean fi = false;
    
        while (!fi) {
            System.out.println("Nom del Producte similar (o fi per terminar):");
            String relacionado = s.nextLine();
            if (relacionado.equalsIgnoreCase("fi")) {
                break;
            }
            System.out.println("Similitud amb el producte:");
            Integer valor;
            try {
                valor = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Similitud invàlida. Introdueix un número vàlid.");
                continue;
            }
            similitudes.put(relacionado, valor);
        }
    
        return similitudes;
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

    private void testSetSimilituds() {
        System.out.println("Escriu el nom del producte al que li vols cambiar les similituds");
        String nomInicial = s.nextLine();
        Producto p = buscarProductoPorNombre(nomInicial);
        if (p == null) {
            System.out.println("Producte no existeix");
            return;
        }
        HashMap<String, Integer> simil = crearSimilitudes();
        p.setSimilitudes(simil);
        System.out.println("Al Producte " + p.getNombre() + " se li han ssignat les seguents similituds:");
        imprimir_similitud(p.getSimilitudes());
    }

    private void testSetNom() {
        System.out.println("Escriu el nom del producte al que li vols cambiar");
        String nomInicial = s.nextLine();
        Producto p = buscarProductoPorNombre(nomInicial);
        if (p == null) {
            System.out.println("Producte no existeix");
            return;
        }
        System.out.println("Insereix el nom final del producte:");
        String nomProd = s.nextLine();
        p.setNombre(nomProd);
         System.out.println("S'ha posat el nom " + p.getNombre() + " al producte");
    }

    private Producto buscarProductoPorNombre(String nombre) {
        for (Producto pro : productos) {
            if (pro.getNombre().equalsIgnoreCase(nombre)) {
                return pro;
            }
        }
        return null;
    }

    private void afegirSimilitud() {
        System.out.println("Escriu el nom del producte al que li vols afegir una similitud");
        String nomInicial = s.nextLine();
        Producto p = buscarProductoPorNombre(nomInicial);
        if (p == null) {
            System.out.println("Producte no existeix");
            return;
        }
        System.out.println("Escriu el nom del producte amb el que esta relacionat:");
        String nomRelacionat = s.nextLine();
        if (p.getSimilitudes().get(nomRelacionat) != null) {
            System.out.println("Ja existeix una similitud amb el producte " + nomRelacionat);
            return;
        }

        System.out.println("Escriu el valor de la relacio:");
        Integer valor;
        try {
            valor = Integer.parseInt(s.nextLine()); 
        } catch (NumberFormatException e) {
            System.out.println("Similitud incorrecta. Introdueix un numero valid.");
            return;
        }
        p.addSimilitudes(nomRelacionat, valor);
        System.out.println("Despres d'afegir la similtud, el Producte " + p.getNombre() + " te les seguents similituds:");
        imprimir_similitud(p.getSimilitudes());
    }

    private void modificarSimilitud() {
        System.out.println("Escriu el nom del producte al que li vols modificar la similitud");
        String nomInicial = s.nextLine();
        Producto p = buscarProductoPorNombre(nomInicial);
        if (p == null) {
            System.out.println("Producte no existeix");
            return;
        }
        
        System.out.println("Escriu el nom del producte amb el que esta relacionat:");
        String nomRelacionat = s.nextLine();

        if (!p.getSimilitudes().containsKey(nomRelacionat)) {
            System.out.println("No existeix una similitud amb aquest producte.");
            return;
        }
        
        System.out.println("Escriu el nou valor de la similitud:");
        Integer valor;
        try {
            valor = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Similitud incorrecta. Introdueix un numero valid.");
            return;
        }

        p.modificarSimilitud(nomRelacionat, valor);
        System.out.println("Similitud modificada correctament.");

        System.out.println("Despres de modificar, les similituds del producte " + p.getNombre() + " son:");
        imprimir_similitud(p.getSimilitudes());
    }

}