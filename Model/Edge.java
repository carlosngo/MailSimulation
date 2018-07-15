package Model;

public class Edge {

    private Location end;
    private double distance;

    public Edge(Location end, double distance){
    	this.end = end;
    	this.distance = distance;
    }

    public Location getEnd(){
    	return end;
    }

    public double getDistance(){
    	return distance;
    }
}

