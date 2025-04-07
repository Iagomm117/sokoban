package iago.sokoban;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Sokoban {
    private JFrame frame;
    private Pantalla pantalla;
    private LevelManager levelManager;
    private Nivel nivel;
    private int WIDTH = 800;
    private int HEIGHT = 600;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Sokoban().iniciar());
        
    }

    private void iniciar() {
        levelManager = new LevelManager();
        levelManager.cargarNiveles("levels/niveles.txt");
        
        frame = new JFrame("Sokoban");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantalla = new Pantalla(levelManager);
        pantalla.setBackground(Color.black);
        frame.add(pantalla);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        leerTeclado();

    }
    
    private void leerTeclado() {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                Nivel nivel = levelManager.getNivelActual();
                if (nivel == null) return;
                
                int[] posJugador = nivel.getPosicionJugador();
                if (posJugador == null) return;
                
                int x = posJugador[0];
                int y = posJugador[1];
                
            switch(e.getKeyCode()) {
                    case 39 :
                        System.out.println("Derecha");
                        moverJugador(nivel, x, y, x+1, y);
                        break;
                    case 37:
                        System.out.println("Izquierda");
                        moverJugador(nivel, x, y, x-1, y);
                        break;
                    case 38:
                        System.out.println("Arriba");
                        moverJugador(nivel, x, y, x, y-1);
                        break;
                    case 40:
                        System.out.println("Abajo");
                        moverJugador(nivel, x, y, x, y+1);
                        break;
                    case 82 : //R 
                            SwingUtilities.invokeLater(() -> new Sokoban().iniciar());
                    break;
                }
                pantalla.repaint();
            }
        });
    }
    private void moverJugador(Nivel nivel, int xActual, int yActual, int xNuevo, int yNuevo) {
        char celdaActual = nivel.getCelda(xActual, yActual);
        char celdaNueva = nivel.getCelda(xNuevo, yNuevo);
        
        
        if (celdaNueva == ' ' || celdaNueva == '.') {
            char casillaActual;
            if (celdaActual == '+') {
                casillaActual = '.';
            } else {
                casillaActual = ' ';
            }
            nivel.setCelda(xActual, yActual, casillaActual);
    
            char nuevaCasilla;
            if (celdaNueva == '.') {
                nuevaCasilla = '+';
            } else {
                nuevaCasilla = '@';
            }
            nivel.setCelda(xNuevo, yNuevo, nuevaCasilla);
        } else if (celdaNueva == '$' || celdaNueva == '*') {
    int xSiguiente = xNuevo + (xNuevo - xActual);
    int ySiguiente = yNuevo + (yNuevo - yActual);
    char celdaSiguiente = nivel.getCelda(xSiguiente, ySiguiente);
            
            if (celdaSiguiente == ' ' || celdaSiguiente == '.') {
                                
               
            }
        }
    }
}