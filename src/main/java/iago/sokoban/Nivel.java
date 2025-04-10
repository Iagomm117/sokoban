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
    for (int i = 0; i < mapa.size(); i++) {
        String linea = mapa.get(i);
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
    public void setCelda(int x, int y, char c) {
        if (y >= 0 && y < mapa.size() && x >= 0 && x < mapa.get(y).length()) {
            StringBuilder sb = new StringBuilder(mapa.get(y));
            sb.setCharAt(x, c);
            mapa.set(y, sb.toString());
        }
    }
    
    public int[] getPosicionJugador() {
        for (int y = 0; y < mapa.size(); y++) {
            String fila = mapa.get(y);
            for (int x = 0; x < fila.length(); x++) {
                char c = fila.charAt(x);
                if (c == '@' || c == '+') {
                    return new int[]{x, y};
                }
            }
        }
        return null;
    }
}