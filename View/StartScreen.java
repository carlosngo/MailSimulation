/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class StartScreen extends JFrame implements ActionListener {
    MainMenu m;
    JButton loadMap, start;
    JLabel choice;
    Mailman man;
    
    public StartScreen() {
        super("Mail Simulator");
        initStartScreen();
    }
    
    public void initStartScreen() {
        JPanel content = new JPanel();
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(Box.createRigidArea(new Dimension(0, 80)));
        String[] split = JOptionPane.showInputDialog(null, "My name is:").trim().split("\\s+");
        while (split[0].equals("")) 
            split = JOptionPane.showInputDialog(null, "My name is:").trim().split("\\s+");
        
        man = new Mailman(split[0]);
        JLabel lb1 = new JLabel ("Welcome to Mail Simulator, " + man.getName() + "!");
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
        
        add(content);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("LOAD MAP")) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "CSV File", "csv");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    if (man.readMaps(file)) {
                        start.setEnabled(true);
                        choice.setText("Load successful! You chose the file: " +
                            file.getName());
                    } else {
                        start.setEnabled(false);
                        choice.setText("Load failed! File could not be found.");
                    }
                } catch (IOException ex) { }
            }
        } else {
            m = new MainMenu(man);
            dispose();
        }
    }
    
    public static void main(String[] args){
        StartScreen sc = new StartScreen();
    }
}
