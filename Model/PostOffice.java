package Model;

import java.util.*;
import java.io.*;

public class PostOffice extends Location {

    private ArrayList<Mail> mails;

    public PostOffice(String name, String region) {
        super(name, region);
        mails = new ArrayList<>();
    }
    
    public ArrayList<Mail> getMails() {
        return mails;    
    }
    
    public int getShortestPath(Location l) {
        
    }

    public void addMail(Mail m) {

    }
    
    public ArrayList<Mail> pushMail() {
        
    }

    public boolean equals(Object obj) {
        PostOffice l = (PostOffice) obj;
        return name.equals(l.name) && region.equals(l.region);
    }
}
