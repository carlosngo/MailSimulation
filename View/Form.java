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

public class Form extends JFrame implements ActionListener{
    
    private Mailman man;
    private JButton back, done;
    private JTextField recipientInput;
    private JComboBox destinationInput; // the list of locations in the region
    private JComboBox mon, day, yr, hr, min;
    
    public Form(Mailman man){
        super("Mailman Simulation");
        this.man = man;
        initForm();
    }
    
    public void initForm(){
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        JLabel title = new JLabel("MAIL INFORMATION");
        title.setFont(new Font("Abril Fatface", Font.PLAIN, 36));
        p.add(title);
        
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        JLabel recipient = new JLabel("Recipient:");
        p1.add(recipient);
        recipientInput = new JTextField("", 20);
        recipientInput.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        p1.add(recipientInput);
        
        
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        JLabel destination = new JLabel("Destination:");
        p2.add(destination);
//        String[] choices = new String[man.getCurrentMap().getLocations().size()];
//        choices[0] = "Select a location...";
//        for (int i = 1; i < choices.length; i++) 
//            choices[i] = man.getCurrentMap().getLocations().get(i).getName();
//        
        destinationInput = new JComboBox();//choices);
        destinationInput.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        p2.add(destinationInput);
        
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        JLabel date = new JLabel("Date (MM/DD/YY):");
        p3.add(date);
        String[] months = {"Month", "January", "February", "March", "April", 
                           "May", "June", "July", "August", "September",
                           "October", "November", "December"};
        mon = new JComboBox(months);
        p3.add(mon);
        String[] days = new String[32];
        days[0] = "Day";
        for (int i = 1; i <= 31; i++)
            days[i] = Integer.toString(i);
        day = new JComboBox(days);
        p3.add(day);
        String[] years = new String[101];
        years[0] = "Year";
        for (int i = 1; i <= 100; i++)
            years[i] = Integer.toString(1999 + i);
        yr = new JComboBox(years);
        p3.add(yr);
        
        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        JLabel time = new JLabel("Time (HH/MM):");
        p4.add(date);
        String[] hours = new String[25];
        hours[0] = "Hour";
        for (int i = 0; i <= 23; i++)
            hours[i + 1] = Integer.toString(i);
        hr = new JComboBox(hours);
        p4.add(hr);
        Integer[] mins = new  Integer[60];
        Integer dummyMin = 0;
        for(int i=0;i<60;i++)
            mins[i] = dummyMin++;
        min = new JComboBox(mins);
        p4.add(min);
        
        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout());
        // back button
        back = new JButton("Back");
        back.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        back.addActionListener (this);
        p5.add(back);
        // done button
        done = new JButton("Done");
        done.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        done.addActionListener (this);
        p5.add(done);
        
        p.add(p1); p.add(p2); p.add(p3); p.add(p4); p.add(p5);
        add(p); // add panel to frame
        pack(); //set the compnents to fit in the frame
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(500, 100);
    }
        
    public void actionPerformed (ActionEvent e){
        if(e.getActionCommand().equals("Done")){
            String recipient = recipientInput.getText();
            JComboBox y = (JComboBox) yr;
            int year = (int) yr.getSelectedItem();
            JComboBox m = (JComboBox) mon;
            int mon = (int) this.mon.getSelectedItem();
            JComboBox d = (JComboBox) day;
            int day = (int) this.day.getSelectedItem();
            JComboBox h = (JComboBox) hr;
            int hour = (int) hr.getSelectedItem();
            JComboBox minuteBox = (JComboBox) min;
            int minute = (int) min.getSelectedItem();
            dispose();
//            man.getCurrentPostOffice().addMail(new Mail(recipient,));
        }  
    } 
    
    public static void main(String[] args) {
        Form f = new Form(new Mailman("Carlos"));
    }
}
