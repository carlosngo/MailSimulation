package Model;

public class Mail {
    private String recipient;
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
    
    @Override
    public boolean equals(Object obj) {
        
    }
}
