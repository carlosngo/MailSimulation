package Model;

import java.util.*;
import java.io.*;

public class PostOffice extends Location {

    private ArrayList<Mail> mails;

    public PostOffice(String name, String region) {
        super(name, region);
        mails = new ArrayList<>();
    }

    public ArrayList<Mail> pushMail() {

    }

    public void generateMail() {

    }
}
