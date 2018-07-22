/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.*;
import java.util.HashSet;
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
public class Delivery extends JFrame {

    private Mailman man;
    private MainMenu mm;
    private PostOfficeMenu po;
    private AnimationPanel animation;
    private JScrollPane animationPane;
    private MailPanel mails;
    private int border;
    private Timer timer;
    
    public Delivery(Mailman man) {
        this.man = man;
        animation = new AnimationPanel(man);
        HashSet<Integer> set = new HashSet<>();
        initDelivery();
        pack();
        for (JLabel lbl : animation.getDestinations()) {
            set.add(lbl.getX());
            border = lbl.getX() + lbl.getWidth();
        }
        
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                animation.repaint();
                if (set.contains(animation.getImageX())) {
                    if (!man.getSorted().isEmpty())
                        man.deliverMail();
                    initDelivery();
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (animation.getImageX() < border)
                    animation.setImageX(animation.getImageX() + 1);
                else {
                    if (man.getBag().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Delivery successful!\n"
                                + "There is no remaining mail in " + man.getName() + "'s bag.\n"
                                + "Returning to main menu.");
                        mm = new MainMenu(man);
                    } else {
                        man.setCurrentStation(getEarliestMail().getOrigin());
                        JOptionPane.showMessageDialog(null, "Delivery successful!\n"
                                + "There are mails left in " + man.getName() + "'s bag.\n"
                                + "Proceeding to the next Post Station.");
                        man.sortMail();
                        po = new PostOfficeMenu(man);
                    }
                    dispose();
                    timer.stop();
                }
                    
            }
        };
        timer = new Timer(0, action);
        timer.start();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    public void initDelivery() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
//        animation = new AnimationPanel(man);
        mails = new MailPanel(man.getSorted());
        JScrollPane animationPane = new JScrollPane(animation, VERTICAL_SCROLLBAR_NEVER, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane mailPane = new JScrollPane(mails, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);

        content.add(animationPane);
        content.add(mailPane);
        setContentPane(content);
//        pack();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public Mail getEarliestMail() {
        Mail earliest = man.getBag().get(0);
        for (Mail m : man.getBag()) 
            if (m.getDateTime().isBefore(earliest.getDateTime()))
                earliest = m;
        return earliest;
    }
}
