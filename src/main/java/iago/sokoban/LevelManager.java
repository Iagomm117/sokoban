package iago.sokoban;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LevelManager {
    private final ArrayList<Nivel> niveles;
    private int nivelActual = 0;

    public LevelManager() {
        this.niveles = new ArrayList<>();
    }
    
    public void cargarNiveles(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            ArrayList<String> nivelActual = new ArrayList<>();
            
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    if (!nivelActual.isEmpty()) {
                        niveles.add(new Nivel(new ArrayList<>(nivelActual)));
                        nivelActual.clear();
                    }
                } else if (!linea.startsWith("Level ") && !linea.startsWith("'")) {
                    nivelActual.add(linea);
                }
            }
            
            if (!nivelActual.isEmpty()) {
                niveles.add(new Nivel(nivelActual));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    public Nivel getNivelActual() {
        if (nivelActual >= 0 && nivelActual < niveles.size()) {
            return niveles.get(nivelActual);
        }
        return null;
    }
    
    public void siguienteNivel() {
        if (nivelActual < niveles.size() - 1) {
            nivelActual++;
        }
    }
    
    public void anteriorNivel() {
        if (nivelActual > 0) {
            nivelActual--;
        }
    }
}