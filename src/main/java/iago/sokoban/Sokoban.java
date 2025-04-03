package iago.sokoban;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Sokoban {
    
    private JFrame frame;
    private int WIDTH = 600;
    private int LENGTH = 600;

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> new Sokoban().iniciar()); 
        LevelManager level = new LevelManager();
        level.cargarNiveles();
        
    }

    private void iniciar() {
        frame = new JFrame("Sokoban");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Pantalla());
        frame.setSize(WIDTH, LENGTH);
        frame.setVisible(true);
        leerTeclado();

    }
    private void leerTeclado(){
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case 39 ->
                      System.out.println("derecha");
                    case 37 ->
                      System.out.println("izquierda");
                    case 38 ->
                      System.out.println("arriba");
                    case 40 ->
                      System.out.println("derecha");
                    }
                }
              
            

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
                }
    }

