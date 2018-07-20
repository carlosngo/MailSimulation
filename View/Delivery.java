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
    
    public static void main(String[] args) {
        Mailman m = new Mailman("Carlos");
        m.setCurrentStation(new PostOffice("Manila Post Office", "Manila"));
        ArrayList<Mail> list = m.getSorted();
        ArrayList<Mail> bag = m.getBag();
        bag.add(new Mail("Halo", new PostOffice("Manila Post Office", "Manila"), new Location("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Manila"), new DateTime(2018,2,28,4,5)));
        list.add(new Mail("Johanna", new PostOffice("Manila Post Office", "Manila"), new Location("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Manila"), new DateTime(2018,2,28,4,5)));
        list.add(new Mail("Miggy", new PostOffice("Manila Post Office", "Manila"), new Location("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Manila"), new DateTime(1,2,3,4,5)));
        list.add(new Mail("Stanley", new PostOffice("Manila Post Office", "Manila"), new Location("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Manila"), new DateTime(1,2,3,4,5)));
        list.add(new Mail("Martin", new PostOffice("Manila Post Office", "Manila"), new Location("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Manila"), new DateTime(1,2,3,4,5)));
        list.add(new Mail("Jeremy", new PostOffice("Manila Post Office", "Manila"), new Location("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Manila"), new DateTime(1,2,3,4,5)));
        list.add(new Mail("Melody", new PostOffice("Manila Post Office", "Manila"), new Location("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Manila"), new DateTime(1,2,3,4,5)));
        Delivery d = new Delivery(m);
    }
}
