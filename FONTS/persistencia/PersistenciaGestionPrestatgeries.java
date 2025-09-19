package FONTS.persistencia;

import FONTS.domini.Prestatgeria;
import FONTS.domini.GestioPrestatgeries;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PersistenciaGestionPrestatgeries {

    private static final String DEFAULT_FILE_NAME = "DATA/prestatgeriesGuardadas.json";
    private final Gson gson;

    public PersistenciaGestionPrestatgeries() {
        this.gson = new Gson();
    }

    /*
     * Guarda todas las prestatgeries en un archivo JSON fijo.
    */
    public void guardarPrestatgeries(GestioPrestatgeries gestioPrestatgeries) {
        try (FileWriter writer = new FileWriter(DEFAULT_FILE_NAME)) {
            // Convertir el mapa de Prestatgeries a JSON
            String json = gson.toJson(gestioPrestatgeries.getPrestatgeries());
            writer.write(json);
            System.out.println("Prestatgeries guardadas exitosamente en " + DEFAULT_FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error al guardar las prestatgeries: " + e.getMessage());
        }
    }

    /*
     * Carga prestatgeries desde un archivo JSON especificado por un path.
     */
    public GestioPrestatgeries cargarPrestatgeries(String path) {
        File file = new File(path);

        // Comprobar si el archivo existe o si está vacío
        if (!file.exists() || file.length() == 0) {
            System.out.println("El archivo " + path + " no existe o está vacío.");
            return new GestioPrestatgeries(Collections.emptyMap()); // Retorna una instancia vacía
        }

        try (FileReader reader = new FileReader(file)) {
            // Definir el tipo de Map<String, Prestatgeria> que se deserializa
            Type type = new TypeToken<Map<String, Prestatgeria>>() {}.getType();
            Map<String, Prestatgeria> prestatgeries = gson.fromJson(reader, type);

            if (prestatgeries == null || prestatgeries.isEmpty()) {
                System.out.println("No se encontraron prestatgeries en el archivo " + path);
                return new GestioPrestatgeries(Collections.emptyMap()); // Retorna una instancia vacía
            }

            return new GestioPrestatgeries(prestatgeries);
        } catch (IOException e) {
            System.err.println("Error al cargar las prestatgeries desde " + path + ": " + e.getMessage());
            return new GestioPrestatgeries(Collections.emptyMap());
        } catch (com.google.gson.JsonSyntaxException e) {
            System.err.println("Error de sintaxis en el archivo JSON " + path + ": " + e.getMessage());
            return new GestioPrestatgeries(Collections.emptyMap());
        }
    }
}
