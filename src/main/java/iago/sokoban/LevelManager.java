package iago.sokoban;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LevelManager {
    
    
    public void cargarNiveles(){
        String nombreArchivo ="levels/niveles.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))){
            String linea;
            while ((linea = br.readLine()) != null){
                System.out.println(linea);

            }
                 
        } catch (IOException e) {
            System.out.println("No se puede leer el nivel");
        }
    }
}
