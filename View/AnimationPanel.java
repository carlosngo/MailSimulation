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
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.imageio.*;
import static javax.swing.ScrollPaneConstants.*;

/**
 *
 * @author Carlos
 */
public class AnimationPanel extends JPanel {

    private int imageX, border;
    private BufferedImage image;
    private Mailman man;
    private JLabel route;
    private ArrayList<JLabel> destinations = new ArrayList<>();
    
    public AnimationPanel(Mailman man) {
        
        this.man = man;
        this.setBackground(Color.WHITE);
        imageX = 1;
        try {
            image = ImageIO.read(new File("C:\\Users\\Carlos\\Documents\\NetBeansProjects\\MailSimulation\\src\\View\\Truck.jpg"));
        } catch (IOException e) {

        }
        JPanel routePanel = new JPanel();
        routePanel.setLayout(new BoxLayout(routePanel, BoxLayout.X_AXIS));
        image = resize(image, 300, 300);
        StringBuilder sb = new StringBuilder();
        String office = man.getCurrentStation().getName();
        for (int i = 0; i < 25; i++) 
            if (i < office.length())
                sb.append(office.charAt(i));
            else
                sb.append(" ");
        route = new JLabel(sb.toString());
        route.setFont(new Font("Abril Fatface", Font.BOLD, 18));
        routePanel.add(route);
        routePanel.add(Box.createRigidArea(new Dimension(60, 0)));
        for (Mail m : man.getSorted()) {
            sb = new StringBuilder();
            String location = m.getDestination().getName();
            for (int i = 0; i < 25; i++) 
                if (i < location.length())
                    sb.append(location.charAt(i));
                else
                    sb.append(" ");
            JLabel lbl = new JLabel(sb.toString());
            lbl.setFont(new Font("Abril Fatface", Font.BOLD, 18));
            routePanel.add(lbl);
            destinations.add(lbl);
            routePanel.add(Box.createRigidArea(new Dimension(60, 0)));
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        add(Box.createRigidArea(new Dimension(0, 300)));
        add(routePanel);
        revalidate();
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        border = destinations.get(destinations.size() - 1).getX() + destinations.get(destinations.size() - 1).getWidth();
        g.drawLine(30, 200, getWidth() - 30, 200);
        for (int i = 100; i <= border + 100; i += 100) 
            g.fillRect(i, 240, 33, 20);
        g.drawImage(image, imageX, 0, null); // see javadoc for more info on the parameters
        g.drawLine(30, 300, getWidth() - 30, 300);
    }
    
    public int getImageX() {
        return imageX;
    }
    
    public int getBorderX() {
        return border;
    }
    
    public ArrayList<JLabel> getDestinations() {
        return destinations;
    }
    
    public void setImageX(int x) {
        imageX = x;
    }
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
