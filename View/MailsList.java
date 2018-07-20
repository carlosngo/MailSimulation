/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import static javax.swing.ScrollPaneConstants.*;
import static javax.swing.SwingConstants.*;

public class MailsList extends JFrame implements ActionListener{
    PostOfficeMenu po;
    ArrayList<Mail> mails;
    JButton done;
    
    public MailsList(PostOfficeMenu po, ArrayList<Mail> mails){
        this.mails = mails;
        this.po = po;
        initMailsList();
    }
    
    public void initMailsList(){
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel title = new JLabel("LIST OF MAILS");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Abril Fatface", Font.PLAIN, 36));
        p.add(title);
        p.add(Box.createRigidArea(new Dimension(0,10))); 
        
        for(int i = 0; i <= mails.size(); i++){
            JLabel reci, dest, dateTime;
            JPanel mail = new JPanel();
            reci = new JLabel("", CENTER);
            dest = new JLabel("", CENTER);
            dateTime = new JLabel("", CENTER);
            reci.setMinimumSize(new Dimension(120, 40));
            reci.setMaximumSize(new Dimension(120, 40));
            reci.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            reci.setAlignmentX(p.LEFT_ALIGNMENT);
            reci.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            dest.setMinimumSize(new Dimension(440, 40));
            dest.setMaximumSize(new Dimension(440, 40));
            dest.setFont(new Font("Times New Roman", Font.PLAIN, 18));            
            dest.setAlignmentX(p.CENTER_ALIGNMENT);
            dest.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            dateTime.setMinimumSize(new Dimension(220, 40));
            dateTime.setMaximumSize(new Dimension(220, 40));
            dateTime.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            dateTime.setAlignmentX(p.RIGHT_ALIGNMENT);
            dateTime.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            mail.setLayout(new BoxLayout(mail, BoxLayout.X_AXIS));
            if (i != 0) {
                reci.setText("  " + mails.get(i - 1).getRecipient() + "  ");
                dest.setText("  " + mails.get(i - 1).getDestination().getName() + "  ");
                dateTime.setText("  " + mails.get(i - 1).getDateTime().toString() + "  ");
            } else {
                reci.setText("  RECIPIENT  ");
                dest.setText("  DESTINATION  ");
                dateTime.setText("  DATE & TIME  ");
            }
            
            
            mail.add(reci);
            //mail.add(Box.createRigidArea(new Dimension(10, 0)));
            mail.add(dest);
            //mail.add(Box.createRigidArea(new Dimension(10, 0)));
            mail.add(dateTime);    
            p.add(mail);
        }
       
       done = new JButton("Done");
       done.setAlignmentX(Component.CENTER_ALIGNMENT);
       done.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
       done.addActionListener (this);
       p.add(Box.createRigidArea(new Dimension(0,10))); 
       p.add(done);
       JScrollPane sp = new JScrollPane(p, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
       
       add(sp);
       setSize(900, 600);
       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
       setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    public void actionPerformed (ActionEvent e){
        if(e.getActionCommand ().equals ("Done")){
            dispose();
            po.setVisible(true);
        }
    }
    
//    public static void main(String[] args) {
//        ArrayList<Mail> list = new ArrayList<>();
//        list.add(new Mail("Johanna", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(2018,2,28,4,5)));
//        list.add(new Mail("Miggy", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Stanley", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Martin", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Jeremy", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Melody", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Johanna", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Miggy", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Stanley", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Martin", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Jeremy", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Melody", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Johanna", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Miggy", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Stanley", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Martin", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Jeremy", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Melody", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Johanna", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Miggy", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Stanley", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Martin", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Jeremy", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Melody", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Johanna", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Miggy", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Stanley", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Martin", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Jeremy", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Melody", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Johanna", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Miggy", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Stanley", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Martin", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Jeremy", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        list.add(new Mail("Melody", new PostOffice("Manila Post Office", "Manila"), new Location("DLSU", "Manila"), new DateTime(1,2,3,4,5)));
//        MailsList ml = new MailsList(new PostOfficeMenu(new Mailman("Carlos")), list);
//    }
}
