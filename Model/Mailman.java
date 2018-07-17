package Model;
import java.util.*;
import java.io.*;

public class Mailman {
    String name;
    private ArrayList<Map> maps;
    private PostOffice currentStation;
    private ArrayList<Mail> bag;
    private ArrayList<Mail> sorted;
    
    /**
     * NOTE: read csv files upon instantiation.
     */
    public Mailman(String name) {
        this.name = name;
    }
    
    public void addMap(Map m) {
        maps.add(m);  
    }
    
    /**
     * reads the CSV file. add a map for every region.
     */
    public void readMaps(File csv) {
        
    }
    
    /**
     * fetches the new batch of mails in the current region's
     * post office. add each mail to bag.
     */
    public void fetchMail() {
        
    }
    
    /**
     * puts all mail with destinations within
     * the current region in the ArrayList variable 
     * sorted, and then sorts it (any sort u prefer).
     */
    public void sortMail() {
        
    }
    
    /**
     * removes all mail from the current region.
     * to be called after displaying the required output.
     */
    public void deliverMail() {
        for (Mail m : sorted)
            bag.remove(m);
        sorted = new ArrayList<>();
    }
    
    public void displaySortedMails() {
        for(int i = 0; i < sorted.size(); i++)
            System.out.println(sorted.get(i).toString());
    }
    
    public void displayRoute() {
        
    }
}
