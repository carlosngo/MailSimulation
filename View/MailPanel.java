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
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.ScrollPaneConstants.*;
import static javax.swing.SwingConstants.*;
/**
 *
 * @author Carlos
 */
public class MailPanel extends JPanel {
    ArrayList<Mail> mails;
    
    public MailPanel(ArrayList<Mail> mails) {
        this.mails = mails;
        initMailPanel();
    }
    
    public void initMailPanel() {
        JPanel p = new JPanel();
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JLabel title = new JLabel("LIST OF MAILS");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Abril Fatface", Font.BOLD, 24));
        add(title);
        add(Box.createRigidArea(new Dimension(0,10))); 
        
        for(int i = 0; i <= mails.size(); i++){
            JLabel reci, dest, dateTime;
            JPanel mail = new JPanel();
            reci = new JLabel("", CENTER);
            dest = new JLabel("", CENTER);
            dateTime = new JLabel("", CENTER);
            
            reci.setMinimumSize(new Dimension(120, 40));
            reci.setMaximumSize(new Dimension(120, 40));
            reci.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            reci.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            dest.setMinimumSize(new Dimension(440, 40));
            dest.setMaximumSize(new Dimension(440, 40));
            dest.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            dest.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            dateTime.setMinimumSize(new Dimension(220, 40));
            dateTime.setMaximumSize(new Dimension(220, 40));
            dateTime.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            dateTime.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            mail.setLayout(new BoxLayout(mail, BoxLayout.X_AXIS));
            if (i != 0) {
                if (mails.get(i - 1).getRecipient().length() > 15)
                    reci.setText("  " + mails.get(i - 1).getRecipient().substring(0, 15) + "  ");
                else
                    reci.setText("  " + mails.get(i - 1).getRecipient() + "  ");
                if (mails.get(i - 1).getDestination().getName().length() > 50)
                    dest.setText("  " + mails.get(i - 1).getDestination().getName().substring(0, 50) + "  ");
                else
                    dest.setText("  " + mails.get(i - 1).getDestination().getName() + "  ");
                dateTime.setText("  " + mails.get(i - 1).getDateTime().toString() + "  ");
            } else {
                reci.setText("  RECIPIENT  ");
                dest.setText("  DESTINATION  ");
                dateTime.setText("  DATE & TIME  ");
            }
            
            
            mail.add(reci);
            mail.add(dest);
            mail.add(dateTime);    
            mail.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(mail);
        }
    }
}
