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
    
    public double getShortestPath(Location l) {
        double min = Double.MAX_VALUE;
        for(Edge edge : getConnections()) 
            if(l.equals(edge.getEnd()))
                if(edge.getDistance() < min)
                    min = edge.getDistance();
        return min;
    }

    public void addMail(Mail m) {
        mails.add(m);
    }
    
    public ArrayList<Mail> pushMail() {
        return mails;
    }
}
