/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package View;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;

public class StartScreen implements ActionListener {
    JButton loadMap, start;
    JLabel choice;
    
    public StartScreen() {
        initStartScreen();
    }
    
    public void initStartScreen() {
        JFrame f = new JFrame("Mail Simulator");
        JPanel content = new JPanel();
        f.setContentPane(content);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        
        JLabel lb1 = new JLabel ("Welcome to Mail Simulator!");
        lb1.setFont(new Font("Abril Fatface", Font.BOLD, 24));
        lb1.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(lb1);
        content.add(Box.createRigidArea(new Dimension(0,40)));
        
        JLabel lb2 = new JLabel ("Please Load a Map to Continue.");
        lb2.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        lb2.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(lb2);
        content.add(Box.createRigidArea(new Dimension(0,40)));
        
        loadMap = new JButton("LOAD MAP");
        loadMap.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        loadMap.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadMap.addActionListener(this);
        content.add(loadMap);
        content.add(Box.createRigidArea(new Dimension(0, 40)));
        
        choice = new JLabel("You haven't chosen a file.");
        choice.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        choice.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(choice);
        content.add(Box.createRigidArea(new Dimension(0, 40)));
        
        start = new JButton("START THE GAME");
        start.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.addActionListener(this);
        start.setEnabled(false);
        content.add(start);
        content.add(Box.createRigidArea(new Dimension(0, 40)));
        
        
        f.setSize(500, 500);
        //f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("LOAD MAP")) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "CSV File", "csv");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                choice.setText("Load successful! You chose the file: " +
                        chooser.getSelectedFile().getName());
                start.setEnabled(true);
            }
        }
    }
    
    public static void main(String[] args){
        StartScreen sc = new StartScreen();
    }
}
