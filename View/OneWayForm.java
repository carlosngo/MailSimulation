/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import Model.*;

/**
 *
 * @author Carlos
 */
public class OneWayForm extends JFrame implements ActionListener {
    private Mailman man;
    private JPanel content, locations;
    private JComboBox region, loc1, loc2;
    private JButton ok;
    private MainMenu m;
    public OneWayForm(Mailman man) {
        super("Mail Delivery Simulator");
        this.man = man;
        init();
    }
    
    public void init() {
        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("ADD ONE-WAY ROAD");
        title.setFont(new Font("Abril Fatface", Font.BOLD, 24));
        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel lbl = new JLabel("REGION");
        title.setFont(new Font("Abril Fatface", Font.BOLD, 18));
        content.add(lbl);
        
        String[] regions = new String[man.getMaps().size() + 1];
        regions[0] = "Select a region...";
        for (int i = 1; i < regions.length; i++)
            regions[i] = man.getMaps().get(i - 1).getRegion();
        region = new JComboBox(regions);
        region.setFont(new Font("Abril Fatface", Font.PLAIN, 16));
        region.addActionListener(this);
        content.add(region);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        
        locations = new JPanel();
        locations.setLayout(new BoxLayout(locations, BoxLayout.Y_AXIS));
        loc1 = new JComboBox();
        loc1.setFont(new Font("Abril Fatface", Font.PLAIN, 16));
        loc1.addActionListener(this);
        loc2 = new JComboBox();
        loc2.setFont(new Font("Abril Fatface", Font.PLAIN, 16));
        loc2.addActionListener(this);
        locations.add(loc1);
        locations.add(Box.createRigidArea(new Dimension(0, 10)));
        locations.add(new JLabel("|"));
        locations.add(new JLabel("|"));
        locations.add(new JLabel("v"));
        locations.add(Box.createRigidArea(new Dimension(0, 10)));
        locations.add(loc2);
        locations.setVisible(false);
        content.add(locations);
        
        ok = new JButton("Confirm");
        ok.setFont(new Font("Abril Fatface", Font.BOLD, 16));
        ok.addActionListener(this);
        content.add(ok);
        
        JButton back = new JButton("Done");
        back.setFont(new Font("Abril Fatface", Font.BOLD, 16));
        back.addActionListener(this);
        content.add(back);
        setContentPane(content);
        pack(); //set the compnents to fit in the frame
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(region)) {
            if (!region.getSelectedItem().equals("Select a region...")) {
                locations.setVisible(true);
                Map map = man.getMaps().get(region.getSelectedIndex() - 1);
                loc1.removeAllItems();
                loc2.removeAllItems();
                String def = "Select a location...";
                loc1.addItem(def);
                loc2.addItem(def);
                for (int i = 0; i < map.getLocations().size(); i++) {
                    loc1.addItem(map.getLocations().get(i).getName());
                    loc2.addItem(map.getLocations().get(i).getName());
                }
                pack();
            }
        } else if (e.getSource().equals(ok)) {
            String location1 = (String) loc1.getSelectedItem();
            String location2 = (String) loc2.getSelectedItem();
            if (location1 == null || location2 == null)
                return;
            if (location1.equals(location2) || location1.equals("Select a location...") || location2.equals("Select a location..."))
                JOptionPane.showMessageDialog(null, "Please select two valid distinct locations.");
            else {
                Map map = man.getMaps().get(region.getSelectedIndex() - 1);
//                System.out.println(map.getRegion());
//                System.out.println(map.getLocations().get(loc1.getSelectedIndex() - 1).getName());
//                System.out.println(map.getLocations().get(loc2.getSelectedIndex() - 1).getName());
                if (man.makeOneWay(map.getLocations().get(loc1.getSelectedIndex() - 1), map.getLocations().get(loc2.getSelectedIndex() - 1)))
                    JOptionPane.showMessageDialog(null, "Successfully made one-way.");
                else
                    JOptionPane.showMessageDialog(null, "The specified locations already have a one-way road between them.");
//                for (Edge edge : map.getLocations().get(loc1.getSelectedIndex() - 1).getConnections())
//                    System.out.println(edge.getEnd().getName());
            }
        } else if (e.getActionCommand().equals("Done")) {
            dispose();
            for (Map m : man.getMaps()) {
                m.calculateRoutes();
            }
            m = new MainMenu(man);          
        }
    }
    
}
