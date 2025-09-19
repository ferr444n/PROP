package FONTS.persistencia;

import FONTS.domini.GestionProductos;
import com.google.gson.*;

import java.io.*;

public class PersistenciaGestionProductos {

    private final String defaultPath = "DATA/productosGuardados.json";
    // Constructor sin parámetros
    public PersistenciaGestionProductos() {
        // No es necesario hacer nada aquí, ya que el archivo para guardar es fijo
    }

/*
     * Guarda el objeto GestionProductos como JSON en un archivo.
     */
    public void guardarGestionProductos(GestionProductos gestionProductos, String... customPath) throws IOException {
        String pathToUse = (customPath != null && customPath.length > 0) ? customPath[0] : this.defaultPath; 
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(pathToUse)) {
            gson.toJson(gestionProductos, writer); 
        }
    }

    /*
     * Carga el objeto GestionProductos desde un archivo JSON en un path personalizado.
     */
    public GestionProductos cargarGestionProductos(String customPath) throws IOException {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(customPath)) {
            return gson.fromJson(reader, GestionProductos.class);
        }
    }
}
