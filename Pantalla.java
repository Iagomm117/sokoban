package iago.sokoban;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Pantalla extends JPanel {
    private BufferedImage pared;
    private BufferedImage suelo;
    private BufferedImage personaje;
    private BufferedImage objetivo;
    private BufferedImage caja;
    private BufferedImage cajaObjetivo;
    
    
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
            cajaObjetivo = ImageIO.read(new File("sprites/caja2.png"));
        } catch (IOException e) {
            System.out.println("Error al cargar imágenes: " + e.getMessage());
        }
    }
    
    public int getTileSize() {
        return tileSize;
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
                
                switch (celda) {
                    case '#' -> g.drawImage(pared, posX, posY, tileSize, tileSize, this);
                    case '@', '+' -> g.drawImage(personaje, posX, posY, tileSize, tileSize, this);
                    case '$' -> g.drawImage(caja, posX, posY, tileSize, tileSize, this);
                    case '*' -> g.drawImage(cajaObjetivo, posX, posY, tileSize, tileSize, this);
                    case ' ' -> g.drawImage(suelo, posX, posY, tileSize, tileSize, this);
                    case '.' -> g.drawImage(objetivo, posX, posY, tileSize, tileSize, this);
                }
            }
        }
    }
}
