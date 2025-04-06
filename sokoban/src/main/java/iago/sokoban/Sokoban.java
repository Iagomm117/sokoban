package iago.sokoban;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Sokoban {
    private JFrame frame;
    private Pantalla pantalla;
    private LevelManager levelManager;
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
                switch(e.getKeyCode()) {
                    case 39 :
                        System.out.println("Derecha");
                    case 37:
                        System.out.println("Izquierda");;
                    case 38:
                        System.out.println("Arriba");
                    case 40:
                        System.out.println("Abajo");
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
}