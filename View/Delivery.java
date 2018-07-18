/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.*;
//import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import javax.imageio.*;
import static javax.swing.ScrollPaneConstants.*;

/**
 *
 * @author Carlos
 */
public class Delivery extends JPanel {

    private int imageX, imageY;
    private BufferedImage image;
    private Mailman man;

    public Delivery(Mailman man) {
        this.man = man;
        this.setBackground(Color.WHITE);
        imageX = 0;
        try {
            image = ImageIO.read(new File("C:\\Users\\Carlos\\Documents\\NetBeansProjects\\MailSimulation\\src\\View\\Truck.jpg"));
        } catch (IOException e) {

        }
        image = resize(image, 300, 300);
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                repaint();
//                if (imageX < getWidth() - image.getWidth())
                imageX++;
            }
        };
        setPreferredSize(new Dimension(500, 500));
        Timer timer = new Timer(1, action);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, imageX, 0, null); // see javadoc for more info on the parameters          

    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Animation");
        Delivery d = new Delivery(new Mailman("Carlos"));
//        JScrollPane pane = new JScrollPane(d, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);

        f.setContentPane(d);
        f.setSize(2000, 800);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
