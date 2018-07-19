package Model;

import java.util.*;
import java.io.*;

public class Mail implements Comparable<Mail> {

    private String recipient;
    private PostOffice origin;
    private Location destination;
    private DateTime dateTime;

    public Mail(String recipient, PostOffice origin, Location destination, DateTime dateTime) {
        this.recipient = recipient;
        this.origin = origin;
        this.destination = destination;
        this.dateTime = dateTime;
    }

    public String getRecipient() {
        return recipient;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Location getDestination() {
        return destination;
    }

    public PostOffice getOrigin() {
        return origin;
    }

    /**
     * Just use the getDistance() method of the Location class and return it.
     */
    public double getShortestPath() {
        return origin.getShortestPath(destination);
    }

    @Override
    public boolean equals(Object obj) {
        Mail m = (Mail) obj;
        return recipient.equals(m.recipient) && origin.equals(m.origin)
                && destination.equals(m.destination) && dateTime.equals(m.dateTime);
    }

    /**
     * Compares two mails. Returns a number less than 0 if this mail is less
     * than the other mail. If this mail's distance is equal to the other mail,
     * return 0. Otherwise, this function will return a number greater than 0.
     */
    @Override
    public int compareTo(Mail mail) {
        return getShortestPath() - mail.getShortestPath();
    }

    @Override
    public String toString() {
        return "Recipient: " + recipient + "\nPost Office: " + origin.getName()
                + "\nLocation: " + destination.getName() + "\nDate/Time: " + dateTime.getMonth()
                + "/" + dateTime.getDay() + "/" + dateTime.getYear() + " [" + dateTime.getHour()
                + ":" + dateTime.getMinute() + "]";
    }
}
