package iago.sokoban;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Sokoban {
    private JFrame frame;
    private Pantalla pantalla;
    private LevelManager levelManager;
    private int WIDTH = 800;
    private int HEIGHT = 600;
    private final char suelo = ' ';
    private final char personaje = '@';
    private final char personajeObjetivo = '+';
    private final char objetivo = '.';
    private final char caja= '$';
    private final char cajaObjetivo = '*';
    
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
                if (posJugador == null) {return;}
                
                int x = posJugador[0];
                int y = posJugador[1];
                
            switch(e.getKeyCode()) {
                    case 39 :
                        //Derecha
                        movimientoJugador(nivel, x, y, x+1, y);
                        break;
                    case 37:
                        //Izquierda
                        movimientoJugador(nivel, x, y, x-1, y);
                        break;
                    case 38:
                        //Arriba
                        movimientoJugador(nivel, x, y, x, y-1);
                        break;
                    case 40:
                        //Abajo
                        movimientoJugador(nivel, x, y, x, y+1);
                        break;
                    case 82 : //R permite reinciar el juego.
                        
                        frame.dispose();
                        iniciar();
                        break;
                    case 32 : //espacio permite cambiar nivel
                        levelManager.siguienteNivel();
                        break;
                    case 8 : //retroceso permite retroceder en el nivel
                        levelManager.anteriorNivel();
                        break;
                        
                }
                pantalla.repaint();
            }
        });
    }
    private void movimientoJugador(Nivel nivel, int xActual, int yActual, int xNuevo, int yNuevo) {
        char celdaActual = nivel.getCelda(xActual, yActual);
        char celdaNueva = nivel.getCelda(xNuevo, yNuevo);
        
        
        
        if (celdaNueva == suelo || celdaNueva == objetivo) {
            char casillaActual;
            if (celdaActual == personajeObjetivo) {
                casillaActual = objetivo;
            } else {
                casillaActual = suelo;
            }
            nivel.setCelda(xActual, yActual, casillaActual);
    
            char nuevaCasilla;
            if (celdaNueva == objetivo) {
                nuevaCasilla = personajeObjetivo;
            } else {
                nuevaCasilla = personaje;
            }
            nivel.setCelda(xNuevo, yNuevo, nuevaCasilla);
            
        }
       
    }
    
}

