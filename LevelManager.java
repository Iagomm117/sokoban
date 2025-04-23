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
            ArrayList<String> nivelTemp = new ArrayList<>();
            
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    if (!nivelTemp.isEmpty()) {
                        niveles.add(new Nivel(new ArrayList<>(nivelTemp)));
                        nivelTemp.clear();
                    }
                } else if (!linea.startsWith("Level ") && !linea.startsWith("'")) {
                    nivelTemp.add(linea);
                }
            }
            
            if (!nivelTemp.isEmpty()) {
                niveles.add(new Nivel(nivelTemp));
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

    public void reiniciarNivel() {
        int nivelGuardado = nivelActual;
        niveles.clear();
        cargarNiveles("levels/niveles.txt");
        nivelActual = nivelGuardado;
    }
}
