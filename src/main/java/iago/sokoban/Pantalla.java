package iago.sokoban;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Pantalla extends JPanel{
    
    private BufferedImage pared;
    private BufferedImage suelo;
    private BufferedImage personaje;
    private BufferedImage objetivo;
    private BufferedImage caja;
    private BufferedImage cajaEnObjetivo;
    private int MIN = 0;
    private int MAX = 9;
    
    public Pantalla(){
        
        try{
            pared = ImageIO.read(new File("sprites/ladrillo.png"));
            suelo = ImageIO.read(new File("sprites/suelo.png"));
            personaje = ImageIO.read(new File("sprites/personaje.png"));
            objetivo = ImageIO.read(new File("sprites/suelopunto.png"));
            caja = ImageIO.read(new File("sprites/caja.png"));
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent (Graphics g){
        super.paintComponents(g);
        
        if(pared == null){return;}
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(i == MIN || j == MIN  || i == MAX || j == MAX) {
                    g.drawImage(pared, i*32, j*32,32,32,this); 
                }
                else {
                   g.drawImage(suelo, i*32, j*32,32,32,this); 
                }
                g.drawImage(personaje,5*32,3*32,32,32,this);
               
            }
            
        }
    }
   
}

