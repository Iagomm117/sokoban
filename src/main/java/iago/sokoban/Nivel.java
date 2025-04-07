package iago.sokoban;

import java.util.ArrayList;

public class Nivel {
    private ArrayList<String> mapa;
    
    public Nivel(ArrayList<String> mapa) {
        this.mapa = mapa;
    }
    
    public char getCelda(int x, int y) {
        if (y >= 0 && y < mapa.size() && x >= 0 && x < mapa.get(y).length()) {
            return mapa.get(y).charAt(x);
        }
        return ' ';
    }
    
    public int getAncho() {
        int max = 0;
        for (String linea : mapa) {
            if (linea.length() > max) {
                max = linea.length();
            }
        }
        return max;
    }
    
    public int getAlto() {
        return mapa.size();
    }
    
    public ArrayList<String> getMapa() {
        return mapa;
    }
}