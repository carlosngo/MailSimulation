/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailmansimulation;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class PostOfficeGUI extends JFrame implements ActionListener{
    
    private JButton postoffice, deliver, viewMails;
    private JFrame f;
    
    public PostOfficeGUI(){
        initPostOfficeGUI();
    }
    
    public void initPostOfficeGUI(){
        f = new JFrame();
        
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        //title
        JLabel title = new JLabel("Add Mails");
        title.setFont(new Font("Abril Fatface", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(title);
        p.add(Box.createRigidArea(new Dimension(0,25))); // add space
        
        //add mails label
        postoffice = new JButton("Post Office");
        postoffice.setAlignmentX(Component.CENTER_ALIGNMENT);
        postoffice.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        postoffice.addActionListener (this);
        p.add(postoffice);
        p.add(Box.createRigidArea(new Dimension(0,20))); // add space
        
        // view mails button
        viewMails = new JButton("View Mails");
        viewMails.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewMails.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        viewMails.addActionListener (this);
        p.add(viewMails);
        p.add(Box.createRigidArea(new Dimension(0,20))); // add space
        
        // deliver button
        deliver = new JButton("Deliver");
        deliver.setAlignmentX(Component.CENTER_ALIGNMENT);
        deliver.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        deliver.addActionListener (this);
        p.add(deliver);
        p.add(Box.createRigidArea(new Dimension(0,20))); // add space
        
        f.add(p); // add panel to frame
        f.pack(); //set the compnents to fit in the frame
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        
    }
    
    public void actionPerformed (ActionEvent e){
        if(e.getActionCommand().equals("Deliver")){
            f.dispose();
            Delivery d = new Delivery();
        }
        else if(e.getActionCommand().equals("Add Mail")){
            Form form = new Form();
        }
        else if(e.getActionCommand().equals("View Mails")){
            MailsList ml = new MailsList();
        }   
    }
    
    public static void main(String[] args){
        PostOfficeGUI am = new PostOfficeGUI();
    }

}
