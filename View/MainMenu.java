/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailmansimulation;

//package View;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener{
    
    //ActionEvent e;
    String chosenCity;
    private JComboBox choices;
    private JButton proceed, exit, select;
    private JFrame f;
    
    public MainMenu(){
        initMainMenu();
    }
    
    public void initMainMenu(){
        f = new JFrame();
        
        //create and set panel to box layout
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        //title
        JLabel title = new JLabel("Mailman Simulation");
        title.setFont(new Font("Abril Fatface", Font.BOLD, 48));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(title);
        p.add(Box.createRigidArea(new Dimension(0,25))); // add space
        
        //main menu
        JLabel mainmenu = new JLabel("Main Menu");
        mainmenu.setFont(new Font("Abril Fatface", Font.PLAIN, 30));
        mainmenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(mainmenu);
        p.add(Box.createRigidArea(new Dimension(0,10))); // add space
        
        //combo box
        String[] cities = {"Where do you want to start?"};
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
        /*
        // select file button
        select = new JButton("Select File");
        select.setAlignmentX(Component.CENTER_ALIGNMENT);
        select.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        select.addActionListener (this);
        p.add(select);
        p.add(Box.createRigidArea(new Dimension(0,10))); // add space
        */
        f.add(p); // add panel to frame
        f.pack(); //set the compnents to fit in the frame
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
    
    //public void enableButtons (ActionEvent e){
    //}
    
    public void actionPerformed (ActionEvent e){
        if(e.getSource() == choices){
            JComboBox cb = (JComboBox) e.getSource();
            String chosenCity = (String) cb.getSelectedItem();
            if(!(chosenCity.equals("Where do you want to start?"))){
                f.setVisible(false);
                PostOfficeGUI am = new PostOfficeGUI();
            }
        }
        else if (e.getActionCommand ().equals ("Exit")){
            f.dispose();
        }
        else if(e.getActionCommand ().equals ("Continue")){
            f.setVisible(false);
            JComboBox cb = (JComboBox) e.getSource();
            chosenCity = (String) cb.getSelectedItem();
            PostOfficeGUI am = new PostOfficeGUI();
        }
        /*else if(e.getActionCommand ().equals ("Select File")){
            //open select file GUI
        }*/
    }
    /*
    public void changedUpdate (DocumentEvent e){ 
        // Gives notification that an attribute or set of attributes changed.
		
    }
	
    public void insertUpdate (DocumentEvent e){ 
        // Gives notification that there was an insert into the document.
		//btnSave.setEnabled (true);
    }
	
    public void removeUpdate (DocumentEvent e){ 
        // Gives notification that a portion of the document has been removed.
		//btnSave.setEnabled (true);
    }
    */
    
    public String getCity(){
        return chosenCity; 
    }
    
    public JFrame getFrame(){
        return f;
    }
    
    public static void main(String[] args){
        MainMenu mm = new MainMenu();
    }
}
