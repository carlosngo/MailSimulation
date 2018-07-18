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

public class Form extends JFrame implements ActionListener{
    
    private Mail mail;
    private JButton back, done;
    private JFrame f;
    private JTextField recipientInput;
    private JTextField destinationInput;
    private JComboBox mon, day, yr, hr, min;
    
    public Form(){
        initForm();
    }
    
    public void initForm(){
        f = new JFrame();
        
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        JLabel title = new JLabel("Form");
        title.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        p.add(title);
        
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        JLabel recipient = new JLabel("Recipient:");
        p1.add(recipient);
        recipientInput = new JTextField();
        recipientInput.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        p1.add(recipientInput);
        
        
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        JLabel destination = new JLabel("Destination:");
        p2.add(destination);
        destinationInput = new JTextField();
        destinationInput.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        p2.add(destinationInput);
        
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        JLabel date = new JLabel("Date (MM/DD/YY):");
        p3.add(date);
        Integer[] months = {1,2,3,4,5,6,7,8,9,10,11,12};
        mon = new JComboBox(months);
        p3.add(mon);
        Integer[] days = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        day = new JComboBox(days);
        p3.add(day);
        Integer[] years = null;
        Integer dummy = 2017;
        for(int i=0;i<30;i++)
            years[i] = dummy++;
        yr = new JComboBox(years);
        p3.add(yr);
        
        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        JLabel time = new JLabel("Time (HH/MM):");
        p4.add(date);
        Integer[] hours = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
        hr = new JComboBox(hours);
        p4.add(hr);
        Integer[] mins = null;
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
        f.add(p); // add panel to frame
        f.pack(); //set the compnents to fit in the frame
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
    
    public String getMailInfo(int year, int mon, int day, int hour, int min){
        mail = new Mail(recipientInput.getText(), destinationInput.getText(), new DateTime(year,mon,day,hour,min));
        return mail;
    }
    
    public void actionPerformed (ActionEvent e){
        if(e.getActionCommand().equals("Done")){
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
            getMailInfo(year,mon,day,hour,minute);
            f.dispose();
        }  
    } 
}
