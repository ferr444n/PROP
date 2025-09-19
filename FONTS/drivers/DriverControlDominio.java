package FONTS.drivers;

import FONTS.excepcions.FaltaSimilitud;
import FONTS.excepcions.ProducteNoExisteix;
import FONTS.excepcions.ProducteYaExisteix;
import FONTS.excepcions.SimilitudInvalida;
import FONTS.domini.CtrlDominio;
import FONTS.domini.Producto;

import java.io.IOException;
import java.util.*;

public class DriverControlDominio {
    private Scanner s;
    private CtrlDominio cd;

    // Constructora
    public DriverControlDominio() {
        this.s = new Scanner(System.in);
        this.cd = new CtrlDominio();
    }

    public static void main(String[] args) {
        DriverControlDominio dcd = new DriverControlDominio();
        System.out.println("Driver de CtrlDominio");
        boolean continuar = true;
        int opcio;
        while (continuar) {
            mostra_menu();
            try {
                opcio = dcd.s.nextInt();
                dcd.s.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada incorrecta, introdueix un enter");
                dcd.s.nextLine();
                continue;
            }
            switch (opcio) {
                case 0:
                    continuar = false;
                    break;
                case 1:
                    dcd.testGetnumero();
                    break;
                case 2:
                    dcd.testAfegirProducte();
                    break;
                case 3:
                    dcd.testTreureProducte();
                    break;
                case 4:
                    dcd.testLlistarProductes();
                    break;
                case 5:
                    dcd.testSetProductes();
                    break;
                case 6:
                    dcd.testcambiarsimilituds();
                    break;
                case 7:
                    dcd.testcalcularalgoritmos();
                    break;
                case 8:
                    dcd.testGuardarProductos();
                    break;
                case 9:
                    dcd.testCargarProductos();
                    break;
                case 10:
                    dcd.testGuardarPrestatgeries();
                    break;
                case 11:
                    dcd.testCargarPrestatgeries();
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
            if (opcio != 0) dcd.tornar_menu();
        }
    }
    private void testGuardarProductos() {
        System.out.println("¿Quieres especificar un path para guardar los productos? (s/n):");
        String respuesta = s.nextLine().trim().toLowerCase();
        try {
            if (respuesta.equals("s")) {
                System.out.println("Indica el path donde quieres guardar los productos:");
                String customPath = s.nextLine().trim();
                cd.guardarProductos(customPath);
                System.out.println("Productos guardados correctamente en el archivo especificado.");
            } else {
                cd.guardarProductos(); // Usa el valor predeterminado en CtrlPersistencia
                System.out.println("Productos guardados correctamente en el archivo predeterminado.");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los productos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Se produjo un error inesperado: " + e.getMessage());
        }
    }
    
    private void testCargarProductos() {
        System.out.println("Indica el path del archivo de productos:");
        String path = s.nextLine();
        try {
            cd.cargarProductos(path);
            System.out.println("Productos cargados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al cargar los productos: " + e.getMessage());
        }
    }
    
    private void testGuardarPrestatgeries() {
        try {
            cd.guardarPrestatgeries();
            System.out.println("Prestatgeries guardadas correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar las prestatgeries: " + e.getMessage());
        }
    }
    
    private void testCargarPrestatgeries() {
        System.out.println("Indica el path del archivo de prestatgeries:");
        String path = s.nextLine();
        try {
            cd.cargarPrestatgeries(path);
            System.out.println("Prestatgeries cargadas correctamente.");
        } catch (Exception e) {
            System.out.println("Error al cargar las prestatgeries: " + e.getMessage());
        }
    }
    
    private void testcalcularalgoritmos() {
        System.out.println("Escriu l'algoritme a utilitzar(Aproximacion/Fuerzabruta):");
        String nomAl = s.nextLine();
        
        int fila;
        while (true) {
            System.out.println("Escriu el numero de files:");
            try {
                fila = s.nextInt();
                s.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada incorrecta, introdueix un enter.");
                s.nextLine();
            }
        }
        
        int columna;
        while (true) {
            System.out.println("Escriu el numero de columnes:");
            try {
                columna = s.nextInt();
                s.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada incorrecta, introdueix un enter.");
                s.nextLine();
            }
        }
        
        try {
            cd.calcularAlgoritmo(nomAl, cd.getAllProductosNombres(), fila, columna);
        } catch (ProducteNoExisteix e) {
            System.out.println(e.getMessage());
        }
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
        List<Map.Entry<String, HashMap<String, Integer>>> nuevosProductos = new ArrayList<>();

        
        for (int i = 0; i < num; i++) {
            System.out.println("Nom del producte " + (i + 1) + ":");
            String nomProd = s.nextLine();
            HashMap<String, Integer> simil = new HashMap<>();
    
            for (Map.Entry<String, HashMap<String, Integer>> prodNuevo : nuevosProductos) {
                String nombreExistente = prodNuevo.getKey();
                boolean entradaValida = false;
    
                while (!entradaValida) {
                    System.out.println("Similitud amb el producte " + nombreExistente + ":");
                    try {
                        int valor = Integer.parseInt(s.nextLine());
                        simil.put(nombreExistente, valor);
                        // Añadir la relación simétrica
                        prodNuevo.getValue().put(nomProd, valor);
                        entradaValida = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Similitud invalida. Introdueix un número vàlid.");
                    }
                }
            }
    
            nuevosProductos.add(new AbstractMap.SimpleEntry<>(nomProd, simil));
        }
    
        try {
            cd.setProductos(nuevosProductos);
            System.out.println("Productos añadidos correctamente.");
        } catch (FaltaSimilitud e) {
            System.out.println(e.getMessage());
        } catch (SimilitudInvalida e) {
            System.out.println(e.getMessage());
        } catch (ProducteYaExisteix e) {
            System.out.println(e.getMessage());
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
            cd.modificarSimilitudEntreProductosControl(nombreProducto1, nombreProducto2, valor);
        } catch (ProducteNoExisteix e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Similitud entre " + nombreProducto1 + " i " + nombreProducto2 + " modificada a " + valor);
    }

    private void testLlistarProductes() {
        List<Producto> productos = cd.getProductosControl();
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

    private void testAfegirProducte() {
        System.out.println("Nom del producte:");
        String nomProd = s.nextLine();
        HashMap<String, Integer> simil = crearSimilitudes();
        try {
            cd.afegirProducte(nomProd, simil);
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

private void testTreureProducte() {
    System.out.println("Nom del producte:");
    String nomProd = s.nextLine();
    try {
        cd.eliminarProducto(nomProd);
        System.out.println("Producte eliminat correctament.");
    } catch (ProducteNoExisteix e) {

    System.out.println(e.getMessage());
    }
}

    private void testGetnumero() {
        System.out.println("Hi han " + cd.getNumProd() + " productes");
    }

    private HashMap<String, Integer> crearSimilitudes() {
        HashMap<String, Integer> similitudes = new HashMap<>();
        ArrayList<Producto> productosExistentes = cd.getProductosControl();
    
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
    private static void mostra_menu() {
        System.out.println("Escull operacio a realitzar");
        System.out.println("0 -> Sortir");
        System.out.println("1 -> Saber quantitat productes");
        System.out.println("2 -> Afegir Producte");
        System.out.println("3 -> Treure Producte");
        System.out.println("4 -> Llistar Productes");
        System.out.println("5 -> Set Productes");
        System.out.println("6 -> Cambiar similitud entre dos Productes");
        System.out.println("7 -> Calcular algoritmos");
        System.out.println("8 -> Guardar productos");
        System.out.println("9 -> Cargar productos");
        System.out.println("10 -> Guardar prestatgeries");
        System.out.println("11 -> Cargar prestatgeries");
    }

    private void tornar_menu() {
        System.out.println("Prem Enter per tornar al menu principal");
        s.nextLine();
    }}
