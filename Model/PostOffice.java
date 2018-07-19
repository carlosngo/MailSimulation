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
        for(Edge edge : l.getConnections()) {
            double min = l.getConnections().get(0).getDistance();
            if(edge.getDistance() < min)
                min = edge.getDistance();
        }
        
        return min;
    }

    public void addMail(Mail m) {
        mails.add(m);
    }
    
    public ArrayList<Mail> pushMail() {
        return mails;
    }

    public boolean equals(Object obj) {
        PostOffice l = (PostOffice) obj;
        return name.equals(l.name) && region.equals(l.region);
    }
}
