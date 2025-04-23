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
    private final char suelo = ' ';
    private final char personaje = '@';
    private final char personajeObjetivo = '+';
    private final char objetivo = '.';
    private final char caja = '$';
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

        ajustarTamañoVentana(); // ajustar tamaño según el nivel

        frame.setVisible(true);
        leerTeclado();
    }

    private void leerTeclado() {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                Nivel nivel = levelManager.getNivelActual();
                if (nivel == null) return;

                int[] posJugador = nivel.getPosicionJugador();
                if (posJugador == null) return;

                int x = posJugador[0];
                int y = posJugador[1];

                switch (e.getKeyCode()) {
                    case 39 -> // Flecha Derecha
                        movimientoJugador(nivel, x, y, x + 1, y);
                    case 37 -> // Flecha Izquierda
                        movimientoJugador(nivel, x, y, x - 1, y);
                    case 38 -> // Flecha Arriba
                        movimientoJugador(nivel, x, y, x, y - 1);
                    case 40 -> // Flecha Abajo
                        movimientoJugador(nivel, x, y, x, y + 1);
                    case 82 -> {
                        // R
                        levelManager.reiniciarNivel();
                        ajustarTamañoVentana();
                    }
                    case 32 -> {
                        // Espacio
                        levelManager.siguienteNivel();
                        ajustarTamañoVentana();
                    }
                    case 8 -> {
                        // Backspace
                        levelManager.anteriorNivel();
                        ajustarTamañoVentana();
                    }
                }

                pantalla.repaint();

                if (nivelCompletado(nivel)) {
                    levelManager.siguienteNivel();
                    ajustarTamañoVentana();
                    pantalla.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    private void movimientoJugador(Nivel nivel, int xActual, int yActual, int xNuevo, int yNuevo) {
        char celdaNueva = nivel.getCelda(xNuevo, yNuevo);

        if (celdaNueva == suelo || celdaNueva == objetivo) {
            moverJugador(nivel, xActual, yActual, xNuevo, yNuevo);
        } else if (celdaNueva == caja || celdaNueva == cajaObjetivo) {
            int xDespues = xNuevo + (xNuevo - xActual);
            int yDespues = yNuevo + (yNuevo - yActual);
            char celdaDespues = nivel.getCelda(xDespues, yDespues);

            if (celdaDespues == suelo || celdaDespues == objetivo) {
                moverCaja(nivel, xNuevo, yNuevo, xDespues, yDespues);
                moverJugador(nivel, xActual, yActual, xNuevo, yNuevo);
            }
        }
    }

    private void moverJugador(Nivel nivel, int xActual, int yActual, int xNuevo, int yNuevo) {
        char celdaActual = nivel.getCelda(xActual, yActual);
        char celdaNueva = nivel.getCelda(xNuevo, yNuevo);

        nivel.setCelda(xActual, yActual, (celdaActual == personajeObjetivo) ? objetivo : suelo);
        nivel.setCelda(xNuevo, yNuevo, (celdaNueva == objetivo) ? personajeObjetivo : personaje);
    }

    private void moverCaja(Nivel nivel, int xActual, int yActual, int xNuevo, int yNuevo) {
        char celdaActual = nivel.getCelda(xActual, yActual);
        char celdaNueva = nivel.getCelda(xNuevo, yNuevo);

        nivel.setCelda(xActual, yActual, (celdaActual == cajaObjetivo) ? objetivo : suelo);
        nivel.setCelda(xNuevo, yNuevo, (celdaNueva == objetivo) ? cajaObjetivo : caja);
    }

    private boolean nivelCompletado(Nivel nivel) {
        for (int y = 0; y < nivel.getAlto(); y++) {
            for (int x = 0; x < nivel.getAncho(); x++) {
                if (nivel.getCelda(x, y) == objetivo || nivel.getCelda(x, y) == personajeObjetivo) {
                    return false;
                }
            }
        }
        return true;
    }

    private void ajustarTamañoVentana() {
        Nivel nivel = levelManager.getNivelActual();
        if (nivel != null) {
            int anchoPantalla = nivel.getAncho() * pantalla.getTileSize();
            int altoPantalla = nivel.getAlto() * pantalla.getTileSize();
            frame.setSize(anchoPantalla+19, altoPantalla+32);
            frame.setLocationRelativeTo(null);
        }
    }
}
