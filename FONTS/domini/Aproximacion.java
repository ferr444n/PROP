package FONTS.domini;

import java.util.*;
import java.util.stream.Collectors;


public class Aproximacion implements AlgoritmoEstanteria{
    private int costomaximo = 0;


    public Aproximacion() {

    }
    @Override
    public Vector<Producto> ejecutarAlgoritmo(ArrayList<Producto> productos, int filas, int columnas) {
        List<Producto> resultadoAprox = ArbolMaximo(productos, productos.size());
        
        Vector<Producto> ListaProductos = new Vector<>(filas * columnas);
        
        for (int i = 0; i < filas; ++i) {
            for (int j = 0; j < columnas; ++j) {
                if (i * columnas + j < resultadoAprox.size()) {
                    ListaProductos.add(resultadoAprox.get(i * columnas + j));
                }
            }
        }
        
        return ListaProductos;
    }

    @Override
    public Integer getCostoMaximo() {
        return this.costomaximo;
    }

    public class Arista {
        int inicio;
        int fin;
        int peso;
        //creamos esta clase para poder relacionar los vertices de una manera sencilla
        Arista(int inicio, int fin, int peso) {
            this.inicio = inicio;
            this.fin = fin;
            this.peso = peso;
        }
    }

    private  int encontrarRaiz(int vertice, Integer[] padres) {
        if (padres[vertice] <= -1) return vertice;
        else {
            int raiz = encontrarRaiz(padres[vertice], padres);
            padres[vertice] = raiz;
            return raiz;
        }
    }

    private  boolean conectar(int v1, int v2, Integer[] padres) {
        int conjA = encontrarRaiz(v1, padres);
        int conjB = encontrarRaiz(v2, padres);
        //buscamos que los vertices sean de conjuntos disjuntos es decir que no esten unidos para ahorrarnos ciclos
        if (conjA != conjB) {
            if (padres[conjA] <= padres[conjB]) {
                padres[conjA] = padres[conjB] + padres[conjA];
                padres[conjB] = conjA;
            } 
            else {
                padres[conjB] = padres[conjA] + padres[conjB];
                padres[conjA] = conjB;
            }
            return true;
        }
        return false;
    }

    public  void aplicarKruskal(List<Arista> grafo, List<Arista> arbolMaximo, Integer[] padres) {
        for (Arista arista : grafo) {
            
            if (conectar(arista.inicio, arista.fin, padres)) {
                arbolMaximo.add(arista);
            }
            
        }
    }

    private  void realizarDFS(Map<Integer, List<Integer>> grafo, int vertice, List<Integer> ciclo) {
        //realizamos un DFS para tener como recorrer el maximum spanning tree
        while (!grafo.get(vertice).isEmpty()) {
            int siguienteVertice = grafo.get(vertice).remove(0);
            realizarDFS(grafo, siguienteVertice, ciclo);
        }
        ciclo.add(vertice);
    }

    private  void eliminarRepetidos(List<Integer> listaFinal, List<Integer> ciclo) {
        //Elimnimamos los vertices repetidos de la lista del DFS
        listaFinal.addAll(ciclo.stream().distinct().collect(Collectors.toList()));
    }

    public  Integer calcularCosto(List<Integer> listaVertices, List<Arista> aristas) {
        int costoTotal = 0;

        // Iteramos sobre los vértices consecutivos en la lista
        for (int i = 0; i < listaVertices.size() - 1; i++) {
            int verticeActual = listaVertices.get(i);
            int siguienteVertice = listaVertices.get(i + 1);

            // Buscamos la arista que conecta estos dos vértices
            for (Arista arista : aristas) {
                if ((arista.inicio == verticeActual && arista.fin == siguienteVertice) ||
                    (arista.fin == verticeActual && arista.inicio == siguienteVertice)) {
                    costoTotal += arista.peso;
                    break;
                }
            }
        }

        // Añadir la última arista para cerrar el ciclo (conectar el primer y último vértice)
        int primerVertice = listaVertices.get(0);
        int ultimoVertice = listaVertices.get(listaVertices.size() - 1);
        for (Arista arista : aristas) {
            if ((arista.inicio == primerVertice && arista.fin == ultimoVertice) ||
                (arista.fin == primerVertice && arista.inicio == ultimoVertice)) {
                costoTotal += arista.peso;
                break;
            }
        }

        return costoTotal;
    }

