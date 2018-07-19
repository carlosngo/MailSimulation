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

public class MainMenu extends JFrame implements ActionListener{
    
    private JComboBox choices;
    private JButton proceed, exit, select;
    Mailman man;
    
    public MainMenu(Mailman man){
        super("Mail Simulator");
        this.man = man;
        initMainMenu();
    }
    
    public void initMainMenu(){
        
        //create and set panel to box layout
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        //title
        JLabel title = new JLabel("MAIN MENU");
        title.setFont(new Font("Abril Fatface", Font.BOLD, 48));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(title);
        p.add(Box.createRigidArea(new Dimension(0,25))); // add space
        
        //main menu
        JLabel mainmenu = new JLabel("Where do you want to start?");
        mainmenu.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        mainmenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(mainmenu);
        p.add(Box.createRigidArea(new Dimension(0,10))); // add space
        
        //combo box
        String[] cities = new String[man.getMaps().size() + 1];
        cities[0] = "Select a region...";
        for (int i = 0; i < man.getMaps().size(); i++)
            cities[i + 1] = man.getMaps().get(i).getRegion();
        choices = new JComboBox(cities);
        choices.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        choices.addActionListener(this);
        p.add(choices);
        p.add(Box.createRigidArea(new Dimension(0,10))); // add space
        
        //start delivering button
        proceed = new JButton("Continue");
        proceed.setAlignmentX(Component.CENTER_ALIGNMENT);
        proceed.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        proceed.addActionListener (this);
        proceed.setEnabled (false);
        p.add(proceed);
        p.add(Box.createRigidArea(new Dimension(0,10))); // add space
        
        // exit button
        exit = new JButton("Exit");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        exit.addActionListener (this);
        p.add(exit);
        p.add(Box.createRigidArea(new Dimension(0,10))); // add space
       
        add(p); // add panel to frame
        pack(); //set the compnents to fit in the frame
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(500, 100);
    }
    
    //public void enableButtons (ActionEvent e){
    //}
    
    public void actionPerformed (ActionEvent e){
        if(e.getSource() == choices) {
            if(choices.getSelectedIndex() != 0)
                proceed.setEnabled(true);
            else
                proceed.setEnabled(false);
        }
        else if (e.getActionCommand ().equals ("Exit")){
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
            if (choice == 0)
                dispose();
        }
        else if(e.getActionCommand ().equals ("Continue")){
            man.setCurrentStation(man.getMaps().get(choices.getSelectedIndex() - 1).getPostOffice());
            PostOfficeMenu pm = new PostOfficeMenu(man);
            dispose();
        }
    }
    
    public static void main(String[] args) {
        MainMenu mm = new MainMenu(new Mailman("Carlos"));
    }
}
