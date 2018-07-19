/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author User
 */
public class MailsList extends JFrame implements ActionListener {
    ArrayList<Mail> list;
    
    public MailsList(ArrayList<Mail> list) {
        this.list = list;
    }
    
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
