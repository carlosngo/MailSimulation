/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailmansimulation;
//import Model.*;
//import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import javax.imageio.*;
/**
 *
 * @author Carlos
 */
public class Delivery extends JPanel {
    private int imageX, imageY;
    private BufferedImage image;
    private Mailman man;
    public Delivery (Mailman man) {
        this.man = man;
        imageX = 0;
        try {
            image = ImageIO.read(new File("C:\\Users\\Carlos\\Documents\\NetBeansProjects\\MailSimulation\\src\\View\\Motorcycle.jpg"));
        } catch (IOException e) {
            
        }
        System.out.println(imageX);
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                repaint();
//                if (imageX < getWidth())
                    imageX++;
            }        
        };
        Timer timer = new Timer(1, action);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, imageX, 0, null); // see javadoc for more info on the parameters          
        
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame("Animation");
        Delivery d = new Delivery(new Mailman("Carlos"));
        f.setContentPane(d);
        f.setSize(2000, 800);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
