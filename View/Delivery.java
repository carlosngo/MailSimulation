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
        animation = new AnimationPanel(man.sortNPlan());
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
                    if (!man.getRegionMail().isEmpty()) {
                        man.deliverMail();
                    }
                    initDelivery();
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (animation.getImageX() < border) {
                    animation.setImageX(animation.getImageX() + 1);
                } else {
                    if (man.getAllMail().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Delivery successful!\n"
                                + "There is no remaining mail in " + man.getName() + "'s bag.\n"
                                + "Returning to main menu.");
                        mm = new MainMenu(man);
                    } else {
                        man.setCurrentMap(getEarliestMail().getOrigin());
                        man.setCurrentLocation(getEarliestMail().getOrigin());
                        JOptionPane.showMessageDialog(null, "Delivery successful!\n"
                                + "There are mails left in " + man.getName() + "'s bag.\n"
                                + "Proceeding to the next Post Station.");
                        for (Mail m : man.getAllMail()) {
                            if (m.getOrigin().getName().equals(man.getCurrentMap().getPostOffice().getName())) {
                                
                                if (!man.getRegionMail().contains(m)) {
                                    man.getRegionMail().add(m);
                                }
                            }
                        }
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
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    public void initDelivery() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
//        animation = new AnimationPanel(man);
        mails = new MailPanel(man.getRegionMail());
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
        Mail earliest = man.getAllMail().get(0);
        for (Mail m : man.getAllMail()) {
            if (m.getDateTime().isBefore(earliest.getDateTime())) {
                earliest = m;
            }
        }
        return earliest;
    }

    public static void main(String[] args) {
        Map map = new Map("Manila");
        PostOffice po = map.getPostOffice();
        Location loc1 = new Location("DLSU", "Manila");
        loc1.setIndex(1);
        Location loc2 = new Location("ADMU", "Manila");
        loc2.setIndex(2);
        Location loc3 = new Location("USTE", "Manila");
        loc3.setIndex(3);
        Location loc4 = new Location("Mapua", "Manila");
        loc4.setIndex(4);

        po.addConnection(new Edge(loc1, 5));
        loc1.addConnection(new Edge(loc2, 5));
        loc1.addConnection(new Edge(loc4, 7.5));
        loc2.addConnection(new Edge(loc3, 10));
        loc2.addConnection(new Edge(loc4, 4));
        loc3.addConnection(new Edge(loc4, 15));
        loc1.addConnection(new Edge(loc4, 10));
        loc4.addConnection(new Edge(loc1, 5));
        map.addLocation(loc1);
        map.addLocation(loc2);
        map.addLocation(loc3);
        map.addLocation(loc4);
        map.calculateRoutes();
        Mailman m = new Mailman("Carlos");
        m.addMap(map);
        m.setCurrentMap(map);
        m.setCurrentLocation(po);
        m.addMail(new Mail("Johanna", po, loc1, new DateTime(1, 2, 3, 4, 5)));
        m.addMail(new Mail("Johanna", po, loc2, new DateTime(1, 2, 3, 4, 5)));
        m.addMail(new Mail("Johanna", po, loc3, new DateTime(1, 2, 3, 4, 5)));
        m.addMail(new Mail("Johanna", po, loc4, new DateTime(1, 2, 3, 4, 5)));
        Delivery d = new Delivery(m);
    }
}
