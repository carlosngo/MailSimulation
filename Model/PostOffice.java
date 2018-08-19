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

    public void addMail(Mail m) {
        mails.add(m);
    }
    
    public ArrayList<Mail> pushMail() {
        return mails;
    }
}
