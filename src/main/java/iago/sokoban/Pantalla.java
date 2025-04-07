package iago.sokoban;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Pantalla extends JPanel {
    private BufferedImage pared;
    private BufferedImage suelo;
    private BufferedImage personaje;
    private BufferedImage objetivo;
    private BufferedImage caja;
    
    private LevelManager levelManager;
    private int tileSize = 32;
    
    public Pantalla(LevelManager levelManager) {
        this.levelManager = levelManager;
        
        try {
            pared = ImageIO.read(new File("sprites/ladrillo.png"));
            suelo = ImageIO.read(new File("sprites/suelo.png"));
            personaje = ImageIO.read(new File("sprites/personaje.png"));
            objetivo = ImageIO.read(new File("sprites/suelopunto.png"));
            caja = ImageIO.read(new File("sprites/caja.png"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (pared == null) return;
        
        Nivel nivel = levelManager.getNivelActual();
        if (nivel == null) return;
        
        for (int y = 0; y < nivel.getAlto(); y++) {
            for (int x = 0; x < nivel.getAncho(); x++) {
                char celda = nivel.getCelda(x, y);
                int posX = x * tileSize;
                int posY = y * tileSize;
                

                if (celda == '#' || celda == ' ' || celda == '$' || celda == '@') {
                    g.drawImage(suelo, posX, posY, tileSize, tileSize, this);
                } else if (celda == '.' || celda == '*' || celda == '+') {
                    g.drawImage(objetivo, posX, posY, tileSize, tileSize, this);
                }
                
                switch (celda) {
                    case '#':
                        g.drawImage(pared, posX, posY, tileSize, tileSize, this);
                        break;
                    case '@':
                        g.drawImage(personaje, posX, posY, tileSize, tileSize, this);
                        break;
                    case '$':
                        g.drawImage(caja, posX, posY, tileSize, tileSize, this);
                        break;
                    case '*':
                        g.drawImage(caja, posX, posY, tileSize, tileSize, this);
                        break;
                    case '+':
                        g.drawImage(personaje, posX, posY, tileSize, tileSize, this);
                        break;
                }
            }
        }
    }
}