package Model;

public class Location {

    String name;
    String region;
    ArrayList<Edge> connections;

    public Location(String name, String region){
    	this.name = name;
    	this.region = region;
    }

    public int getDistance(Location l){
    	//calculate for the distance then return it
    }

    public String getRegion(){
    	return region;
    }

    public String getName(){
    	return name;
    }

    public void addConnection(Edge e){
    	connections.add(e);
    }

    public boolean equals(Object obj){
    	Location l = (Location) obj;
    	return name.equals(l.name) && region.equals(l.region);
    }
}
