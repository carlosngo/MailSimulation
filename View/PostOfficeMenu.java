package View;

import Model.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class PostOfficeMenu extends JFrame implements ActionListener{
    
    private JButton postoffice, deliver, viewMails;
    Mailman man;
    MailsList ml;
    Delivery d;
    Form f;
    
    public PostOfficeMenu(Mailman man){
        super("Mail Simulator");
        this.man = man;
        initPostOfficeGUI();
    }
    
    public void initPostOfficeGUI(){
        
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        //title
        JLabel title = new JLabel(man.getCurrentStation().getName().toUpperCase());
        title.setFont(new Font("Abril Fatface", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(title);
        p.add(Box.createRigidArea(new Dimension(0,25))); // add space
        
        //add mails label
        postoffice = new JButton("Add Mail");
        postoffice.setAlignmentX(Component.CENTER_ALIGNMENT);
        postoffice.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        postoffice.addActionListener (this);
        p.add(postoffice);
        p.add(Box.createRigidArea(new Dimension(0,20))); // add space
        
        viewMails = new JButton("View Bag");
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
        if (man.getSorted().isEmpty())
            deliver.setEnabled(false);
        p.add(deliver);
        p.add(Box.createRigidArea(new Dimension(0,20))); // add space
        
        add(p); // add panel to frame
        pack(); //set the compnents to fit in the frame
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    public void actionPerformed (ActionEvent e){
        if(e.getActionCommand().equals("Deliver")){
            d = new Delivery(man);
            dispose();
        }
        else if(e.getActionCommand().equals("Add Mail")){
            f = new Form(man);
            dispose();
        }
        else {
            ml = new MailsList(this, man.getBag());
            setVisible(false);
        }
    }   
    
    public static void main(String[] args){
        PostOfficeMenu am = new PostOfficeMenu(new Mailman("Carlos"));
    }

}