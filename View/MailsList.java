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

public class MailsList extends JFrame implements ActionListener{
    
    Mailman m;
    JButton done = new JButton();
    
    public MailsList(){
        m = new Mailman("messenger boy");
        initMailsList();
    }
    
    public void initMailsList(){
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        JLabel mail = new JLabel();
        for(int i=0;i<m.getBag().size();i++){
            mail.setText(m.getBag().get(i).toString());
            p.add(mail);
            p.add(Box.createRigidArea(new Dimension(0,10)));
        }
       
       done = new JButton("Done");
       done.setAlignmentX(Component.CENTER_ALIGNMENT);
       done.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
       done.addActionListener (this);
       p.add(done);
       p.add(Box.createRigidArea(new Dimension(0,10))); 
    }
    
    public void actionPerformed (ActionEvent e){
        if(e.getActionCommand ().equals ("Done")){
            dispose();
        }
    }
}
