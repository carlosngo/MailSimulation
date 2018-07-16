package Model;

public class Mail implements Comparable<Mail> {
    private String recipient;
    private PostOffice origin;
    private Location destination;
    private DateTime dateTime;

    public Mail(String recipient, Location destination, DateTime dateTime) {
        this.recipient = recipient;
        this.destination = destination;
        this.dateTime = dateTime;
    }
    
    public Location getDestination() {
        return destination;
    }
    
    /**
     *  Just use the getDistance() method of the Location class and return it.
     */
    public int getShortestPath() {
        return destination.getDistance(origin);
    }
    
    @Override
    public boolean equals(Object obj) {
        Mail m = (Mail) obj;
        return this.recipient == m.recipient && this.origin == m.origin 
           && this.destination == m.destination && this.dateTime == m.dateTime;
    }
    
    /**
     *  Compares two mails. Returns a number less than 0 if this mail is less
     *  than the other mail. If this mail's distance is equal to the other mail,
     *  return 0. Otherwise, this function will return a number greater than 0.
     */
    @Override
    public int compareTo (Mail mail) {
        return getShortestPath() - mail.getShortestPath();
    }    
}
