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

public class Form extends JFrame implements ActionListener {

    private Mailman man;
    private JButton back, done;
    private JTextField recipientInput;
    private JComboBox destinationInput; // the list of locations in the region
    private JComboBox mon, day, yr, hr, min;

    public Form(Mailman man) {
        super("Mailman Simulation");
        this.man = man;
        initForm();
    }

    public void initForm() {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("MAIL INFORMATION");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Abril Fatface", Font.PLAIN, 36));
        p.add(title);

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        JLabel recipient = new JLabel("Recipient:");
        p1.add(recipient);
        recipientInput = new JTextField("", 20);
        recipientInput.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        recipientInput.addActionListener(this);
        recipientInput.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { 
                if (recipientInput.getText().isEmpty())
                    done.setEnabled(false);
            }
            public void removeUpdate(DocumentEvent e) { 
                if (recipientInput.getText().isEmpty())
                    done.setEnabled(false);
            }
            public void changedUpdate(DocumentEvent e) {
                if (recipientInput.getText().isEmpty())
                    done.setEnabled(false);
            }
        });
        p1.add(recipientInput);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        JLabel destination = new JLabel("Destination:");
        p2.add(destination);
        String[] choices = new String[man.getNoOfLocations() - 1 * (man.getMaps().size() - 1)];
        choices[0] = "Select a location...";
        int index = 1;
        for (Map m : man.getMaps())
            for (Location l : m.getLocations()) 
                if (l instanceof PostOffice)
                    ;
                else
                    choices[index++] = l.getName();
        destinationInput = new JComboBox(choices);
        destinationInput.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        destinationInput.addActionListener(this);
        p2.add(destinationInput);

        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        JLabel date = new JLabel("Date (MM/DD/YY):");
        p3.add(date);
        String[] months = {"Month", "January", "February", "March", "April",
            "May", "June", "July", "August", "September",
            "October", "November", "December"};
        mon = new JComboBox(months);
        mon.addActionListener(this);
        p3.add(mon);
        String[] days = new String[32];
        days[0] = "Day";
        for (int i = 1; i <= 31; i++) {
            days[i] = Integer.toString(i);
        }
        day = new JComboBox(days);
        day.addActionListener(this);
        p3.add(day);
        String[] years = new String[101];
        years[0] = "Year";
        for (int i = 1; i <= 100; i++) {
            years[i] = Integer.toString(1999 + i);
        }
        yr = new JComboBox(years);
        yr.addActionListener(this);
        p3.add(yr);

        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        JLabel time = new JLabel("Time (HH/MM):");
        p4.add(time);
        String[] hours = new String[25];
        hours[0] = "Hour";
        for (int i = 0; i <= 23; i++) {
            hours[i + 1] = Integer.toString(i);
        }
        hr = new JComboBox(hours);
        hr.addActionListener(this);
        p4.add(hr);
        String[] mins = new String[61];
        mins[0] = "Minute";
        for (int i = 0; i <= 59; i++) {
            mins[i + 1] = Integer.toString(i);
        }
        min = new JComboBox(mins);
        min.addActionListener(this);
        p4.add(min);

        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout());
        // back button
        back = new JButton("Back");
        back.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        back.addActionListener(this);
        p5.add(back);
        // done button
        done = new JButton("Done");
        done.setEnabled(false);
        done.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        done.addActionListener(this);
        p5.add(done);

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        p.add(p5);
        add(p); // add panel to frame
        pack(); //set the compnents to fit in the frame
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    public void actionPerformed(ActionEvent e) {
        if (!recipientInput.getText().trim().equals("")) {
            if (destinationInput.getSelectedIndex() != 0) {
                if (yr.getSelectedIndex() != 0) {
                    if (mon.getSelectedIndex() != 0) {
                        if (day.getSelectedIndex() != 0) {
                            if (hr.getSelectedIndex() != 0) {
                                if (min.getSelectedIndex() != 0) {
                                    done.setEnabled(true);
                                } else {
                                    done.setEnabled(false);
                                }
                            } else {
                                done.setEnabled(false);
                            }
                        } else {
                            done.setEnabled(false);
                        }
                    } else {
                        done.setEnabled(false);
                    }
                } else {
                    done.setEnabled(false);
                }
            } else {
                done.setEnabled(false);
            }
        } else {
            done.setEnabled(false);
        }
        if (e.getActionCommand().equals("Done")) {
            String recipient = recipientInput.getText();
            Location location = new Location((String) destinationInput.getSelectedItem(), man.getCurrentMap().getRegion());
            for (Map m : man.getMaps())
                for (Location l : m.getLocations())
                    if (l.equals(location))
                        location = l;
            int year = Integer.parseInt((String) yr.getSelectedItem());
            int month = mon.getSelectedIndex();
            int day = Integer.parseInt((String) this.day.getSelectedItem());
            int hour = Integer.parseInt((String) hr.getSelectedItem());
            int minute = Integer.parseInt((String) min.getSelectedItem());
            DateTime dt = new DateTime(year, month, day, hour, minute);
            Mail m = new Mail(recipient, man.getCurrentStation(), location, dt);
//            System.out.println(m.toString());
            man.getCurrentStation().addMail(m);
            man.getBag().add(m);
            PostOfficeMenu po = new PostOfficeMenu(man);
            dispose();
        } else if (e.getActionCommand().equals("Back")) {
            PostOfficeMenu m = new PostOfficeMenu(man);
            dispose();
        }
    }
}
