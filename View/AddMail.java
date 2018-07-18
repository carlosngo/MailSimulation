/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class AddMail  extends JFrame implements ActionListener{
    
    private JButton addMail, done;
    private JTextArea taContent;
    private JFrame f;
    
    public AddMail(){
        initAddMail();
    }
    
    public void initAddMail(){
        f = new JFrame();
        
        JPanel motherPnl = new JPanel();
        motherPnl.setLayout(new GridLayout());
        
        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
        
        // add mails title
        JLabel title = new JLabel("Add Mails");
        title.setFont(new Font("Abril Fatface", Font.PLAIN, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        p1.add(title);
        p1.add(Box.createRigidArea(new Dimension(0,40))); // add space
        
        //add mail button
        addMail = new JButton("Add Mail");
        addMail.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        addMail.setAlignmentX(Component.CENTER_ALIGNMENT);
        addMail.addActionListener(this);
        p1.add(addMail);
        p1.add(Box.createRigidArea(new Dimension(0,25))); // add space
        
        //done button
        done = new JButton("Done");
        done.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        done.setAlignmentX(Component.CENTER_ALIGNMENT);
        done.addActionListener(this);
        p1.add(done);
        p1.add(Box.createRigidArea(new Dimension(0,25))); // add space
        
        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        
        JLabel bag = new JLabel("Bag");
        bag.setAlignmentX(Component.CENTER_ALIGNMENT);
        p2.add(bag,BorderLayout.NORTH);
        
        taContent = new JTextArea(/* mail list here */);
        p2.add(taContent,BorderLayout.CENTER);
        
        
        
        motherPnl.add(p1);
        motherPnl.add(p2);
        f.add(motherPnl); // add panel to frame
        f.setSize(500,400); //set the compnents to fit in the frame
        f.setVisible(false);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        
    }
    
    public void actionPerformed (ActionEvent e){
        if(e.getActionCommand().equals("Done"))
            f.dispose();
    }
    /*
    public static void main(String[] args){
        AddMail am = new AddMail();
    }
*/
}