    private List<Integer> a2opt(List<Integer> cycle, int i, int j) {
        List<Integer> newcycle = new ArrayList<>(cycle.size());
        //creamos una lista que va de 0 al vertice i+1
       newcycle.addAll(cycle.subList(0, i + 1));
       //añadimos las dos aristas que queremos pero permutadas
        for (int k = j; k > i; k--) {
            newcycle.add(cycle.get(k));
        }
        //añadimos los vertices que faltan
        newcycle.addAll(cycle.subList(j + 1, cycle.size()));
        
        return newcycle;
    }



    private List<Integer> hillClimbing(List<Integer> solucionini, List<Arista> grafo) {
        boolean haymejora = true;
        List<Integer> bestcycle = new ArrayList<>(solucionini);
        costomaximo = calcularCosto(solucionini, grafo);

        while(haymejora) {
            haymejora = false;
            //permutamos las aristas con el a2opt para asi poder explorar mas soluciones
            for (int i = 0; i < solucionini.size() - 1; ++i) {
                for (int j = i + 1; j < solucionini.size(); ++j) {
                    List<Integer> newcycle = a2opt(bestcycle, i, j);
                    int newcost = calcularCosto(newcycle, grafo);

                    if (newcost > costomaximo){
                        haymejora = true;
                        bestcycle = newcycle;
                        costomaximo = newcost;
                    }
                }
            }
        }
        return bestcycle;
    }

    public List<Producto> ArbolMaximo(List<Producto> productos, int nproductos) {
        //Si no hay productos o solo hay un producto se devuelve directamente la lista de productos
        if (productos.isEmpty() || productos.size() == 1) {
            return productos;
        }
        else {
        //Mapeamos los productos para asi poder tener una referencia suya dentro del grafo que crearemos
        //Creamos el grafo con integers porque su utilizacion es más fácil e intuitiva
        Map<Integer, Producto> productosmapped = new HashMap<Integer, Producto>();
        List<Arista> grafo = new ArrayList<>();
        for (int i = 0; i < nproductos; ++i) {
            productosmapped.put(i, productos.get(i));
        }
        //Creamos el grafo
        for (int i = 0; i < nproductos; ++i) {
            for (int j = 0; j < productos.get(i).getSimilitudes().size(); ++j) {
                int origen = i;
                int destino = j;
                int peso = productos.get(i).getSimilitud(productos.get(j).getNombre()); //obtener la similitud entre producto en posicion i y j
                grafo.add(new Arista(origen, destino, peso));
            }
        }
        // Ordenar las aristas por peso de mayor a menor
        grafo.sort((a, b) -> Integer.compare(b.peso, a.peso));
        //Creamos la solucion
        List<Arista> arbolMaximo = new ArrayList<>();
        //Creamos un método para poder tener las raices de los vertices y asi poder unirlo sin crear ciclos (de momento)
        Integer[] padres = new Integer[nproductos];
        Arrays.fill(padres, -1);

        aplicarKruskal(grafo, arbolMaximo, padres);

        // Duplicamos las aristas para poder obtener un grafo euleriano
        Map<Integer, List<Integer>> grafoDuplicado = new HashMap<>();
        for (Arista arista : arbolMaximo) {
            grafoDuplicado.computeIfAbsent(arista.inicio, k -> new ArrayList<>()).add(arista.fin);
            grafoDuplicado.computeIfAbsent(arista.fin, k -> new ArrayList<>()).add(arista.inicio);
        }

        // Realizar DFS para obtener el ciclo euleriano
        List<Integer> cicloEuleriano = new ArrayList<>();
        realizarDFS(grafoDuplicado, 0, cicloEuleriano);
        //Eliminamos repetidos para obtener el ciclo hamiltoniano
        List<Integer> cicloSinRepetidos = new ArrayList<>();
        eliminarRepetidos(cicloSinRepetidos, cicloEuleriano);

        //Usamos las estrategia de hillclimbing para obtener la mejor solucion posible
        List<Integer> resultadoOPT = new ArrayList<>();
        resultadoOPT = hillClimbing(cicloSinRepetidos, grafo);

        //Como antes hemos indexado los productos ahora podemos añadir los productos a la solucion
        List<Producto> solucion = new ArrayList<>(resultadoOPT.size());
        for (int i = 0; i < resultadoOPT.size(); ++i) solucion.add(productosmapped.get(resultadoOPT.get(i))); //obtenemos el producto del resultadoOPT

        return solucion;
        }
    }
    

}
